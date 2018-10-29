package com.weka.demo.cluster;

import org.junit.Test;
import org.springframework.util.ResourceUtils;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.CSVLoader;
import weka.core.converters.JSONLoader;
import weka.core.json.JSONInstances;
import weka.core.json.JSONNode;

import java.io.File;
import java.io.IOException;

/**
 * Created by changlu on 9/20/18.
 */
public class SimpleKMeansTest {

    @Test
    public void test() throws Exception {
        SimpleKMeans simpleKMeans = new SimpleKMeans();
        File file  = ResourceUtils.getFile("classpath:data.arff");
        ArffLoader arffLoader = new ArffLoader();
        arffLoader.setFile(file);
        simpleKMeans.buildClusterer(arffLoader.getDataSet());
        System.out.println(simpleKMeans.toString());
    }
}
