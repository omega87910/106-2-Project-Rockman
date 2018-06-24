import javax.swing.*;

public class PetitGoblin extends JLabel implements Runnable {
    private int objx, objy=-100,v=5;
    private Boolean show = false;
    public void setObjx(int objx1) {
        this.objx = objx1;
    }
    public void setV(int v) {
        this.v = v;
    }
    public int getObjx() {
        return objx;
    }

    public void setObjy(int objy1) {
        this.objy = objy1;
    }

    public int getObjy() {
        return objy;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public Boolean getShow() {
        return show;
    }

    PetitGoblin() {
        PetitGoblin.this.setIcon(new ImageIcon("Action/petitgoblin_o.png"));
    }

    @Override
    public void run() {
        while (true) {
            objx -= v;
            PetitGoblin.this.setBounds(objx, objy, PetitGoblin.this.getIcon().getIconWidth(), PetitGoblin.this.getIcon().getIconHeight());
            if (objx <= -150) {
                PetitGoblin.this.show = false;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}