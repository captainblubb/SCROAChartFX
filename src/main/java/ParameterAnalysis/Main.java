package ParameterAnalysis;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args){

        ExecutorService executorService = Executors.newFixedThreadPool(6);
        ArrayList<Executer> executers = new ArrayList();

        for (int i = 0; i<6;i++){
            executers.add(new Executer(i+1));
        }

        for (int r = 0; r < executers.size(); r++) {
            executorService.execute(executers.get(r));
        }

        executorService.shutdown();
        // Wait until all threads are finish
        while (!executorService.isTerminated()) {
            try {
                Thread.sleep(2000);
            }catch (Exception exp){

            }

        }
 /*
        ParameterAnalysis parameterAnalysis;
        ParameterAnalysis.stepsEachVarMinusOne = 7;
        ParameterAnalysis.RunsEachConfigMultiple = 1;

        for (int i = 0;i!=3;i++) {
            parameterAnalysis = new ParameterAnalysis();
            parameterAnalysis.EveryImportantParameterAnalyticsCROA("Ackley");

            parameterAnalysis = new ParameterAnalysis();
            parameterAnalysis.EveryImportantParameterAnalyticsCROA("Rosenbrock");

            parameterAnalysis = new ParameterAnalysis();
            parameterAnalysis.EveryImportantParameterAnalyticsCROA("Rastirgin");
        }

        ParameterAnalysis.stepsEachVarMinusOne = 5;
        ParameterAnalysis.RunsEachConfigMultiple =1;
        for (int i = 0;i!=3;i++) {

            parameterAnalysis = new ParameterAnalysis();
            parameterAnalysis.EveryImportantParameterAnalyticsSCROA("Ackley");

            parameterAnalysis = new ParameterAnalysis();
            parameterAnalysis.EveryImportantParameterAnalyticsSCROA("Rosenbrock");

            parameterAnalysis = new ParameterAnalysis();
            parameterAnalysis.EveryImportantParameterAnalyticsSCROA("Rastirgin");
        }
      */
    }

    public static void MultipleParamImportant(){


        ParameterAnalysis parameterAnalysis = new ParameterAnalysis();

        String[] functions = {"Rastirgin","Ackley","Rosenbrock"};
        int[] importantForCROA = {5,11,12,13,15};
        int[] importantForSCROA = {2,3,4,5,11,12,13,15};

        int times = 1;



        //############## SCROA ##################
        //3*7*5*15.000 Iterations  //== 1.575.000 / 5Iterations
        for (String function : functions) {
            //Multiple Param analysis
            parameterAnalysis.stepsEachVarMinusOne = 8; //==2.100.000 Iterations *3
            parameterAnalysis.EveryImportantParameterAnalyticsSCROA(function);
            System.out.println("SCROA"+function+" done");
        }
        //################## SCROA done ############



        //############## CROA ##################
        //3*4*5*15.000 Iterations //== 1.575.000 /5 Iterations
        for (String function : functions) {
            System.out.println(function+" start");
            //Multiple Param analysis
            parameterAnalysis.stepsEachVarMinusOne = 25; //==390625 Iterations *3
            parameterAnalysis.EveryImportantParameterAnalyticsCROA(function);
            System.out.println("CROA " +function+" done");
        }
        //################## CROA done ############


    }

    public static void analysisAllImportantParamPrio1Single(){

        ParameterAnalysis parameterAnalysis = new ParameterAnalysis();

        String[] functions = {"Rastirgin","Ackley","Rosenbrock"};
        int[] importantForCROA = {5,11,12,13};
        int[] importantForSCROA = {2,3,4,5,11,12,13};

        //############## CROA ##################
        int times = 1;
        parameterAnalysis.stepsEachVarMinusOne= 357;
        //3*4*5*15.000 Iterations //== 1.575.000 /5 Iterations
        for (String function : functions) {
            System.out.println(function+" start");
            //Single Param Analysis
            for (int n = 0; n < importantForCROA.length; n++) {
                for (int i = 0; i < times; i++) {
                    parameterAnalysis.stepsEachVarMinusOne++;
                    parameterAnalysis.SingleParameterAnalyticsSpecificParam(function, importantForCROA[n], 15000, "CROA");
                }
            }
            System.out.println("CROA " +function+" done");
        }
        //################## CROA done ############


        //############## SCROA ##################
        parameterAnalysis.stepsEachVarMinusOne= 357;
        //3*7*5*15.000 Iterations  //== 1.575.000 / 5Iterations
        for (String function : functions) {
            //Single Param Analysis
            for (int n = 0; n < importantForSCROA.length; n++) {
                for (int i = 0; i < times; i++) {
                    parameterAnalysis.stepsEachVarMinusOne++;
                    parameterAnalysis.SingleParameterAnalyticsSpecificParam(function, importantForSCROA[n], 15000, "SCROA");
                }
            }
        }
        //################## SCROA done ############
        //Total Iterations = ca. 10 Mio.
    }

    public static void analysisParamsCROASingle(){
        ParameterAnalysis parameterAnalysis = new ParameterAnalysis();
        String[] functions = {"Rastirgin","Ackley","Rosenbrock"};
        int[] importantForCROA = {1,5,11,12,13,14,15,6,7};
        int times = 1;
        for (String function : functions) {
            System.out.println("CROA "+function+" start");
            //Single Param Analysis
            for (int n = 0; n < importantForCROA.length; n++) {
                System.out.println("CROA "+n+"/"+importantForCROA.length);
                parameterAnalysis.stepsEachVarMinusOne= 500;
                for (int i = 0; i < times; i++) {
                    parameterAnalysis.SingleParameterAnalyticsSpecificParam(function, importantForCROA[n], 14500, "CROA");
                }
            }
            System.out.println("CROA " +function+" done");
        }

    }

    public static void analysisParamSCROASingle(){
        ParameterAnalysis parameterAnalysis = new ParameterAnalysis();
        String[] functions = {"Rastirgin","Ackley","Rosenbrock"};
        int[] importantForSCROA = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        int times = 1;
        for (String function : functions) {
            System.out.println("SCROA "+function+" start");
            //Single Param Analysis
            for (int n = 0; n < importantForSCROA.length; n++) {
                System.out.println("SCROA "+n+"/"+importantForSCROA.length);
                parameterAnalysis.stepsEachVarMinusOne= 500;
                for (int i = 0; i < times; i++) {
                    parameterAnalysis.SingleParameterAnalyticsSpecificParam(function, importantForSCROA[n], 14500, "SCROA");
                }
            }
            System.out.println("SCROA " +function+" done");
        }

    }

    public static void analysisParamSCROADefault(){

        ParameterAnalysis parameterAnalysis = new ParameterAnalysis();
        String[] functions = {"Rastirgin","Ackley","Rosenbrock"};
        int times = 10;
        for (String function : functions) {
            System.out.println("SROA Default "+function+" start");
            //Single Param Analysis
            for (int i = 0; i < times; i++) {
                parameterAnalysis.SingleParameterAnalyticsSpecificParam(function, 16, 14500, "SCROA");
            }
            System.out.println("SCROA Default " +function+" done");
        }
    }

    public static void analysisParamCROADefault(){
        ParameterAnalysis parameterAnalysis = new ParameterAnalysis();
        String[] functions = {"Rastirgin","Ackley","Rosenbrock"};
        int times = 10;
        for (String function : functions) {
            System.out.println("CROA Default "+function+" start");
            //Single Param Analysis
                for (int i = 0; i < times; i++) {
                    parameterAnalysis.SingleParameterAnalyticsSpecificParam(function, 16, 14500, "CROA");
                }
            System.out.println("CROA Default " +function+" done");
        }
    }


}
