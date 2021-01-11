package proc;

import feature.Bag;
import feature.MainFrame;
import feature.GamePlay;
import feature.Shop;
import feature.Menu;
import feature.Basket;
import proc_data.JsData;
import proc_data.SqlData;

public class Main {

    public static MainFrame mainFrame;

    public static Menu menu;
    public static GamePlay gamePlay;
    public static Shop shop;
    public static Bag bag;
    public static Basket basket;

    private static final String JSONDATA_ID_PLANT = "jsdat\\id\\plant.json";
    private static final String JSONDATA_ID_FERTILIZER = "jsdat\\id\\fer.json";
    private static final String JSONDATA_TIME_PLANT = "jsdat\\id\\timeplant.json";
    private static final String JSONDATA_PATH_GROUND = "jsdat\\path\\game\\plant.json";
    private static final String JSONDATA_PATH_CARD = "jsdat\\path\\shop\\card.json";
    private static final String JSONDATA_PRICE = "jsdat\\id\\price.json";
    private static final String JSONDATA_PATH_ITEM_BAG = "jsdat\\path\\bag\\item.json";
    private static final String JSONDATA_PATH_ITEM_BASKET = "jsdat\\path\\basket\\item.json";
    
    private static final String SQL_HOST = "jdbc:mysql://localhost:3306/farm_game";
    private static final String SQL_USER = "root";
    private static final String SQL_PASS = "050120";

    public static void main(String[] args) {
        loadData();
        loadComponent();
        
        ModuleManager.plugIn(mainFrame, gamePlay);
        mainFrame.setVisible(true);
    }

    private static void loadData() {
        JsData.loadAll(JSONDATA_ID_PLANT, JSONDATA_ID_FERTILIZER, JSONDATA_TIME_PLANT, JSONDATA_PATH_GROUND, JSONDATA_PATH_CARD, JSONDATA_PRICE, JSONDATA_PATH_ITEM_BAG, JSONDATA_PATH_ITEM_BASKET);
        SqlData.initConnection(SQL_HOST, SQL_USER, SQL_PASS);
    }
    
    private static void loadComponent() {
        mainFrame = new MainFrame();
        menu = new Menu();
        gamePlay = new GamePlay();
        shop = new Shop();
        bag = new Bag();
        basket = new Basket();
    }
}
