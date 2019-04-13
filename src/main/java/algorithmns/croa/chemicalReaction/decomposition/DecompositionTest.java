package algorithmns.croa.chemicalReaction.decomposition;

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
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DecompositionTest {

    Buffer buffer = new Buffer(0);
    Decomposition decomposition;
    IRandomGenerator randomGenerator = new MersenneTwisterFast(System.nanoTime());
    IEquation currentEquation = new Rosenbrock();
    ICalculatorPE calculatorPE = new CalculateFunction();
    ArrayList<IMolecule> molecules = new ArrayList<>();
    IBestSolutionListener bestSolutionListener = new BestSolution();



    @Before
    public void setUp() {

        buffer.setBuffer(50);
        decomposition = new Decomposition(randomGenerator, buffer, bestSolutionListener);

    }

    @Test
    public void decomposition_Test(){


        for (int i = 0; i<2; i++){
            //Generate koords
            double randomX =(randomGenerator.nextDouble() * (currentEquation.getBoundary().getMaxX()- currentEquation.getBoundary().getMinX())+ currentEquation.getBoundary().getMinX());
            double randomY =(randomGenerator.nextDouble() * (currentEquation.getBoundary().getMaxY()- currentEquation.getBoundary().getMinY())+ currentEquation.getBoundary().getMinY());
            IMolecule molecule = new MoleculeCROA(new Point(randomX,randomY), currentEquation.calculateValue(new Point(randomX,randomY)),calculatorPE, currentEquation,bestSolutionListener);
            molecule.setKE(currentEquation.getConfiguration().minimumKe-1);
            molecules.add(molecule);
        }

        double pe1 = molecules.get(0).getPE();
        double pe2 = molecules.get(1).getPE();
        double bufferBefore = buffer.getBuffer();

        double ke1 = molecules.get(0).getKE();
        double ke2 = molecules.get(1).getKE();

        List<IMolecule> decomposition = this.decomposition.decomposition(molecules.get(0));
        decomposition.addAll(this.decomposition.decomposition(molecules.get(1)));

        //Same Energylevel
        assertEquals(ke1 + ke2 + pe1 + pe2 + bufferBefore , molecules.get(0).getKE() + molecules.get(1).getKE() + molecules.get(0).getPE() + molecules.get(1).getPE());

        boolean inBoundrysMolecule1 = !(molecules.get(0).getCurrentStructure().x >= currentEquation.getBoundary().getMaxX()
                || molecules.get(0).getCurrentStructure().y >= currentEquation.getBoundary().getMaxY()
                || molecules.get(0).getCurrentStructure().x <= currentEquation.getBoundary().getMinX()
                || molecules.get(0).getCurrentStructure().y <= -currentEquation.getBoundary().getMaxY());


        boolean inBoundrysMolecule2 = !(molecules.get(1).getCurrentStructure().x >= currentEquation.getBoundary().getMaxX()
                || molecules.get(1).getCurrentStructure().y >= currentEquation.getBoundary().getMaxY()
                || molecules.get(1).getCurrentStructure().x <= currentEquation.getBoundary().getMinX()
                || molecules.get(1).getCurrentStructure().y <= -currentEquation.getBoundary().getMaxY());

        Assert.assertTrue((inBoundrysMolecule1 && inBoundrysMolecule2));
    }
}