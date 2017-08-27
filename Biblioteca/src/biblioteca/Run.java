package biblioteca;

import biblioteca.BackEnd.Biblioteca.Biblioteca;
import biblioteca.FrontIn.Biblioteca.BibliotecaGIU;
import java.util.Locale;

/**
 *
 * @author angel
 */
public class Run {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Locale.setDefault(new Locale("en", "US"));
        Biblioteca biblioteca = new Biblioteca();
        BibliotecaGIU bibliotecaGUI = new BibliotecaGIU(biblioteca);
        bibliotecaGUI.setVisible(true);
    }
    
}
