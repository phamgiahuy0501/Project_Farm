package proc;

import feature.MainFrame;
import feature.GamePlay;
import feature.Shop;
import feature.Menu;
import feature.Basket;
import java.sql.ResultSet;
import proc_data.JsData;
import proc_data.SqlData;
import proc_data.SqlDataFarm;

public class Main {

    public static MainFrame mainFrame;

    public static Menu menu;
    public static GamePlay gamePlay;
    public static Shop shop;
    public static Basket basket;

    public static void main(String[] args) {
        JsData.loadListIDPlant("jsdat\\jsplant.json");
        JsData.loadListIDFer("jsdat\\jsfer.json");
        JsData.loadTimePlant("jsdat\\jstimeplant.json");

        SqlData.initConnection("jdbc:mysql://localhost:3306/farm_game", "root", "050120");
        SqlDataFarm.loadAllGround();
        SqlDataFarm.display();
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
