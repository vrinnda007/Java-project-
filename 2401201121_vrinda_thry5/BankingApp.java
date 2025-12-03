import java.util.*;

class Account {

    int accountNumber;
    String accountHolderName;
    double balance;
    String email;
    String phoneNumber;

    Account(int accNo, String name, double bal, String mail, String phone) {
        accountNumber = accNo;
        accountHolderName = name;
        balance = bal;
        email = mail;
        phoneNumber = phone;
    }

    void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            System.out.println("Invalid amount.");
        }
    }

    void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    void displayAccountDetails() {
        System.out.println("Account No: " + accountNumber);
        System.out.println("Name: " + accountHolderName);
        System.out.println("Balance: " + balance);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phoneNumber);
    }

    void updateContactDetails(String mail, String phone) {
        email = mail;
        phoneNumber = phone;
    }
}

public class BankingApp {

    static Account[] accounts = new Account[100];
    static int count = 0;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== Banking Application =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. View Account");
            System.out.println("5. Update Contact");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume leftover enter

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    performDeposit();
                    break;
                case 3:
                    performWithdraw();
                    break;
                case 4:
                    showAccountDetails();
                    break;
                case 5:
                    updateContact();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    static void createAccount() {

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter initial balance: ");
        double bal = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter email: ");
        String mail = sc.nextLine();

        System.out.print("Enter phone: ");
        String phone = sc.nextLine();

        int accNo = 1000 + count;
        accounts[count] = new Account(accNo, name, bal, mail, phone);
        System.out.println("Account created with number: " + accNo);
        count++;
    }

    static void performDeposit() {

        System.out.print("Enter account number: ");
        int accNo = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter amount: ");
        double amt = sc.nextDouble();

        boolean found = false;

        for (int i = 0; i < count; i++) {
            if (accounts[i].accountNumber == accNo) {
                accounts[i].deposit(amt);
                System.out.println("Deposit successful!");
                found = true;
                break;
            }
        }

        if (!found) System.out.println("Account not found.");
    }

    static void performWithdraw() {

        System.out.print("Enter account number: ");
        int accNo = sc.nextInt();

        System.out.print("Enter amount: ");
        double amt = sc.nextDouble();

        boolean found = false;

        for (int i = 0; i < count; i++) {
            if (accounts[i].accountNumber == accNo) {
                accounts[i].withdraw(amt);
                System.out.println("Withdrawal successful!");
                found = true;
                break;
            }
        }

        if (!found) System.out.println("Account not found.");
    }

    static void showAccountDetails() {

        System.out.print("Enter account number: ");
        int accNo = sc.nextInt();

        boolean found = false;

        for (int i = 0; i < count; i++) {
            if (accounts[i].accountNumber == accNo) {
                accounts[i].displayAccountDetails();
                found = true;
                break;
            }
        }

        if (!found) System.out.println("Account not found.");
    }

    static void updateContact() {

        System.out.print("Enter account number: ");
        int accNo = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter new email: ");
        String mail = sc.nextLine();

        System.out.print("Enter new phone: ");
        String phone = sc.nextLine();

        boolean found = false;

        for (int i = 0; i < count; i++) {
            if (accounts[i].accountNumber == accNo) {
                accounts[i].updateContactDetails(mail, phone);
                System.out.println("Contact updated successfully!");
                found = true;
                break;
            }
        }

        if (!found) System.out.println("Account not found.");
    }
}
