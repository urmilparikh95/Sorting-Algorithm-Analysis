import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class SortUtility{

    private static long comparisons;
    private static Comp c;

    public static void main(String[] args) {
        comparisons = 0;
        int n;
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        n = Integer.parseInt(str.split(" ")[1]);
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(input.nextInt());
        }
        c = new Comp();
        long start_time = System.nanoTime();
        Collections.sort(arr, c);
        long end_time = System.nanoTime();
        double difference = (end_time - start_time) / 1e6;
        for (int k = 0; k < arr.size(); k++) {
            System.out.println(arr.get(k));
        }
        System.err.println("runtime," + Math.round(difference));
        System.err.println("comparisons," + comparisons);
    }

    public static class Comp implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            comparisons++;
            if (o1 > o2) {
                return 1;
            } else if (o1 < o2) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
