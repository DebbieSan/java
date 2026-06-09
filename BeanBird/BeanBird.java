import java.util.ArrayList;
import java.util.HashMap;

/**
 * The main class for the BeanBird budgeting program.
 *
 * This class starts the program and holds the first test version
 * of the budget categories and transactions.
 */
public class BeanBird {

    /**
     * The main method is where the BeanBird program starts.
     *
     * For this first version of BeanBird, the program:
     * 1. Creates budget categories
     * 2. Creates sample income and expense transactions
     * 3. Prints the categories
     * 4. Prints the transactions
     *
     * @param args command-line arguments, which we are not using yet
     */
    public static void main(String[] args) {

        /*
         * Stores budget categories by their name.
         *
         * The key is a String, such as "Gas".
         * The value is a Category object.
         */
        HashMap<String, Category> categories = new HashMap<>();

        /*
         * Stores all income and expense transactions.
         *
         * Each item in this ArrayList is a Transaction object.
         */
        ArrayList<Transaction> transactions = new ArrayList<>();

        /*
         * Add default budget categories to the HashMap.
         */
        categories.put("Gas", new Category("Gas", 400.00));
        categories.put("Housing", new Category("Housing", 1000.00));
        categories.put("Subscriptions", new Category("Subscriptions", 300.00));
        categories.put("Groceries", new Category("Groceries", 500.00));
        categories.put("Restaurants", new Category("Restaurants", 300.00));
        categories.put("Hobbies", new Category("Hobbies", 200.00));

        /*
         * Add sample transactions to the ArrayList.
         */
        transactions.add(new Transaction("Paycheque", "Income", 2500.00, TransactionType.INCOME));
        transactions.add(new Transaction("Gas Pump", "Gas", 50.00, TransactionType.EXPENSE));
        transactions.add(new Transaction("Netflix", "Subscriptions", 15.99, TransactionType.EXPENSE));

        System.out.println("        .-.");
        System.out.println("       (o o)");
        System.out.println("      /  V  \\");
        System.out.println("     /(  _  )\\");
        System.out.println("       ^^ ^^");
        System.out.println("     BeanBird");
        System.out.println();
        System.out.println("Welcome to BeanBird!");

        System.out.println("Categories:");

        for (String categoryName : categories.keySet()) {
            Category category = categories.get(categoryName);

            System.out.println(
                    category.getName()
                            + " - Budget: $"
                            + category.getMonthlyBudget());
        }

        System.out.println();

        System.out.println("Transactions:");

        for (Transaction transaction : transactions) {
            System.out.println(
                    transaction.getDescription()
                            + " | "
                            + transaction.getCategory()
                            + " | $"
                            + transaction.getAmount()
                            + " | "
                            + transaction.getType());
        }
    }
}
