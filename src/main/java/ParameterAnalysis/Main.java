package ParameterAnalysis;

public class Main {

    public static void main(String[] args){
       // analysisParamCROADefault();
        // analysisParamSCROADefault();
        ParameterAnalysis parameterAnalysis = new ParameterAnalysis();
        String[] functions = {"Ackley"};
        int times = 1;
        for (String function : functions) {
            System.out.println("SCROA Default "+function+" start");
            //Single Param Analysis
            for (int i = 0; i < times; i++) {
                parameterAnalysis.SingleParameterAnalyticsSpecificParam(function, 16, 14500, "CROA");
            }
            System.out.println("SCROA Default " +function+" done");
        }
    }

    public static void MultipleParamImportant(){


        ParameterAnalysis parameterAnalysis = new ParameterAnalysis();

        String[] functions = {"Rastirgin","Ackley","Rosenbrock"};
        int[] importantForCROA = {5,11,12,13};
        int[] importantForSCROA = {2,3,4,5,11,12,13};

        int times = 1;



        //############## SCROA ##################
        //3*7*5*15.000 Iterations  //== 1.575.000 / 5Iterations
        for (String function : functions) {
            //Multiple Param analysis
            ParameterAnalysis.stepsEachVar = 8; //==2.100.000 Iterations *3
            parameterAnalysis.EveryImportantParameterAnalyticsSCROA(function);
            System.out.println("SCROA"+function+" done");
        }
        //################## SCROA done ############



        //############## CROA ##################
        //3*4*5*15.000 Iterations //== 1.575.000 /5 Iterations
        for (String function : functions) {
            System.out.println(function+" start");
            //Multiple Param analysis
            ParameterAnalysis.stepsEachVar = 25; //==390625 Iterations *3
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
        ParameterAnalysis.stepsEachVar= 357;
        //3*4*5*15.000 Iterations //== 1.575.000 /5 Iterations
        for (String function : functions) {
            System.out.println(function+" start");
            //Single Param Analysis
            for (int n = 0; n < importantForCROA.length; n++) {
                for (int i = 0; i < times; i++) {
                    ParameterAnalysis.stepsEachVar++;
                    parameterAnalysis.SingleParameterAnalyticsSpecificParam(function, importantForCROA[n], 15000, "CROA");
                }
            }
            System.out.println("CROA " +function+" done");
        }
        //################## CROA done ############


        //############## SCROA ##################
        ParameterAnalysis.stepsEachVar= 357;
        //3*7*5*15.000 Iterations  //== 1.575.000 / 5Iterations
        for (String function : functions) {
            //Single Param Analysis
            for (int n = 0; n < importantForSCROA.length; n++) {
                for (int i = 0; i < times; i++) {
                    ParameterAnalysis.stepsEachVar++;
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
                ParameterAnalysis.stepsEachVar= 500;
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
                ParameterAnalysis.stepsEachVar= 500;
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
