package configuration.configuration;

public class ConfigurationAlgorithm {

    public ConfigurationAlgorithm(double c1, double c2, double w, double maxVelocity, double InitialMaxLengthVelocityPerDim,
                                  double minVelocityStep, int trysOfPSOUpdate, double distanceToBoundrys, double keMinLossRate,
                                  double MoleColl, double InitialKE, double minimumKe, double InitialBuffer, int numberOfHitsForDecomposition,
                                  double MoveAlongGradeMaxStep, double impactOfOtherMolecule, int PopSize){

        this.c1 = c1;
        this.c2=c2;
        this.w = w;
        this.maxVelocity = maxVelocity;
        this.initialMaxLengthVelocityPerDim = InitialMaxLengthVelocityPerDim;
        this.minVelocityStep = minVelocityStep;
        this.trysOfPSOUpdate = trysOfPSOUpdate;
        this.distanceToBoundrys = distanceToBoundrys;
        this.keMinLossRate = keMinLossRate;
        this.moleColl = MoleColl;
        this.initialKE = InitialKE;
        this.minimumKe = minimumKe;
        this.initialBuffer = InitialBuffer;
        this.numberOfHitsForDecomposition = numberOfHitsForDecomposition;
        this.moveAlongGradeMaxStep = MoveAlongGradeMaxStep;
        this.impactOfOtherMolecule = impactOfOtherMolecule;
        this.PopSize = PopSize;
    }


    //defaultconfiguration
    public ConfigurationAlgorithm(){

    }


    // Als Standard-Belegung der Variablen wird von Eberhart und Shi [ES00] c1 = c2 = 1.4962 und w = 0.72984 vorgeschlagen,
    public double c1 = 1.4962;
    public double c2 = 1.4962;
    public double w = 0.52984; //inertia weight w; How much % of old velocity is used to calc new
    public double maxVelocity = 10;
    public double initialMaxLengthVelocityPerDim = 3;
    public int PopSize = 50;

    //PSO UPdate
    public double minVelocityStep = 0.0; //Molecule makes step in direction of Velocity % element of [minVelocityStep,1]
    public int trysOfPSOUpdate = 1;

    //public static int initialStepsize = 1; //Steps between Moles @ Init
    public double distanceToBoundrys = 0.05; // Init distance to boundry //Gui appereance

    //High impact on CROAParamAnalysis Algorithm
    public double keMinLossRate = 0.1; //min Ke that is lost in onWallIneffective in %
    public double moleColl = 0.6; //Percentage of propability -> unimol or intermol reaction ::: moleColl Higher results in more intermol coll
    public double initialKE = 20.0; // Initial KE of each MoleculeCROA
    public double minimumKe = 1.0; //Minimum of KE -> if Ke is less -> synthesis
    public double initialBuffer = 200.0; // Initial Buffer Energy
    public int numberOfHitsForDecomposition = 30; //hits in molecule which leads to decomposition

    //NeighboursearchSingle
    public double moveAlongGradeMaxStep = 0.0001;

    //NeighbourghhoodsearchTwo
    public double impactOfOtherMolecule = 0.001; //Impact of Molecule to other Molecule in intermolecular coll

}
