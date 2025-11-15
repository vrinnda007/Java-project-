public class Calculator {

    // Overloaded add() methods
    public int add(int a, int b) {
        return a + b;
    }

    public double add(double a, double b) {
        return a + b;
    }

    public int add(int a, int b, int c) {
        return a + b + c;
    }

    // Subtraction
    public int subtract(int a, int b) {
        return a - b;
    }

    // Multiplication
    public double multiply(double a, double b) {
        return a * b;
    }

    // Division with exception handling
    public double divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Error: Cannot divide by zero!");
        }
        return (double) a / b;
    }
}
