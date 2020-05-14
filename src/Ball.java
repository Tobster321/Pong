import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Ball {
    private int xDirection, yDirection;
    private final int[] pixels;
    private final Rectangle boundingBox;
    private final int height = 10;
    private final int width = 10;
    public int rightPoints = 0;
    public int leftPoints = 0;


    public Ball(int x, int y){
        pixels = new int[width*height];

        for (int i = 0 ; i < pixels.length ; i++)
            pixels[i] = 0xFFFFFFFF;

        boundingBox = new Rectangle(x, y, width, height);


        setXDirection(0);

        setYDirection(0);
    }

    public void setXDirection(int xdir){
        xDirection = xdir;
    }

    public void setYDirection(int ydir){
        yDirection = ydir;
    }

    public int getXDirection() {
        return xDirection;
    }

    public int getYDirection() {
        return yDirection;
    }

    public void draw(int[] Screen, int screenWidth){
        for (int i = 0 ; i < height ; i++) {
            for (int j = 0 ; j < width ; j++) {
                Screen[(boundingBox.y+i)*screenWidth + boundingBox.x+j] = pixels[i*width+j];
            }
        }
    }


    public void collision(Rectangle r){
        if(boundingBox.intersects(r)) {
            if (getXDirection() > 0 && Math.abs(r.x - (boundingBox.x + boundingBox.width)) <= getXDirection()) {
                setXDirection(-1);
            } else if (getXDirection() < 0 && Math.abs(r.x + r.width - boundingBox.x) <= -getXDirection()) {
                setXDirection(+1);
            } else if (getYDirection() > 0 && Math.abs(r.y - (boundingBox.y + boundingBox.height)) <= getYDirection()) {
                setYDirection(-1);
            } else if (getYDirection() < 0 && Math.abs(r.y + r.height - boundingBox.y) <= -getYDirection()) {
                setYDirection(+1);
            }
        }
    }

    public int move() {


        boundingBox.x += xDirection;
        boundingBox.y += yDirection;
        System.out.println();
        //Reset the ball when edge is detected
        if (boundingBox.x <= 0) {
            boundingBox.x = 190;
            boundingBox.y = 130;
            rightPoints++;
            setXDirection(0);
            setYDirection(0);
            JOptionPane.showMessageDialog(null,"Left side has " + leftPoints + " points and right side has " + rightPoints + " points" );


        }
        if (boundingBox.x >= 385) {
            boundingBox.x = 190;
            boundingBox.y = 90;
            leftPoints++;
            setXDirection(0);
            setYDirection(0);
            JOptionPane.showMessageDialog(null,"Left side has " + leftPoints + " points and right side has " + rightPoints + " points"   );
        }
        if (boundingBox.y <= 0) setYDirection(+1);
        if (boundingBox.y >= 285) setYDirection(-1);
    }

    public void keyPressed(KeyEvent e) {
        Random rand = new Random();
        int rDir = rand.nextInt(2);
        if(rDir == 0) {
            rDir--;
        }
        int yrDir = rand.nextInt(2);
        if(yrDir == 0) {
            yrDir--;
        }
        if (e.getKeyCode() == e.VK_SPACE) {
            setXDirection(rDir);
            setYDirection(yrDir);
        }
    }

    public void update(Rectangle r) {
        collision(r);
        move();
        collision(r);
    }
}
