package universal;

/*
定义了空间实例，包括实例的属性：
    1.特征 : String
    2.id : String
    3.x坐标 : double
    4.y坐标 : double
 */

public class Point {
    public String feature;
    public String id;
    public double x;
    public double y;

    public Point(String feature, String id, double x, double y) {
        this.feature = feature;
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public Point() {
    }
}

