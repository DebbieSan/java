/**
 * Represents one money movement in BeanBird.
 *
 * A transaction can be either income or an expense.
 *
 * Examples:
 * - Paycheque: income
 * - Shell gas: expense
 * - Netflix subscription: expense
 * - Grocery store purchase: expense
 */

public class Transaction {

    private String description; // A short description of the transaction such as "Paycheque"
    private String category; // The category this transaction belongs to such as "gas"
    private double amount; // The dollar amount of the transaction such as 45.00
    private TransactionType type; // This tells BeanBird if the transaction is income or expense

    /**
     * Creates a new Transaction object.
     *
     * @param description a short description of the transaction
     * @param category    the category the transaction belongs to
     * @param amount      the dollar amount of the transaction
     * @param type        whether the transaction is income or expense
     */

    public Transaction(String description, String category, double amount, TransactionType type) {
        this.description = description;
        this.category = category;
        this.amount = amount;
        this.type = type;

    }

    /**
     * Gets the transaction description.
     *
     * @return the description of the transaction
     */

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

}
