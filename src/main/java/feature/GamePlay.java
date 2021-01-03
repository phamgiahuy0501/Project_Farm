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
import java.util.ArrayList;
import java.util.List;
import proc.Main;
import proc.ModuleManager;

/**
 *
 * @author kazen
 */
public class GamePlay extends JPanel {

    static final int STEP_X = 100;
    static final int STEP_Y = 65;
    static final int NUMBER_GROUND = 8;
    static final int SIZE_GROUND_X = 100;
    static final int SIZE_GROUND_Y = 115;

    static final Point START_ROW_1 = new Point(470, 90);
    static final Point START_ROW_2 = new Point(610, 170);

    /* PATH */
 /* BACK GROUND */
    static final String PATH_BACKGROUND = "D:\\Project_Farm\\img\\game\\background\\Farm-ground.png";
    /* END BACK GROUND */

 /* ICON */
    static final String PATH_BAG = "D:\\Project_Farm\\img\\game\\icon\\Bag-icon.png";
    static final String PATH_SHOP = "D:\\Project_Farm\\img\\game\\icon\\Shop-icon.png";
    static final String PATH_OPEN_BASKET = "D:\\Project_Farm\\img\\game\\icon\\Open-left-icon.png";
    static final String PATH_VOLUME = "D:\\Project_Farm\\img\\game\\icon\\Volume-icon.png";
    static final String PATH_VOLUME_DARKER = "D:\\Project_Farm\\img\\game\\icon\\Volume-darker-icon.png";
    static final String PATH_BACK = "D:\\Project_Farm\\img\\game\\icon\\Back-icon.png";
    /* END ICON */

 /* PLANT PATH */
    static final String PATH_FREEGROUND = "D:\\Project_Farm\\img\\game\\obj\\Free-ground.png";

    static final String PATH_TOMATO_STAGE_1 = "D:\\Project_Farm\\img\\game\\obj\\Tomato\\Tomato-stage-1.png";
    static final String PATH_TOMATO_STAGE_2 = "D:\\Project_Farm\\img\\game\\obj\\Tomato\\Tomato-stage-2.png";
    static final String PATH_TOMATO_STAGE_3 = "D:\\Project_Farm\\img\\game\\obj\\Tomato\\Tomato-stage-3.png";

    static final String PATH_POTATO_STAGE_1 = "D:\\Project_Farm\\img\\game\\obj\\Potato\\Potato-stage-1.png";
    static final String PATH_POTATO_STAGE_2 = "D:\\Project_Farm\\img\\game\\obj\\Potato\\Potato-stage-2.png";
    static final String PATH_POTATO_STAGE_3 = "D:\\Project_Farm\\img\\game\\obj\\Potato\\Potato-stage-3.png";

    static final String PATH_CARROT_STAGE_1 = "D:\\Project_Farm\\img\\game\\obj\\Carrot\\Carrot-stage-1.png";
    static final String PATH_CARROT_STAGE_2 = "D:\\Project_Farm\\img\\game\\obj\\Carrot\\Carrot-stage-2.png";
    static final String PATH_CARROT_STAGE_3 = "D:\\Project_Farm\\img\\game\\obj\\Carrot\\Carrot-stage-3.png";
    /* END PLANT PATH */
 /* END PATH */

    JLabel background = new JLabel();
    JLabel bag_icon = new JLabel();
    JLabel shop_icon = new JLabel();
    JLabel open_basket = new JLabel();
    JLabel back = new JLabel();
    JLabel volume = new JLabel();

    boolean volume_status = true;

    List<Point> listPoint = new ArrayList<>(); //list coordinate ground X Y
    List<JLabel> listGround = new ArrayList<>(); // list label ground

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

        initFreeGround();

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

    // STEP GENERATE GROUND X-=100 Y+=65 i=4
    private void initFreeGround() {
        int i;

        Point start_row_1 = new Point(START_ROW_1);
        Point start_row_2 = new Point(START_ROW_2);

        for (i = 0; i < 4; i++) {
            JLabel temp_jlabel = new JLabel();

            temp_jlabel.setIcon(new ImageIcon(PATH_TOMATO_STAGE_3)); // path free ground
            temp_jlabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

            add(temp_jlabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(start_row_1.getX(), start_row_1.getY(), SIZE_GROUND_X, SIZE_GROUND_Y), 0);

            listPoint.add(new Point(start_row_1));
            listGround.add(temp_jlabel);

            start_row_1.set(start_row_1.getX() - STEP_X, start_row_1.getY() + STEP_Y);
        }

        for (; i < NUMBER_GROUND; i++) {
            JLabel temp_jlabel = new JLabel();

            if (i >= 6) {
                temp_jlabel.setIcon(new ImageIcon(PATH_CARROT_STAGE_3)); // path free ground
            } else {
                temp_jlabel.setIcon(new ImageIcon(PATH_POTATO_STAGE_3)); // path free ground
            }
            temp_jlabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

            add(temp_jlabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(start_row_2.getX(), start_row_2.getY(), SIZE_GROUND_X, SIZE_GROUND_Y), 0);

            listPoint.add(new Point(start_row_2));
            listGround.add(temp_jlabel);

            start_row_2.set(start_row_2.getX() - STEP_X, start_row_2.getY() + STEP_Y);
        }
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
}
