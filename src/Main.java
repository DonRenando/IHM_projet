import java.util.ArrayDeque;

public class Main {
    public static void main(String[] args) {
        Lines lines = new Lines();
        Fenetre frame = new Fenetre(lines);
        Automate a = new Automate(frame, lines);
    }
}
