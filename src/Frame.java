import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class Frame implements ActionListener , KeyListener {

    private Render render;
    private static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
    static int tick=0;
    private Frame() {
        JFrame jFrame=new JFrame("Snake");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);

        jFrame.setLayout(new GridLayout(1,1));

        render=new Render();
        jFrame.add(render);
        jFrame.pack();

        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);

        Timer timer=new Timer(10,this);
        timer.start();
        jFrame.addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tick++;
        render.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int i=e.getKeyCode();

        if(i==KeyEvent.VK_A) Body.move=LEFT;

        if(i==KeyEvent.VK_D) Body.move=RIGHT;

        if(i==KeyEvent.VK_W) Body.move=UP;

        if(i==KeyEvent.VK_S) Body.move=DOWN;


        if(i==KeyEvent.VK_LEFT) Body2.move2=LEFT;


        if(i==KeyEvent.VK_RIGHT) Body2.move2=RIGHT;

        if(i==KeyEvent.VK_UP) Body2.move2=UP;


        if(i==KeyEvent.VK_DOWN) Body2.move2=DOWN;

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public static void main(String[] args) {
        new Frame();
    }
}
