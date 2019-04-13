package gui;/*
import algorithmns.croa.CROAParamAnalysis;
import algorithmns.equations.IEquation;
import algorithmns.equations.Rosenbrock;
import algorithmns.scroa.SCROAParamAnalysis;
import configuration.configuration.globalConfig;
 */

import algorithmns.IAlgorithm;
import algorithmns.croa.CROA;
import algorithmns.equations.*;
import algorithmns.scroa.SCROA;
import configuration.configuration.globalConfig;
import gui.ChartFactory.ChartFactory;
import gui.comboBoxItems.ComboBoxItem;
import gui.updateObject.IUpdateable;
import gui.updateObject.UpdateObject;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import org.jzy3d.maths.Range;
import org.jzy3d.plot3d.builder.Mapper;

import java.util.concurrent.CyclicBarrier;

import static configuration.configuration.globalConfig.csvWriter;
/*import gui.updateObject.IUpdateable;
import gui.updateObject.UpdateObject;*/

public class Controller implements IUpdateable {


    @FXML
    private ComboBox<String> functionComboBox;

    @FXML
    private Button startButton;

    @FXML
    private Button stopButton;

    @FXML
    private Button pauseButton;

    @FXML
    private Button enableLogging;

    @FXML
    private HBox chartHBox;

    @FXML
    private Label label_Algo1;

    @FXML
    private Label label_Algo2;

    @FXML
    private Label label_Iteration;

    @FXML
    private Label sliderValue;

    @FXML
    private Slider slider;

    volatile IEquation currentEquation;
    volatile Mapper mapper;
    volatile ChartFactory chartFactory;
    volatile ChartFactory chartFactory2;

    volatile Thread worker;
    volatile Thread worker2;

    volatile IAlgorithm croa;
    volatile IAlgorithm scroa;

    @FXML
    public Pane chartLeft;

    @FXML
    public Pane chartRight;

    volatile boolean paused = false;
    volatile boolean showSurface = true;


    boolean initalized = false;

    volatile CyclicBarrier cyclicBarrier;

    String selectedCombo = "Rosenbrock";



