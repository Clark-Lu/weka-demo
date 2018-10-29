package com.weka.demo.data;

import org.junit.Test;
import org.springframework.util.ResourceUtils;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.json.JSONInstances;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by changlu on 9/20/18.
 */
public class DataTest {

    @Test
    public void testData() throws IOException {
        File file  = ResourceUtils.getFile("classpath:data.arff");
        ArffLoader arffLoader = new ArffLoader();
        arffLoader.setFile(file);
        StringBuffer stringBuffer = new StringBuffer();
        JSONInstances.toJSON(arffLoader.getDataSet()).toString(stringBuffer);
        System.out.println(stringBuffer.toString());

    }

}
