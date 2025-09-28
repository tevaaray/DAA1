import java.util.Arrays;
import java.util.Random;

public class MainGraph {
    public static void main(String[] args) {
        Metrics m = new Metrics();
        Random rand = new Random();

        int[] sizes = {100, 500, 1000, 5000, 10000};
        System.out.println("n,MergeSortComparisons,QuickSortComparisons");

        for (int n : sizes) {
            int[] arr1 = new int[n];
            for (int i = 0; i < n; i++) arr1[i] = rand.nextInt(10000);

            // MergeSort
            int[] arr2 = Arrays.copyOf(arr1, arr1.length);
            m.reset();
            MergeSort ms = new MergeSort(m);
            ms.sort(arr2);
            long mergeComparisons = m.comparisons;

            // QuickSort
            arr2 = Arrays.copyOf(arr1, arr1.length);
            m.reset();
            QuickSort qs = new QuickSort(m);
            qs.sort(arr2);
            long quickComparisons = m.comparisons;


            System.out.println(n + "," + mergeComparisons + "," + quickComparisons);
        }
    }
}
