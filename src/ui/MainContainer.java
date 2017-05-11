package ui;

import gamemain.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * Created by tonto on 5/10/2017.
 */
public class MainContainer {
    private static MainContainer instance;
    private List<Component> components;
    public static final String TAG_MENU = "tag_menu";
    public static final String TAG_START = "tag_start";
    public static final String TAG_INSTRUCTION = "tag_instruction";
    public static final String TAG_GAME = "tag_game";
    public static final String TAG_GAME_OVER = "tag_game_over";

    private MenuPanel menuPanel;
    private StartPanel startPanel;
    private GamePanel gamePanel;

    public MainContainer() {
        components = new ArrayList<>();
        instance = this;
        showPanel(TAG_MENU, true);
    }

    public static MainContainer getInstance() {
        if (instance == null) {
            instance = new MainContainer();
        }
        return instance;
    }


    public void showPanel(String tag, boolean refresh) {
        if (tag.equals(TAG_START)) {
//            if (!refresh) {
//                for (Component component : components) {
//                    System.out.println(component.getName());
//                    if (component.getName().equals(TAG_START)) {
//                        GameFrame.getInstance().setPanel((JPanel) component);
//                    }
//                }
//            } else {
//                if (startPanel != null) {
//                    components.remove(startPanel);
//                }
//                startPanel = new StartPanel();
//                components.add(startPanel);
//                Component component = components.get(components.size() - 1);
//                component.setName(TAG_START);
//                GameFrame.getInstance().setPanel(startPanel);
//            }
            showArrangeShipFrame();
        } else if (tag.equals(TAG_MENU)) {
            if (menuPanel != null) {
                components.clear();
            }
            menuPanel = new MenuPanel();
            components.add(menuPanel);
            Component component = components.get(components.size() - 1);
            component.setName(TAG_MENU);
            GameFrame.getInstance().setPanel(menuPanel);
        } else if (tag.equals(TAG_INSTRUCTION)) {
            showInstruction();
        } else if (tag.equals(TAG_GAME)) {
            if (!refresh) {
                for (Component component : components) {
                    System.out.println(component.getName());
                    if (component.getName().equals(TAG_GAME)) {
                        GameFrame.getInstance().setPanel((JPanel) component);
                    }
                }
            } else {
                if (startPanel != null) {
                    components.remove(gamePanel);
                }
                gamePanel = new GamePanel();
                components.add(gamePanel);
                Component component = components.get(components.size() - 1);
                component.setName(TAG_GAME);
                GameFrame.getInstance().setPanel(gamePanel);
            }
        }
    }

    private void showArrangeShipFrame() {
        JFrame frame = new ArrangeShipFrame();
        frame.setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        frame.setResizable( false );
        frame.pack();
        frame.setLocationRelativeTo( null );
        frame.setVisible(true);

    }

    public void onBackPressed() {
        components.remove(components.size() - 1);
        Component component = components.get(components.size() - 1);
        GameFrame.getInstance().setPanel((JPanel) component);
    }

    public void showInstruction() {
        new InstructionFrame();
    }

}
