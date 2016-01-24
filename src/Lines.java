import javax.swing.*;
import java.awt.*;
import java.util.ArrayDeque;

public class Lines extends JComponent{
    private ArrayDeque<Line> lines;

    public Lines(){
        super();
        this.lines = new ArrayDeque<Line>();;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2 = (Graphics2D)graphics;
        for (Line l: lines) {
            g2.setColor(l.getC());
            g2.draw(l.getLine());
        }
    }

    public Line pointOnLine(Point p) {
        for (Line l: lines) {
            if(l.pointSurLine(p)) return l;
        }
        return null;
    }

    public ArrayDeque<Line> getLines() {
        return lines;
    }
}