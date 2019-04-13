package algorithmns.equations;

import algorithmns.croa.models.Point;
import algorithmns.equations.boundrys.Boundary;
import configuration.configuration.ConfigurationAlgorithm;


/*
    This Class represents the simpled Rosenbrock function
    f(x,y) = (0-x)^2 + 100(y-x^2)^2
 */
public class Rosenbrock implements IEquation{

    Boundary boundary;
    ConfigurationAlgorithm configurationAlgorithm;

    public Rosenbrock(){
        this.boundary = new Boundary(-2.5,2.5,-2.5,2.5);
        configurationAlgorithm = new ConfigurationAlgorithm(
                1.4962, //c1
                1.4962, //c2
                0.72984,//w
                5,    //maxVelocity
                2,     //initialMaxLengthVelocityPerDim
                0.3,   //minVelocityStepInPerCent
                10,    //trysOfPSOUpdate
                0.05,  //distaneToBoundrys
                0.1,   //keMinLossRate
                0.6,   //moleColl %
                50.0,  //initialKE
                5.0,   //minimumKE
                0.0,   //initialBuffer
                60,    //numberOfHitsForDecomposition
                0.0001,//MoveAlonGradeMaxStep);
                0.1,50); //Impact of Molecule to other Molecule in intermolecular coll
    }

    public Rosenbrock(ConfigurationAlgorithm configurationAlgorithm){
        boundary = new Boundary(-5.12,5.12,-5.12,5.12);
        this.configurationAlgorithm = configurationAlgorithm;
    }

   public Rosenbrock(String algorithmn){

       if (algorithmn.equals("CROA")){

           configurationAlgorithm = new ConfigurationAlgorithm(
                   1.4962, //c1
                   1.4963, //c2
                   0.72984,//w
                   10,    //maxVelocity
                   2,     //initialMaxLengthVelocityPerDim
                   0.3,   //minVelocityStepInPerCent
                   10,    //trysOfPSOUpdate
                   0.05,  //distaneToBoundrys
                   0.451,   //keMinLossRate
                   0.711,   //moleColl %
                   361.0,  //initialKE
                   72.0,   //minimumKE
                   4110,   //initialBuffer
                   1,    //numberOfHitsForDecomposition
                   0.185, //MoveAlonGradeMaxStep);
                   0.23,10); //

       }else if (algorithmn.equals("SCROA")){

           configurationAlgorithm = new ConfigurationAlgorithm(
                   1.97, //c1
                   1.51, //c2
                   0.205,//w
                   10,    //maxVelocity
                   2,     //initialMaxLengthVelocityPerDim
                   0,   //minVelocityStepInPerCent
                   10,    //trysOfPSOUpdate
                   0.05,  //distaneToBoundrys
                   0.997,   //keMinLossRate
                   0.096,   //moleColl %
                   440,  //initialKE
                   70.0,   //minimumKE
                   7700.0,   //initialBuffer
                   5,    //numberOfHitsForDecomposition
                   0.072, //MoveAlonGradeMaxStep);
                   0.00005,27); //
       }
   }


    @Override
    public ConfigurationAlgorithm getConfiguration() {
        return configurationAlgorithm;
    }

    //return result for f(x,y)
    public double calculateValue(Point point) {
        return (Math.pow((0.0-point.x),2.0)+ 105.0*(Math.pow(point.y-Math.pow(point.x,2),2)));
    }

    @Override
    public double calculateValue(double x, double y) {
        return (Math.pow((0.0-x),2.0)+ 105.0*(Math.pow(y-Math.pow(x,2),2)));
    }

    //Calculate direction to move
    public Point calculateGrade(Point point) {
        double GradInX = 2.0*point.x*(200.0 * Math.pow(point.x,2.0) - 200.0 * point.y + 1.0);
        double GradInY = 200.0*(point.y - Math.pow(point.x,2.0));
        return new Point(GradInX,GradInY);
    }

    @Override
    public Boundary getBoundary() {
        return boundary;
    }

    @Override
    public String getName() {
        return "Rosenbrock";
    }

    @Override
    public float getPercColoring() {
        return 0.25f;
    }


}