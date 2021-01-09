/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feature;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import proc.Main;
import proc.ModuleManager;

/**
 *
 * @author kazen
 */
public class Menu extends JPanel {

    JLabel background = new JLabel();
    JLabel play = new JLabel();
    JLabel options = new JLabel();
    JLabel quit = new JLabel();

    /* PATH */
 /* BACKGROUND */
    static final String PATH_BACKGROUND = "D:\\Project_Farm\\img\\menu\\background\\Background-menu.png";
    /* END BACKGROUND */

 /* ICON */
    static final String PATH_PLAY = "D:\\Project_Farm\\img\\menu\\icon\\Play-icon.png";
    static final String PATH_OPTIONS = "D:\\Project_Farm\\img\\menu\\icon\\Options-icon.png";
    static final String PATH_QUIT = "D:\\Project_Farm\\img\\menu\\icon\\Quit-icon.png";
    /* END ICON */
 /* END PATH */

    public Menu() {
        setMaximumSize(new Dimension(800, 600));
        setMinimumSize(new Dimension(800, 600));
        setSize(new Dimension(800, 600));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        background.setIcon(new ImageIcon(PATH_BACKGROUND));
        play.setIcon(new ImageIcon(PATH_PLAY));
        options.setIcon(new ImageIcon(PATH_OPTIONS));
        quit.setIcon(new ImageIcon(PATH_QUIT));

        add(play, new org.netbeans.lib.awtextra.AbsoluteConstraints(344, 400, -1, -1));
        add(options, new org.netbeans.lib.awtextra.AbsoluteConstraints(344, 450, -1, -1));
        add(quit, new org.netbeans.lib.awtextra.AbsoluteConstraints(344, 500, -1, -1));
        add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        play.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                playClicked(evt);
            }
        });

        options.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                optionsClicked(evt);
            }
        });

        quit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                quitClicked(evt);
            }
        });
    }

    private void playClicked(MouseEvent evt) {
        ModuleManager.plugOut(Main.mainFrame, this);

        ModuleManager.plugIn(Main.mainFrame, Main.gamePlay);
        ModuleManager.resumeProcess(Main.gamePlay);
        
        ModuleManager.revalidate(Main.mainFrame);
        ModuleManager.repaint(Main.mainFrame);
    }

    private void optionsClicked(MouseEvent evt) {
        System.out.println("ok options");
    }

    private void quitClicked(MouseEvent evt) {
        ModuleManager.quit(Main.mainFrame);
    }

}
