import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class MainFrame extends JFrame{
    private JLabel charcter = new JLabel();
    private ImageIcon icon =new ImageIcon("Action/default.png");
    private String icon_name;
    private Timer[] timer=new Timer[10];
    private int objx=0,objy=720-185,objw=106,objh=115;
    private int objbox;
    private double t=0,speed=0,v=0;
    private int key;
    private boolean jumpflag=true,falldowncheck,rightdirection=false,jumpdirection;
    MainFrame(){
        game();
        timer[0].start();
    }
    private void game(){
        this.setBounds(300,100,1280,720);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        charcter.setIcon(icon);
        charcter.setBounds(objx,objy,objw,objh);
        this.add(charcter);
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()== KeyEvent.VK_RIGHT){
                    objx += 5;
                    charcter.setLocation(objx,objy);
                }
                if(e.getKeyCode()==KeyEvent.VK_X && jumpflag){
                    icon =new ImageIcon("Action/jump.png");
                    objw=icon.getIconWidth();
                    objh=icon.getIconHeight();
                    charcter.setIcon(icon);
                    objbox=objy;
                    jumpflag=false;
                    timer[3].start();
                    timer[0].stop();
                    timer[1].stop();
                    while (rightdirection){
                        run();
                    }
                }
                if(e.getKeyCode()==KeyEvent.VK_C ){
                    if(jumpflag) {
                        icon = new ImageIcon("Action/shoot.png");
                        objw = icon.getIconWidth();
                        objh = icon.getIconHeight();
                        charcter.setIcon(icon);
                    }
                    else{
                        icon = new ImageIcon("Action/jumpshoot.png");
                        objw = icon.getIconWidth();
                        objh = icon.getIconHeight();
                        charcter.setIcon(icon);
                    }
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
//                if (e.getKeyCode()==KeyEvent.VK_X){
//                    timer[3].stop();
//                    timer[4].start();
//                }
            }
        });
        /////////////////////////closeeye//////////////////////////////
        timer[0] = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(charcter.getIcon()==icon) {
                    icon = new ImageIcon("Action/closeeye.png");
                    charcter.setIcon(icon);
                    timer[1].start();
                }
            }
        });
        ////////////////////////Default//////////////////////////////
        timer[1] =new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                icon =new ImageIcon("Action/default.png");
                charcter.setIcon(icon);
                timer[1].stop();
            }
        });
        ////////////////////////jump///////////////////////
        timer[3] = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                v=30;
                t += 0.1;
                speed = v-9.8*t;
                if(speed<=0 && speed>-v){
                    objy += -speed*t+(1/2)*(-9.8*t*t);
                }
                else if(speed>0){
                    objy -= speed* t +(1 / 2) * (-9.8 * t * t);
                    if(speed-9.8*(t+0.1)<=0){
                        speed=0;
                    }
                }
                else if(speed <= -v){
                    timer[3].stop();
                    timer[1].start();
                    timer[0].start();
                    jumpflag=true;
                }

                charcter.setBounds(objx,objy,objw,objh);
                charcter.setLocation(objx,objy);

            }
        });
        /////////////////////////jumpfalldown/////////////////////////////
        timer[4] =new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                charcter.setBounds(objx,objy,objw,objh);
                charcter.setLocation(objx,objy);
                if(objy==720-185){
                    timer[4].stop();
                    timer[1].start();
                    timer[0].start();
                    jumpflag=true;
                    t=0;
                }
            }
        });
        ////////////////////////walk/////////////////////////////////////////

    }
    private void run(){
        if(key==KeyEvent.VK_RIGHT){
            objx += 5;
        }
    }
}
