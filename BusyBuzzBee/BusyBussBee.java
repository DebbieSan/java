// This is the structure of a task manager utilizing the power of java's LinkedList class and the ListIterator interface.

import java.util.LinkedList;
import java.util.ListIterator;

public class BusyBussBee {

    public static void main(String[] args) {

        LinkedList<String> tasks = new LinkedList<>();

        tasks.add("task 2");
        tasks.add("task 3");
        tasks.add("task 4");

        tasks.addFirst("task 0");
        tasks.addLast("task 5");

        ListIterator<String> iterator = tasks.listIterator();

        System.out.println("Here is today's to do list:");

        while (iterator.hasNext()) {
            System.out.println(iterator.next());

        }

    }
}
