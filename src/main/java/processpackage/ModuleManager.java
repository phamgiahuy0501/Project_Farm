/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processpackage;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author kazen
 */
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
    
    public static void revalidate(JFrame frame) {
        frame.revalidate();
    }
    
    public static void repaint(JFrame frame) {
        frame.repaint();
    }
    
}
