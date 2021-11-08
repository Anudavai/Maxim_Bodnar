import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class task1_1 {
    public static List<Integer> getIntegersFromList(List list) {
        List<Integer> out = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof Integer) {
                out.add((Integer)list.get(i));
            }
        }
        return out;
    }

    public static void main(String[] args) {
        //getIntegersFromList(Arrays.asList(1,2,'a','b')=>{1,2}
        //getIntegersFromList(Arrays.asList(1,2,'a','b',0,15)=>{1,2,0,15}
        //getIntegersFromList(Arrays.asList(1,2,'a','b','aasf','1','123',231))=>{1,2,231}

        List example1 = Arrays.asList(1,2,'a','b');
        List example2 = Arrays.asList(1,2,'a','b',0,15);
        List example3 = Arrays.asList(1,2,'a','b',"aasf",'1',"123",231);

        System.out.println(String.format("getIntegersFromList(%s) = ", example1) + getIntegersFromList(example1));
        System.out.println(String.format("getIntegersFromList(%s) = ", example2) + getIntegersFromList(example2));
        System.out.println(String.format("getIntegersFromList(%s) = ", example3) + getIntegersFromList(example3));
    }
}
