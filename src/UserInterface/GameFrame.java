package UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class GameFrame extends JFrame {
    public static final int Screen_width = 1300; //fixed size for height and width
    public static final int Screen_height = 900; // final: bien se khong doi
    GamePanel gamePanel;
    public GameFrame(){
        Toolkit toolkit = this.getToolkit();
        Dimension dimension = toolkit.getScreenSize(); // get size of Screen
        this.setBounds((dimension.width-Screen_width)/2,(dimension.height-Screen_height)/2,Screen_width,Screen_height); // set cai gioi han
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gamePanel = new GamePanel();
        add(gamePanel);
        this.addKeyListener(gamePanel);

    }
    public void startGame(){
        gamePanel.startGame();
    }
    public static void main(String[] args) {
        GameFrame gameframe = new GameFrame();
        gameframe.setVisible(true);
        gameframe.startGame();

        }
    }
