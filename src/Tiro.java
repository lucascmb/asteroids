/**
 * Created by lcs on 29/05/16.
 */
public class Tiro extends ObjetosGerais implements ObjetosConcretos {

    private double anguloDaNave;

    private boolean allowToRemove;

    private Cor cor;

    public double getX(){
        return posX;
    }

    public double getY(){
        return posY;
    }

    public Tiro(double posX, double posY, double velocidadeX, double velocidadeY, double anguloDaNave){

        this.posX = posX;
        this.posY = posY;
        this.velocidadeX = velocidadeX;
        this.velocidadeY = velocidadeY;
        this.cor = Cor.BRANCO;
        this.anguloDaNave = anguloDaNave;
        this.allowToRemove = false;

    }

    public void desenhar(Tela t){
        t.circulo(posX, posY, 1, cor);
    }

    public void movimenta(double dt, double alturaTela, double larguraTela){
        posX += (velocidadeX + 100*Math.cos(anguloDaNave)) * dt;
        posY += (velocidadeY + 100*Math.sin(anguloDaNave)) * dt;
        if(posX > larguraTela + 5 || posX < -5 || posY > alturaTela + 5 || posY < -5){
            allowToRemove = true;
        }
    }

    public boolean testaRange(){
        return allowToRemove;
    }

}
