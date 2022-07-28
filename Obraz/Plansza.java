package Obraz;

import Rozgrywka.Generator;
import Rozgrywka.Odkryj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Plansza {

    private static JFrame ramka = new JFrame();
    private static JButton [][] p;
    private static JLabel postep;
    private static JButton R;
    private static JButton S;
    private static JButton F;
    private static JPanel panel;
    private static JPanel panel2;
    private static JPanel panel3;

    private int a;
    private int b;
    private int bombs;
    private String [][] w;    //plansza widzialna
    private char [][] t;    //plansza wygenerowana
    private int n;          //ilość pól odkrytych, -1 przy zakończeniu
    private Odkryj odkryj;
    private boolean f;

    public Plansza(int a, int b, int bombs) {
        this.a=a;
        this.b=b;
        this.bombs=bombs;
    }

    public void wyczysc() {
        for(int i=0; i<a; i++) {
            for (int j = 0; j < b; j++) {
                t[i][j]=' ';
                w[i][j]=" ";
                p[i][j].setText(w[i][j]);
                p[i][j].setBackground(null);
            }
        }
    }

    public int [] getXY(Object x) {
        int [] xy = new int[2];
        for (int i=0; i<a; i++) {
            for (int j=0; j<b; j++) {
                if(x == p[i][j]) {
                    xy[0]=i;
                    xy[1]=j;
                    return xy;
                }
            }
        }
        return xy;
    }

    public void aktualizuj() {
        for(int i=0; i<a; i++) {
            for(int j=0; j<b; j++) {
                if((p[i][j].getText().equals(" ") || p[i][j].getText().equals("F")) && !w[i][j].equals(" ")) {
                    n++;
                    p[i][j].setText(w[i][j]);
                    switch (w[i][j]) {
                        case "0": {
                            p[i][j].setBackground(Color.WHITE);
                        }   break;
                        case "1": {
                            p[i][j].setBackground(Color.CYAN);
                        }   break;
                        case "2": {
                            p[i][j].setBackground(Color.GREEN);
                        }   break;
                        case "3": {
                            p[i][j].setBackground(Color.YELLOW);
                        }   break;
                        case "4": {
                            p[i][j].setBackground(Color.ORANGE);
                        }   break;
                        case "5": {
                            p[i][j].setBackground(Color.MAGENTA);
                        }   break;
                        case "6": {
                            p[i][j].setBackground(Color.BLUE);
                        }   break;
                        case "7": {
                            p[i][j].setBackground(Color.RED);
                        }   break;
                        case "8": {
                            p[i][j].setBackground(Color.PINK);
                        }   break;
                        case "*": {
                            p[i][j].setBackground(Color.BLACK);
                        }   break;

                    }
                }
            }
        }
    }

    class Restart implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            n=0;
            postep.setText("rozpocznij grę");
            F.setText("Włącz flagowanie");
            f=true;
            F.setBackground(Color.WHITE);
            wyczysc();
            panel3.setBackground(null);
        }
    }

    class Rozwiaz implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(n!=0) {
                postep.setText("rozwiązanie");
                for(int i=0; i<a; i++) {
                    for (int j = 0; j < b; j++) {
                        w[i][j]=""+t[i][j];
                    }
                }
                aktualizuj();
                n=-1;
            }
        }
    }

    class Odslon implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int [] xy = getXY(e.getSource());
            if(n==0) {
                int pierwszy=b*xy[0]+xy[1];
                t=Generator.generuj(a,b,pierwszy,bombs);
                odkryj = new Odkryj(t,w);
            }
            if(f) {
                if(n!=-1 && !p[xy[0]][xy[1]].getText().equals("F")) {
                    if(t[xy[0]][xy[1]]=='*') {
                        przegrana();
                    }
                    else {
                        w=odkryj.odkryj(xy[0],xy[1]);
                        aktualizuj();
                        int l=a*b-bombs;
                        postep.setText(n + " na " + l);
                        if(n==a*b-bombs) {
                            wygrana();
                        }
                    }
                }
            }
            else {
                if(p[xy[0]][xy[1]].getText().equals(" ")) {
                    p[xy[0]][xy[1]].setText("F");
                    p[xy[0]][xy[1]].setBackground(Color.GRAY);
                }
                else {
                    if(p[xy[0]][xy[1]].getText().equals("F")) {
                        p[xy[0]][xy[1]].setText(" ");
                        p[xy[0]][xy[1]].setBackground(null);
                    }
                }
            }
        }
    }

    class Flagowanie implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(n>0) {
                if(f) {
                    F.setText("Wyłącz flagowanie");
                    f=false;
                    F.setBackground(Color.RED);
                }
                else {
                    F.setText("Włącz flagowanie");
                    f=true;
                    F.setBackground(Color.WHITE);
                }
            }
        }
    }

    public void przegrana() {
        for(int i=0; i<a; i++) {
            for (int j = 0; j < b; j++) {
                n=-1;
                if(t[i][j]=='*') {
                    p[i][j].setText("*");
                    p[i][j].setBackground(Color.BLACK);
                }
            }
        }
        postep.setText("Przegrana");
        panel3.setBackground(Color.GRAY);
    }

    public void wygrana() {
        n=-1;
        postep.setText("Wygrana");
        panel3.setBackground(Color.ORANGE);
    }

    public void wyswietl() {

        n=0;

        t = new char[a][b];
        w = new String[a][b];

        ramka.setTitle("Saper");
        ramka.setSize(45*b,45*a+75);

        panel = new JPanel();
        ramka.getContentPane().add(panel);

        panel.setLayout(new GridLayout(a,b));

        p = new JButton[a][b];
        for (int i=0; i<a; i++) {
            for (int j=0; j<b; j++) {
                p[i][j] = new JButton(" ");
                panel.add(p[i][j]);
                p[i][j].addActionListener(new Odslon());
            }
        }

        wyczysc();

        panel2 = new JPanel();
        ramka.getContentPane().add(panel2);

        postep = new JLabel("rozpocznij grę");
        panel2.add(postep);

        R = new JButton("Restart");
        panel2.add(R);
        R.addActionListener(new Restart());

        S = new JButton("Pokaż rozwiązanie");
        panel2.add(S);
        S.addActionListener(new Rozwiaz());

        panel3 = new JPanel();
        ramka.getContentPane().add(panel3);

        F = new JButton("Włącz flagowanie");
        panel3.add(F);
        f=true;
        F.addActionListener(new Flagowanie());
        F.setBackground(Color.WHITE);

        ramka.getContentPane().add(BorderLayout.CENTER, panel);
        ramka.getContentPane().add(BorderLayout.NORTH, panel2);
        ramka.getContentPane().add(BorderLayout.SOUTH, panel3);

        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ramka.setVisible(true);

    }
}
