import java.awt.Point;
import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {
    private double bestDist;
    private Point[] bestPair;

    public double distance(Point a, Point b) {
        return a.distance(b);
    }

    public Point[] findClosest(Point[] points) {
        Point[] px = points.clone();
        Arrays.sort(px, Comparator.comparingInt(p -> p.x));
        bestDist = Double.MAX_VALUE;
        closest(px, 0, px.length - 1);
        return bestPair;
    }

    private void closest(Point[] px, int left, int right) {
        if (right - left <= 3) {
            for (int i = left; i <= right; i++)
                for (int j = i + 1; j <= right; j++)
                    update(px[i], px[j]);
            return;
        }
        int mid = (left + right) / 2;
        closest(px, left, mid);
        closest(px, mid + 1, right);

        int midX = px[mid].x;
        Point[] strip = Arrays.stream(px, left, right + 1)
                .filter(p -> Math.abs(p.x - midX) < bestDist)
                .toArray(Point[]::new);
        Arrays.sort(strip, Comparator.comparingInt(p -> p.y));

        for (int i = 0; i < strip.length; i++)
            for (int j = i + 1; j < strip.length && (strip[j].y - strip[i].y) < bestDist; j++)
                update(strip[i], strip[j]);
    }

    private void update(Point a, Point b) {
        double d = distance(a, b);
        if (d < bestDist) {
            bestDist = d;
            bestPair = new Point[]{a, b};
        }
    }
}
