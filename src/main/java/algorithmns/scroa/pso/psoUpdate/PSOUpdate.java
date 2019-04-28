package algorithmns.scroa.pso.psoUpdate;

import algorithmns.LocalSearcher.randomGenerator.IRandomGenerator;
import algorithmns.bestSolutionObserver.IBestSolutionListener;
import algorithmns.croa.models.Buffer;
import algorithmns.croa.models.Point;
import algorithmns.scroa.models.IMoleculeSCROA;

public class PSOUpdate implements IPSOUpdate{


    Buffer buffer;
    IBestSolutionListener bestSolutionListener;
    IRandomGenerator randomGenerator;

    public PSOUpdate(IBestSolutionListener bestSolutionListener, Buffer buffer, IRandomGenerator randomGenerator){
        this.randomGenerator = randomGenerator;
        this.buffer=buffer;
        this.bestSolutionListener=bestSolutionListener;
    }

    //1. molecule SCROAParamAnalysis geschwindigkeitsvektor wird geupdated
    //2. moleculeSCROA neue position wird berechnet

    public void psoUpdateOnMol(IMoleculeSCROA moleculeSCROA) {

        updateVelocityMol(moleculeSCROA);

        moveMoleculeByVelocity(moleculeSCROA, moleculeSCROA.getEquation().getConfiguration().trysOfPSOUpdate);

    }


    private void updateVelocityMol(IMoleculeSCROA moleculeSCROA) {


        // NewVelocity = w*CurrentVelocity + c1*r1*(molBest -molCurrent) + c2*r2*(bestGlobal-currentStructure)

        double r1 = randomGenerator.nextDouble();
        double r2 = randomGenerator.nextDouble();

        // w*CurrentVelocity
        double newVeolcityX = moleculeSCROA.getEquation().getConfiguration().w*moleculeSCROA.getVelocity().x
                //+ c1*r1*(molBest -molCurrent)
                + moleculeSCROA.getEquation().getConfiguration().c1*r1*(moleculeSCROA.getMinStructure().x - moleculeSCROA.getCurrentStructure().x)
                //+ c2*r2*(bestGlobal-currentStructure)
                + moleculeSCROA.getEquation().getConfiguration().c2*r2*(bestSolutionListener.getBestSolutionPoint().x- moleculeSCROA.getCurrentStructure().x);


        // w*CurrentVelocity
        double newVelocityY = moleculeSCROA.getEquation().getConfiguration().w*moleculeSCROA.getVelocity().y
                //+ c1*r1*(molBest -molCurrent)
                + moleculeSCROA.getEquation().getConfiguration().c1*r1*(moleculeSCROA.getMinStructure().y - moleculeSCROA.getCurrentStructure().y)
                //+ c2*r2*(bestGlobal-currentStructure)
                + moleculeSCROA.getEquation().getConfiguration().c2*r2*(bestSolutionListener.getBestSolutionPoint().y- moleculeSCROA.getCurrentStructure().y);



        //Normalize vektor if to long
        double length = Math.sqrt((newVeolcityX*newVeolcityX+newVelocityY*newVelocityY));
        if (length>moleculeSCROA.getEquation().getConfiguration().maxVelocity){

            double factor = moleculeSCROA.getEquation().getConfiguration().maxVelocity / length;
            newVeolcityX/=factor;
            newVelocityY/=factor;

        }


        moleculeSCROA.setVelocity(new Point(newVeolcityX,newVelocityY));

    }


    private void moveMoleculeByVelocity(IMoleculeSCROA moleculeSCROA, int trys) {


        if(trys > 0) {
            //zufällig stark in die RIchtung -> neue Pos generieren

            double stepPercentage = randomGenerator.nextDouble() * (1 - moleculeSCROA.getEquation().getConfiguration().minVelocityStep) + moleculeSCROA.getEquation().getConfiguration().minVelocityStep;


            // neue position -> molCurrentPos + mol.Velocity*stepPercentage
            double newPointX = moleculeSCROA.getCurrentStructure().x + moleculeSCROA.getVelocity().x * stepPercentage;
            double newPointY = moleculeSCROA.getCurrentStructure().y + moleculeSCROA.getVelocity().y * stepPercentage;

            Point newPoint = new Point(newPointX, newPointY);

            boolean inBoundrys = moleculeSCROA.getEquation().getBoundary().inBoundary(newPointX, newPointY);

            //randomBuffer =[0...1] * [0....1]
            //wenn (neuePosition.PE <= mol.CurrentPE+mol.CurrentKE)

            if (inBoundrys ) {

                double newKE =  (moleculeSCROA.getPE() + moleculeSCROA.getKE()) - moleculeSCROA.getCalculatorPE().calculatePE(newPointX, newPointY, moleculeSCROA.getEquation());
                moleculeSCROA.setSolution(newPoint);
                moleculeSCROA.setKE(newKE);
                moleculeSCROA.resetCurrentHits();

            } else {

                //else ... try again with reduced try
                moveMoleculeByVelocity(moleculeSCROA, --trys);

            }
        }


    }


}
