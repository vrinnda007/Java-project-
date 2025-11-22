import java.io.*;
import java.util.*;

// ---------------- STUDENT CLASS ----------------
class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    private int rollNo;
    private String name;
    private String email;
    private String course;
    private double marks;

    public Student(int rollNo, String name, String email, String course, double marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.email = email;
        this.course = course;
        this.marks = marks;
    }

    public int getRollNo() { return rollNo; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getCourse() { return course; }
    public double getMarks() { return marks; }

    @Override
    public String toString() {
        char grade;
        if (marks >= 90) grade = 'A';
        else if (marks >= 75) grade = 'B';
        else if (marks >= 50) grade = 'C';
        else grade = 'D';

        return String.format(
                "RollNo: %d, Name: %s, Email: %s, Course: %s, Marks: %.2f, Grade: %c",
                rollNo, name, email, course, marks, grade
        );
    }

    public String toFileString() {
        return rollNo + "," + name + "," + email + "," + course + "," + marks;
    }

    public static Student fromFileString(String line) {
        String[] parts = line.split(",");
        return new Student(
                Integer.parseInt(parts[0]),
                parts[1],
                parts[2],
                parts[3],
                Double.parseDouble(parts[4])
        );
    }
}

// ---------------- STUDENT MANAGEMENT ----------------
public class StudentRecordSystem {

    private static final String FILE_NAME = "students.txt";
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    // ---------------- LOAD STUDENTS ----------------
    private static void loadStudents() {
        try {
            File file = new File(FILE_NAME);

            if (!file.exists()) {
                file.createNewFile();
                System.out.println("students.txt created as it did not exist.");
                return;
            }

            // Display file attributes
            System.out.println("File exists: " + file.exists());
            System.out.println("File name: " + file.getName());
            System.out.println("File path: " + file.getAbsolutePath());
            System.out.println("Readable: " + file.canRead());
            System.out.println("Writable: " + file.canWrite());
            System.out.println("File size: " + file.length() + " bytes");

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                students.add(Student.fromFileString(line));
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Error reading students file: " + e.getMessage());
        }
    }

    // ---------------- SAVE STUDENTS ----------------
    private static void saveStudents() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME));

            for (Student s : students) {
                bw.write(s.toFileString());
                bw.newLine();
            }

            bw.close();
            System.out.println("Student records saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }

    // ---------------- ADD STUDENT ----------------
    private static void addStudent() {
        try {
            System.out.print("Enter Roll No: ");
            int roll = Integer.parseInt(sc.nextLine());

            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            if (name.isEmpty()) throw new Exception("Name cannot be empty");

            System.out.print("Enter Email: ");
            String email = sc.nextLine();
            if (email.isEmpty()) throw new Exception("Email cannot be empty");

            System.out.print("Enter Course: ");
            String course = sc.nextLine();
            if (course.isEmpty()) throw new Exception("Course cannot be empty");

            System.out.print("Enter Marks: ");
            double marks = Double.parseDouble(sc.nextLine());
            if (marks < 0 || marks > 100)
                throw new Exception("Marks must be between 0 and 100");

            students.add(new Student(roll, name, email, course, marks));

            // Simulate loading
            Thread loader = new Thread(() -> {
                try {
                    System.out.println("Saving student data...");
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    System.out.println("Loading interrupted");
                }
            });

            loader.start();
            loader.join();

            System.out.println("Student added successfully!");

        } catch (NumberFormatException e) {
            System.out.println("Invalid numeric input.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ---------------- DISPLAY STUDENTS ----------------
    private static void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        students.sort(Comparator.comparingDouble(Student::getMarks).reversed());

        System.out.println("\n--- Student Records ---");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    // ---------------- RANDOM ACCESS DEMO ----------------
    private static void randomAccessDemo() {
        try {
            RandomAccessFile raf = new RandomAccessFile(FILE_NAME, "r");

            if (raf.length() == 0) {
                System.out.println("File is empty, cannot perform random access.");
                raf.close();
                return;
            }

            byte[] buffer = new byte[50];
            raf.read(buffer);

            System.out.println("First 50 bytes of file:\n" + new String(buffer));
            raf.close();

        } catch (IOException e) {
            System.out.println("RandomAccessFile error: " + e.getMessage());
        }
    }

    // ---------------- MAIN MENU ----------------
    public static void main(String[] args) {
        loadStudents();

        while (true) {
            System.out.println("\n==== Student Record Management ====");
            System.out.println("1. Add Student");
            System.out.println("2. Display Students (Sorted by Marks)");
            System.out.println("3. Random Access Demo");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> addStudent();
                case "2" -> displayStudents();
                case "3" -> randomAccessDemo();
                case "4" -> {
                    saveStudents();
                    System.out.println("Exiting... Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
