/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feature;

import obj.MyLabel;
import obj.Point;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import obj.Card;
import proc.Main;
import proc.ModuleManager;
import proc_data.JsData;

/**
 *
 * @author kazen
 */
public class Shop extends JPanel {

    static final int STEP_X = 140;
    static final int STEP_Y = 170;
    static final int NUMBER_CARDINROW = 4;
    static final int NUMBER_SEED_CARD = 6;
    static final int NUMBER_FERTILIZER_CARD = 3;
    
    static final Point START = new Point(130, 200);

    JLabel background = new JLabel();
    JLabel seed = new JLabel();
    JLabel fertilizer = new JLabel();
    JLabel close = new JLabel();

    /* PATH */
 /* BACKGROUND */
    static final String PATH_BACKGROUND = "D:\\Project_Farm\\img\\shop\\background\\Background-shop.png";
    /* END BACKGROUND */

 /* ICON */
    static final String PATH_SEED = "D:\\Project_Farm\\img\\shop\\icon\\Seed-tag-icon.png";
    static final String PATH_SEED_LIGHTER = "D:\\Project_Farm\\img\\shop\\icon\\Seed-tag-lighter-icon.png";
    static final String PATH_FERTILIZER = "D:\\Project_Farm\\img\\shop\\icon\\Fertilizer-tag-icon.png";
    static final String PATH_FERTILIZER_LIGHTER = "D:\\Project_Farm\\img\\shop\\icon\\Fertilizer-tag-lighter-icon.png";
    static final String PATH_CLOSE = "D:\\Project_Farm\\img\\shop\\icon\\Close-icon.png";
    /* END ICON */

 /* CARD */
 /* SEED */
    static final String PATH_CABBAGE_CARD = "D:\\Project_Farm\\img\\shop\\card\\seed\\Cabbage-card-price.png";
    static final String PATH_CARROT_CARD = "D:\\Project_Farm\\img\\shop\\card\\seed\\Carrot-card-price.png";
    static final String PATH_CORN_CARD = "D:\\Project_Farm\\img\\shop\\card\\seed\\Corn-card-price.png";
    static final String PATH_PEAS_CARD = "D:\\Project_Farm\\img\\shop\\card\\seed\\Peas-card-price.png";
    static final String PATH_POTATO_CARD = "D:\\Project_Farm\\img\\shop\\card\\seed\\Potato-card-price.png";
    static final String PATH_TOMATO_CARD = "D:\\Project_Farm\\img\\shop\\card\\seed\\Tomato-card-price.png";
    /* END SEED */

 /* FERTILIZER */
    static final String PATH_SMALL_FERTILIZER_CARD = "D:\\Project_Farm\\img\\shop\\card\\fertilizer\\Small-fertilizer-card-price.png";
    static final String PATH_MEDIUM_FERTILIZER_CARD = "D:\\Project_Farm\\img\\shop\\card\\fertilizer\\Medium-fertilizer-card-price.png";
    static final String PATH_BIG_FERTILIZER_CARD = "D:\\Project_Farm\\img\\shop\\card\\fertilizer\\Big-fertilizer-card-price.png";
    /* END FERTILIZER */
 /* END CARD */
 /* END PATH */

    List<Card> listSeedCard = new ArrayList<>();
    List<Card> listFertilizerCard = new ArrayList<>();

    public Shop() {
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

        ModuleManager.resumeProcess(Main.gamePlay);

        ModuleManager.revalidate(Main.mainFrame);
        ModuleManager.repaint(Main.mainFrame);
    }

    private void removeSeedCart() {
        listSeedCard.forEach(card -> {
            card.visible(false);
        });
    }

    private void removeFertilizerCard() {
        listFertilizerCard.forEach(card -> {
            card.visible(false);
        });
    }

    private void initFertilizerCard() {
        if (listFertilizerCard.isEmpty()) {
            for (int i = 0; i < NUMBER_FERTILIZER_CARD; i++) {
                JLabel temp_jlabel = new JLabel();
                
                final int type = i;
                
                temp_jlabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent evt) {
                        cardClicked(evt, 1, type);
                    }
                });
                
                Point temp_point = new Point(START.getX() + (i % NUMBER_CARDINROW) * STEP_X, START.getY() + (i / NUMBER_CARDINROW) * STEP_Y);
                add(temp_jlabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(temp_point.getX(), temp_point.getY(), -1, -1), 0);

                listFertilizerCard.add(new Card(1, type, temp_jlabel, temp_point));
            }
        } else {
            listFertilizerCard.forEach((card) ->{
                card.visible(true);
            });
        }
    }

    private void initSeedCart() {
        if (listSeedCard.isEmpty()) {
            for (int i = 0; i < NUMBER_SEED_CARD; i++) {
                JLabel temp_jlabel = new JLabel();
                
                final int type = i + 1;
                
                temp_jlabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent evt) {
                        cardClicked(evt, 0, type);
                    }
                });

                Point temp_point = new Point(START.getX() + (i % NUMBER_CARDINROW) * STEP_X, START.getY() + (i / NUMBER_CARDINROW) * STEP_Y);
                add(temp_jlabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(temp_point.getX(), temp_point.getY(), -1, -1), 0);

                listSeedCard.add(new Card(0, type, temp_jlabel, temp_point));
            }
        } else {
            listSeedCard.forEach((card) -> {
                card.visible(true);
            });
        }
    }

    private void cardClicked(MouseEvent evt, int brand, int type) {
        System.out.println(JsData.getPrice(brand, type));
    }
}
