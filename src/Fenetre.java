import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Fenetre extends JFrame{;
    private Automate a;

    private Lines lines;
    
    private JPanel btnPan;
    private GridLayout grid;
    private JButton btnAjouter;
    private JButton btnDeplacer;
    private JButton btnModifier;
    private JButton btnSupprimer;


    public Fenetre(Lines lines ) {
        super("IHM TP Lines");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.lines = lines;

        btnPan = new JPanel();
        grid = new GridLayout(1,4);
        btnAjouter = new JButton("Ajouter");
        btnDeplacer = new JButton("Déplacer");
        btnModifier = new JButton("Modifier");
        btnSupprimer = new JButton("Supprimer");

        btnPan.setLayout(grid);
        btnPan.add(btnAjouter);
        btnPan.add(btnDeplacer);
        btnPan.add(btnModifier);
        btnPan.add(btnSupprimer);

        this.add(btnPan, BorderLayout.SOUTH);

        btnAjouter.addActionListener(actionEvent -> a.clicBtnAjouter());
        btnDeplacer.addActionListener(actionEvent -> a.clicBtnDeplacer());
        btnModifier.addActionListener(actionEvent -> a.clicBtnModifier());
        btnSupprimer.addActionListener(actionEvent -> a.clicBtnSupprimer());

        this.add(lines);

        lines.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {}

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                if (SwingUtilities.isLeftMouseButton (mouseEvent)) a.mousePressed(mouseEvent.getX(), mouseEvent.getY());
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                if (SwingUtilities.isLeftMouseButton (mouseEvent)) a.mouseReleased(mouseEvent.getX(), mouseEvent.getY());
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {}

            @Override
            public void mouseExited(MouseEvent mouseEvent) {}
        });

        lines.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
                if (SwingUtilities.isLeftMouseButton (mouseEvent)) a.mouseDragged(mouseEvent.getX(), mouseEvent.getY());
            }

            @Override
            public void mouseMoved(MouseEvent mouseEvent) {
                a.mouvSouris(mouseEvent.getX(), mouseEvent.getY());
            }
        });

        setSize(600,600);
        setVisible(true);
    }


    public JButton getBtnSupprimer() {
        return btnSupprimer;
    }

    public JButton getBtnAjouter() {
        return btnAjouter;
    }

    public JButton getBtnDeplacer() {
        return btnDeplacer;
    }

    public JButton getBtnModifier() {
        return btnModifier;
    }

    public Lines getLines() {
        return lines;
    }

    public void setA(Automate a) {
        this.a = a;
    }

}
