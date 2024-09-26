import java.util.*;
public class Main {
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Integer> rollNos = new ArrayList<>();

    public static void main(String[] args) {
        students = FileHandler.loadStudents();  // Load existing records from file

        int choice;
        do {
            showMenu();
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    FileHandler.saveStudents(students);  // Save records before exiting
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 5);
    }

    private static void showMenu() {
        System.out.println("\n---- Student Record Management ----");
        System.out.println("1. Add Student");
        System.out.println("2. View Students");
        System.out.println("3. Update Student");
        System.out.println("4. Delete Student");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addStudent() {
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Roll Number: ");

        int rollNo = scanner.nextInt();
        while(rollNos.contains(rollNo)){
            System.out.print("Roll Number Taken, Enter New Roll Number: ");
            rollNo = scanner.nextInt();
        }
        rollNos.add(rollNo);
        scanner.nextLine();  // Consume newline
        System.out.print("Enter Grade: ");
        String grade = scanner.nextLine();

        students.add(new Student(name, rollNo, grade));
        System.out.println("Student added successfully!");
    }

    private static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records found.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    private static void updateStudent() {
        System.out.print("Enter Roll Number of student to update: ");
        int rollNo = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Student student = findStudentByRollNo(rollNo);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter new name (leave blank to keep unchanged): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            student.setName(name);
        }

        System.out.print("Enter new grade (leave blank to keep unchanged): ");
        String grade = scanner.nextLine();
        if (!grade.isEmpty()) {
            student.setGrade(grade);
        }

        System.out.println("Student updated successfully!");
    }

    private static void deleteStudent() {
        System.out.print("Enter Roll Number of student to delete: ");
        int rollNo = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Student student = findStudentByRollNo(rollNo);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        students.remove(student);
        System.out.println("Student deleted successfully!");
    }

    private static Student findStudentByRollNo(int rollNo) {
        for (Student student : students) {
            if (student.getRollNo() == rollNo) {
                return student;
            }
        }
        return null;
    }
}
