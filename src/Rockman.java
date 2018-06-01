import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Rockman extends JLabel implements Runnable {
    protected int objx, objy, v = 65, t = 1;
    protected boolean running, jumpping, direction_Right;

    public Rockman() {
    }

    public void setObjx(int objx1) {
        this.objx = objx1;
    }

    public int getv() {
        return v;
    }

    public void setDirection_Right(boolean direction_Right1) {
        this.direction_Right = direction_Right1;
    }

    public boolean getDirection_Right() {
        return direction_Right;
    }

    public void setJumpping(boolean jumpping1) {
        this.jumpping = jumpping1;
    }

    public boolean getJumpping() {
        return jumpping;
    }

    public void setObjy(int objy1) {
        this.objy = objy1;
    }

    public void run_stop() {
        running = false;
    }

    public void run_start() {
        running = true;
    }

    @Override
    public void run() {
        while (true) {
            if (running) {
                if (direction_Right) {
                    objx += 10;
                    System.out.println("666");
                } else {
                    objx -= 10;
                    System.out.println("666");
                }
                Rockman.this.setBounds(objx, objy+12, this.getIcon().getIconWidth(), this.getIcon().getIconHeight());
            } else {
                System.out.println("stop");
            }
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (jumpping) {
                v = v - 5 * t;
                if (v <= 0 && v > -65) {
                    objy -= v * t + (1 / 2) * (-10 * t * t);
                } else if (v == -65) {
                    if (direction_Right) {
                        Rockman.this.setIcon(new ImageIcon("Action/default-r.png"));
                    } else {
                        Rockman.this.setIcon(new ImageIcon("Action/default-l.png"));
                    }
                    jumpping = false;
                    v = 65;
                } else if (v > 0) {
                    objy -= v * t + (1 / 2) * (-10 * t * t);
                }
                Rockman.this.setBounds(objx, objy+12, Rockman.this.getIcon().getIconWidth(), Rockman.this.getIcon().getIconHeight());
            }
        }
    }

}
