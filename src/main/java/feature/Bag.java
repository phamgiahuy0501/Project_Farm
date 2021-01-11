package feature;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import obj.Item;
import obj.Point;
import org.json.simple.JSONObject;
import proc.Main;
import proc.ModuleManager;
import proc_data.SqlDataItem;

public class Bag extends JPanel {

    static final int STEP_X = 100;
    static final int STEP_Y = 100;
    static final int NUMBER_CARDINROW = 6;

    static final Point START = new Point(130, 200);

    JLabel background = new JLabel();
    JLabel seed = new JLabel();
    JLabel fertilizer = new JLabel();
    JLabel ingredient = new JLabel();
    JLabel close = new JLabel();

    private static final String SQL_SYNTAX_BRAND = "brand_id";
    private static final String SQL_SYNTAX_TYPE = "type";
    private static final String SQL_SYNTAX_AMOUNT = "amount";

    /* PATH */
 /* BACKGROUND */
    static final String PATH_BACKGROUND = "img\\bag\\background\\Background-bag.png";
    /* END BACKGROUND */

 /* ICON */
    static final String PATH_SEED = "img\\bag\\icon\\Seed-tag-icon.png";
    static final String PATH_SEED_LIGHTER = "img\\bag\\icon\\Seed-tag-lighter-icon.png";
    static final String PATH_FERTILIZER = "img\\bag\\icon\\Fertilizer-tag-icon.png";
    static final String PATH_FERTILIZER_LIGHTER = "img\\bag\\icon\\Fertilizer-tag-lighter-icon.png";
    static final String PATH_INGREDIENT = "img\\bag\\icon\\Ingredient-tag-icon.png";
    static final String PATH_INGREDIENT_LIGHTER = "img\\bag\\icon\\Ingredient-tag-lighter-icon.png";
    static final String PATH_CLOSE = "img\\bag\\icon\\Close-icon.png";

    /* END ICON */
 /* END PATH */
    List<Item> listSeed = new ArrayList<>();
    List<Item> listFertilizer = new ArrayList<>();
    List<Item> listIngredient = new ArrayList<>();

    public Bag() {
        setMaximumSize(new Dimension(800, 600));
        setMinimumSize(new Dimension(800, 600));
        setSize(new Dimension(800, 600));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        setOpaque(false);

        background.setIcon(new ImageIcon(PATH_BACKGROUND));
        seed.setIcon(new ImageIcon(PATH_SEED_LIGHTER));
        fertilizer.setIcon(new ImageIcon(PATH_FERTILIZER));
        ingredient.setIcon(new ImageIcon(PATH_INGREDIENT));
        close.setIcon(new ImageIcon(PATH_CLOSE));

        add(seed, new org.netbeans.lib.awtextra.AbsoluteConstraints(205, 150, -1, -1));
        add(fertilizer, new org.netbeans.lib.awtextra.AbsoluteConstraints(289, 150, -1, -1));
        add(ingredient, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 150, -1, -1));

        initSeedItem();

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

        ingredient.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                ingredientClicked(evt);
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

    private void initSeedItem() {
        if (listSeed.isEmpty()) {

            List<JSONObject> listSeedObject = SqlDataItem.getAllItem(0);

            for (int i = 0; i < listSeedObject.size(); i++) {
                JLabel tempJLabel = new JLabel();

                final int index = i;

                tempJLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent evt) {
                        itemClicked(evt, index);
                    }
                });

                int brand = (int) listSeedObject.get(i).get(SQL_SYNTAX_BRAND);
                int type = (int) listSeedObject.get(i).get(SQL_SYNTAX_TYPE);
                int amount = (int) listSeedObject.get(i).get(SQL_SYNTAX_AMOUNT);

                Point tempPoint = new Point(START.getX() + (i % NUMBER_CARDINROW) * STEP_X, START.getY() + (i / NUMBER_CARDINROW) * STEP_Y);
                add(tempJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(tempPoint.getX(), tempPoint.getY(), -1, -1));

