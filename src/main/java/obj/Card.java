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
public class Card extends MyLabel {
    int type;

    public Card() {
        super();
    }

    public Card(int type, JLabel label, Point point) {
        super(label, point);
        this.type = type;
    }
    
    public int getType() {
        return type;
    }
}
