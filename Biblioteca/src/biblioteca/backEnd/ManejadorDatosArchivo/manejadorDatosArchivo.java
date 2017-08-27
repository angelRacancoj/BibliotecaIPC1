package biblioteca.backEnd.ManejadorDatosArchivo;

import biblioteca.backEnd.Estudiante.Estudiante;
import biblioteca.backEnd.Libro.Libro;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author angel
 */
public class manejadorDatosArchivo<T> {

    public void guardarLista(List<T> listado, File Archivo) throws IOException {
//        Archivo.setExecutable(true,false);
        ObjectOutputStream salidaObj = null;
        try {
            FileOutputStream salida = new FileOutputStream(Archivo);
            salidaObj = new ObjectOutputStream(salida);
            salidaObj.writeObject(listado);
        } catch (FileNotFoundException ex) {
            throw new IOException(ex);
        } catch (IOException ex) {
            throw new IOException(ex);
        } finally {
            salidaObj.close();
        }
    }

    public List<T> cargarLista(File archivo) throws IOException {
        if (archivo.exists()) {
            FileInputStream entrada = new FileInputStream(archivo);
            ObjectInputStream entradaObj = new ObjectInputStream(entrada);
            try {
                return (List<T>) entradaObj.readObject();
            } catch (ClassNotFoundException ex) {
                throw new IOException(ex);
            } finally {
                entradaObj.close();
            }
        } else {
            return new ArrayList<>();
        }
    }
    
    public void ordenamientoBurbuja(List<Estudiante> lista) {
        Estudiante auxiliar = new Estudiante();
        boolean cambios = false;

        while (true) {
            cambios = false;
            for (int i = 1; i < lista.size(); i++) {
                if (Long.parseLong(lista.get(i).getCarnet()) < Long.parseLong(lista.get(i - 1).getCarnet())) {
                    
//                    auxiliar.setCantidadLibroPrestados(lista.get(i).getCantidadLibroPrestados());
//                    auxiliar.setCarnet(lista.get(i).getCarnet());
//                    auxiliar.setCodigoCarrera(lista.get(i).getCodigoCarrera());
//                    auxiliar.setFechaCumple(lista.get(i).getFechaCumple());
//                    auxiliar.setNombre(lista.get(i).getNombre());
                    
                    Estudiante estA = lista.get(i-1);
                    Estudiante estB = lista.get(i);
                    
                    lista.set(i - 1, estB);
                    lista.set(i, estA);
                    
                    
//                    lista.get(i).setCantidadLibroPrestados(lista.get(i - 1).getCantidadLibroPrestados());
//                    lista.get(i).setCarnet(lista.get(i - 1).getCarnet());
//                    lista.get(i).setCodigoCarrera(lista.get(i - 1).getCodigoCarrera());
//                    lista.get(i).setFechaCumple(lista.get(i - 1).getFechaCumple());
//                    lista.get(i).setNombre(lista.get(i - 1).getNombre());
//
//                    lista.get(i - 1).setCantidadLibroPrestados(auxiliar.getCantidadLibroPrestados());
//                    lista.get(i - 1).setCarnet(auxiliar.getCarnet());
//                    lista.get(i - 1).setCodigoCarrera(auxiliar.getCodigoCarrera());
//                    lista.get(i - 1).setFechaCumple(auxiliar.getFechaCumple());
//                    lista.get(i - 1).setNombre(auxiliar.getNombre());

                    cambios=true;
                }
            }if (cambios==false) {
                break;
            }
        }
    }
    
