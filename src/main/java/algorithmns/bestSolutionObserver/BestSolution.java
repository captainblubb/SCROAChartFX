package algorithmns.bestSolutionObserver;

import algorithmns.croa.models.Point;

public class BestSolution implements IBestSolutionListener {

    Point bestSolution;
    volatile double bestPE;

    public BestSolution(){
        bestPE = Double.MAX_VALUE;
        bestSolution = new Point(0,0);
    }

    @Override
    public synchronized void getNotified(Point point, double PE) {
        if(PE <= bestPE){
            this.bestPE = PE;
            bestSolution=point;
        }
    }

    @Override
    public Point getBestSolutionPoint() {
        return bestSolution;
    }

    @Override
    public double getBestPE() {
        return bestPE;
    }

}
