/**
 * Created by lcs on 25/05/16.
 */
public class Asteroide{

    private double coordX, coordY, velX, velY;
    private int tamanho;
    private Cor cor;

    public Asteroide(double coordX, double coordY, double velX, double velY, int tamanho){
        this.coordX = coordX;
        this.coordY = coordY;
        this.velX = velX;
        this.velY = velY;
        this.tamanho = tamanho*10;
        this.cor = new Cor(Math.random(), Math.random(), Math.random());
    }

    public double getTamanho(){
        return tamanho;
    }

    public double getX(){
        return coordX;
    }

    public double getY(){
        return coordY;
    }

    public double getVelX(){
        return velX;
    }

    public double getVelY(){
        return velY;
    }

    public void mudaTamanho(int novoTamanho){
        this.tamanho = novoTamanho;
    }

    public void desenhar(Tela t) {
        t.circulo(coordX, coordY, tamanho, cor);
    }

    public void identificaAsteroide(double tempo, double altura, double largura){
        movimenta(tempo, altura, largura, verificaPosicao(altura, largura));
    }

    private int verificaPosicao(double altura, double largura){
        if(coordX > this.tamanho + largura) return 0;
        else if(coordX < -this.tamanho) return 1;
        else if(coordY > this.tamanho + altura) return 2;
        else if(coordY < -this.tamanho) return 3;

        return 666;
    }

    private void movimenta(double tempo, double altura, double largura, int valida){
        switch (valida){
            case 0:
                coordX = -this.tamanho;
                break;
            case 1:
                coordX = this.tamanho + largura;
                break;
            case 2:
                coordY = -this.tamanho;
                break;
            case 3:
                coordY = this.tamanho + altura;
                break;
        }
        this.coordX += largura * (tempo/velX);
        this.coordY += altura * (tempo/velY);
    }

}
