import javax.swing.*;
import java.util.Random;

public class Metall extends JLabel implements Runnable {
    private int objx, objy = -100;
    private boolean show = false, defence = true, dead;
    private Random rd = new Random();
    private int defence_time = 0, defence_time_random, open_time = 0,open_time_random;

    public void setObjy(int objy) {
        this.objy = objy;
    }

    public void setObjx(int objx) {
        this.objx = objx;
    }

    public int getObjx() {
        return objx;
    }

    public boolean getDefence() {
        return defence;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public boolean getShow() {
        return show;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public Metall() {
        Metall.this.setIcon(new ImageIcon(this.getClass().getResource("Action/metall_close.png")));
        defence_time_random = rd.nextInt(100) + 30;
        open_time_random=50+rd.nextInt(80);
    }

    @Override
    public void run() {
        while (true) {
            if (!dead) {
                if (defence) {
                    Metall.this.setIcon(new ImageIcon(this.getClass().getResource("Action/metall_close.png")));
                    Metall.this.setBounds(objx, objy, Metall.this.getIcon().getIconWidth(), Metall.this.getIcon().getIconHeight());
                } else {
                    open_time++;
                    Metall.this.setBounds(objx, objy - 18, Metall.this.getIcon().getIconWidth(), Metall.this.getIcon().getIconHeight());
                    if (open_time >= open_time_random) {
                        open_time_random=50+rd.nextInt(80);
                        defence = true;
                        open_time = 0;
                    }
                }
                if (defence_time == defence_time_random) {
                    defence_time = 0;
                    defence = false;
                    Metall.this.setIcon(new ImageIcon(this.getClass().getResource("Action/metall_open.png")));
                    Metall.this.setBounds(objx, objy - 18, Metall.this.getIcon().getIconWidth(), Metall.this.getIcon().getIconHeight());
                    defence_time_random = rd.nextInt(250) + 100;
                }
            }
            if (objx <= -150 || dead) {
                defence = true;
                open_time = 0;
                defence_time = 0;
                dead = false;
                show = false;
                Metall.this.setIcon(new ImageIcon(this.getClass().getResource("Action/metall_close.png")));
                defence_time_random = rd.nextInt(200) + 100;
            }
            defence_time++;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}