                listSeed.add(new Item(brand, type, tempJLabel, tempPoint));
            }

        } else {
            listSeed.forEach(item -> {
                item.visible(true);
            });
        }
    }

    private void itemClicked(MouseEvent evt, int index_list) {
        
    }

    private void removeSeedItem() {
        listSeed.forEach(item -> {
            item.visible(false);
        });
    }

    private void initFertilizerItem() {
        if (listFertilizer.isEmpty()) {
            List<JSONObject> listFertilizerObject = SqlDataItem.getAllItem(1);
            
            for (int i = 0; i < listFertilizerObject.size(); i++) {
                JLabel tempJLabel = new JLabel();
                
                final int index = i;
                
                tempJLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent evt) {
                        itemClicked(evt, index);
                    }
                });

                int brand = (int) listFertilizerObject.get(i).get(SQL_SYNTAX_BRAND);
                int type = (int) listFertilizerObject.get(i).get(SQL_SYNTAX_TYPE);
                int amount = (int) listFertilizerObject.get(i).get(SQL_SYNTAX_AMOUNT);

                Point tempPoint = new Point(START.getX() + (i % NUMBER_CARDINROW) * STEP_X, START.getY() + (i / NUMBER_CARDINROW) * STEP_Y);
                add(tempJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(tempPoint.getX(), tempPoint.getY(), -1, -1));

                listFertilizer.add(new Item(brand, type, tempJLabel, tempPoint));
            }
        } else {
            listFertilizer.forEach(item -> {
                item.visible(true);
            });
        }
    }

    private void removeFertilizerItem() {
        listFertilizer.forEach(item -> {
            item.visible(false);
        });
    }

    private void initIngredientItem() {
        if (listIngredient.isEmpty()) {
            List<JSONObject> listIngredientObject = SqlDataItem.getAllItem(2);
            
            for (int i = 0; i < listIngredientObject.size(); i++) {
                JLabel tempJLabel = new JLabel();
                
                final int index = i;
                
                tempJLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent evt) {
                        itemClicked(evt, index);
                    }
                });

                int brand = (int) listIngredientObject.get(i).get(SQL_SYNTAX_BRAND);
                int type = (int) listIngredientObject.get(i).get(SQL_SYNTAX_TYPE);
                int amount = (int) listIngredientObject.get(i).get(SQL_SYNTAX_AMOUNT);

                Point tempPoint = new Point(START.getX() + (i % NUMBER_CARDINROW) * STEP_X, START.getY() + (i / NUMBER_CARDINROW) * STEP_Y);
                add(tempJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(tempPoint.getX(), tempPoint.getY(), -1, -1));

                listIngredient.add(new Item(brand, type, tempJLabel, tempPoint));
            }
        } else {
            listIngredient.forEach(item -> {
                item.visible(true);
            });
        }
    }

    private void removeIngredientItem() {
        listIngredient.forEach(item -> {
            item.visible(false);
        });
    }

    private void backgroundClicked(MouseEvent evt) {
        System.out.println("background clicked");
    }

    private void seedClicked(MouseEvent evt) {
        clearReview();

        fertilizer.setIcon(new ImageIcon(PATH_FERTILIZER));
        ingredient.setIcon(new ImageIcon(PATH_INGREDIENT));
        seed.setIcon(new ImageIcon(PATH_SEED_LIGHTER));

        removeFertilizerItem();
        removeIngredientItem();
        initSeedItem();

        ModuleManager.revalidate(Main.mainFrame);
        ModuleManager.repaint(Main.mainFrame);
    }

    private void fertilizerClicked(MouseEvent evt) {
        clearReview();

        seed.setIcon(new ImageIcon(PATH_SEED));
        ingredient.setIcon(new ImageIcon(PATH_INGREDIENT));
        fertilizer.setIcon(new ImageIcon(PATH_FERTILIZER_LIGHTER));

        removeSeedItem();
        removeIngredientItem();
        initFertilizerItem();

        ModuleManager.revalidate(Main.mainFrame);
        ModuleManager.repaint(Main.mainFrame);
    }

    private void ingredientClicked(MouseEvent evt) {
        clearReview();

        seed.setIcon(new ImageIcon(PATH_SEED));
        fertilizer.setIcon(new ImageIcon(PATH_FERTILIZER));
        ingredient.setIcon(new ImageIcon(PATH_INGREDIENT_LIGHTER));

        removeSeedItem();
        removeFertilizerItem();
        initIngredientItem();

        ModuleManager.revalidate(Main.mainFrame);
        ModuleManager.repaint(Main.mainFrame);
    }

    private void closeClicked(MouseEvent evt) {
        clearReview();
        ModuleManager.plugOut(Main.mainFrame, this);

        ModuleManager.resumeProcess(Main.gamePlay);

        ModuleManager.revalidate(Main.mainFrame);
        ModuleManager.repaint(Main.mainFrame);
    }

    private void clearReview() {

    }
}
