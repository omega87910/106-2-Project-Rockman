import javax.swing.*;

public class Bean extends JLabel implements Runnable {
    private int objx, objy;
    private boolean direction_Right, shoot_allow = true, hit = false;

    public Bean() {
        Bean.this.setIcon(new ImageIcon(this.getClass().getResource("Action/bean.png")));
    }

    public void setObjx(int objx1) {
        this.objx = objx1;
    }

    public void setObjy(int objy1) {
        this.objy = objy1;
    }

    public int getObjx() {
        return objx;
    }

    public int getObjy() {
        return objy;
    }

    public void setDirection_Right(boolean direction_Right1) {
        this.direction_Right = direction_Right1;
    }

    public void setShoot_allow(boolean shoot_allow1) {
        this.shoot_allow = shoot_allow1;
    }

    public boolean getShoot_allow() {
        return shoot_allow;
    }

    public void setHit(boolean hit1) {
        this.hit = hit1;
    }

    @Override
    public void run() {
        while (true) {
            if (!hit) {
                if (direction_Right) {
                    objx += 20;
                } else {
                    objx -= 20;
                }
                if (objx > 1180 || objx < 0) {
                    this.setShoot_allow(true);
                }
            } else {
                this.setShoot_allow(true);
            }
            Bean.this.setBounds(objx, objy, Bean.this.getIcon().getIconWidth(), Bean.this.getIcon().getIconHeight());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}