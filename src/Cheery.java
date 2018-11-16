import javax.swing.*;
import java.awt.*;

class Cheery extends Component {
    static Point cheery=new Point(400,360);
    private ImageIcon cheeryPng=new ImageIcon(this.getClass().getResource("prey.png"));

    void paintCherry(Graphics g) {
        cheeryPng.paintIcon(this,g,cheery.x,cheery.y);
    }
}
