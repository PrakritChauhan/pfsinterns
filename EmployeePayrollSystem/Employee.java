public class Employee {
    private int id;
    private String name;
    private String position;
    private double baseSalary;
    private double hourlyRate;

    public Employee(int id, String name, String position, double baseSalary, double hourlyRate) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.baseSalary = baseSalary;
        this.hourlyRate = hourlyRate;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }
}
