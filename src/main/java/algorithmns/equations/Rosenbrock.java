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

    @Deprecated
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
       this.boundary = new Boundary(-2.5,2.5,-2.5,2.5);

       if (algorithmn.equals("CROA")){

           configurationAlgorithm = new ConfigurationAlgorithm(
                   0, //c1
                   0, //c2
                   0,//w
                   10000,    //maxVelocity
                   3,     //initialMaxLengthVelocityPerDim
                   0.0,   //minVelocityStepInPerCent
                   1,    //trysOfPSOUpdate
                   0.05,  //distaneToBoundrys
                   0.82,   //keMinLossRate
                   0.133,   //moleColl %
                   50.0,  //initialKE
                   7.3,   //minimumKE
                   1750,   //initialBuffer
                   1,    //numberOfHitsForDecomposition
                   0.216, //MoveAlonGradeMaxStep);
                   0.167,18); //

       }else if (algorithmn.equals("SCROA")){

           configurationAlgorithm = new ConfigurationAlgorithm(
                   0, //c1
                   2.0, //c2
                   0.5714,//w
                   10000,    //maxVelocity
                   3,     //initialMaxLengthVelocityPerDim
                   0.0,   //minVelocityStepInPerCent
                   1,    //trysOfPSOUpdate
                   0.05,  //distaneToBoundrys
                   0.286,   //keMinLossRate
                   0.6,   //moleColl %
                   70,  //initialKE
                   14,   //minimumKE
                   3250.0,   //initialBuffer
                   20,    //numberOfHitsForDecomposition
                   0.067, //MoveAlonGradeMaxStep);
                   0.089,5); //
       }else if (algorithmn.equals("PSO")){
        //Rastirgin 1.212 0.644 0.712 71
        //Ackley 0.0 0.8 0.688 90
        //Rosenbrock 0.2 1.6 0.5 33
           boundary = new Boundary(-5.00,5.00,-5.00,5.00);
           configurationAlgorithm = new ConfigurationAlgorithm(
                   0.226, //c1
                   1.616, //c2
                   0.512,//w
                   (boundary.getMaxY()-(boundary.getMinY())*0.1),    //maxVelocity
                   2,     //initialMaxLengthVelocityPerDim
                   0.3,   //minVelocityStepInPerCent
                   10,    //trysOfPSOUpdate
                   0.05,  //distaneToBoundrys
                   0.1,   //keMinLossRate
                   0.7,   //moleColl %
                   20.0,  //initialKE
                   2.0,   //minimumKE
                   0.0,   //initialBuffer
                   50,    //numberOfHitsForDecomposition
                   0.0000001, //MoveAlonGradeMaxStep);
                   0.00005,33); //
       }
   }


    @Override
    public ConfigurationAlgorithm getConfiguration() {
        return configurationAlgorithm;
    }

    //return result for f(x,y)
    public double calculateValue(Point point) {
        return (Math.pow((1.0-point.x),2.0)+ 105.0*(Math.pow(point.y-Math.pow(point.x,2),2)));
    }

    @Override
    public double calculateValue(double x, double y) {
        return (Math.pow((1.0-x),2.0)+ 105.0*(Math.pow(y-Math.pow(x,2),2)));
    }

    //Calculate direction to move
    public Point calculateGrade(Point point) {
        double GradInX =-( 2.0*point.x*(200.0 * Math.pow(point.x,2.0) - 200.0 * point.y + 1.0));
        double GradInY = -(200.0*(point.y - Math.pow(point.x,2.0)));
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