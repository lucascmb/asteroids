/**
 * Created by lcs on 25/05/16.
 */
public class Ponto {
    double x, y, xPos, yPos;

    public Ponto(double x, double y){
        this.x = this.xPos = x;
        this.y = this.yPos = y;
    }

    public void rotacao(double direcao){
        this.xPos = (this.x * Math.cos(direcao)) - (this.y * Math.sin(direcao));
        this.yPos = (this.x * Math.sin(direcao)) + (this.y * Math.cos(direcao));
    }
}
