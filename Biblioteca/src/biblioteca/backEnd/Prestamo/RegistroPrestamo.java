package biblioteca.backEnd.Prestamo;

import biblioteca.backEnd.Estudiante.Estudiante;
import biblioteca.backEnd.Libro.Libro;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author angel
 */
public class RegistroPrestamo implements Serializable, Comparable<RegistroPrestamo> {
    
    private PropertyChangeSupport propertySupport = new PropertyChangeSupport(this);
//    private List<DetallesPrestamo> detalles;
    // no se envia el objeto libro ni al estudiante
    private transient Libro libro;
    private transient Estudiante estudiante;
    // generamos nuestras llaves
    private String codigoLibro;
    private String carnetEstudiante;
    //datos necesarios para el prestamo del libro
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private double totalpago;
    private double totalpagoConMora;
    private int diasPrestamo;
    private int cantidadLibrosprestados;
    
    public static final String PROP_TOTAL ="total";
    public static final String PROP_TOTAL_MORA ="totalConMora";
    public static final String PROP_DIAS_PRESTAMO ="diasPrestamo";
    

    public RegistroPrestamo(Libro libro, Estudiante estudiante, String codigoLibro, String carnetEstudiante, LocalDate fechaPrestamo, LocalDate fechaDevolucion,double totalpago, double totalpagoConMora, int diasPrestamo) {
        this.libro = libro;
        this.estudiante = estudiante;
        this.codigoLibro = codigoLibro;
        this.carnetEstudiante = carnetEstudiante;
        this.fechaPrestamo = fechaPrestamo;
        this.totalpago = totalpago;
        this.totalpagoConMora = totalpagoConMora;
        this.diasPrestamo = diasPrestamo;
        this.cantidadLibrosprestados = libro.getCantidadLibro();
        this.fechaDevolucion = fechaDevolucion;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public String getCodigoLibro() {
        return codigoLibro;
    }

    public void setCodigoLibro(String codigoLibro) {
        this.codigoLibro = codigoLibro;
    }

    public String getCarnetEstudiante() {
        return carnetEstudiante;
    }

    public void setCarnetEstudiante(String carnetEstudiante) {
        this.carnetEstudiante = carnetEstudiante;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public double getTotalpago() {
        return totalpago;
    }

    public void setTotalpago(double totalpago) {
        double totalAnterior = this.totalpago;
        this.totalpago = totalpago;
        propertySupport.firePropertyChange(PROP_TOTAL,totalAnterior, totalpago);
    }

    public int getDiasPrestamo() {
        return diasPrestamo;
    }

    public void setDiasPrestamo(int diasPrestamo) {
        int diasAnterior = this.diasPrestamo;
        this.diasPrestamo = diasPrestamo;
        propertySupport.firePropertyChange(PROP_DIAS_PRESTAMO, diasAnterior, diasPrestamo);
    }

    public String getEstadoPrestamo() {
        return this.esCerrado() ? "Cerrado" : "Abierto";
    }

    public boolean esCerrado() {
        return this.diasPrestamo > 0;
    }

    public boolean getAntiguedad() {
        return fechaPrestamo.isBefore(LocalDate.now().minus(2, ChronoUnit.DAYS));
    }

    public double getTotalpagoConMora() {
        return totalpagoConMora;
    }

    public void setTotalpagoConMora(double totalpagoConMora) {
        double totalMoraAnterior = this.totalpagoConMora;
        this.totalpagoConMora = totalpagoConMora;
        propertySupport.firePropertyChange(PROP_TOTAL_MORA,totalMoraAnterior,totalpagoConMora);
    }

    public int getCantidadLibrosprestados() {
        return cantidadLibrosprestados;
    }

    public void setCantidadLibrosprestados(int cantidadLibrosprestados) {
        this.cantidadLibrosprestados = cantidadLibrosprestados;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
    
    
        @Override
    public RegistroPrestamo clone() {
        return new RegistroPrestamo(this.libro, this.estudiante,this.codigoLibro,this.carnetEstudiante,this.fechaPrestamo, this.fechaDevolucion,this.totalpago,this.totalpagoConMora,this.diasPrestamo);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }

    @Override
    public int compareTo(RegistroPrestamo o) {
        return String.valueOf(libro.getCantidadLibroPrestados()).compareTo(String.valueOf(o.libro.getCantidadLibroPrestados()));
    }
}
