import java.util.Scanner;

public class task1_extra2 {
    public static String ipFromNum(long num) {
        StringBuilder ip = new StringBuilder(15);
        for (int i = 0; i < 4; i++) {
            ip.insert(0, Long.toString(num & 0xff));
            if (i < 3) {
                ip.insert(0, '.');
            }
            num = num >> 8;
        }
        return ip.toString();
    }

    public static void main(String[] args) {
        long example = 2149583361L;
        System.out.printf("ipFromNum(%d) = \"%s\"\n", example, ipFromNum(example));

        System.out.print("Enter number between 0-4294967295: ");
        Scanner scanner = new Scanner(System.in);
        long inputNum = Long.parseLong(scanner.nextLine());
        System.out.printf("ipFromNum(%d) = \"%s\"\n", inputNum, ipFromNum(inputNum));
    }
}
