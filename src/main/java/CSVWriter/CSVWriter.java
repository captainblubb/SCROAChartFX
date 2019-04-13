package CSVWriter;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;

public class CSVWriter {
    private  String filePath = "";
    CSVPrinter csvPrinter;

    public CSVWriter(String path){
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
                    csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("AlgorithmnName", "algorithmnID", "lowestPoint", "minPE"));
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

    public void addRecord(String algorithmnName, String algorithmnID, String point, String minPE) throws IOException {

        if(csvPrinter != null) {
            csvPrinter.printRecord(algorithmnName, algorithmnID, point, minPE);
        }
    }

    public void flush() throws Exception{
        csvPrinter.flush();
    }
}
