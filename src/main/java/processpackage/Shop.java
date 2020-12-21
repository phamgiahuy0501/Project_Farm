/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processpackage;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author kazen
 */
public class Shop extends JPanel {
    
    JLabel background = new JLabel();
    
    static final String PATH_BACKGROUND = "D:\\Project_Farm\\img\\Background\\Background-shop.png";

    Shop() {
        setMaximumSize(new Dimension(800, 600));
        setMinimumSize(new Dimension(800, 600));
        setSize(new Dimension(800, 600));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        background.setIcon(new ImageIcon(PATH_BACKGROUND));
        
        add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }

}
