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
//                if (e.getKeyCode() == KeyEvent.VK_C) {
//                    if (jumpping) {
//                        if (direction_Right) {
//                            icon = new ImageIcon("Action/jumpshoot-r.png");
//
//                        } else {
//                            icon = new ImageIcon("Action/jumpshoot-l.png");
//                        }
//                    } else {
//                        if (direction_Right) {
//                            icon = new ImageIcon("Action/shoot-r.png");
//                        } else {
//                            icon = new ImageIcon("Action/shoot-l.png");
//                        }
//                    }
//                    shooting=true;
//                    objw = icon.getIconWidth()+10;
//                    objh = icon.getIconHeight();
//                    timer[0].stop();
//                    timer[1].stop();
//                    timer[4].start();
//                    rockman.setIcon(icon);
//                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_X) {
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
//        timer[4] = new Timer(400, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (jumpping) {
//                    if (direction_Right) {
//                        rockman.setIcon(new ImageIcon("Action/jump-r.png"));
//                    } else {
//                        rockman.setIcon(new ImageIcon("Action/jump-l.png"));
//                    }
//                } else {
//                    if (direction_Right) {
//                        rockman.setIcon(new ImageIcon("Action/default-r.png"));
//                    } else {
//                        rockman.setIcon(new ImageIcon("Action/default-l.png"));
//                    }
//                }
//                rockman.setLocation(objx, objy);
////                timer[0].start();
////                timer[4].stop();
//            }
//        });
    }
}