import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class MainFrame extends JFrame {
    Rockman rockman = new Rockman();
    private JLabel background = new JLabel();
    private ImageIcon icon = new ImageIcon("Action/default-r.png");
    Thread thread = new Thread(rockman);
    MainFrame() {
        game();
        thread.start();
    }
    private void game() {
        this.setBounds(300, 100, 1280 - 10, 720);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        rockman.setIcon(icon);
        rockman.setBounds(this.getX(), this.getY() + 12, this.getWidth(), this.getHeight());
        this.add(rockman);
        this.add(background);
        background.setIcon(new ImageIcon("./background.png"));
        background.setBounds(0, -20, getWidth(), getHeight());
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
                    rockman.setShooting(true);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_C) {
                    rockman.setShooting(false);
                    if (!rockman.getJumpping() && !rockman.getRunning() && !rockman.getDirection_Right() && !rockman.getShooting()) {
                        rockman.setObjx(rockman.getObjx() + 50);
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT) {
                        rockman.run_stop();
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