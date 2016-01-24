import java.awt.*;
import java.awt.geom.Line2D;

public class Line {
    private static final int HIT_BOX_SIZE = 5;
    private Line2D line;
    private Color c;

    public Line(Point p1, Point p2, Color c){
        this.line = new Line2D.Double(p1,p2);
        this.c = c;
    }

    public boolean pointSurLine(Point p) {
        int boxX = p.x - HIT_BOX_SIZE / 2;
        int boxY = p.y - HIT_BOX_SIZE / 2;

        int width = HIT_BOX_SIZE;
        int height = HIT_BOX_SIZE;

        return line.intersects(boxX, boxY, width, height);
    }

    public Color getC() {
        return c;
    }

    public Line2D getLine() {
        return line;
    }

    public void setC(Color c) {
        this.c = c;
    }

    public void setP1(Point p1) {
        this.line = new Line2D.Double(p1,line.getP2());
    }

    public void setP2(Point p2) {
        this.line = new Line2D.Double(line.getP1(),p2);
    }

    public void translate(int tx, int ty) {
        this.line = new Line2D.Double(line.getX1()+tx,line.getY1()+ty,line.getX2()+tx,line.getY2()+ty);
    }
}
