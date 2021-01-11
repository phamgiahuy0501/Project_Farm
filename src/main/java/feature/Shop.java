/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feature;

import java.awt.Color;
import obj.Point;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import obj.Card;
import proc.Main;
import proc.ModuleManager;
import proc_data.JsData;
import proc_data.SqlDataItem;

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

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                backgroundClicked(evt);
            }
        });
    }

    private void backgroundClicked(MouseEvent evt) {
        clearReview();

        ModuleManager.revalidate(this);
        ModuleManager.repaint(this);
    }

    private void seedClicked(MouseEvent evt) {
        clearReview();

        fertilizer.setIcon(new ImageIcon(PATH_FERTILIZER));
        seed.setIcon(new ImageIcon(PATH_SEED_LIGHTER));

        removeFertilizerCard();
        initSeedCart();

        ModuleManager.revalidate(this);
        ModuleManager.repaint(this);
    }

    private void fertilizerClicked(MouseEvent evt) {
        clearReview();

        fertilizer.setIcon(new ImageIcon(PATH_FERTILIZER_LIGHTER));
        seed.setIcon(new ImageIcon(PATH_SEED));

        removeSeedCart();
        initFertilizerCard();

        ModuleManager.revalidate(this);
        ModuleManager.repaint(this);
    }

    private void closeClicked(MouseEvent evt) {
        clearReview();
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
                        cardClicked(evt, 1, type, type);
                    }
                });

                Point temp_point = new Point(START.getX() + (i % NUMBER_CARDINROW) * STEP_X, START.getY() + (i / NUMBER_CARDINROW) * STEP_Y);
                add(temp_jlabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(temp_point.getX(), temp_point.getY(), -1, -1), 0);

                listFertilizerCard.add(new Card(1, type, temp_jlabel, temp_point));
            }
        } else {
            listFertilizerCard.forEach((card) -> {
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
                        cardClicked(evt, 0, type, type - 1);
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

    private static ReviewAmount thisReviewAmount;

    private void cardClicked(MouseEvent evt, int brand, int type, int index_listCard) {
//        System.out.println(JsData.getPrice(brand, type));

        clearReview();

        thisReviewAmount = new ReviewAmount(brand, type);

        Point createPoint;

        if (brand == 0) {
            createPoint = listSeedCard.get(index_listCard).getPoint();
        } else { // brand == 2
            createPoint = listFertilizerCard.get(index_listCard).getPoint();
        }

        add(thisReviewAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(createPoint.getX(), createPoint.getY(), ReviewAmount.MY_WIDTH, ReviewAmount.MY_HEIGHT), 0);

        ModuleManager.revalidate(this);
        ModuleManager.repaint(this);
    }

    private void clearReview() {
        if (thisReviewAmount != null) {
            remove(thisReviewAmount);
            thisReviewAmount = null;
        }
    }
}

class ReviewAmount extends JPanel {

    JLabel amount = new JLabel();
    JLabel total = new JLabel();
    JLabel buy = new JLabel();
    JLabel plus = new JLabel();
    JLabel minus = new JLabel();

    private int brand;
    private int type;
    private int price;
    private int money;
    public static final int MY_WIDTH = 100;
    public static final int MY_HEIGHT = 41;

    private static final String STR_TOTAL = "Total : ";

    public ReviewAmount() {
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        plus.setText("+");
        minus.setText("-");
        buy.setText("BUY");

        plus.setFont(new Font("", Font.BOLD, 20));
        minus.setFont(new Font("", Font.BOLD, 30));
        amount.setFont(new Font("", Font.PLAIN, 15));
        buy.setFont(new Font("", Font.BOLD, 10));
        total.setFont(new Font("", Font.PLAIN, 10));

        plus.setPreferredSize(new Dimension(13, 13));
        minus.setPreferredSize(new Dimension(13, 13));
        amount.setPreferredSize(new Dimension(20, 13));

        buy.setBackground(Color.ORANGE);

        setAlignCenter(plus);
        setAlignCenter(minus);
        setAlignCenter(buy);
        setAlignCenter(total);
        setAlignCenter(amount);

        plus.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                plusClicked(evt);
            }
        });

        minus.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                minusClicked(evt);
            }
        });

        buy.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                buyClicked(evt);
            }
        });

        add(minus, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 0, -1, -1));
        add(amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 4, -1, -1));
        add(plus, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 4, -1, -1));
        add(buy, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 27, -1, -1));
        add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 16, -1, -1));
    }

    public ReviewAmount(int brand, int type) {
        this();

        price = JsData.getPrice(brand, type);
        this.brand = brand;
        this.type = type;
        verified();

    }

    private void verified() {
        money = SqlDataItem.getMoney();
        if (money >= price) {
            amount.setText("1");
            total.setText(STR_TOTAL + price);
        } else {
            amount.setText("0");
            total.setText(STR_TOTAL + 0);
        }
        
    }

    private void buyClicked(MouseEvent evt) {
        int amountInt = Integer.parseInt(amount.getText());
        if (amountInt > 0) {
            int newMoney = money - amountInt * price;

            SqlDataItem.updateAmountItem(brand, type, SqlDataItem.getAmountItem(brand, type) + amountInt);
            SqlDataItem.updateMoney(newMoney);

            verified();

            System.out.println("buy ok");
        }
    }

    private void plusClicked(MouseEvent evt) {
        int max = money / price;
        int newAmount = Integer.parseInt(amount.getText());

        if (newAmount != max) {
            ++newAmount;

            amount.setText(String.valueOf(newAmount));
            total.setText(STR_TOTAL + String.valueOf(newAmount * price));
        }        
    }

    private void minusClicked(MouseEvent evt) {
        int min = 1;
        int newAmount = Integer.parseInt(amount.getText());

        if (newAmount > min) {
            --newAmount;

            amount.setText(String.valueOf(newAmount));
            total.setText(STR_TOTAL + String.valueOf(newAmount * price));
        }
    }

    private void setAlignCenter(JLabel label) {
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
    }
}
