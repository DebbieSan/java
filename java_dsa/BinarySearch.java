import java.util.Arrays;


public class BinarySearch {
    

    public static int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (array[mid] == target) {
                System.out.println("This target was found in the array.");
                return mid;
            }

            if (array[mid] < target) {
                left = mid + 1;

            }

            else {
                right = mid - 1;
            }

        
        }
        System.out.println("Target not found");
        return -1;
       
         
    }
    

    public static void main(String[] args) {
        int[] myArray = { 1, 2, 3, 4, 5, 6, 7 };


        binarySearch(myArray, 15);        

    }


}