package universal;

public class CalculateDistance {
    public static double calculateDistance(Point point1, Point point2) {
        double distance = Math.sqrt(Math.pow(point1.x - point2.x, 2)
                + Math.pow(point1.y - point2.y, 2));
        return distance;
    }
}
