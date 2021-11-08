import java.util.Arrays;

public class task1_4 {
    public static int returnNumOfPairs(int[] arr, int target) {
        int counter = 0;
        for (int i : arr) {
            for (int j : arr) {
                int pairSum = i + j;
                if (pairSum == target) {
                    counter++;
                }
            }
        }

        return counter;
    }

    public static void main(String[] args) {
        int[] example = {1, 3, 6, 2, 2, 0, 4, 5};
        int target = 5;

        System.out.println("example = " + Arrays.toString(example));
        System.out.println("target = " + target);
        System.out.printf("NumOfPairs(%s, %d) = %d (With repeating)", Arrays.toString(example), target, returnNumOfPairs(example, target));
    }
}
