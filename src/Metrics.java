public class Metrics {
    public long comparisons = 0;
    public long swaps = 0;
    public int maxDepth = 0;
    public int currentDepth = 0;

    public void enterRecursion() {
        currentDepth++;
        if (currentDepth > maxDepth) maxDepth = currentDepth;
    }

    public void exitRecursion() {
        currentDepth--;
    }

    public void reset() {
        comparisons = 0;
        swaps = 0;
        maxDepth = 0;
        currentDepth = 0;
    }
}
