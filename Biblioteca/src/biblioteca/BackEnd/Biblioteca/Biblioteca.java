package biblioteca.BackEnd.Biblioteca;

import biblioteca.BackEnd.Manejadorer.ManejadorLibros;
import biblioteca.backEnd.Estudiante.Estudiante;
import biblioteca.backEnd.Libro.Libro;
import biblioteca.backEnd.Prestamo.RegistroPrestamo;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author angel
 */
public class Biblioteca {

    public static final int CANTIDAD_COPIAS_PODEMOS_PRESTAR_A_LA_VEZ = 1;
    public static final int CANTIDAD_LIBROS_MAS_PRESTADOS = 3;

    public static final double PRECIO_PRESTAMO_NORMAL = 5;
    public static final double PRECIO_PRESTAMO_MOROSO = 10;
    public static final int LIMITE_DIAS_HABILES_PRESTAMO = 3;
    public static final int LIMITE_DIAS_SIN_MORA = 5;
    public static final int DIAS_HABILES_SIN_COSTO = 2;

    public static final int CODIGO_CARRERA_INGENIERIA = 0;
    public static final int CODIGO_CARRERA_MEDICINA = 1;
    public static final int CODIGO_CARRERA_DERECHO = 2;
    public static final int CODIGO_CARRERA_ARQUITECTURA = 3;
    public static final int CODIGO_CARRERA_ADMINISTRACION = 4;

    public static final String FORMATO_FECHA = "d/MM/yyyy";

    private int librosDisponibles;

    private List<RegistroPrestamo> prestamo;
    private List<Estudiante> estudiante;
    private List<Libro> libros;

    ManejadorLibros manejadorLibro;

    public Biblioteca() {
        prestamo = new LinkedList<>();
        estudiante = new LinkedList<>();
        libros = new LinkedList<>();

//        DateTimeFormatter formato = DateTimeFormatter.ofPattern("d/MM/yyyy");
//
//        estudiante.add(new Estudiante("angel", "201631547", 3,LocalDate.parse("13/05/1997", formato), 1));
    }

    public int getLibrosDisponibles() {
        return librosDisponibles;
    }

    public void setLibrosDisponibles(int librosDisponibles) {
        this.librosDisponibles = librosDisponibles;
    }

    public List<RegistroPrestamo> getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(List<RegistroPrestamo> prestamo) {
        this.prestamo = prestamo;
    }

    public List<Estudiante> getEstudiante() {
        return estudiante;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setEstudiante(List<Estudiante> estudiante) {
        this.estudiante = estudiante;
    }

    public void set1Estudiante(Estudiante estudiante) {
        this.estudiante.add(estudiante);
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public ManejadorLibros getManejadorLibro() {
        return manejadorLibro;
    }

    public void setManejadorLibro(ManejadorLibros manejadorLibro) {
        this.manejadorLibro = manejadorLibro;
    }

    public void agregarLibro(Libro libro) {
        this.libros.add(libro);
    }

    public void agregarPrestamo(RegistroPrestamo prestamo) {
        this.prestamo.add(prestamo);
    }

    public LocalDate fecha(String dateToParse) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern(FORMATO_FECHA);
        LocalDate fecha = LocalDate.parse(dateToParse, formato);
        return fecha;
    }
}
