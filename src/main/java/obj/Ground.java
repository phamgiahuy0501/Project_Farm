/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

import feature.GamePlay;
import java.awt.FlowLayout;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import org.json.simple.JSONObject;
import proc.ModulePlanting;
import proc_data.JsData;
import proc_data.SqlData;
import proc_data.SqlDataFarm;
import proc_data.SqlDataItem;

/**
 *
 * @author kazen
 */
public class Ground extends MyLabel {

    int type;
    long timeFinish;
    Thread process;
    final JLabel timeLabel = new JLabel();
    public boolean processPause = false;

    public Ground() {
        super();
        process = null;
    }

    public Ground(int type, long timeFinish, JLabel label, Point point) {
        super(label, point);
        label.setLayout(new FlowLayout(FlowLayout.CENTER));
        addSubLable(timeLabel);
        this.timeLabel.setVisible(false);
        
        this.type = type;
        this.timeFinish = timeFinish;
        
        process = new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        if (!processPause) {
                            System.out.println(type);
                            label.setIcon(new ImageIcon(JsData.getPathGround(type, GamePlay.calStage(timeFinish, type))));
                            updateTimeLabel(GamePlay.calGrowthPercent(timeFinish, type));
                        }

                        sleep(500);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        process.start();
    }

    public void pauseProcess() {
        processPause = true;
        visible(!processPause);
    }
    
    public void resumeProcess() {
        processPause = false;
        visible(!processPause);
    }
    
    public void plant() {
        if (!ModulePlanting.isPlanting && !isFree()){
            return;
        }
    }
    
    public void harvest() {
        if (ModulePlanting.isPlanting && isFree() && GamePlay.calGrowthPercent(timeFinish, type) < 100) {
            return;
        }
        
        int amountHarvested = random(3, 5);
        int amount = SqlDataItem.getAmountItem(3, type);
        SqlDataItem.updateAmountItem(2, type, amountHarvested + amount);
        
        type = 0;
    }
    
    private boolean isFree() {
        return type == 0;
    }
    
    private int random(int min, int max) {
        Random rand = new Random();
        return min + rand.nextInt(max - min + 1);
    }
    
    public void hideTimeLabel() {
        timeLabel.setVisible(false);
        label.revalidate();
        label.repaint();
    }

    public void updateTimeLabel(float percent) {
        timeLabel.setText(String.format("%.2f", percent) + "%");
        timeLabel.repaint();
    }

    public void showTimeLabel() {
        timeLabel.setVisible(true);
        label.revalidate();
        label.repaint();
    }
}
