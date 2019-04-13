package algorithmns.equations;

import algorithmns.croa.models.Point;
import algorithmns.equations.boundrys.Boundary;
import configuration.configuration.ConfigurationAlgorithm;

import static java.lang.Math.sin;

public class Rastrigin implements IEquation {

    Boundary boundary;
    ConfigurationAlgorithm configurationAlgorithm;

    //default
    public Rastrigin(){
        boundary = new Boundary(-5.12,5.12,-5.12,5.12);
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

    public Rastrigin(ConfigurationAlgorithm configurationAlgorithm){
        boundary = new Boundary(-5.12,5.12,-5.12,5.12);
        this.configurationAlgorithm = configurationAlgorithm;
    }

    public Rastrigin(String algorithmn){

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
                    1,    //numberOfHitsForDecomposition
                    0.185, //MoveAlonGradeMaxStep);
                    0.00005,
                    10); //

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

        double[] koordinates = {point.x,point.y};
        //could be also wrote as : -10 cos(2* π *x) - 10 cos(2 *π *y) + x^2 + y^2
        double sum = 10 * 2;
        for (int i = 0; i<2; i++){
            sum+= Math.pow(koordinates[i],2) - 10*Math.cos(2*Math.PI*koordinates[i]);
        }
        return sum;
    }

    @Override
    public double calculateValue(double x, double y) {
        double[] koordinates = {x,y};


        //could be also wrote as : -10 cos(2* π *x) - 10 cos(2 *π *y) + x^2 + y^2
        double sum = 10 * 2;
        for (int i = 0; i<2; i++){
            sum+= Math.pow(koordinates[i],2) - 10*Math.cos(2*Math.PI*koordinates[i]);
        }
        return sum;
    }

    @Override
    public Point calculateGrade(Point point) {
        double partialX = 2*(point.x +10 * Math.PI *sin(2*Math.PI*point.x));
        double partialY = 2*(point.x +10 * Math.PI *sin(2*Math.PI*point.x));
        return new Point(partialX,partialY);
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
        return 0.8f;
    }
}
