package algorithmns.LocalSearcher.neighbourhoodSearch.neighbourhoodSearchTwo;

import algorithmns.LocalSearcher.randomGenerator.IRandomGenerator;
import algorithmns.croa.models.Point;
import algorithmns.equations.IEquation;
import algorithmns.equations.boundrys.Boundary;

import java.util.ArrayList;
import java.util.List;

public class RandombasedSearch implements INeighbourhoodSearchTwo {


    IRandomGenerator randomGenerator;
    IEquation equation;

    public RandombasedSearch(IRandomGenerator randomGenerator, IEquation equation){
        this.randomGenerator = randomGenerator;
        this.equation = equation;
    }

    public List<Point> randomGenerateRandomCombination(Point point1, Point point2) {

        ArrayList<Point> newPoints = new ArrayList<>();

        Point newPoint1=null;

        int counter1 = 0;
        while (newPoint1==null && counter1 < 20){
            counter1++;
            newPoint1 = generatePointWithOtherPoint(point1,point2);
        }

        Point newPoint2= null;
        int counter2 = 0;
        while (newPoint2==null || counter2 < 20) {
            counter2++;
            newPoint2 = generatePointWithOtherPoint(point2, point1);
        }


        Boundary boundary = equation.getBoundary();

        if (newPoint1 != null && boundary.inBoundary(newPoint1.x,newPoint1.y)){
            newPoints.add(newPoint1);
        }else {
            newPoints.add(point1);
        }


        if (newPoint2 != null && boundary.inBoundary(newPoint2.x,newPoint2.y)){
            newPoints.add(newPoint2);
        }else {
            newPoints.add(point2);
        }

        return newPoints;

    }


    //Generate new point with a slitly impact of a other point
    public Point generatePointWithOtherPoint(Point point1, Point impactPoint){

        double impactX = (randomGenerator.nextDouble()* equation.getConfiguration().impactOfOtherMolecule*2 - equation.getConfiguration().impactOfOtherMolecule)*impactPoint.x;
        double impactY = (randomGenerator.nextDouble()* equation.getConfiguration().impactOfOtherMolecule*2 - equation.getConfiguration().impactOfOtherMolecule)*impactPoint.y;
        return new Point(point1.x+impactX,point1.y+impactY);

    }
}
