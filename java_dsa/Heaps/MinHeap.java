import java.util.ArrayList;

public class MinHeap {
    public ArrayList<Integer> heap;
    public int size;

    public MinHeap() {
        this.heap = new ArrayList<>();
        this.heap.add(null);
        this.size = 0;

    }

    public void add(int value) {
        this.heap.add(value);
        this.size++;
        System.out.println("Added value: " + value);
        System.out.println(this.heap);
        this.bubbleUp();

    }

    private void bubbleUp() {
        System.out.println("Restoring heap property...");
    }

    public static void main(String[] args) {
        MinHeap heap = new MinHeap();
        heap.add(10);
        heap.add(5);
        heap.add(20);
    }

}