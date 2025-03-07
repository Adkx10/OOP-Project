package MotorPH;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;

public abstract class Employee implements DataReader {

    private String employeeNo;
    private String employeeFN;
    private String employeeLN;
    private String employeeAddress;
    private String employeeDOB;
    private String employeePhoneNumber;
    private String employeeSSS;
    private String employeePhilHealth;
    private String employeeTIN;
    private String employeePagIbig;
    private String employeeStatus;
    private String employeePosition;
    private String employeeSupervisor;
    private double rice;
    private double phone;
    private double cloth;
    private double hrRate;
    private double basicSalary;
    private double grossSemiMonthly;
    private HomePage homePage;
    DecimalFormat df = new DecimalFormat("#,##0.00");
    
    public Employee(String employeeNo){ //Constructor for Login
        this.employeeNo = employeeNo;
    }
    
    public Employee(String employeeNo, String employeeFN, String employeeLN, String employeeAddress,
        String employeeDOB, String employeePhoneNumber, String employeeSSS, String employeePhilHealth,
        String employeeTIN, String employeePagIbig, String employeeStatus, String employeePosition, 
        String employeeSupervisor, double rice, double phone, double cloth, double hrRate) {
        
        this.employeeNo = employeeNo;
        this.employeeFN = employeeFN;
        this.employeeLN = employeeLN;
        this.employeeAddress = employeeAddress;
        this.employeeDOB = employeeDOB;
        this.employeePhoneNumber = employeePhoneNumber;
        this.employeeSSS = employeeSSS;
        this.employeePhilHealth = employeePhilHealth;
        this.employeeTIN = employeeTIN;
        this.employeePagIbig = employeePagIbig;
        this.employeeStatus = employeeStatus;
        this.employeePosition = employeePosition;
        this.employeeSupervisor = employeeSupervisor;
        this.rice = rice;
        this.phone = phone;
        this.cloth = cloth;
        this.hrRate = hrRate;
    }

    //Getter Methods
    public String getEmployeeNo() {
        return employeeNo;
    }

    public String getEmployeeFN() {
        return employeeFN;
    }

    public String getEmployeeLN() {
        return employeeLN;
    }

    public String getEmployeeDOB() {
        return employeeDOB;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public String getEmployeePhoneNumber() {
        return employeePhoneNumber;
    }

    public String getEmployeeSSS() {
        return employeeSSS;
    }

    public String getEmployeePhilHealth() {
        return employeePhilHealth;
    }

    public String getEmployeeTIN() {
        return employeeTIN;
    }

    public String getEmployeePagIbig() {
        return employeePagIbig;
    }

    public String getEmployeeStatus() {
        return employeeStatus;
    }

    public String getEmployeePosition() {
        return employeePosition;
    }

    public String getEmployeeSupervisor() {
        return employeeSupervisor;
    }

    public double getRiceAllowance() {
        return rice;
    }

    public double getPhoneAllowance() {
        return phone;
    }

    public double getClothAllowance() {
        return cloth;
    }

    public double getHourlyRate() {
        return hrRate;
    }
    
    public HomePage getHomePage() {
        return homePage;
    }
    
    public double getBasicSalary() {
        return basicSalary;
    }
    
    public double getGrossSemiMonthly(){
        return grossSemiMonthly;
    }
   

    //Setter Methods
    public void setEmployeeNo(String newEmployeeNo) {
        this.employeeNo = newEmployeeNo;
    }

    public void setEmployeeFN(String newEmployeeFN) {
        this.employeeFN = newEmployeeFN;
    }

    public void setEmployeeLN(String newEmployeeLN) {
        this.employeeLN = newEmployeeLN;
    }

    public void setEmployeeAddress(String newEmployeeAddress) {
        this.employeeAddress = newEmployeeAddress;
    }

    public void setEmployeeDOB(String newEmployeeDOB) {
        this.employeeDOB = newEmployeeDOB;
    }
    
    public void setEmployeePhoneNumber(String newEmployeePhoneNumber) {
        this.employeePhoneNumber = newEmployeePhoneNumber;
    }
    
    public void setEmployeeSSS(String newEmployeeSSS) {
        this.employeeSSS = newEmployeeSSS;
    }
    
    public void setEmployeePhilHealth(String newEmployeePhilHealth) {
        this.employeePhilHealth = newEmployeePhilHealth;
    }
    
    public void setEmployeeTIN(String newEmployeeTIN) {
        this.employeeTIN = newEmployeeTIN;
    }
    
    public void setEmployeePagIbig(String newEmployeePagIbig) {
        this.employeePagIbig = newEmployeePagIbig;
    }
    
    public void setEmployeeStatus(String newEmployeeStatus) {
        this.employeeStatus = newEmployeeStatus;
    }
    
    public void setEmployeePosition(String newEmployeePosition) {
        this.employeePosition = newEmployeePosition;
    }
    
    public void setEmployeeSupervisor(String newEmployeeSupervisor) {
        this.employeeSupervisor = newEmployeeSupervisor;
    }
    
    public void setEmployeeRiceAllowance(double newEmployeeRiceAllowance) {
        this.rice = newEmployeeRiceAllowance;
    }
    
    public void setEmployeePhoneAllowance(double newEmployeePhoneAllowance) {
        this.phone = newEmployeePhoneAllowance;
    }
    
    public void setEmployeeClothAllowance(double newEmployeeClothAllowance) {
        this.cloth = newEmployeeClothAllowance;
    }
    
    public void setHourlyRate(double newHourlyRate) {
        this.hrRate = newHourlyRate;
    }
    
    public void setHomePage(HomePage newHomePage) {
        this.homePage = newHomePage;
    }
    
    public void setBasicSalary(double newBasicSalary){
        this.basicSalary = newBasicSalary;
    }
    
    public void setGrossSemiMonthly(double newGrossSemiMonthly){
        this.grossSemiMonthly = newGrossSemiMonthly;
    }
    
    // Factory Method: Creates an Employee object based on accessType
    public static Employee createEmployeeInstance(String empNo) throws IOException, CsvValidationException {
        String filename = "Credentials.csv";

        try (CSVReader reader = new CSVReader(new FileReader(filename))) {
            reader.readNext(); // Skip the header
            String[] employeeData;
            while ((employeeData = reader.readNext()) != null) {
                if (employeeData[0].equals(empNo)) {
                    return switch (employeeData[3]) {
                        case "Admin" -> new Admin(empNo);
                        case "Manager" -> new Manager(empNo);
                        case "Regular" -> new RegularEmployee(empNo);
                        default -> throw new IllegalArgumentException("Invalid access type: " + employeeData[3]);
                    };
                }
            }
        }
        return null;
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
    
    public abstract void accessPermissions(HomePage homePage);
    public abstract void viewEmployeeDetails();
    public abstract void processLeaveRequest();
    public abstract void viewSalary();
}
