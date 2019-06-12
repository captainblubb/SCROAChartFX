package gui.updateObject;

import java.util.List;

public class UpdateObject {


    //Punkte von allen Molekülen
    List<Point3d> points;

    //beste Lösung -> Textuelle anzeige
    private Point3d bestPoint;

    private String algoName;


    //Für gui -> Linkes oder Rechtes Fenster updaten
    int algorithmCounter;

    //Aktuelle Iteration
    int iteration;

    public UpdateObject(List<Point3d> points, Point3d bestPoint, int algorithmCounter, int iteration, String algoName) {
        this.algoName = algoName;
        this.points = points;
        this.bestPoint = bestPoint;
        this.algorithmCounter = algorithmCounter;
        this.iteration = iteration;
    }


    public Point3d getBestPoint() {
        return bestPoint;
    }

    public List<Point3d> getPoints() {
        return points;
    }

    public int getAlgorithmCounter() {
        return algorithmCounter;
    }

    public int getIteration() {
        return iteration;
    }

    public String getAlgoName() {
        return algoName;
    }

    public void setAlgoName(String algoName) {
        this.algoName = algoName;
    }
}
