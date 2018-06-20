import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
class FrameStart extends JFrame {
    private JButton jbtnStart =new JButton("START");
    private JButton jbtnOption =new JButton("OPTION");
    private JButton jbtnExit =new JButton("EXIT");
    private JButton jbtnback = new JButton("←back");
    private JLabel jlb_BGM =new JLabel("BGM選項");
    private static JRadioButton jrbtn_BGM =new JRadioButton("忍者外傳BGM");
    private static JRadioButton jrbtn_BGM2 =new JRadioButton("龍隼BGM");
    private ButtonGroup bgm_group =new ButtonGroup();
    private JPanel jpnTitle =new JPanel();
    private JPanel jpnOption =new JPanel();
    private static boolean bgm;
    Container cp;
    FrameStart() {
        init();
        jrbtn_BGM.setSelected(true);
    }
    private void init() {
        cp=this.getContentPane();
        this.setBounds(300,100,1280,720);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jpnTitle.setLayout(null);
        jpnOption.setLayout(null);
        jpnTitle.add(jbtnStart);
        jpnTitle.add(jbtnOption);
        jpnTitle.add(jbtnExit);
        jpnOption.add(jbtnback);
        jpnOption.add(jrbtn_BGM);
        jpnOption.add(jrbtn_BGM2);
        jpnOption.add(jlb_BGM);
        bgm_group.add(jrbtn_BGM);
        bgm_group.add(jrbtn_BGM2);
        /////////////////////////////////////////////////////////////////////////
        jbtnStart.setBounds(600,300,100,60);
        jbtnOption.setBounds(600,400,100,60);
        jbtnExit.setBounds(600,500,100,60);
        jbtnback.setBounds(0,0,80,30);
        jlb_BGM.setBounds(150,150,100,30);
        jrbtn_BGM.setBounds(80,200,120,30);
        jrbtn_BGM2.setBounds(200,200,100,30);
        jpnOption.setBounds(-1280,0,1280,720);
        jpnTitle.setBounds(0,0,1280,720);
        cp.add(jpnOption);
        cp.add(jpnTitle);
        jbtnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame mf =new MainFrame();
                mf.setVisible(true);
                FrameStart.this.dispose();
            }
        });
        jbtnOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    jpnTitle.setBounds(-1280,0,1280,720);
                    jpnOption.setBounds(0,0,1280,720);
            }
        });
        jbtnback.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jpnTitle.setBounds(0,0,1280,720);
                jpnOption.setBounds(-1280,0,1280,720);
            }
        });
        jbtnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    public static boolean getbgm(){
        if(jrbtn_BGM.isSelected()){
            bgm=true;
        }else{
            bgm=false;
        }
        return bgm;
    }
}
