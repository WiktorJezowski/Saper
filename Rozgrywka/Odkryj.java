package Rozgrywka;

public class Odkryj {
    private char[][] t;
    private String[][] w;

    public Odkryj(char [][] t, String [][] w) {
        this.t=t;
        this.w=w;
    }

    public void odkrywanie(int x, int y) {
        w[x][y]=String.valueOf(t[x][y]);
        if(t[x][y]=='0') {
            if(x!=0) {
                if(w[x-1][y].equals(" ")) odkrywanie(x-1,y);
                if(y!=0 && w[x-1][y-1].equals(" "))  odkrywanie(x-1,y-1);
                if(y!=w[0].length-1 && w[x-1][y+1].equals(" "))  odkrywanie(x-1,y+1);
            }
            if(x!=w.length-1) {
                if(w[x+1][y].equals(" ")) odkrywanie(x+1,y);
                if(y!=0 && w[x+1][y-1].equals(" "))  odkrywanie(x+1,y-1);
                if(y!=w[0].length-1 && w[x+1][y+1].equals(" "))  odkrywanie(x+1,y+1);
            }
            if(y!=0 && w[x][y-1].equals(" "))    odkrywanie(x,y-1);
            if(y!=w[0].length-1 && w[x][y+1].equals(" "))    odkrywanie(x,y+1);
        }
    }

    public String[][] odkryj(int x, int y) {
        odkrywanie(x,y);
        return w;
    }
}
