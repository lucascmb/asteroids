/**
 * Created by lcs on 25/05/16.
 */
public class Asteroide extends ObjetosGerais implements ObjetosConcretos{

    private int tamanho;

    private Cor cor;

    public Asteroide(double coordX, double coordY, double velX, double velY, int tamanho){
        this.posX = coordX;
        this.posY = coordY;
        this.velocidadeX = velX;
        this.velocidadeY = velY;
        this.tamanho = tamanho*10;
        this.cor = new Cor(Math.random(), Math.random(), Math.random());
    }

    public double getTamanho(){
        return tamanho;
    }

    public double getX(){
        return posX;
    }

    public double getY(){
        return posY;
    }

    public double getVelX(){
        return velocidadeX;
    }

    public double getVelY(){
        return velocidadeY;
    }

    public void mudaAsteroid(int novoTamanho){
        this.tamanho = novoTamanho;
        this.velocidadeX *= -1;
    }

    public void desenhar(Tela t) {
        t.circulo(posX, posY, tamanho, cor);
    }

    public void identificaAsteroide(double tempo, double altura, double largura){
        movimenta(tempo, altura, largura);
    }

    private void verificaPosicao(double altura, double largura){
        if(posX > this.tamanho + largura) posX = -this.tamanho;
        else if(posX < -this.tamanho) posX = this.tamanho + largura;
        else if(posY > this.tamanho + altura) posY = -this.tamanho;
        else if(posY < -this.tamanho) posY = this.tamanho + altura;

    }

    public void movimenta(double tempo, double altura, double largura){
        this.posX += largura * (tempo/velocidadeX);
        this.posY += altura * (tempo/velocidadeY);

        verificaPosicao(altura, largura);
    }

}
