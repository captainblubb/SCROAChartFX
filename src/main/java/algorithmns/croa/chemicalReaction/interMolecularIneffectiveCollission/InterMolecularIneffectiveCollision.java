package algorithmns.croa.chemicalReaction.interMolecularIneffectiveCollission;

import algorithmns.LocalSearcher.neighbourhoodSearch.neighbourhoodSearchTwo.INeighbourhoodSearchTwo;
import algorithmns.LocalSearcher.randomGenerator.IRandomGenerator;
import algorithmns.croa.models.Buffer;
import algorithmns.croa.models.IMolecule;
import algorithmns.croa.models.Point;

import java.util.ArrayList;
import java.util.List;

public class InterMolecularIneffectiveCollision implements IInterMolecularIneffectiveCollision {


    IRandomGenerator randomGenerator;
    INeighbourhoodSearchTwo neighbourhoodSearchTwo;
    Buffer buffer;

    public InterMolecularIneffectiveCollision(IRandomGenerator randomGenerator, INeighbourhoodSearchTwo neighbourhoodSearchTwo, Buffer buffer){
        this.randomGenerator = randomGenerator;
        this.neighbourhoodSearchTwo = neighbourhoodSearchTwo;
        this.buffer = buffer;
    }

    @Override
    public void interMolecularIneffectiveCollission(IMolecule molecule1, IMolecule molecule2) {

        List<Point> potentialPoints = neighbourhoodSearchTwo.randomGenerateRandomCombination(molecule1.getCurrentStructure(),molecule2.getCurrentStructure());

        if (potentialPoints.size() == 2) {

            molecule1.increaseNumberOfHits();
            molecule2.increaseNumberOfHits();

            //(PE1 + PE2 + KE1+Ke2) - (PE1' + PE2')
            double newEnergyAfterColl = (molecule1.getPE()+molecule2.getPE()+molecule1.getKE()+molecule2.getKE())
                    - (molecule1.getCalculatorPE().calculatePE(potentialPoints.get(0).x,potentialPoints.get(0).y,molecule1.getEquation()) + molecule2.getCalculatorPE().calculatePE(potentialPoints.get(1).x,potentialPoints.get(1).y,molecule2.getEquation()));
            if(newEnergyAfterColl > 0){

                double spreadOfKE = randomGenerator.nextDouble()*1.0; //the energy that is to much gets spread to both molecules -> generate % of spread here

                molecule1.setSolution(potentialPoints.get(0));
                molecule2.setSolution(potentialPoints.get(1));
                molecule1.setKE(newEnergyAfterColl*spreadOfKE);
                molecule2.setKE(newEnergyAfterColl*(1-spreadOfKE));
            }
        }
        ArrayList<IMolecule> molecules = new ArrayList<>();
        molecules.add(molecule1);
        molecules.add(molecule2);

      //return molecules;
    }

}
