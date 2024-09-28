public class Payroll {
    private double hoursWorked;
    private double deductions;
    private double taxRate;
    private double bonuses;

    public Payroll(double hoursWorked, double deductions, double taxRate, double bonuses) {
        this.hoursWorked = hoursWorked;
        this.deductions = deductions;
        this.taxRate = taxRate;
        this.bonuses = bonuses;
    }

    public double calculateNetSalary(Employee employee) {
        double grossSalary = employee.getBaseSalary() + (hoursWorked * employee.getHourlyRate()) + bonuses;
        double taxes = grossSalary * taxRate;
        return grossSalary - taxes - deductions;
    }
}
