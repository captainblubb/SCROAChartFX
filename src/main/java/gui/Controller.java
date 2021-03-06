package gui;

import algorithmns.IAlgorithm;
import algorithmns.croa.CROA;
import algorithmns.equations.*;
import algorithmns.scroa.SCROA;
import algorithmns.scroa.pso.PSO;
import configuration.configuration.GlobalConfig;
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

import static configuration.configuration.GlobalConfig.csvWriter;
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

    volatile IEquation currentEquation1;
    volatile IEquation currentEquation2;
    volatile Mapper mapper1;
    volatile Mapper mapper2;
    volatile ChartFactory chartFactory;
    volatile ChartFactory chartFactory2;

    volatile Thread worker;
    volatile Thread worker2;

    volatile IAlgorithm algorithmnLeft;
    volatile IAlgorithm algorithmnRight;

    @FXML
    public Pane chartLeft;

    @FXML
    public Pane chartRight;

    volatile boolean paused = false;
    volatile boolean showSurface = true;


    @FXML
    public ComboBox<String> algoLeft;

    @FXML
    public ComboBox<String> algoRight;

    boolean initalized = false;

    volatile CyclicBarrier cyclicBarrier;

    String selectedCombo = "Rastirgin";



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

                GlobalConfig.Iterations = sliderValueRounded;
            }
        });

        algoLeft.getSelectionModel().select(0);

        algoRight.getSelectionModel().select(0);

        functionComboBox.getSelectionModel().select(0);

        System.out.println("JavaFx Initialized");

        //JZY3D initialize ChartFactory
        chartFactory = new gui.ChartFactory.ChartFactory();
        chartFactory2 = new gui.ChartFactory.ChartFactory();
        int pointsPerMM = 120;

        currentEquation1 = new Rastrigin("CROA");
        currentEquation2 = new Rastrigin("SCROA");
        //GlobalConfig.ConfigurationAlgorithm = currentEquation.getConfiguration();
        mapper1 = new Mapper() {
            @Override
            public double f(double x, double y) {
                return currentEquation1.calculateValue(x,y);
            }
        };
        mapper2 = new Mapper() {
            @Override
            public double f(double x, double y) {
                return currentEquation2.calculateValue(x,y);
            }
        };

        Range xrange1= new Range((float)currentEquation1.getBoundary().getMinX(), (float)currentEquation1.getBoundary().getMaxX());
        Range yrange1 = new Range((float)currentEquation1.getBoundary().getMinY(), (float)currentEquation1.getBoundary().getMaxY());

        Range xrange2= new Range((float)currentEquation2.getBoundary().getMinX(), (float)currentEquation2.getBoundary().getMaxX());
        Range yrange2 = new Range((float)currentEquation2.getBoundary().getMinY(), (float)currentEquation2.getBoundary().getMaxY());



        // JavaFX
        chartLeft = chartFactory.getChart(xrange1,yrange1, mapper1, pointsPerMM,currentEquation1.getPercColoring());
        chartRight = chartFactory2.getChart(xrange2,yrange2, mapper2, pointsPerMM,currentEquation2.getPercColoring());
        chartHBox.getChildren().addAll(chartLeft,chartRight);

        chartHBox.setHgrow(chartLeft, Priority.ALWAYS);
        chartHBox.setHgrow(chartRight,Priority.ALWAYS);

        initalized=true;

    }


    //Change Equation after selected
    @FXML
    public void comboBoxAction(ActionEvent event){

        if(!selectedCombo.equals(functionComboBox.getSelectionModel().toString())){
            selectedCombo = functionComboBox.getSelectionModel().getSelectedItem();

            switch (selectedCombo){

                case "Rosenbrock":
                                    currentEquation1 = new Rosenbrock("CROA");
                                    currentEquation2 = new Rosenbrock("SCROA");
                                    break;

                case "Rastirgin" :  currentEquation1 = new Rastrigin("CROA");
                                    currentEquation2 = new Rastrigin("SCROA");
                                    break;

                case "Ackley"    :  currentEquation1 = new Ackley("CROA");
                                    currentEquation2 = new Ackley("SCROA");
                                    break;

                case "MishrasBird": currentEquation1 = new MishrasBird("CROA");
                                    currentEquation2 = new MishrasBird("SCROA");
                                    break;
            }
            reinitialize();
        }
    }

    public IEquation getEquation(String algorithmn) {
        if (!selectedCombo.equals(functionComboBox.getSelectionModel().toString())) {
            selectedCombo = functionComboBox.getSelectionModel().getSelectedItem();

            switch (selectedCombo) {

                case "Rosenbrock":
                    return new Rosenbrock(algorithmn);
                case "Rastirgin":
                    return new Rastrigin(algorithmn);
                case "Ackley":
                    return new Ackley(algorithmn);
                case "MishrasBird":
                    return new MishrasBird(algorithmn);
            }
        }
        return new Rosenbrock("CROA");
    }


    //Change Equation after selected
    @FXML
    public void comboBoxActionAlgoLeft(ActionEvent event){

    }


    //Change Equation after selected
    @FXML
    public void comboBoxActionAlgoRight(ActionEvent event){

    }

    private void initializeComboBox() {

        functionComboBox.getItems().addAll("Rastirgin","Rosenbrock","Ackley");
        //Set the cellFactory property
        functionComboBox.setCellFactory(listview -> new ComboBoxItem());
        // Set Icon as Selections the buttonCell property
        //functionComboBox.setButtonCell(new ComboBoxItem());

        algoLeft.getItems().addAll("CROA","PSO","SCROA");
        algoRight.getItems().addAll("CROA","PSO","SCROA");


    }


    //Neues setzen der Charts
    private void reinitialize(){

        chartHBox.getChildren().removeAll(chartLeft,chartRight);

        //JZY3D initialize ChartFactory
        chartFactory = new gui.ChartFactory.ChartFactory();
        chartFactory2 = new gui.ChartFactory.ChartFactory();
        int pointsPerMM = 120;
        //GlobalConfig.ConfigurationAlgorithm = currentEquation.getConfiguration();
        mapper1 = new Mapper() {
            @Override
            public double f(double x, double y) {
                return currentEquation1.calculateValue(x,y);
            }
        };
        mapper2 = new Mapper() {
            @Override
            public double f(double x, double y) {
                return currentEquation2.calculateValue(x,y);
            }
        };

        Range xrange1= new Range((float)currentEquation1.getBoundary().getMinX(), (float)currentEquation1.getBoundary().getMaxX());
        Range yrange1 = new Range((float)currentEquation1.getBoundary().getMinY(), (float)currentEquation1.getBoundary().getMaxY());

        Range xrange2= new Range((float)currentEquation2.getBoundary().getMinX(), (float)currentEquation2.getBoundary().getMaxX());
        Range yrange2 = new Range((float)currentEquation2.getBoundary().getMinY(), (float)currentEquation2.getBoundary().getMaxY());


        // JavaFX
        chartLeft = chartFactory.getChart(xrange1,yrange1, mapper1, pointsPerMM,currentEquation1.getPercColoring());
        chartRight = chartFactory2.getChart(xrange2,yrange2, mapper2, pointsPerMM,currentEquation2.getPercColoring());

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

            algorithmnLeft.goOn();
            algorithmnRight.goOn();


        }else {

            if (!initalized) {
                reinitialize();
            }

            //Cyclic to synchronize both algorithmnLeft and algorithmnRight iterations
            cyclicBarrier = new CyclicBarrier(2);
            //GlobalConfig.ConfigurationAlgorithm = equation.getConfiguration();


            //Set Algorithmn
            switch (algoLeft.getSelectionModel().getSelectedItem()){

                case "CROA" :
                    label_Algo1.setText("CROA no Solution yet");
                    currentEquation1 = getEquation("CROA");
                    algorithmnLeft = new CROA(currentEquation1, this, 1, cyclicBarrier);
                    break;
                case "SCROA":
                    label_Algo1.setText("SCROA no Solution yet");
                    currentEquation1 = getEquation("SCROA");
                    algorithmnLeft = new SCROA(currentEquation1, this, 1, cyclicBarrier);
                    break;
                case "PSO":
                    label_Algo1.setText("PSO no Solution yet");
                    currentEquation1 = getEquation("PSO");
                    algorithmnLeft = new PSO(currentEquation1, this, 1, cyclicBarrier);
                    break;
            }

            //Set Algorithmn
            switch (algoRight.getSelectionModel().getSelectedItem()){

                case "CROA" :
                    label_Algo2.setText("CROA no Solution yet");
                    currentEquation2 = getEquation("CROA");
                    algorithmnRight = new CROA(currentEquation1, this, 2, cyclicBarrier);
                    break;
                case "SCROA":
                    label_Algo2.setText("SCROA no Solution yet");
                    currentEquation2 = getEquation("SCROA");
                    algorithmnRight = new SCROA(currentEquation1, this, 2, cyclicBarrier);
                    break;
                case "PSO":

                    label_Algo2.setText("PSO no Solution yet");
                    currentEquation2 = getEquation("PSO");
                    algorithmnRight = new PSO(currentEquation1, this, 2, cyclicBarrier);
                    break;
            }

            reinitialize();

            worker = new Thread(algorithmnLeft);
            worker2 = new Thread(algorithmnRight);

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
        algorithmnRight.stop();
        algorithmnLeft.stop();
        worker.interrupt();
        worker2.interrupt();
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
        algorithmnLeft.pause();
        algorithmnRight.pause();
        functionComboBox.disableProperty().setValue(true);

    }

    @FXML
    private void toggleSurface(){

        showSurface = !showSurface;
        chartFactory.showSurface(showSurface);
        chartFactory2.showSurface(showSurface);


    }


    public void enableLogging(){

        if(GlobalConfig.loggin==false){
            GlobalConfig.loggin=true;
            enableLogging.setText("Logging on");
        }else {

            GlobalConfig.loggin=false;
            enableLogging.setText("Logging off");
        }
    }


    @Override
    public void update(UpdateObject updateObject) {

        if (updateObject.getIteration() == GlobalConfig.Iterations) {
            try {
                csvWriter.addRecord(Integer.toString(updateObject.getAlgorithmCounter()), Integer.toString(updateObject.getAlgorithmCounter()), ("(" + updateObject.getBestPoint().x + "|" + updateObject.getBestPoint().y + ")"), "" + updateObject.getBestPoint().z);
            } catch (Exception exp) {
                System.out.println(exp);
            }
        }


        /*
         * update charts
         */
        if (updateObject.getAlgorithmCounter() == 1) {
            chartFactory.updatePointsInChart(updateObject.getPoints());
        } else if (updateObject.getAlgorithmCounter() == 2) {
            chartFactory2.updatePointsInChart(updateObject.getPoints());
        }

        /*
         * update text
         */
        Platform.setImplicitExit(false);
        Platform.runLater(() -> {

            if (updateObject.getAlgorithmCounter() == 1) {

                label_Algo1.setText(updateObject.getAlgoName()+" best solution: " + updateObject.getBestPoint().toString());

            } else if (updateObject.getAlgorithmCounter() == 2) {

                label_Algo2.setText(updateObject.getAlgoName()+" best solution: " + updateObject.getBestPoint().toString());
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

        //Output aller Punkte am Ende
        if (updateObject.getIteration() == GlobalConfig.Iterations) {
            for (int i = 0; i < updateObject.getPoints().size(); i++) {
                System.out.println(updateObject.getPoints().get(i).toParseFormat());
            }
        }

    }



}
