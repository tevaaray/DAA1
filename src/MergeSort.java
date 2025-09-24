public class MergeSort {
    private Metrics metrics;

    public MergeSort(Metrics metrics) {
        this.metrics = metrics;
    }

    public void sort(int[] arr) {
        int[] buffer = new int[arr.length];
        mergeSort(arr, buffer, 0, arr.length - 1);
    }

    private void mergeSort(int[] arr, int[] buffer, int left, int right) {
        metrics.enterRecursion();
        if (right - left <= 10) {  // cutoff
            insertionSort(arr, left, right);
            metrics.exitRecursion();
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(arr, buffer, left, mid);
        mergeSort(arr, buffer, mid + 1, right);
        merge(arr, buffer, left, mid, right);
        metrics.exitRecursion();
    }

    private void merge(int[] arr, int[] buffer, int left, int mid, int right) {
        System.arraycopy(arr, left, buffer, left, right - left + 1);
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            metrics.comparisons++;
            if (buffer[i] <= buffer[j]) arr[k++] = buffer[i++];
            else arr[k++] = buffer[j++];
            metrics.swaps++;
        }
        while (i <= mid) arr[k++] = buffer[i++];
        while (j <= right) arr[k++] = buffer[j++];
    }

    private void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
                metrics.comparisons++;
                metrics.swaps++;
            }
            arr[j + 1] = key;
            metrics.swaps++;
        }
    }
}
