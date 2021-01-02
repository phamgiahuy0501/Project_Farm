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
    static final String PATH_CABBAGE_CARD = "D:\\Project_Farm\\img\\Card\\Shop\\Seed\\Cabbage-card-price.png";
    static final String PATH_CARROT_CARD = "D:\\Project_Farm\\img\\Card\\Shop\\Seed\\Carrot-card-price.png";
    static final String PATH_CORN_CARD = "D:\\Project_Farm\\img\\Card\\Shop\\Seed\\Corn-card-price.png";
    static final String PATH_PEAS_CARD = "D:\\Project_Farm\\img\\Card\\Shop\\Seed\\Peas-card-price.png";
    static final String PATH_POTATO_CARD = "D:\\Project_Farm\\img\\Card\\Shop\\Seed\\Potato-card-price.png";
    static final String PATH_TOMATO_CARD = "D:\\Project_Farm\\img\\Card\\Shop\\Seed\\Tomato-card-price.png";
    /* END CARD SEED */
    /* CARD FERTILIZER */
    static final String PATH_SMALL_FERTILIZER_CARD = "D:\\Project_Farm\\img\\Card\\Shop\\Fertilizer\\Small-fertilizer-card-price.png";
    static final String PATH_MEDIUM_FERTILIZER_CARD = "D:\\Project_Farm\\img\\Card\\Shop\\Fertilizer\\Medium-fertilizer-card-price.png";
    static final String PATH_BIG_FERTILIZER_CARD = "D:\\Project_Farm\\img\\Card\\Shop\\Fertilizer\\Big-fertilizer-card-price.png";
    /* END CARD FERTILIZER */
    
 /* END CARD */

    List<MyLabel> listSeedCard = new ArrayList<>();
    List<MyLabel> listFertilizerCard = new ArrayList<>();

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

        initSeedCart();
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

        removeFertilizerCard();
        initSeedCart();

        ModuleManager.revalidate(Main.mainFrame);
        ModuleManager.repaint(Main.mainFrame);
    }

    private void fertilizerClicked(MouseEvent evt) {
        fertilizer.setIcon(new ImageIcon(PATH_FERTILIZER_LIGHTER));
        seed.setIcon(new ImageIcon(PATH_SEED));

        removeSeedCart();
        initFertilizerCard();

        ModuleManager.revalidate(Main.mainFrame);
        ModuleManager.repaint(Main.mainFrame);
    }

    private void closeClicked(MouseEvent evt) {
        ModuleManager.plugOut(Main.mainFrame, this);

        ModuleManager.revalidate(Main.mainFrame);
        ModuleManager.repaint(Main.mainFrame);
    }

    private void removeSeedCart() {
        listSeedCard.forEach(mylabel -> {
            remove(mylabel.getLabel());
        });
    }

    private void removeFertilizerCard() {
        listFertilizerCard.forEach(mylabel -> {
            remove(mylabel.getLabel());
        });
    }

    private void initFertilizerCard() {
        if (listFertilizerCard.isEmpty()) {
            
            List<String> LIST_PATH = new ArrayList<>();
            LIST_PATH.add(PATH_SMALL_FERTILIZER_CARD);
            LIST_PATH.add(PATH_MEDIUM_FERTILIZER_CARD);
            LIST_PATH.add(PATH_BIG_FERTILIZER_CARD);
            
            for (int i = 0; i < LIST_PATH.size(); i++) {
                JLabel temp_jlabel = new JLabel();
                temp_jlabel.setIcon(new ImageIcon(LIST_PATH.get(i)));
                
                Point temp_point = new Point(START.getX() + (i % NUMBER_CARDINROW) * STEP_X, START.getY() + (i / NUMBER_CARDINROW) * STEP_Y);
                add(temp_jlabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(temp_point.getX(), temp_point.getY(), -1, -1), 0);
            
                listFertilizerCard.add(new MyLabel(temp_jlabel, new Point(temp_point)));
            }
        } else {
            listFertilizerCard.forEach((mylabel) -> {
                add(mylabel.getLabel(), new org.netbeans.lib.awtextra.AbsoluteConstraints(mylabel.getPoint().getX(), mylabel.getPoint().getY()), 0);
            });
        }
    }

    private void initSeedCart() {
        if (listSeedCard.isEmpty()) {

            List<String> LIST_PATH = new ArrayList<>();
            LIST_PATH.add(PATH_CARROT_CARD);
            LIST_PATH.add(PATH_POTATO_CARD);
            LIST_PATH.add(PATH_TOMATO_CARD);
            LIST_PATH.add(PATH_CORN_CARD);
            LIST_PATH.add(PATH_PEAS_CARD);
            LIST_PATH.add(PATH_CABBAGE_CARD);

            for (int i = 0; i < LIST_PATH.size(); i++) {
                JLabel temp_jlabel = new JLabel();

                temp_jlabel.setIcon(new ImageIcon(LIST_PATH.get(i)));

                Point temp_point = new Point(START.getX() + (i % NUMBER_CARDINROW) * STEP_X, START.getY() + (i / NUMBER_CARDINROW) * STEP_Y);
                add(temp_jlabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(temp_point.getX(), temp_point.getY(), -1, -1), 0);

                listSeedCard.add(new MyLabel(temp_jlabel, new Point(temp_point)));
            }
        } else {
            listSeedCard.forEach((mylabel) -> {
                add(mylabel.getLabel(), new org.netbeans.lib.awtextra.AbsoluteConstraints(mylabel.getPoint().getX(), mylabel.getPoint().getY()), 0);
            });
        }
    }
}