    @FXML
    public void initialize(){

        startButton.setDisable(false);
        stopButton.setDisable(true);
        pauseButton.setDisable(true);
        slider.setDisable(false);

        initializeComboBox();

        slider.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2) {

                double sliderValueDouble = slider.getValue();

                int sliderValueRounded = (int)(sliderValueDouble - (sliderValueDouble % 500));

                sliderValue.setText("Iterations: "+sliderValueRounded);

                globalConfig.Iterations = sliderValueRounded;
            }
        });

        System.out.println("JavaFx Initialized");

        //JZY3D initialize ChartFactory
        chartFactory = new gui.ChartFactory.ChartFactory();
        chartFactory2 = new gui.ChartFactory.ChartFactory();
        int pointsPerMM = 120;

        currentEquation = new Rosenbrock();
        //globalConfig.ConfigurationAlgorithm = currentEquation.getConfiguration();
        mapper = new Mapper() {
            @Override
            public double f(double x, double y) {
                return currentEquation.calculateValue(x,y);
            }
        };

        Range xrange = new Range((float)currentEquation.getBoundary().getMinX(), (float)currentEquation.getBoundary().getMaxX());
        Range yrange = new Range((float)currentEquation.getBoundary().getMinY(), (float)currentEquation.getBoundary().getMaxY());
        // JavaFX
        chartLeft = chartFactory.getChart(xrange,yrange, mapper, pointsPerMM,currentEquation.getPercColoring());
        chartRight = chartFactory2.getChart(xrange,yrange, mapper, pointsPerMM,currentEquation.getPercColoring());
        chartHBox.getChildren().addAll(chartLeft,chartRight);

        chartHBox.setHgrow(chartLeft, Priority.ALWAYS);
        chartHBox.setHgrow(chartRight,Priority.ALWAYS);
    }


    //Change Equation after selected
    @FXML
    public void comboBoxAction(ActionEvent event){

        if(!selectedCombo.equals(functionComboBox.getSelectionModel().toString())){
            selectedCombo = functionComboBox.getSelectionModel().getSelectedItem();

            switch (selectedCombo){

                case "Rosenbrock":  currentEquation = new Rosenbrock();
                                    break;

                case "Rastirgin" :  currentEquation = new Rastrigin();
                                    break;

                case "Ackley"    :  currentEquation = new Ackley();
                                    break;

                case "MishrasBird": currentEquation = new MishrasBird();
                                    break;
            }
            reinitialize();
        }
    }

    private void initializeComboBox() {

        functionComboBox.getItems().addAll("Rosenbrock","Rastirgin","Ackley","MishrasBird");
        //Set the cellFactory property
        functionComboBox.setCellFactory(listview -> new ComboBoxItem());
        // Set Icon as Selections the buttonCell property
        //functionComboBox.setButtonCell(new ComboBoxItem());

    }


    //Neues setzen der Charts
    private void reinitialize(){

        chartHBox.getChildren().removeAll(chartLeft,chartRight);

        //JZY3D initialize ChartFactory
        chartFactory = new gui.ChartFactory.ChartFactory();
        chartFactory2 = new gui.ChartFactory.ChartFactory();
        int pointsPerMM = 80;
        //globalConfig.ConfigurationAlgorithm = currentEquation.getConfiguration();
        mapper = new Mapper() {
            @Override
            public double f(double x, double y) {
                return currentEquation.calculateValue(x,y);
            }
        };

        Range xrange = new Range((float)currentEquation.getBoundary().getMinX(), (float)currentEquation.getBoundary().getMaxX());
        Range yrange = new Range((float)currentEquation.getBoundary().getMinY(), (float)currentEquation.getBoundary().getMaxY());
        // JavaFX
        chartLeft = chartFactory.getChart(xrange,yrange, mapper, pointsPerMM,currentEquation.getPercColoring());
        chartRight = chartFactory2.getChart(xrange,yrange, mapper, pointsPerMM,currentEquation.getPercColoring());

        chartFactory.showSurface(showSurface);
        chartFactory2.showSurface(showSurface);
        chartHBox.getChildren().addAll(chartLeft,chartRight);

        chartHBox.setHgrow(chartLeft, Priority.ALWAYS);
        chartHBox.setHgrow(chartRight,Priority.ALWAYS);
    }



    @FXML
    private void startSimulation() {


        startButton.setText("Start");
        functionComboBox.disableProperty().setValue(true);
        startButton.setDisable(true);
        stopButton.setDisable(false);
        pauseButton.setDisable(false);
        slider.setDisable(true);

        if (paused){

            croa.goOn();
            scroa.goOn();


        }else {

            if (initalized) {
                reinitialize();
            }

            //Cyclic to synchronize both croa and scroa iterations
            cyclicBarrier = new CyclicBarrier(2);
            //globalConfig.ConfigurationAlgorithm = equation.getConfiguration();

            croa = new CROA(currentEquation, this, 1, cyclicBarrier);
            scroa = new SCROA(currentEquation, this, 2, cyclicBarrier);

            worker = new Thread(croa);
            worker2 = new Thread(scroa);

            worker.start();
            worker2.start();

            initalized = true;
            System.out.println("Finished starting all threads");
        }
    }


    @FXML
    public void stopSimulation() {


        startButton.setText("Start");
        slider.setDisable(false);
        stopButton.setDisable(true);
        pauseButton.setDisable(true);
        startButton.setDisable(false);
        paused = false;
        scroa.stop();
        croa.stop();
        functionComboBox.disableProperty().setValue(false);

    }


    @FXML
    private void pauseSimulation() {

        startButton.setText("continue");
        slider.setDisable(false);
        paused = true;
        stopButton.setDisable(false);
        startButton.setDisable(false);
        pauseButton.setDisable(true);
        croa.pause();
        scroa.pause();
        functionComboBox.disableProperty().setValue(true);

    }

    @FXML
    private void toggleSurface(){

        showSurface = !showSurface;
        chartFactory.showSurface(showSurface);
        chartFactory2.showSurface(showSurface);


    }


    public void enableLogging(){

        if(globalConfig.loggin==false){
            globalConfig.loggin=true;
            enableLogging.setText("Logging on");
        }else {

            globalConfig.loggin=false;
            enableLogging.setText("Logging off");
        }
    }


    @Override
    public void update(UpdateObject updateObject) {

            if (updateObject.getIteration() == globalConfig.Iterations) {
                try {
                    csvWriter.addRecord(Integer.toString(updateObject.getAlgorithmCounter()), Integer.toString(updateObject.getAlgorithmCounter()), ("(" + updateObject.getBestPoint().x + "|" + updateObject.getBestPoint().y + ")"), "" + updateObject.getBestPoint().z);
                } catch (Exception exp) {
                    System.out.println(exp);
                }
            }




            /*
             * Update GUI
             *
             */
        Platform.runLater(() -> {
            if (updateObject.getAlgorithmCounter() == 1) {

                label_Algo1.setText("CROAParamAnalysis best solution: " + updateObject.getBestPoint().toString() );

            } else if (updateObject.getAlgorithmCounter() == 2) {

                label_Algo2.setText("SCROAParamAnalysis best solution: " + updateObject.getBestPoint().toString());
            }

            label_Iteration.setText("Iteration: " + updateObject.getIteration());
        });


            /*
             * Update Console
             *
             */
            System.out.println("Update Received Best point of algorithmn " + updateObject.getAlgorithmCounter()
                    + " : Iteration: " + updateObject.getIteration() + " " + updateObject.getBestPoint().toParseFormat()
                    + " Population count : " + updateObject.getPoints().size());


            for (int i = 0; i < updateObject.getPoints().size(); i++) {
                if (updateObject.getPoints().get(i).x >= currentEquation.getBoundary().getMaxX()
                        || updateObject.getPoints().get(i).y >= currentEquation.getBoundary().getMaxY()
                        || updateObject.getPoints().get(i).x <= currentEquation.getBoundary().getMinX()
                        || updateObject.getPoints().get(i).y <= -currentEquation.getBoundary().getMaxY()) {
                    System.out.println("Bad Point in ALgo" + updateObject.getAlgorithmCounter() + " : " + updateObject.getPoints().get(i).toParseFormat());
                }
            }

            //Output aller Punkte am Ende
            if (updateObject.getIteration() == globalConfig.Iterations) {
                for (int i = 0; i < updateObject.getPoints().size(); i++) {
                    System.out.println(updateObject.getPoints().get(i).toParseFormat());
                }
            }

            if (updateObject.getAlgorithmCounter() == 1) {
                chartFactory.updatePointsInChart(updateObject.getPoints());
            } else if (updateObject.getAlgorithmCounter() == 2) {
                chartFactory2.updatePointsInChart(updateObject.getPoints());
            }
    }



}
