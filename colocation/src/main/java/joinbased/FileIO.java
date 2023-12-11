package joinbased;

import universal.Point;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileIO {
    public List<Point> ReadFromFile(String path) throws IOException {

        String line;
        String[] elem;
        BufferedReader br = null;
        Point point = null;
        //注意此处初始化若为null，则会报错：Cannot invoke "java.util.List.add(Object)" because "points" is null
        List<Point> points = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(path));
            line = br.readLine();;

            while ((line = br.readLine()) != null) {
                elem = line.split(",");
                point = new Point();
                point.feature = elem[0];
                point.id = elem[1];
                point.x = Double.parseDouble(elem[2]);
                point.y = Double.parseDouble(elem[3]);

//                points = createPointSet(point);
                //将point放入集合中
                points.add(point);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br.close();
        }

        return points;
    }
}
