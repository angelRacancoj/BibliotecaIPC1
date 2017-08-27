package biblioteca.backEnd.Libro;

import biblioteca.backEnd.Prestamo.RegistroPrestamo;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author angel
 */
public class Libro implements Serializable {

    private PropertyChangeSupport propertySupport = new PropertyChangeSupport(this);

    public static final String FORMATO_FECHA = "yyyy-MM-dd";

    private String titulo;
    private String autor;
    private String codigoLibro;
    private int cantidadLibro;
    private int cantidadLibrosInventario;
    private LocalDate fechaPublicacion;
    private RegistroPrestamo prestamo;
    private int vecesPrestado;
    private String editorial;

    private List<RegistroPrestamo> prestamoLibro;

    public static final String PROP_TITULO = "Titulo";
    public static final String PROP_AUTOR = "Autor";
    public static final String PROP_COD_LIBRO = "codigoLibro";
    public static final String PROP_CANT_LIBROS = "cantidadLibros";
    public static final String PROP_EDITORIAL = "editorial";

    public Libro(String titulo, String autor, String codigoLibro, int cantidadLibro,int librosInventario, LocalDate fechaPublicacion,String editorial, int vecesPrestado) {
        this.titulo = titulo;
        this.autor = autor;
        this.codigoLibro = codigoLibro;
        this.cantidadLibro = cantidadLibro;
        this.fechaPublicacion = fechaPublicacion;
        this.editorial= editorial;
        this.vecesPrestado = vecesPrestado;
        this.cantidadLibrosInventario= librosInventario;
        this.prestamo = prestamo;
    }

    public Libro() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        String tituloAnterior = this.titulo;
        this.titulo = titulo;
        propertySupport.firePropertyChange(PROP_TITULO, tituloAnterior, titulo);
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        String autorAnterior = this.autor;
        this.autor = autor;
        propertySupport.firePropertyChange(PROP_AUTOR, autorAnterior, autor);
    }

    public String getCodigoLibro() {
        return codigoLibro;
    }

    public void setCodigoLibro(String codigoLibro) {
        String codigoLibroAnterior = this.codigoLibro;
        this.codigoLibro = codigoLibro;
        propertySupport.firePropertyChange(PROP_COD_LIBRO, codigoLibroAnterior, codigoLibro);
    }

    public int getCantidadLibro() {
        return cantidadLibro;
    }
    
    public int getCantidadLibroPrestados(){
        return getCantidadLibrosInventario()-cantidadLibro;
    }

    public void setCantidadLibro(int cantidadLibro) {
        int cantidadLibrosAnterior = this.cantidadLibro;
        this.cantidadLibro = cantidadLibro;
        propertySupport.firePropertyChange(PROP_CANT_LIBROS, cantidadLibrosAnterior, cantidadLibro);
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public RegistroPrestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(RegistroPrestamo prestamo) {
        this.prestamo = prestamo;
    }

    public int getVecesPrestado() {
        return vecesPrestado;
    }

    public void setVecesPrestado(int vecesPrestado) {
        this.vecesPrestado = vecesPrestado;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        String editorialAnt = this.editorial;
        this.editorial = editorial;
        propertySupport.firePropertyChange(PROP_EDITORIAL,editorialAnt,editorial);
    }

    public int getCantidadLibrosInventario() {
        return cantidadLibrosInventario;
    }

    public void setCantidadLibrosInventario(int cantidadLibrosInventario) {
        this.cantidadLibrosInventario = cantidadLibrosInventario;
    }

    @Override
    public Libro clone() {
        return new Libro(this.titulo, this.autor, this.codigoLibro, this.cantidadLibro,this.cantidadLibrosInventario, this.fechaPublicacion,this.editorial, this.vecesPrestado);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }
}
