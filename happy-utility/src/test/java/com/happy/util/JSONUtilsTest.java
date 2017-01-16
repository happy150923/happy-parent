package com.happy.util;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.*;

/**
 * @author chengxia
 * @version 2017-01-10 18:40
 */
public class JSONUtilsTest {

    @Test
    public void listToJsonTest() {
        List list = new ArrayList<>();
        list.add("4");
        list.add("hello");
        list.add("hello5");
        System.out.println(JSONUtils.objToJSON(list));
    }

    @Test
    public void mapToJsonTest() {
        Map map = new HashMap<>();
        map.put(1, 10);
        map.put(2, 20);
        map.put(3, 30);
        map.put(4, 40);
        System.out.println(JSONUtils.objToJSON(map));
    }

    @Test
    public void setToJsonTest() throws Exception {
        Set set = new HashSet<>();
        set.add(new Timestamp(System.currentTimeMillis()));
        Thread.sleep(3);
        set.add(new Timestamp(System.currentTimeMillis()));
        Thread.sleep(3);
        set.add(new Timestamp(System.currentTimeMillis()));
        System.out.println(JSONUtils.objToJSON(set, null));
    }

    @Test
    public void jsonToListTest() {
        String string = "[\"4\",\"hello\",\"hello5\"]";
        List list = JSONUtils.JSONToObj(string, List.class);
        System.out.println(list.get(2));
    }

    @Test
    public void jsonToObjTypeReferenceTest(){
        String string = "[\"2017-01-10 18:57:36\",\"2017-01-10 18:57:36\",\"2017-01-10 18:57:36\"]";
        List<Timestamp> timestamps = JSONUtils.JSONToObj(string, new TypeReference<List<Timestamp>>() {});
        System.out.println(timestamps.get(0).getClass());
    }
    
    @Test
    public void jsonToMapTest() {
        String string = "{\"1\":10,\"2\":20,\"3\":30,\"4\":40}";
        Map map = JSONUtils.JSONToObj(string, Map.class);
        System.out.println(map.get("4"));
        System.out.println(map.get(3));
        System.out.println(map.get("3"));
    }

    @Test
    public void jsonToSetTest() {
        String string = "[\"2017-01-10 18:57:36\",\"2017-01-10 18:57:36\",\"2017-01-10 18:57:36\"]";
        String json = "[1484046164243,1484046164240,1484046164236]";
        List<Timestamp> list = JSONUtils.JSONToObj(json, List.class);
        Timestamp timestamp = list.get(0);
        System.out.println(timestamp);
//        Set<Timestamp> set = JSONUtils.JSONToObj(json, Set.class);
//        System.out.println(set.size());
//        for (Timestamp timestamp2 : set) {
//            System.out.println(timestamp2);
//        }
    }

    @Test
    public void timeSerialTest() {
        TimeTest timeTest = new TimeTest();
        timeTest.setDate(new Date());
        timeTest.setTimestamp(new Timestamp(System.currentTimeMillis() + 3));
        System.out.println(JSONUtils.objToJSON(timeTest));
    }

    @Test
    public void timeDeserialTest() {
        String string = "{\"timestamp\":\"2017-01-11 11:01:33\",\"date\":\"2017-01-11 11:01:33\"}";
        TimeTest timeTest = JSONUtils.JSONToObj(string, TimeTest.class);
        System.out.println(timeTest);
    }

    @Test
    public void timeListSerialTest() {
        long millis = System.currentTimeMillis();
        TimeTest timeTest = new TimeTest();
        timeTest.setDate(new Date(millis));
        timeTest.setTimestamp(new Timestamp(millis + 3000));

        List<TimeTest> timeTests = new ArrayList<>();
        timeTests.add(timeTest);

        timeTest = new TimeTest();
        timeTest.setDate(new Date(millis + 6000));
        timeTest.setTimestamp(new Timestamp(millis + 9000));
        timeTests.add(timeTest);
        System.out.println(JSONUtils.objToJSON(timeTests));
    }

    @Test
    public void timeListDeserialTest() {
        String string = "[{\"timestamp\":\"2017-01-11 11:09:50\",\"date\":\"2017-01-11 11:09:47\"},{\"timestamp\":\"2017-01-11 11:09:56\",\"date\":\"2017-01-11 11:09:53\"}]";
        List<TimeTest> timeTests = JSONUtils.JSONToObj(string, new TypeReference<List<TimeTest>>() {});
        System.out.println(timeTests.get(0).getDate());
        System.out.println(timeTests.get(0).getClass());
    }
}

class TimeTest {
    Timestamp timestamp;
    Date date;

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return date + " " + timestamp;
    }
}
