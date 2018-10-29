package com.weka.demo.util;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.springframework.util.ResourceUtils;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
import weka.core.ManhattanDistance;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by changlu on 9/20/18.
 */
public class DataUtilTest {

    @Test
    public void testGetInstances() throws Exception {
        List<String> originList = new ArrayList<>();
        File file = ResourceUtils.getFile("classpath:advice.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);
        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            try {
                originList.add(row.getCell(0).getStringCellValue());
            }catch (Exception e){
                continue;
            }
        }
        final Instances instances = DataSetUtil.getInstances(originList);
//        System.out.println(instances);
//        System.out.println(DataSetUtil.getInstances(originList));
        SimpleKMeans simpleKMeans = new SimpleKMeans();
        simpleKMeans.setDistanceFunction(new ManhattanDistance());
        int clusterNum = 20;
        simpleKMeans.setNumClusters(clusterNum);
        simpleKMeans.buildClusterer(instances);
//        System.out.println(simpleKMeans.toString());
        Map<Integer,List<String>> result = new HashMap<>();
        for (int i = 0; i < clusterNum; i++) {
            result.put(i, new ArrayList<>());
        }
        for (int i = 0; i < instances.size(); i++) {
//            System.out.print(instances.get(i) + "  ");
//            System.out.println(simpleKMeans.clusterInstance(instances.get(i)));
            result.get(simpleKMeans.clusterInstance(instances.get(i))).add(originList.get(i));
        }
        for (int i = 0; i < clusterNum; i++) {
            System.out.println("第"+ (i+1) + "类:");
            result.get(i).forEach(s -> System.out.println("        " + s));
        }
    }
}
