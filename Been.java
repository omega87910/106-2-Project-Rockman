import javax.swing.*;
public class Been extends JLabel{
    private int objx,objy;
    private boolean direction_Right,shoot_allow=true;
    public Been(){
        Been.this.setIcon(new ImageIcon("Action/been.png"));
    }
    public void setObjx(int objx) {
        this.objx = objx;
    }
    public void setObjy(int objy) {
        this.objy = objy;
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
}
