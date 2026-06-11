package day02.maps;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        // Map maintains SET of keys, hence keys need to be unique
        // Map doesn't belong to Collection and Iterable

        // hashMap doesn't guarantee order
        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("fname", "MS");
        hashMap.put("lname", "Dhoni");
        hashMap.put("team", "CSK");
        hashMap.put("position", "WK");
        System.out.println(hashMap);
        System.out.println(hashMap.get("fname"));
        System.out.println(hashMap.get("lname"));
        System.out.println(hashMap.keySet());       // set of keys
        System.out.println(hashMap.values());       // set of values

        for(String key: hashMap.keySet()){
            System.out.println(key + ": " + hashMap.get(key));
        }
        System.out.println("-------------------------------");
        for(String value: hashMap.values()){
            System.out.println(value);
        }

        System.out.println("-------------------------------------");
        hashMap.keySet().stream().forEach((key)-> System.out.println(key + ": " + hashMap.get(key)));

        System.out.println("----------------------------------------");
        hashMap.values().stream().forEach((value)-> System.out.println(value));

        System.out.println("-----------------------------------------");

        // entry set
        for(Map.Entry<String,String> entry: hashMap.entrySet()){
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        hashMap.entrySet().stream().forEach((entry) -> System.out.println(entry.getKey() + ": " + entry.getValue()));


        // LinkedHashMap guarantee order of insertion
        Map<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        linkedHashMap.put("fname", "Sanju");
        linkedHashMap.put("lnam", "Samson");
        linkedHashMap.put("team", "CSK");
        linkedHashMap.put("position", "WK");
        System.out.println(linkedHashMap);
        System.out.println(linkedHashMap.get("team"));
        System.out.println(linkedHashMap.get("position"));
        linkedHashMap.put("fname", "Chetta");   // value is overwritten
        System.out.println(linkedHashMap);
        System.out.println(linkedHashMap.keySet());
        System.out.println(linkedHashMap.values());
        System.out.println("-----------------------------");
        for(String key: linkedHashMap.keySet()){
            System.out.println(key + ": " + linkedHashMap.get(key));
        }
        System.out.println("---------------------------");
        System.out.println();


        // TreeMap has sorted order
        Map<String, String> treeMap = new TreeMap<String, String>();
        treeMap.put("fname", "Ruturaj");
        treeMap.put("lnam", "Gaikwad");
        treeMap.put("team", "CSK");
        treeMap.put("position", "Batsman");
        System.out.println(treeMap);
        System.out.println(treeMap.get("team"));
        System.out.println(treeMap.get("fname"));


    }
}
