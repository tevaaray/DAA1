import java.awt.Point;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Metrics m = new Metrics();
        int[] arr = {5, 2, 9, 1, 5, 6};

        System.out.println("Original: " + Arrays.toString(arr));

        MergeSort ms = new MergeSort(m);
        ms.sort(arr);
        System.out.println("MergeSort: " + Arrays.toString(arr));
        System.out.println("Comparisons: " + m.comparisons + ", Swaps: " + m.swaps + ", MaxDepth: " + m.maxDepth);

        m.reset();
        ArrayUtils.shuffle(arr);
        QuickSort qs = new QuickSort(m);
        qs.sort(arr);
        System.out.println("QuickSort: " + Arrays.toString(arr));
        System.out.println("Comparisons: " + m.comparisons + ", Swaps: " + m.swaps + ", MaxDepth: " + m.maxDepth);

        m.reset();
        DeterministicSelect ds = new DeterministicSelect(m);
        int kth = ds.select(arr, 2);  // 3-й элемент по порядку
        System.out.println("3rd smallest element: " + kth);
        System.out.println("Comparisons: " + m.comparisons + ", Swaps: " + m.swaps);

        Point[] points = {new Point(0,0), new Point(1,1), new Point(2,2), new Point(0,2)};
        ClosestPair cp = new ClosestPair();
        Point[] pair = cp.findClosest(points);
        System.out.println("Closest Pair: (" + pair[0].x + "," + pair[0].y + ") and (" + pair[1].x + "," + pair[1].y + ")");
    }
}
