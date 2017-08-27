package biblioteca.BackEnd.Manejadorer;

import biblioteca.BackEnd.Biblioteca.Biblioteca;
import biblioteca.BackEnd.Excepciones.InputsVaciosException;
import biblioteca.backEnd.Libro.Libro;
import biblioteca.backEnd.ManejadorDatosArchivo.manejadorDatosArchivo;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.ValidationException;

/**
 *
 * @author angel
 */
public class ManejadorLibros {
    
    private static final String ARCHIVO_LIBROS = "libros.dat";
    private File archivoLibros;
    private manejadorDatosArchivo<Libro> manejadorDatos;
    
    Libro libros;
    Biblioteca biblioteca;
    
    List<Libro> librosBuscados = new LinkedList<>();
    
    public ManejadorLibros(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
        archivoLibros = new File(ARCHIVO_LIBROS);
        manejadorDatos = new manejadorDatosArchivo<>();
    }

    /**
     * Agrega los datos del Frame a nuestro elemento Libro para luego guardarlo
     * en un listado que se encuentra almacenado en la clase biblioteca, y este
     * listado luego es guardado como archivo SE UTILIZA ORDENAMIENTO POR METODO
     * DE BURBUJA
     *
     * @param titulo
     * @param autor
     * @param codigoLibro
     * @param cantidadLibro
     * @param fecha
     * @param editorial
     * @return
     * @throws ValidationException
     * @throws InputsVaciosException
     */
    public Libro agregarLibro(String titulo, String autor, String codigoLibro, int cantidadLibro, String fecha, String editorial)
            throws ValidationException, InputsVaciosException {
        String tituloPrueba = titulo;
        String autorPrueba = autor;
        String codigoLibroPrueba = codigoLibro;
        String fechaPublicacionPrueva = fecha;
        
        try {
            biblioteca.fecha(fechaPublicacionPrueva);
        } catch (DateTimeException ex) {
            throw new ValidationException("Fecha Incorrecta");
        }
        
        DateTimeFormatter formato = DateTimeFormatter.ofPattern(Biblioteca.FORMATO_FECHA);
        LocalDate fechaPublicacion = LocalDate.parse(fecha, formato);
        
        if (tituloPrueba.replace(" ", "").isEmpty()
                || autorPrueba.replace(" ", "").isEmpty()
                || codigoLibroPrueba.replace("-", "").isEmpty()
                || fechaPublicacionPrueva.isEmpty()
                || editorial.replace(" ", "").isEmpty()) {
            throw new InputsVaciosException("Debe llenar todos los campos");
        } else {
            if (buscarLibroPorCodigo(codigoLibro) != null) {
                throw new InputsVaciosException("El libro ya existe con el codigo" + codigoLibro);
            } else if (buscarLibroPorTitulo(titulo) != null) {
                throw new InputsVaciosException("Ya se ha registrado un libro con ese Titulo" + titulo);
            } else {
                Libro nuevoLibro = new Libro(titulo, autor, codigoLibro, cantidadLibro, cantidadLibro, fechaPublicacion, editorial, 0);
                biblioteca.agregarLibro(nuevoLibro);
                manejadorDatos.ordenamientoBurbujaLibro(biblioteca.getLibros());
                try {
                    manejadorDatos.guardarLista(biblioteca.getLibros(), archivoLibros);
                } catch (Exception e) {
                    Logger.getLogger(ManejadorLibros.class.getName()).log(Level.SEVERE, null, e);
                }
                return nuevoLibro;
            }
        }
    }

    /**
     * Este metodo es funcional pero no se encuentra conectado a ningun Frame
     *
     * @param tituloOriginal
     * @param autorOriginal
     * @param codigoLibroOriginal
     * @param cantidadLibrosOriginal
     * @param libroModificado
     * @return
     * @throws ValidationException
     */
    public Libro modificarLibro(String tituloOriginal, String autorOriginal, String codigoLibroOriginal, int cantidadLibrosOriginal, Libro libroModificado) throws ValidationException {
        if (buscarLibroPorCodigo(codigoLibroOriginal) != null && libroModificado.getCodigoLibro().equalsIgnoreCase(codigoLibroOriginal)) {
            throw new ValidationException("Ya se ha registrado un libro con ese codigo");
        } else if (buscarLibroPorTitulo(libroModificado.getTitulo()) != null && libroModificado.getTitulo().equalsIgnoreCase(tituloOriginal)) {
            throw new ValidationException("ya existe un libro con el ese titulo" + libroModificado.getTitulo());
        } else {
            Libro libro = (Libro) buscarLibroPorCodigo(codigoLibroOriginal);
            libro.setTitulo(libroModificado.getTitulo());
            libro.setAutor(libroModificado.getAutor());
            libro.setCodigoLibro(libroModificado.getCodigoLibro());
            libro.setCantidadLibro(libroModificado.getCantidadLibro());
            libro.setFechaPublicacion(libroModificado.getFechaPublicacion());
            try {
                manejadorDatos.guardarLista(biblioteca.getLibros(), archivoLibros);
            } catch (Exception e) {
                Logger.getLogger(ManejadorLibros.class.getName()).log(Level.SEVERE, null, e);
            }
            return libro;
        }
    }
    
