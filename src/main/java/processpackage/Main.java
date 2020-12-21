/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processpackage;

/**
 *
 * @author kazen
 */
public class Main {
    public static MainFrame mainFrame;
    
    public static GamePlay gamePlay;
    public static Shop shop;
    
    public static void main(String[] args) {
        mainFrame = new MainFrame();
        
        gamePlay = new GamePlay();
        shop = new Shop();
        
        
        ModuleManager.plugIn(mainFrame, gamePlay);
        mainFrame.setVisible(true);
        
    }
}
