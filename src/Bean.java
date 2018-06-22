import javax.swing.*;
public class Bean extends JLabel implements Runnable{
    private int objx,objy;
    private boolean direction_Right,shoot_allow=true;
    public Bean(){
        Bean.this.setIcon(new ImageIcon("Action/bean.png"));
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
    public boolean getDirection_Right(){
        return  direction_Right;
    }
    public void setShoot_allow(boolean shoot_allow1){
        this.shoot_allow = shoot_allow1;
    }
    public boolean getShoot_allow() {
        return shoot_allow;
    }
    @Override
    public void run() {
        while (true) {
            if (direction_Right) {
                objx += 20;
            } else {
                objx -= 20;
            }
            Bean.this.setBounds(objx, objy, Bean.this.getIcon().getIconWidth(), Bean.this.getIcon().getIconHeight());
            if (objx > 1180 || objx < 0) {
                this.setShoot_allow(true);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
