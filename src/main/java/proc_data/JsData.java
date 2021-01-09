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

    private static JSONArray listPlant = new JSONArray();
    private static JSONArray listFer = new JSONArray();
    private static JSONObject timePlant = new JSONObject();
    private static JSONObject pathsGround = new JSONObject();
    private static JSONObject pathsCard = new JSONObject();

    public static void loadAll(String pathIDPlant, String pathIDFer, String pathTimePlant, String pathGround, String pathCard) {
        loadListIDPlant(pathIDPlant);
        loadListIDFer(pathIDFer);
        loadTimePlant(pathTimePlant);
        loadPathsGround(pathGround);
        loadPathsCard(pathCard);
    }

    public static void loadTimePlant(String path) {
        timePlant.clear();

        JSONParser parser = new JSONParser();
        try ( FileReader file = new FileReader(path)) {
            timePlant = (JSONObject) parser.parse(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadListIDPlant(String path) {
        listPlant.clear();

        JSONParser parser = new JSONParser();
        try ( FileReader file = new FileReader(path)) {
            listPlant = (JSONArray) parser.parse(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadListIDFer(String path) {
        listFer.clear();

        JSONParser parser = new JSONParser();
        try ( FileReader file = new FileReader(path)) {
            listFer = (JSONArray) parser.parse(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadPathsGround(String path) {
        JSONParser parser = new JSONParser();
        try ( FileReader file = new FileReader(path)) {
            pathsGround = (JSONObject) parser.parse(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadPathsCard(String path) {
        JSONParser parser = new JSONParser();
        try ( FileReader file = new FileReader(path)) {
            pathsCard = (JSONObject) parser.parse(file);
        } catch (Exception e) {
        }
    }

    public static String getPathGround(int type, int stage) {
        if (type != 0) {
            return ((JSONObject) pathsGround.get(getPlant(type))).get("Stage " + stage).toString();
        }
        return pathsGround.get(getPlant(type)).toString();
    }

    public static String getPlant(int index) {
        return listPlant.get(index).toString();
    }

    public static String getFertilizer(int index) {
        return listFer.get(index).toString();
    }

    public static String getPathCard(int brand, int type) {
        if (brand == 0) {// 0 = seed 
            return ((JSONObject) pathsCard.get("Seed")).get(listPlant.get(type)).toString();
        }
        // 1 = fertilizer
        return ((JSONObject) pathsCard.get("Fertilizer")).get(listFer.get(type)).toString();
    }

    public static int getTimePlant(String plantName) {
        return Integer.parseInt(timePlant.get(plantName).toString());
    }
}
