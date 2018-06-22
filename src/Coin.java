import javax.swing.*;
public class Coin extends JLabel implements Runnable{
    private int objx,objy;
    private boolean show=false,rm=false;
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
    public boolean getshow(){
        return show;
    }
    Coin(){
       Coin.this.setIcon(new ImageIcon("coin.png"));
    }
    @Override
    public void run() {
        while(true){
            if(show) {
                objx -= 5;
                Coin.this.setBounds(objx, objy, Coin.this.getIcon().getIconWidth(), Coin.this.getIcon().getIconHeight());
                this.setVisible(true);
                if(objx<=-50 ){
                    show=false;
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
