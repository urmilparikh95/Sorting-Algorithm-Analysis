import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MergeSort {

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
        arr = Mergesort(arr);
        long end_time = System.nanoTime();
        double difference = (end_time - start_time) / 1e6;
        for (int k = 0; k < arr.size(); k++) {
            System.out.println(arr.get(k));
        }
        System.err.println("runtime," + Math.round(difference));
        System.err.println("comparisons," + comparisons);
    }

    public static List<Integer> Mergesort(List<Integer> l) {
        if (l.size() <= 1) {
            return l;
        } else {
            int pivot = l.size() / 2;
            int lsize = l.size();
            List<Integer> list1 = new ArrayList<>(l.subList(0, pivot));
            List<Integer> list2 = new ArrayList<>(l.subList(pivot, lsize));
            return Merge(Mergesort(list1), Mergesort(list2));
        }
    }

    public static List<Integer> Merge(List<Integer> l1, List<Integer> l2) {
        List<Integer> l = new ArrayList<>();
        while (l1.size() > 0 && l2.size() > 0) {
            if (c.compare(l1.get(0), l2.get(0)) < 0) {
                l.add(l1.remove(0));
            } else {
                l.add(l2.remove(0));
            }
        }
        if (l1.isEmpty()) {
            l.addAll(l2);
        } else {
            l.addAll(l1);
        }
        return l;
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
