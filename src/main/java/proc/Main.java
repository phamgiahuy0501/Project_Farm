package proc;

import feature.MainFrame;
import feature.GamePlay;
import feature.Shop;
import feature.Menu;
import feature.Basket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import proc_data.JsData;
import proc_data.SqlData;
import proc_data.SqlDataFarm;

public class Main {

    public static MainFrame mainFrame;

    public static Menu menu;
    public static GamePlay gamePlay;
    public static Shop shop;
    public static Basket basket;
    
    private static final String JSONDATA_ID_PLANT = "jsdat\\id\\jsplant.json";
    private static final String JSONDATA_ID_FERTILIZER = "jsdat\\id\\jsfer.json";
    private static final String JSONDATA_TIME_PLANT = "jsdat\\id\\jstimeplant.json";
    public static void main(String[] args) {
        JsData.loadAll(JSONDATA_ID_PLANT, JSONDATA_ID_FERTILIZER, JSONDATA_TIME_PLANT);

        SqlData.initConnection("jdbc:mysql://localhost:3306/farm_game", "root", "050120");

//        mainFrame = new MainFrame();
//        
//        menu = new Menu();
//        gamePlay = new GamePlay();
//        shop = new Shop();
//        basket = new Basket();
//        
//        ModuleManager.plugIn(mainFrame, menu);
//        mainFrame.setVisible(true);
    }
}
