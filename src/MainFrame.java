import javafx.scene.media.AudioClip;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

class MainFrame extends JFrame {
    private AudioClip shootsound = new AudioClip(getClass().getResource("sound/sfx3.wav").toString());
    private AudioClip coinsound = new AudioClip(getClass().getResource("sound/coin.wav").toString());
    private AudioClip enemy_deadsound = new AudioClip(getClass().getResource("sound/sfx6.wav").toString());
    private AudioClip enemy_defencesound = new AudioClip(getClass().getResource("sound/sfx7.wav").toString());
    private AudioClip dead_sound = new AudioClip(getClass().getResource("sound/sfx5.wav").toString());
    private AudioClip bgm;
    private int key_jump = FrameStart.getKey_jump(), key_shoot = FrameStart.getKey_shoot();
    private Rockman rockman = new Rockman();
    private Timer timer[] = new Timer[3];
    private JPanel jpn = new JPanel();
    private JLabel background1 = new JLabel();
    private JLabel background2 = new JLabel();
    private JLabel background3 = new JLabel();
    private JLabel jlb_retry = new JLabel("Retry");
    private JLabel jlb_score = new JLabel();
    private JLabel jlb_thebestscore =new JLabel();
    private Bean[] bean = new Bean[3];
    private Coin[] coin = new Coin[6];
    private Metall[] metall = new Metall[3];
    private int lb1X = 0, lb2X = 884, lb3X = 1262;
    private Random random = new Random();
    private int score = 0,thebestscore=FrameStart.getThebestscore();
    private Container cp;

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
        Thread metall0_thread = new Thread(metall[0]);
        Thread metall1_thread = new Thread(metall[1]);
        Thread metall2_thread = new Thread(metall[2]);
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
        metall0_thread.start();
        metall1_thread.start();
        metall2_thread.start();
        timer[0].start();
        timer[1].start();
        timer[2].start();
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
        }
        if (bgm != null) {
            bgm.setCycleCount(AudioClip.INDEFINITE);
            bgm.play(0.8);
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
        metall[0] = new Metall();
        metall[1] = new Metall();
        metall[2] = new Metall();
        this.setTitle("Rockman");
        this.setBounds(300, 100, 1280 - 10, 720);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        cp = this.getContentPane();
        jlb_retry.setFont(new Font(null, Font.BOLD, 30));
        jlb_score.setBounds(800, 0, 800, 100);
        jlb_score.setFont(new Font(null, Font.BOLD, 50));
        jlb_score.setText("SCORE:" + score);
        jlb_thebestscore.setFont(new Font(null,Font.BOLD,50));
        jlb_thebestscore.setText("THE BEST SCORE:" + thebestscore);
        background1.setBounds(0, 0, 884, 685);
        background1.setIcon(new ImageIcon("./bg1.png"));
        background2.setBounds(880, 0, 380, 685);
        background2.setIcon(new ImageIcon("./bg2.png"));
        background3.setBounds(1258, 0, 884, 685);
        background3.setIcon(new ImageIcon("./bg1.png"));
        jpn.add(jlb_retry);
        jpn.add(rockman);
        jpn.add(bean[0]);
        jpn.add(bean[1]);
        jpn.add(bean[2]);
        jpn.add(metall[0]);
        jpn.add(metall[1]);
        jpn.add(metall[2]);
        jpn.add(coin[0]);
        jpn.add(coin[1]);
        jpn.add(coin[2]);
        jpn.add(coin[3]);
        jpn.add(coin[4]);
        jpn.add(coin[5]);
        jpn.add(jlb_score);
        jpn.add(jlb_thebestscore);
        jpn.add(background1);
        jpn.add(background2);
        jpn.add(background3);
        cp.add(jpn);
        jlb_thebestscore.setVisible(false);
        jlb_retry.setVisible(false);
        jpn.setLayout(null);
        //////////////////////////key/////////////////////////////////////
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (rockman.getDead()) {
                    if (e.getKeyCode() == KeyEvent.VK_R) {
                        fw();
                    }
                }else{
                    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        if (rockman.getJumpping() && !rockman.getDead()) {
                            rockman.setIcon(new ImageIcon("Action/jump-r.png"));
                        }
                        rockman.run_start();
                        rockman.setDirection_Right(true);
                    }
                    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        if (rockman.getJumpping() && !rockman.getDead()) {
                            rockman.setIcon(new ImageIcon("Action/jump-l.png"));
                        }
                        rockman.run_start();
                        rockman.setDirection_Right(false);
                    }
                    if (e.getKeyCode() == key_jump && !rockman.getJumpping()) {
                        rockman.setJumpping(true);
                        if (!rockman.getDead()) {
                            if (rockman.getDirection_Right()) {
                                rockman.setIcon(new ImageIcon("Action/jump-r.png"));
                            } else {
                                rockman.setIcon(new ImageIcon("Action/jump-l.png"));
                            }
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
                            for (int i = 0; i < 3; i++) {
                                if (bean[i].getShoot_allow()) {
                                    if (rockman.getDirection_Right()) {
                                        bean[i].setObjx(rockman.getObjx() + rockman.getIcon().getIconWidth());
                                    } else {
                                        bean[i].setObjx(rockman.getObjx());
                                    }
                                    bean[i].setObjy(rockman.getObjy() + 50);
                                    bean[i].setDirection_Right(rockman.getDirection_Right());
                                    bean[i].setShoot_allow(false);
                                    bean[i].setHit(false);
                                    rockman.setShooting(true);
                                    shootsound.play();
                                    break;
                                }
                            }
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
        timer[0] = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                background1.setLocation(lb1X, 0);
                background2.setLocation(lb2X, 0);
                background3.setLocation(lb3X, 0);
                if (lb1X <= -880) {
                    background1.setBounds(1258, 0, 884, 685);
                    lb1X = 1258;
                }
                if (lb2X <= -374) {
                    background2.setBounds(1764, 0, 380, 685);
                    lb2X = 1764;
                }
                if (lb3X <= -880) {
                    background3.setBounds(1258, 0, 884, 685);
                    lb3X = 1258;
                }
                lb1X -= 3;
                lb2X -= 3;
                lb3X -= 3;
                if (rockman.getX() - 1 >= 0) {
                    rockman.setObjx(rockman.getObjx() - 3);
                } else {
                    if (!rockman.getShooting()) {
                        rockman.setObjx(0);
                    }
                }
                for (int i = 0; i < 3; i++) {
                    if (metall[i].getShow()) {
                        metall[i].setObjx(metall[i].getObjx() - 3);
                    }
                }
                for (int i = 0; i < 6; i++) {
                    if (coin[i].getshow()) {
                        coin[i].setObjx(coin[i].getObjx() - 3);
                    }
                }
                if (!rockman.getDead()) {
                    for (int i = 0; i < 6; i++) {
                        if ((coin[i].getObjx() + (coin[i].getObjx() + coin[i].getIcon().getIconWidth())) / 2 >= rockman.getX() && (coin[i].getObjx() + (coin[i].getObjx() + coin[i].getIcon().getIconWidth())) / 2 <= rockman.getX() + rockman.getIcon().getIconWidth() && (coin[i].getObjy() + coin[i].getObjy() + coin[i].getIcon().getIconHeight()) / 2 >= rockman.getY() && (coin[i].getObjy() + coin[i].getObjy() + coin[i].getIcon().getIconHeight()) / 2 <= rockman.getY() + rockman.getIcon().getIconHeight()) {
                            coin[i].setObjx(-150);
                            score += 100;
                            jlb_score.setText("SCORE:" + score);
                            coinsound.play();
                            coin[i].setVisible(false);
                        }
                    }
                    for (int i = 0; i < 3; i++) {
                        if (!rockman.getShooting()) {
                            if (rockman.getX() + rockman.getIcon().getIconWidth() >= metall[i].getX() + 30 && rockman.getX() + rockman.getIcon().getIconWidth() <= metall[i].getX() + metall[i].getIcon().getIconWidth() - 30 || (rockman.getX() >= metall[i].getX() - 30 && rockman.getX() <= metall[i].getX() + metall[i].getIcon().getIconWidth() - 30)) {
                                if (rockman.getY() + rockman.getIcon().getIconHeight() >= metall[i].getY() + 10 && rockman.getY() + rockman.getIcon().getIconHeight() <= metall[i].getY() + metall[i].getIcon().getIconHeight() + 10) {
                                    rockman.setDead(true);
                                    rockman.setV(30);
                                    dead_sound.play();
                                    System.out.println("dead");
                                }
                            }
                        } else {
                            if (rockman.getX() + rockman.getIcon().getIconWidth() - 100 >= metall[i].getX() + 30 && rockman.getX() + rockman.getIcon().getIconWidth() <= metall[i].getX() + metall[i].getIcon().getIconWidth() - 30 || (rockman.getX() + 100 >= metall[i].getX() && rockman.getX() + 100 <= metall[i].getX() + metall[i].getIcon().getIconWidth() - 30)) {
                                if (rockman.getY() + rockman.getIcon().getIconHeight() >= metall[i].getY() + 30 && rockman.getY() + rockman.getIcon().getIconHeight() <= metall[i].getY() + metall[i].getIcon().getIconHeight() + 10) {
                                    rockman.setDead(true);
                                    rockman.setV(30);
                                    dead_sound.play();
                                    System.out.println("dead");
                                }
                            }
                        }
                    }
                }
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if ((bean[i].getObjx() + (bean[i].getObjx() + bean[i].getIcon().getIconWidth())) / 2 >= metall[j].getX() && (bean[i].getObjx() + (bean[i].getObjx() + bean[i].getIcon().getIconWidth())) / 2 <= metall[j].getX() + metall[j].getIcon().getIconWidth() && (bean[i].getObjy() + (bean[i].getObjy() + bean[i].getIcon().getIconHeight())) / 2 + 5 >= metall[j].getY() && (bean[i].getObjy() + (bean[i].getObjy() + bean[i].getIcon().getIconHeight())) / 2 <= metall[j].getY() + metall[j].getIcon().getIconHeight()) {
                            bean[i].setHit(true);
                            bean[i].setObjx(1500);
                            if (!metall[j].getDefence()) {
                                metall[j].setDead(true);
                                metall[j].setObjy(-200);
                                score += 500;
                                jlb_score.setText("SCORE:" + score);
                                enemy_deadsound.play();
                            } else {
                                enemy_defencesound.play();
                            }
                            System.out.println("get");
                        }
                    }
                }
                if (rockman.getDead()) {
                    if(score>thebestscore) {
                        thebestscore = score;
                        jlb_thebestscore.setText("THE BEST SCORE:" + thebestscore);
                    }
                    jlb_score.setVisible(false);
                    jlb_thebestscore.setVisible(true);
                    jlb_thebestscore.setBounds(350,200,800,100);
                    jlb_retry.setVisible(true);
                    jlb_retry.setBounds(600, 350, 100, 50);
                }
            }
        });
        jlb_retry.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fw();
            }
        });
        timer[1] = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (random.nextBoolean()) {
                    for (int i = 0; i < 6; i++) {
                        if (!coin[i].getshow()) {
                            coin[i].setShow(true);
                            coin[i].setObjx(1380);
                            coin[i].setObjy(random.nextInt(300) + 300);
                            break;
                        }
                    }
                }
                timer[1].setDelay(400 + random.nextInt(500));
            }
        });
        timer[2] = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (random.nextBoolean()) {
                    for (int i = 0; i < 3; i++) {
                        if (!metall[i].getShow()) {
                            metall[i].setObjx(1380);
                            metall[i].setShow(true);
                            metall[i].setObjy(586);
                            break;
                        }
                    }
                }
                timer[2].setDelay(800 + random.nextInt(8) * 100);
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
    }
    public void fw(){
        score=0;
        jlb_score.setText("SCORE:" + score);
        rockman.setDead(false);
        rockman.setJumpping(false);
        System.out.println("click");
        jlb_retry.setVisible(false);
        jlb_thebestscore.setVisible(false);
        jlb_score.setVisible(true);
        rockman.setV(60);
        rockman.setObjx(0);
        rockman.setObjy(720 - 203);
        rockman.setLocation(0, 720 - 203);
        try {
            FileWriter fw =new FileWriter("option.ini");
            fw.write("BGM="+ FrameStart.getbgm());
            fw.write("\nJUMP="+(char)FrameStart.getKey_jump());
            fw.write("\nSHOOT="+(char)FrameStart.getKey_shoot());
            fw.write("\nTHE_BEST_SCORE="+thebestscore);
            fw.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}