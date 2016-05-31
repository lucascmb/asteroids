/**
 * Created by lcs on 25/05/16.
 */

/**
 * Cores em RGB
 */
public class Cor
{
    int r;
    int g;
    int b;

    static Cor BRANCO = new Cor("branco");
    static Cor AZUL = new Cor("azul");
    static Cor VERDE = new Cor("verde");
    static Cor VERMELHO = new Cor("vermelho");
    static Cor CINZA = new Cor(0.7, 0.7, 0.7);
    static Cor AMARELO = new Cor(1.0, 1.0, 0.0);
    static Cor PRETO = new Cor(0.0, 0.0, 0.0);

    /*
     * Cria uma cor dados os componentes entre 0 e 255
     */
    public Cor(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    /*
     * Cria uma cor dados os componentes entre 0 e 1
     */
    public Cor(double r, double g, double b) {
        this.r = (int)(r*255);
        this.g = (int)(g*255);
        this.b = (int)(b*255);
    }

    /*
     * Cria uma cor dado seu nome
     */
    public Cor(String cor) {
        if(cor.equals("branco")) {
            r = 255;
            g = 255;
            b = 255;
        } else if(cor.equals("azul")) {
            b = 255;
        } else if(cor.equals("vermelho")) {
            r = 255;
        } else if(cor.equals("verde")) {
            g = 255;
        }
    }
}