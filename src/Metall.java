import javax.swing.*;
public class Metall extends JLabel{
    private int objx,objy;
    private boolean defence;

    public void setObjy(int objy) {
        this.objy = objy;
    }

    public int getObjy() {
        return objy;
    }

    public void setObjx(int objx) {
        this.objx = objx;
    }

    public int getObjx() {
        return objx;
    }

    public void setDefence(boolean defence) {
        this.defence = defence;
    }

    public boolean getDefence() {
        return defence;
    }

    public Metall(){
        Metall.this.setIcon(new ImageIcon("Action/metall_close.png"));
    }
}
