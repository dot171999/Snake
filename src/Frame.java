import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class Frame implements ActionListener , KeyListener {

    private Render render;
    private static final int UP = 1, DOWN = 2, LEFT = 3, RIGHT = 4;
    static final int RUNNING=1,PAUSED=2,INITIAL=3;
    static int game=3;
    static final int HEAD2HEAD=1,HEAD2BODY=2;
    static int collision=0;
    static int tick=0;
    static boolean start=false;
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
        if(start)
        {
            tick++;
            render.repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int i=e.getKeyCode();

        if(i==KeyEvent.VK_SPACE) {

            if(collision==HEAD2HEAD || collision==HEAD2BODY)
            {
                System.out.println("coll");
                Body.snakeParts.clear();
                Body.past.clear();
                Body.head=new Point(1,1);
                Body.direction=DOWN;
                Body.move=DOWN;
                Body2.snakeParts.clear();
                Body2.past.clear();
                Body2.head=new Point(781,1);
                Body2.direction=DOWN;
                Body2.move2=DOWN;
                collision=0;
                start=true;
            }

            if(game==PAUSED && collision==0) {
                start=true;
                game=RUNNING;
            }
            else if(game==RUNNING&& collision==0) {
                start=false;
                game=PAUSED;
                render.repaint();
            }

            if(game==INITIAL) {
                start=true;
                Server.send(100);
                game=RUNNING;
            }

        }

        if(i==KeyEvent.VK_A) {
            Body.move=LEFT;
            Server.send(Body.move*4);
        }

        if(i==KeyEvent.VK_D) {
            Body.move=RIGHT;
            Server.send(Body.move*4);
        }

        if(i==KeyEvent.VK_W) {
            Body.move=UP;
            Server.send(Body.move*4);
        }

        if(i==KeyEvent.VK_S) {
            Body.move=DOWN;
            Server.send(Body.move*4);
        }

        if(i==KeyEvent.VK_LEFT) {
            Body2.move2=LEFT;
            Server.send(Body.move*20);
        }


        if(i==KeyEvent.VK_RIGHT) {
            Body2.move2=RIGHT;
            Server.send(Body.move*20);
        }

        if(i==KeyEvent.VK_UP) {
            Body2.move2=UP;
            Server.send(Body.move*20);
        }


        if(i==KeyEvent.VK_DOWN) {
            Body2.move2=DOWN;
            Server.send(Body.move*20);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public static void main(String[] args) {
        new Frame();
        new Server();
    }
}
