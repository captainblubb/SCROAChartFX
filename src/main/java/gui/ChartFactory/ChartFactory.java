package gui.ChartFactory;

import gui.updateObject.Point3d;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.jzy3d.chart.AWTChart;
import org.jzy3d.chart.Chart;
import org.jzy3d.colors.Color;
import org.jzy3d.colors.ColorMapper;
import org.jzy3d.colors.colormaps.ColorMapRainbow;
import org.jzy3d.javafx.JavaFXChartFactory;
import org.jzy3d.maths.Coord3d;
import org.jzy3d.maths.Range;
import org.jzy3d.plot3d.builder.Builder;
import org.jzy3d.plot3d.builder.Mapper;
import org.jzy3d.plot3d.builder.concrete.OrthonormalGrid;
import org.jzy3d.plot3d.primitives.Point;
import org.jzy3d.plot3d.primitives.Shape;
import org.jzy3d.plot3d.rendering.canvas.OffscreenCanvas;
import org.jzy3d.plot3d.rendering.canvas.Quality;

import java.util.ArrayList;
import java.util.List;


public class ChartFactory {

    volatile AWTChart chart;
    volatile Pane pane;
    boolean initialized = false;
    private volatile ArrayList<Point> jzy3dPoints = new ArrayList<>();
    private volatile Mapper mapper;
    private volatile Range range;
    private volatile int steps;
    private volatile JavaFXChartFactory factory;
    private volatile ImageView imageView;
    private Range xRange;
    private Range yRange;
    private volatile Shape currSurface;
    private volatile float coloringPerc;

    public Pane getChart(Range xrange, Range yrange, Mapper function, int pointsPerMM,float coloringPerc){

        if (!initialized || pane == null) {
            this.yRange = yrange;
            this.xRange = xrange;
            this.coloringPerc = coloringPerc;
            this.mapper = function;
            this.range = range;
            this.steps = pointsPerMM;
            // Jzy3d
            factory = new JavaFXChartFactory();
            AWTChart chart = createChart(factory, "offscreen");
            imageView = factory.bindImageView(chart);

            //Save the chart for later mutations
            this.chart = chart;

            // JavaFX
            pane = new Pane();
            pane.getChildren().add(imageView);

            //imageView.fitHeightProperty().bind(pane.heightProperty());
            //imageView.fitWidthProperty().bind(pane.widthProperty());
            //imageView.xProperty().bind(pane.x);

            addSceneSizeChangedListener(chart, pane);
        }

        return pane;

    }

