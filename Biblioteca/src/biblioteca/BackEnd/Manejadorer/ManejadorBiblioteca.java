package biblioteca.BackEnd.Manejadorer;

import biblioteca.BackEnd.Biblioteca.Biblioteca;
import biblioteca.BackEnd.Excepciones.InputsVaciosException;
import biblioteca.backEnd.Estudiante.Estudiante;
import biblioteca.backEnd.Libro.Libro;
import biblioteca.backEnd.ManejadorDatosArchivo.manejadorDatosArchivo;
import biblioteca.backEnd.Prestamo.RegistroPrestamo;
import java.io.File;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.ValidationException;

/**
 *
 * @author angel
 */
public class ManejadorBiblioteca {

    private static final String ARCHIVO_PRESTAMOS = "prestamosBiblioteca.dat";
    private File archivoBiblioteca;
    private manejadorDatosArchivo<RegistroPrestamo> manejadorDatos;
    private ManejadorEstudiantes manejadorEstudiantes;
    private ManejadorLibros manejadoLibros;
//    private ManejadorDatosBiblioteca manejadorDatosBiblioteca;

    private Biblioteca biblioteca;
    private Estudiante estudiante;
    private Libro libro;
    private RegistroPrestamo registroPrestamo;

    List<RegistroPrestamo> registroPrestamosList = new LinkedList<>();

