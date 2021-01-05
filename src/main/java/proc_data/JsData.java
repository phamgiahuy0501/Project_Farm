/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proc_data;

import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author kazen
 */
public class JsData {
    public static JSONArray listPlant = new JSONArray();
    public static JSONArray listFer = new JSONArray();
    public static JSONObject timePlant = new JSONObject();
    
    
    public static void loadTimePlant(String path){
        timePlant.clear();
        
        JSONParser parser = new JSONParser();
        try (FileReader file = new FileReader(path)){
            timePlant = (JSONObject)parser.parse(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void loadListIDPlant(String path){
        listPlant.clear();
        
        JSONParser parser = new JSONParser();
        try (FileReader file = new FileReader(path)){
            listPlant = (JSONArray)parser.parse(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void loadListIDFer(String path) {
        listFer.clear();
        
        JSONParser parser = new JSONParser();
        try (FileReader file = new FileReader(path)){
            listFer = (JSONArray)parser.parse(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
