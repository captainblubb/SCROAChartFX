package algorithmns.scroa.models;

import algorithmns.bestSolutionObserver.IBestSolutionListener;
import algorithmns.bestSolutionObserver.IBestSolutionNotifier;
import algorithmns.croa.calculatePE.ICalculatorPE;
import algorithmns.croa.models.Point;
import algorithmns.equations.IEquation;

import java.util.ArrayList;

//import gui.updateObject.Point3d;
//import jdk.jshell.spi.ExecutionControl;

public class MoleculeSCROA implements IBestSolutionNotifier, IMoleculeSCROA {

    public Point directionVector;

    ArrayList<IBestSolutionListener> solutionListeners = new ArrayList<>();

    private Point currentStructure;

    private double PE = Double.MAX_VALUE;

    private double KE;

    private int numberOfHits=0;

    private IEquation equation;

    //Best Solution currently
    //############################
    private Point minStructure;

    private ICalculatorPE calculatorPE;

    private double minPE = Double.MAX_VALUE;

    private int minHits;


    //############################ Getter


    public double getPE() {
        return PE;
    }

    public ICalculatorPE getCalculatorPE() {
        return calculatorPE;
    }

    public Point getMinStructure() {
        return minStructure;
    }

    public double getMinPE() {
        return minPE;
    }

    public Point getCurrentStructure() {
        return currentStructure;
    }

    public double getKE() {
        return KE;
    }

    public int getNumberOfHits() {
        return numberOfHits;
    }


    public void increaseNumberOfHits() {
        numberOfHits++;
    }


    public void setKE(double KE) {
        this.KE = KE;
    }

    public IEquation getEquation() {
        return equation;
    }


    public void setMinStructure(Point point, int numberOfHits) {
        minStructure = point;
        minPE = equation.calculateValue(point);
        minHits = numberOfHits;
    }

    public int getMinNumberOfHits() {
        return minHits;
    }

    //
    public MoleculeSCROA(Point m_StartPoint, double m_KE, ICalculatorPE m_calculatorPE, IEquation equation, Point directionVector,IBestSolutionListener bestSolutionListener){

        this.directionVector = directionVector;
        currentStructure = m_StartPoint;
        this.KE = m_KE;
        this.equation = equation;
        this.calculatorPE = m_calculatorPE;
        register(bestSolutionListener);
        setSolution(m_StartPoint);

    }


    /*
        Calculate Z over interface
     */
    private void calculatePE(){
        PE = calculatorPE.calculatePE(currentStructure.x,currentStructure.y,equation);
    }



    public void setSolution(Point v){

        this.currentStructure =v;
        calculatePE();

        if(PE < minPE){
            //Set Best solution
            minPE = PE;
            minStructure =currentStructure;
            minHits= numberOfHits;
            //notify
            notifyBestSolutionChanged();
        }
    }

    @Override
    public void register(IBestSolutionListener bestSolutionListener) {
        solutionListeners.add(bestSolutionListener);
    }

    @Override
    public void unregister(IBestSolutionListener bestSolutionListener) {
        solutionListeners.remove(bestSolutionListener);
    }

    @Override
    public void notifyBestSolutionChanged() {
        for (IBestSolutionListener a : solutionListeners) {
            a.getNotified(minStructure,minPE);
        }
    }

    @Override
    public Point getVelocity() {
        return directionVector;
    }

    @Override
    public void setVelocity(Point velocity) {

        directionVector = velocity;
    }

    @Override
    public void resetCurrentHits() {
        numberOfHits =0;
    }
}
