
/**
 * Description:
 * -------------
 * 
 * Bag (Interface)
 * ----------------
 * Defines the abstract data type for a Bag collection, based on a USet concept.
 * A Bag allows duplicate elements and supports operations:
 * add(x), remove(x), find(x), and findAll(x).
 */

import java.util.List;

public interface Bag {

    // Add element (duplicates allowed)
    public void add(String x);

    // Remove one occurrence
    public boolean remove(String x);

    // Return first occurrence
    String find(String x);

    // Return all occurrences
    List<String> findAll(String x);

    // Return number of elements

    int size();

}
