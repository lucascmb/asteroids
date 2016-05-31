/**
 * Created by lcs on 25/05/16.
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.FontMetrics;

/**
 * Tela para desenho.
 *
 */
public class Tela
{
    Graphics g;

    public static int ESQUERDA = 0;
    public static int FUNDO    = 0;
    public static int DIREITA  = 1;
    public static int CENTROX  = 2;
    public static int TOPO     = 4;
    public static int CENTROY  = 8;
    public static int CENTRO   = CENTROX | CENTROY;

    public Tela(Graphics g) {
        this.g = g;
        g.setColor(Color.white);
    }

    public void triangulo(int x1, int y1,
                          int x2, int y2, int x3, int y3, Cor cor) {
        g.setColor(new Color(cor.r, cor.g, cor.b));
        g.fillPolygon(new int[] { x1, x2, x3 },
                new int[] { y1, y2, y3 },
                3);
    }

    public void triangulo(double x1, double y1,
                          double x2, double y2, double x3, double y3, Cor cor) {
        triangulo((int)Math.round(x1),
                (int)Math.round(y1),
                (int)Math.round(x2),
                (int)Math.round(y2),
                (int)Math.round(x3),
                (int)Math.round(y3), cor);
    }

    public void circulo(int cx, int cy, int raio, Cor cor) {
        g.setColor(new Color(cor.r, cor.g, cor.b));
        g.fillOval(cx - raio, cy - raio, raio*2, raio*2);
    }

    public void circulo(double cx, double cy, int raio, Cor cor) {
        circulo((int)Math.round(cx),
                (int)Math.round(cy),
                raio, cor);
    }

    public void quadrado(int x, int y, int lado, Cor cor) {
        g.setColor(new Color(cor.r, cor.g, cor.b));
        g.fillRect(x, y, lado, lado);
    }

    public void quadrado(double x, double y, int lado, Cor cor) {
        quadrado((int)Math.round(x), (int)Math.round(y),
                lado, cor);
    }

    public void retangulo(int x, int y, int largura, int altura, Cor cor) {
        g.setColor(new Color(cor.r, cor.g, cor.b));
        g.fillRect(x, y, largura, altura);
    }

    public void retangulo(double x, double y, int largura, int altura, Cor cor) {
        retangulo((int)Math.round(x), (int)Math.round(y),
                largura, altura, cor);
    }

    public void texto(String texto, int x, int y, int tamanho, Cor cor) {
        g.setColor(new Color(cor.r, cor.g, cor.b));
        g.setFont(new Font("Arial", Font.BOLD, tamanho));
        g.drawString(texto, x, y);
    }

    public void texto(String texto, double x, double y, int tamanho, Cor cor) {
        texto(texto, (int)Math.round(x), (int)Math.round(y), tamanho, cor);
    }

    // O parametro pos da o alinhamento do texto, e deve ser uma combinação
    // de ESQUERDO, DIREITO, TOPO, FUNDO
    public void texto(String texto, int x, int y, int larg, int alt, int pos, int tamanho, Cor cor) {
        Font f = new Font("Arial", Font.BOLD, tamanho);
        FontMetrics fm   = g.getFontMetrics(f);
        java.awt.geom.Rectangle2D rect = fm.getStringBounds(texto, g);

        int textHeight = (int)(rect.getHeight());
        int textWidth  = (int)(rect.getWidth());

        y = y + alt;

        // Center text horizontally and vertically
        if((pos & CENTROX) == CENTROX)
            x = x + larg/2 - textWidth/2;
        else if((pos & DIREITA) == DIREITA)
            x = x + larg - textWidth;
        if((pos & CENTROY) == CENTROY)
            y = y - alt/2 - textHeight/2 + fm.getAscent();
        else if((pos & TOPO) == TOPO)
            y = y - alt + textHeight;
        g.setColor(new Color(cor.r, cor.g, cor.b));
        g.setFont(f);
        g.drawString(texto, x, y);
    }

}