    public void addSceneSizeChangedListener(Chart chart, Pane scene) {
        try {
            scene.widthProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                    // System.out.println("scene Width: " + newSceneWidth);
                    resetTo(chart, scene.widthProperty().get(), scene.heightProperty().get());
                    // System.out.println("resize ok");
                }
            });
            scene.heightProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                    // System.out.println("scene Height: " + newSceneHeight);
                    resetTo(chart, scene.widthProperty().get(), scene.heightProperty().get());
                    // System.out.println("resize ok");
                }
            });
        }catch (Exception exp){
            System.out.println("Could not update size of Chart "+exp);
        }
    }

    //Bewegung reset
    protected void resetTo(Chart chart, double width, double height) {

        try {

        if ( width>0 &&height>0 && chart.getCanvas() instanceof OffscreenCanvas) {
            OffscreenCanvas canvas = (OffscreenCanvas) chart.getCanvas();

            // System.out.println("will init");
            canvas.initBuffer(canvas.getCapabilities(), (int) width, (int) height);
            // LOGGER.error("done initBuffer");
            chart.render();
            // LOGGER.error("done render");
        } else {

        }


        }catch (Exception exp){
            System.out.println("Exception reset To Method in Chart: "+exp);
        }

       // chart.render();
    }

    //Erstellt ein Chart
    private AWTChart createChart(JavaFXChartFactory factory, String toolkit) {
        // -------------------------------
        // Define a function to plot

        // Define range and precision for the function to plot

        // Create the object to represent the function over the given range.
        //final Shape surface = Builder.buildOrthonormal(mapper, range, steps);

        OrthonormalGrid orthonormalGrid = new OrthonormalGrid(xRange,120, yRange,120);


        final Shape surface = Builder.buildOrthonormal(orthonormalGrid,mapper);

        //Expand bounds by 10%
        float zPositive = surface.getBounds().getZmax();
        float zNegative = surface.getBounds().getZmin();
        float zRange = zPositive-zNegative;
        surface.getBounds().setZmax(zPositive+zRange*0.15f);
        surface.getBounds().setZmin(zNegative-zRange*0.15f);

        float yPositive = surface.getBounds().getYmax();
        float yNegative = surface.getBounds().getYmin();
        float yRange = yPositive-yNegative;
        surface.getBounds().setYmax(yPositive+yRange*0.1f);
        surface.getBounds().setYmin(yNegative-yRange*0.1f);

        float xPositive = surface.getBounds().getXmax();
        float xNegative = surface.getBounds().getXmin();
        float xRange = xPositive-xNegative;
        surface.getBounds().setZmax(xPositive+xRange*0.1f);
        surface.getBounds().setZmin(xNegative-xRange*0.1f);


        //HeatColor
        surface.setColorMapper(new ColorMapper(new ColorMapRainbow(), zNegative , (zRange)*coloringPerc, new Color(1, 1, 1, 0.7f)));
        surface.setFaceDisplayed(true);
        surface.setBoundingBoxDisplayed(false);
        surface.setWireframeDisplayed(false);
        surface.setLegendDisplayed(false);
        surface.setBoundingBoxDisplayed(false);


        currSurface = surface;
        // -------------------------------
        // Create a chart
        Quality quality = Quality.Advanced;
        //quality.setSmoothPolygon(true);
        //quality.setAnimated(true);

        // let factory bind mouse and keyboard controllers to JavaFX node
        AWTChart chart = (AWTChart) factory.newChart(quality, toolkit);
        chart.getScene().getGraph().add(surface);
        return chart;
    }

    private volatile int toggleAxes = 0;

    public void showSurface(boolean showSurfaceBool){
        try {
            currSurface.setFaceDisplayed(showSurfaceBool);
            chart.updateProjectionsAndRender();
            if (toggleAxes == 1 || toggleAxes == 2) {
                chart.setAxeDisplayed(false);
                toggleAxes%=4;
            }else {
                chart.setAxeDisplayed(true);
                toggleAxes%=4;
            }
            toggleAxes++;
        }catch (Exception exp){

        }
     }

    /***
     *  Update drawed Points on surface
     * @param points3d
     */
    public synchronized void updatePointsInChart(List<Point3d> points3d){

        try {

        //Case 1: not enough points
        if(jzy3dPoints.size()<points3d.size()) {

            ArrayList<Point> jzy3dPointsToAdd = new ArrayList<>();
            for (int i = 0; i < points3d.size();i++){
                //Solange noch genügend jzy3d Punkte gibt diese updaten
                if (jzy3dPoints.size()-1-i > 0){
                    jzy3dPoints.get(i).setData(new Coord3d(points3d.get(i).x,points3d.get(i).y,points3d.get(i).z));

                }else {
                    Point newJzy3dPoint = new Point(new Coord3d(points3d.get(i).x,points3d.get(i).y,points3d.get(i).z),Color.BLACK,2f);
                    jzy3dPointsToAdd.add(newJzy3dPoint);
                }

            }
            jzy3dPoints.addAll(jzy3dPointsToAdd);
            chart.getScene().getGraph().add(jzy3dPointsToAdd);
        }
        //Case 2: to many points
        else{
            //Remove to many from Chart
            for (int i = points3d.size()-1; i < jzy3dPoints.size();i++){
                //Remove from Plot
                chart.getScene().getGraph().remove(jzy3dPoints.get(i));
            }
            //Remove those which are 2 many
            for (int i = jzy3dPoints.size()-1; i >= points3d.size() ;i--){
                //Remove from List
                jzy3dPoints.remove(i);
            }
            for (int i = 0; i <jzy3dPoints.size();i++){
                //Update
                jzy3dPoints.get(i).setData(new Coord3d(points3d.get(i).x,points3d.get(i).y,points3d.get(i).z));
            }
        }

        chart.render();

        }catch (Exception exp){

            System.out.println("Exception: in update Chart method"+exp.toString());
            exp.printStackTrace();
        }

    }

    //Löscht alle Punkte aus einem Chart
    public void clearPoints(){

        for (int i = 0; i< jzy3dPoints.size(); i++){
            //Remove from Plot
            chart.getScene().getGraph().remove(jzy3dPoints.get(i));
        }

        jzy3dPoints = new ArrayList<>();

        chart.updateProjectionsAndRender();

    }



}
