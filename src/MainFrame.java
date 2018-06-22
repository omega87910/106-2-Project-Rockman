import javafx.scene.media.AudioClip;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

class MainFrame extends JFrame {
    private AudioClip shootsound = new AudioClip(getClass().getResource("sound/sfx3.wav").toString());
    private AudioClip coinsound =new AudioClip(getClass().getResource("sound/coin.wav").toString());
    private AudioClip bgm;
    private int key_jump = FrameStart.getKey_jump(), key_shoot = FrameStart.getKey_shoot();
    private Rockman rockman = new Rockman();
    private Timer timer[] = new Timer[2];
    private JPanel jpn = new JPanel();
    private JLabel background1 = new JLabel();
    private JLabel background2 = new JLabel();
    private JLabel background3 = new JLabel();
    private JLabel jlb_score =new JLabel();
    private Bean[] bean = new Bean[3];
    private Coin[] coin = new Coin[6];
    private int lb1X = 0, lb2X = 884, lb3X = 1262;
    private Random random = new Random();
    private int score=0;
    MainFrame() {
        game();
        Thread rockman_thread = new Thread(rockman);
        Thread bean1_thread = new Thread(bean[0]);
        Thread bean2_thread = new Thread(bean[1]);
        Thread bean3_thread = new Thread(bean[2]);
        Thread coin0_thread = new Thread(coin[0]);
        Thread coin1_thread = new Thread(coin[1]);
        Thread coin2_thread = new Thread(coin[2]);
        Thread coin3_thread = new Thread(coin[3]);
        Thread coin4_thread = new Thread(coin[4]);
        Thread coin5_thread = new Thread(coin[5]);
        rockman_thread.start();
        bean1_thread.start();
        bean2_thread.start();
        bean3_thread.start();
        coin0_thread.start();
        coin1_thread.start();
        coin2_thread.start();
        coin3_thread.start();
        coin4_thread.start();
        coin5_thread.start();
        timer[0].start();
        timer[1].start();
        switch (FrameStart.getbgm()) {
            case 1:
                bgm = new AudioClip(getClass().getResource("sound/NinjaGaiden.mp3").toString());
                break;
            case 2:
                bgm = new AudioClip(getClass().getResource("sound/RyuHayabusaTheme.mp3").toString());
                break;
            case 3:
                bgm = null;
                break;
            default:
                bgm.play();
                break;
        }
    }

