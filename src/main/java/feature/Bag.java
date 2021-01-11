package feature;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import obj.Item;
import obj.Point;
import proc.Main;
import proc.ModuleManager;

public class Bag extends JPanel {

    static final int STEP_X = 140;
    static final int STEP_Y = 170;
    static final int NUMBER_CARDINROW = 5;

    static final Point START = new Point(130, 200);

    JLabel background = new JLabel();
    JLabel seed = new JLabel();
    JLabel fertilizer = new JLabel();
    JLabel ingredient = new JLabel();
    JLabel close = new JLabel();

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
    List<Item> listIngerdient = new ArrayList<>();

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

        add(seed, new org.netbeans.lib.awtextra.AbsoluteConstraints(287, 150, -1, -1));
        add(fertilizer, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 150, -1, -1));
        add(ingredient, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 150, -1, -1));

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

    }

    private void removeSeedItem() {

    }

    private void initFertilizerItem() {

    }

    private void removeFertilizerItem() {

    }

    private void initIngredientItem() {

    }

    private void removeIngredientItem() {

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
