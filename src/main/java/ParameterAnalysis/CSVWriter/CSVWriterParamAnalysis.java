package ParameterAnalysis.CSVWriter;


import configuration.configuration.ConfigurationAlgorithm;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;

public class CSVWriterParamAnalysis {
    private  String filePath = "";
    CSVPrinter csvPrinter;

    public CSVWriterParamAnalysis(String path){
        this.filePath = path;
        try {

            boolean fileEmpty = false;
            File f = new File(path);
            if(f.exists() && !f.isDirectory()) {
                //Check if file is empty
                FileInputStream fis = new FileInputStream(new File(path));
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                String line = br.readLine();
                if (line != null) {
                    if (line.equals("")) {
                        fileEmpty = true;
                    }
                }else {
                    fileEmpty=true;
                }
                br.close();
                fis.close();
            }else {
                fileEmpty=true;
            }
            boolean csvNotInitialized = true;
            int counter = 0;
            while(csvNotInitialized && counter < 50) {

                try {

                if (fileEmpty) {
                    FileWriter writer = new FileWriter(path, false);
                    csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Function","AlgorithmnName", "lowestPoint", "minPE","c1","c2","w","maxVelocity","initialMaxLengthVelocityPerDim","minVelocityStep","trysOfPSOUpdate","distanceToBoundrys","keMinLossRate","moleColl","initialKE","minimumKe","initialBuffer","numberOfHitsForDecomposition","moveAlongGradeMaxStep","impactOfOtherMolecule","PopSize"));
                    csvNotInitialized=false;
                } else {
                    FileWriter writer = new FileWriter(path, true);
                    csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
                    csvNotInitialized= false;
                }
                }catch (Exception exp){
                    //... fileWriter didnt close file yet...
                    Thread.sleep(5);
                    counter++;
                }
            }
        }catch (Exception exp){
            System.out.println("Exception in CSVWriterParamAnalysis ");
            exp.printStackTrace();
        }

    }

    public synchronized void addRecord(String function ,String algorithmnName, String point, String minPE, ConfigurationAlgorithm conf) throws IOException {
        if(csvPrinter != null) {
            csvPrinter.printRecord(function,algorithmnName, point, minPE, conf.c1, conf.c2, conf.w, conf.maxVelocity, conf.initialMaxLengthVelocityPerDim, conf.minVelocityStep, conf.trysOfPSOUpdate, conf.distanceToBoundrys, conf.keMinLossRate, conf.moleColl, conf.initialKE, conf.minimumKe, conf.initialBuffer, conf.numberOfHitsForDecomposition, conf.moveAlongGradeMaxStep, conf.impactOfOtherMolecule,conf.PopSize);
        }
    }

    public void flush() throws Exception{
        csvPrinter.flush();
    }
}
