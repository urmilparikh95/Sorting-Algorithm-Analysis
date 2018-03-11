import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class HeapSort {

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
        arr = Heapsort(arr);
        long end_time = System.nanoTime();
        double difference = (end_time - start_time) / 1e6;
        for (int k = 0; k < arr.size(); k++) {
            System.out.println(arr.get(k));
        }
        System.err.println("runtime," + Math.round(difference));
        System.err.println("comparisons," + comparisons);
    }

    public static List<Integer> heapify(List<Integer> list, int index) {
        int len = list.size();
        if (len - 1 >= 2 * index + 1) {
            int y;
            if (len - 1 == 2 * index + 1) {
                y = len - 1;
            } else {
                if (c.compare(list.get(2 * index + 1), list.get(2 * index + 2)) > 0) {
                    y = 2 * index + 1;
                } else {
                    y = 2 * index + 2;
                }
            }
            if (c.compare(list.get(index), list.get(y)) < 0) {
                int temp = list.get(y);
                list.set(y, list.get(index));
                list.set(index, temp);
                heapify(list, y);
            }
        }
        return list;
    }

    public static List<Integer> Buildheap(List<Integer> list) {
        int n = list.size();
        for (int i = n / 2; i >= 0; i--) {
            list = heapify(list, i);
        }
        return list;
    }

    public static List<Integer> Heapsort(List<Integer> list) {
        list = Buildheap(list);
        List<Integer> l = new ArrayList<>();
        while (list.size() > 0) {
            int len = list.size();
            int temp = list.get(0);
            list.set(0, list.get(len - 1));
            list.set(len - 1, temp);
            int n = list.remove(len - 1);
            l.add(0, n);
            list = heapify(list, 0);
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