    /**
     *Se utiliza para enlazar los listados utilizando un COMBOBOX el el frame
     * respecto a la opcion seleccionada se motraran valores en la tabla
     * @param verOpcion
     * @param codigoLibro
     * @return
     */
    public List<Libro> buscarLibro(int verOpcion, String codigoLibro) {
        if (verOpcion == 0) {
            return biblioteca.getLibros();
        }
        if (verOpcion == 1) {
            return listadoLibrosPorCodigo(codigoLibro);
        }
        if (verOpcion == 2) {
            return listadoLibrosAgotados();
        }
        if (verOpcion == 3) {
            return listadoLibrosSinPrestar();
        }
        if (verOpcion == 4) {
         return listadoLibroMasPrestados();
        }
        return null;
    }
    
    public Libro cantLibrosPrestados(int cantLibros, String codigo) {
        Libro libro = (Libro) buscarLibroPorCodigo(codigo);
        libro.setCantidadLibro(libro.getCantidadLibro() - cantLibros);
        libro.setVecesPrestado(libro.getVecesPrestado() + cantLibros);
        return libro;
    }
    
    public Libro cantLibrosDevueltos(int cantLibros, String codigo) {
        Libro libro = (Libro) buscarLibroPorCodigo(codigo);
        libro.setCantidadLibro(libro.getCantidadLibro() + cantLibros);
        return libro;
    }
    
    public List<Libro> consultaLibros() {
        return biblioteca.getLibros();
    }
    
    public List<Libro> listadoLibrosPorCodigo(String codigoLibro) {
        librosBuscados.clear();
        librosBuscados.add(buscarLibroPorCodigo(codigoLibro));
        return librosBuscados;
    }
    
    public Libro buscarLibroPorCodigo(String codigoLibro) {
        for (Libro librosBusqueda : biblioteca.getLibros()) {
            if (librosBusqueda.getCodigoLibro().equalsIgnoreCase(codigoLibro)) {
                return librosBusqueda;
            }
        }
        return null;
    }
    
    public List<Libro> listadoLibrosPorAutor(String autor) {
        librosBuscados.clear();
        librosBuscados.add(buscarLibroPorAutor(autor));
        return librosBuscados;
    }
    
    public Libro buscarLibroPorAutor(String autor) {
        for (Libro librosBusqueda : biblioteca.getLibros()) {
            if (librosBusqueda.getAutor().equalsIgnoreCase(autor)) {
                return librosBusqueda;
            }
        }
        return null;
    }
    
    public List<Libro> listadoLibrosPorTitulo(String Titulo) {
        librosBuscados.clear();
        librosBuscados.add(buscarLibroPorTitulo(Titulo));
        return librosBuscados;
    }
    
    public Libro buscarLibroPorTitulo(String Titulo) {
        for (Libro librosBusqueda : biblioteca.getLibros()) {
            if (librosBusqueda.getTitulo().equalsIgnoreCase(Titulo)) {
                return librosBusqueda;
            }
        }
        return null;
    }
    
    public List<Libro> listadoLibrosPorFechaPublicacion(LocalDate FechaPublicacion) {
        librosBuscados.clear();
        librosBuscados.add(buscarLibroPorFechaPublicacion(FechaPublicacion));
        return librosBuscados;
    }
    
    public Libro buscarLibroPorFechaPublicacion(LocalDate FechaPublicacion) {
        for (Libro librosBusqueda : biblioteca.getLibros()) {
            if (librosBusqueda.getFechaPublicacion().equals(FechaPublicacion)) {
                return librosBusqueda;
            }
        }
        return null;
    }
    
    public Libro buscarLibrosAgotados() {
        for (Libro book : biblioteca.getLibros()) {
            if (book.getCantidadLibro() == 0) {
                return book;
            }
        }
        return null;
    }
    
    public List<Libro> listadoLibrosAgotados() {
        librosBuscados.clear();
        librosBuscados.add(buscarLibrosAgotados());
        return librosBuscados;
    }
    
    public Libro buscarLibrosSinPrestar() {
        for (Libro book : biblioteca.getLibros()) {
            if (book.getVecesPrestado() == 0) {
                return book;
            }
        }
        return null;
    }
    
    public List<Libro> listadoLibrosSinPrestar() {
        librosBuscados.clear();
        librosBuscados.add(buscarLibrosSinPrestar());
        return librosBuscados;
    }
    
    public Libro buscarLibrosMasPrestados() {
        for (Libro book : biblioteca.getLibros()) {
            if (book.getVecesPrestado() > 1) {
                return book;
            }
        }
        return null;
    }
    
    /**
     *devuelve los libros que se buscan
     * @return List
     */
    public List<Libro> listadoLibroMasPrestados() {
        manejadorDatos.ordenamientoBurbujaLibroMasPrestado(biblioteca.getLibros());
        librosBuscados.clear();
        while (librosBuscados.size() <= Biblioteca.CANTIDAD_LIBROS_MAS_PRESTADOS) {
            librosBuscados.add(buscarLibrosMasPrestados());
        }
        return librosBuscados;
    }
    
    /**
     *Va por el archivo y lo carga en el listado
     * que se encuentra en la clase Biblioteca
     * 
     */
    public void cargarListadoLibros() {
        try {
            getBiblioteca().setLibros(manejadorDatos.cargarLista(archivoLibros));
        } catch (Exception ex) {
            Logger.getLogger(ManejadorEstudiantes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Biblioteca getBiblioteca() {
        return biblioteca;
    }
    
    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }
}
