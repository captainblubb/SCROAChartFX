package algorithmns.scroa.pso;

        import algorithmns.IAlgorithm;
import algorithmns.LocalSearcher.neighbourhoodSearch.neighbourhoodSearchSingle.INeighbourhoodSearchSingle;
import algorithmns.LocalSearcher.neighbourhoodSearch.neighbourhoodSearchSingle.MoveAlongGrade;
import algorithmns.LocalSearcher.neighbourhoodSearch.neighbourhoodSearchTwo.INeighbourhoodSearchTwo;
import algorithmns.LocalSearcher.neighbourhoodSearch.neighbourhoodSearchTwo.RandombasedSearch;
import algorithmns.LocalSearcher.randomGenerator.IRandomGenerator;
import algorithmns.LocalSearcher.randomGenerator.MersenneTwisterFast;
import algorithmns.bestSolutionObserver.BestSolution;
import algorithmns.bestSolutionObserver.IBestSolutionListener;
import algorithmns.croa.calculatePE.CalculateFunction;
import algorithmns.croa.calculatePE.ICalculatorPE;
import algorithmns.croa.chemicalReaction.interMolecularIneffectiveCollission.IInterMolecularIneffectiveCollision;
import algorithmns.croa.chemicalReaction.interMolecularIneffectiveCollission.InterMolecularIneffectiveCollision;
import algorithmns.croa.chemicalReaction.onWallIneffectiveCollission.IOnWallIneffectiveCollission;
import algorithmns.croa.chemicalReaction.onWallIneffectiveCollission.OnWallIneffectiveCollission;
import algorithmns.croa.models.Buffer;
import algorithmns.croa.models.IMolecule;
import algorithmns.croa.models.Point;
import algorithmns.equations.IEquation;
import algorithmns.equations.boundrys.Boundary;
import algorithmns.scroa.chemicalReactions.ChemicalReactionSCROA;
import algorithmns.scroa.chemicalReactions.IChemicalReactionSCROA;
import algorithmns.scroa.models.IMoleculeSCROA;
import algorithmns.scroa.models.MoleculeSCROA;
import algorithmns.scroa.pso.psoUpdate.IPSOUpdate;
import algorithmns.scroa.pso.psoUpdate.PSOUpdate;
import configuration.configuration.GlobalConfig;
import configuration.logger.LoggerFileWriter;
import gui.updateObject.IUpdateable;
import gui.updateObject.Point3d;
import gui.updateObject.UpdateObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.Collectors;


public class PSO implements IAlgorithm {


    private Thread scroaThread;

    IEquation equation;
    Buffer buffer;
    IChemicalReactionSCROA chemicalReactionsSCROA;
    ArrayList<IMoleculeSCROA> molecules;
    IRandomGenerator randomGenerator;
    ICalculatorPE calculatorPE;
    LoggerFileWriter loggerFileWriter;
    CyclicBarrier barrier;
    IUpdateable gui;
    protected volatile boolean paused = false;
    public volatile boolean stopped = false;



    //Observerpattern which stores the best current solution of all Molecues
    IBestSolutionListener currentBestSolution = null;

    //For update -> which algorithmn needs to be updated 1 or 2
    int algorithmCounter;

