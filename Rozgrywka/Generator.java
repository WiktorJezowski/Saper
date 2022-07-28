package Rozgrywka;

import java.util.Random;

public class Generator {

    public static char[][] generuj(int a, int b, int p, int m) {

        char [][] t = new char[a][b];
        int n=a*b;

        for(int i=0; i<a; i++) {
            for(int j=0; j<b; j++) {
                t[i][j]='0';
            }
        }

        Random random = new Random();
        int x=1;    //pola zajęte na planszy
        int z;      //zmienna pomocnicza
        int [] bombs = new int[m+1];    //tablica z indeksami bomb
        bombs[0] = p;

        while (x<=m) {
            bombs[x] = random.nextInt(n-x);
            for(int i=0; i<x; i++) {
                if(bombs[x]>=bombs[i])  bombs[x]++;     //znajdowanie indeksu nowej bomby
            }
            for(int i=0; i<x; i++) {
                if(bombs[x]<bombs[i]) {
                    for(int j=x; j>i; j--) {
                        z=bombs[j];
                        bombs[j]=bombs[j-1];
                        bombs[j-1]=z;
                    }
                    i=x;
                }
            }
            x++;
        }

        for(int i=0; i<=m; i++) {
            t[bombs[i]/b][bombs[i]%b]='*';
        }
        t[p/b][p%b]='0';

        for(int i=0; i<a; i++) {
            for(int j=0; j<b; j++) {
                if(t[i][j] != '*') {
                    z=0;
                    if(i!=0) {
                        if(t[i-1][j] == '*')    z++;
                        if(j!=0)
                            if(t[i-1][j-1] == '*')  z++;
                        if(j!=b-1)
                            if(t[i-1][j+1] == '*')  z++;
                    }
                    if(i!=a-1) {
                        if(t[i+1][j] == '*')    z++;
                        if(j!=0)
                            if(t[i+1][j-1] == '*')  z++;
                        if(j!=b-1)
                            if(t[i+1][j+1] == '*')  z++;
                    }
                    if(j!=0 && t[i][j-1] == '*')    z++;
                    if(j!=b-1 && t[i][j+1] == '*')    z++;

                    t[i][j] = (char)(z+48);
                }
            }
        }
        return t;
    }
}
