import javax.swing.*;
import java.awt.*;

class Render extends JPanel {
    private Body body=new Body();
    private Body2 body2=new Body2();
    private Cheery cheery=new Cheery();

    Render() {
        setPreferredSize(new Dimension(800,700));
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.DARK_GRAY);
        for(int i=0;i<800/20;i++) {
            g.drawLine(i*20,0,i*20,700);
        }
        for(int i=0;i<700/20;i++) {
            g.drawLine(0,i*20,800,i*20);
        }

        g.setColor(Color.WHITE);
        g.fillRect(5,606,790,89);

        body.paintHead(g);
        body2.paintHead(g);
        cheery.paintCherry(g);

    }
}
