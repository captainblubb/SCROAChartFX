package algorithmns.equations;

import algorithmns.croa.models.Point;
import algorithmns.equations.boundrys.Boundary;
import configuration.configuration.ConfigurationAlgorithm;

import static java.lang.Math.PI;
import static java.lang.Math.cos;

public class Ackley implements IEquation {

    Boundary boundary;
    ConfigurationAlgorithm configurationAlgorithm;

    public Ackley(){
        boundary = new Boundary(-5.00,5.00,-5.00,5.00);
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


    public Ackley(ConfigurationAlgorithm configurationAlgorithm){
        boundary = new Boundary(-5.12,5.12,-5.12,5.12);
        this.configurationAlgorithm = configurationAlgorithm;
    }

    public Ackley(String algorithmn){

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
        return calculateValue(point.x,point.y);
    }

    @Override
    public double calculateValue(double x, double y) {
        double value = 0.0;
        value = -20*Math.pow(Math.E,-0.2*Math.sqrt(0.5*(Math.pow(x,2)+Math.pow(y,2))))
                -Math.pow(Math.E,0.5*(cos(2* PI*x)+ cos(2* PI*y)))
                +Math.E+20;


        //-20 * e^(-0.2 * sqrt(0.5*(x^2 +y^2))) - e^(0.5(cos(2*PI*x) + cos(2*PI*y)))+e+20


        return value;
    }

    @Override
    public Point calculateGrade(Point point) {

        double gradeInX = (2.82843*point.x* Math.pow(Math.E,-Math.sqrt(2)*Math.sqrt( Math.pow(point.x,2)+ Math.pow(point.y,2))))/(Math.sqrt( Math.pow(point.x,2)+ Math.pow(point.y,2)))+ PI*Math.sin(2*PI*point.x)*Math.pow(Math.E,0.5*(cos(2*PI*point.x)+cos(2*PI*point.y)));
        double gradeInY = (2.82843*point.y* Math.pow(Math.E,-Math.sqrt(2)*Math.sqrt( Math.pow(point.x,2)+ Math.pow(point.y,2))))/(Math.sqrt( Math.pow(point.x,2)+ Math.pow(point.y,2)))+ PI*Math.sin(2*PI*point.y)*Math.pow(Math.E,0.5*(cos(2*PI*point.x)+cos(2*PI*point.y)));

        return new Point(gradeInX,gradeInY);
    }


    @Override
    public Boundary getBoundary() {
        return boundary;
    }

    @Override
    public String getName() {
        return "Ackley";
    }

    @Override
    public float getPercColoring() {
        return 0.8f;
    }
}
