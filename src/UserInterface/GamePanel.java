package UserInterface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    private Thread thread;
    private boolean isRunning;
    private InputManager inputManager;
    public GamePanel(){
        inputManager = new InputManager();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(0, 0, GameFrame.Screen_width, GameFrame.Screen_height);
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
        long period = 1000*1000000/FPS;
        long beginTime;
        long sleepTime;
        int a = 1;
        beginTime = System.nanoTime();
        while (isRunning) {
            // Update and Render

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
