package configuration.configuration;

import CSVWriter.CSVWriter;

//Holding Parameters
public class globalConfig {

    //Configuration for Algorithm
   // public static ConfigurationAlgorithm configurationAlgorithmCROA = new ConfigurationAlgorithm(); //Default config.. Set when new Equation loaded
   // public static ConfigurationAlgorithm configurationAlgorithmSCROA = new ConfigurationAlgorithm(); //Default config.. Set when new Equation loaded

    public static int updateAfterIterations = 500;

   // public static int PopSize = 50; //Initial Population size
    public static int Iterations = 10000;

    //public static int initialStepsize = 1; //Steps between Moles @ Init
    public static final double distanceToBoundrys = 0.01; // Init distance to boundry // just gui apperance
    public static boolean loggin = false;

    //round after , in ToString in Point
    public static double positionsAfterKomma = 8;

    public static CSVWriter csvWriter = new CSVWriter("csvOut.csv");
}
