package algorithmns.croa.chemicalReaction.interMolecularIneffectiveCollission;

import algorithmns.LocalSearcher.neighbourhoodSearch.neighbourhoodSearchTwo.INeighbourhoodSearchTwo;
import algorithmns.LocalSearcher.neighbourhoodSearch.neighbourhoodSearchTwo.RandombasedSearch;
import algorithmns.LocalSearcher.randomGenerator.IRandomGenerator;
import algorithmns.LocalSearcher.randomGenerator.MersenneTwisterFast;
import algorithmns.bestSolutionObserver.BestSolution;
import algorithmns.bestSolutionObserver.IBestSolutionListener;
import algorithmns.croa.calculatePE.CalculateFunction;
import algorithmns.croa.calculatePE.ICalculatorPE;
import algorithmns.croa.models.Buffer;
import algorithmns.croa.models.IMolecule;
import algorithmns.croa.models.MoleculeCROA;
import algorithmns.croa.models.Point;
import algorithmns.equations.IEquation;
import algorithmns.equations.Rosenbrock;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InterMolecularIneffectiveCollisionTest {

    Buffer buffer = new Buffer(0);
    InterMolecularIneffectiveCollision interMolecularIneffectiveCollission;
    IRandomGenerator randomGenerator = new MersenneTwisterFast(System.nanoTime());
    IEquation currentEquation = new Rosenbrock();
    ICalculatorPE calculatorPE = new CalculateFunction();
    IBestSolutionListener bestSolutionListener = new BestSolution();
    ArrayList<IMolecule> molecules = new ArrayList<>();



    @BeforeEach
    public void SetUp() {

        buffer.setBuffer(50);
        INeighbourhoodSearchTwo neighbourhoodSearchTwo = new RandombasedSearch(randomGenerator,currentEquation);

        interMolecularIneffectiveCollission = new InterMolecularIneffectiveCollision(randomGenerator,neighbourhoodSearchTwo,buffer);

    }

    @Test
    public void synthesis_Test() {


        for (int i = 0; i < 2; i++) {
            //Generate koords
            double randomX = (randomGenerator.nextDouble() * (currentEquation.getBoundary().getMaxX() - currentEquation.getBoundary().getMinX()) + currentEquation.getBoundary().getMinX());
            double randomY = (randomGenerator.nextDouble() * (currentEquation.getBoundary().getMaxY() - currentEquation.getBoundary().getMinY()) + currentEquation.getBoundary().getMinY());
            IMolecule molecule = new MoleculeCROA(new Point(randomX, randomY), currentEquation.calculateValue(new Point(randomX, randomY)), calculatorPE, currentEquation,bestSolutionListener);
            molecule.setKE(currentEquation.getConfiguration().minimumKe - 1);
            molecules.add(molecule);
        }

        double pe1 = molecules.get(0).getPE();
        double pe2 = molecules.get(1).getPE();
        double ke1 = molecules.get(0).getKE();
        double ke2 = molecules.get(1).getKE();
        double bufferBefore = buffer.getBuffer();

        this.interMolecularIneffectiveCollission.interMolecularIneffectiveCollission(molecules.get(0), molecules.get(1));

        //Same Energylevel
        assertEquals(ke1 + ke2 + pe1 + pe2 + bufferBefore , molecules.get(0).getKE() + molecules.get(1).getKE() + molecules.get(0).getPE() + molecules.get(1).getPE());


        boolean inBoundarysMolecule1 = !(molecules.get(0).getCurrentStructure().x >= currentEquation.getBoundary().getMaxX()
                || molecules.get(0).getCurrentStructure().y >= currentEquation.getBoundary().getMaxY()
                || molecules.get(0).getCurrentStructure().x <= currentEquation.getBoundary().getMinX()
                || molecules.get(0).getCurrentStructure().y <= -currentEquation.getBoundary().getMaxY());


        boolean inBoundarysMolecule2 = !(molecules.get(1).getCurrentStructure().x >= currentEquation.getBoundary().getMaxX()
                || molecules.get(1).getCurrentStructure().y >= currentEquation.getBoundary().getMaxY()
                || molecules.get(1).getCurrentStructure().x <= currentEquation.getBoundary().getMinX()
                || molecules.get(1).getCurrentStructure().y <= -currentEquation.getBoundary().getMaxY());

        Assert.assertTrue((inBoundarysMolecule1 && inBoundarysMolecule2));
    }

}