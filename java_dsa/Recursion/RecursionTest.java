public record RecursionTest() {

    public static void main(String[] args) {

        sayHi(5);
        System.out.println(sumTo(5));

    }

    private static void sayHi(int count) {
        System.out.println("Hi");

        if (count <= 1) {
            return;
        }

        sayHi(count - 1);

    }

    private static int sumTo(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Must be greater or equal to 0");
        }

        if (n == 0) {
            return 0;
        }

        return n + sumTo(n - 1);

    }

}
