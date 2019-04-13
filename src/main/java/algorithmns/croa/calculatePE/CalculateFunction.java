package algorithmns.croa.calculatePE;

import algorithmns.croa.models.Point;
import algorithmns.equations.IEquation;

public class CalculateFunction implements ICalculatorPE {

    public double calculatePE(double x, double y, IEquation equation) {
        return equation.calculateValue(new Point(x,y));
    }
}