    public void ordenamientoBurbujaLibro(List<Libro> lista) {
        Libro book = new Libro();
        boolean cambios = false;

        while (true) {
            cambios = false;
            for (int i = 1; i < lista.size(); i++) {
                if (lista.get(i).getCodigoLibro().compareTo(lista.get(i - 1).getCodigoLibro())<1){
                    
                    book.setAutor(lista.get(i).getAutor());
                    book.setCantidadLibro(lista.get(i).getCantidadLibro());
                    book.setCodigoLibro(lista.get(i).getCodigoLibro());
                    book.setEditorial(lista.get(i).getEditorial());
                    book.setFechaPublicacion(lista.get(i).getFechaPublicacion());
                    book.setTitulo(lista.get(i).getTitulo());
                    book.setVecesPrestado(lista.get(i).getVecesPrestado());
                    book.setCantidadLibrosInventario(lista.get(i).getCantidadLibrosInventario());
                    
                    lista.get(i).setAutor(lista.get(i-1).getAutor());
                    lista.get(i).setCantidadLibro(lista.get(i-1).getCantidadLibro());
                    lista.get(i).setCodigoLibro(lista.get(i-1).getCodigoLibro());
                    lista.get(i).setEditorial(lista.get(i-1).getEditorial());
                    lista.get(i).setFechaPublicacion(lista.get(i-1).getFechaPublicacion());
                    lista.get(i).setTitulo(lista.get(i-1).getTitulo());
                    lista.get(i).setVecesPrestado(lista.get(i-1).getVecesPrestado());
                    lista.get(i).setCantidadLibrosInventario(lista.get(i-1).getCantidadLibrosInventario());
                    
                    lista.get(i-1).setAutor(book.getAutor());
                    lista.get(i-1).setCantidadLibro(book.getCantidadLibro());
                    lista.get(i-1).setCodigoLibro(book.getCodigoLibro());
                    lista.get(i-1).setEditorial(book.getEditorial());
                    lista.get(i-1).setFechaPublicacion(book.getFechaPublicacion());
                    lista.get(i-1).setTitulo(book.getTitulo());
                    lista.get(i-1).setVecesPrestado(book.getVecesPrestado());
                    lista.get(i-1).setCantidadLibrosInventario(book.getCantidadLibrosInventario());
                    cambios=true;
                }
            }if (cambios==false) {
                break;
            }
        }
    }
    
    public void ordenamientoBurbujaLibroMasPrestado(List<Libro> lista) {
        Libro book = new Libro();
        boolean cambios = false;

        while (true) {
            cambios = false;
            for (int i = 1; i < lista.size(); i++) {
                if (lista.get(i).getVecesPrestado()<lista.get(i - 1).getVecesPrestado()){
                    
                    book.setAutor(lista.get(i).getAutor());
                    book.setCantidadLibro(lista.get(i).getCantidadLibro());
                    book.setCodigoLibro(lista.get(i).getCodigoLibro());
                    book.setEditorial(lista.get(i).getEditorial());
                    book.setFechaPublicacion(lista.get(i).getFechaPublicacion());
                    book.setTitulo(lista.get(i).getTitulo());
                    book.setVecesPrestado(lista.get(i).getVecesPrestado());
                    
                    lista.get(i).setAutor(lista.get(i-1).getAutor());
                    lista.get(i).setCantidadLibro(lista.get(i-1).getCantidadLibro());
                    lista.get(i).setCodigoLibro(lista.get(i-1).getCodigoLibro());
                    lista.get(i).setEditorial(lista.get(i-1).getEditorial());
                    lista.get(i).setFechaPublicacion(lista.get(i-1).getFechaPublicacion());
                    lista.get(i).setTitulo(lista.get(i-1).getTitulo());
                    lista.get(i).setVecesPrestado(lista.get(i-1).getVecesPrestado());
                    
                    lista.get(i-1).setAutor(book.getAutor());
                    lista.get(i-1).setCantidadLibro(book.getCantidadLibro());
                    lista.get(i-1).setCodigoLibro(book.getCodigoLibro());
                    lista.get(i-1).setEditorial(book.getEditorial());
                    lista.get(i-1).setFechaPublicacion(book.getFechaPublicacion());
                    lista.get(i-1).setTitulo(book.getTitulo());
                    lista.get(i-1).setVecesPrestado(book.getVecesPrestado());
                    cambios=true;
                }
            }if (cambios==false) {
                break;
            }
        }
    }

}
