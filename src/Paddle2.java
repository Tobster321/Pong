import java.awt.*;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Paddle2 {
    private int xDirection;
    private int yDirection;
    private int[] pixels;
    private Rectangle boundingBox;
    private int width = 10;
    private int height = 40;
    private double rightYSpeedUp = -3;
    private double rightYspeedDown = 3;

    public Paddle2(int x, int y, int col){
        boundingBox = new Rectangle(x, y, width, height);
        pixels = new int[width*height];
        for (int i = 0 ; i < pixels.length ; i++) {
            pixels[i] = col;
        }
        boundingBox.x = 390;
        boundingBox.y = 120;
    }
    // Comeback system
    public void comeback(int leftPoints, int rightPoints) {
        int rp = rightPoints + 1;

        if (rp < leftPoints) {
            rightYspeedDown = 4;
            rightYSpeedUp = -4;
        }

        if (!(rp < leftPoints)) {
            rightYspeedDown = 3;
            rightYSpeedUp = -3;
        }
    }
    // movement
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == e.VK_UP){
            setYDirection((int) rightYSpeedUp);
        }
        if(e.getKeyCode() == e.VK_DOWN){
            setYDirection((int) rightYspeedDown);
        }
    }

    public void keyReleased(KeyEvent e){
        if(e.getKeyCode() == e.VK_UP){
            setYDirection(0);
        }
        if(e.getKeyCode() == e.VK_DOWN){
            setYDirection(0);
        }

    }

    public void setXDirection(int xdir) {
        xDirection = xdir;
    }

    public void setYDirection(int ydir){
        yDirection = ydir;
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public void update(int rightPoints, int leftPoints){
        boundingBox.x += xDirection;
        if(boundingBox.x <= 0) {
            boundingBox.x = 0;
        }
        if(boundingBox.x >= 380) {
            boundingBox.x = 380;
        }
        boundingBox.y += yDirection;
        if(boundingBox.y <= 0) {
            boundingBox.y = 0;
        }
        if(boundingBox.y >= 260) {
            boundingBox.y = 260;
        }
        comeback(leftPoints, rightPoints);
    }

    public void draw(int[] Screen, int screenWidth){
        for (int i = 0 ; i < height ; i++) {
            for (int j = 0 ; j < width ; j++) {
                Screen[(boundingBox.y+i)*screenWidth + boundingBox.x+j] = pixels[i*width+j];
            }
        }
    }

    }

