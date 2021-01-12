/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proc_data;

import java.io.FileReader;
import java.util.List;
import jdk.nashorn.api.scripting.JSObject;
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
    private static JSONObject priceAll = new JSONObject();
    private static JSONObject pathsItemBag = new JSONObject();
    private static JSONObject pathsItemBasket = new JSONObject();

    public static void loadAll(String pathIDPlant, String pathIDFer, String pathTimePlant, String pathGround, String pathCard, String pathPrice, String pathItemBag, String pathItemBasket) {
        
        loadListIDPlant(pathIDPlant);
        loadListIDFer(pathIDFer);
        loadTimePlant(pathTimePlant);
        loadPathsGround(pathGround);
        loadPathsCard(pathCard);
        loadPriceAll(pathPrice);
        loadPathsItemBag(pathItemBag);
        loadPathsItemBasket(pathItemBasket);
    }
    
    
    private static void loadPriceAll(String path) {
        JSONParser parser = new JSONParser();
        try ( FileReader file = new FileReader(path)) {
            priceAll = (JSONObject) parser.parse(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadTimePlant(String path) {
        JSONParser parser = new JSONParser();
        try ( FileReader file = new FileReader(path)) {
            timePlant = (JSONObject) parser.parse(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadListIDPlant(String path) {
        JSONParser parser = new JSONParser();
        try ( FileReader file = new FileReader(path)) {
            listPlant = (JSONArray) parser.parse(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadListIDFer(String path) {
        JSONParser parser = new JSONParser();
        try ( FileReader file = new FileReader(path)) {
            listFer = (JSONArray) parser.parse(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadPathsGround(String path) {
        JSONParser parser = new JSONParser();
        try ( FileReader file = new FileReader(path)) {
            pathsGround = (JSONObject) parser.parse(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadPathsCard(String path) {
        JSONParser parser = new JSONParser();
        try ( FileReader file = new FileReader(path)) {
            pathsCard = (JSONObject) parser.parse(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    private static void loadPathsItemBag(String path) {
        JSONParser parser = new JSONParser();
        try ( FileReader file = new FileReader(path)) {
            pathsItemBag = (JSONObject) parser.parse(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadPathsItemBasket(String path) {
        JSONParser parser = new JSONParser();
        try (FileReader file = new FileReader(path)){
            pathsItemBasket = (JSONObject) parser.parse(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    
    
    public static int getPrice(int brand, int type) {
        if (brand == 0) {
            return Integer.parseInt(((JSONArray) priceAll.get("Seed")).get(type).toString());
        }
        return Integer.parseInt(((JSONArray) priceAll.get("Fertilizer")).get(type).toString());
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

    public static String getPathItemBag(int brand, int type) {
        switch (brand) {
            case 0:
                return ((JSONObject)pathsItemBag.get("Seed")).get(listPlant.get(type)).toString();
            case 1:
                return ((JSONObject)pathsItemBag.get("Fertilizer")).get(listFer.get(type)).toString();
            case 2:
                return ((JSONObject)pathsItemBag.get("Ingredient")).get(listPlant.get(type)).toString();
            default:
                System.out.println("error load paths item");
                return null;
        }
    }
    
    public static int getTimePlant(String plantName) {
        return Integer.parseInt(timePlant.get(plantName).toString());
    }
}
