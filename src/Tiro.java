/**
 * Created by lcs on 29/05/16.
 */
public class Tiro {

    double posX, posY, velocidadeTiro, anguloDaNave;
    boolean allowToRemove;
    Cor cor;

    public Tiro(double posX, double posY, double velocidade, double anguloDaNave){

        this.posX = posX;
        this.posY = posY;
        this.velocidadeTiro = velocidade + 100;
        this.cor = Cor.BRANCO;
        this.anguloDaNave = anguloDaNave;
        this.allowToRemove = false;

    }

    public void desenhar(Tela t){
        t.circulo(posX, posY, 1, cor);
    }

    public void movimentaTiro(double dt){
        posX += velocidadeTiro * Math.cos(anguloDaNave) * dt;
        posY += velocidadeTiro * Math.sin(anguloDaNave) * dt;
//        System.out.println(posX);
    }

    public boolean testaRange(double alturaTela, double larguraTela){
        if(posX > larguraTela + 5 || posX < -5 || posY > alturaTela + 5 || posY < -5){
            allowToRemove = true;
        }
        return allowToRemove;
    }

}