    public PSO(IEquation equation, IUpdateable gui, int algorithmCounter,  CyclicBarrier barrier){
        this.barrier = barrier;
        this.equation = equation;
        this.gui = gui;
        this.algorithmCounter = algorithmCounter;
        if(GlobalConfig.loggin) {
            this.loggerFileWriter = new LoggerFileWriter("PSO",algorithmCounter);
        }
        this.currentBestSolution = new BestSolution();
        // Initialzation

        //buffer
        buffer = new Buffer(equation.getConfiguration().initialBuffer);
        calculatorPE = new CalculateFunction();
        // Initialize algorithmns.croa
        //random generator -> init with nanoTime for less cluster
        randomGenerator = new MersenneTwisterFast(System.nanoTime());
        //neighbourhoodSearchSingle
        INeighbourhoodSearchSingle neighbourhoodSearchSingle = new MoveAlongGrade(randomGenerator,equation);
        //neighbourhoodSearchTwo
        INeighbourhoodSearchTwo neighbourhoodSearchTwo = new RandombasedSearch(randomGenerator,equation);

        //Chemical Reactions
        IPSOUpdate ipsoUpdate = new PSOUpdate(currentBestSolution,buffer,randomGenerator);
        //onwallIneffective
        IOnWallIneffectiveCollission onWallIneffectiveCollission = new OnWallIneffectiveCollission(randomGenerator,neighbourhoodSearchSingle,buffer);
        //intermolecularCollission
        IInterMolecularIneffectiveCollision interMolecularIneffectiveCollission = new InterMolecularIneffectiveCollision(randomGenerator,neighbourhoodSearchTwo,buffer);
        //Chemical Reaction
        chemicalReactionsSCROA = new ChemicalReactionSCROA(ipsoUpdate,onWallIneffectiveCollission,interMolecularIneffectiveCollission);

        //Population
        molecules = generateRandomPopulation(equation, equation.getConfiguration().PopSize, randomGenerator);

        for (IMolecule m: molecules) {
            m.register(currentBestSolution);
        }
    }



    public ArrayList<IMoleculeSCROA> generateRandomPopulation(IEquation equation, int initialPopoSize, IRandomGenerator randomGenerator){

        ArrayList<IMoleculeSCROA> population = new ArrayList<>();

        Boundary boundary = equation.getBoundary();
        //Generate Population in Boundries

        //Check boundries start in boundrie not on boundrie
        double xRange = (boundary.getMaxX() - boundary.getMinX());
        if(xRange-2* GlobalConfig.distanceToBoundrys > 1){
            xRange-=(2* GlobalConfig.distanceToBoundrys);
        }
        double yRange = (boundary.getMaxY() - boundary.getMinY());
        if(yRange-2* GlobalConfig.distanceToBoundrys > 1){
            yRange-=(2* GlobalConfig.distanceToBoundrys);
        }


        for (int i = 0; i<initialPopoSize;i++){


            double fixInX = boundary.getMinX()+ GlobalConfig.distanceToBoundrys;
            double fixInY = boundary.getMinY()+ GlobalConfig.distanceToBoundrys;

            double randomX = randomGenerator.nextDouble()*xRange;
            double randomY = randomGenerator.nextDouble()*yRange;


            Point point = new Point(fixInX+randomX,fixInY+randomY);

            //System.out.println(point.toParseFormat());

            double velocityX = randomGenerator.nextDouble()*2* equation.getConfiguration().initialMaxLengthVelocityPerDim - equation.getConfiguration().initialMaxLengthVelocityPerDim;

            double velocityY = randomGenerator.nextDouble()*2* equation.getConfiguration().initialMaxLengthVelocityPerDim - equation.getConfiguration().initialMaxLengthVelocityPerDim;

            MoleculeSCROA molecule = new MoleculeSCROA(point, equation.getConfiguration().initialKE,calculatorPE,equation,new Point(velocityX,velocityY),currentBestSolution);
            population.add(molecule);

        }

        return population;
    }



    /*
        Es wird CROAParamAnalysis verwendet jedoch Decomop und Synthesis durch PsoUpdateSCROA ersetzt

        In PSOUpdate wird anhand der besten Lösung global und anhand der besten Lösung des Moleculs ein neuer Punkt berechnet


     */

