public class Student {
    private String name;
    private int rollNo;
    private String grade;

    public Student(String name, int rollNo, String grade) {
        this.name = name;
        this.rollNo = rollNo;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getRollNo() {
        return rollNo;
    }

    public String getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return rollNo + ";" + name + ";" + grade;
    }

    // Method to create a Student object from a line in the file
    public static Student fromString(String line) {
        String[] parts = line.split(";");
        int rollNo = Integer.parseInt(parts[0]);
        String name = parts[1];
        String grade = parts[2];
        return new Student(name, rollNo, grade);
    }
}
