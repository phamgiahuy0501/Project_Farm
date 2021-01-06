/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feature;

import obj.Point;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import obj.Ground;
import proc.Main;
import proc.ModuleManager;
import proc_data.JsData;
import proc_data.SqlDataFarm;

/**
 *
 * @author kazen
 */
public class GamePlay extends JPanel {

    static final int STEP_X = 100;
    static final int STEP_Y = 65;
//    static final int NUMBER_GROUND = 8;
    static final int NUMBER_GROUNDINROW = 4;
    static final int SIZE_GROUND_X = 100;
    static final int SIZE_GROUND_Y = 115;
    
    static final Point START = new Point(470, 90);
//    static final Point START_ROW_2 = new Point(610, 170);
//    
    static final int DISTANCE_ROW_X = 140;
    static final int DISTANCE_ROW_Y = 80;
    /* PATH */
 /* BACK GROUND */
    static final String PATH_BACKGROUND = "img\\game\\background\\Farm-ground.png";
    /* END BACK GROUND */

 /* ICON */
    static final String PATH_BAG = "img\\game\\icon\\Bag-icon.png";
    static final String PATH_SHOP = "img\\game\\icon\\Shop-icon.png";
    static final String PATH_OPEN_BASKET = "img\\game\\icon\\Open-left-icon.png";
    static final String PATH_VOLUME = "img\\game\\icon\\Volume-icon.png";
    static final String PATH_VOLUME_DARKER = "img\\game\\icon\\Volume-darker-icon.png";
    static final String PATH_BACK = "img\\game\\icon\\Back-icon.png";
    /* END ICON */

    JLabel background = new JLabel();
    JLabel bag_icon = new JLabel();
    JLabel shop_icon = new JLabel();
    JLabel open_basket = new JLabel();
    JLabel back = new JLabel();
    JLabel volume = new JLabel();

    boolean volume_status = true;

    List<Point> listPoint = new ArrayList<>(); //list coordinate ground X Y
    List<JLabel> listGround = new ArrayList<>(); // list label ground

    List<Ground> listGroundx = new ArrayList<>();

