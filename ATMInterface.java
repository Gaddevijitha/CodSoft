import java.util.Scanner;

// Class to represent the user's bank account
class BankAccount {
    private String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return "Successfully deposited " + amount + ". New balance is " + balance;
        } else {
            return "Deposit amount must be positive.";
        }
    }

    public String withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return "Successfully withdrew " + amount + ". New balance is " + balance;
        } else if (amount > balance) {
            return "Insufficient balance.";
        } else {
            return "Withdrawal amount must be positive.";
        }
    }

    public String checkBalance() {
        return "Your current balance is " + balance;
    }

    public double getBalance() {
        return balance;
    }
}

// Class to represent the ATM interface
class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void displayMenu() {
        System.out.println("ATM Menu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Exit");
    }

    public void processOption(int option, Scanner scanner) {
        switch (option) {
            case 1:
                System.out.println(account.checkBalance());
                break;
            case 2:
                System.out.print("Enter the amount to deposit: ");
                double depositAmount = scanner.nextDouble();
                System.out.println(account.deposit(depositAmount));
                break;
            case 3:
                System.out.print("Enter the amount to withdraw: ");
                double withdrawAmount = scanner.nextDouble();
                System.out.println(account.withdraw(withdrawAmount));
                break;
            case 4:
                System.out.println("Thank you for using the ATM. Goodbye!");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
}
// Main class to run the ATM program
public class ATMInterface {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("12345678", 500.00); // Creating a bank account with initial balance
        ATM atm = new ATM(account);
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            atm.displayMenu();
            System.out.print("Choose an option: ");
            option = scanner.nextInt();
            atm.processOption(option, scanner);
        } while (option != 4);

        scanner.close();
    }
}
