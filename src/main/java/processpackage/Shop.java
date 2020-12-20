/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processpackage;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author kazen
 */
public class Shop {
    JLabel label;
    
    Shop() {
        label = new JLabel();
        label.setIcon(new javax.swing.ImageIcon("D:\\Project_Farm\\img\\Background\\Background-shop.png"));
        label.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                test_function();
            }
        });
    }
    
    void test_function() {
        
    }
    
    JLabel getLabel() {
        return label;
    }
    
}
