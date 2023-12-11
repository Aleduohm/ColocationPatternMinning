package universal;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

//计算支持度阈值
public class CalculatePrev {
    public static HashMap<List<String>, List<List<Point>>> calculatePrev(HashMap<List<String>, List<List<Point>>> CkTk,
                                                                          HashMap<String, Integer> featureNum, double minPrev) {

//        HashMap<List<String>, List<List<String>>> PkTk = CkTk;
        //tips：如果使用上面一条代码，PkCk与TkCk指向同一数据，PkCk发生改变TkCk也会发生改变
        HashMap<List<String>, List<List<Point>>> PkTk = new HashMap<>(CkTk);

        for (var entry : CkTk.entrySet()) {//Pattern: [H, M]，Instances: [[1248, 3577], [1258, 3580], [1259, 3580]]
            double fractionScore = 0;
            double minScore = 1;
            HashMap<Point, Integer> colocationFeaNum = new HashMap<>();
            //得到当前计算的特征
            for (int i = 0; i < entry.getKey().size(); i++) {
                //得到当前colocation的所有行实例
                for (List<Point> RI : entry.getValue()) {
                    Point key = RI.get(i);//1248、1258、1259
                    if (colocationFeaNum.containsKey(key)) {
                        int value = colocationFeaNum.get(key);
                        colocationFeaNum.put(key, value + 1);
                    }
                    else {
                        colocationFeaNum.put(key, 1);
                    }
                }
                if (colocationFeaNum.size() != 0 ) {
                    //定义保留4位小数
                    DecimalFormat df = new DecimalFormat("#0.0000");
                    //每个特征下不同实例的数量/该特征实例的总数量
                    fractionScore = Double.parseDouble(df.format((double) colocationFeaNum.size() / featureNum.get(entry.getKey().get(i))));
//                    System.out.println(featureNum.get(entry.getKey().get(i)));
                }
                minScore = fractionScore;
                if (minScore > fractionScore) {
                    minScore = fractionScore;
                }
            }
            if (minScore < minPrev) {
                PkTk.remove(entry.getKey());
            }
        }
        return PkTk;
    }
}
