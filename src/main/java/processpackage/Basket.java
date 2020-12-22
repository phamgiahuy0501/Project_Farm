/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processpackage;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Basket extends JPanel{
    
    JLabel background = new JLabel();
    JLabel close = new JLabel();
    
    static final String PATH_BACKGROUND = "D:\\Project_Farm\\img\\Background\\Background-basket.png";
    static final String PATH_CLOSE = "D:\\Project_Farm\\img\\Button\\Close-button-2.png";

    public Basket() {
        setMaximumSize(new Dimension(800, 600));
        setMinimumSize(new Dimension(800, 600));
        setSize(new Dimension(800, 600));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        setOpaque(false);
        
        background.setIcon(new ImageIcon(PATH_BACKGROUND));
        close.setIcon(new ImageIcon(PATH_CLOSE));
        
        add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
        add(close, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, -1, -1));
        
        close.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                closeClicked(evt);
            }
        });
    }
    
    private void closeClicked(MouseEvent evt) {
        ModuleManager.plugOut(Main.mainFrame, this);
        
        ModuleManager.revalidate(Main.mainFrame);
        ModuleManager.repaint(Main.mainFrame);
    }
    
}
