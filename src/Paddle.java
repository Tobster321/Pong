import java.awt.*;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Paddle {
    private int xDirection;
    private int yDirection;
    private int[] pixels;
    private Rectangle boundingBox;
    private int width = 10;
    private int height = 40;
    private int leftYSpeedUp = -3;
    private int leftYspeedDown = 3;

    public Paddle(int x, int y, int col){
        boundingBox = new Rectangle(x, y, width, height);
        pixels = new int[width*height];
        for (int i = 0 ; i < pixels.length ; i++) {
            pixels[i] = col;
        }
        boundingBox.x = 10;
        boundingBox.y = 120;
    }

    // comeback system
    public void comeback(int leftPoints, int rightPoints) {
        int lp = leftPoints + 1;

        if (lp < rightPoints) {
            leftYspeedDown = 4;
            leftYSpeedUp = -4;
        }

        if (!(lp < rightPoints)) {
            leftYspeedDown = 3;
            leftYSpeedUp = -3;
        }
    }

    // Movement
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == e.VK_W){
            setYDirection(leftYSpeedUp);
        }
        if(e.getKeyCode() == e.VK_S){
            setYDirection(leftYspeedDown);
        }
    }

    public void keyReleased(KeyEvent e){
        if(e.getKeyCode() == e.VK_W){
            setYDirection(0);
        }
        if(e.getKeyCode() == e.VK_S){
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

    public void update(int leftPoints, int rightPoints){
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