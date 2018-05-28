import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.Runnable;
class MainFrame extends JFrame implements Runnable{
    private JLabel charcter = new JLabel();
    private JLabel background = new JLabel();
    private JLabel[] been = new JLabel[3];
    private ImageIcon icon = new ImageIcon("Action/default-r.png");
    private Timer[] timer = new Timer[10];
    private int objx = 0, objy = 720 - 185, objw = 106, objh = 115, v = 90, t = 1;
    private boolean jumpping = false, direction_Right = true;
    MainFrame() {
        game();
        timer[0].start();
    }
    private void game() {
        this.setBounds(300, 100, 1280, 720);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        charcter.setIcon(icon);
        charcter.setBounds(objx, objy, objw, objh);
        this.add(charcter);
        this.add(background);
        background.setIcon(new ImageIcon("./background.png"));
        background.setBounds(0,0,getWidth(),getHeight());
        //////////////////////////key/////////////////////////////////////
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if (jumpping) {
                        icon = new ImageIcon("Action/jump-r.png");
                    } else {
                        icon = new ImageIcon("Action/default-r.png");
                    }
                    charcter.setIcon(icon);
                    objx += 5;
                    charcter.setLocation(objx, objy);
                    timer[4].start();
                    direction_Right = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    if (jumpping) {
                        icon = new ImageIcon("Action/jump-l.png");
                    } else {
                        icon = new ImageIcon("Action/default-l.png");
                    }
                    charcter.setIcon(icon);
                    objx -= 5;
                    charcter.setLocation(objx, objy);
                    timer[4].start();
                    direction_Right = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_X && !jumpping) {
                    if (direction_Right) {
                        icon = new ImageIcon("Action/jump-r.png");
                    } else {
                        icon = new ImageIcon("Action/jump-l.png");
                    }
                    objw = icon.getIconWidth();
                    objh = icon.getIconHeight();
                    charcter.setIcon(icon);
                    jumpping = true;
                    timer[3].start();
                    timer[0].stop();
                    timer[1].stop();
                }
                if (e.getKeyCode() == KeyEvent.VK_C) {
                    if (jumpping) {
                        if (direction_Right) {
                            icon = new ImageIcon("Action/jumpshoot-r.png");
                        } else {
                            icon = new ImageIcon("Action/jumpshoot-l.png");
                        }
                    } else {
                        if (direction_Right) {
                            icon = new ImageIcon("Action/shoot-r.png");
                        } else {
                            icon = new ImageIcon("Action/shoot-l.png");
                        }
                    }
                    timer[0].stop();
                    timer[1].stop();
                    timer[4].start();
                    objw = icon.getIconWidth();
                    objh = icon.getIconHeight();
                    charcter.setIcon(icon);
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        /////////////////////////closeeye//////////////////////////////
        timer[0] = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (charcter.getIcon() == icon) {
                    if (direction_Right) {
                        icon = new ImageIcon("Action/closeeye-r.png");
                    } else {
                        icon = new ImageIcon("Action/closeeye-l.png");
                    }
                    charcter.setIcon(icon);
                    timer[1].restart();
                }
            }
        });
        ////////////////////////Default//////////////////////////////
        timer[1] = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (direction_Right) {
                    icon = new ImageIcon("Action/default-r.png");
                } else {
                    icon = new ImageIcon("Action/default-l.png");
                }
                charcter.setIcon(icon);
                timer[1].stop();
            }
        });
        ////////////////////////jump///////////////////////
        timer[3] = new Timer(45, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                v = v - 10 * t;
                if (v <= 0 && v > -90) {
                    objy -= v * t + (1 / 2) * (-10 * t * t);
                } else if (v <= -90) {
                    if (direction_Right) {
                        icon = new ImageIcon("Action/default-r.png");
                    } else {
                        icon = new ImageIcon("Action/default-l.png");
                    }
                    charcter.setIcon(icon);
                    timer[3].stop();
                    timer[1].restart();
                    timer[0].restart();
                    jumpping = false;
                    v = 90;
                } else if (v > 0) {
                    objy -= v * t + (1 / 2) * (-10 * t * t);
                }
                charcter.setBounds(objx, objy, objw, objh);
                charcter.setLocation(objx, objy);
            }
        });
        /////////////////////////shoot/////////////////////////////
        timer[4] = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!jumpping) {
                    timer[0].restart();
                    timer[1].start();
                }
            }
        });
    }

    @Override
    public void run() {

    }
}