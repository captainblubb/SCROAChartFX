package algorithmns.equations;

import algorithmns.croa.models.Point;
import algorithmns.equations.boundrys.Boundary;
import configuration.configuration.ConfigurationAlgorithm;

public class MishrasBird implements IEquation {

    Boundary boundary;
    ConfigurationAlgorithm configurationAlgorithm;

    public MishrasBird(){
        boundary = new Boundary(-10.00,0,-6.5,0);
        configurationAlgorithm = new ConfigurationAlgorithm(
                1.4962, //c1
                1.4962, //c2
                0.72984,//w
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
                0.00005,50); //
    }

    public MishrasBird(ConfigurationAlgorithm configurationAlgorithm){
        boundary = new Boundary(-5.12,5.12,-5.12,5.12);
        this.configurationAlgorithm = configurationAlgorithm;
    }


    public MishrasBird(String algorithmn){

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
                    0.1,   //keMinLossRate
                    0.7,   //moleColl %
                    20.0,  //initialKE
                    2.0,   //minimumKE
                    0.0,   //initialBuffer
                    50,    //numberOfHitsForDecomposition
                    0.0000001, //MoveAlonGradeMaxStep);
                    0.00005,50); //

        }else if (algorithmn.equals("SCROA")){

            configurationAlgorithm = new ConfigurationAlgorithm(
                    1.4962, //c1
                    1.4963, //c2
                    0.72984,//w
                    10,    //maxVelocity
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
                    0.00005,50); //
        }
    }


    @Override
    public ConfigurationAlgorithm getConfiguration() {
        return configurationAlgorithm;
    }

    @Override
    public double calculateValue(Point point) {
        return Math.sin(point.y)* Math.pow(Math.E, Math.pow((1-Math.cos(point.x)),2)) + Math.cos(point.x)* Math.pow(Math.E, Math.pow((1-Math.sin(point.y)),2)) + Math.pow((point.x-point.y),2);
    }

    @Override
    public double calculateValue(double x, double y) {
        return Math.sin(y)* Math.pow(Math.E, Math.pow((1-Math.cos(x)),2)) + Math.cos(x)* Math.pow(Math.E, Math.pow((1-Math.sin(y)),2)) + Math.pow((x-y),2);
    }

    @Override
    public Point calculateGrade(Point point) {
        return new Point(1,1);
    }


    @Override
    public Boundary getBoundary() {
        return boundary;
    }

    @Override
    public String getName() {
        return "Rastirgin";
    }

    @Override
    public float getPercColoring() {
        return 0.2f;
    }
}
