
package processpackage;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Basket extends JPanel{
    
    JLabel background = new JLabel();
    JLabel seed = new JLabel();
    JLabel fertilizer = new JLabel();
    JLabel close = new JLabel();
    
    static final String PATH_BACKGROUND = "D:\\Project_Farm\\img\\Background\\Background-basket.png";
    static final String PATH_SEED = "D:\\Project_Farm\\img\\Button\\Basket\\Seed-tag-button.png";
    static final String PATH_SEED_LIGHTER = "D:\\Project_Farm\\img\\Button\\Basket\\Seed-tag-lighter-button.png";
    static final String PATH_FERTILIZER = "D:\\Project_Farm\\img\\Button\\Basket\\Fertilizer-tag-button.png";
    static final String PATH_FERTILIZER_LIGHTER = "D:\\Project_Farm\\img\\Button\\Basket\\Fertilizer-tag-lighter-button.png";
    static final String PATH_CLOSE = "D:\\Project_Farm\\img\\Button\\Basket\\Close-right-button.png";

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
        seed.setIcon(new ImageIcon(PATH_SEED_LIGHTER));
        fertilizer.setIcon(new ImageIcon(PATH_FERTILIZER));
        
        /* ADD GENERATE CODE HERE */
    }
    
    private void fertilizerClicked(MouseEvent evt) {
        seed.setIcon(new ImageIcon(PATH_SEED));
        fertilizer.setIcon(new ImageIcon(PATH_FERTILIZER_LIGHTER));
        
        /* ADD GENERATE CODE HERE */
    }
    
    private void closeClicked(MouseEvent evt) {
        ModuleManager.plugOut(Main.mainFrame, this);
        
        ModuleManager.revalidate(Main.mainFrame);
        ModuleManager.repaint(Main.mainFrame);
    }
    
    private void initSeed() {
        
    }
    
    private void initFertilizer() {
        
    }
    
}
