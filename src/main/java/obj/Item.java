/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import proc_data.JsData;

/**
 *
 * @author kazen
 */
//  brand = 0 - seed
// brand = 1 - fertilizer
public class Item extends MyLabel {
    int brand;
    int type;
    
    public Item() {
        super();
        brand = -1;
        type = -1;
    }

    public Item(int brand, int type ,JLabel label, Point point) {
        super(label, point);
        this.type = type;
        this.brand = brand;
        
        label.setIcon(new ImageIcon(JsData.getPathCard(brand, type)));
       
    }
    
    public int getType() {
        return type;
    }
}
