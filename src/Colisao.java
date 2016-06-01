/**
 * Created by lcs on 30/05/16.
 */
public class Colisao {

    public Colisao(){

    }

    public boolean testaColisaoNaveAsteroid(Nave nave, Asteroide asteroide){
        return Math.sqrt(Math.pow(asteroide.getX() - nave.getPosX(), 2) + Math.pow(asteroide.getY() - nave.getPosY(), 2)) < asteroide.getTamanho() + 5;
    }

    public boolean testaColisaoTiroAsteroid(Tiro tiro, Asteroide asteroide){
        return Math.sqrt(Math.pow(asteroide.getX() - tiro.getX(), 2) + Math.pow(asteroide.getY() - tiro.getY(), 2)) < asteroide.getTamanho() + 1;
    }
}
