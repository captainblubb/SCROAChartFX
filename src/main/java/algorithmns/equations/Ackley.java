package algorithmns.equations;

import algorithmns.croa.models.Point;
import algorithmns.equations.boundrys.Boundary;
import configuration.configuration.ConfigurationAlgorithm;

import static java.lang.Math.PI;
import static java.lang.Math.cos;

public class Ackley implements IEquation {

    Boundary boundary;
    ConfigurationAlgorithm configurationAlgorithm;

    @Deprecated
    private Ackley(){
        boundary = new Boundary(-5.00,5.00,-5.00,5.00);
        configurationAlgorithm = new ConfigurationAlgorithm(
                1.4962, //c1
                1.4962, //c2
                0.72984,//w
                (boundary.getMaxY()-(boundary.getMinY())*0.1),    //maxVelocity
                2,     //initialMaxLengthVelocityPerDim
                0.3,   //minVelocityStepInPerCent
                1,    //trysOfPSOUpdate
                0.05,  //distaneToBoundrys
                0.1,   //keMinLossRate
                0.6,   //moleColl %
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
        boundary = new Boundary(-5.00,5.00,-5.00,5.00);
        if (algorithmn.equals("CROA")){

            configurationAlgorithm = new ConfigurationAlgorithm(
                    0, //c1
                    0, //c2
                    0,//w
                    0,    //maxVelocity
                    0,     //initialMaxLengthVelocityPerDim
                    0,   //minVelocityStepInPerCent
                    0,    //trysOfPSOUpdate
                    0.00,  //distaneToBoundrys
                    0.1,   //keMinLossRate
                    0.56,   //moleColl %
                    20.0,  //initialKE
                    5.0,   //minimumKE
                    2000.0,   //initialBuffer
                    33,    //numberOfHitsForDecomposition
                    0.0992, //MoveAlonGradeMaxStep);
                    0.097,
                    120); //

        }else if (algorithmn.equals("SCROA")){

            configurationAlgorithm = new ConfigurationAlgorithm(
                    0.0, //c1
                    0.28, //c2
                    0.28,//w
                    10000,    //maxVelocity
                    3,     //initialMaxLengthVelocityPerDim
                    0,   //minVelocityStepInPerCent
                    1,    //trysOfPSOUpdate
                    0.05,  //distaneToBoundrys
                    0.1429,   //keMinLossRate
                    0.7,   //moleColl %
                    50.0,  //initialKE
                    14.6,   //minimumKE
                    2500.0,   //initialBuffer
                    40,    //numberOfHitsForDecomposition
                    0.0667, //MoveAlonGradeMaxStep);
                    0.078,72); //
        }else if(algorithmn.equals("PSO")){

            //Rastirgin 1.212 0.644 0.712 71
            //Ackley 0.0 0.8 0.688 90
            //Rosenbrock 0.2 1.6 0.5 33
            boundary = new Boundary(-5.00,5.00,-5.00,5.00);
            configurationAlgorithm = new ConfigurationAlgorithm(
                    0.242, //c1
                    0.806, //c2
                    0.688,//w
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
                    0.00005,71); //

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
