import java.util.Scanner;

class Account {
    int accountNumber;
    String accountHolderName;
    double balance;
    String email;
    String phoneNumber;

    // Constructor
    Account(int accNo, String name, double initialDeposit, String email, String phone) {
        this.accountNumber = accNo;
        this.accountHolderName = name;
        this.balance = initialDeposit;
        this.email = email;
        this.phoneNumber = phone;
    }

    // Deposit
    void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. New Balance: " + balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    // Withdraw
    void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
        } else if (amount > balance) {
            System.out.println("Insufficient balance!");
        } else {
            balance -= amount;
            System.out.println("Withdrawal successful. New Balance: " + balance);
        }
    }

    // Show details
    void displayAccountDetails() {
        System.out.println("\n--- Account Details ---");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Name           : " + accountHolderName);
        System.out.println("Balance        : " + balance);
        System.out.println("Email          : " + email);
        System.out.println("Phone          : " + phoneNumber);
    }

    // Update contact
    void updateContactDetails(String newEmail, String newPhone) {
        email = newEmail;
        phoneNumber = newPhone;
        System.out.println("Contact details updated.");
    }
}

public class BankingApp {
    static Account[] accounts = new Account[100]; // array of accounts
    static int accountCount = 0;
    static int nextAccountNumber = 1001;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- Banking Application ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. View Account Details");
            System.out.println("5. Update Contact");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1: createAccount(); break;
                case 2: performDeposit(); break;
                case 3: performWithdrawal(); break;
                case 4: showAccountDetails(); break;
                case 5: updateContact(); break;
                case 6: System.out.println("Exiting... Goodbye!"); break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 6);
    }

    // Create account
    static void createAccount() {
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter initial deposit: ");
        double deposit = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter email: ");
        String email = sc.nextLine();
        System.out.print("Enter phone: ");
        String phone = sc.nextLine();

        Account acc = new Account(nextAccountNumber++, name, deposit, email, phone);
        accounts[accountCount++] = acc;
        System.out.println("Account created successfully with Account No: " + acc.accountNumber);
    }

    // Find account
    static Account findAccount(int accNo) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].accountNumber == accNo) {
                return accounts[i];
            }
        }
        return null;
    }

    // Deposit
    static void performDeposit() {
        System.out.print("Enter account number: ");
        int accNo = sc.nextInt();
        System.out.print("Enter amount to deposit: ");
        double amount = sc.nextDouble();
        sc.nextLine();
        Account acc = findAccount(accNo);
        if (acc != null) acc.deposit(amount);
        else System.out.println("Account not found.");
    }

    // Withdraw
    static void performWithdrawal() {
        System.out.print("Enter account number: ");
        int accNo = sc.nextInt();
        System.out.print("Enter amount to withdraw: ");
        double amount = sc.nextDouble();
        sc.nextLine();
        Account acc = findAccount(accNo);
        if (acc != null) acc.withdraw(amount);
        else System.out.println("Account not found.");
    }

    // Show details
    static void showAccountDetails() {
        System.out.print("Enter account number: ");
        int accNo = sc.nextInt();
        sc.nextLine();
        Account acc = findAccount(accNo);
        if (acc != null) acc.displayAccountDetails();
        else System.out.println("Account not found.");
    }

    // Update contact
    static void updateContact() {
        System.out.print("Enter account number: ");
        int accNo = sc.nextInt();
        sc.nextLine();
        Account acc = findAccount(accNo);
        if (acc != null) {
            System.out.print("Enter new email: ");
            String email = sc.nextLine();
            System.out.print("Enter new phone: ");
            String phone = sc.nextLine();
            acc.updateContactDetails(email, phone);
        } else {
            System.out.println("Account not found.");
        }
    }
}

