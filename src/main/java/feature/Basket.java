package feature;

import java.awt.Color;
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
import proc.ModulePlanting;
import proc_data.SqlDataItem;

public class Basket extends JPanel {

    static final int STEP_X = 100;
    static final int NUMBER_CARDINROW = 6;
    static final Point START = new Point(50, 485);

    JLabel background = new JLabel();
    JLabel seed = new JLabel();
    JLabel fertilizer = new JLabel();
    JLabel close = new JLabel();

    private static final String SQL_SYNTAX_BRAND = "brand_id";
    private static final String SQL_SYNTAX_TYPE = "type";
    private static final String SQL_SYNTAX_AMOUNT = "amount";
    /* PATH */
 /* BACKGROUND */
    static final String PATH_BACKGROUND = "D:\\Project_Farm\\img\\basket\\background\\Background-basket.png";
    /* END BACKGROUND */

 /* ICON */
    static final String PATH_SEED = "D:\\Project_Farm\\img\\basket\\icon\\Seed-tag-icon.png";
    static final String PATH_SEED_LIGHTER = "D:\\Project_Farm\\img\\basket\\icon\\Seed-tag-lighter-icon.png";
    static final String PATH_FERTILIZER = "D:\\Project_Farm\\img\\basket\\icon\\Fertilizer-tag-icon.png";
    static final String PATH_FERTILIZER_LIGHTER = "D:\\Project_Farm\\img\\basket\\icon\\Fertilizer-tag-lighter-icon.png";
    static final String PATH_CLOSE = "D:\\Project_Farm\\img\\basket\\icon\\Close-right-icon.png";
    /* END ICON */
 /* END PATH */

    List<Item> listSeed = new ArrayList<>();
    List<Item> listFertilizer = new ArrayList<>();

    public Basket() {
        setMaximumSize(new Dimension(800, 600));
        setMinimumSize(new Dimension(800, 600));
        setSize(new Dimension(800, 600));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        setOpaque(false);

        background.setIcon(new ImageIcon(PATH_BACKGROUND));
        seed.setIcon(new ImageIcon(PATH_SEED_LIGHTER));
        fertilizer.setIcon(new ImageIcon(PATH_FERTILIZER));
        close.setIcon(new ImageIcon(PATH_CLOSE));

        add(close, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 520, -1, -1));
        add(seed, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 455, -1, -1));
        add(fertilizer, new org.netbeans.lib.awtextra.AbsoluteConstraints(87, 455, -1, -1));

        initSeedItem();

        add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 475, -1, -1));

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
        ModulePlanting.isPlanting = false;
        
        seed.setIcon(new ImageIcon(PATH_SEED_LIGHTER));
        fertilizer.setIcon(new ImageIcon(PATH_FERTILIZER));

        removeFertilizerItem();
        initSeedItem();

        ModuleManager.revalidate(this);
        ModuleManager.repaint(this);
    }

    private void fertilizerClicked(MouseEvent evt) {
        ModulePlanting.isPlanting = false;
        
        seed.setIcon(new ImageIcon(PATH_SEED));
        fertilizer.setIcon(new ImageIcon(PATH_FERTILIZER_LIGHTER));

        removeSeedItem();
        initFertilizerItem();

        ModuleManager.revalidate(this);
        ModuleManager.repaint(this);
    }

    private void closeClicked(MouseEvent evt) {
        ModulePlanting.isPlanting = false;
        ModuleManager.plugOut(Main.mainFrame, this);

        ModuleManager.revalidate(Main.mainFrame);
        ModuleManager.repaint(Main.mainFrame);
    }

    private void initSeedItem() {
        listSeed.clear();
        List<JSONObject> listSeedObject = SqlDataItem.getAllItem(0);
        final int id = 0;

        for (int i = 0; i < listSeedObject.size(); i++) {
            JLabel tempJLabel = new JLabel();

            final int index = i;

            tempJLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent evt) {
                    itemClicked(evt, id, index);
                }
            });

            int brand = (int) listSeedObject.get(i).get(SQL_SYNTAX_BRAND);
            int type = (int) listSeedObject.get(i).get(SQL_SYNTAX_TYPE);
            int amount = (int) listSeedObject.get(i).get(SQL_SYNTAX_AMOUNT);

            if (amount <= 0) {
                continue;
            }

            Point tempPoint = new Point(START.getX() + (i % NUMBER_CARDINROW) * STEP_X, START.getY());
            add(tempJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(tempPoint.getX(), tempPoint.getY()), 0);

            listSeed.add(new Item(brand, type, tempJLabel, tempPoint));
        }
    }

    public void update() {
        
    }

    // seed id = 0
    // fertilizer id = 1
    private void itemClicked(MouseEvent evt, int id, int index) {        
        Item tempItem;
        if (id == 0) {
            tempItem = listSeed.get(index);
            
            listSeed.forEach(item -> {
                item.unhighlight();
            });
        } else {
            tempItem = listFertilizer.get(index);
            
            listFertilizer.forEach(item -> {
                item.unhighlight();
            });
        }
        
        
        tempItem.highlightProcess();
        
        ModulePlanting.isPlanting = true;
        ModulePlanting.brand = tempItem.getBrand();
        ModulePlanting.type = tempItem.getType();
    }

    private void removeSeedItem() {
        listSeed.forEach(item -> {
            item.visible(false);
        });
    }

    private void initFertilizerItem() {
        listFertilizer.clear();
        List<JSONObject> listFertilizerObject = SqlDataItem.getAllItem(1);
        final int id = 1;

        for (int i = 0; i < listFertilizerObject.size(); i++) {
            JLabel tempJLabel = new JLabel();

            final int index = i;

            tempJLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent evt) {
                    itemClicked(evt, id, index);
                }
            });

            int brand = (int) listFertilizerObject.get(i).get(SQL_SYNTAX_BRAND);
            int type = (int) listFertilizerObject.get(i).get(SQL_SYNTAX_TYPE);
            int amount = (int) listFertilizerObject.get(i).get(SQL_SYNTAX_AMOUNT);

            if (amount <= 0) {
                continue;
            }

            Point tempPoint = new Point(START.getX() + (i % NUMBER_CARDINROW) * STEP_X, START.getY());
            add(tempJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(tempPoint.getX(), tempPoint.getY()), 0);

            listFertilizer.add(new Item(brand, type, tempJLabel, tempPoint));
        }
    }

    private void removeFertilizerItem() {
        listFertilizer.forEach(item -> {
            item.visible(false);
        });
    }
}
