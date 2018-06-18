import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
class MainFrame extends JFrame {
    Rockman rockman = new Rockman();
    Timer timer[] =new Timer[2] ;
    private JPanel jpn =new JPanel();
    private JLabel background1 = new JLabel();
    private JLabel background2 = new JLabel();
    private JLabel background3 = new JLabel();
    Been[] been = new Been[3];
    private int lb1X = 0 , lb2X = 884 , lb3X = 1262;
    private ImageIcon icon = new ImageIcon("Action/default-r.png");
    private Container cp;
    Thread thread = new Thread(rockman);
    MainFrame() {
        game();
        thread.start();
        timer[0].start();
    }
    private void game() {
        been[0]=new Been();
        been[1]=new Been();
        been[2]=new Been();
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
        been[0].setIcon(new ImageIcon("Action/been.png"));
        been[1].setIcon(new ImageIcon("Action/been.png"));
        been[2].setIcon(new ImageIcon("Action/been.png"));
        jpn.add(rockman);
        jpn.add(been[0]);
        jpn.add(been[1]);
        jpn.add(been[2]);
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
                        if(been[0].getShoot_allow()) {
                            if (rockman.getDirection_Right()) {
                                been[0].setObjx(rockman.getObjx() + rockman.getIcon().getIconWidth());
                            } else {
                                been[0].setObjx(rockman.getObjx());
                            }
                            been[0].setObjy(rockman.getObjy() + 50);
                            been[0].setBounds(been[0].getObjx(), been[0].getObjy(), been[0].getIcon().getIconWidth(), been[0].getIcon().getIconHeight());
                            been[0].setDirection_Right(rockman.getDirection_Right());
                            been[0].setShoot_allow(false);
                        }else if(been[1].getShoot_allow()) {
                            if (rockman.getDirection_Right()) {
                                been[1].setObjx(rockman.getObjx() + rockman.getIcon().getIconWidth());
                            } else {
                                been[1].setObjx(rockman.getObjx());
                            }
                            been[1].setObjy(rockman.getObjy() + 50);
                            been[1].setBounds(been[1].getObjx(), been[1].getObjy(), been[1].getIcon().getIconWidth(), been[1].getIcon().getIconHeight());
                            been[1].setDirection_Right(rockman.getDirection_Right());
                            been[1].setShoot_allow(false);

                        }else if(been[2].getShoot_allow()) {
                            if (rockman.getDirection_Right()) {
                                been[2].setObjx(rockman.getObjx() + rockman.getIcon().getIconWidth());
                            } else {
                                been[2].setObjx(rockman.getObjx());
                            }
                            been[2].setObjy(rockman.getObjy() + 50);
                            been[2].setBounds(been[2].getObjx(), been[2].getObjy(), been[2].getIcon().getIconWidth(), been[2].getIcon().getIconHeight());
                            been[2].setDirection_Right(rockman.getDirection_Right());
                            been[2].setShoot_allow(false);

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
            }
        });
        timer[1]=new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(been[0].getDirection_Right()) {
                    been[0].setObjx(been[0].getObjx() + 2);
                }else{
                    been[0].setObjx(been[0].getObjx() - 2);
                }
                been[0].setLocation(been[0].getObjx(),been[0].getObjy());
                if(been[0].getObjx()>1180 || been[0].getObjx()<0){
                    been[0].setShoot_allow(true);
                }
                ////////
                if(been[1].getDirection_Right()) {
                    been[1].setObjx(been[1].getObjx() + 2);
                }else{
                    been[1].setObjx(been[1].getObjx() - 2);
                }
                been[1].setLocation(been[1].getObjx(),been[1].getObjy());
                if(been[1].getObjx()>1180 || been[1].getObjx()<0){
                    been[1].setShoot_allow(true);
                }
                ////////
                if(been[2].getDirection_Right()) {
                    been[2].setObjx(been[2].getObjx() + 2);
                }else{
                    been[2].setObjx(been[2].getObjx() - 2);
                }
                been[2].setLocation(been[2].getObjx(),been[2].getObjy());
                if(been[2].getObjx()>1180 || been[2].getObjx()<0){
                    been[2].setShoot_allow(true);
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