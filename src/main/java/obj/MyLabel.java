/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

import javax.swing.JLabel;

/**
 *
 * @author kazen
 */
public class MyLabel {
    JLabel label;
    Point point;
    
    public MyLabel() {
        label = null;
        point = null;
    }
    
    public MyLabel(JLabel label, Point point) {
        this.label = label;
        this.point = point;
    }
    
    public Point getPoint() {
        return point;
    }
    
    public void setPoint(Point point) {
        this.point = point;
    }
    
    public JLabel getLabel() {
        return label;
    }
    
    public void setLabel(JLabel label) {
        this.label = label;
    }
}