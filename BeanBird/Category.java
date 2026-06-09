/**
 * Represents a budget category in BeanBird.
 *
 * A category is a group where expenses can belong.
 * For example, "Gas", "Groceries", "Housing", or "Restaurants".
 */

public class Category {

    private String name; // The name of the budget category
    private double monthlyBudget;// The amount of money planned per category, for example $100 for groceries

    /**
     * Creates a new Category object.
     *
     * @param name          the name of the category
     * @param monthlyBudget the monthly budget amount for this category
     */

    public Category(String name, double monthlyBudget) {
        this.name = name;
        this.monthlyBudget = monthlyBudget;
    }

    /**
     * Gets the name of the category.
     *
     * @return the category name
     */

    public String getName() {
        return name;
    }

    /**
     * Gets the monthly budget amount for this category.
     *
     * @return the monthly budget amount
     */

    public double getMonthlyBudget() {
        return monthlyBudget;
    }

    /**
     * Changes the monthly budget amount for this category.
     *
     * This will be useful later if the user wants to edit
     * a category budget.
     *
     * @param monthlyBudget the new monthly budget amount
     */

    public void setMonthlyBudget(double monthlyBudget) {
        this.monthlyBudget = monthlyBudget;

    }

}
