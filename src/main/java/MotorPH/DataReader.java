
package MotorPH;

import com.opencsv.exceptions.CsvValidationException;
import java.io.IOException;


public interface DataReader {
    
    boolean readData(String empNo, String... params) throws IOException, CsvValidationException;
    
}
