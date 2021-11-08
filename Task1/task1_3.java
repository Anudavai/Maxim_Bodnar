import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class task1_3 {
    public static Integer digitalRoot(Integer n) {
        if (n < 0) {
            throw new RuntimeException("The number can't be negative");
        }
        //int length = (int) (Math.log10(n) + 1);
        int length = Integer.toString(n).length();
        if (length > 1) {
            int sum = 0;
            while (n > 0) {
                sum += n % 10;
                n = n / 10;
            }
            return digitalRoot(sum);
        }
        return n;
    }

    public static void main(String[] args) {
        List<Integer> examples = Arrays.asList(16, 942, 132189, 493193);
        for (Integer example : examples) {
            System.out.println(String.format("digitalRoot(%d) = ", example) + digitalRoot(example));
        }

        System.out.print("Enter number: ");
        Scanner scanner = new Scanner(System.in);
        int inputNum = Integer.parseInt(scanner.nextLine());
        System.out.println(String.format("digitalRoot(%d) = ", inputNum) + digitalRoot(inputNum));
    }
}
