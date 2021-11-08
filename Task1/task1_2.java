import java.util.Scanner;
import java.util.List;
import java.util.Arrays;

public class task1_2 {
    public static char first_non_repeating_letter(String str) {
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            String tempStr = (str.substring(0, i) + str.substring(i+1)).toUpperCase();
            if (!tempStr.contains(""+Character.toUpperCase(ch))) {
                return ch;
            }
        }
        return '\0';
    }

    public static void main(String[] args) {
        List<String> examples = Arrays.asList("stress", "sTreSS");
        for (String example : examples) {
            System.out.println(String.format("first_non_repeating_letter(\"%s\") = ", example) + first_non_repeating_letter(example));
        }

        System.out.print("Enter input string: ");
        Scanner scanner = new Scanner(System.in);
        String input_str = scanner.nextLine();
        System.out.println(String.format("first_non_repeating_letter(\"%s\") = ", input_str) + first_non_repeating_letter(input_str));
    }
}
