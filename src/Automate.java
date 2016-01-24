import java.awt.*;
import java.util.ArrayDeque;

public class Automate {
    private Fenetre f;
    private Point pOrigne;
    private Line lActive, lNewModif;
    private int etat;
    private int nbLigne;
    private ArrayDeque<Line> ArrayLine;
    private  Lines lines;

    public Automate(Fenetre f, Lines lines) {
        this.f = f;
        this.ArrayLine = lines.getLines();
        this.lines = lines;
        init();
        f.setA(this);
    }

    public void init()
    {
        f.getBtnAjouter().setEnabled(true);
        f.getBtnDeplacer().setEnabled(false);
        f.getBtnModifier().setEnabled(false);
        f.getBtnSupprimer().setEnabled(false);
        etat = 0;
    }


    public void mousePressed(int x, int y) {
        switch (etat) {
            case 1 :
                pOrigne = new Point(x,y);
                etat = 2;
                break;
            case 5 :
                pOrigne = new Point(x,y);
                lActive.setC(Color.BLUE);
                etat = 6;
                break;
            case 9:
                lActive.setC(Color.BLUE);
                etat = 10;
                break;
            case 13:
                lActive.setC(Color.BLUE);
                etat = 14;
                break;
        }
        f.getLines().repaint();
    }

    public void mouseReleased(int x, int y) {
        switch (etat) {
            case 2 :
                etat = 1;
                break;
            case 3 :
                lActive.setP2(new Point(x,y));
                lActive.setC(Color.BLACK);
                lActive = null;
                nbLigne ++;
                f.getBtnAjouter().setEnabled(false);
                f.getBtnDeplacer().setEnabled(true);
                f.getBtnModifier().setEnabled(true);
                f.getBtnSupprimer().setEnabled(true);
                etat = 1;
                break;
            case 6 :
                lActive.setC(Color.BLACK);
                lActive = null;
                etat = 4;
                break;
            case 7:
                lActive.setC(Color.BLACK);
                lActive = null;
                etat = 4;
                break;
            case 10:
                lActive.setC(Color.BLACK);
                lActive = null;
                lNewModif = null;
                etat = 8;
                break;
            case 11:
                lActive.setC(Color.BLACK);
                lNewModif.setC(Color.BLACK);
                lActive = null;
                lNewModif = null;
                etat = 8;
                break;
            case 14 :
                ArrayLine.remove(lActive);
                lActive = null;
                nbLigne --;
                if (nbLigne == 0) {
                    f.getBtnAjouter().setEnabled(false);
                    f.getBtnDeplacer().setEnabled(false);
                    f.getBtnModifier().setEnabled(false);
                    f.getBtnSupprimer().setEnabled(false);
                    etat = 1;
                }
                else
                    etat = 12;
                break;

        }
        f.getLines().repaint();
    }

    public void mouseDragged(int x, int y) {
        switch (etat) {
            case  2 :
                lActive = new Line(pOrigne, new Point(x,y),Color.BLUE);
                ArrayLine.add(lActive);
                etat = 3;
                break;
            case 3 :
                lActive.setP2(new Point(x,y));
                etat = 3;
                break;
            case 6 :
            case 7 :
                lActive.translate(x - pOrigne.x, y - pOrigne.y);
                pOrigne = new Point(x,y);
                etat = 7;
                break;
            case 10 :
                lNewModif = new Line(new Point((int) lActive.getLine().getX1(), (int) lActive.getLine().getY1()),new Point(x,y),Color.BLUE);
                lActive.setP1(new Point(x,y));
                ArrayLine.add(lNewModif);
                nbLigne ++;
                etat = 11;
                break;
            case 11:
                lNewModif.setP2(new Point(x,y));
                lActive.setP1(new Point(x,y));
                etat = 11;
                break;
            case 14 :
                lActive.setC(Color.BLACK);
                lActive = null;
        }
        f.getLines().repaint();
    }

    public void mouvSouris(int x, int y) {
        switch (etat)
        {
            case 4 :
                lActive = lines.pointOnLine(new Point(x,y));
                if (lActive != null)
                {
                    lActive.setC(Color.RED);
                    etat = 5;
                }
                break;
            case 5 :
                if (lines.pointOnLine(new Point(x,y)) == null)
                {
                    lActive.setC(Color.BLACK);
                    etat = 4;
                }
                break;
            case 8 :
                lActive = lines.pointOnLine(new Point(x,y));
                if (lActive != null)
                {
                    lActive.setC(Color.RED);
                    etat = 9;
                }
                break;
            case 9 :
                if (lines.pointOnLine(new Point(x,y)) == null)
                {
                    lActive.setC(Color.BLACK);
                    etat = 8;
                }
                break;
            case 12 :
                lActive = lines.pointOnLine(new Point(x,y));
                if (lActive != null)
                {
                    lActive.setC(Color.RED);
                    etat = 13;
                }
                break;
            case 13 :
                if (lines.pointOnLine(new Point(x,y)) == null)
                {
                    lActive.setC(Color.BLACK);
                    etat = 12;
                }
                break;
        }
        f.getLines().repaint();
    }

    public void clicBtnAjouter() {
        switch (etat) {
            case 0:
                nbLigne = 0;
                f.getBtnAjouter().setEnabled(false);
                etat = 1;
                break;
            case 4 :
            case 8 :
            case 12 :
                if (nbLigne == 0)
                {
                    f.getBtnAjouter().setEnabled(false);
                    f.getBtnDeplacer().setEnabled(false);
                    f.getBtnModifier().setEnabled(false);
                    f.getBtnSupprimer().setEnabled(false);
                }
                else
                {
                    f.getBtnAjouter().setEnabled(false);
                    f.getBtnDeplacer().setEnabled(true);
                    f.getBtnModifier().setEnabled(true);
                    f.getBtnSupprimer().setEnabled(true);
                }
                etat = 1;
                break;
        }
    }

    public void clicBtnDeplacer() {
        switch (etat) {
            case 1:
            case 8:
            case 12:
                f.getBtnAjouter().setEnabled(true);
                f.getBtnDeplacer().setEnabled(false);
                f.getBtnModifier().setEnabled(true);
                f.getBtnSupprimer().setEnabled(true);
                etat = 4;
                break;
        }
    }

    public void clicBtnModifier() {
        switch (etat) {
            case 1:
            case 4:
            case 12:
                f.getBtnAjouter().setEnabled(true);
                f.getBtnDeplacer().setEnabled(true);
                f.getBtnModifier().setEnabled(false);
                f.getBtnSupprimer().setEnabled(true);
                etat = 8;
                break;
        }
    }

    public void clicBtnSupprimer() {
        switch (etat) {
            case 1:
            case 4:
            case 8:
                f.getBtnAjouter().setEnabled(true);
                f.getBtnDeplacer().setEnabled(true);
                f.getBtnModifier().setEnabled(true);
                f.getBtnSupprimer().setEnabled(false);
                etat = 12;
                break;
        }
    }
}
