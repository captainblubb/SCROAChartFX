package algorithmns.croa.chemicalReaction.decomposition;

import algorithmns.LocalSearcher.randomGenerator.IRandomGenerator;
import algorithmns.bestSolutionObserver.IBestSolutionListener;
import algorithmns.croa.models.Buffer;
import algorithmns.croa.models.IMolecule;
import algorithmns.croa.models.MoleculeCROA;
import algorithmns.croa.models.Point;
import algorithmns.equations.boundrys.Boundary;

import java.util.ArrayList;
import java.util.List;

public class Decomposition implements IDecomposition {

    IRandomGenerator randomGenerator;
    Buffer buffer;
    IBestSolutionListener bestSolutionListener;
    public Decomposition(IRandomGenerator randomGenerator, Buffer buffer, IBestSolutionListener bestSolutionListener){
        this.randomGenerator = randomGenerator;
        this.bestSolutionListener = bestSolutionListener;
        this.buffer = buffer;
    }

    @Override
    public List<IMolecule> decomposition(IMolecule molecule) {


        molecule.increaseNumberOfHits();
        ArrayList<IMolecule> molecules = new ArrayList<>();

        Boundary boundary = molecule.getEquation().getBoundary();
        double generatedY = (boundary.getMaxY()- boundary.getMinY())*randomGenerator.nextDouble()+ boundary.getMinY();
        Point point1 = new Point(molecule.getCurrentStructure().x, generatedY);
        double PEp1 = molecule.getCalculatorPE().calculatePE(point1.x,point1.y,molecule.getEquation());


        double generatedX = (boundary.getMaxX()- boundary.getMinX())*randomGenerator.nextDouble()+ boundary.getMinX();
        Point point2 = new Point(generatedX,molecule.getCurrentStructure().y );
        double PEp2 = molecule.getCalculatorPE().calculatePE(point2.x,point2.y,molecule.getEquation());

        //Edec -> Überschüssige energie die nicht benötigt wird um die 2 neuen Punkte zu generieren
        double Edec;

        //Check if new Molecules have better PE together than old
        if((molecule.getPE()+molecule.getKE()) >= (PEp1+PEp2)){

            Edec = molecule.getPE()+molecule.getKE() - PEp1 - PEp2;
            double spreadEdec = randomGenerator.nextDouble();

            MoleculeCROA molecule1 = new MoleculeCROA(point1,Edec*spreadEdec,molecule.getCalculatorPE(),molecule.getEquation(),bestSolutionListener);
            MoleculeCROA molecule2 = new MoleculeCROA(point2,Edec*(1-spreadEdec),molecule.getCalculatorPE(),molecule.getEquation(),bestSolutionListener);

            if (molecule1.getMinPE() < molecule.getMinPE()){
                molecule1.setMinStructure(molecule1.getMinStructure(),molecule1.getMinNumberOfHits());
            }
            if(molecule2.getMinPE() < molecule.getMinPE()){
                molecule2.setMinStructure(molecule1.getMinStructure(),molecule1.getMinNumberOfHits());
            }

            molecules.add(molecule1);
            molecules.add(molecule2);

        }else {

            double randomBuff1 = randomGenerator.nextDouble();
            double randomBuff2 = randomGenerator.nextDouble();

            //Check if Energy of buffer could generate new points
            Edec = molecule.getPE()+ molecule.getKE() + randomBuff1*randomBuff2*buffer.getBuffer() -(PEp1+PEp2);

            if (Edec >= 0){

                buffer.setBuffer(buffer.getBuffer()*(1-randomBuff1*randomBuff2));

                double spreadEdec = randomGenerator.nextDouble();

                MoleculeCROA molecule1 = new MoleculeCROA(point1,Edec*spreadEdec,molecule.getCalculatorPE(),molecule.getEquation(),bestSolutionListener);
                MoleculeCROA molecule2 = new MoleculeCROA(point2,Edec*(1-spreadEdec),molecule.getCalculatorPE(),molecule.getEquation(),bestSolutionListener);

                molecules.add(molecule1);
                molecules.add(molecule2);
            }
        }
        return molecules;
    }
}
