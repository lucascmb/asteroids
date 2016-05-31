import java.util.HashSet;
import java.util.Set;

/**
 * Created by lcs on 25/05/16.
 */
public class Inicia implements Jogo {

    Set<Asteroide> asteroides = new HashSet<>();
    Nave nave = new Nave(getLargura()/2, getAltura()/2);
    public int life = 3;

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
        else if (teclas.contains("right")){
            nave.rotacionaDireita(dt);
        }

//        Controlam a velocidade com que a nave se move

        else if (teclas.contains("up")) {
            nave.movimentaFrente(dt);
        }
        if(!teclas.contains("up")){
            nave.freio(dt);
        }
        nave.moveNave(dt, getAltura(), getLargura());

        nave.testeDosTiros(getAltura(), getLargura());

        for(Tiro shoot : nave.Tiros){
            shoot.movimentaTiro(dt);
        }

        for(Asteroide asteroide : asteroides){
            Colisao colision = new Colisao();
            if(colision.testaColisaoNaveAsteroid(nave,asteroide)){
                this.life--;
                if(life > 0) {
                    nave.retorna(getLargura(), getAltura());
                }
                else{
                    nave.destroy();
                }
            }
        }
    }

    public void desenhar(Tela tela){
        for(Asteroide ast : asteroides){
            ast.desenhar(tela);
        }
        nave.desenhar(tela);
        for(Tiro tiro : nave.Tiros){
            tiro.desenhar(tela);
        }
        if(life == 0) {
            tela.texto("GAME OVER", getLargura()/2, getAltura()/2, 20, Cor.VERMELHO);
        }
    }

    public void tecla(String tecla){
        if(tecla.equals("x")){
            nave.addTiros();
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
