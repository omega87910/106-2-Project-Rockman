import javax.swing.*;

public class Coin extends JLabel implements Runnable {
    private int objx, objy;
    private boolean show = false;

    public void setObjx(int objx) {
        this.objx = objx;
    }

    public int getObjx() {
        return objx;
    }

    public void setObjy(int objy) {
        this.objy = objy;
    }

    public int getObjy() {
        return objy;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public boolean getshow() {
        return show;
    }

    Coin() {
        Coin.this.setIcon(new ImageIcon(this.getClass().getResource("Action/coin.png")));
    }

    @Override
    public void run() {
        while (true) {
            if (show) {
                Coin.this.setBounds(objx, objy, Coin.this.getIcon().getIconWidth(), Coin.this.getIcon().getIconHeight());
                this.setVisible(true);
                if (objx <= -150) {
                    show = false;
                }
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}