    public ManejadorBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
        manejadorDatos = new manejadorDatosArchivo<>();
        manejadorEstudiantes = new ManejadorEstudiantes(biblioteca);
        manejadoLibros = new ManejadorLibros(biblioteca);
        archivoBiblioteca = new File(ARCHIVO_PRESTAMOS);
    }

    /**
     * Se encarga de guardar los datos necesarios del prestamo dentro del
     * listado de prestamos que se encuentra en la clase Biblioteca
     *
     * @param codigoLibro
     * @param fechaPrestamo
     * @param carnet
     * @param manejadorEst
     * @param manejadorBook
     * @return
     * @throws ValidationException
     * @throws InputsVaciosException
     */
    public RegistroPrestamo PrestarLibro(String codigoLibro, String fechaPrestamo, String carnet, ManejadorEstudiantes manejadorEst, ManejadorLibros manejadorBook) throws ValidationException, InputsVaciosException {
        String codPrueba = codigoLibro;
        String fechaPresPrueva = fechaPrestamo;
        String carnetPrueva = carnet;

        if (codPrueba.replace(" ", "").trim().isEmpty() || fechaPresPrueva.replace("-", "").trim().isEmpty() || carnetPrueva.trim().isEmpty()) {
            throw new InputsVaciosException("Debe llenar todos los campos");
        } else {
            Libro book = this.manejadoLibros.buscarLibroPorCodigo(codigoLibro);
            Estudiante est = this.manejadorEstudiantes.buscarEstPorCarnet(carnet);
            try {
                biblioteca.fecha(fechaPresPrueva);
            } catch (DateTimeException ex) {
                throw new ValidationException("Fecha Incorrecta");
            }
            if (est == null) {
                throw new ValidationException("No existe el Estudiante: " + carnet);
            }
            if (buscarRegistroAbierto(carnet, codigoLibro) != null) {
                throw new ValidationException("Ya ha prestado el libro codigo: " + codigoLibro);
            }
            int cantLibros = est.getCantidadLibroPrestados();
            System.out.println("Cantidad libros:" + cantLibros);
            if (cantLibros > Estudiante.CANTIDAD_MAXIMA_LIBROS) {
                throw new ValidationException("Ya supero la cantidad de libros prestados maximo (" + Estudiante.CANTIDAD_MAXIMA_LIBROS + ")");
            }
            if (book.getCantidadLibro() == 0) {
                throw new ValidationException("El libro " + book.getTitulo() + " ya se hagotaron las existencias");
            } else {
                est.setCantidadLibroPrestados(est.getCantidadLibroPrestados() + Biblioteca.CANTIDAD_COPIAS_PODEMOS_PRESTAR_A_LA_VEZ);
                book.setCantidadLibro(book.getCantidadLibro() - Biblioteca.CANTIDAD_COPIAS_PODEMOS_PRESTAR_A_LA_VEZ);
                book.setVecesPrestado(book.getVecesPrestado() + Biblioteca.CANTIDAD_COPIAS_PODEMOS_PRESTAR_A_LA_VEZ);
                RegistroPrestamo registro = new RegistroPrestamo(book, est, codigoLibro, carnet, biblioteca.fecha(fechaPrestamo), null, 0, 0, 0);
                biblioteca.agregarPrestamo(registro);
//                Collections.sort(registroPrestamosList);
                try {
                    manejadorDatos.guardarLista(biblioteca.getPrestamo(), archivoBiblioteca);
                } catch (IOException ex) {
                    Logger.getLogger(ManejadorBiblioteca.class.getName()).log(Level.SEVERE, null, ex);
                }

                return registro;
            }
        }
    }

    /**
     * A?ade los datos necesarios al elemento de la lista generado por el mtodo
     * para guardar prestamos
     *
     * @param carnet
     * @param codLibro
     * @param fechaDevolucion
     * @return
     * @throws ValidationException
     * @throws InputsVaciosException
     */
    public RegistroPrestamo DevolucionLibro(String carnet, String codLibro, String fechaDevolucion) throws ValidationException, InputsVaciosException {
        String carnetPrueba = carnet;
        String codLibroPrueba = codLibro;
        String fechaDevPrueba = fechaDevolucion;
        try {
            biblioteca.fecha(fechaDevolucion);
        } catch (DateTimeException ex) {
            throw new ValidationException("Fecha Incorrecta");
        }
        if (carnetPrueba.trim().isEmpty() || codLibroPrueba.replace("-", "").trim().isEmpty() || fechaDevPrueba.replace("/", "").trim().isEmpty()) {
            throw new ValidationException("Debe llenar todos los campos");
        }
        if (buscarPorFechaYCarnet(carnet, codLibro) == null) {
            throw new ValidationException("No se ha realizado el prestamo");
        } else {
            Libro book = manejadoLibros.buscarLibroPorCodigo(codLibro);
            Estudiante est = manejadorEstudiantes.buscarEstPorCarnet(carnet);
            RegistroPrestamo registroP = buscarPorFechaYCarnet(carnet, codLibro);
            try {
                LocalDate fechaDev = biblioteca.fecha(fechaDevolucion);
                LocalDate fechaRec = registroP.getFechaPrestamo();
                int dias = cantidadDiasPrestamo(fechaRec, fechaDev);
                System.out.println("dias: " + dias);
                double total = calcularTotal(dias);
                System.out.println("total: " + total);
                if (dias <= Biblioteca.LIMITE_DIAS_HABILES_PRESTAMO) {
                    registroP.setTotalpago(total);
                    registroP.setTotalpagoConMora(1);
                    est.setPrestamosNormales(est.getPrestamosNormales() + 1);
                } else {
                    registroP.setTotalpagoConMora(total);
                    registroP.setTotalpago(1);
                    est.setPrestamosConMora(est.getPrestamosConMora() + 1);
                }
                registroP.setFechaDevolucion(fechaDev);
                registroP.setDiasPrestamo(cantidadDiasPrestamo(fechaRec, fechaDev));
                est.setCantidadLibroPrestados(est.getCantidadLibroPrestados() - Biblioteca.CANTIDAD_COPIAS_PODEMOS_PRESTAR_A_LA_VEZ);
                book.setCantidadLibro(book.getCantidadLibro() + Biblioteca.CANTIDAD_COPIAS_PODEMOS_PRESTAR_A_LA_VEZ);
                manejadorDatos.guardarLista(biblioteca.getPrestamo(), archivoBiblioteca);
            } catch (Exception e) {
                throw new ValidationException("Error");
            }
            return registroP;
        }

    }

    /**
     * verifica que el libro cuente con una o mas copias
     *
     * @param codigoLibro
     * @return
     */
    public boolean VerificarExistencia(String codigoLibro) {
        return manejadoLibros.buscarLibroPorCodigo(codigoLibro).getCantidadLibro() >= 1;
    }

    /**
     * recorrermos el listado en biblioteca y extraemos los registros abiertos
     *
     * @param carnet
     * @param codLibro
     * @return
     */
    public RegistroPrestamo buscarRegistroAbierto(String carnet, String codLibro) {
        for (RegistroPrestamo registro : biblioteca.getPrestamo()) {
            if (registro.getEstudiante().getCarnet().equalsIgnoreCase(carnet) && registro.getLibro().getCodigoLibro().equalsIgnoreCase(codLibro) && !registro.esCerrado()) {
                return registro;
            }
        }
        return null;
    }

    /**
     * Utilizamos las opciones para generar las salidas en la tabla d resoltados
     *
     * @param verOpcion
     * @return
     */
    public List<RegistroPrestamo> buscarRegistro(int verOpcion, int opcionCarrera, String fechaInicial, String fechaFinal) {
        if (verOpcion == 0) {
            return biblioteca.getPrestamo();
        }
        if (verOpcion == 1) {
            return listadoPrestamoPorCarrera(opcionCarrera);
        }
        List<RegistroPrestamo> resultado = new LinkedList<>();
        for (RegistroPrestamo registro : biblioteca.getPrestamo()) {
            if (!registro.esCerrado() && verOpcion == 2) {
                resultado.add(registro);
            } else if (registro.esCerrado() && verOpcion == 3) {
                resultado.add(registro);
            }
        }
        if (verOpcion == 4) {
            return listadoLibrosPorEntregarHoy();
        }
        if (verOpcion == 5) {
            return listadoLibrosPorEntregarConMora();
        }if (verOpcion==6) {
            return listadoPrestamosPorCarrerayFechas(fechaInicial, fechaFinal);
        }if (verOpcion==7) {
            return listadoPrestamosMorosos();
        }
        return resultado;
    }

    /**
     * con la ayuda de otro metodo hacemos el calculo del total.
     *
     * @param diasPrestamo
     * @return
     */
    public double calcularTotal(int diasPrestamo) {
        if (diasPrestamo >= 1 && diasPrestamo <= Biblioteca.LIMITE_DIAS_HABILES_PRESTAMO) {
            double total = diasPrestamo * Biblioteca.PRECIO_PRESTAMO_NORMAL;
            System.out.println("total dentro:" + total);
            return total;
        } else if (diasPrestamo > Biblioteca.LIMITE_DIAS_HABILES_PRESTAMO) {
            double totalMora = Biblioteca.LIMITE_DIAS_HABILES_PRESTAMO * Biblioteca.PRECIO_PRESTAMO_NORMAL + ((diasPrestamo - Biblioteca.LIMITE_DIAS_HABILES_PRESTAMO) * Biblioteca.PRECIO_PRESTAMO_MOROSO);
            System.out.println("total con mora: " + totalMora);
            return totalMora;
        } else {
            return 0;
        }
    }

    public int cantidadDiasPrestamo(LocalDate fechaPrestamo, LocalDate fechaDevolucion) {
        int numeroDias = fechaDevolucion.getDayOfYear() - fechaPrestamo.getDayOfYear() - Biblioteca.DIAS_HABILES_SIN_COSTO;
        if (numeroDias >= 1) {
            return numeroDias;
        }
        return 0;
    }

    public void cargarListadoRegistros() {
        try {
            getBiblioteca().setPrestamo(manejadorDatos.cargarLista(archivoBiblioteca));
        } catch (Exception ex) {
            Logger.getLogger(ManejadorBiblioteca.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public RegistroPrestamo LibrosConPrestamosAbiertos() {
        for (RegistroPrestamo registros : biblioteca.getPrestamo()) {
            if (!registros.esCerrado()) {
                return registros;
            }
        }
        return null;
    }

    public List<RegistroPrestamo> listadoLibrosConRegistroAbierto() {
        registroPrestamosList.clear();
        registroPrestamosList.add(LibrosConPrestamosAbiertos());
        Collections.sort(registroPrestamosList);
        return registroPrestamosList;
    }

    public RegistroPrestamo librosPorEntregarHoy() {
        for (RegistroPrestamo registrosP : biblioteca.getPrestamo()) {
            if (registrosP.getAntiguedad()) {
                return registrosP;
            }
        }
        return null;
    }

    public List<RegistroPrestamo> listadoLibrosPorEntregarHoy() {
        registroPrestamosList.clear();
        registroPrestamosList.add(librosPorEntregarHoy());
        return registroPrestamosList;
    }

    public RegistroPrestamo librosPorEntregarConMora() {
        for (RegistroPrestamo registrosP : biblioteca.getPrestamo()) {
            if (cantidadDiasPrestamo(registrosP.getFechaPrestamo(), LocalDate.now()) > Biblioteca.LIMITE_DIAS_HABILES_PRESTAMO) {
                return registrosP;
            }
        }
        return null;
    }

    public List<RegistroPrestamo> listadoLibrosPorEntregarConMora() {
        registroPrestamosList.clear();
        registroPrestamosList.add(librosPorEntregarConMora());
        return registroPrestamosList;
    }

    /**
     * Listado de pretamos ya pagados en un periodo d tiempo
     *
     * @param fechaInicial
     * @param FechaFinal
     * @return
     */
    public RegistroPrestamo prestamosCerradosConIntervaloDeTiempo(String fechaInicial, String FechaFinal) {
        int fechaInicio = biblioteca.fecha(fechaInicial).getDayOfYear();
        int fechaFinal = biblioteca.fecha(FechaFinal).getDayOfYear();
        for (RegistroPrestamo registroP : biblioteca.getPrestamo()) {
            int diasPrestamo = registroP.getFechaDevolucion().getDayOfYear();
            if (diasPrestamo >= fechaInicio && diasPrestamo <= fechaFinal && registroP.esCerrado()) {
                return registroP;
            }
        }
        return null;
    }

    /**
     * Se obtienen todos los ingresos generados durante un periodo de tiempo
     * indicado x el usuario
     *
     * @param fechaInicial
     * @param FechaFinal
     * @return
     */
    public double prestamosCerradosConTotal(String fechaInicial, String FechaFinal) {
        double total = 0;
        int fechaInicio = biblioteca.fecha(fechaInicial).getDayOfYear();
        int fechaFinal = biblioteca.fecha(FechaFinal).getDayOfYear();
        for (RegistroPrestamo registroP : biblioteca.getPrestamo()) {
            int diasPrestamo = registroP.getFechaDevolucion().getDayOfYear();
            if (diasPrestamo >= fechaInicio && diasPrestamo <= fechaFinal && registroP.esCerrado()) {
                return total += registroP.getTotalpago() + registroP.getTotalpagoConMora() - Biblioteca.CANTIDAD_COPIAS_PODEMOS_PRESTAR_A_LA_VEZ;
            }
        }
        return 0;
    }

    /**
     * Todas los registros que ya existen y que ya han cancelado por el prestamo
     *
     * @param fechaInicial
     * @param fechaFinal
     * @return
     */
    public List<RegistroPrestamo> listadoCerradoConIntervaloDeTiempo(String fechaInicial, String fechaFinal) {
        registroPrestamosList.clear();
        registroPrestamosList.add(prestamosCerradosConIntervaloDeTiempo(fechaInicial, fechaFinal));
        return registroPrestamosList;
    }

    /**
     * Calculamos cuantos registros existen por carrera, al colocar el codigo de
     * la carrera y las fechas
     *
     * @param fechaInicial
     * @param FechaentregaLibro
     * @param codigoCarrera
     * @return
     */
    public Integer cantidadDeRegistrosPorCarrera(String fechaInicial, String FechaentregaLibro, int codigoCarrera) {
        int cantidad = 0;
        int fechaInicio = biblioteca.fecha(fechaInicial).getDayOfYear();
        int fechaFinal = biblioteca.fecha(FechaentregaLibro).getDayOfYear();
        for (RegistroPrestamo registroP : biblioteca.getPrestamo()) {
            int diasPrestamo = registroP.getFechaDevolucion().getDayOfYear();
            if (diasPrestamo >= fechaInicio && diasPrestamo <= fechaFinal && registroP.getEstudiante().getCodigoCarrera() == codigoCarrera) {
                return cantidad++;
            }

        }
        return 0;
    }

    /**
     * Buscamos ne los registros de prestamos a todos los registros que se
     * encuentre entre dos dias
     *
     * @param fechaInicial
     * @param fechaentregaLibro
     * @param codigoCarrera
     * @return
     */
    public RegistroPrestamo prestamosPorFechasYCarrera(String fechaInicial, String fechaentregaLibro, int codigoCarrera) {
        int fechaInicio = biblioteca.fecha(fechaInicial).getDayOfYear();
        int fechaFinal = biblioteca.fecha(fechaentregaLibro).getDayOfYear();
        for (RegistroPrestamo registroP : biblioteca.getPrestamo()) {
            int diasPrestamo = registroP.getFechaDevolucion().getDayOfYear();
            if (diasPrestamo >= fechaInicio && diasPrestamo <= fechaFinal && registroP.getEstudiante().getCodigoCarrera() == codigoCarrera) {
                return registroP;
            }
        }
        return null;
    }

    /**
     * Todos los regisntro en una carrera, si no existe diferencia entre ellos
     * lanza nuevamente todos el listado
     *
     * @param fechaInicial
     * @param fechaentregaLibro
     * @return
     */
    public List<RegistroPrestamo> listadoPrestamosPorCarrerayFechas(String fechaInicial, String fechaentregaLibro) {
        int ingenieria = cantidadDeRegistrosPorCarrera(fechaInicial, fechaentregaLibro, Biblioteca.CODIGO_CARRERA_INGENIERIA);
        int medicina = cantidadDeRegistrosPorCarrera(fechaInicial, fechaentregaLibro, Biblioteca.CODIGO_CARRERA_MEDICINA);
        int derecho = cantidadDeRegistrosPorCarrera(fechaInicial, fechaentregaLibro, Biblioteca.CODIGO_CARRERA_DERECHO);
        int arquitectura = cantidadDeRegistrosPorCarrera(fechaInicial, fechaentregaLibro, Biblioteca.CODIGO_CARRERA_ARQUITECTURA);
        int admin = cantidadDeRegistrosPorCarrera(fechaInicial, fechaentregaLibro, Biblioteca.CODIGO_CARRERA_ADMINISTRACION);

        registroPrestamosList.clear();

        if (ingenieria > medicina && ingenieria > derecho && ingenieria > arquitectura && ingenieria > admin) {
            registroPrestamosList.add(prestamosPorFechasYCarrera(fechaInicial, fechaentregaLibro, Biblioteca.CODIGO_CARRERA_INGENIERIA));
            return registroPrestamosList;
        } else if (medicina > ingenieria && medicina > derecho && medicina > arquitectura && medicina > admin) {
            registroPrestamosList.add(prestamosPorFechasYCarrera(fechaInicial, fechaentregaLibro, Biblioteca.CODIGO_CARRERA_MEDICINA));
            return registroPrestamosList;
        } else if (derecho > ingenieria && derecho > medicina && derecho > arquitectura && derecho > admin) {
            registroPrestamosList.add(prestamosPorFechasYCarrera(fechaInicial, fechaentregaLibro, Biblioteca.CODIGO_CARRERA_DERECHO));
            return registroPrestamosList;
        } else if (arquitectura > ingenieria && arquitectura > medicina && arquitectura > derecho && arquitectura > admin) {
            registroPrestamosList.add(prestamosPorFechasYCarrera(fechaInicial, fechaentregaLibro, Biblioteca.CODIGO_CARRERA_ARQUITECTURA));
            return registroPrestamosList;
        } else if (admin > ingenieria && admin > medicina && admin > derecho && admin > arquitectura) {
            registroPrestamosList.add(prestamosPorFechasYCarrera(fechaInicial, fechaentregaLibro, Biblioteca.CODIGO_CARRERA_ADMINISTRACION));
            return registroPrestamosList;
        } else {
            try {
                return biblioteca.getPrestamo();
            } catch (Exception e) {
            }
        }
        return null;
    }

    /**
     *Entrega el listado en base a las carreras existente, tanto de los prestamos "Abiertos" como los "Cerrados"
     * @param codigoCarrera
     * @return
     */
    public RegistroPrestamo prestamosPorCarrera(int codigoCarrera) {
        for (RegistroPrestamo registroP : biblioteca.getPrestamo()) {
            if (registroP.getEstudiante().getCodigoCarrera() == codigoCarrera) {
                return registroP;
            }
        }
        return null;
    }
    
    public List<RegistroPrestamo> listadoPrestamoPorCarrera(int codigoCarrera){
        registroPrestamosList.clear();
        registroPrestamosList.add(prestamosPorCarrera(codigoCarrera));
        return registroPrestamosList;
    }
    
    public RegistroPrestamo prestamosMorosos(){
        for (RegistroPrestamo registroP : biblioteca.getPrestamo()) {
            if (registroP.esCerrado()&& registroP.getDiasPrestamo()>Biblioteca.DIAS_HABILES_SIN_COSTO) {
                return registroP;
            }
        }
        return null;
    }
    
    public List<RegistroPrestamo> listadoPrestamosMorosos(){
        registroPrestamosList.clear();
        registroPrestamosList.add(prestamosMorosos());
        return registroPrestamosList;
    }

    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public LocalDate fecha(String dateToParse) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern(Biblioteca.FORMATO_FECHA);
        LocalDate fecha = LocalDate.parse(dateToParse, formato);
        return fecha;
    }

    public RegistroPrestamo buscarPorFechaYCarnet(String carnet, String codLibro) {
        for (RegistroPrestamo registroPre : biblioteca.getPrestamo()) {
            if (!registroPre.esCerrado() && registroPre.getCarnetEstudiante().equalsIgnoreCase(carnet) && registroPre.getCodigoLibro().equalsIgnoreCase(codLibro)) {
                return registroPre;
            }
        }
        return null;
    }
}
