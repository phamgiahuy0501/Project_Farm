package proc;

import feature.GamePlay;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ModuleManager {

    public static void plugIn(JFrame frame, JPanel panel) {
        frame.add(panel);
    }

    public static void plugIn(JFrame frame, JPanel panel, int pos) {
        frame.add(panel, pos);
    }

    public static void plugOut(JFrame frame, JPanel panel) {
        frame.remove(panel);
    }

    public static void resumeProcess(GamePlay gamePlay) {
        if (!gamePlay.isListGroundNull()) {
            gamePlay.resumeProcess();
        }
    }
    
    public static int getMoney(GamePlay gamePlay) {
        return gamePlay.getMoney();
    }
    
    public static void updateMoney(GamePlay gamePlay, int amount) {
        gamePlay.updateMoney(amount);
    }

    public static void clearPlug(JFrame frame) {
        frame.getContentPane().removeAll();
    }
    
    public static void revalidate(JFrame frame) {
        frame.revalidate();
    }
    
    public static void revalidate(JPanel panel) {
        panel.revalidate();
    }
    
    public static void repaint(JFrame frame) {
        frame.repaint();
    }
    
    public static void repaint(JPanel panel) {
        panel.repaint();
    }
    
    public static void repaint(JLabel label) {
        label.repaint();
    }

    public static void quit(JFrame frame) {
        frame.dispose();
        System.exit(1);
    }

}