    public GamePlay() {
        setMaximumSize(new Dimension(800, 600));
        setMinimumSize(new Dimension(800, 600));
        setSize(new Dimension(800, 600));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        background.setIcon(new ImageIcon(PATH_BACKGROUND));
        bag_icon.setIcon(new ImageIcon(PATH_BAG));
        shop_icon.setIcon(new ImageIcon(PATH_SHOP));
        open_basket.setIcon(new ImageIcon(PATH_OPEN_BASKET));
        back.setIcon(new ImageIcon(PATH_BACK));
        volume.setIcon(new ImageIcon(PATH_VOLUME));

        add(shop_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));
        add(bag_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));
        add(volume, new org.netbeans.lib.awtextra.AbsoluteConstraints(715, 25, -1, -1));
        add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 25, -1, -1));
        add(open_basket, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, -1, -1));

        
        SqlDataFarm.loadAllGround();
        loadGround();

        add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        shop_icon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                shop_iconClicked(evt);
            }
        });

        bag_icon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                bag_iconClicked(evt);
            }
        });

        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                backClicked(evt);
            }
        });

        volume.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                volumeClicked(evt);
            }
        });

        open_basket.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                open_basketClicked(evt);
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                mainClicked(evt);
            }
        });
    }

    private void removeGround() {
        System.out.println("remove ground");
    }

    // STEP GENERATE GROUND X-=100 Y+=65 i=4
    private void loadGround() {
        ResultSet data = SqlDataFarm.getAllGround();
        int i = 0;

        try {
            do {
                int type = data.getInt(2);
                long timeFinish = data.getLong(3);
                int stage = calStage(getCurrentTime(), timeFinish, type);

                JLabel tempJLabel = new JLabel();
                tempJLabel.setIcon(new ImageIcon(JsData.getPathGround(type, stage)));
                tempJLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

                Point tempPoint = new Point(START.getX() + (i / NUMBER_GROUNDINROW) * DISTANCE_ROW_X - (i % NUMBER_GROUNDINROW) * STEP_X, START.getY() + (i / NUMBER_GROUNDINROW) * DISTANCE_ROW_Y + (i % NUMBER_GROUNDINROW) * STEP_Y);
                add(tempJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(tempPoint.getX(), tempPoint.getY(), SIZE_GROUND_X, SIZE_GROUND_Y), 0);

                listGroundx.add(new Ground(type, timeFinish, tempJLabel, tempPoint));
                ++i;
            } while (data.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
//
//        int i;
//        for (i = 0; i < 4; i++) {
//            JLabel temp_jlabel = new JLabel();
//
//            temp_jlabel.setIcon(new ImageIcon(PATH_TOMATO_STAGE_3)); // path free ground
//            temp_jlabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
//
//            add(temp_jlabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(start_row_1.getX(), start_row_1.getY(), SIZE_GROUND_X, SIZE_GROUND_Y), 0);
//
//            listPoint.add(new Point(start_row_1));
//            listGround.add(temp_jlabel);
//
//            start_row_1.set(start_row_1.getX() - STEP_X, start_row_1.getY() + STEP_Y);
//        }
//
//        for (; i < NUMBER_GROUND; i++) {
//            JLabel temp_jlabel = new JLabel();
//
//            if (i >= 6) {
//                temp_jlabel.setIcon(new ImageIcon(PATH_CARROT_STAGE_3)); // path free ground
//            } else {
//                temp_jlabel.setIcon(new ImageIcon(PATH_POTATO_STAGE_3)); // path free ground
//            }
//            temp_jlabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
//
//            add(temp_jlabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(start_row_2.getX(), start_row_2.getY(), SIZE_GROUND_X, SIZE_GROUND_Y), 0);
//
//            listPoint.add(new Point(start_row_2));
//            listGround.add(temp_jlabel);
//
//            start_row_2.set(start_row_2.getX() - STEP_X, start_row_2.getY() + STEP_Y);
//        }
    }

    private void open_basketClicked(MouseEvent evt) {
        ModuleManager.plugIn(Main.mainFrame, Main.basket, 0);

        ModuleManager.revalidate(Main.mainFrame);
        ModuleManager.repaint(Main.mainFrame);
    }

    private void backClicked(MouseEvent evt) {
        ModuleManager.clearPlug(Main.mainFrame);
        ModuleManager.plugIn(Main.mainFrame, Main.menu);

        ModuleManager.revalidate(Main.mainFrame);
        ModuleManager.repaint(Main.mainFrame);
    }

    private void volumeClicked(MouseEvent evt) {
        volume_status = !volume_status;

        if (volume_status) {
            volume.setIcon(new ImageIcon(PATH_VOLUME));
        } else {
            volume.setIcon(new ImageIcon(PATH_VOLUME_DARKER));
        }
    }

    private void shop_iconClicked(MouseEvent evt) {
        ModuleManager.plugIn(Main.mainFrame, Main.shop, 0);

        ModuleManager.revalidate(Main.mainFrame);
        ModuleManager.repaint(Main.mainFrame);
    }

    private void mainClicked(MouseEvent evt) {
        System.out.println("ok main");
    }

    private void bag_iconClicked(MouseEvent evt) {
        System.out.println("ok bag");
    }

    private long getCurrentTime() {
        Date date = new Date(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return ((cal.get(Calendar.YEAR) * 10000000000l + 100000000 * cal.get(Calendar.MONTH) + 1000000 * cal.get(Calendar.DATE) + 10000 * cal.get(Calendar.HOUR) + 100 * cal.get(Calendar.MINUTE) + cal.get(Calendar.SECOND)));
    }

    private int calStage(long timeCurr, long timeFinish, int typePlant) {
        int growthPercent = calGrowthPercent(timeCurr, timeFinish, typePlant);

        if (growthPercent >= 100) {
            return 3;
        } else if (growthPercent >= 50) {
            return 2;
        } else {
            return 1;
        }
    }

    private int calGrowthPercent(long timeCurr, long timeFinish, int typePlant) {
        int timePlant = JsData.getTimePlant(JsData.getPlant(typePlant));

        return ((int) (((timeCurr - timeFinish) * 100) / timePlant));
    }
}
