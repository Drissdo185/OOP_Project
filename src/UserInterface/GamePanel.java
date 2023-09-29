package UserInterface;
import effect.Animation;
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
    FrameImage frame1, frame2, frame3;
    Animation anim;


    public GamePanel() {
        inputManager = new InputManager();
        try {
            BufferedImage image = ImageIO.read(new File("data/megasprite.png"));
            BufferedImage image1 = image.getSubimage(529, 38, 85, 100);
            frame1 = new FrameImage("frame1", image1);
            BufferedImage image2 = image.getSubimage(616, 38, 85, 100);
            frame2 = new FrameImage("frame2", image2);
            BufferedImage image3 = image.getSubimage(703, 38, 85, 100);
            frame3 = new FrameImage("frame3", image3);

            anim = new Animation();
            anim.add(frame1, 500 * 1000000);
            anim.add(frame2, 500 * 1000000);
            anim.add(frame3, 500 * 1000000);
        } catch (IOException ex) {}
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, GameFrame.Screen_width, GameFrame.Screen_height);

        Graphics2D g2 = (Graphics2D) g;
        anim.Update(System.nanoTime());
        System.out.println("Current Frame: " + anim.getCurrentFrame());
        anim.draw(100, 100, g2);
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
