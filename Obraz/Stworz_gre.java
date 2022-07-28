package Obraz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Stworz_gre {

    private static JFrame ramka = new JFrame();

    private static JTextField l_wierszy;
    private static JTextField l_kolumn;
    private static JTextField l_bomb;


    public static boolean isNotInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return true;
        } catch (NullPointerException e) {
            return true;
        }
        return false;
    }

    static class Tworz implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            boolean p=true;
            String [] t = new String[3];
            t[0]=l_wierszy.getText();
            t[1]=l_kolumn.getText();
            t[2]=l_bomb.getText();
            for (int i=0; i<3; i++) {
                if(isNotInteger(t[i])) p=false;
            }
            if(p && (Integer.parseInt(t[0])*Integer.parseInt(t[1]))>(Integer.parseInt(t[2])-1)) {
                ramka.dispose();
                Plansza plansza = new Plansza(Integer.parseInt(t[0]),Integer.parseInt(t[1]),Integer.parseInt(t[2]));
                plansza.wyswietl();
            }
        }
    }

    public static void wybor() {

        ramka.setTitle("Stwórz grę");
        ramka.setSize(250,150);

        JPanel panel = new JPanel();
        ramka.getContentPane().add(panel);

        panel.setLayout(new GridLayout(3, 2));

        JLabel etykieta1 = new JLabel(" liczba wierszy");
        panel.add(etykieta1);

        l_wierszy = new JTextField("", 10);
        panel.add(l_wierszy);

        JLabel etykieta2 = new JLabel(" liczba kolumn");
        panel.add(etykieta2);

        l_kolumn = new JTextField("", 10);
        panel.add(l_kolumn);

        JLabel etykieta3 = new JLabel(" liczba bomb");
        panel.add(etykieta3);

        l_bomb = new JTextField("", 10);
        panel.add(l_bomb);

        JPanel potwierdzanie = new JPanel();
        ramka.getContentPane().add(potwierdzanie);

        JButton ok = new JButton("OK");
        potwierdzanie.add(ok);
        ok.addActionListener(new Tworz());

        ramka.getContentPane().add(BorderLayout.CENTER, panel);
        ramka.getContentPane().add(BorderLayout.SOUTH, potwierdzanie);

        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ramka.setVisible(true);

    }

}
