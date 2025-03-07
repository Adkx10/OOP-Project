package MotorPH;

import java.text.DecimalFormat;

public class Deduct {

    private double _baseSalary;

    DecimalFormat df = new DecimalFormat("#,##0.00");

    public Deduct(double base) {
        _baseSalary = base;
    }

    public void Contributions() {
        //SSS CONTRIBUTION COMPUTATION   
        double sssContri = 0;
        if (_baseSalary == 0) {
            sssContri = 0;
        } else if (_baseSalary < 22500) {
            sssContri = 135;
        } else if (_baseSalary >= 22500 && _baseSalary <= 22750) {
            sssContri = 1012.50;
        } else if (_baseSalary >= 22750 && _baseSalary <= 23250) {
            sssContri = 1035.00;
        } else if (_baseSalary >= 23250 && _baseSalary <= 23750) {
            sssContri = 1057.50;
        } else if (_baseSalary >= 23750 && _baseSalary <= 24250) {
            sssContri = 1080.00;
        } else if (_baseSalary >= 24250 && _baseSalary <= 24750) {
            sssContri = 1102.50;
        } else if (_baseSalary >= 24750) {
            sssContri = 1125.00;
        }
        System.out.println("\n------------------ Deductions ------------------");
        System.out.println("SSS: " + df.format(sssContri));

        //PHILHEALTH CONTRIBUTION COMPUTATION
        double phHContri;
        if (_baseSalary < 60000) {
            phHContri = (_baseSalary * .03) / 2;
        } else {
            phHContri = 1800 / 2;
        }
        System.out.println("PhilHealth: " + df.format(phHContri));

        //PAGIBIG CONTRIBUTION COMPUTATION
        double pgIContri = 0;
        if (_baseSalary <= 1500) {
            pgIContri = (_baseSalary * .03) - (_baseSalary * .02);
        } else if (_baseSalary > 1500) {
            //DIVIDED INTO 2 AS HALF WILL BE PAID BY THE EMPLOYER
            pgIContri = Math.min((_baseSalary * 0.04) / 2, 200 / 2);
        }
        System.out.println("Pag Ibig: " + df.format(pgIContri));

        //WITHHOLDING TAX COMPUTATION
        double govContri = sssContri + phHContri + pgIContri;
        double taxableInc = _baseSalary - govContri;
        double withHoldingTax = 0;
        if (taxableInc <= 20832) {
            withHoldingTax = 0;
        } else if (taxableInc >= 20833 && taxableInc < 33333) {
            withHoldingTax = (taxableInc - 20833) * 0.20;
        } else if (taxableInc >= 33333 && taxableInc < 66667) {
            withHoldingTax = ((taxableInc - 33333) * 0.25) + 2500;
        } else if (taxableInc >= 66667 && taxableInc < 166667) {
            withHoldingTax = ((taxableInc - 66667) * 0.30) + 10833;
        } else if (taxableInc >= 166667 && taxableInc < 666667) {
            withHoldingTax = ((taxableInc - 166667) * 0.32) + 40833.33;
        } else if (taxableInc >= 666667) {
            withHoldingTax = ((taxableInc - 666667) * 0.35) + 200833.33;
        }
        System.out.println("Tax: " + df.format(withHoldingTax));

        double _netPay = taxableInc - withHoldingTax;
        System.out.println("\n------------------ Salary ------------------");
        System.out.println("Net Salary: " + df.format(_netPay));
    }
}
