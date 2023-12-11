package joinbased;

import universal.CalculateDistance;
import universal.CalculatePrev;
import universal.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class GenCandidateColocation {
    static HashMap<String, Integer> featureNum = new HashMap<>();
    //产生2阶候选模式及表实例
    public static HashMap<List<String>, List<List<Point>>> genC2(List<String> P1, List<Point> points, double maxDistance) {

        HashMap<List<String>, List<List<Point>>> C2 = new HashMap();
        List<List<String>> F2 = new ArrayList<>();
        //产生2阶候选模式
        for (int i = 0; i < P1.size() - 1; i++) {
            for (int j = i + 1; j < P1.size(); j ++) {
                List<String> combinedElements = new ArrayList<>();
                combinedElements.add(P1.get(i));
                combinedElements.add(P1.get(j));
                F2.add(combinedElements);
            }
        }

        //产生2阶表实例
        for (List<String> F : F2) {
            List<List<Point>> instances = new ArrayList<>();
            for (int i = 0; i < points.size() - 1; i++) {
                for (int j = i + 1; j < points.size(); j++) {
                    if (Objects.equals(points.get(i).feature, F.get(0)) && Objects.equals(points.get(j).feature, F.get(1))) {
                        double distance = CalculateDistance.calculateDistance(points.get(i), points.get(j));
                        if (distance < maxDistance) {
                            List<Point> instance = new ArrayList<>();
                            instance.add(points.get(i));
                            instance.add(points.get(j));
                            instances.add(instance);
                        }
                    }
                }
            }
            C2.put(F, instances);
        }

        //计算每个特征下有多少实例
//        HashMap<String, Integer> featureNum = new HashMap<>();
        for (Point point : points) {
            String key = point.feature;
            if (featureNum.containsKey(key)) {
                int value = featureNum.get(key);
                featureNum.put(key, value + 1);
            }
            else{
                featureNum.put(key, 1);
            }
        }

        CalculatePrev calPrev = new CalculatePrev();
        HashMap<List<String>, List<List<Point>>> P2T2 = calPrev.calculatePrev(C2, featureNum, 0.05);

        return P2T2;
    }

    public static HashMap<List<String>, List<List<Point>>> genCkTk(HashMap<List<String>, List<List<Point>>> C2,
                                                                    List<Point> points, double maxDistance) {

//        HashMap<List<String>, List<List<String>>> Ck = new HashMap<>();
        List<List<String>> Fk = new ArrayList<>();
        HashMap<List<String>, List<List<Point>>> CkTk = new HashMap();

        //将Ck所有的键（即k阶模式）放到列表中
        List<List<String>> keysList = new ArrayList<>(C2.keySet());
        for (int i = 0; i < keysList.size() - 1; i++) {
            for (int j = i + 1; j < keysList.size(); j ++) {
                //得到k阶模式的前k-1个特征
                List<String> preFea1 = keysList.get(i).subList(0, keysList.get(i).size()-1);
                List<String> preFea2 = keysList.get(j).subList(0, keysList.get(j).size()-1);
                if (Objects.equals(preFea1, preFea2)) {
                    //[H, M] + [H, O] -> [H, M, O]
                    List<String> preFea1Copy = new ArrayList<>(preFea1);
                    preFea1Copy.add(keysList.get(i).get(keysList.get(i).size() - 1));
                    preFea1Copy.add(keysList.get(j).get(keysList.get(j).size() - 1));
//                    preFea1.add(keysList.get(i).get(keysList.get(i).size() - 1));
//                    preFea1.add(keysList.get(j).get(keysList.get(j).size() - 1));
                    Fk.add(preFea1Copy);
                }
            }
        }

        //Apriori剪枝,先不做
        for (List<String> Fki : Fk) {

        }

        //产生k阶表实例

        for (List<String> F : Fk) {
            List<List<Point>> instances = new ArrayList<>();
            for (int i = 0; i < C2.size() - 1; i++) {
                for (int j = i + 1; j < C2.size(); j++) {
                    List<String> preFea1 = keysList.get(i).subList(0, keysList.get(i).size()-1);
                    List<String> preFea2 = keysList.get(j).subList(0, keysList.get(j).size()-1);
                    if (Objects.equals(preFea1, preFea2)) {
                        if (Objects.equals(preFea1, F.subList(0, F.size() - 2))) {
//                            List<List<Point>> k = C2.get(keysList.get(i));
                            for (int m = 0; m < C2.get(keysList.get(i)).size(); m++) {
                                for (int n = 0; n < C2.get(keysList.get(j)).size(); n++) {
                                    if (Objects.equals(C2.get(keysList.get(i)).get(m).subList(0, C2.get(keysList.get(i)).get(m).size() - 1)
                                            , C2.get(keysList.get(j)).get(n).subList(0, C2.get(keysList.get(j)).get(n).size() - 1))) {
                                        double distance = CalculateDistance.calculateDistance(C2.get(keysList.get(i)).get(m).get(C2.get(keysList.get(i)).get(m).size() - 1),
                                                C2.get(keysList.get(j)).get(n).get(C2.get(keysList.get(j)).get(n).size() - 1));
                                        if (distance < maxDistance) {
                                            List<Point> instance = new ArrayList<>();
                                            for (int l = 0; l < C2.get(keysList.get(j)).get(n).size() - 1; l++) {
                                                instance.add(C2.get(keysList.get(i)).get(m).get(l));
                                            }
//                                            instance.add(C2.get(keysList.get(i)).get(m).subList(0, C2.get(keysList.get(j)).get(n).size() - 1));
                                            instance.add(C2.get(keysList.get(i)).get(m).get(C2.get(keysList.get(i)).get(m).size() - 1));
                                            instance.add(C2.get(keysList.get(j)).get(n).get(C2.get(keysList.get(j)).get(n).size() - 1));
                                            instances.add(instance);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            CkTk.put(F, instances);
        }


//        List<List<Point>> instances = new ArrayList<>();
//        List<String> F = new ArrayList<>();
//        for (int i = 0; i < C2.size() - 1; i++) {
//            for (int j = i + 1; j < C2.size(); j++) {
//                List<String> preFea1 = keysList.get(i).subList(0, keysList.get(i).size()-1);
//                List<String> preFea2 = keysList.get(j).subList(0, keysList.get(j).size()-1);
//                if (Objects.equals(preFea1, preFea2)) {
//                    for (int m = 0; m < C2.get(keysList.get(i)).size(); m++) {
//                        for (int n = 0; n < C2.get(keysList.get(j)).size(); n++) {
//                            if (C2.get(keysList.get(i)).get(m).subList(0, C2.get(keysList.get(i)).get(m).size() - 1)
//                                    == C2.get(keysList.get(j)).get(n).subList(0, C2.get(keysList.get(j)).get(n).size() - 1)) {
//                                double distance = CalculateDistance.calculateDistance(C2.get(keysList.get(i)).get(m).get(C2.get(keysList.get(i)).get(m).size()),
//                                        C2.get(keysList.get(j)).get(n).get(C2.get(keysList.get(j)).get(n).size()));
//                                if (distance < maxDistance) {
//                                    List<Point> instance = new ArrayList<>();
//                                    instance.add((Point) C2.get(keysList.get(i)).get(m).subList(0, C2.get(j).get(n).size() - 1));
//                                    instance.add(C2.get(keysList.get(i)).get(m).get(C2.get(i).get(m).size()));
//                                    instance.add(C2.get(keysList.get(j)).get(n).get(C2.get(j).get(n).size()));
//                                    instances.add(instance);
//
//                                    F.add(keysList.get(i).subList(0, keysList.get(i).size()).toString());
//                                    F.add(keysList.get(j).get(keysList.get(j).size()));
//                                    PkTk.put(F, instances);
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }

        CalculatePrev calPrev = new CalculatePrev();
        HashMap<List<String>, List<List<Point>>> PkTk = calPrev.calculatePrev(CkTk, featureNum, 0.05);

        return PkTk;

    }
}
