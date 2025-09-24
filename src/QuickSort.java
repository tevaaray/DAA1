import java.util.Random;

public class QuickSort {
    private Metrics metrics;
    private Random rand = new Random();

    public QuickSort(Metrics metrics) {
        this.metrics = metrics;
    }

    public void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(int[] arr, int low, int high) {
        while (low < high) {
            metrics.enterRecursion();
            int pivotIndex = partition(arr, low, high);
            metrics.exitRecursion();
            if (pivotIndex - low < high - pivotIndex) {
                quickSort(arr, low, pivotIndex - 1);
                low = pivotIndex + 1;
            } else {
                quickSort(arr, pivotIndex + 1, high);
                high = pivotIndex - 1;
            }
        }
    }

    private int partition(int[] arr, int low, int high) {
        int pivotIndex = low + rand.nextInt(high - low + 1);
        int pivot = arr[pivotIndex];
        swap(arr, pivotIndex, high);
        int i = low;
        for (int j = low; j < high; j++) {
            metrics.comparisons++;
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, high);
        return i;
    }

    private void swap(int[] arr, int i, int j) {
        metrics.swaps++;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
