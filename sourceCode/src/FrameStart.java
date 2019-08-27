import javafx.scene.media.AudioClip;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class FrameStart extends JFrame {
    private String[] str;
    private AudioClip title_sound = new AudioClip(getClass().getResource("sound/MegaMan11OST.mp3").toString());
    private AudioClip choose_sound = new AudioClip(getClass().getResource("sound/sound08.wav").toString());
    private AudioClip save_sound = new AudioClip(getClass().getResource("sound/sound10.wav").toString());
    private AudioClip option_sound = new AudioClip(getClass().getResource("sound/sound07.wav").toString());
    private AudioClip key_sound = new AudioClip(getClass().getResource("sound/sound09.wav").toString());
    private AudioClip key_sound2 = new AudioClip(getClass().getResource("sound/sound11.wav").toString());
    private JButton jbtnStart = new JButton("START");
    private JButton jbtnOption = new JButton("OPTION");
    private JButton jbtnExit = new JButton("EXIT");
    private JButton jbtnback = new JButton("←back");
    private JButton jbtnjump = new JButton("X");
    private JButton jbtnshoot = new JButton("C");
    private JButton jbtnsave = new JButton("save");
    private JLabel jlb_title = new JLabel();
    private JLabel jlb_option = new JLabel();
    private JLabel jlb_BGM = new JLabel("BGM選項");
    private JLabel jlb_btnset = new JLabel("按鍵設定");
    private JLabel jlb_jump = new JLabel("JUMP");
    private JLabel jlb_shoot = new JLabel("SHOOT");
    private static JRadioButton jrbtn_BGM = new JRadioButton("忍者外傳BGM");
    private static JRadioButton jrbtn_BGM2 = new JRadioButton("龍隼BGM");
    private static JRadioButton jrbtn_BGM3 = new JRadioButton("無");
    private ButtonGroup bgm_group = new ButtonGroup();
    private JPanel jpnTitle = new JPanel();
    private JPanel jpnOption = new JPanel();
    private static int bgm = 1;
    private boolean focus_jump = false, focus_shoot = false;
    private static int key_jump = KeyEvent.VK_X, key_shoot = KeyEvent.VK_C, thebestscore = 0;

    FrameStart() {
        title_sound.setCycleCount(AudioClip.INDEFINITE);
        title_sound.play(0.8);
        init();
        jpnOption.setVisible(false);
        jrbtn_BGM.setSelected(true);
        try {
            BufferedReader bfr = new BufferedReader(new FileReader("option.conf"));
            try {
                str = bfr.readLine().split("=");
                System.out.println(str[1]);
                switch (str[1]) {
                    case "1":
                        jrbtn_BGM.setSelected(true);
                        break;
                    case "2":
                        jrbtn_BGM2.setSelected(true);
                        break;
                    case "3":
                        jrbtn_BGM3.setSelected(true);
                        break;
                }
                str = bfr.readLine().split("=");
                System.out.println(str[1]);
                key_jump = str[1].charAt(0);
                jbtnjump.setText(str[1]);
                str = bfr.readLine().split("=");
                System.out.println(str[1]);
                key_shoot = str[1].charAt(0);
                jbtnshoot.setText(str[1]);
                str = bfr.readLine().split("=");
                thebestscore = Integer.parseInt(str[1]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            try {
                FileWriter fw = new FileWriter("option.conf");
                fw.write("BGM=" + bgm);
                fw.write("\nJUMP=" + (char) key_jump);
                fw.write("\nSHOOT=" + (char) key_shoot);
                fw.write("\nTHE_BEST_SCORE=" + thebestscore);
                fw.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    private void init() {
        Container cp = this.getContentPane();
        this.setBounds(300, 100, 1280, 720);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Rockman");
        this.setResizable(false);
        jlb_title.setIcon(new ImageIcon(this.getClass().getResource("background/rockman_Title.png")));
        jlb_option.setIcon(new ImageIcon(this.getClass().getResource("background/rockman11.jpg")));
        jpnTitle.setLayout(null);
        jpnOption.setLayout(null);
        jpnTitle.add(jbtnStart);
        jpnTitle.add(jbtnOption);
        jpnTitle.add(jbtnExit);
        jpnTitle.add(jlb_title);
        jpnOption.add(jbtnjump);
        jpnOption.add(jbtnshoot);
        jpnOption.add(jlb_jump);
        jpnOption.add(jlb_shoot);
        jpnOption.add(jlb_btnset);
        jpnOption.add(jrbtn_BGM);
        jpnOption.add(jrbtn_BGM2);
        jpnOption.add(jrbtn_BGM3);
        jpnOption.add(jlb_BGM);
        jpnOption.add(jbtnback);
        jpnOption.add(jbtnsave);
        jpnOption.add(jlb_option);
        bgm_group.add(jrbtn_BGM);
        bgm_group.add(jrbtn_BGM2);
        bgm_group.add(jrbtn_BGM3);
        /////////////////////////////////////////////////////////////////////////
        jbtnStart.setBounds(600, 400, 100, 60);
        jbtnOption.setBounds(600, 500, 100, 60);
        jbtnExit.setBounds(600, 600, 100, 60);
        jbtnback.setBounds(0, 0, 80, 30);
        jlb_BGM.setBounds(80, 150, 100, 30);
        jrbtn_BGM.setBounds(80, 200, 120, 30);
        jrbtn_BGM2.setBounds(200, 200, 100, 30);
        jrbtn_BGM3.setBounds(300, 200, 100, 30);
        jbtnsave.setBounds(1100, 600, 100, 30);
        jlb_btnset.setBounds(80, 250, 100, 30);
        jlb_jump.setBounds(80, 300, 100, 30);
        jbtnjump.setBounds(150, 300, 100, 30);
        jlb_shoot.setBounds(80, 350, 100, 30);
        jbtnshoot.setBounds(150, 350, 100, 30);
        jpnOption.setBounds(0, 0, 1280, 720);
        jpnTitle.setBounds(0, 0, 1280, 720);
        jlb_title.setBounds(0, 0, jlb_title.getIcon().getIconWidth(), jlb_title.getIcon().getIconHeight());
        jlb_option.setBounds(0, 0, jlb_option.getIcon().getIconWidth(), jlb_option.getIcon().getIconHeight());
        jlb_BGM.setForeground(Color.ORANGE);
        jlb_jump.setForeground(Color.ORANGE);
        jlb_shoot.setForeground(Color.ORANGE);
        jlb_btnset.setForeground(Color.ORANGE);
        jrbtn_BGM.setForeground(Color.ORANGE);
        jrbtn_BGM2.setForeground(Color.ORANGE);
        jrbtn_BGM3.setForeground(Color.ORANGE);
        jrbtn_BGM.setOpaque(false);
        jrbtn_BGM2.setOpaque(false);
        jrbtn_BGM3.setOpaque(false);
        cp.add(jpnOption);
        cp.add(jpnTitle);
        jbtnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                title_sound.stop();
                option_sound.play();
                MainFrame mf = new MainFrame();
                mf.setVisible(true);
                FrameStart.this.dispose();
            }
        });
        jbtnStart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                choose_sound.play();
            }
        });
        jbtnjump.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (focus_jump) {
                    key_sound2.play();
                    key_jump = e.getKeyCode();
                    jbtnjump.setText(Character.toString((char) e.getKeyCode()));
                    focus_jump = false;
                }
            }
        });
        jbtnjump.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                focus_jump = true;
                key_sound.play();
            }
        });
        jbtnshoot.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (focus_shoot) {
                    key_sound2.play();
                    key_shoot = e.getKeyCode();
                    jbtnshoot.setText(Character.toString((char) e.getKeyCode()));
                    focus_shoot = false;
                }
            }
        });
        jbtnshoot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                focus_shoot = true;
                key_sound.play();
            }
        });
        jbtnOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                option_sound.play(1000);
                jpnTitle.setVisible(false);
                jpnOption.setVisible(true);
            }
        });
        jbtnOption.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                choose_sound.play();
            }
        });
        jbtnback.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jpnTitle.setVisible(true);
                jpnOption.setVisible(false);
                try {
                    FileReader fr = new FileReader("option.conf");
                    BufferedReader bfr = new BufferedReader(fr);
                    try {
                        str = bfr.readLine().split("=");
                        System.out.println(str[1]);
                        switch (str[1]) {
                            case "1":
                                jrbtn_BGM.setSelected(true);
                                break;
                            case "2":
                                jrbtn_BGM2.setSelected(true);
                                break;
                            case "3":
                                jrbtn_BGM3.setSelected(true);
                                break;
                        }
                        str = bfr.readLine().split("=");
                        System.out.println(str[1]);
                        key_jump = str[1].charAt(0);
                        jbtnjump.setText(str[1]);
                        str = bfr.readLine().split("=");
                        System.out.println(str[1]);
                        key_shoot = str[1].charAt(0);
                        jbtnshoot.setText(str[1]);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });
        jbtnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        jbtnExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                choose_sound.play();
            }
        });
        jbtnsave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save_sound.play();
                getbgm();
                try {
                    FileWriter fw = new FileWriter("option.conf");
                    fw.write("BGM=" + bgm);
                    fw.write("\nJUMP=" + (char) key_jump);
                    fw.write("\nSHOOT=" + (char) key_shoot);
                    fw.write("\nTHE_BEST_SCORE=" + thebestscore);
                    fw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    public static int getbgm() {
        if (jrbtn_BGM.isSelected()) {
            bgm = 1;
        } else if (jrbtn_BGM2.isSelected()) {
            bgm = 2;
        } else {
            bgm = 3;
        }
        return bgm;
    }

    public static int getKey_jump() {
        return key_jump;
    }

    public static int getKey_shoot() {
        return key_shoot;
    }

    public static int getThebestscore() {
        return thebestscore;
    }

}