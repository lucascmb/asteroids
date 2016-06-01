import java.util.HashSet;
import java.util.Set;

/**
 * Created by lcs on 25/05/16.
 */
public class Inicia implements Jogo {

    Set<Asteroide> asteroides = new HashSet<>();
    Set<Asteroide> asteroidesAtingidos = new HashSet<>();
    Set<Asteroide> asteroidesParaSeremRemovidos = new HashSet<>();

    Nave nave = new Nave(getLargura()/2, getAltura()/2);

    private int life = 3;
    private int pontos = 0;

    boolean naveViva = false;

    public Inicia(){
        for(int i = 0 ; i < 6 ; i++){
            Asteroide ast = new Asteroide(limitaValor(0), limitaValor(0) , limitaValor(1), limitaValor(1), (int)limitaValor(2));
            asteroides.add(ast);
        }
    }

    public String getTitulo(){
        return "Asteroids";
    }

    public int getLargura(){
        return 1024;
    }

    public int getAltura(){
        return 768;
    }

    public void tique(Set<String> teclas, double dt){
        for(Asteroide ast : asteroides){
            ast.identificaAsteroide(dt, getAltura(), getLargura());
        }



//        Controlam a rotação da nave

        if(teclas.contains("left")) {
            nave.rotacionaEsquerda(dt);
        }
        if (teclas.contains("right")){
            nave.rotacionaDireita(dt);
        }

//        Controlam a velocidade com que a nave se move

        if (teclas.contains("up")) {
            nave.movimentaFrente(dt);
            naveViva = true;
        }
        if(!teclas.contains("up")){
            nave.freio(dt);
        }
        nave.moveNave(dt, getAltura(), getLargura());

        nave.testeDosTiros(getAltura(), getLargura());

        for(Tiro shoot : nave.Tiros){
            shoot.movimentaTiro(dt);
        }

        if(naveViva) {
            for (Asteroide asteroide : asteroides) {
                Colisao colision = new Colisao();
                if (colision.testaColisaoNaveAsteroid(nave, asteroide)) {
                    this.life--;
                    naveViva = false;
                    if (life > 0) {
                        nave.retorna(getLargura(), getAltura());
                    } else {
                        nave.destroy();
                    }
                }
            }
        }

        for(Asteroide asteroide : asteroides){
            for(Tiro tiroDado : nave.Tiros){
                Colisao colision = new Colisao();
                if(colision.testaColisaoTiroAsteroid(tiroDado, asteroide)){
                    pontos += 10;
                    nave.tirosParaSeremRemovidos.add(tiroDado);
                    asteroidesAtingidos.add(asteroide);
                }
            }
        }
        for(Asteroide asteroide : asteroidesAtingidos){
            if(asteroide.getTamanho() == 10 || asteroide.getTamanho() == 20) asteroidesParaSeremRemovidos.add(asteroide);
            else if(asteroide.getTamanho() == 30){
                Asteroide novoasteroide = new Asteroide(asteroide.getX(), asteroide.getY(), -asteroide.getVelX(), -asteroide.getVelY(), 1);
                asteroides.add(novoasteroide);
                asteroide.mudaTamanho(10);
            }
            else if(asteroide.getTamanho() == 40){
                Asteroide novoasteroide = new Asteroide(asteroide.getX(), asteroide.getY(), -asteroide.getVelX(), -asteroide.getVelY(), 2);
                asteroides.add(novoasteroide);
                asteroide.mudaTamanho(20);
            }
        }
        asteroides.removeAll(asteroidesParaSeremRemovidos);
        asteroidesAtingidos.removeAll(asteroidesAtingidos);
        asteroidesParaSeremRemovidos.removeAll(asteroidesParaSeremRemovidos);
    }

    public void desenhar(Tela tela){
        for(Asteroide ast : asteroides){
            ast.desenhar(tela);
        }
        if(this.life > 0) {
            nave.desenhar(tela);
        }
        for(Tiro tiro : nave.Tiros){
            tiro.desenhar(tela);
        }
        if(this.life == 0) {
            tela.texto("GAME OVER", getLargura()/2, getAltura()/2, 20, Cor.VERMELHO);
        }
        tela.texto("" + pontos, 50, 80, 50, Cor.CINZA);
        tela.texto("" + life, 924, 80, 50, Cor.CINZA);
        if(!naveViva){
            tela.texto("APERTE A TECLA UP PARA COMEÇAR O JOGO", getLargura()/2 - 300, 700, 25, Cor.VERMELHO);
            tela.texto("Aperte X para atirar", 370, 100, 25, Cor.CINZA);
            tela.texto( "As teclas LEFT e RIGHT comandam a rotação da nave, enquanto a tecla UP permite acelerar para o lado apontado pela nave", 5, 150, 15, Cor.CINZA);
        }
    }

    public void tecla(String tecla){
        if(naveViva) {
            if (tecla.equals("x")) {
                nave.addTiros();
            }
        }
    }

    public static void main (String[] args){
        new Motor(new Inicia());
    }

    private double limitaValor(int valor){
        double x = Math.random();
        switch(valor) {
            case 0:
                x = x * 768;
                break;
            case 1:
                x = Math.pow((-1), (int)(x*2)) * (1 + (x * 20));
                break;
            case 2:
                x = 1 + (x * 4);
                break;
        }
        return x;
    }

}
