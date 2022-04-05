import java.util.*;
import java.io.*;

// Class that simply lets us read a TSV file and export it as a String[]
public class TSVReader {
 
    public static String[] readTSVFile(String fileName) throws IOException {

        BufferedReader tsvFile = new BufferedReader(new FileReader(fileName));
        ArrayList<String> readArr = new ArrayList<>();
        String currRow;

        while ((currRow = tsvFile.readLine()) != null) {
            readArr.add(currRow);
        }
        String[] result = new String[readArr.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = readArr.get(i);
        }
        tsvFile.close();
        return result;
    }


}