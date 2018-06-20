import javafx.scene.media.AudioClip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
class MainFrame extends JFrame {
    private AudioClip shootsound =new AudioClip(getClass().getResource("sfx3.wav").toString());
    private AudioClip bgm1 =new AudioClip(getClass().getResource("NinjaGaiden.mp3").toString());
    private AudioClip bgm2 =new AudioClip(getClass().getResource("RyuHayabusaTheme.mp3").toString());
    private AudioClip bgm;
    private Rockman rockman = new Rockman();
    private Timer timer[] =new Timer[2] ;
    private JPanel jpn =new JPanel();
    private JLabel background1 = new JLabel();
    private JLabel background2 = new JLabel();
    private JLabel background3 = new JLabel();
    private Bean[] bean = new Bean[3];
    private int lb1X = 0 , lb2X = 884 , lb3X = 1262;
    private ImageIcon icon = new ImageIcon("Action/default-r.png");
    private Container cp;
    Thread thread = new Thread(rockman);
    MainFrame() {
        game();
        thread.start();
        timer[0].start();
        if(FrameStart.getbgm()){
            bgm=bgm1;
            bgm.play();
        }else {
            bgm=bgm2;
            bgm.play();
        }

    }
    private void game() {
        bean[0]=new Bean();
        bean[1]=new Bean();
        bean[2]=new Bean();
        this.setBounds(300, 100, 1280 - 10, 720);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        cp=this.getContentPane();
        rockman.setIcon(icon);
        rockman.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        background1.setBounds(0, 0, 884, 685);
        background1.setIcon(new ImageIcon("./bg1.png"));
        background2.setBounds(880, 0, 380, 685);
        background2.setIcon(new ImageIcon("./bg2.png"));
        background3.setBounds(1258, 0, 884, 685);
        background3.setIcon(new ImageIcon("./bg1.png"));
        bean[0].setIcon(new ImageIcon("Action/bean.png"));
        bean[1].setIcon(new ImageIcon("Action/bean.png"));
        bean[2].setIcon(new ImageIcon("Action/bean.png"));
        jpn.add(rockman);
        jpn.add(bean[0]);
        jpn.add(bean[1]);
        jpn.add(bean[2]);
        jpn.add(background1);
        jpn.add(background2);
        jpn.add(background3);
        cp.add(jpn);
        jpn.setLayout(null);
        //////////////////////////key/////////////////////////////////////
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if (rockman.getJumpping()) {
                        rockman.setIcon(new ImageIcon("Action/jump-r.png"));
                    }
                    rockman.run_start();
                    rockman.setDirection_Right(true);
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    if (rockman.getJumpping()) {
                        rockman.setIcon(new ImageIcon("Action/jump-l.png"));
                    }
                    rockman.run_start();
                    rockman.setDirection_Right(false);
                }
                if (e.getKeyCode() == KeyEvent.VK_X && !rockman.getJumpping()) {
                    rockman.setJumpping(true);
                    if (rockman.getDirection_Right()) {
                        rockman.setIcon(new ImageIcon("Action/jump-r.png"));
                    } else {
                        rockman.setIcon(new ImageIcon("Action/jump-l.png"));
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_C) {
                    if (!rockman.getJumpping() && !rockman.getRunning() && !rockman.getDirection_Right() && !rockman.getShooting()) {
                        rockman.setObjx(rockman.getObjx() - 50);
                    }

                    if(rockman.getJumpping() && !rockman.getDirection_Right()){
                        rockman.setObjx(rockman.getObjx() - 14);
                    }
                    if(!rockman.getShooting()) {
                        if(bean[0].getShoot_allow()) {
                            if (rockman.getDirection_Right()) {
                                bean[0].setObjx(rockman.getObjx() + rockman.getIcon().getIconWidth());
                            } else {
                                bean[0].setObjx(rockman.getObjx());
                            }
                            bean[0].setObjy(rockman.getObjy() + 50);
                            bean[0].setBounds(bean[0].getObjx(), bean[0].getObjy(), bean[0].getIcon().getIconWidth(), bean[0].getIcon().getIconHeight());
                            bean[0].setDirection_Right(rockman.getDirection_Right());
                            bean[0].setShoot_allow(false);
                            shootsound.play();
                        }else if(bean[1].getShoot_allow()) {
                            if (rockman.getDirection_Right()) {
                                bean[1].setObjx(rockman.getObjx() + rockman.getIcon().getIconWidth());
                            } else {
                                bean[1].setObjx(rockman.getObjx());
                            }
                            bean[1].setObjy(rockman.getObjy() + 50);
                            bean[1].setBounds(bean[1].getObjx(), bean[1].getObjy(), bean[1].getIcon().getIconWidth(), bean[1].getIcon().getIconHeight());
                            bean[1].setDirection_Right(rockman.getDirection_Right());
                            bean[1].setShoot_allow(false);
                            shootsound.play();
                        }else if(bean[2].getShoot_allow()) {
                            if (rockman.getDirection_Right()) {
                                bean[2].setObjx(rockman.getObjx() + rockman.getIcon().getIconWidth());
                            } else {
                                bean[2].setObjx(rockman.getObjx());
                            }
                            bean[2].setObjy(rockman.getObjy() + 50);
                            bean[2].setBounds(bean[2].getObjx(), bean[2].getObjy(), bean[2].getIcon().getIconWidth(), bean[2].getIcon().getIconHeight());
                            bean[2].setDirection_Right(rockman.getDirection_Right());
                            bean[2].setShoot_allow(false);
                            shootsound.play();
                        }
                        rockman.setShooting(true);
                        timer[1].start();
                    }
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT) {
                        rockman.run_stop();
                }
            }
        });
        timer[0] = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                background1.setLocation(lb1X,0);
                background2.setLocation(lb2X,0);
                background3.setLocation(lb3X,0);
                if (lb1X <= -880){
                    jpn.remove(background1);
                    jpn.add(background1);
                    background1.setBounds(1258,0,884,685);
                    lb1X = 1258;
                    repaint();
                }
                if (lb2X <= -374){
                    jpn.remove(background2);
                    jpn.add(background2);
                    background2.setBounds(1764,0,380,685);
                    lb2X = 1764;
                    repaint();
                }
                if (lb3X <= -880){
                    jpn.remove(background3);
                    jpn.add(background3);
                    background3.setBounds(1258,0,884,685);
                    lb3X = 1258;
                    repaint();
                }
                lb1X -= 1 ;
                lb2X -= 1 ;
                lb3X -= 1 ;
                if(rockman.getX()-1>=0) {
                    rockman.setObjx(rockman.getObjx() - 1);
                }else{
                    if(!rockman.getShooting()) {
                        rockman.setObjx(0);
                    }
                }
                if(!bgm.isPlaying()){
                        bgm.play();
                }
            }
        });
        timer[1]=new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(bean[0].getDirection_Right()) {
                    bean[0].setObjx(bean[0].getObjx() + 3);
                }else{
                    bean[0].setObjx(bean[0].getObjx() - 3);
                }
                bean[0].setLocation(bean[0].getObjx(), bean[0].getObjy());
                if(bean[0].getObjx()>1180 || bean[0].getObjx()<0){
                    bean[0].setShoot_allow(true);
                }
                ////////
                if(bean[1].getDirection_Right()) {
                    bean[1].setObjx(bean[1].getObjx() + 3);
                }else{
                    bean[1].setObjx(bean[1].getObjx() - 3);
                }
                bean[1].setLocation(bean[1].getObjx(), bean[1].getObjy());
                if(bean[1].getObjx()>1180 || bean[1].getObjx()<0){
                    bean[1].setShoot_allow(true);
                }
                ////////
                if(bean[2].getDirection_Right()) {
                    bean[2].setObjx(bean[2].getObjx() + 3);
                }else{
                    bean[2].setObjx(bean[2].getObjx() - 3);
                }
                bean[2].setLocation(bean[2].getObjx(), bean[2].getObjy());
                if(bean[2].getObjx()>1180 || bean[2].getObjx()<0){
                    bean[2].setShoot_allow(true);
                }
            }
        });
        /////////////////////////closeeye//////////////////////////////
//        timer[0] = new Timer(2000, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (rockman.getIcon() == new ImageIcon("Action/default-r.png") ||rockman.getIcon() == new ImageIcon("Action/default-l.png") ) {
//                    if (direction_Right) {
//                        icon = new ImageIcon("Action/closeeye-r.png");
//                    } else {
//                        icon = new ImageIcon("Action/closeeye-l.png");
//                    }
//                    rockman.setIcon(icon);
//                    timer[1].start();
//                }
//            }
//        });
        ////////////////////////Default//////////////////////////////
//        timer[1] = new Timer(100, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (rockman.getIcon() == new ImageIcon("Action/default-r.png") ||rockman.getIcon() == new ImageIcon("Action/default-l.png")) {
//                    if (direction_Right) {
//                        icon = new ImageIcon("Action/default-r.png");
//                    } else {
//                        icon = new ImageIcon("Action/default-l.png");
//                    }
//                }
//                rockman.setIcon(icon);
//                timer[1].stop();
//
//        }
//        });
        /////////////////////////shoot/////////////////////////////
    }
}