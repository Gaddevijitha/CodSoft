import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Class to represent a student
class Student {
    private String id;
    private String name;
    private int age;

    public Student(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age;
    }
}

// Class for Student Management System
class StudentManagementSystem {

    private static final String FILE_NAME = "students.txt";
    private List<Student> studentList = new ArrayList<>();

    public StudentManagementSystem() {
        loadStudentsFromFile();
    }

    // Method to add a new student
    public void addStudent(String id, String name, int age) {
        if (id.isEmpty() || name.isEmpty() || age <= 0) {
            System.out.println("Invalid input. Please provide valid student information.");
            return;
        }
        Student student = new Student(id, name, age);
        studentList.add(student);
        saveStudentsToFile();
        System.out.println("Student added successfully.");
    }

    // Method to edit student information
    public void editStudent(String id, String newName, int newAge) {
        Student student = findStudentById(id);
        if (student == null) {
            System.out.println("Student not found.");
        } else {
            if (!newName.isEmpty()) student.setName(newName);
            if (newAge > 0) student.setAge(newAge);
            saveStudentsToFile();
            System.out.println("Student information updated.");
        }
    }

    // Method to search for a student by ID
    public void searchStudent(String id) {
        Student student = findStudentById(id);
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student not found.");
        }
    }

    // Method to display all students
    public void displayAllStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No students to display.");
        } else {
            for (Student student : studentList) {
                System.out.println(student);
            }
        }
    }

    // Method to find a student by ID
    private Student findStudentById(String id) {
        for (Student student : studentList) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    // Method to load students from the file
    private void loadStudentsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String id = parts[0];
                    String name = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    studentList.add(new Student(id, name, age));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading students from file.");
        }
    }

    // Method to save students to the file
    private void saveStudentsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student student : studentList) {
                writer.write(student.getId() + "," + student.getName() + "," + student.getAge());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving students to file.");
        }
    }
}

// Main class to run the system
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagementSystem sms = new StudentManagementSystem();

        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add New Student");
            System.out.println("2. Edit Student Information");
            System.out.println("3. Search for a Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    System.out.print("Enter student ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter student age: ");
                    int age = scanner.nextInt();
                    sms.addStudent(id, name, age);
                    break;
                case 2:
                    System.out.print("Enter student ID to edit: ");
                    id = scanner.nextLine();
                    System.out.print("Enter new name (leave blank to keep current): ");
                    name = scanner.nextLine();
                    System.out.print("Enter new age (0 to keep current): ");
                    age = scanner.nextInt();
                    sms.editStudent(id, name, age);
                    break;
                case 3:
                    System.out.print("Enter student ID to search: ");
                    id = scanner.nextLine();
                    sms.searchStudent(id);
                    break;
                case 4:
                    sms.displayAllStudents();
                    break;
                case 5:
                    System.out.println("Exiting system. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
