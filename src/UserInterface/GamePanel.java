package UserInterface;
import effect.Animation;
import effect.CacheDataLoader;
import effect.FrameImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    private Thread thread;
    private boolean isRunning;
    private InputManager inputManager;
    FrameImage frame1;
    Animation anim1;


    public GamePanel() {
        inputManager = new InputManager();
        frame1 = CacheDataLoader.getInstance().getFrameImage("idleshoot1");
        anim1 = CacheDataLoader.getInstance().getAnimation("runshoot");
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, GameFrame.Screen_width, GameFrame.Screen_height);

        Graphics2D g2 = (Graphics2D) g;
        frame1.draw(130,130,g2);

        anim1.draw(300, 300, g2);
    }

    public void startGame() {
        if (thread == null) {
            isRunning = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    @Override
    public void run() {
        long FPS = 80;
        long period = 1000000000/80;
        long beginTime;
        long sleepTime;
        beginTime = System.nanoTime();
        while (isRunning) {
            // Update and Render

            
            anim1.Update(System.nanoTime());
            repaint();

            long detalTime = System.nanoTime() - beginTime; // nanoTime la lay thoi gian he thong
            sleepTime = period - detalTime;
            try {
                if(sleepTime>0) {
                    Thread.sleep(sleepTime/1000000);
                }else {Thread.sleep(period/2000000);}
            } catch (InterruptedException ex) {
            }
            beginTime = System.nanoTime();
        }
    }
    // callback method: duoc cac class khac goi
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        inputManager.processKeyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        inputManager.processKeyReleased(e.getKeyCode());

    }
}
