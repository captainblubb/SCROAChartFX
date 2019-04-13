package algorithmns.equations;

import algorithmns.croa.models.Point;
import algorithmns.equations.boundrys.Boundary;
import configuration.configuration.ConfigurationAlgorithm;

public interface IEquation {

    ConfigurationAlgorithm getConfiguration();
    double calculateValue(Point point);
    double calculateValue(double x, double y);
    Point calculateGrade(Point point);
    Boundary getBoundary();
    String getName();
    float getPercColoring();
}
