import javafx.scene.media.AudioClip;

import javax.swing.*;

public class Rockman extends JLabel implements Runnable {
    private int objx = 0;
    private int objy = 720 - 203;
    private int v = 60, t = 1, runstep = -6, shootrest = 0;
    private boolean running, jumpping, shooting = false, direction_Right = true, dead;
    private AudioClip falldown = new AudioClip(getClass().getResource("sound/sound01.wav").toString());

    Rockman() {
        Rockman.this.setIcon(new ImageIcon(this.getClass().getResource("Action/default-r.png")));
    }

    public void setObjx(int objx1) {
        this.objx = objx1;
    }

    public void setV(int v) {
        this.v = v;
    }

    public int getObjx() {
        return objx;
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

    public void setShooting(boolean shooting1) {
        this.shooting = shooting1;
    }

    public boolean getShooting() {
        return shooting;
    }

    public void setObjy(int objy1) {
        this.objy = objy1;
    }

    public int getObjy() {
        return objy;
    }

    public void run_stop() {
        running = false;
    }

    public void run_start() {
        running = true;
    }

    public boolean getRunning() {
        return running;
    }

    public void setDead(boolean dead1) {
        this.dead = dead1;
    }

    public boolean getDead() {
        return dead;
    }

    public void setRunstep(int runstep1) {
        this.runstep = runstep1;
    }

    @Override
    public void run() {
        while (true) {
            if (!dead) {
                ///////////////////////////////////move///////////////////////////////////////////////
                if (running) {
                    if (direction_Right) {
                        if (runstep >= 0) {
                            if (this.getX() < 1180) {
                                objx += 16;
                            }
                            if (!jumpping) {
                                if (!shooting) {
                                    switch (runstep % 40) {
                                        case 0:
                                            Rockman.this.setIcon(new ImageIcon(this.getClass().getResource("Action/walk1-r.png")));
                                            Rockman.this.setBounds(objx, objy + 21, this.getIcon().getIconWidth(), this.getIcon().getIconHeight());
                                            break;
                                        case 10:
                                            Rockman.this.setIcon(new ImageIcon(this.getClass().getResource("Action/walk2-r.png")));
                                            break;
                                        case 20:
                                            Rockman.this.setIcon(new ImageIcon(this.getClass().getResource("Action/walk3-r.png")));
                                            Rockman.this.setBounds(objx, objy + 21, this.getIcon().getIconWidth(), this.getIcon().getIconHeight());
                                            break;
                                        case 30:
                                            Rockman.this.setIcon(new ImageIcon(this.getClass().getResource("Action/walk2-r.png")));
                                            break;
                                    }
                                } else {
                                    switch (runstep % 40) {
                                        case 0:
                                            Rockman.this.setIcon(new ImageIcon(this.getClass().getResource("Action/walkshoot1-r.png")));
                                            Rockman.this.setBounds(objx, objy + 21, this.getIcon().getIconWidth(), this.getIcon().getIconHeight());
                                            break;
                                        case 10:
                                            Rockman.this.setIcon(new ImageIcon(this.getClass().getResource("Action/walkshoot2-r.png")));
                                            break;
                                        case 20:
                                            Rockman.this.setIcon(new ImageIcon(this.getClass().getResource("Action/walkshoot3-r.png")));
                                            Rockman.this.setBounds(objx, objy + 21, this.getIcon().getIconWidth(), this.getIcon().getIconHeight());
                                            break;
                                        case 30:
                                            Rockman.this.setIcon(new ImageIcon(this.getClass().getResource("Action/walkshoot2-r.png")));
                                            break;
                                    }
                                }
                            } else {//跳躍且射擊
                                if (shooting) {
                                    if (direction_Right) {
                                        Rockman.this.setIcon(new ImageIcon(this.getClass().getResource("Action/jumpshoot-r.png")));
                                    } else {
                                        Rockman.this.setIcon(new ImageIcon(this.getClass().getResource("Action/jumpshoot-l.png")));
                                    }
                                }
                            }
                        } else {
                            objx += 1;
                            if (!jumpping) {
                                Rockman.this.setIcon(new ImageIcon(this.getClass().getResource("Action/walk0-r.png")));
                            }
                        }
                    } else {/////////////////////////////////向左
                        if (runstep >= 0) {
                            if (this.getX() >= -3) {
                                objx -= 10;
                            }
                            if (!jumpping) {
                                if (!shooting) {
                                    switch (runstep % 40) {
                                        case 0:
                                            Rockman.this.setIcon(new ImageIcon(this.getClass().getResource("Action/walk1-l.png")));
                                            Rockman.this.setBounds(objx, objy + 21, this.getIcon().getIconWidth(), this.getIcon().getIconHeight());
                                            break;
                                        case 10:
                                            Rockman.this.setIcon(new ImageIcon(this.getClass().getResource("Action/walk2-l.png")));
                                            break;
                                        case 20:
                                            Rockman.this.setIcon(new ImageIcon(this.getClass().getResource("Action/walk3-l.png")));
                                            Rockman.this.setBounds(objx, objy + 21, this.getIcon().getIconWidth(), this.getIcon().getIconHeight());
                                            break;
                                        case 30:
                                            Rockman.this.setIcon(new ImageIcon(this.getClass().getResource("Action/walk2-l.png")));
                                            break;
                                    }
                                } else {
                                    switch (runstep % 40) {
                                        case 0:
                                            Rockman.this.setIcon(new ImageIcon(this.getClass().getResource("Action/walkshoot1-l.png")));
                                            Rockman.this.setBounds(objx, objy + 21, this.getIcon().getIconWidth(), this.getIcon().getIconHeight());
                                            break;
                                        case 10:
                                            Rockman.this.setIcon(new ImageIcon(this.getClass().getResource("Action/walkshoot2-l.png")));
                                            break;
                                        case 20:
                                            Rockman.this.setIcon(new ImageIcon(this.getClass().getResource("Action/walkshoot3-l.png")));
                                            Rockman.this.setBounds(objx, objy + 21, this.getIcon().getIconWidth(), this.getIcon().getIconHeight());
                                            break;
                                        case 30:
                                            Rockman.this.setIcon(new ImageIcon(this.getClass().getResource("Action/walkshoot2-l.png")));
                                            break;
                                    }
                                }
                            } else {//跳躍
                                if (shooting) {
                                    if (direction_Right) {
                                        Rockman.this.setIcon(new ImageIcon(this.getClass().getResource("Action/jumpshoot-r.png")));
                                    } else {
                                        Rockman.this.setIcon(new ImageIcon(this.getClass().getResource("Action/jumpshoot-l.png")));
                                    }
                                }
                            }
                        } else {
                            objx -= 1;
                            if (!jumpping) {
                                Rockman.this.setIcon(new ImageIcon(this.getClass().getResource("Action/walk0-l.png")));
                            }
                        }
                    }
                    if ((runstep % 40 >= 0 && runstep % 40 < 10) || (runstep % 40 >= 20 && runstep % 40 < 30)) {
                        Rockman.this.setBounds(objx, objy + 21, this.getIcon().getIconWidth(), this.getIcon().getIconHeight());
                    } else {
                        Rockman.this.setBounds(objx, objy + 12, this.getIcon().getIconWidth(), this.getIcon().getIconHeight());
                    }
                    runstep += 2;
                }
                /////////////////////////////stop////////////////////////////////////////////////
                else {
                    runstep = 0;
                    if (!jumpping) {
                        if (!shooting) {
                            if (direction_Right) {
                                Rockman.this.setIcon(new ImageIcon(this.getClass().getResource("Action/default-r.png")));
                            } else {
                                Rockman.this.setIcon(new ImageIcon(this.getClass().getResource("Action/default-l.png")));
                            }
                        } else {//沒有跳躍而且射擊
                            if (direction_Right) {
                                Rockman.this.setIcon(new ImageIcon(this.getClass().getResource("Action/shoot-r.png")));
                            } else {
                                Rockman.this.setIcon(new ImageIcon(this.getClass().getResource("Action/shoot-l.png")));
                            }
                        }
                    } else {//跳躍並射擊
                        if (shooting) {
                            if (direction_Right) {
                                Rockman.this.setIcon(new ImageIcon(this.getClass().getResource("Action/jumpshoot-r.png")));
                            } else {
                                Rockman.this.setIcon(new ImageIcon(this.getClass().getResource("Action/jumpshoot-l.png")));
                            }
                        }
                    }
                    Rockman.this.setBounds(objx, objy + 12, this.getIcon().getIconWidth(), this.getIcon().getIconHeight());
                    runstep = -6;
                }
                //////////////////////////////////////jump/////////////////////////////////////////////
                if (jumpping) {
                    runstep = 0;
                    v = v - 5 * t;
                    if (v <= 0 && v > -60) {
                        objy -= v * t + (1 / 2) * (-10 * t * t);
                    } else if (v == -60) {
                        falldown.play();
                        if (direction_Right) {
                            Rockman.this.setIcon(new ImageIcon(this.getClass().getResource("Action/default-r.png")));
                        } else {
                            Rockman.this.setIcon(new ImageIcon(this.getClass().getResource("Action/default-l.png")));
                        }
                        jumpping = false;
                        v = 60;
                    } else if (v > 0) {
                        objy -= v * t + (1 / 2) * (-10 * t * t);
                    }
                    Rockman.this.setBounds(objx, objy + 12, Rockman.this.getIcon().getIconWidth(), Rockman.this.getIcon().getIconHeight());
                }
                ////////////////////////////////shoottime_limit/////////////////////////////////////////////////////////
                if (shooting) {
                    shootrest += 2;
                    if (shootrest % 12 == 0) {
                        if (!getJumpping() && !getRunning() && !getDirection_Right()) {
                            objx += 50;
                        }
                        this.setShooting(false);
                    }
                }
            }
            //////////////////////////////////////dead/////////////////////////////////////////////////////
            if (dead) {
                if (direction_Right) {
                    Rockman.this.setIcon(new ImageIcon(this.getClass().getResource("Action/damage-r.png")));
                } else {
                    Rockman.this.setIcon(new ImageIcon(this.getClass().getResource("Action/damage-l.png")));
                }
                v = v - 5 * t;
                if (v <= 0 && v > -200) {
                    objy -= v * t + (1 / 2) * (-10 * t * t);
                } else if (v <= -200) {
                } else if (v > 0) {
                    objy -= v * t + (1 / 2) * (-10 * t * t);
                }
                Rockman.this.setBounds(objx, objy, Rockman.this.getIcon().getIconWidth(), Rockman.this.getIcon().getIconHeight());
            }
            try {
                Thread.sleep(30);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}