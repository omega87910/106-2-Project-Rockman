import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
class FrameStart extends JFrame {
    private JButton jbtnStart =new JButton("START");
    FrameStart() {
        init();
    }
    private void init() {
        this.setBounds(300,100,1280,720);
        this.setLayout(null);
        this.add(jbtnStart);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jbtnStart.setBounds(600,300,100,60);
        jbtnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame mf =new MainFrame();
                mf.setVisible(true);
                FrameStart.this.dispose();
            }
        });
    }
}
