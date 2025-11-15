import java.util.Scanner;

public class ResultManager {
    private Student[] students = new Student[50]; // Supports up to 50 students
    private int count = 0; // Current number of students
    private Scanner sc = new Scanner(System.in);

    // Add student
    public void addStudent() {
        try {
            System.out.print("Enter Roll Number: ");
            int roll = sc.nextInt();
            sc.nextLine(); // Consume newline
            System.out.print("Enter Student Name: ");
            String name = sc.nextLine();
            int[] marks = new int[3];
            for (int i = 0; i < 3; i++) {
                System.out.print("Enter marks for subject " + (i + 1) + ": ");
                marks[i] = sc.nextInt();
            }

            // Create Student object (validates marks)
            Student s = new Student(roll, name, marks);
            students[count++] = s;
            System.out.println("Student added successfully. Returning to main menu...");

        } catch (InvalidMarksException e) {
            System.out.println("Error: " + e.getMessage() + " Returning to main menu...");
        } catch (Exception e) {
            System.out.println("Invalid input! Returning to main menu...");
            sc.nextLine(); // Clear buffer
        }
    }

    // Show student details by roll number
    public void showStudentDetails() {
        try {
            System.out.print("Enter Roll Number to search: ");
            int roll = sc.nextInt();
            boolean found = false;
            for (int i = 0; i < count; i++) {
                if (students[i].getRollNumber() == roll) {
                    students[i].displayResult();
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Student not found!");
            }
        } catch (Exception e) {
            System.out.println("Invalid input!");
            sc.nextLine();
        }
    }

    // Main menu
    public void mainMenu() {
        int choice = 0;
        do {
            System.out.println("\n===== Student Result Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. Show Student Details");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            try {
                choice = sc.nextInt();
                switch (choice) {
                    case 1: addStudent(); break;
                    case 2: showStudentDetails(); break;
                    case 3: System.out.println("Exiting program. Thank you!"); break;
                    default: System.out.println("Invalid choice!"); break;
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine();
            }
        } while (choice != 3);

        // Finally block simulation: close scanner
        finallyBlock();
    }

    private void finallyBlock() {
        sc.close();
        System.out.println("Scanner closed. Program terminated.");
    }

    public static void main(String[] args) {
        ResultManager rm = new ResultManager();
        rm.mainMenu();
    }
}
