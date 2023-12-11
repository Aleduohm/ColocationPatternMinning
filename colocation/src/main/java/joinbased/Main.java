package joinbased;

import universal.CalculateDistance;
import universal.CalculatePrev;
import universal.Point;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> featerSet = new ArrayList<>();

        //获取文件数据 E:\jobfile\DataMining\常用数据集\三江并流\sanjiangbingliu.csv
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入文件目录");
        String path = sc.nextLine();

        FileIO fileIO = new FileIO();
        List<Point> points = fileIO.ReadFromFile(path);

        //得到特征集合
        for (Point point : points) {
            featerSet.add(point.feature);
        }

        //1阶候选co-location集
        List C1 = featerSet.stream().distinct().collect(Collectors.toList());
        System.out.println(C1);
        //1阶频繁co-location集
        List P1 = C1;

        GenCandidateColocation genCandidateColocation = new GenCandidateColocation();
        HashMap<List<String>, List<List<Point>>> P2 = genCandidateColocation.genC2(P1, points, 300);

        //entrySet():返回一个包含所有键值对（Entry 对象）的 Set 集合。每个 Entry 对象都包含一个键和对应的值。
        for (var entry : P2.entrySet()) {
            System.out.println("Pattern: " + entry.getKey());
//            System.out.println("Instances: " + entry.getValue());
        }
//
        System.out.println(P2.size());

        //计算k阶频繁模式
        HashMap<List<String>, List<List<Point>>> Pk = genCandidateColocation.genCkTk(P2, points, 300);
        for (var entry : Pk.entrySet()) {
            System.out.println("Pattern: " + entry.getKey());
//            System.out.println("Instances: " + entry.getValue());
        }
        System.out.println(Pk.size());

        while (!Pk.isEmpty()) {
            Pk = genCandidateColocation.genCkTk(Pk, points, 300);
            for (var entry : Pk.entrySet()) {
                System.out.println("Pattern: " + entry.getKey());
//            System.out.println("Instances: " + entry.getValue());
            }
            System.out.println(Pk.size());
        }
    }
}
