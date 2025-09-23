public class Searching {
    
    public static int linearSearch(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }

        }

        return -1;
    }

    public static int binarySearch(int[] biArray, int newTarget) {
        int left = 0;
        int right = biArray.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Check if the target it present at the mid

            if (biArray[mid] == newTarget) {
                return mid;
            }

            // If the target is greater, ignore left half
            else if (biArray[mid] < newTarget) {
                left = mid + 1;
            }

            // If target is smaller, ignore right half
            else {
                right = mid - 1;
            }

        }
        
        // if we reach here then the element was not present
         return -1;

    }
    

    public static void main(String[] args) {


        int[] array = { 1, 2, 3, 4, 5 };
        int target = 3;

        int result = linearSearch(array, target);

        if (result == -1) {
            System.out.println("Element not found in the array");
        } else {
            System.out.println("Element found at index: " + result + " The element is " + array[result]);
        }


        int[] newArray = { 7, 8, 9, 10, 11 };
        int newTarget = 12;

        int newResult = binarySearch(newArray, newTarget);

        if (newResult == -1) {
            System.out.println("Sorry, the element was not found in this array.");
        } else {
            System.out.println("Element found in index: " + newResult + " Element is: " + newArray[newResult]);
        }


    
}
}