    private void game() {
        bean[0] = new Bean();
        bean[1] = new Bean();
        bean[2] = new Bean();
        coin[0] = new Coin();
        coin[1] = new Coin();
        coin[2] = new Coin();
        coin[3] = new Coin();
        coin[4] = new Coin();
        coin[5] = new Coin();
        this.setTitle("Rockman");
        this.setBounds(300, 100, 1280 - 10, 720);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        Container cp = this.getContentPane();
        rockman.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        jlb_score.setBounds(800,0,800,100);
        jlb_score.setFont(new Font(null,Font.BOLD,50));
        jlb_score.setText("SCORE:"+score);
        background1.setBounds(0, 0, 884, 685);
        background1.setIcon(new ImageIcon("./bg1.png"));
        background2.setBounds(880, 0, 380, 685);
        background2.setIcon(new ImageIcon("./bg2.png"));
        background3.setBounds(1258, 0, 884, 685);
        background3.setIcon(new ImageIcon("./bg1.png"));
        jpn.add(rockman);
        jpn.add(bean[0]);
        jpn.add(bean[1]);
        jpn.add(bean[2]);
        jpn.add(coin[0]);
        jpn.add(coin[1]);
        jpn.add(coin[2]);
        jpn.add(coin[3]);
        jpn.add(coin[4]);
        jpn.add(coin[5]);
        jpn.add(jlb_score);
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
                if (e.getKeyCode() == key_jump && !rockman.getJumpping()) {
                    rockman.setJumpping(true);
                    if (rockman.getDirection_Right()) {
                        rockman.setIcon(new ImageIcon("Action/jump-r.png"));
                    } else {
                        rockman.setIcon(new ImageIcon("Action/jump-l.png"));
                    }
                }
                if (e.getKeyCode() == key_shoot) {
                    if (!rockman.getJumpping() && !rockman.getRunning() && !rockman.getDirection_Right() && !rockman.getShooting() && (bean[0].getShoot_allow() || bean[1].getShoot_allow() || bean[2].getShoot_allow())) {
                        rockman.setObjx(rockman.getObjx() - 50);
                    }

                    if (rockman.getJumpping() && !rockman.getDirection_Right() && (bean[0].getShoot_allow() || bean[1].getShoot_allow() || bean[2].getShoot_allow())) {
                        rockman.setObjx(rockman.getObjx() - 14);
                    }
                    if (!rockman.getShooting()) {
                        if (bean[0].getShoot_allow()) {
                            if (rockman.getDirection_Right()) {
                                bean[0].setObjx(rockman.getObjx() + rockman.getIcon().getIconWidth());
                            } else {
                                bean[0].setObjx(rockman.getObjx());
                            }
                            bean[0].setObjy(rockman.getObjy() + 50);
                            bean[0].setDirection_Right(rockman.getDirection_Right());
                            bean[0].setShoot_allow(false);
                            rockman.setShooting(true);
                            shootsound.play();
                        } else if (bean[1].getShoot_allow()) {
                            if (rockman.getDirection_Right()) {
                                bean[1].setObjx(rockman.getObjx() + rockman.getIcon().getIconWidth());
                            } else {
                                bean[1].setObjx(rockman.getObjx());
                            }
                            bean[1].setObjy(rockman.getObjy() + 50);
                            bean[1].setDirection_Right(rockman.getDirection_Right());
                            bean[1].setShoot_allow(false);
                            rockman.setShooting(true);
                            shootsound.play();
                        } else if (bean[2].getShoot_allow()) {
                            if (rockman.getDirection_Right()) {
                                bean[2].setObjx(rockman.getObjx() + rockman.getIcon().getIconWidth());
                            } else {
                                bean[2].setObjx(rockman.getObjx());
                            }
                            bean[2].setObjy(rockman.getObjy() + 50);
                            bean[2].setDirection_Right(rockman.getDirection_Right());
                            bean[2].setShoot_allow(false);
                            rockman.setShooting(true);
                            shootsound.play();
                        }
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
                background1.setLocation(lb1X, 0);
                background2.setLocation(lb2X, 0);
                background3.setLocation(lb3X, 0);
                if (lb1X <= -880) {
                    jpn.remove(background1);
                    jpn.add(background1);
                    background1.setBounds(1258, 0, 884, 685);
                    lb1X = 1258;
                    repaint();
                }
                if (lb2X <= -374) {
                    jpn.remove(background2);
                    jpn.add(background2);
                    background2.setBounds(1764, 0, 380, 685);
                    lb2X = 1764;
                    repaint();
                }
                if (lb3X <= -880) {
                    jpn.remove(background3);
                    jpn.add(background3);
                    background3.setBounds(1258, 0, 884, 685);
                    lb3X = 1258;
                    repaint();
                }
                lb1X -= 1;
                lb2X -= 1;
                lb3X -= 1;
                if (rockman.getX() - 1 >= 0) {
                    rockman.setObjx(rockman.getObjx() - 1);
                } else {
                    if (!rockman.getShooting()) {
                        rockman.setObjx(0);
                    }
                }
                if (bgm != null) {
                    if (!bgm.isPlaying()) {
                        bgm.play();
                    }
                }
                for(int i=0;i<6;i++) {
                    if ((coin[i].getObjx()+(coin[i].getObjx()+ coin[i].getIcon().getIconWidth()))/2 >=rockman.getX() &&(coin[i].getObjx()+(coin[i].getObjx()+ coin[i].getIcon().getIconWidth()))/2 <=rockman.getX()+rockman.getIcon().getIconWidth() &&(coin[i].getObjy()+ coin[i].getObjy()+ coin[i].getIcon().getIconHeight())/2>=rockman.getY()&&(coin[i].getObjy()+ coin[i].getObjy()+ coin[i].getIcon().getIconHeight())/2<=rockman.getY()+rockman.getIcon().getIconHeight()) {
                        coin[i].setObjx(-50);
                        score+=100;
                        jlb_score.setText("SCORE:"+score);
                        coinsound.play();
                        coin[i].setVisible(false);
                    }
                }
            }
        });
        timer[1] = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((random.nextInt(4) + 1) % 2 == 0) {
                    if (!coin[0].getshow()) {
                        coin[0].setShow(true);
                        coin[0].setObjx(1380);
                        coin[0].setObjy(random.nextInt(300)+300);
                    } else if (!coin[1].getshow()) {
                        coin[1].setShow(true);
                        coin[1].setObjx(1380);
                        coin[1].setObjy(random.nextInt(300)+300);
                    } else if (!coin[2].getshow()) {
                        coin[2].setShow(true);
                        coin[2].setObjx(1380);
                        coin[2].setObjy(random.nextInt(300)+300);
                    }else if (!coin[3].getshow()) {
                        coin[3].setShow(true);
                        coin[3].setObjx(1380);
                        coin[3].setObjy(random.nextInt(300)+300);
                    }else if (!coin[4].getshow()) {
                        coin[4].setShow(true);
                        coin[4].setObjx(1380);
                        coin[4].setObjy(random.nextInt(300)+300);
                    }else if (!coin[5].getshow()) {
                        coin[5].setShow(true);
                        coin[5].setObjx(1380);
                        coin[5].setObjy(random.nextInt(300)+300);
                    }
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