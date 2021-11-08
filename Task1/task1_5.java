import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Stream;
import java.util.Arrays;

public class task1_5 {
    public static String meeting(String str) {
        str = str.toUpperCase();
        String[] guestsRaw = str.split(";");
        List<String[]> guests = new ArrayList<>();

        // --Via For--
        for (String guestRaw : guestsRaw) {
            String[] guest = guestRaw.split(":");
            guests.add(guest);
        }

        // --Via Stream--
        //Stream<String> stream = Arrays.stream(guestsRaw);
        //stream.forEach(guestRaw -> {guests.add(guestRaw.split(":"));});

        guests.sort(new Comparator<>() {
            @Override
            public int compare(String[] guest1, String[] guest2) {
                String lastName1 = guest1[1];
                String lastName2 = guest2[1];

                int res = lastName1.compareTo(lastName2);
                if (res != 0) {
                    return res;
                } else {
                    String firstName1 = guest1[0];
                    String firstName2 = guest2[0];
                    return firstName1.compareTo(firstName2);
                }
            }
        });

        String out = "";
        for (String[] fullName : guests) {
            out += "(" + fullName[1] + ", " + fullName[0] + ")";
        }

        return out;
    }

    public static void main(String[] args) {
        String s = "Fred:Corwill;Wilfred:Corwill;Bainey:Tornbull;Betty:Tornbull;Bjon:Tornbull;Raphael:Corwill;Alfred:Corwill";
        System.out.println("s = \"" + s + "\"");
        System.out.println("meeting(s) = \"" + meeting(s) + "\"");
    }
}
