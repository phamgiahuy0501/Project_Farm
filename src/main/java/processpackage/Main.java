
package processpackage;

public class Main {
    public static MainFrame mainFrame;
    
    public static Menu menu;
    public static GamePlay gamePlay;
    public static Shop shop;
    public static Basket basket;
    
    public static void main(String[] args) {
        mainFrame = new MainFrame();
        
        menu = new Menu();
        gamePlay = new GamePlay();
        shop = new Shop();
        basket = new Basket();
        
        ModuleManager.plugIn(mainFrame, menu);
        mainFrame.setVisible(true);
    }
}
