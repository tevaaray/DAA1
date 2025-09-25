public class DeterministicSelect {
    private Metrics metrics;

    public DeterministicSelect(Metrics metrics) {
        this.metrics = metrics;
    }

    public int select(int[] arr, int k) {
        return select(arr, 0, arr.length - 1, k);
    }

    private int select(int[] arr, int left, int right, int k) {
        metrics.enterRecursion();
        if (right - left < 5) {
            insertionSort(arr, left, right);
            metrics.exitRecursion();
            return arr[left + k];
        }

        int n = right - left + 1;
        int numMedians = (n + 4) / 5;
        int[] medians = new int[numMedians];
        for (int i = 0; i < numMedians; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(subLeft + 4, right);
            insertionSort(arr, subLeft, subRight);
            medians[i] = arr[subLeft + (subRight - subLeft) / 2];
        }

        int pivot = select(medians, 0, numMedians - 1, numMedians / 2);
        int pivotIndex = partition(arr, left, right, pivot);

        int rank = pivotIndex - left;
        metrics.exitRecursion();
        if (k == rank) return arr[pivotIndex];
        else if (k < rank) return select(arr, left, pivotIndex - 1, k);
        else return select(arr, pivotIndex + 1, right, k - rank - 1);
    }

    private int partition(int[] arr, int left, int right, int pivot) {
        int i = left, j = right;
        while (i <= j) {
            while (arr[i] < pivot) { i++; metrics.comparisons++; }
            while (arr[j] > pivot) { j--; metrics.comparisons++; }
            if (i <= j) {
                swap(arr, i, j);
                i++; j--;
            }
        }
        return i - 1;
    }

    private void swap(int[] arr, int i, int j) {
        metrics.swaps++;
        int tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
    }

    private void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i]; int j = i - 1;
            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j]; j--;
                metrics.comparisons++; metrics.swaps++;
            }
            arr[j + 1] = key; metrics.swaps++;
        }
    }
}
