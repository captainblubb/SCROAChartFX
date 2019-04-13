package ParameterAnalysis;

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
import algorithmns.croa.chemicalReaction.ChemicalReaction;
import algorithmns.croa.chemicalReaction.IChemicalReactions;
import algorithmns.croa.chemicalReaction.decomposition.Decomposition;
import algorithmns.croa.chemicalReaction.decomposition.IDecomposition;
import algorithmns.croa.chemicalReaction.interMolecularIneffectiveCollission.IInterMolecularIneffectiveCollision;
import algorithmns.croa.chemicalReaction.interMolecularIneffectiveCollission.InterMolecularIneffectiveCollision;
import algorithmns.croa.chemicalReaction.onWallIneffectiveCollission.IOnWallIneffectiveCollission;
import algorithmns.croa.chemicalReaction.onWallIneffectiveCollission.OnWallIneffectiveCollission;
import algorithmns.croa.chemicalReaction.synthesis.ISynthesis;
import algorithmns.croa.chemicalReaction.synthesis.Synthesis;
import algorithmns.croa.models.Buffer;
import algorithmns.croa.models.IMolecule;
import algorithmns.croa.models.MoleculeCROA;
import algorithmns.croa.models.Point;
import algorithmns.equations.IEquation;
import algorithmns.equations.boundrys.Boundary;
import configuration.configuration.globalConfig;
import configuration.logger.LoggerFileWriter;
import gui.updateObject.Point3d;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CROAParamAnalysis implements IAlgorithm {

    public IEquation equation;
    public Buffer buffer;
    public IChemicalReactions chemicalReactions;
    public ArrayList<IMolecule> molecules;
    public IRandomGenerator randomGenerator;
    public ICalculatorPE calculatorPE;
    public LoggerFileWriter loggerFileWriter;
    //Observerpattern which stores the best current solution of all Molecues
    public IBestSolutionListener currentBestSolution;
    //For update -> which algorithmn needs to be updated 1 or 2
    public int algorithmCounter;



    public CROAParamAnalysis(IEquation equation, int algorithmCounter){
        this.equation = equation;
        this.algorithmCounter = algorithmCounter;
        if(globalConfig.loggin) {
            this.loggerFileWriter = new LoggerFileWriter("croa",algorithmCounter);
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
        //decomposition
        IDecomposition decomposition = new Decomposition(randomGenerator,buffer,currentBestSolution);
        //synthesis
        ISynthesis synthesis = new Synthesis(randomGenerator,buffer,currentBestSolution);
        //onwallIneffective
        IOnWallIneffectiveCollission onWallIneffectiveCollission = new OnWallIneffectiveCollission(randomGenerator,neighbourhoodSearchSingle,buffer);
        //intermolecularCollission
        IInterMolecularIneffectiveCollision interMolecularIneffectiveCollission = new InterMolecularIneffectiveCollision(randomGenerator,neighbourhoodSearchTwo,buffer);
        //Chemical Reaction
        chemicalReactions = new ChemicalReaction(decomposition,onWallIneffectiveCollission,synthesis,interMolecularIneffectiveCollission);

        //Population
        molecules = generateRandomPopulation(equation, equation.getConfiguration().PopSize, randomGenerator);

    }



    public ArrayList<IMolecule> generateRandomPopulation(IEquation equation, int initialPopoSize, IRandomGenerator randomGenerator){

        ArrayList<IMolecule> population = new ArrayList<>();

        Boundary boundary = equation.getBoundary();
        //Generate Population in Boundries


        //Check boundries start in boundrie not on boundrie
        double xRange = (boundary.getMaxX() - boundary.getMinX());
        if(xRange-2* globalConfig.distanceToBoundrys > 1){
            xRange-=(2* globalConfig.distanceToBoundrys);
        }
        double yRange = (boundary.getMaxY() - boundary.getMinY());
        if(yRange-2* globalConfig.distanceToBoundrys > 1){
            yRange-=(2* globalConfig.distanceToBoundrys);
        }


        for (int i = 0; i<initialPopoSize;i++){

            double fixInX = boundary.getMinX()+ globalConfig.distanceToBoundrys;
            double fixInY = boundary.getMinY()+ globalConfig.distanceToBoundrys;

            double randomX = randomGenerator.nextDouble()*xRange;
            double randomY = randomGenerator.nextDouble()*yRange;

            Point point = new Point(fixInX+randomX,fixInY+randomY);
           //System.out.println(point.toParseFormat());
            MoleculeCROA molecule = new MoleculeCROA(point, equation.getConfiguration().initialKE,calculatorPE,equation,currentBestSolution);
            population.add(molecule);

        }

        return population;
    }



    @Override
    public void stop(){
    }

    @Override
    public void pause() {

    }

    @Override
    public void goOn() {

    }

    @Override
    public void run() {
                    int onWallCollHappend = 0;
                    int interMolColHappend = 0;
                    int decompositionTrys = 0;
                    int decompositionHappend = 0;
                    int synthesisHappend = 0;
                    int synthesisTrys = 0;
                    double energyStart = molecules.stream().map(m -> m.getKE() + m.getPE()).collect(Collectors.summingDouble(d -> d)) + buffer.getBuffer();

                    try {

                        int currentIteration = 0;

                        //Update at ZERO
                        List<Point3d> collect = molecules.stream().map(m -> new Point3d(m.getCurrentStructure().x, m.getCurrentStructure().y, m.getPE())).collect(Collectors.toList());
                        Point bestSolution = currentBestSolution.getBestSolutionPoint();

                        //Wait for other Thread to Synchronize

                        while (currentIteration < globalConfig.Iterations ) {
                            currentIteration++;
                            double randomCollission = randomGenerator.nextDouble();


                            //unimolekular reaction
                            if (randomCollission >= equation.getConfiguration().moleColl || molecules.size() == 1) {

                                int randomIndex = randomGenerator.nextInt(0, molecules.size() - 1);

                                IMolecule selectedMolecule = molecules.get(randomIndex);

                                if (selectedMolecule.getNumberOfHits() >= equation.getConfiguration().numberOfHitsForDecomposition) {
                                    //decomposition
                                    decompositionTrys++;
                                    List<IMolecule> decompositionResult = chemicalReactions.decomposition(selectedMolecule);
                                    if (decompositionResult.size() == 2) {

                                        // System.out.println("--------Decomposition------------");
                                        decompositionHappend++;
                                        molecules.remove(randomIndex);
                                        molecules.add(decompositionResult.get(0));
                                        molecules.add(decompositionResult.get(1));
                                    }

                                } else {
                                    //onWall
                                    // System.out.println("--------onWallIneffectivce------------");
                                    onWallCollHappend++;
                                    chemicalReactions.onWallIneffectivCollission(selectedMolecule);
                                }

                                //intermolekular reaction
                            } else {

                                int randomIndex1 = randomGenerator.nextInt(0, molecules.size() - 1);
                                int randomIndex2 = randomGenerator.nextInt(0, molecules.size() - 1);

                                while (randomIndex1 == randomIndex2) {
                                    randomIndex2 = randomGenerator.nextInt(0, molecules.size() - 1);
                                }

                                IMolecule molecule1 = molecules.get(randomIndex1);
                                IMolecule molecule2 = molecules.get(randomIndex2);

                                if (molecule1.getKE() <= equation.getConfiguration().minimumKe && molecule2.getKE() <= equation.getConfiguration().minimumKe) {
                                    //synthesis
                                    synthesisTrys++;

                                    IMolecule synthesisResult = chemicalReactions.synthesis(molecule1, molecule2);
                                    if (synthesisResult != null) {
                                        // System.out.println("--------Synthesis------------");
                                        synthesisHappend++;
                                        molecules.remove(molecule1);
                                        molecules.remove(molecule2);
                                        molecules.add(synthesisResult);
                                    }

                                } else {
                                    //intermolcol
                                    // System.out.println("--------interMolIneffectivce------------");
                                    interMolColHappend++;

                                    chemicalReactions.interMolecularIneffectiveCollision(molecule1, molecule2);
                                }

                            }


                            if (globalConfig.loggin) {
                                loggerFileWriter.logBestSolution("Croa", currentIteration, currentBestSolution.getBestSolutionPoint(), currentBestSolution.getBestPE());
                            }

                            if (currentIteration % globalConfig.updateAfterIterations == 0 ) {
                                collect = molecules.stream().map(m -> new Point3d(m.getCurrentStructure().x, m.getCurrentStructure().y, m.getPE())).collect(Collectors.toList());
                                bestSolution = currentBestSolution.getBestSolutionPoint();
                            }


                        }


                    } catch (Exception exp) {
                       if (globalConfig.loggin) {
                           loggerFileWriter.logInformation("croa Thread ended in an Exception: " + exp);
                       }else {
                           System.out.println("Exception in CROAParamAnalysis Thread"+exp.toString());
                       }
                    }


               // System.out.println("minPE: __ "+currentBestSolution.getBestPE());
                /*
                    double energyEnd = molecules.stream().map(m -> m.getKE() + m.getPE()).collect(Collectors.summingDouble(d -> d)) + buffer.getBuffer();
                    System.out.println("CROAParamAnalysis  algorithm ("+algorithmCounter+") Best Point"+(new Point3d(currentBestSolution.getBestSolutionPoint().x,currentBestSolution.getBestSolutionPoint().y,currentBestSolution.getBestPE()).toParseFormat()));
                    System.out.println("CROAParamAnalysis  algorithm (" +algorithmCounter+ ") Engieblinazstart : " + energyStart + "Engieblinazende : " + energyEnd + " start - end" + (energyStart - energyEnd));
                    System.out.println("CROAParamAnalysis  algorithm (" +algorithmCounter+ ") interMol: " + interMolColHappend + " onWallin " + onWallCollHappend + " synthesis trys: "+synthesisTrys+" synthesis happend : " + synthesisHappend +" decomposition trys: "+ decompositionTrys+" decomposition happend " + decompositionHappend);
                    System.out.println("CROAParamAnalysis  algorithm (" +algorithmCounter+ ") Average KE end " + molecules.stream().map(m -> m.getKE()).collect(Collectors.averagingDouble(d -> d)));
                    System.out.println("CROAParamAnalysis  algorithm (" +algorithmCounter+ ") buffer at end " + buffer.getBuffer());
                    System.out.println("___________________________________________");
                    System.out.println();

                    */
    }




}
