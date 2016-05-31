/**
 * Created by lcs on 25/05/16.
 */
import java.util.Set;
import java.util.HashSet;

public class Nave {

    private double velocidade, posY, posX, anguloDirecao;
    public int valorRotacao = 0;
    public Ponto [] p = new Ponto[3];
    private Cor cor;
    Set<Tiro> Tiros = new HashSet<>();
    Set<Tiro> tirosParaSeremRemovidos = new HashSet<>();

    public Nave (double posX, double posY){
        this.velocidade = 0;
        this.posX = posX;
        this.posY = posY;
        this.anguloDirecao = 0;
        this.p[0] = new Ponto(5 , 0);
        this.p[1] = new Ponto(-3, -4);
        this.p[2] = new Ponto(-3, 4);
        this.cor = new Cor(Math.random(), Math.random(), Math.random());
    }

    public double getPosX(){
        return posX;
    }

    public double getPosY(){
        return posY;
    }

    public void rotacionaDireita(double dt){
        anguloDirecao = anguloDirecao + Math.PI * (dt/1);
        for(int i = 0 ; i < 3 ; i++){
            p[i].rotacao(anguloDirecao);
        }
    }

    public void rotacionaEsquerda(double dt){
        anguloDirecao = anguloDirecao - Math.PI * (dt/1);
        for(int i = 0 ; i < 3 ; i++){
            p[i].rotacao(anguloDirecao);
        }
    }

    public void movimentaFrente (double dt) {
        velocidade += 100 * (dt/1);
    }

    public void freio(double dt){
        if(velocidade > 0) {
            velocidade -= 100 * (dt / 1);
        }
        else{
            velocidade = 0;
        }
    }

    public void moveNave(double dt, double altura, double largura){
        posX += (velocidade * (dt)) * Math.cos(anguloDirecao);
        posY += (velocidade * (dt)) * Math.sin(anguloDirecao);
        if(posX > largura){
            posX = 0;
        }
        else if (posX < 0){
            posX = largura;
        }
        else if (posY > altura){
            posY = 0;
        }
        else if (posY < 0){
            posY = altura;
        }
    }

    public void desenhar (Tela t){
        t.triangulo(getPosX() + p[0].xPos, getPosY() + p[0].yPos, getPosX() + p[1].xPos, getPosY() + p[1].yPos, getPosX() + p[2].xPos, getPosY() + p[2].yPos, cor);
    }

    public void addTiros(){
        Tiro tirinho = new Tiro(getPosX() + p[0].xPos, getPosY()+ p[0].yPos, velocidade, anguloDirecao);
        Tiros.add(tirinho);
    }

    public void testeDosTiros(double altura, double largura){
        for(Tiro tiro : Tiros){
            tiro.testaRange(altura, largura);
            if(tiro.allowToRemove){
                tirosParaSeremRemovidos.add(tiro);
            }
        }
        this.Tiros.removeAll(this.tirosParaSeremRemovidos);
    }

    public void retorna(double largura, double altura){
        posX = largura/2;
        posY = altura/2;
        velocidade = 0;
    }

    public void destroy(){
        posX = posY = 10000000;
    }


}
