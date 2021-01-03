
package proc;

import javax.swing.JFrame;
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
    
    public static void clearPlug(JFrame frame) {
        frame.getContentPane().removeAll();
    }
    
    public static void revalidate(JFrame frame) {
        frame.revalidate();
    }
    
    public static void repaint(JFrame frame) {
        frame.repaint();
    }
    
    public static void quit(JFrame frame) {
        frame.dispose();
    }
    
}
