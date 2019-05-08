package algorithmns.croa.chemicalReaction.synthesis;

import algorithmns.LocalSearcher.randomGenerator.IRandomGenerator;
import algorithmns.bestSolutionObserver.IBestSolutionListener;
import algorithmns.croa.models.Buffer;
import algorithmns.croa.models.IMolecule;
import algorithmns.croa.models.MoleculeCROA;
import algorithmns.croa.models.Point;

public class Synthesis implements ISynthesis {

    IRandomGenerator randomGenerator;
    Buffer buffer;
    IBestSolutionListener bestSolutionListener;

    public Synthesis(IRandomGenerator randomGenerator, Buffer buffer, IBestSolutionListener bestSolutionListener){
        this.randomGenerator = randomGenerator;
        this.buffer = buffer;
        this.bestSolutionListener = bestSolutionListener;
    }

    @Override
    public IMolecule synthesis(IMolecule molecule1, IMolecule molecule2) {

        molecule1.increaseNumberOfHits();
        molecule2.increaseNumberOfHits();
        double spreadX = randomGenerator.nextDouble();
        double newX = molecule1.getCurrentStructure().x *spreadX + molecule2.getCurrentStructure().x*(1-spreadX);

        double spreadY = randomGenerator.nextDouble();
        double newY = molecule1.getCurrentStructure().y *spreadY + molecule2.getCurrentStructure().y*(1-spreadY);

        Point newPoint = new Point(newX,newY);

        if(molecule1.getEquation().getBoundary().inBoundary(newPoint.x,newPoint.y)){

           double newPE = molecule1.getCalculatorPE().calculatePE(newPoint.x,newPoint.y,molecule1.getEquation());

            // (PE1 +PE2 +KE1 +KE2 ) >= newPE
           if( (molecule1.getPE()+molecule2.getPE()+molecule1.getKE()+molecule2.getKE()) >= newPE ){

                double newKE = molecule1.getPE()+molecule2.getPE()+molecule1.getKE()+molecule2.getKE() - newPE;

                MoleculeCROA moleculeCROA =new MoleculeCROA(newPoint,newKE,molecule1.getCalculatorPE(),molecule1.getEquation(),bestSolutionListener);

                if (molecule1.getMinPE() <= molecule2.getMinPE()){

                    if (molecule1.getMinPE() < moleculeCROA.getMinPE()) {
                        moleculeCROA.setMinStructure(molecule1.getMinStructure(), molecule1.getMinNumberOfHits());
                    }

                }else {
                    if (molecule2.getMinPE() < moleculeCROA.getMinPE()) {
                        moleculeCROA.setMinStructure(molecule2.getMinStructure(), molecule2.getMinNumberOfHits());
                    }
                }

                return moleculeCROA;

                /*
                     if (molecule1.getMinPE() < molecule.getMinPE()){
                molecule1.setMinStructure(molecule1.getMinStructure(),molecule1.getMinNumberOfHits());
            }
                 */
           }


        }

        return null;

    }

}
