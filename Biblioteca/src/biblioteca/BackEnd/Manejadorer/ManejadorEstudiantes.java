package biblioteca.BackEnd.Manejadorer;

import biblioteca.BackEnd.Biblioteca.Biblioteca;
import biblioteca.BackEnd.Excepciones.InputsVaciosException;
import biblioteca.backEnd.Estudiante.Estudiante;
import biblioteca.backEnd.ManejadorDatosArchivo.manejadorDatosArchivo;
import java.io.File;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.ValidationException;

/**
 *
 * @author angel
 */
public class ManejadorEstudiantes {

    private static final String ARCHIVO_ESTUDIANTES = "estudiantes.dat";
    private File archivoEstudiantes;
    private manejadorDatosArchivo<Estudiante> manejadorDatos;

    Estudiante estudiante;
    Biblioteca biblioteca;

    List<Estudiante> busquedaEstudiantes = new ArrayList<>();

    public ManejadorEstudiantes(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
        archivoEstudiantes = new File(ARCHIVO_ESTUDIANTES);
        manejadorDatos = new manejadorDatosArchivo<>();
    }

    /**
     *agrega los parametros para colocarlos dentro del listado de archivos
     * @param nombre
     * @param carnet
     * @param codigoCarrera
     * @param fechaCumple
     * @return
     * @throws ValidationException
     * @throws InputsVaciosException
     * @throws DateTimeParseException
     */
    public Estudiante agregarEstudiante(String nombre, String carnet, int codigoCarrera, String fechaCumple) throws ValidationException, InputsVaciosException, DateTimeParseException {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate fecha = LocalDate.parse(fechaCumple, formato);

        try {
            biblioteca.fecha(fechaCumple);
        } catch (DateTimeException ex) {
            throw new ValidationException("Fecha Incorrecta");
        }

        String nombrePrueba = nombre;
        String carnetPrueba = carnet;

        if (nombrePrueba.replace(" ", "").isEmpty() || carnetPrueba.replace(" ", "").isEmpty() || fechaCumple.replace(" ", "").replace("-", "").isEmpty()) {
            throw new InputsVaciosException("Deben llenarse todos los campos");
        }
//        else {
        if (buscarEstPorCarnet(carnet) != null) {
            throw new InputsVaciosException("Ya existe el estudiante con el carnet: " + carnet);
        } else if (buscarEstPorNombre(nombre) != null) {
            throw new InputsVaciosException("Ya existe el estudiante con el carnet: " + carnet);
        } else {
            Estudiante newEst = new Estudiante(nombre, carnet, codigoCarrera, fecha, 0,0,0);
            biblioteca.set1Estudiante(newEst);
            manejadorDatos.ordenamientoBurbuja(biblioteca.getEstudiante());
            try {
                manejadorDatos.guardarLista(biblioteca.getEstudiante(), archivoEstudiantes);
            } catch (Exception e) {
                Logger.getLogger(ManejadorLibros.class.getName()).log(Level.SEVERE, null, e);
            }
            return newEst;
        }
//        }
    }

    /**
     *Metodo util pero actualmente desconectado de los frames
     * @param nombreOriginal
     * @param carnetOriginal
     * @param codigoCarreraOriginal
     * @param fechaCumpleOriginal
     * @param estModificado
     * @return
     * @throws ValidationException
     * @throws InputsVaciosException
     */
    public Estudiante modificarEstudiante(String nombreOriginal, String carnetOriginal, int codigoCarreraOriginal, String fechaCumpleOriginal, Estudiante estModificado)
            throws ValidationException, InputsVaciosException {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern(Biblioteca.FORMATO_FECHA);
        LocalDate fecha = LocalDate.parse(fechaCumpleOriginal, formato);

        if ((buscarEstPorNombre(nombreOriginal) != null && estModificado.getNombre().equalsIgnoreCase(nombreOriginal))
                || (buscarEstPorCarnet(carnetOriginal) != null && estModificado.getCarnet().equalsIgnoreCase(carnetOriginal))) {
            throw new ValidationException("Ya existe el Estudiante");
        } else {
            Estudiante est = (Estudiante) buscarEstPorCarnet(carnetOriginal);
            est.setNombre(estModificado.getNombre());
            est.setCarnet(estModificado.getCarnet());
            est.setCodigoCarrera(estModificado.getCodigoCarrera());
            est.setFechaCumple(estModificado.getFechaCumple());
            try {
                manejadorDatos.guardarLista(biblioteca.getEstudiante(), archivoEstudiantes);
            } catch (Exception e) {
                Logger.getLogger(ManejadorLibros.class.getName()).log(Level.SEVERE, null, e);
            }
            return est;
        }
    }

    /**
     *muestra todos los estudiantes
     * @return
     */
    public List<Estudiante> consultaEstudiantes() {
        return biblioteca.getEstudiante();
    }

    public Estudiante buscarEstPorNombre(String nombre) {
        for (Estudiante est : biblioteca.getEstudiante()) {
            if (est.getNombre().equalsIgnoreCase(nombre)) {
                return est;
            }
        }
        return null;
    }

    public List<Estudiante> listadoEstPorNombre(String nombre) {
        busquedaEstudiantes.clear();
        busquedaEstudiantes.add(buscarEstPorNombre(nombre));
        return busquedaEstudiantes;
    }

    public Estudiante buscarEstPorCarnet(String carnet) {
        for (Estudiante est : biblioteca.getEstudiante()) {
            if (est.getCarnet().equalsIgnoreCase(carnet)) {
                return est;
            }
        }
        return null;
    }

    /**
     *busqueda de estudaintes por carnet
     * @param carnet
     * @return
     */
    public List<Estudiante> listadoEstPorCarnet(String carnet) {
        busquedaEstudiantes.clear();
        busquedaEstudiantes.add(buscarEstPorCarnet(carnet));
        return busquedaEstudiantes;
    }

    public Estudiante buscarEstPorCodCarrera(int codCarrera) {
        for (Estudiante est : biblioteca.getEstudiante()) {
            if (est.getCodigoCarrera() == codCarrera) {
                return est;
            }
        }
        return null;
    }

    public List<Estudiante> listadoEstPorCarrera(int codCarrera) {
        busquedaEstudiantes.clear();
        busquedaEstudiantes.add(buscarEstPorCodCarrera(codCarrera));
        return busquedaEstudiantes;
    }

    public Estudiante buscarEstPorCumple(LocalDate cumple) {
        for (Estudiante est : biblioteca.getEstudiante()) {
            if (est.getFechaCumple().equals(cumple)) {
                return est;
            }
        }
        return null;
    }

    public List<Estudiante> listadoEstPorCumple(LocalDate cumple) {
        busquedaEstudiantes.clear();
        busquedaEstudiantes.add(buscarEstPorCumple(cumple));
        return busquedaEstudiantes;
    }

    /**
     *carga los listados que se encuentran en el archivo binario
     */
    public void cargarListadoEst() {
        try {
            getBiblioteca().setEstudiante(manejadorDatos.cargarLista(archivoEstudiantes));
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