    @Override
    public void run() {




        int onWallCollHappend = 0;
        int interMolColHappend = 0;
        int psoHappendInsteadOfDecomp = 0;
        int psoHappendInsteadOfSynthesis =0;

        double energyStart = molecules.stream().map(m -> m.getKE() + m.getPE()).collect(Collectors.summingDouble(d -> d)) + buffer.getBuffer();

        try {

            int currentIteration = 0;

            //Update at ZERO
            List<Point3d> collect = molecules.stream().map(m -> new Point3d(m.getCurrentStructure().x, m.getCurrentStructure().y, m.getPE())).collect(Collectors.toList());
            Point bestSolution = currentBestSolution.getBestSolutionPoint();
            gui.update(new UpdateObject(collect, new Point3d(bestSolution.x, bestSolution.y, currentBestSolution.getBestPE()), algorithmCounter, currentIteration));
            //Wait for other Thread to Synchronize
            barrier.await();


            while (!stopped) {


                if (currentIteration < GlobalConfig.Iterations) {

                    currentIteration++;

                        int randomIndex = randomGenerator.nextInt(0, molecules.size() - 1);
                        IMoleculeSCROA selectedMolecule = molecules.get(randomIndex);
                        chemicalReactionsSCROA.psoUpdate(selectedMolecule);
                    if (GlobalConfig.loggin) {
                        loggerFileWriter.logBestSolution("PSO", currentIteration, currentBestSolution.getBestSolutionPoint(), currentBestSolution.getBestPE());
                    }





                    if (currentIteration % GlobalConfig.updateAfterIterations == 0 || paused) {

                        collect = molecules.stream().map(m -> new Point3d(m.getCurrentStructure().x, m.getCurrentStructure().y, m.getPE())).collect(Collectors.toList());
                        bestSolution = currentBestSolution.getBestSolutionPoint();
                        gui.update(new UpdateObject(collect, new Point3d(bestSolution.x, bestSolution.y, currentBestSolution.getBestPE()), algorithmCounter, currentIteration));
                    }
                    //Wait for other Thread to Synchronize
                    barrier.await();

                    while (paused) {
                        try {
                            Thread.sleep(5); //wait
                        } catch (Exception exp) {

                        }
                    }
                }else if(currentIteration>= GlobalConfig.Iterations) {
                    //If Thread is finished, wait 200ms -> next Iteration and wait for Stop or iterations are raised....
                    try{
                        Thread.sleep(5);
                    }catch (Exception exp){

                    }
                }
            }
        } catch (Exception exp) {
            if (GlobalConfig.loggin) {
                loggerFileWriter.logInformation("PSO Thread ended in an Exception: " + exp);
            } else {
                System.out.println("Exception in PSO Thread "+exp.toString());
                exp.printStackTrace();}
        }

        try {
            barrier.await();
        }catch (Exception exp){

        }
        double energyEnd = molecules.stream().map(m -> m.getKE() + m.getPE()).collect(Collectors.summingDouble(d -> d)) + buffer.getBuffer();
        System.out.println("PSO algorithm ("+algorithmCounter+") Best Point"+(new Point3d(currentBestSolution.getBestSolutionPoint().x,currentBestSolution.getBestSolutionPoint().y,currentBestSolution.getBestPE()).toParseFormat()));
        System.out.println("PSO algorithm ("+algorithmCounter+") Engieblinazstart : " + energyStart + "Engieblinazende : " + energyEnd + " start - end" + (energyStart - energyEnd));
        System.out.println("PSO algorithm ("+algorithmCounter+") interMol: " + interMolColHappend + " onWallin " + onWallCollHappend + " PSO instead of Synthesis " +psoHappendInsteadOfSynthesis +" PSO instead of Decomp "+ psoHappendInsteadOfDecomp);
        System.out.println("PSO algorithm ("+algorithmCounter+") Average KE end " + molecules.stream().map(m -> m.getKE()).collect(Collectors.averagingDouble(d -> d)));
        System.out.println("PSO algorithm ("+algorithmCounter+") buffer at end " + buffer.getBuffer());

    }

    @Override
    public void stop(){
        stopped = true;
    }

    @Override
    public void pause() {
        paused = true;
        stopped= false;
    }

    @Override
    public void goOn() {
        stopped = false;
        paused = false;
    }

}

