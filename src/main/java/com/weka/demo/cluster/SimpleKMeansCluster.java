package com.weka.demo.cluster;

import com.weka.demo.util.DataSetUtil;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by changlu on 9/21/18.
 */
public class SimpleKMeansCluster {

    public static Map<Integer,List<String>> clusterStringList(int clusterNum , List<String> originList) throws Exception {
        SimpleKMeans simpleKMeans = new SimpleKMeans();
        simpleKMeans.setNumClusters(clusterNum);
        Instances instances = DataSetUtil.getInstances(originList);
        simpleKMeans.buildClusterer(instances);
        Map<Integer,List<String>> result = new HashMap<>();
        for (int i = 0; i < clusterNum; i++) {
            result.put(i, new ArrayList<>());
        }
        for (int i = 0; i < instances.size(); i++) {
            result.get(simpleKMeans.clusterInstance(instances.get(i))).add(originList.get(i));
        }
        return result;
    }

}
