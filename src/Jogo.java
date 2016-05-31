/**
 * Created by lcs on 25/05/16.
 */
import java.util.Set;

public interface Jogo {
    String getTitulo();
    int getLargura();
    int getAltura();
    void tique(Set<String> teclas, double dt);
    void desenhar(Tela tela);
    void tecla(String tecla);
}