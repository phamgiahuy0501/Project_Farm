/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feature;

import java.awt.Color;
import obj.Point;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.SwingConstants;
import obj.Ground;
import org.json.simple.JSONObject;
import proc.Main;
import proc.ModuleManager;
import proc_data.JsData;
import proc_data.SqlDataFarm;
import proc_data.SqlDataItem;

/**
 *
 * @author kazen
 */
public class GamePlay extends JPanel {

    static final int STEP_X = 100;
    static final int STEP_Y = 65;
    static final int NUMBER_GROUNDINROW = 4;
    static final int GROUND_WIDTH = 100;
    static final int GROUND_HEIGHT = 115;

    static final Point START = new Point(470, 90);

    static final int DISTANCE_ROW_X = 140;
    static final int DISTANCE_ROW_Y = 80;

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
    public static JLabel moneyLaybel = new JLabel();
    JLabel back = new JLabel();
    JLabel volume = new JLabel();

    boolean volume_status = true;

    List<Ground> listGround = new ArrayList<>();

    public GamePlay() {
        setMaximumSize(new Dimension(800, 600));
        setMinimumSize(new Dimension(800, 600));
        setSize(new Dimension(800, 600));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JLabel goldIcon = new JLabel();
        goldIcon.setIcon(new ImageIcon("img\\game\\icon\\Coin-icon.png"));
        
        moneyLaybel.setHorizontalAlignment(SwingConstants.RIGHT);
        moneyLaybel.setText(String.valueOf(SqlDataItem.getMoney()));
        moneyLaybel.setForeground(Color.YELLOW);
        moneyLaybel.setFont(new Font("", Font.BOLD, 18));
        
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
        add(moneyLaybel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));
        add(goldIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 25, -1, -1));
        
        
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

    public void updateMoney(int amount) {
        SqlDataItem.updateMoney(amount);
    }

    private void loadGround() {
        listGround.clear();
        List<JSONObject> listGroundObject = SqlDataFarm.getAllGround();

        for(int i = 0; i < listGroundObject.size(); i++) {
            
            int type = (int) listGroundObject.get(i).get(SqlDataFarm.SQL_FARM_SYNTAX_TYPE);
            long timeFinish = (long) listGroundObject.get(i).get(SqlDataFarm.SQL_FARM_SYNTAX_TIMEFINISH);
            final int index = i;
            JLabel tempJLabel = new JLabel();

            tempJLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent evt) {
                    groundClicked(evt, index);
                }

                @Override
                public void mouseEntered(MouseEvent evt) {
                    groundEntered(evt, index);
                }

                @Override
                public void mouseExited(MouseEvent evt) {
                    groundExited(evt, index);
                }
            });

            Point tempPoint = new Point(START.getX() + (index / NUMBER_GROUNDINROW) * DISTANCE_ROW_X - (index % NUMBER_GROUNDINROW) * STEP_X, START.getY() + (index / NUMBER_GROUNDINROW) * DISTANCE_ROW_Y + (index % NUMBER_GROUNDINROW) * STEP_Y);

            add(tempJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(tempPoint.getX(), tempPoint.getY(), GROUND_WIDTH, GROUND_HEIGHT));

            listGround.add(new Ground(type, timeFinish, tempJLabel, tempPoint));
        }
    }

    public boolean isListGroundNull() {
        return listGround.isEmpty();
    }

    private void groundEntered(MouseEvent evt, int index) {
        Ground thisGround = listGround.get(index);
        thisGround.showTimeLabel();
    }

    private void groundExited(MouseEvent evt, int index) {
        Ground thisGround = listGround.get(index);
        thisGround.hideTimeLabel();
    }

    private void groundClicked(MouseEvent evt, int index) {
        listGround.get(index).harvest();
        SqlDataFarm.updateSpecificGround(index, 0, 0);
        
        System.out.println("harvested");
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

    private void pauseProcess() {
        listGround.forEach((ground) -> {
            ground.pauseProcess();
        });
    }

    public void resumeProcess() {
        listGround.forEach((ground) -> {
            ground.resumeProcess();
        });
    }

    private void shop_iconClicked(MouseEvent evt) {
        ModuleManager.plugIn(Main.mainFrame, Main.shop, 0);

        pauseProcess();

        ModuleManager.revalidate(Main.mainFrame);
        ModuleManager.repaint(Main.mainFrame);
    }

    private void mainClicked(MouseEvent evt) {
        System.out.println("ok main");
    }

    private void bag_iconClicked(MouseEvent evt) {
        ModuleManager.plugIn(Main.mainFrame, Main.bag, 0);

        pauseProcess();

        ModuleManager.revalidate(Main.mainFrame);
        ModuleManager.repaint(Main.mainFrame);
    }

    private static long getCurrentTime() {
        Date date = new Date(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return ((cal.get(Calendar.YEAR) * 10000000000l + 100000000 * cal.get(Calendar.MONTH) + 1000000 * cal.get(Calendar.DATE) + 10000 * cal.get(Calendar.HOUR) + 100 * cal.get(Calendar.MINUTE) + cal.get(Calendar.SECOND)));
    }

    public static float calGrowthPercent(long timeFinish, int typePlant) {
        if (typePlant == 0) {
            return 0;
        }
        int timePlant = JsData.getTimePlant(JsData.getPlant(typePlant));

        return (float) (getCurrentTime() - (timeFinish - timePlant)) * 100 / timePlant;
    }

    public static int calStage(long timeFinish, int typePlant) {
        float growthPercent = calGrowthPercent(timeFinish, typePlant);

        if (growthPercent >= 100) {
            return 3;
        } else if (growthPercent >= 50) {
            return 2;
        } else {
            return 1;
        }
    }
}
