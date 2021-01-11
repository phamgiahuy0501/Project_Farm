/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.SQLData;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import proc.ModuleManager;
import proc_data.JsData;
import proc_data.SqlDataItem;

/**
 *
 * @author kazen
 */
public class Item extends MyLabel {
    private final int brand;
    private final int type;
    final JLabel amountLable = new JLabel();
    
    public Item() {
        super();
        brand = -1;
        type = -1;
    }
    
    public Item(int brand, int type, JLabel label, Point point) {
        super(label, point);
        this.brand = brand;
        this.type = type;
        
        int amount = SqlDataItem.getAmountItem(brand, type);
        
        label.setLayout(new FlowLayout(FlowLayout.CENTER));
        label.setIcon(new ImageIcon(JsData.getPathItemBag(brand, type)));
        
        amountLable.setPreferredSize(new Dimension(20,13));
        amountLable.setVerticalAlignment(SwingConstants.CENTER);
        amountLable.setHorizontalAlignment(SwingConstants.CENTER);
        amountLable.setOpaque(true);
        amountLable.setText(String.valueOf(amount));
        addSubLable(amountLable);
    }
    
    public int getBrand() {
        return brand;
    }
    
    public int getType() {
        return type;
    }
    
    public int getAmount() {
        return SqlDataItem.getAmountItem(brand, type);
    }
    
    public void updateAmount(int amount) {
        SqlDataItem.updateAmountItem(brand, type, amount);
    }

    public void highlightProcess() {
        if (label.isOpaque()) {
            unhighlight();
        } else {
            highlight();
        }
    }
    
    public void highlight() {
        label.setBackground(Color.lightGray);
        label.setOpaque(true);
        
        ModuleManager.repaint(label);
    }

    public void unhighlight() {
        label.setOpaque(false);
        
        ModuleManager.repaint(label);
    }
}
