package com.weka.demo.util;


import weka.clusterers.SimpleKMeans;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by changlu on 18/9/19.
 */
public class DataSetUtil {


    public static Instances getInstances(List<String> originList){
        List<String> allWords = new ArrayList<>();
        originList.forEach(s -> allWords.addAll(Arrays.asList(s.split(""))));
        List<String> words = allWords.stream().distinct().collect(Collectors.toList());
        ArrayList<Attribute> attributes = new ArrayList<Attribute>(words.size());
        for (String s : words){
            Attribute attribute = new Attribute(s);
            attributes.add(attribute);
        }
        Instances instances = new Instances("vector", attributes, 0);
        for (String origin : originList){
            Instance inst = new DenseInstance(words.size());
            int i = 0;
            for (String word : words){
                if (origin.contains(word)){
                    inst.setValue(i,1);
                }else {
                    inst.setValue(i,0);
                }
                i++;
            }
            instances.add(inst);
        }
        return instances;

    }


}
