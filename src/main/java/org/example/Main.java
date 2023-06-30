package org.example;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String json = readString("data.json");
        List<Employee> list = jsonToList(json);

        for (Employee e: list){
            System.out.println(e);
        }
    }

    public static String readString(String fileName){
        String answer = "";
        JSONParser parser = new JSONParser();
        try{
            Object obj = parser.parse(new FileReader(fileName));
            JSONArray jsonArray = (JSONArray) obj;
            answer = jsonArray.toJSONString();
        } catch (IOException | ParseException e){
            e.printStackTrace();
        }
        return answer;
    }

    public static List<Employee> jsonToList(String json){
        List<Employee> list = new ArrayList<>();
        List<String> test = new ArrayList<>();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        int i = json.indexOf("}");
        test.add(json.substring(1,i+1));
        test.add(json.substring(i+2,json.length()-1));
        for (String a: test){
            list.add(gson.fromJson(a,Employee.class));
        }
        return list;
    }
}