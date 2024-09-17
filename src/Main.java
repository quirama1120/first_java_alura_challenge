import java.io.File;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        try {
            File file = new File("F:\\Documentos\\PROGRAMACIÓN\\ALURA\\JAVA\\studying_and_learning\\1rst_activity\\src\\empleados.txt");
            Scanner keyword = new Scanner(System.in);
            Scanner reader = new Scanner(file);
            String name = "";
            String account = "";
            String textBalance = "";
            double balance = 0;

            if(file.exists()) {
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    String[] lineArray = line.split(":");
                    if (lineArray[0].equals("Client name")) {
                        name = lineArray[1];
                    }
                    if (lineArray[0].equals("kind of account")) {
                        account = lineArray[1];
                    }
                    if (lineArray[0].equals("Available balance")) {
                        textBalance = lineArray[1].replace("$", "").trim();
                        balance = Double.parseDouble(textBalance);
                    }
                }
            } else {
                System.out.println("El archivo 'empleados.txt' no existe.");
            }

            reader.close();
            boolean running = true;
            while (running) {
                showMenu(name, account);
                String userInput = keyword.nextLine();

                switch (userInput) {
                    case    "1" -> checkBalance(balance);
                    case    "2" -> {
                        System.out.println( """
                        Your balance is
                        """ + balance + """
                         How much do you want to withDraw
                        """);
                        double userDoubleInput = keyword.nextDouble();
                        keyword.nextLine();
                        balance = withdrawMoney(userDoubleInput, balance);
                    }
                    case    "3" -> {
                        System.out.println("insert the amount to deposit");
                        double userDoubleInput = keyword.nextDouble();
                        keyword.nextLine();
                        balance = depositMoney(userDoubleInput, balance);
                    }
                    case    "4" -> {
                        System.out.println("Exiting...");
                        running = false;
                    }
                    default -> System.out.println("Invalid option, please input a correct number and try again");
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }

    public static void checkBalance(double balance) {
        System.out.println("your current balance is " + balance);
    }

    public static double withdrawMoney(double userDoubleInput, double balance) {
        if(userDoubleInput < balance && userDoubleInput > 0) {
            balance -= userDoubleInput;
            System.out.println("""
                You have withdrawn
                """ + userDoubleInput + """
                 your current balance is
                """ + balance );
        } else {
            System.out.println("insufficient funds or incorrect value.");
        }
    return balance;
    }

    public static double depositMoney(double userDoubleInput, double balance) {
        balance += userDoubleInput;
        System.out.println("""
                You have deposited
                """ + userDoubleInput +
                " Your current balance is " + balance
                );
        return balance;
    }

    public static void showMenu(String name, String account) {
        System.out.println("Your name: " + name);
        System.out.println("Your account: " + account);
        System.out.println("""                      
                        Choose one of the following options:
                        1. Check your balance.
                        2. Withdraw money from the ATM.
                        3. Deposit money.
                        4. Exit.
                        """);
    }
}

