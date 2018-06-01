import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Rockman extends JLabel implements Runnable {
    protected int objx = 0, objy = 720 - 205, v = 65, t = 1, runstep = -6;
    protected boolean running, jumpping, direction_Right;
    private Timer[] timer = new Timer[2];

    public Rockman() {
    }

    public void setObjx(int objx1) {
        this.objx = objx1;
    }

    public void setDirection_Right(boolean direction_Right1) {
        this.direction_Right = direction_Right1;
    }

    public boolean getDirection_Right() {
        return direction_Right;
    }

    public void setJumpping(boolean jumpping1) {
        this.jumpping = jumpping1;
    }

    public boolean getJumpping() {
        return jumpping;
    }

    public void setObjy(int objy1) {
        this.objy = objy1;
    }

    public void run_stop() {
        running = false;
    }

    public void run_start() {
        running = true;
    }

    @Override
    public void run() {
        while (true) {
            if (running) {
                if (direction_Right) {
                    System.out.println("right");
                    if (runstep >= 0) {
                        objx += 10;
                        if (!jumpping) {
                            switch (runstep % 40) {
                                case 0:
                                    Rockman.this.setIcon(new ImageIcon("Action/walk" + 1 + "-r.png"));
                                    Rockman.this.setBounds(objx, objy + 21, this.getIcon().getIconWidth(), this.getIcon().getIconHeight());
                                    break;
                                case 10:
                                    Rockman.this.setIcon(new ImageIcon("Action/walk" + 2 + "-r.png"));
                                    break;
                                case 20:
                                    Rockman.this.setIcon(new ImageIcon("Action/walk" + 3 + "-r.png"));
                                    Rockman.this.setBounds(objx, objy + 21, this.getIcon().getIconWidth(), this.getIcon().getIconHeight());
                                    break;
                                case 30:
                                    Rockman.this.setIcon(new ImageIcon("Action/walk" + 2 + "-r.png"));
                                    break;
                            }
                        }
                    } else {
                        objx += 1;
                        if (!jumpping) {
                            Rockman.this.setIcon(new ImageIcon("Action/walk" + 0 + "-r.png"));
                        }
                    }
                } else {
                    System.out.println("left");
                    if (runstep >= 0) {
                        objx -= 10;
                        if (!jumpping) {
                            switch (runstep % 40) {
                                case 0:
                                    Rockman.this.setIcon(new ImageIcon("Action/walk" + 1 + "-l.png"));
                                    Rockman.this.setBounds(objx, objy + 21, this.getIcon().getIconWidth(), this.getIcon().getIconHeight());
                                    break;
                                case 10:
                                    Rockman.this.setIcon(new ImageIcon("Action/walk" + 2 + "-l.png"));
                                    break;
                                case 20:
                                    Rockman.this.setIcon(new ImageIcon("Action/walk" + 3 + "-l.png"));
                                    Rockman.this.setBounds(objx, objy + 21, this.getIcon().getIconWidth(), this.getIcon().getIconHeight());
                                    break;
                                case 30:
                                    Rockman.this.setIcon(new ImageIcon("Action/walk" + 2 + "-l.png"));
                                    break;
                            }
                        }
                    } else {
                        objx -= 1;
                        if (!jumpping) {
                            Rockman.this.setIcon(new ImageIcon("Action/walk" + 0 + "-l.png"));
                        }
                    }
                }
                if ((runstep % 40 >= 0 && runstep % 40 < 10) || (runstep % 40 >= 20 && runstep % 40 < 30)) {
                    Rockman.this.setBounds(objx, objy + 21, this.getIcon().getIconWidth(), this.getIcon().getIconHeight());
                } else {
                    Rockman.this.setBounds(objx, objy + 12, this.getIcon().getIconWidth(), this.getIcon().getIconHeight());
                }
                runstep += 2;
            } else {
                runstep=0;
                if (!jumpping) {
                    if (direction_Right) {
                        Rockman.this.setIcon(new ImageIcon("Action/default-r.png"));
                    } else {
                        Rockman.this.setIcon(new ImageIcon("Action/default-l.png"));
                    }
                    Rockman.this.setBounds(objx, objy + 12, this.getIcon().getIconWidth(), this.getIcon().getIconHeight());
                    runstep = -6;
                }
                System.out.println("stop");
            }
            if (jumpping) {
                runstep=0;
                System.out.println("jump");
                v = v - 5 * t;
                if (v <= 0 && v > -65) {
                    objy -= v * t + (1 / 2) * (-10 * t * t);
                } else if (v == -65) {
                    if (direction_Right) {
                        Rockman.this.setIcon(new ImageIcon("Action/default-r.png"));
                    } else {
                        Rockman.this.setIcon(new ImageIcon("Action/default-l.png"));
                    }
                    jumpping = false;
                    v = 65;
                } else if (v > 0) {
                    objy -= v * t + (1 / 2) * (-10 * t * t);
                }
                Rockman.this.setBounds(objx, objy + 12, Rockman.this.getIcon().getIconWidth(), Rockman.this.getIcon().getIconHeight());
            }
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
