package algorithmns.croa.models;

import algorithmns.bestSolutionObserver.IBestSolutionListener;
import algorithmns.croa.calculatePE.ICalculatorPE;
import algorithmns.equations.IEquation;

import java.util.ArrayList;

public class MoleculeCROA implements IMolecule {

    public Point getCurrentStructure() {
        return currentStructure;
    }

    public double getKE() {
        return KE;
    }

    public int getNumberOfHits() {
        return numberOfHits;
    }

    public int getMinNumberOfHits(){
        return minHits;
    }

    ArrayList<IBestSolutionListener> solutionListeners = new ArrayList<>();


    public void increaseNumberOfHits() {
        numberOfHits++;
    }


    private Point currentStructure;

    public double getPE() {
        return PE;
    }

    private double PE = Double.MAX_VALUE;

    public void setKE(double KE) {
        this.KE = KE;
    }

    private double KE;

    private int numberOfHits=0;

    public IEquation getEquation() {
        return equation;
    }

    private IEquation equation;


    //Best Solution currently
    //############################
    private Point minStructure;

    public ICalculatorPE getCalculatorPE() {
        return calculatorPE;
    }

    private ICalculatorPE calculatorPE;

    public Point getMinStructure() {
        return minStructure;
    }

    public void setMinStructure(Point point, int numberOfHits) {
        minPE = equation.calculateValue(point);
        minStructure = point;
        minHits= numberOfHits;
    }

    public double getMinPE() {
        return minPE;
    }

    private double minPE = Double.MAX_VALUE;

    private int minHits;


    //############################

    // 
    public MoleculeCROA(Point m_StartPoint, double m_KE, ICalculatorPE m_calculatorPE, IEquation equation, IBestSolutionListener bestSolutionListener){

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


    ///
    ///
    ///
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
}
