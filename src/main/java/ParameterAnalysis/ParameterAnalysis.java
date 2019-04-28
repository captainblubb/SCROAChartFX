package ParameterAnalysis;

import ParameterAnalysis.CSVWriter.CSVWriterParamAnalysis;
import algorithmns.equations.Ackley;
import algorithmns.equations.IEquation;
import algorithmns.equations.Rastrigin;
import algorithmns.equations.Rosenbrock;
import configuration.configuration.ConfigurationAlgorithm;
import gui.updateObject.Point3d;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParameterAnalysis {

    public int stepsEachVarMinusOne = 200;
    private  int stepCounter = 0;
    public  int RunsEachConfigMultiple =1 ;


    //default
    private static int PopSize = 100;
    private double c1 = 1.4962;
    private double c2 = 1.4962;
    private double w = 0.52984;
    private double maxVelocity = 15;
    private double initialMaxLengthVelocityPerDim = 3;
    private double minVelocityStep = 0.0;
    private int trysOfPSOUpdate = 1; //Not used
    private double distanceToBoundrys = 0.005; //Not used
    private double keMinLossRate = 0.1;
    private double moleColl = 0.6;
    private double initialKE = 20.0;
    private double minimumKe = 5.0;
    private double initialBuffer = 200.0;
    private int numberOfHitsForDecomposition = 30;
    private double moveAlongGradeMaxStep = 0.0001;
    private double impactOfOtherMolecule = 0.185;

    //dynamicly modified
    private static int PopSizeMin = 5;
    private static int PopSizeMax = 100;
    private double c1Min = 0;
    private double c1Max = 2;
    private double c2Min = 0;
    private double c2Max = 2;
    private double wMin = 0.0;
    private double wMax = 1;
    private double maxVelocityMin = 0;
    private double maxVelocityMax = 25;
    private double initialMaxLengthVelocityPerDimMin = 3;
    private double initialMaxLengthVelocityPerDimMax = 3;
    private double minVelocityStepMin = 0.0;
    private double minVelocityStepMax = 100;
    private double keMinLossRateMin = 0.0;
    private double keMinLossRateMax = 1.0;
    private double moleCollMin = 0.0;
    private double moleCollMax = 1.0;
    private double initialKEMin = 0.0;
    private double initialKEMax = 2000.0;
    private double minimumKeMin = 0;
    private double minimumKeMax = 100;
    private double initialBufferMin = 0.0;
    private double initialBufferMax = 100000.0;
    private int numberOfHitsForDecompositionMin = 0;
    private int getNumberOfHitsForDecompositionMax = 140;
    private double moveAlongGradeMaxStepMin = 1;
    private double moveAlongGradeMaxStepMax = 0.0000000001;
    private double impactOfOtherMoleculeMin = 1;
    private double impactOfOtherMoleculeMax = 0.0000000001;



    public ParameterAnalysis(){

    }

    public void EveryParameterAnalyticsSCROA(String function){

        double totalLoop = Math.pow(stepsEachVarMinusOne,14);

        ConfigurationAlgorithm configurationAlgorithm = new ConfigurationAlgorithm();

        for (int a = stepCounter; a <=stepsEachVarMinusOne;a++){

            System.out.println("____________________Progress :: "+a+"/"+stepsEachVarMinusOne);

            configurationAlgorithm = getConfig(1,configurationAlgorithm,((0.0+a/ 0.0+stepsEachVarMinusOne)));

            for (int b = stepCounter; b <=stepsEachVarMinusOne;b++){


                System.out.println("____________________Sub1Progress :: "+b+"/"+stepsEachVarMinusOne);

                configurationAlgorithm = getConfig(2,configurationAlgorithm,((0.0+b/ 0.0+stepsEachVarMinusOne)));

                for (int c = stepCounter; c <=stepsEachVarMinusOne;c++){


                    System.out.println("____________________Sub2Progress :: "+c+"/"+stepsEachVarMinusOne);

                    configurationAlgorithm = getConfig(3,configurationAlgorithm,((0.0+c/0.0+stepsEachVarMinusOne)));

                    for (int d = stepCounter; d <=stepsEachVarMinusOne;d++){

                        System.out.println("____________________Sub3Progress :: "+c+"/"+stepsEachVarMinusOne);

                        configurationAlgorithm = getConfig(4,configurationAlgorithm,((0.0+d/0.0+stepsEachVarMinusOne)));

                        for (int e = stepCounter; e <=stepsEachVarMinusOne;e++){

                            configurationAlgorithm = getConfig(5,configurationAlgorithm,((0.0+e/0.0+stepsEachVarMinusOne)));

                            for (int f = stepCounter; f <=stepsEachVarMinusOne;f++){

                                configurationAlgorithm = getConfig(6,configurationAlgorithm,((0.0+f/0.0+stepsEachVarMinusOne)));

                                for (int g= stepCounter; g <=stepsEachVarMinusOne;g++){

                                    configurationAlgorithm = getConfig(7,configurationAlgorithm,((0.0+g/0.0+stepsEachVarMinusOne)));

                                    for (int h = stepCounter; h <=stepsEachVarMinusOne;h++){

                                        configurationAlgorithm = getConfig(8,configurationAlgorithm,((0.0+h/0.0+stepsEachVarMinusOne)));

                                        for (int i = stepCounter; i <=stepsEachVarMinusOne;i++){

                                            configurationAlgorithm = getConfig(9,configurationAlgorithm,((0.0+i/0.0+stepsEachVarMinusOne)));

                                            for (int j = stepCounter;  j<=stepsEachVarMinusOne;j++){

                                                configurationAlgorithm = getConfig(10,configurationAlgorithm,((0.0+j/0.0+stepsEachVarMinusOne)));

                                                for (int k = stepCounter; k <=stepsEachVarMinusOne;k++){

                                                    configurationAlgorithm = getConfig(11,configurationAlgorithm,((0.0+k/0.0+stepsEachVarMinusOne)));

                                                    for (int l = stepCounter; l <=stepsEachVarMinusOne;l++){

                                                        configurationAlgorithm = getConfig(12,configurationAlgorithm,((0.0+l/0.0+stepsEachVarMinusOne)));

                                                        for (int m = stepCounter; m <=stepsEachVarMinusOne;m++){

                                                            configurationAlgorithm = getConfig(13,configurationAlgorithm,((0.0+m/0.0+stepsEachVarMinusOne)));

                                                            for (int n = stepCounter; n <=stepsEachVarMinusOne;n++){

                                                                configurationAlgorithm = getConfig(14,configurationAlgorithm,((0.0+n/0.0+stepsEachVarMinusOne)));

                                                                System.out.println("Percantage done : " +
                                                                        ((double)
                                                                                a*Math.pow(13,stepsEachVarMinusOne)+
                                                                                b*Math.pow(12,stepsEachVarMinusOne)+
                                                                                c*Math.pow(11,stepsEachVarMinusOne)+
                                                                                d*Math.pow(10,stepsEachVarMinusOne)+
                                                                                e*Math.pow(9,stepsEachVarMinusOne)+
                                                                                f*Math.pow(8,stepsEachVarMinusOne)+
                                                                                g*Math.pow(7,stepsEachVarMinusOne)+
                                                                                h*Math.pow(6,stepsEachVarMinusOne)+
                                                                                i*Math.pow(5,stepsEachVarMinusOne)+
                                                                                j*Math.pow(4,stepsEachVarMinusOne)+
                                                                                k*Math.pow(3,stepsEachVarMinusOne)+
                                                                                l*Math.pow(2,stepsEachVarMinusOne)+
                                                                                m*Math.pow(1,stepsEachVarMinusOne)+
                                                                                +n
                                                                                 )/totalLoop);


                                                                for (int o = stepCounter; o <=stepsEachVarMinusOne;o++){

                                                                    configurationAlgorithm = getConfig(15,configurationAlgorithm,((0.0+o/0.0+stepsEachVarMinusOne)));

                                                                    //CSVWriterParamAnalysis csvWriterParamAnalysisCROA = new CSVWriterParamAnalysis("AnalysisData/MultipleParam/CROA/"+function+"/ParameterAnalysis.csv");
                                                                    CSVWriterParamAnalysis csvWriterParamAnalysisSCROA = new CSVWriterParamAnalysis("AnalysisData/MultipleParam/SCROA/"+function+"/ParameterAnalysis.csv");

                                                                    //ArrayList<CROAParamAnalysis> CROAs = new ArrayList<>();
                                                                    ArrayList<SCROAParamAnalysis> SCROAs = new ArrayList<>();

                                                                    IEquation equation = getiEquation(function, configurationAlgorithm);

                                                                    for (int p = 0 ; p < RunsEachConfigMultiple; p++) {

                                                                        CROAParamAnalysis croaParamAnalysis = new CROAParamAnalysis(equation, 1);
                                                                        SCROAParamAnalysis scroaParamAnalysis = new SCROAParamAnalysis(equation, 2);

                                                                       // CROAs.add(croaParamAnalysis);
                                                                        SCROAs.add(scroaParamAnalysis);

                                                                    }

                                                                    //ExecutorService executorService = Executors.newFixedThreadPool(CROAs.size()+SCROAs.size());
                                                                    ExecutorService executorService = Executors.newFixedThreadPool(SCROAs.size());

                                                                    /*
                                                                    for (int r =0; r <CROAs.size();r++){

                                                                        executorService.execute(CROAs.get(r));

                                                                    }
                                                                    */

                                                                    for (int x = 0; x <SCROAs.size(); x++) {

                                                                        executorService.execute(SCROAs.get(x));
                                                                    }
                                                                    executorService.shutdown();
                                                                    // Wait until all threads are finish
                                                                    while (!executorService.isTerminated()) {


                                                                    }


                                                                    /*
                                                                    for (int r =0; r <CROAs.size();r++){

                                                                        String point = (new Point3d(CROAs.get(r).currentBestSolution.getBestSolutionPoint().x,CROAs.get(r).currentBestSolution.getBestSolutionPoint().y,CROAs.get(r).currentBestSolution.getBestPE()).toStringNotRounded());

                                                                        try {

                                                                            csvWriterParamAnalysisCROA.addRecord(function,"CROA", point, "" + CROAs.get(r).currentBestSolution.getBestPE(), CROAs.get(r).equation.getConfiguration());

                                                                        }catch (Exception exp) {
                                                                            System.out.println("Failed logging record");
                                                                        }
                                                                    }

                                                                    try {

                                                                        csvWriterParamAnalysisCROA.flush();
                                                                    }catch (Exception exp) {
                                                                        System.out.println("flushing logging record");
                                                                    }
                                                                    */

                                                                    for (int q =0; q <SCROAs.size();q++){

                                                                        String point = (new Point3d(SCROAs.get(q).currentBestSolution.getBestSolutionPoint().x,SCROAs.get(q).currentBestSolution.getBestSolutionPoint().y,SCROAs.get(q).currentBestSolution.getBestPE()).toStringNotRounded());
                                                                        try {
                                                                            csvWriterParamAnalysisSCROA.addRecord(function,"SCROA", point, "" + SCROAs.get(q).currentBestSolution.getBestPE(), SCROAs.get(q).equation.getConfiguration());
                                                                        }catch (Exception exp) {
                                                                            System.out.println("Failed logging record");
                                                                        }

                                                                    }

                                                                    try {

                                                                        csvWriterParamAnalysisSCROA.flush();
                                                                    }catch (Exception exp) {
                                                                        System.out.println("flushing logging record");
                                                                    }


                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void SingleParameterAnalyticsSpecificParam(String function, int configParameter, int amountOfRecords, String algorithmn){

        stepCounter = 1;
        int innerCount = Runtime.getRuntime().availableProcessors();

        int innerLoop = innerCount;
        int outerLoop = amountOfRecords!= 0? (int)((double)amountOfRecords/(double)innerCount) : 0;

        for (int i = 0; i <=outerLoop; i++){

            System.out.println("Process: "+i+"/"+outerLoop);

            stepCounter%=stepsEachVarMinusOne;
            stepCounter++;
            CSVWriterParamAnalysis csvWriterParamAnalysisCROA=null;
            CSVWriterParamAnalysis csvWriterParamAnalysisSCROA=null;

            if(algorithmn.equals("CROA")) {
                 csvWriterParamAnalysisCROA = new CSVWriterParamAnalysis("AnalysisData/SingleParam/CROA/" + function + "/"+function+"_"+algorithmn+"ParameterAnalysis_" + getConfigByName(configParameter) + ".csv");
            }

            if (algorithmn.equals("SCROA")) {
                 csvWriterParamAnalysisSCROA = new CSVWriterParamAnalysis("AnalysisData/SingleParam/SCROA/" + function + "/"+function+"_"+algorithmn+"ParameterAnalysis_" + getConfigByName(configParameter) + ".csv");
            }
            ArrayList<CROAParamAnalysis> CROAs = new ArrayList<>();
            ArrayList<SCROAParamAnalysis> SCROAs = new ArrayList<>();
            ConfigurationAlgorithm config = getConfig(configParameter);

            IEquation equation = getiEquation(function, config);

            for (int k = 0; k<innerLoop;k++){


                if (algorithmn.equals("CROA")) {
                    CROAParamAnalysis croaParamAnalysis = new CROAParamAnalysis(equation, 1);
                    CROAs.add(croaParamAnalysis);

                }else if (algorithmn.equals("SCROA")){

                    SCROAParamAnalysis scroaParamAnalysis = new SCROAParamAnalysis(equation,2);
                    SCROAs.add(scroaParamAnalysis);

                }

            }


            ExecutorService executorService = Executors.newFixedThreadPool(CROAs.size()+SCROAs.size());

            for (int n =0; n <CROAs.size();n++){
                executorService.execute(CROAs.get(n));
            }

            for (int k = 0; k <SCROAs.size(); k++) {

                executorService.execute(SCROAs.get(k));
            }
            executorService.shutdown();
            // Wait until all threads are finish
            while (!executorService.isTerminated()) {

            }


            if (algorithmn.equals("CROA")) {
                for (int n = 0; n < CROAs.size(); n++) {

                    String point = (new Point3d(CROAs.get(n).currentBestSolution.getBestSolutionPoint().x, CROAs.get(n).currentBestSolution.getBestSolutionPoint().y, CROAs.get(n).currentBestSolution.getBestPE()).toStringNotRounded());

                    try {

                        csvWriterParamAnalysisCROA.addRecord(function, "CROA", point, "" + CROAs.get(n).currentBestSolution.getBestPE(), CROAs.get(n).equation.getConfiguration());

                    } catch (Exception exp) {
                        System.out.println("Failed logging record");
                    }
                }

                try {

                    csvWriterParamAnalysisCROA.flush();
                } catch (Exception exp) {
                    System.out.println("ERROR: flushing logging record");
                }

            }

            if (algorithmn.equals("SCROA")) {
                for (int k = 0; k < SCROAs.size(); k++) {

                    String point = (new Point3d(SCROAs.get(k).currentBestSolution.getBestSolutionPoint().x, SCROAs.get(k).currentBestSolution.getBestSolutionPoint().y, SCROAs.get(k).currentBestSolution.getBestPE()).toStringNotRounded());
                    try {
                        csvWriterParamAnalysisSCROA.addRecord(function, "SCROA", point, "" + SCROAs.get(k).currentBestSolution.getBestPE(), SCROAs.get(k).equation.getConfiguration());
                    } catch (Exception exp) {
                        System.out.println("Failed logging record");
                    }

                }

                try {

                    csvWriterParamAnalysisSCROA.flush();
                } catch (Exception exp) {
                    System.out.println("ERROR: flushing logging record");
                }
            }

        }

    }


    public void EveryImportantParameterAnalyticsSCROA(String function) {

        //Configs: 1,2,3,4,11,12,13,15

          /*
            case 1: return "popSize";
            case 2: return "c1";
            case 3: return "c2";
            case 4: return "w";
                     case 5: return "impactOfOtherMolecule";
                     case 6: return "initalBuffer";
                     case 7: return "initalKE";
                     case 8: return "minVelocityStep";
                     case 9: return "maxLengthVelocityPerDimInitial";
                     case 10: return "maxVelocity";
            case 11: return "minKELossRate";
            case 12: return "moleColl";
            case 13: return "numberOfHitsForDecomp";
            case 14: return "moveAlongGradeMaxStep";
            case 15: return "minimumKE";
         */


        double maxIterations = Math.pow((stepsEachVarMinusOne+1)*RunsEachConfigMultiple,8);
        double currentIteration = 0;


        ConfigurationAlgorithm configurationAlgorithm = new ConfigurationAlgorithm();

        for (int a = stepCounter; a <= stepsEachVarMinusOne; a++) {

            System.out.println("SCROA__"+function+"______________Progress :: " + a + "/" + stepsEachVarMinusOne);

            configurationAlgorithm = getConfig(2, configurationAlgorithm, (((((double) a) / ((double) stepsEachVarMinusOne)))));

            for (int b = stepCounter; b <= stepsEachVarMinusOne; b++) {

                System.out.println("SCROA__"+function+"____________________Sub1Progress :: " + b + "/" + stepsEachVarMinusOne);

                configurationAlgorithm = getConfig(3, configurationAlgorithm, ((((double) b) / ((double) stepsEachVarMinusOne))));

                for (int c = stepCounter; c <= stepsEachVarMinusOne; c++) {

                    configurationAlgorithm = getConfig(4, configurationAlgorithm, ((((double) c) / ((double) stepsEachVarMinusOne))));

                    /*
                    for (int d = stepCounter; d <= stepsEachVarMinusOne; d++) {

                        configurationAlgorithm = getConfig(5, configurationAlgorithm, ((((double) d) / ((double) stepsEachVarMinusOne))));
                    */
                        for (int e = stepCounter; e <= stepsEachVarMinusOne; e++) {

                            configurationAlgorithm = getConfig(11, configurationAlgorithm, ((((double) e) / ((double) stepsEachVarMinusOne))));

                            for (int f = stepCounter; f <= stepsEachVarMinusOne; f++) {

                                configurationAlgorithm = getConfig(12, configurationAlgorithm, ((((double) f) / ((double) stepsEachVarMinusOne))));

                                CSVWriterParamAnalysis csvWriterParamAnalysisSCROA = new CSVWriterParamAnalysis("AnalysisData/MultipleParam/SCROA/" + function + "/ParameterAnalysisSCROAImportantParams.csv");

                                System.out.println("SCROA__"+function+"____Progress "+currentIteration+"/"+maxIterations+" in % "+ ((int)((0.0+currentIteration/0.0+maxIterations)*100)));

                                ArrayList<SCROAParamAnalysis> SCROAs = new ArrayList<>();


                                for (int g = stepCounter; g <= stepsEachVarMinusOne; g++) {

                                    configurationAlgorithm = getConfig(13, configurationAlgorithm, ((((double) g) / ((double) stepsEachVarMinusOne))));


                                    for (int h = stepCounter; h <= stepsEachVarMinusOne; h++) {

                                        configurationAlgorithm = getConfig(1, configurationAlgorithm, ((((double) h) / ((double) stepsEachVarMinusOne))));

                                        for (int i = stepCounter; i <= stepsEachVarMinusOne; i++) {

                                            configurationAlgorithm = getConfig(15, configurationAlgorithm, ((((double) i) / ((double) stepsEachVarMinusOne))));



                                            IEquation equation = getiEquation(function, configurationAlgorithm);

                                            for (int p = 0; p < RunsEachConfigMultiple; p++) {

                                                currentIteration++;
                                                SCROAParamAnalysis scroaParamAnalysis = new SCROAParamAnalysis(equation, 2);
                                                SCROAs.add(scroaParamAnalysis);

                                            }
                                        }
                                    }
                                }

                                ExecutorService executorService = Executors.newFixedThreadPool(SCROAs.size());


                                for (int x = 0; x < SCROAs.size(); x++) {

                                    executorService.execute(SCROAs.get(x));
                                }
                                executorService.shutdown();
                                // Wait until all threads are finish
                                while (!executorService.isTerminated()) {


                                }


                                for (int q = 0; q < SCROAs.size(); q++) {

                                    String point = (new Point3d(SCROAs.get(q).currentBestSolution.getBestSolutionPoint().x, SCROAs.get(q).currentBestSolution.getBestSolutionPoint().y, SCROAs.get(q).currentBestSolution.getBestPE()).toStringNotRounded());
                                    try {
                                        csvWriterParamAnalysisSCROA.addRecord(function, "SCROA", point, "" + SCROAs.get(q).currentBestSolution.getBestPE(), SCROAs.get(q).equation.getConfiguration());
                                    } catch (Exception exp) {
                                        System.out.println("Failed logging record");
                                    }

                                }

                                try {

                                    csvWriterParamAnalysisSCROA.flush();
                                } catch (Exception exp) {
                                    System.out.println("flushing logging record");
                                }
                            }
                        }
                   // }
                }

            }
        }
    }

    public void EveryImportantParameterAnalyticsCROA(String function) {

        //1,5,11,12,13,15

        /*
                    case 1: return "popSize";
            case 2: return "c1";
            case 3: return "c2";
            case 4: return "w";
                    case 5: return "impactOfOtherMolecule";
            case 6: return "initalBuffer";
            case 7: return "initalKE";
            case 8: return "minVelocityStep";
            case 9: return "maxLengthVelocityPerDimInitial";
            case 10: return "maxVelocity";
                     case 11: return "minKELossRate";
                     case 12: return "moleColl";
                     case 13: return "numberOfHitsForDecomp";
            case 14: return "moveAlongGradeMaxStep";
                     case 15: return "minimumKE";

         */
        // duration = ((stepsEachVarMinusOne+1)*RunsEachConfigMultiple exp 6 )

        ConfigurationAlgorithm configurationAlgorithm = new ConfigurationAlgorithm();

        double maxIterations = Math.pow((stepsEachVarMinusOne+1)*RunsEachConfigMultiple,6);
        double currentIteration = 0;
        for (int a = stepCounter; a <= stepsEachVarMinusOne; a++) {
            System.out.println("CROA__"+function+"_______________Progress :: " + a + "/" + stepsEachVarMinusOne);
            configurationAlgorithm = getConfig(1, configurationAlgorithm, (((((double) a) / ((double) stepsEachVarMinusOne)))));

            for (int b = stepCounter; b <= stepsEachVarMinusOne; b++) {

                double currentIterationAc = (a)*Math.pow(stepsEachVarMinusOne,5) + (b)*Math.pow(stepsEachVarMinusOne,4);

                System.out.println("CROA__"+function+"____Progress "+currentIterationAc+"/"+maxIterations+" in % "+ ((double)((int)(currentIterationAc/maxIterations)*100)));
                configurationAlgorithm = getConfig(5, configurationAlgorithm, ((((double) b) / ((double) stepsEachVarMinusOne))));

                for (int c = stepCounter; c <= stepsEachVarMinusOne; c++) {
                    configurationAlgorithm = getConfig(11, configurationAlgorithm, ((((double) c) / ((double) stepsEachVarMinusOne))));

                    for (int d = stepCounter; d <= stepsEachVarMinusOne; d++) {
                        configurationAlgorithm = getConfig(12, configurationAlgorithm, ((((double) d) / ((double) stepsEachVarMinusOne))));

                        CSVWriterParamAnalysis csvWriterParamAnalysisCROA = new CSVWriterParamAnalysis("AnalysisData/MultipleParam/CROA/" + function + "/ParameterAnalysisCROAImportantVariables.csv");
                        //CSVWriterParamAnalysis csvWriterParamAnalysisSCROA = new CSVWriterParamAnalysis("AnalysisData/MultipleParam/SCROA/" + function + "/ParameterAnalysis.csv");

                        ArrayList<CROAParamAnalysis> CROAs = new ArrayList<>();
                        //ArrayList<SCROAParamAnalysis> SCROAs = new ArrayList<>();


                        System.out.println("CROA__"+function+"____Progress "+currentIteration+"/"+maxIterations+" in % "+((int)((0.0+currentIteration/0.0+maxIterations)*100)));

                        for (int e = stepCounter; e <= stepsEachVarMinusOne; e++) {
                            configurationAlgorithm = getConfig(13, configurationAlgorithm, ((((double) e) / ((double) stepsEachVarMinusOne))));

                            for (int f = stepCounter; f <= stepsEachVarMinusOne; f++) {
                                configurationAlgorithm = getConfig(15, configurationAlgorithm, ((((double) f) / ((double) stepsEachVarMinusOne))));


                                IEquation equation = getiEquation(function, configurationAlgorithm);

                                for (int p = 0; p < RunsEachConfigMultiple; p++) {
                                    currentIteration++;
                                    CROAParamAnalysis croaParamAnalysis = new CROAParamAnalysis(equation, 1);
                                    //SCROAParamAnalysis scroaParamAnalysis = new SCROAParamAnalysis(equation, 2);

                                    CROAs.add(croaParamAnalysis);
                                    //SCROAs.add(scroaParamAnalysis);

                                }


                            }
                        }

                        ExecutorService executorService = Executors.newFixedThreadPool(CROAs.size());
                        //ExecutorService executorService = Executors.newFixedThreadPool(SCROAs.size());

                        for (int r = 0; r < CROAs.size(); r++) {
                            executorService.execute(CROAs.get(r));
                        }

                        /*
                        for (int x = 0; x < SCROAs.size(); x++) {

                            executorService.execute(SCROAs.get(x));
                        }*/
                        executorService.shutdown();
                        // Wait until all threads are finish
                        while (!executorService.isTerminated()) {


                        }


                        for (int r = 0; r < CROAs.size(); r++) {

                            String point = (new Point3d(CROAs.get(r).currentBestSolution.getBestSolutionPoint().x, CROAs.get(r).currentBestSolution.getBestSolutionPoint().y, CROAs.get(r).currentBestSolution.getBestPE()).toStringNotRounded());

                            try {

                                csvWriterParamAnalysisCROA.addRecord(function, "CROA", point, "" + CROAs.get(r).currentBestSolution.getBestPE(), CROAs.get(r).equation.getConfiguration());

                            } catch (Exception exp) {
                                System.out.println("Failed logging record");
                            }
                        }

                        try {
                            csvWriterParamAnalysisCROA.flush();
                        } catch (Exception exp) {
                            System.out.println("flushing logging record");
                        }


                        /*
                        for (int q = 0; q < SCROAs.size(); q++) {

                            String point = (new Point3d(SCROAs.get(q).currentBestSolution.getBestSolutionPoint().x, SCROAs.get(q).currentBestSolution.getBestSolutionPoint().y, SCROAs.get(q).currentBestSolution.getBestPE()).toStringNotRounded());
                            try {
                                csvWriterParamAnalysisSCROA.addRecord(function, "SCROA", point, "" + SCROAs.get(q).currentBestSolution.getBestPE(), SCROAs.get(q).equation.getConfiguration());
                            } catch (Exception exp) {
                                System.out.println("Failed logging record");
                            }

                        }

                        try {

                            csvWriterParamAnalysisSCROA.flush();
                        } catch (Exception exp) {
                            System.out.println("flushing logging record");
                        }
                        */

                    }
                }

            }
        }

    }

    private IEquation getiEquation(String function, ConfigurationAlgorithm configurationAlgorithm) {
        IEquation equation = new Rastrigin(configurationAlgorithm);

        switch (function) {

            case "Rastirgin":
                equation = new Rastrigin(configurationAlgorithm);
                break;
            case "Ackley":
                equation = new Ackley(configurationAlgorithm);
                break;
            case "Rosenbrock":
                equation = new Rosenbrock(configurationAlgorithm);
                break;

        }
        return equation;
    }


    public void EveryImportantParameterAnalyticsSCROAWithMedian(String function) {

        //Configs: 1,2,3,4,11,12,13,15

          /*
            case 1: return "popSize";
            case 2: return "c1";
            case 3: return "c2";
            case 4: return "w";
                     case 5: return "impactOfOtherMolecule";
                     case 6: return "initalBuffer";
                     case 7: return "initalKE";
                     case 8: return "minVelocityStep";
                     case 9: return "maxLengthVelocityPerDimInitial";
                     case 10: return "maxVelocity";
            case 11: return "minKELossRate";
            case 12: return "moleColl";
            case 13: return "numberOfHitsForDecomp";
                     case 14: return "moveAlongGradeMaxStep";
            case 15: return "minimumKE";
         */

        stepsEachVarMinusOne = 7;
        RunsEachConfigMultiple = 6;
        double maxIterations = Math.pow((stepsEachVarMinusOne+1),8)*RunsEachConfigMultiple;
        double currentIteration = 0;



        for (int a = stepCounter; a <= stepsEachVarMinusOne; a++) {

            //  System.out.println("SCROA__"+function+"______________Progress :: " + a + "/" + stepsEachVarMinusOne);

            for (int b = stepCounter; b <= stepsEachVarMinusOne; b++) {

                // System.out.println("SCROA__"+function+"____________________Sub1Progress :: " + b + "/" + stepsEachVarMinusOne);

                CSVWriterParamAnalysis csvWriterParamAnalysisStatus = new CSVWriterParamAnalysis("AnalysisData/MultipleParam/SCROA/"+ function + "/SCROAStatus.csv",1.0);

                try {
                    csvWriterParamAnalysisStatus.addRecord(function,"SCROA",""+currentIteration+"/"+maxIterations);
                    csvWriterParamAnalysisStatus.flush();
                }catch (Exception exp){

                }
                for (int c = stepCounter; c <= stepsEachVarMinusOne; c++) {

                    /*
                    for (int d = stepCounter; d <= stepsEachVarMinusOne; d++) {

                        configurationAlgorithm = getConfig(5, configurationAlgorithm, ((((double) d) / ((double) stepsEachVarMinusOne))));
                    */
                    for (int e = stepCounter; e <= stepsEachVarMinusOne; e++) {


                        for (int f = stepCounter; f <= stepsEachVarMinusOne; f++) {


                            CSVWriterParamAnalysis csvWriterParamAnalysisSCROA = new CSVWriterParamAnalysis("AnalysisData/MultipleParam/SCROA/" + function + "/SCROAImportantParameter.csv",1);

                            //  System.out.println("SCROA__"+function+"____Progress "+currentIteration+"/"+maxIterations+" in % "+ ((int)((0.0+currentIteration/0.0+maxIterations)*100.0))+"%");

                            ArrayList<SCROAParamAnalysis> SCROAs = new ArrayList<>();


                            for (int g = stepCounter; g <= stepsEachVarMinusOne; g++) {


                                for (int h = stepCounter; h <= stepsEachVarMinusOne; h++) {


                                    for (int i = stepCounter; i <= stepsEachVarMinusOne; i++) {

                                        ConfigurationAlgorithm configurationAlgorithm = new ConfigurationAlgorithm();
                                        configurationAlgorithm = getConfig(1, configurationAlgorithm, (((((double) a) / ((double) stepsEachVarMinusOne)))));
                                        configurationAlgorithm = getConfig(2, configurationAlgorithm, ((((double) b) / ((double) stepsEachVarMinusOne))));
                                        configurationAlgorithm = getConfig(3, configurationAlgorithm, ((((double) c) / ((double) stepsEachVarMinusOne))));
                                        configurationAlgorithm = getConfig(4, configurationAlgorithm, ((((double) e) / ((double) stepsEachVarMinusOne))));
                                        configurationAlgorithm = getConfig(11, configurationAlgorithm, ((((double) f) / ((double) stepsEachVarMinusOne))));
                                        configurationAlgorithm = getConfig(12, configurationAlgorithm, ((((double) g) / ((double) stepsEachVarMinusOne))));
                                        configurationAlgorithm = getConfig(13, configurationAlgorithm, ((((double) h) / ((double) stepsEachVarMinusOne))));
                                        configurationAlgorithm = getConfig(15, configurationAlgorithm, ((((double) i) / ((double) stepsEachVarMinusOne))));

                                        IEquation equation = getiEquation(function, configurationAlgorithm);

                                        for (int p = 0; p < RunsEachConfigMultiple; p++) {

                                            SCROAParamAnalysis scroaParamAnalysis = new SCROAParamAnalysis(equation, 2);
                                            SCROAs.add(scroaParamAnalysis);

                                        }

                                        currentIteration+=RunsEachConfigMultiple;
                                    }
                                }
                            }

                            ExecutorService executorService = Executors.newFixedThreadPool(20);


                            for (int x = 0; x < SCROAs.size(); x++) {

                                executorService.execute(SCROAs.get(x));
                            }
                            executorService.shutdown();
                            // Wait until all threads are finish
                            while (!executorService.isTerminated()) {


                            }


                            for (int q = 0; q < (SCROAs.size()/RunsEachConfigMultiple); q++) {

                                int currentElement = q*RunsEachConfigMultiple;

                                double[] values = new double[RunsEachConfigMultiple];

                                for (int i = currentElement; i < (currentElement+RunsEachConfigMultiple); i++ ){
                                    values[i-currentElement] = SCROAs.get(i).currentBestSolution.getBestPE();
                                }

                                double median = getMedian(values);
                                double average = getAverage(values);

                                try {
                                    csvWriterParamAnalysisSCROA.addRecordSpecial(function, "SCROA", SCROAs.get(q).equation.getConfiguration(),median,average);
                                } catch (Exception exp) {
                                    System.out.println("Failed logging record");
                                }

                            }

                            try {

                                csvWriterParamAnalysisSCROA.flush();
                            } catch (Exception exp) {
                                System.out.println("flushing logging record");
                            }
                        }
                    }
                    // }
                }

            }
        }
    }

    public void EveryImportantParameterAnalyticsCROAWithMedian(String function) {

        //1,11,12,13,15

        /*
                    case 1: return "popSize";
            case 2: return "c1";
            case 3: return "c2";
            case 4: return "w";
            case 5: return "impactOfOtherMolecule";
            case 6: return "initalBuffer";
            case 7: return "initalKE";
            case 8: return "minVelocityStep";
            case 9: return "maxLengthVelocityPerDimInitial";
            case 10: return "maxVelocity";
                     case 11: return "minKELossRate";
                     case 12: return "moleColl";
                     case 13: return "numberOfHitsForDecomp";
            case 14: return "moveAlongGradeMaxStep";
                     case 15: return "minimumKE";

         */
        // duration = ((stepsEachVarMinusOne+1)*RunsEachConfigMultiple exp 6 )


        stepsEachVarMinusOne = 15;
        RunsEachConfigMultiple = 6;

        double maxIterations = Math.pow((stepsEachVarMinusOne + 1), 5) * RunsEachConfigMultiple;
        double currentIteration = 0;
        for (int a = stepCounter; a <= stepsEachVarMinusOne; a++) {

            for (int b = stepCounter; b <= stepsEachVarMinusOne; b++) {

                CSVWriterParamAnalysis csvWriterParamAnalysisStatus = new CSVWriterParamAnalysis("AnalysisData2/MultipleParam/CROA/" + function + "/CROAStatus.csv", 1.0);

                try {
                    csvWriterParamAnalysisStatus.addRecord(function, "CROA", "" + currentIteration + "/" + maxIterations);
                    csvWriterParamAnalysisStatus.flush();
                } catch (Exception exp) {

                }

                for (int c = stepCounter; c <= stepsEachVarMinusOne; c++) {

                    CSVWriterParamAnalysis csvWriterParamAnalysisCROA = new CSVWriterParamAnalysis("AnalysisData2/MultipleParam/CROA/" + function + "/CROAImportantParameter.csv", 1);

                    ArrayList<CROAParamAnalysis> CROAs = new ArrayList<>();


                    for (int d = stepCounter; d <= stepsEachVarMinusOne; d++) {
                        for (int e = stepCounter; e <= stepsEachVarMinusOne; e++) {

                            ConfigurationAlgorithm configurationAlgorithm = new ConfigurationAlgorithm();
                            configurationAlgorithm = getConfig(1, configurationAlgorithm, (((((double) a) / ((double) stepsEachVarMinusOne)))));
                            configurationAlgorithm = getConfig(11, configurationAlgorithm, ((((double) c) / ((double) stepsEachVarMinusOne))));
                            configurationAlgorithm = getConfig(12, configurationAlgorithm, ((((double) d) / ((double) stepsEachVarMinusOne))));
                            configurationAlgorithm = getConfig(13, configurationAlgorithm, ((((double) e) / ((double) stepsEachVarMinusOne))));
                            configurationAlgorithm = getConfig(15, configurationAlgorithm, ((((double) b) / ((double) stepsEachVarMinusOne))));

                            IEquation equation = getiEquation(function, configurationAlgorithm);

                            for (int p = 0; p < RunsEachConfigMultiple; p++) {
                                CROAParamAnalysis croaParamAnalysis = new CROAParamAnalysis(equation, 1);
                                CROAs.add(croaParamAnalysis);
                            }

                            currentIteration += 6;


                        }
                    }

                    ExecutorService executorService = Executors.newFixedThreadPool(41);

                    for (int r = 0; r < CROAs.size(); r++) {
                        executorService.execute(CROAs.get(r));
                    }

                    executorService.shutdown();
                    // Wait until all threads are finish
                    while (!executorService.isTerminated()) {


                    }

                    for (int q = 0; q < (CROAs.size() / RunsEachConfigMultiple); q++) {

                        int currentElement = q * RunsEachConfigMultiple;

                        double[] values = new double[RunsEachConfigMultiple];

                        for (int i = currentElement; i < (currentElement + RunsEachConfigMultiple); i++) {
                            values[i - currentElement] = CROAs.get(i).currentBestSolution.getBestPE();
                        }

                        double median = getMedian(values);
                        double average = getAverage(values);

                        try {
                            csvWriterParamAnalysisCROA.addRecordSpecial(function, "CROA", CROAs.get(q).equation.getConfiguration(), median, average);
                        } catch (Exception exp) {
                            System.out.println("Failed logging record");
                        }
                    }


                    try {
                        csvWriterParamAnalysisCROA.flush();
                    } catch (Exception exp) {
                        System.out.println("flushing logging record");
                    }

                }
            }

        }
    }


    private double getMedian(double[] numArray){
        Arrays.sort(numArray);
        double median;
        if (numArray.length % 2 == 0)
            median = ((double)numArray[numArray.length/2] + (double)numArray[numArray.length/2 - 1])/2;
        else
            median = (double) numArray[numArray.length/2];
        return median;
    }

    private double getAverage(double[] numArray){

        double average = 0.0;

        for (int i = 0; i < numArray.length;i++){
            average+=numArray[i];
        }

        return (average/(double)numArray.length);
    }

    public void SingleParamAnalytics(String function){


        for (int i = 1; i<=15; i++){

           CSVWriterParamAnalysis csvWriterParamAnalysisCROA = new CSVWriterParamAnalysis("AnalysisData/SingleParam/CROA/"+function+"/ParameterAnalysis_"+getConfigByName(i)+".csv");
           CSVWriterParamAnalysis csvWriterParamAnalysisSCROA = new CSVWriterParamAnalysis("AnalysisData/SingleParam/SCROA/"+function+"/ParameterAnalysis_"+getConfigByName(i)+".csv");

            stepCounter = 1;

            System.out.println("Testing "+i+"/15");

            ArrayList<CROAParamAnalysis> CROAs = new ArrayList<>();
            ArrayList<SCROAParamAnalysis> SCROAs = new ArrayList<>();


            //Generate Croas and SCROAS
            for (;stepCounter<=stepsEachVarMinusOne;stepCounter++){

                ConfigurationAlgorithm config = getConfig(i);


                IEquation equation = getiEquation(function, config);

                CROAParamAnalysis croaParamAnalysis = new CROAParamAnalysis(equation,1);
                SCROAParamAnalysis scroaParamAnalysis = new SCROAParamAnalysis(equation,2);

                CROAs.add(croaParamAnalysis);
                SCROAs.add(scroaParamAnalysis);
            }

            ExecutorService executorService = Executors.newFixedThreadPool(CROAs.size()+SCROAs.size());

            for (int n =0; n <CROAs.size();n++){

                executorService.execute(CROAs.get(n));

            }

            for (int k = 0; k <SCROAs.size(); k++) {

                executorService.execute(SCROAs.get(k));
            }
            executorService.shutdown();
            // Wait until all threads are finish
            while (!executorService.isTerminated()) {


            }

            for (int n =0; n <CROAs.size();n++){

                String point = (new Point3d(CROAs.get(n).currentBestSolution.getBestSolutionPoint().x,CROAs.get(n).currentBestSolution.getBestSolutionPoint().y,CROAs.get(n).currentBestSolution.getBestPE()).toStringNotRounded());

                try {

                    csvWriterParamAnalysisCROA.addRecord(function,"CROA", point, "" + CROAs.get(n).currentBestSolution.getBestPE(), CROAs.get(n).equation.getConfiguration());

                }catch (Exception exp) {
                    System.out.println("Failed logging record");
                }
            }

            try {

                csvWriterParamAnalysisCROA.flush();
            }catch (Exception exp) {
                System.out.println("flushing logging record");
            }


            for (int k =0; k <SCROAs.size();k++){

                String point = (new Point3d(SCROAs.get(k).currentBestSolution.getBestSolutionPoint().x,SCROAs.get(k).currentBestSolution.getBestSolutionPoint().y,SCROAs.get(k).currentBestSolution.getBestPE()).toStringNotRounded());
                try {
                    csvWriterParamAnalysisSCROA.addRecord(function,"SCROA", point, "" + SCROAs.get(k).currentBestSolution.getBestPE(), SCROAs.get(k).equation.getConfiguration());
                }catch (Exception exp) {
                    System.out.println("Failed logging record");
                }

            }

            try {

                csvWriterParamAnalysisSCROA.flush();
            }catch (Exception exp) {
                System.out.println("flushing logging record");
            }

        }

    }


    private String getConfigByName(int n){

        switch (n){
            case 1: return "popSize";
            case 2: return "c1";
            case 3: return "c2";
            case 4: return "w";
            case 5: return "impactOfOtherMolecule";
            case 6: return "initalBuffer";
            case 7: return "initalKE";
            case 8: return "minVelocityStep";
            case 9: return "maxLengthVelocityPerDimInitial";
            case 10: return "maxVelocity";
            case 11: return "minKELossRate";
            case 12: return "moleColl";
            case 13: return "numberOfHitsForDecomp";
            case 14: return "moveAlongGradeMaxStep";
            case 15: return "minimumKE";
            default: return "default";
        }

    }


    private ConfigurationAlgorithm getConfig(int n){
        switch (n){
            case 1: return getConfigPopSize();
            case 2: return getConfigc1();
            case 3: return getConfigc2();
            case 4: return getConfigw();
            case 5: return getConfigimpactOfOtherMolecule();
            case 6: return getConfiginitialBuffer();
            case 7: return getConfiginitialKE();
            case 8: return getConfigminVelocityStep();
            case 9: return getConfiginitialMaxLengthVelocityPerDim();
            case 10: return getConfigmaxVelocity();
            case 11: return getConfigkeMinLossRate();
            case 12: return getConfigmoleColl();
            case 13: return getConfignumberOfHitsForDecomposition();
            case 14: return getConfigmoveAlongGradeMaxStep();
            case 15: return getConfigminimumKe();
            default: return getdefaultConfiguration();
        }
    }

    private ConfigurationAlgorithm getConfig(int n, ConfigurationAlgorithm configurationAlgorithm, double factor){
        switch (n){

            //All: 1,5,11,12,13,14,15,6,7
            //CROA Required : Prio1 __> (1),5,11,12,13,15
            //                Prio2 __> 14
            //                Prio3 __> 6,7

            //1,2,3,4,5,6,7,8,9,10,11,12,13,14,15
            //SCROA Required : Prio1 __> 1,2,3,4,5,11,12,13,15
            //                 Prio2 __> 15
            //                 Prio3 __> 6,8,9,10

            case 1: return getConfigPopSize(configurationAlgorithm,factor); //CROA & SCROA ___>1st option (set )
            case 2: return getConfigc1(configurationAlgorithm,factor);//SCROA ___> 1st option
            case 3: return getConfigc2(configurationAlgorithm,factor);//SCROA ___> 1st option
            case 4: return getConfigw(configurationAlgorithm,factor);//SCROA ___> 1st option
            case 5: return getConfigimpactOfOtherMolecule(configurationAlgorithm,factor);//CROA & SCROA __> 1st option
            case 6: return getConfiginitialBuffer(configurationAlgorithm,factor);//CROA & SCROA __> 3nd Option
            case 7: return getConfiginitialKE(configurationAlgorithm,factor);//CROA & SCROA ___> 3nd option
            case 8: return getConfigminVelocityStep(configurationAlgorithm,factor);//SCROA __> 3rd option
            case 9: return getConfiginitialMaxLengthVelocityPerDim(configurationAlgorithm,factor);//SCROA __> 3rd option
            case 10: return getConfigmaxVelocity(configurationAlgorithm,factor);//SCROA ___> 3rd option
            case 11: return getConfigkeMinLossRate(configurationAlgorithm,factor);//CROA & SCROA ___> 1nd option
            case 12: return getConfigmoleColl(configurationAlgorithm,factor);//CROA & SCROA ___> 1st option
            case 13: return getConfignumberOfHitsForDecomposition(configurationAlgorithm,factor);//CROA & SCROA __> 1st option
            case 14: return getConfigmoveAlongGradeMaxStep(configurationAlgorithm,factor);//CROA & SCROA __> 2nd option
            case 15: return getConfigminimumKe(configurationAlgorithm,factor);//CROA & SCROA 2nd option
            default: return getdefaultConfiguration();
        }
    }


    //####Configurations#########
    private ConfigurationAlgorithm getdefaultConfiguration(){

        ConfigurationAlgorithm configurationAlgorithm = new ConfigurationAlgorithm(
                c1, //c1
                c2, //c2
                w,//w
                maxVelocity,    //maxVelocity
                initialMaxLengthVelocityPerDim,     //initialMaxLengthVelocityPerDim
                minVelocityStep,   //minVelocityStepInPerCent
                trysOfPSOUpdate,    //trysOfPSOUpdate
                distanceToBoundrys,  //distaneToBoundrys
                keMinLossRate,   //keMinLossRate
                moleColl,   //moleColl %
                initialKE,  //initialKE
                minimumKe,   //minimumKE
                initialBuffer,   //initialBuffer
                numberOfHitsForDecomposition,    //numberOfHitsForDecomposition
                moveAlongGradeMaxStep, //MoveAlonGradeMaxStep);
                impactOfOtherMolecule,
                PopSize); //

        return configurationAlgorithm;

    }



    //overgiven with 1 Modified
    public ConfigurationAlgorithm getConfigPopSize(ConfigurationAlgorithm configurationAlgorithm, double factor){
        int popSize =PopSizeMin+(int)((-PopSizeMin+PopSizeMax)*(factor));
        configurationAlgorithm.PopSize = popSize;
        return configurationAlgorithm;
    }

    public ConfigurationAlgorithm getConfigc1(ConfigurationAlgorithm configurationAlgorithm, double factor){
        double c1 =c1Min+((-c1Min+c1Max)*(factor));
        configurationAlgorithm.c1 = c1;
        return configurationAlgorithm;
    }

    public ConfigurationAlgorithm getConfigc2(ConfigurationAlgorithm configurationAlgorithm, double factor){
        double c2 =c2Min+((-c2Min+c2Max)*(factor));
        configurationAlgorithm.c2 = c2;
        return configurationAlgorithm;
    }

    public ConfigurationAlgorithm getConfigw(ConfigurationAlgorithm configurationAlgorithm, double factor){
        double w =wMin+((-wMin+wMax)*(factor));
        configurationAlgorithm.w = w;
        return configurationAlgorithm;
    }

    public ConfigurationAlgorithm getConfigmaxVelocity(ConfigurationAlgorithm configurationAlgorithm, double factor){
        double maxVelocity =maxVelocityMin+((-maxVelocityMin+maxVelocityMax)*(factor));
        configurationAlgorithm.maxVelocity = maxVelocity;
        return configurationAlgorithm;
    }

    public ConfigurationAlgorithm getConfiginitialMaxLengthVelocityPerDim(ConfigurationAlgorithm configurationAlgorithm, double factor){
        double initialMaxLengthVelocityPerDim =initialMaxLengthVelocityPerDimMin+((-initialMaxLengthVelocityPerDimMin+initialMaxLengthVelocityPerDimMax)*(factor));
        configurationAlgorithm.initialMaxLengthVelocityPerDim = initialMaxLengthVelocityPerDim;
        return configurationAlgorithm;
    }

    public ConfigurationAlgorithm getConfigminVelocityStep(ConfigurationAlgorithm configurationAlgorithm, double factor){
        double minVelocityStep=minVelocityStepMin+((-minVelocityStepMin+minVelocityStepMax)*(factor));
        configurationAlgorithm.minVelocityStep= minVelocityStep;
        return configurationAlgorithm;
    }

    public ConfigurationAlgorithm getConfigkeMinLossRate(ConfigurationAlgorithm configurationAlgorithm, double factor){
        double keMinLossRate=keMinLossRateMin+((-keMinLossRateMin+keMinLossRateMax)*(factor));
        configurationAlgorithm.keMinLossRate = keMinLossRate;
        return configurationAlgorithm;
    }

    public ConfigurationAlgorithm getConfigmoleColl(ConfigurationAlgorithm configurationAlgorithm, double factor){
        double moleColl=moleCollMin+((-moleCollMin+moleCollMax)*(factor));
        configurationAlgorithm.moleColl= moleColl;
        return configurationAlgorithm;
    }

    public ConfigurationAlgorithm getConfiginitialKE(ConfigurationAlgorithm configurationAlgorithm, double factor){
        double initialKE=initialKEMin+((-initialKEMin+initialKEMax)*(factor));
        configurationAlgorithm.initialKE= initialKE;
        return configurationAlgorithm;
    }

    public ConfigurationAlgorithm getConfigminimumKe(ConfigurationAlgorithm configurationAlgorithm, double factor){
        double minimumKe=minimumKeMin+((-minimumKeMin+minimumKeMax)*(factor));
        configurationAlgorithm.minimumKe= minimumKe;
        return configurationAlgorithm;
    }

    public ConfigurationAlgorithm getConfiginitialBuffer(ConfigurationAlgorithm configurationAlgorithm, double factor){
        double initialBuffer=initialBufferMin+((-initialBufferMin+initialBufferMax)*(factor));
        configurationAlgorithm.initialBuffer= initialBuffer;
        return configurationAlgorithm;
    }

    public ConfigurationAlgorithm getConfignumberOfHitsForDecomposition(ConfigurationAlgorithm configurationAlgorithm, double factor){
        int numberOfHitsForDecomposition=numberOfHitsForDecompositionMin+(int)((-numberOfHitsForDecompositionMin+getNumberOfHitsForDecompositionMax)*(factor));
        configurationAlgorithm.numberOfHitsForDecomposition= numberOfHitsForDecomposition;
        return configurationAlgorithm;
    }


    public ConfigurationAlgorithm getConfigmoveAlongGradeMaxStep(ConfigurationAlgorithm configurationAlgorithm, double factor){
        double moveAlongGradeMaxStep=moveAlongGradeMaxStepMin+((-moveAlongGradeMaxStepMin+moveAlongGradeMaxStepMax)*(factor));
        configurationAlgorithm.moveAlongGradeMaxStep= moveAlongGradeMaxStep;
        return configurationAlgorithm;
    }

    public ConfigurationAlgorithm getConfigimpactOfOtherMolecule(ConfigurationAlgorithm configurationAlgorithm, double factor){
        double impactOfOtherMolecule=impactOfOtherMoleculeMin+((-impactOfOtherMoleculeMin+impactOfOtherMoleculeMax)*(factor));
        configurationAlgorithm.impactOfOtherMolecule= impactOfOtherMolecule;
        return configurationAlgorithm;
    }

    //default with 1 modifed
    public ConfigurationAlgorithm getConfigPopSize(){
        ConfigurationAlgorithm configurationAlgorithm = getdefaultConfiguration();
        int popSize =PopSizeMin+(int)((-PopSizeMin+PopSizeMax)*(((double)stepCounter)/((double)stepsEachVarMinusOne)));
        configurationAlgorithm.PopSize = popSize;
        return configurationAlgorithm;
    }

    public ConfigurationAlgorithm getConfigc1(){
        ConfigurationAlgorithm configurationAlgorithm = getdefaultConfiguration();
        double c1 =c1Min+((-c1Min+c1Max)*(((double)stepCounter)/((double)stepsEachVarMinusOne)));
        configurationAlgorithm.c1 = c1;
        return configurationAlgorithm;
    }

    public ConfigurationAlgorithm getConfigc2(){
        ConfigurationAlgorithm configurationAlgorithm = getdefaultConfiguration();
        double c2 =c2Min+((-c2Min+c2Max)*(((double)stepCounter)/((double)stepsEachVarMinusOne)));
        configurationAlgorithm.c2 = c2;
        return configurationAlgorithm;
    }

    public ConfigurationAlgorithm getConfigw(){
        ConfigurationAlgorithm configurationAlgorithm = getdefaultConfiguration();
        double w =wMin+((-wMin+wMax)*(((double)stepCounter)/((double)stepsEachVarMinusOne)));
        configurationAlgorithm.w = w;
        return configurationAlgorithm;
    }

    public ConfigurationAlgorithm getConfigmaxVelocity(){
        ConfigurationAlgorithm configurationAlgorithm = getdefaultConfiguration();
        double maxVelocity =maxVelocityMin+((-maxVelocityMin+maxVelocityMax)*(((double)stepCounter)/((double)stepsEachVarMinusOne)));
        configurationAlgorithm.maxVelocity = maxVelocity;
        return configurationAlgorithm;
    }

    public ConfigurationAlgorithm getConfiginitialMaxLengthVelocityPerDim(){
        ConfigurationAlgorithm configurationAlgorithm = getdefaultConfiguration();
        double initialMaxLengthVelocityPerDim =initialMaxLengthVelocityPerDimMin+((-initialMaxLengthVelocityPerDimMin+initialMaxLengthVelocityPerDimMax)*(((double)stepCounter)/((double)stepsEachVarMinusOne)));
        configurationAlgorithm.initialMaxLengthVelocityPerDim = initialMaxLengthVelocityPerDim;
        return configurationAlgorithm;
    }

    public ConfigurationAlgorithm getConfigminVelocityStep(){
        ConfigurationAlgorithm configurationAlgorithm = getdefaultConfiguration();
        double minVelocityStep=minVelocityStepMin+((-minVelocityStepMin+minVelocityStepMax)*(((double)stepCounter)/((double)stepsEachVarMinusOne)));
        configurationAlgorithm.minVelocityStep= minVelocityStep;
        return configurationAlgorithm;
    }

    public ConfigurationAlgorithm getConfigkeMinLossRate(){
        ConfigurationAlgorithm configurationAlgorithm = getdefaultConfiguration();
        double keMinLossRate=keMinLossRateMin+((-keMinLossRateMin+keMinLossRateMax)*(((double)stepCounter)/((double)stepsEachVarMinusOne)));
        configurationAlgorithm.keMinLossRate = keMinLossRate;
        return configurationAlgorithm;
    }

    public ConfigurationAlgorithm getConfigmoleColl(){
        ConfigurationAlgorithm configurationAlgorithm = getdefaultConfiguration();
        double moleColl=moleCollMin+((-moleCollMin+moleCollMax)*(((double)stepCounter)/((double)stepsEachVarMinusOne)));
        configurationAlgorithm.moleColl= moleColl;
        return configurationAlgorithm;
    }

    public ConfigurationAlgorithm getConfiginitialKE(){
        ConfigurationAlgorithm configurationAlgorithm = getdefaultConfiguration();
        double initialKE=initialKEMin+((-initialKEMin+initialKEMax)*(((double)stepCounter)/((double)stepsEachVarMinusOne)));
        configurationAlgorithm.initialKE= initialKE;
        return configurationAlgorithm;
    }

    public ConfigurationAlgorithm getConfigminimumKe(){
        ConfigurationAlgorithm configurationAlgorithm = getdefaultConfiguration();
        double minimumKe=minimumKeMin+((-minimumKeMin+minimumKeMax)*(((double)stepCounter)/((double)stepsEachVarMinusOne)));
        configurationAlgorithm.minimumKe= minimumKe;
        return configurationAlgorithm;
    }

    public ConfigurationAlgorithm getConfiginitialBuffer(){
        ConfigurationAlgorithm configurationAlgorithm = getdefaultConfiguration();
        double initialBuffer=initialBufferMin+((-initialBufferMin+initialBufferMax)*(((double)stepCounter)/((double)stepsEachVarMinusOne)));
        configurationAlgorithm.initialBuffer= initialBuffer;
        return configurationAlgorithm;
    }

    public ConfigurationAlgorithm getConfignumberOfHitsForDecomposition(){
        ConfigurationAlgorithm configurationAlgorithm = getdefaultConfiguration();
        int numberOfHitsForDecomposition=numberOfHitsForDecompositionMin+(int)((double)(-numberOfHitsForDecompositionMin+getNumberOfHitsForDecompositionMax)*(((double)stepCounter)/((double)stepsEachVarMinusOne)));
        configurationAlgorithm.numberOfHitsForDecomposition= numberOfHitsForDecomposition;
        return configurationAlgorithm;
    }


    public ConfigurationAlgorithm getConfigmoveAlongGradeMaxStep(){
        ConfigurationAlgorithm configurationAlgorithm = getdefaultConfiguration();
        double moveAlongGradeMaxStep=moveAlongGradeMaxStepMin+((-moveAlongGradeMaxStepMin+moveAlongGradeMaxStepMax)*(((double)stepCounter)/((double)stepsEachVarMinusOne)));
        configurationAlgorithm.moveAlongGradeMaxStep= moveAlongGradeMaxStep;
        return configurationAlgorithm;
    }

    public ConfigurationAlgorithm getConfigimpactOfOtherMolecule(){
        ConfigurationAlgorithm configurationAlgorithm = getdefaultConfiguration();
        double impactOfOtherMolecule=impactOfOtherMoleculeMin+((-impactOfOtherMoleculeMin+impactOfOtherMoleculeMax)*(((double)stepCounter)/((double)stepsEachVarMinusOne)));
        configurationAlgorithm.impactOfOtherMolecule= impactOfOtherMolecule;
        return configurationAlgorithm;
    }


}
