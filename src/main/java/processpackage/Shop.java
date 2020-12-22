/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processpackage;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author kazen
 */
public class Shop extends JPanel {

    static final int STEP_X = 140;
    static final int STEP_Y = 170;
    static final int NUMBER_SEED = 6;
    static final int NUMBER_CARDINROW = 4;

    static final Point START = new Point(130, 200);

    JLabel background = new JLabel();
    JLabel seed = new JLabel();
    JLabel fertilizer = new JLabel();
    JLabel close = new JLabel();

    static final String PATH_BACKGROUND = "D:\\Project_Farm\\img\\Background\\Background-shop.png";
    static final String PATH_SEED = "D:\\Project_Farm\\img\\Button\\Shop\\Seed-tag-button.png";
    static final String PATH_SEED_LIGHTER = "D:\\Project_Farm\\img\\Button\\Shop\\Seed-tag-lighter-button.png";
    static final String PATH_FERTILIZER = "D:\\Project_Farm\\img\\Button\\Shop\\Fertilizer-tag-button.png";
    static final String PATH_FERTILIZER_LIGHTER = "D:\\Project_Farm\\img\\Button\\Shop\\Fertilizer-tag-lighter-button.png";
    static final String PATH_CLOSE = "D:\\Project_Farm\\img\\Button\\Shop\\Close-button.png";

    /* CARD */
 /* CARD SEED */
    static final String PATH_CABBAGE_CARD = "D:\\Project_Farm\\img\\Card\\Shop\\Cabbage-card-with-price.png";
    static final String PATH_CARROT_CARD = "D:\\Project_Farm\\img\\Card\\Shop\\Carrot-card-with-price.png";
    static final String PATH_CORN_CARD = "D:\\Project_Farm\\img\\Card\\Shop\\Corn-card-with-price.png";
    static final String PATH_PEAS_CARD = "D:\\Project_Farm\\img\\Card\\Shop\\Peas-card-with-price.png";
    static final String PATH_POTATO_CARD = "D:\\Project_Farm\\img\\Card\\Shop\\Potato-card-with-price.png";
    static final String PATH_TOMATO_CARD = "D:\\Project_Farm\\img\\Card\\Shop\\Tomato-card-with-price.png";
    /* END CARD SEED */
 /* END CARD */

    List<Point> listSeedPoint = new ArrayList<Point>();
    List<Point> listFertilizerList = new ArrayList<Point>();

    List<JLabel> listSeedCard = new ArrayList<JLabel>();
    List<JLabel> listFertilizerCard = new ArrayList<JLabel>();

    Shop() {
        setMaximumSize(new Dimension(800, 600));
        setMinimumSize(new Dimension(800, 600));
        setSize(new Dimension(800, 600));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        setOpaque(false);

        background.setIcon(new ImageIcon(PATH_BACKGROUND));
        seed.setIcon(new ImageIcon(PATH_SEED_LIGHTER));
        fertilizer.setIcon(new ImageIcon(PATH_FERTILIZER));
        close.setIcon(new ImageIcon(PATH_CLOSE));

        add(seed, new org.netbeans.lib.awtextra.AbsoluteConstraints(287, 150, -1, -1));
        add(fertilizer, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 150, -1, -1));

        //initSeedCart();

        add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
        add(close, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 60, -1, -1));

        seed.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                seedClicked(evt);
            }
        });

        fertilizer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                fertilizerClicked(evt);
            }
        });

        close.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                closeClicked(evt);
            }
        });
    }

    private void seedClicked(MouseEvent evt) {
        fertilizer.setIcon(new ImageIcon(PATH_FERTILIZER));
        seed.setIcon(new ImageIcon(PATH_SEED_LIGHTER));

        /* ADD GENERATE CODE HERE */
    }

    private void fertilizerClicked(MouseEvent evt) {
        fertilizer.setIcon(new ImageIcon(PATH_FERTILIZER_LIGHTER));
        seed.setIcon(new ImageIcon(PATH_SEED));

        /* ADD GENERATE CODE HERE */
    }

    private void closeClicked(MouseEvent evt) {
        ModuleManager.plugOut(Main.mainFrame, this);

        ModuleManager.revalidate(Main.mainFrame);
        ModuleManager.repaint(Main.mainFrame);
    }
    
    
    /* ON WORKING */
    private void initSeedCart() {
        if (listSeedCard.isEmpty() && listSeedPoint.isEmpty()) {
            int i;

            for (i = 0; i < NUMBER_SEED; i++) {
                JLabel temp_jlabel = new JLabel();

                switch (i) {
                    case 0:
                        temp_jlabel.setIcon(new ImageIcon(PATH_CARROT_CARD));
                        break;
                    case 1:
                        temp_jlabel.setIcon(new ImageIcon(PATH_POTATO_CARD));
                        break;
                    case 2:
                        temp_jlabel.setIcon(new ImageIcon(PATH_TOMATO_CARD));
                        break;
                    case 3:
                        temp_jlabel.setIcon(new ImageIcon(PATH_CORN_CARD));
                        break;
                    case 4:
                        temp_jlabel.setIcon(new ImageIcon(PATH_PEAS_CARD));
                        break;
                    case 5:
                        temp_jlabel.setIcon(new ImageIcon(PATH_CABBAGE_CARD));
                        break;
                }

                Point temp_point = new Point(START.getX() + (i % NUMBER_CARDINROW) * STEP_X, START.getY() + (i / NUMBER_CARDINROW) * STEP_Y);
                add(temp_jlabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(temp_point.getX(), temp_point.getY(), -1, -1));

                listSeedPoint.add(new Point(temp_point));
                listSeedCard.add(temp_jlabel);
            }
        } else 
        {
            for (int i = 0; i < listSeedCard.size(); i++) {
                
            }
        }
    }
}
