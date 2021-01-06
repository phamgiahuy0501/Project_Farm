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
public class Ground extends MyLabel{
    int type;
    long timeFinish;
    
    public Ground() {
        super();
        timeFinish = 0;
        type = 0;
    }

    public Ground(int type, long timeFinish, JLabel label, Point point) {
        super(label, point);
        this.type = type;
        this.timeFinish = timeFinish;
    }

    public long getTimeFinish() {
        return timeFinish;
    }
    
    public int getType() {
        return type;
    }
}
