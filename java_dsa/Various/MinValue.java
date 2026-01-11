public class MinValue {

    public static int minValue( int[] array, int left, int right) {
        
        if (right - left <= 1){
            return array[left] < array[right] ? array[left] : array[right];
        }else{
            int mid = left + (right - left) /2;
            int leftMin = minValue (array,left,mid);
            int rightMin = minValue (array, mid +1, right);
            return leftMin < rightMin ? leftMin : rightMin;
        }


        
    }

    public static void main(String[] args) {

        int[] array = { 0, -9, 13, 4, 645, 86, -67, 230, 21, 42 };


        int result = minValue(array, 0, array.length - 1);

        //Output the result

        System.out.println("The element with the smallest value is: " + result);


        // trying on a different array

        int[] newArray = { 12, 15, 18, 25, 27, 35, 258, -45, -200 };

        int secondResult = minValue(newArray, 0, newArray.length - 1);

        //Output the second result

        System.out.printf("The element with the smallest value in the second array is %d  \n ", secondResult);
    }

}