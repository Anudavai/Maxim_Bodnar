import java.util.Collections;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class task1_extra1 {
    public static int nextBigger(int n) {
        int out = -1;
        int pivot = Integer.MAX_VALUE;

        List<String> digits = Arrays.asList(Integer.toString(n).split(""));
        for (int i = 0; i < digits.size(); i++) {
            for (int j = 0; j < digits.size(); j++) {
                Collections.swap(digits, i, j);
                int nextNum = Integer.parseInt(String.join("", digits));
                if (nextNum > n && nextNum <= pivot) {
                    pivot = nextNum;
                    out = pivot;
                }
            }
        }

        return out;
    }

    public static void main(String[] args) {
        int[] examples = {12, 513, 2017, 9, 531, 111, 1001};
        for (int example : examples) {
            System.out.println(String.format("nextBigger(%d) = ", example) + nextBigger(example));
        }

        System.out.print("Enter number: ");
        Scanner scanner = new Scanner(System.in);
        int inputNum = Integer.parseInt(scanner.nextLine());
        System.out.println(String.format("nextBigger(%d) = ", inputNum) + nextBigger(inputNum));
    }
}
