import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

class Body2 extends Component {

    private Point head;
    private ImageIcon headPng,bodyPng=new ImageIcon(this.getClass().getResource("body2.png"));
    private ArrayList<Point> snakeParts=new ArrayList<>();
    private ArrayList<Point> past=new ArrayList<>();
    private static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
    private int direction=DOWN;
    static int move2=DOWN;
    private int score=0;
    private Font font = new Font("Verdana", Font.BOLD, 26);

    Body2() {
        this.head=new Point(781,1);
    }

    private void tick() {

        past.add(new Point(head.x,head.y));

        if(Frame.tick%20==0) direction=move2;

        if (direction == DOWN) {
            head = new Point(head.x, head.y + 1);
            for (int i=0;i<snakeParts.size();i++)
                snakeParts.set(i,new Point(past.get((past.size())-(i+1)*22).x,past.get((past.size())-(i+1)*22).y+1));
        }

        if (direction == UP) {
            head = new Point(head.x, head.y - 1);
            for (int i=0;i<snakeParts.size();i++)
                snakeParts.set(i,new Point(past.get((past.size())-(i+1)*22).x,past.get((past.size())-(i+1)*22).y-1));
        }

        if (direction == RIGHT) {
            head = new Point(head.x+1, head.y);
            for (int i=0;i<snakeParts.size();i++)
                snakeParts.set(i,new Point(past.get((past.size())-(i+1)*22).x+1,past.get((past.size())-(i+1)*22).y));
        }

        if (direction == LEFT) {
            head = new Point(head.x-1, head.y);
            for (int i=0;i<snakeParts.size();i++)
                snakeParts.set(i,new Point(past.get((past.size())-(i+1)*22).x-1,past.get((past.size())-(i+1)*22).y));
        }

        if(head.x<1) head=new Point(799,head.y);

        if(head.x>799) head=new Point(1,head.y);

        if(head.y<1) head=new Point(head.x,580);

        if(head.y>580) head=new Point(head.x,1);

        if(doOverlap(head, Cheery.cheery))
        {
            score++;
            snakeParts.add(new Point(past.get(past.size()-score*22).x,past.get(past.size()-score*22).y));
            Random ran=new Random();
            Cheery.cheery.setLocation(ran.nextInt(40)*20,ran.nextInt(30)*20);
        }
    }

    private boolean doOverlap(Point l1, Point l2) {
        if (l1.x > l2.x+20 || l2.x > l1.x+20 || l1.y > l2.y+20 || l2.y > l1.y+20) return false;

        return true;
    }

    void paintHead(Graphics g) {

        tick();

        for (Point point : snakeParts) bodyPng.paintIcon(this,g,point.x, point.y);

        if(direction==UP) headPng=new ImageIcon(this.getClass().getResource("head_up2.png"));

        if(direction==DOWN) headPng=new ImageIcon(this.getClass().getResource("head_down2.png"));

        if(direction==LEFT) headPng=new ImageIcon(this.getClass().getResource("head_left2.png"));

        if(direction==RIGHT) headPng=new ImageIcon(this.getClass().getResource("head_right2.png"));

        headPng.paintIcon(this, g, head.x, head.y);

        g.setColor(Color.BLUE);
        g.setFont(font);
        g.drawString("Score 2 = "+score,600,660);
    }
}
