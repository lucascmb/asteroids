/**
 * Created by lcs on 25/05/16.
 */
import java.util.Set;
import java.util.HashSet;

public class Nave implements ObjetosConcretos {

    private double velocidadeX, velocidadeY, velocidadeTotal;
    private double posY;
    private double posX;
    private double anguloDirecao;

    public int valorRotacao = 0;

    public Ponto [] p = new Ponto[3];

    private Cor cor;

    Set<Tiro> Tiros = new HashSet<>();
    Set<Tiro> tirosParaSeremRemovidos = new HashSet<>();

    public Nave (double posX, double posY){
        this.velocidadeX = this.velocidadeY = 0;
        this.posX = posX;
        this.posY = posY;
        this.anguloDirecao = 0;
        this.p[0] = new Ponto(5 , 0);
        this.p[1] = new Ponto(-3, -4);
        this.p[2] = new Ponto(-3, 4);
        this.cor = new Cor(Math.random(), Math.random(), Math.random());
    }

    public double getX(){
        return posX;
    }

    public double getY(){
        return posY;
    }

    public void rotacionaDireita(double dt){
        anguloDirecao = anguloDirecao + Math.PI * (dt);
        for(int i = 0 ; i < 3 ; i++){
            p[i].rotacao(anguloDirecao);
        }
    }

    public void rotacionaEsquerda(double dt){
        anguloDirecao = anguloDirecao - Math.PI * (dt);
        for(int i = 0 ; i < 3 ; i++){
            p[i].rotacao(anguloDirecao);
        }
    }

    public void movimentaFrente (double dt) {
        velocidadeX += 100 * (dt) * Math.cos(anguloDirecao);
//        System.out.println(velocidadeX);
        velocidadeY += 100 * (dt) * Math.sin(anguloDirecao);
//        System.out.println(velocidadeY);
    }

    public void freio(double dt){
        if(velocidadeX > 0) {
            velocidadeX -= 100 * dt;
        }
        if(velocidadeY > 0){
            velocidadeY -= 100 * dt;
        }
        if(velocidadeX < 0){
            velocidadeX += 100 * dt;
        }
        if(velocidadeY < 0){
            velocidadeY += 100 * dt;
        }
    }

    public void moveNave(double dt, double altura, double largura){
        this.posX += (velocidadeX * (dt));
        this.posY += (velocidadeY * (dt));
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
        t.triangulo(getX() + p[0].xPos, getY() + p[0].yPos, getX() + p[1].xPos, getY() + p[1].yPos, getX() + p[2].xPos, getY() + p[2].yPos, cor);
    }

    public void addTiros(){
        Tiro tirinho = new Tiro(getX() + p[0].xPos, getY()+ p[0].yPos, velocidadeX, velocidadeY, anguloDirecao);
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
        velocidadeX = velocidadeY = 0;
    }

    public void destroy(){
        posX = posY = 124887;
    }


}
