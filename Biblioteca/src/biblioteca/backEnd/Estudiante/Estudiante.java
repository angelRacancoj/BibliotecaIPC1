package biblioteca.backEnd.Estudiante;

import biblioteca.BackEnd.Biblioteca.Biblioteca;
import biblioteca.backEnd.Prestamo.RegistroPrestamo;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author angel
 */
public class Estudiante implements Serializable {

    public static final int CANTIDAD_MAXIMA_LIBROS = 3;

    private PropertyChangeSupport propertySupport = new PropertyChangeSupport(this);

    private String nombre;
    private String carnet;
    private int codigoCarrera;
    private LocalDate fechaCumple;
    private int cantidadLibroPrestados;
    private int prestamosNormales;
    private int prestamosConMora;

    public static final String PROP_NOMBRE = "nombre";
    public static final String PROP_CARNET = "carnet";
    public static final String PROP_COD_CARRERA = "CodigoCarrera";
    public static final String PROP_CANT_LIBROS = "CantidadLibrosPrestados";
    public static final String PROP_PRESTAMOS_NORMALES = "prestamosNormales";
    public static final String PROP_PRESTAMOS_MORA = "prestamosConMora";

    public Estudiante(String nombre, String carnet, int codigoCarrera, LocalDate fechaCumple, int cantidadLibrosPrestados, int prestamosNormales, int prstamosConMora) {
        this.nombre = nombre;
        this.carnet = carnet;
        this.codigoCarrera = codigoCarrera;
        this.fechaCumple = fechaCumple;
        this.cantidadLibroPrestados = cantidadLibrosPrestados;
        this.prestamosNormales = prestamosNormales;
        this.prestamosConMora = prstamosConMora;
    }

    public Estudiante() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        String nombreAnterior = this.nombre;
        this.nombre = nombre;
        propertySupport.firePropertyChange(PROP_NOMBRE, nombreAnterior, nombre);
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        String carnetAnterior = this.carnet;
        this.carnet = carnet;
        propertySupport.firePropertyChange(PROP_CARNET, carnetAnterior, carnet);
    }

    public int getCodigoCarrera() {
        return codigoCarrera;
    }

    /**
     *pernite mostrar en la tabla la carrera y no solo en numero q le pertenece a la carrera
     * @return
     */
    public String getCodigoCarreraStr() {
        switch (codigoCarrera) {
            case Biblioteca.CODIGO_CARRERA_INGENIERIA:
                return "Ingenieria";
            case Biblioteca.CODIGO_CARRERA_MEDICINA:
                return "Medicina";
            case Biblioteca.CODIGO_CARRERA_DERECHO:
                return "Derecho";
            case Biblioteca.CODIGO_CARRERA_ARQUITECTURA:
                return "Arquitectura";
            case Biblioteca.CODIGO_CARRERA_ADMINISTRACION:
                return "Administracion";
        }
        return null;
    }

    public void setCodigoCarrera(int codigoCarrera) {
        int codigoCarreraAnterior = this.codigoCarrera;
        this.codigoCarrera = codigoCarrera;
        propertySupport.firePropertyChange(PROP_COD_CARRERA, codigoCarreraAnterior, codigoCarrera);
    }

    public LocalDate getFechaCumple() {
        return fechaCumple;
    }

    public void setFechaCumple(LocalDate fechaCumple) {
        this.fechaCumple = fechaCumple;
    }

    public int getCantidadLibroPrestados() {
        return cantidadLibroPrestados;
    }

    public void setCantidadLibroPrestados(int cantidadLibroPrestados) {
        int cantidadLibroPrestadosAnterior = this.cantidadLibroPrestados;
        this.cantidadLibroPrestados = cantidadLibroPrestados;
        propertySupport.firePropertyChange(PROP_CANT_LIBROS, cantidadLibroPrestadosAnterior, cantidadLibroPrestados);
    }

    public int getPrestamosNormales() {
        return prestamosNormales;
    }

    public void setPrestamosNormales(int prestamosNormales) {
        int prestamoNormalAnteiror = this.prestamosNormales;
        this.prestamosNormales = prestamosNormales;
        propertySupport.firePropertyChange(PROP_PRESTAMOS_NORMALES,prestamoNormalAnteiror,prestamosNormales);
    }

    public int getPrestamosConMora() {
        return prestamosConMora;
    }

    public void setPrestamosConMora(int prestamosConMora) {
        int prestamoMora = this.prestamosConMora;
        this.prestamosConMora = prestamosConMora;
        propertySupport.firePropertyChange(PROP_PRESTAMOS_MORA,prestamoMora,prestamosConMora);
    }

    @Override
    public Estudiante clone() {
        return new Estudiante(this.nombre, this.carnet, this.codigoCarrera, this.fechaCumple, this.cantidadLibroPrestados, this.prestamosNormales, this.prestamosConMora);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }

}
