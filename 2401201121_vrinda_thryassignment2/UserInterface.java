import java.util.Scanner;

public class UserInterface {

    Scanner sc = new Scanner(System.in);
    Calculator calc = new Calculator();

    // ADDITION
    public void performAddition() {
        System.out.println("Choose Addition Type:");
        System.out.println("1. Add two integers");
        System.out.println("2. Add two doubles");
        System.out.println("3. Add three integers");
        System.out.print("Enter choice: ");
        int ch = sc.nextInt();

        switch (ch) {
            case 1:
                System.out.print("Enter first integer: ");
                int a1 = sc.nextInt();
                System.out.print("Enter second integer: ");
                int b1 = sc.nextInt();
                System.out.println("Result: " + calc.add(a1, b1));
                break;

            case 2:
                System.out.print("Enter first double: ");
                double d1 = sc.nextDouble();
                System.out.print("Enter second double: ");
                double d2 = sc.nextDouble();
                System.out.println("Result: " + calc.add(d1, d2));
                break;

            case 3:
                System.out.print("Enter three integers: ");
                int x = sc.nextInt();
                int y = sc.nextInt();
                int z = sc.nextInt();
                System.out.println("Result: " + calc.add(x, y, z));
                break;

            default:
                System.out.println("Invalid option!");
        }
    }

    // SUBTRACTION
    public void performSubtraction() {
        System.out.print("Enter first integer: ");
        int a = sc.nextInt();
        System.out.print("Enter second integer: ");
        int b = sc.nextInt();
        System.out.println("Result: " + calc.subtract(a, b));
    }

    // MULTIPLICATION
    public void performMultiplication() {
        System.out.print("Enter first double: ");
        double a = sc.nextDouble();
        System.out.print("Enter second double: ");
        double b = sc.nextDouble();
        System.out.println("Result: " + calc.multiply(a, b));
    }

    // DIVISION
    public void performDivision() {
        try {
            System.out.print("Enter numerator: ");
            int a = sc.nextInt();
            System.out.print("Enter denominator: ");
            int b = sc.nextInt();

            double result = calc.divide(a, b);
            System.out.println("Result: " + result);

        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }

    // MENU
    public void mainMenu() {
        int choice;

        do {
            System.out.println("\n--- Calculator Application ---");
            System.out.println("1. Add Numbers");
            System.out.println("2. Subtract Numbers");
            System.out.println("3. Multiply Numbers");
            System.out.println("4. Divide Numbers");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: performAddition(); break;
                case 2: performSubtraction(); break;
                case 3: performMultiplication(); break;
                case 4: performDivision(); break;
                case 5: System.out.println("Thank you for using Calculator!"); break;
                default: System.out.println("Invalid choice!");
            }

        } while (choice != 5);
    }

    // MAIN METHOD
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.mainMenu();
    }
}
