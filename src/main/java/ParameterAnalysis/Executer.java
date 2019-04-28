package ParameterAnalysis;

public class Executer implements Runnable {

    private int taskToDo;

    public Executer(int task){
        this.taskToDo = task;
    }

    @Override
    public void run() {

        ParameterAnalysis parameterAnalysis = new ParameterAnalysis();

        switch (taskToDo){

            case 1: parameterAnalysis.EveryImportantParameterAnalyticsCROAWithMedian("Ackley");
                break;
            case 2:parameterAnalysis.EveryImportantParameterAnalyticsCROAWithMedian("Rastirgin");
                break;
            case 3:parameterAnalysis.EveryImportantParameterAnalyticsCROAWithMedian("Rosenbrock");
                break;
            case 4:parameterAnalysis.EveryImportantParameterAnalyticsSCROAWithMedian("Ackley");
                break;
            case 5:parameterAnalysis.EveryImportantParameterAnalyticsSCROAWithMedian("Rastirgin");
                break;
            case 6:parameterAnalysis.EveryImportantParameterAnalyticsSCROAWithMedian("Rosenbrock");
                break;

        }



    }
}
