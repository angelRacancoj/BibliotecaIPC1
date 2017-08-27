package biblioteca.BackEnd.Manejadorer;

import biblioteca.BackEnd.Biblioteca.Biblioteca;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author angel
 */
public class ManejadorDatosBiblioteca {
    private static final String ARCHIVO_BIBLIOTECA = "biblioteca.dat";
    private File archivoBiblioteca;

    public ManejadorDatosBiblioteca() {
        archivoBiblioteca = new File(ARCHIVO_BIBLIOTECA);
    }
    
    public void GuardarInfo(Biblioteca biblioteca)throws IOException{
        ObjectOutputStream salidaObj = null;
        try {
            FileOutputStream salida = new FileOutputStream(archivoBiblioteca);
            salidaObj = new ObjectOutputStream(salida);
            salidaObj.writeObject(biblioteca);
        } catch (FileNotFoundException ex) {
            throw new IOException(ex);
        }catch(IOException ex){
            throw new IOException(ex);
        }finally{
            salidaObj.close();
        }
    }
    
    public Biblioteca cargarBiblioteca() throws IOException{
        if (archivoBiblioteca.exists()) {
            FileInputStream entrada = new FileInputStream(archivoBiblioteca);
            ObjectInputStream entradaObj = new ObjectInputStream(entrada);
            try{
                return (Biblioteca) entradaObj.readObject();
            }catch(ClassNotFoundException ex){
                throw new IOException(ex);
            }finally{
                entradaObj.close();
            }
        }else{
            return new Biblioteca();
        }
    }
}
