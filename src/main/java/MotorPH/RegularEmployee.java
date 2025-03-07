package MotorPH;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegularEmployee extends Employee implements DataReader {

    // Default constructor
    public RegularEmployee() {
        super("", "", "", "", "", "", "", "", "", "", "", "", "", 0, 0, 0, 0);
    }
    
    // For login
    public RegularEmployee(String employeeNo) {
        super(employeeNo);
    }

    public RegularEmployee(String employeeNo, String employeeFN, String employeeLN, String employeeAddress,
            String employeeDOB, String employeePhoneNumber, String employeeSSS,
            String employeePhilHealth, String employeeTIN, String employeePagIbig,
            String employeeStatus, String employeePosition, String employeeSupervisor,
            double rice, double phone, double cloth, double hrRate) {

        super(employeeNo, employeeFN, employeeLN, employeeAddress, employeeDOB, employeePhoneNumber,
                employeeSSS, employeePhilHealth, employeeTIN, employeePagIbig, employeeStatus,
                employeePosition, employeeSupervisor, rice, phone, cloth, hrRate);

    }

    @Override
    public void accessPermissions(HomePage homePage) {
        homePage.addButton(homePage.getViewPersonalDetailsBtn());
        homePage.addButton(homePage.getViewSalaryBtn());
        homePage.addButton(homePage.getRequestLeaveBtn());
    }

    @Override
    public void viewEmployeeDetails() {
        try {
            EmployeeData frame = new EmployeeData(this.getHomePage(), this.getEmployeeNo());
            frame.setVisible(true);
            frame.viewOwnInfo();
        } catch (CsvValidationException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RegularEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void processLeaveRequest() {
        try {
            ViewRequest frame = new ViewRequest(this.getHomePage());
            frame.setVisible(true);
            frame.leaveRequestor();
        } catch (IOException ex) {
            Logger.getLogger(RegularEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void viewSalary() {
        ViewSalary frame = new ViewSalary(this.getHomePage(), this.getEmployeeNo());
        frame.setVisible(true);
        frame.viewOwnSalary();
    }

    @Override
    public boolean readData(String empNo, String... params) throws IOException, CsvValidationException {
        boolean empFound = false;
        String filename = "Employee Data.csv";
        try (CSVReader reader = new CSVReader(new FileReader(filename))) {
            String[] headers = reader.readNext();
            String[] employeeData;
            while ((employeeData = reader.readNext()) != null) {
                if (employeeData[0].equals(empNo)) {
                    this.setEmployeeNo(employeeData[0]);
                    this.setEmployeeLN(employeeData[1]);
                    this.setEmployeeFN(employeeData[2]);
                    this.setEmployeeDOB(employeeData[3]);
                    this.setEmployeeAddress(employeeData[4]);
                    this.setEmployeePhoneNumber(employeeData[5]);
                    this.setEmployeeSSS(employeeData[6]);
                    this.setEmployeePhilHealth(employeeData[7]);
                    this.setEmployeeTIN(employeeData[8]);
                    this.setEmployeePagIbig(employeeData[9]);
                    this.setEmployeeStatus(employeeData[10]);
                    this.setEmployeePosition(employeeData[11]);
                    this.setEmployeeSupervisor(employeeData[12]);
                    this.setEmployeeRiceAllowance(Double.parseDouble(employeeData[14]));
                    this.setEmployeePhoneAllowance(Double.parseDouble(employeeData[15]));
                    this.setEmployeeClothAllowance(Double.parseDouble(employeeData[16]));
                    this.setHourlyRate(Double.parseDouble(employeeData[18]));
                    this.setBasicSalary(Double.parseDouble(employeeData[13]));
                    this.setGrossSemiMonthly(Double.parseDouble(employeeData[17]));

                    empFound = true;
                    break;
                }
            }
        }
        return empFound;
    }

}
