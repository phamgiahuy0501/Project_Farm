/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

import feature.GamePlay;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import proc_data.JsData;

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
        timeFinish = 0;
        type = 0;
        process = null;
    }

    public Ground(int type, long timeFinish, JLabel label, Point point) {
        super(label, point);
        this.type = type;
        this.timeFinish = timeFinish;
        label.setLayout(new FlowLayout(FlowLayout.CENTER));
        addTimeLable(timeLabel);
        this.timeLabel.setVisible(false);

        process = new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        if (!processPause) {
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
        label.setVisible(!processPause);
    }
    
    public void resumeProcess() {
        processPause = false;
        label.setVisible(!processPause);
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

    public long getTimeFinish() {
        return timeFinish;
    }

    public int getType() {
        return type;
    }

    public void setEmpty() {
        type = 0;
        timeFinish = 0;
    }
}
