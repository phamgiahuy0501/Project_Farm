/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processpackage;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author kazen
 */
public class GamePlay extends JPanel {

    static final int STEP_X = 100;
    static final int STEP_Y = 65;
    static final int NUMBER_GROUND = 8;

    JLabel background = new JLabel();
    JLabel bag_icon = new JLabel();
    JLabel shop_icon = new JLabel();

    static final String PATH_BAG = "D:\\Project_Farm\\img\\Icon\\Bag-icon.png";
    static final String PATH_BACKGROUND = "D:\\Project_Farm\\img\\Background\\Farm-ground.png";
    static final String PATH_SHOP = "D:\\Project_Farm\\img\\Icon\\Shop-icon.png";
    static final String PATH_FREEGROUND = "D:\\Project_Farm\\img\\Ground\\free-ground.png";

    List<Point> listPoint = new ArrayList<Point>(); //list coordinate ground X Y
    List<JLabel> listGround = new ArrayList<JLabel>(); // list label ground

    public GamePlay() {
        setMaximumSize(new Dimension(800, 600));
        setMinimumSize(new Dimension(800, 600));
        setSize(new Dimension(800, 600));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        background.setIcon(new ImageIcon(PATH_BACKGROUND));
        bag_icon.setIcon(new ImageIcon(PATH_BAG));
        shop_icon.setIcon(new ImageIcon(PATH_SHOP));

        add(shop_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));
        add(bag_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

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
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                mainClicked(evt);
            }
        });
    }

    public void run(JFrame jframe) {
        jframe.add(this);
    }
    
    // STEP GENERATE GROUND X-=100 Y+=65 i=4
    private void initFreeGround() {
        int i;
        Point start_row_1 = new Point(490, 130);
        Point start_row_2 = new Point(610, 200);

        for (i = 0; i < 4; i++) {
            JLabel temp_jlabel = new JLabel();

            temp_jlabel.setIcon(new ImageIcon(PATH_FREEGROUND));
            temp_jlabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

            add(temp_jlabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(start_row_1.getX(), start_row_1.getY(), 100, 90), 0);

            listPoint.add(start_row_1);
            listGround.add(temp_jlabel);

            start_row_1.set(start_row_1.getX() - STEP_X, start_row_1.getY() + STEP_Y);
        }

        for (; i < NUMBER_GROUND; i++) {
            JLabel temp_jlabel = new JLabel();

            temp_jlabel.setIcon(new ImageIcon(PATH_FREEGROUND));
            temp_jlabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

            add(temp_jlabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(start_row_2.getX(), start_row_2.getY(), 100, 90), 0);
            
            listPoint.add(start_row_1);
            listGround.add(temp_jlabel);
            
            start_row_2.set(start_row_2.getX() - STEP_X, start_row_2.getY() + STEP_Y);
        }
    }

    private void shop_iconClicked(MouseEvent evt) {
        
    }

    private void mainClicked(MouseEvent evt) {
        System.out.println("ok main");
    }
    
    private void bag_iconClicked(MouseEvent evt) {
        System.out.println("ok bag");
    }
}
