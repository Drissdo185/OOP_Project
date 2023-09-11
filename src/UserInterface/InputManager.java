package UserInterface;

import java.awt.event.KeyEvent;

public class InputManager {
    public void processKeyPressed(int keyCode){
        switch (keyCode){
            case KeyEvent.VK_UP:
                System.out.println("Moving up");
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("Moving down");
                break;
            case KeyEvent.VK_LEFT:
                System.out.println("Moving left");
                break;
            case KeyEvent.VK_RIGHT:
                System.out.println("Moving right");
                break;
            case KeyEvent.VK_ENTER:
                System.out.println("Pressed Enter");
                break;
            case KeyEvent.VK_SPACE:
                System.out.println("Pressed Space");
                break;
            case KeyEvent.VK_A:
                System.out.println("Pressed A");
                break;


        }
    }
    public void processKeyReleased(int keyCode){
        switch (keyCode){
            case KeyEvent.VK_UP:
                System.out.println("Released Moving up");
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("Released Moving down");
                break;
            case KeyEvent.VK_LEFT:
                System.out.println("Released Moving left");
                break;
            case KeyEvent.VK_RIGHT:
                System.out.println("Released Moving right");
                break;
            case KeyEvent.VK_ENTER:
                System.out.println("Released Pressed Enter");
                break;
            case KeyEvent.VK_SPACE:
                System.out.println("Released Pressed Space");
                break;
            case KeyEvent.VK_A:
                System.out.println("Released Pressed A");
                break;


        }
    }
}
