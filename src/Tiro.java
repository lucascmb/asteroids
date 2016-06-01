/**
 * Created by lcs on 29/05/16.
 */
public class Tiro implements ObjetosConcretos {

    double posX, posY, velocidadeTiroX, velocidadeTiroY, anguloDaNave;
    boolean allowToRemove;
    Cor cor;

    public double getX(){
        return posX;
    }

    public double getY(){
        return posY;
    }

    public Tiro(double posX, double posY, double velocidadeX, double velocidadeY, double anguloDaNave){

        this.posX = posX;
        this.posY = posY;
        this.velocidadeTiroX = velocidadeX;
        this.velocidadeTiroY = velocidadeY;
        this.cor = Cor.BRANCO;
        this.anguloDaNave = anguloDaNave;
        this.allowToRemove = false;

    }

    public void desenhar(Tela t){
        t.circulo(posX, posY, 1, cor);
    }

    public void movimentaTiro(double dt){
        posX += (velocidadeTiroX + 100*Math.cos(anguloDaNave)) * dt;
        posY += (velocidadeTiroY + 100*Math.sin(anguloDaNave)) * dt;
    }

    public boolean testaRange(double alturaTela, double larguraTela){
        if(posX > larguraTela + 5 || posX < -5 || posY > alturaTela + 5 || posY < -5){
            allowToRemove = true;
        }
        return allowToRemove;
    }

}
