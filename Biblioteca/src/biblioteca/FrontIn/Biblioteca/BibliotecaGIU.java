package biblioteca.FrontIn.Biblioteca;

import biblioteca.FrontIn.GUIPretamos.RegresarLibro;
import biblioteca.FrontIn.GUIPretamos.PrestarLibro;
import biblioteca.BackEnd.Biblioteca.Biblioteca;
import biblioteca.BackEnd.ImportarArchivo.ManejadorArchivo;
import biblioteca.BackEnd.Manejadorer.ManejadorBiblioteca;
import biblioteca.BackEnd.Manejadorer.ManejadorDatosBiblioteca;
import biblioteca.BackEnd.Manejadorer.ManejadorEstudiantes;
import biblioteca.BackEnd.Manejadorer.ManejadorLibros;
import biblioteca.FrontIn.Estudiante.NuevoEstudianteFrame;
import biblioteca.FrontIn.Estudiante.listadoEstudiantesFrame;
import biblioteca.FrontIn.Libro.ListadoLibrosFrame;
import biblioteca.FrontIn.Libro.nuevoLibroFrame;
import biblioteca.FrontIn.Reportes.LibrosEnPrestamo;
import biblioteca.FrontIn.Reportes.PrestamosGeneral;

/**
 *
 * @author angel
 */
public class BibliotecaGIU extends javax.swing.JFrame {

    private ManejadorEstudiantes manejadorEstudiante;
    private ManejadorLibros manejadorLibro;
    private ManejadorDatosBiblioteca manejadorDatosBiblioteca;
    private ManejadorBiblioteca manejadorBiblioteca;
    private ManejadorArchivo manejadorArchivos;
    //Frames
    private NuevoEstudianteFrame nuevoEstudianteFrame;
    private listadoEstudiantesFrame listaEstudiantesFrame;
    private nuevoLibroFrame nuevoLibroFrame;
    private ListadoLibrosFrame listadoLibrosFrame;
    private PrestarLibro prestarLibro;
    private RegresarLibro regresarLibro;
    private PrestamosGeneral prestamoGeneral;
    private ImportarArchivo importarArchivo;
    private LibrosEnPrestamo librosEnPrestamo;
    /**
     * Creates new form Biblioteca
     */
    public BibliotecaGIU(Biblioteca biblioteca) {
        this.manejadorEstudiante= new ManejadorEstudiantes(biblioteca);
        this.manejadorLibro = new ManejadorLibros(biblioteca);
        this.manejadorDatosBiblioteca = new ManejadorDatosBiblioteca();
        this.manejadorBiblioteca = new ManejadorBiblioteca(biblioteca);
        this.manejadorArchivos = new ManejadorArchivo();
        
        manejadorEstudiante.cargarListadoEst();
        manejadorLibro.cargarListadoLibros();
        manejadorBiblioteca.cargarListadoRegistros();
        
        nuevoEstudianteFrame = new NuevoEstudianteFrame(true, manejadorEstudiante);
        listaEstudiantesFrame = new listadoEstudiantesFrame(manejadorEstudiante);
        nuevoLibroFrame = new nuevoLibroFrame(true, manejadorLibro);
        listadoLibrosFrame = new ListadoLibrosFrame(manejadorLibro);
//        prestarLibro = new PrestarLibro(true, manejadorPrestamos);
        prestarLibro = new PrestarLibro(true, manejadorBiblioteca, manejadorEstudiante, manejadorLibro);
        regresarLibro = new RegresarLibro(true, manejadorBiblioteca, manejadorLibro, manejadorEstudiante);
        prestamoGeneral = new PrestamosGeneral(manejadorBiblioteca);
        importarArchivo = new ImportarArchivo(manejadorArchivos);
        librosEnPrestamo = new LibrosEnPrestamo(manejadorBiblioteca);
        
        
        initComponents();
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        this.DesktopPane.add(listaEstudiantesFrame);
        this.DesktopPane.add(listadoLibrosFrame);
        this.DesktopPane.add(prestamoGeneral);
        this.DesktopPane.add(importarArchivo);
        this.DesktopPane.add(librosEnPrestamo);
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        DesktopPane = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        nuevoEstudianteMenuItem = new javax.swing.JMenuItem();
        listaEstudiantesMenuItem = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        nuevoLibroMenuItem = new javax.swing.JMenuItem();
        listaLibrosMenuItem = new javax.swing.JMenuItem();
        prestamoMenu = new javax.swing.JMenu();
        prestarLibroMenuItem = new javax.swing.JMenuItem();
        devolverLibroMenuItem = new javax.swing.JMenuItem();
        listadoprestamosMenuItem = new javax.swing.JMenuItem();
        librosDevolverHoyMenu = new javax.swing.JMenu();
        librosPrestadosMenuItem = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        menuArchivoMenu = new javax.swing.JMenu();
        cargarArchivoMenuItem = new javax.swing.JMenuItem();

        jMenu2.setText("jMenu2");

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        jMenuItem3.setText("jMenuItem3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Biblioteca CUNOC");

        jMenu1.setText("Estudiantes");

        nuevoEstudianteMenuItem.setText("Nuevo estudiante");
        nuevoEstudianteMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoEstudianteMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(nuevoEstudianteMenuItem);

        listaEstudiantesMenuItem.setText("Lista estudiantes");
        listaEstudiantesMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listaEstudiantesMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(listaEstudiantesMenuItem);

        jMenuBar1.add(jMenu1);

        jMenu4.setText("Libros");

        nuevoLibroMenuItem.setText("nuevo Libro");
        nuevoLibroMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoLibroMenuItemActionPerformed(evt);
            }
        });
        jMenu4.add(nuevoLibroMenuItem);

        listaLibrosMenuItem.setText("Lista libros");
        listaLibrosMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listaLibrosMenuItemActionPerformed(evt);
            }
        });
        jMenu4.add(listaLibrosMenuItem);

        jMenuBar1.add(jMenu4);

        prestamoMenu.setText("Prestamo");

        prestarLibroMenuItem.setText("Prestar");
        prestarLibroMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prestarLibroMenuItemActionPerformed(evt);
            }
        });
        prestamoMenu.add(prestarLibroMenuItem);

        devolverLibroMenuItem.setText("Devolver");
        devolverLibroMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                devolverLibroMenuItemActionPerformed(evt);
            }
        });
        prestamoMenu.add(devolverLibroMenuItem);

        listadoprestamosMenuItem.setText("Listado Prestamos");
        listadoprestamosMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listadoprestamosMenuItemActionPerformed(evt);
            }
        });
        prestamoMenu.add(listadoprestamosMenuItem);

        jMenuBar1.add(prestamoMenu);

        librosDevolverHoyMenu.setText("Reportes");

        librosPrestadosMenuItem.setText("Libros en prestamo");
        librosDevolverHoyMenu.add(librosPrestadosMenuItem);

        jMenuItem4.setText("Libros Prestados (Cantidad)");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        librosDevolverHoyMenu.add(jMenuItem4);

        jMenuBar1.add(librosDevolverHoyMenu);

        menuArchivoMenu.setText(" Archivo ");
        menuArchivoMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuArchivoMenuActionPerformed(evt);
            }
        });

        cargarArchivoMenuItem.setText("Cargar Archivo");
        cargarArchivoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarArchivoMenuItemActionPerformed(evt);
            }
        });
        menuArchivoMenu.add(cargarArchivoMenuItem);

        jMenuBar1.add(menuArchivoMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(DesktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 916, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(DesktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void devolverLibroMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_devolverLibroMenuItemActionPerformed
        this.regresarLibro.setVisible(true);
    }//GEN-LAST:event_devolverLibroMenuItemActionPerformed

    private void nuevoEstudianteMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoEstudianteMenuItemActionPerformed
        this.nuevoEstudianteFrame.setVisible(true);
    }//GEN-LAST:event_nuevoEstudianteMenuItemActionPerformed

    private void listaEstudiantesMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listaEstudiantesMenuItemActionPerformed
        this.listaEstudiantesFrame.setVisible(true);
    }//GEN-LAST:event_listaEstudiantesMenuItemActionPerformed

    private void nuevoLibroMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoLibroMenuItemActionPerformed
        this.nuevoLibroFrame.setVisible(true);
    }//GEN-LAST:event_nuevoLibroMenuItemActionPerformed

    private void listaLibrosMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listaLibrosMenuItemActionPerformed
        this.listadoLibrosFrame.setVisible(true);
    }//GEN-LAST:event_listaLibrosMenuItemActionPerformed

    private void prestarLibroMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prestarLibroMenuItemActionPerformed
        this.prestarLibro.setVisible(true);
    }//GEN-LAST:event_prestarLibroMenuItemActionPerformed

    private void listadoprestamosMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listadoprestamosMenuItemActionPerformed
        this.prestamoGeneral.setVisible(true);
    }//GEN-LAST:event_listadoprestamosMenuItemActionPerformed

    private void menuArchivoMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuArchivoMenuActionPerformed
        
    }//GEN-LAST:event_menuArchivoMenuActionPerformed

    private void cargarArchivoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarArchivoMenuItemActionPerformed
        this.importarArchivo.setVisible(true);
    }//GEN-LAST:event_cargarArchivoMenuItemActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        this.librosEnPrestamo.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane DesktopPane;
    private javax.swing.JMenuItem cargarArchivoMenuItem;
    private javax.swing.JMenuItem devolverLibroMenuItem;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenu librosDevolverHoyMenu;
    private javax.swing.JMenuItem librosPrestadosMenuItem;
    private javax.swing.JMenuItem listaEstudiantesMenuItem;
    private javax.swing.JMenuItem listaLibrosMenuItem;
    private javax.swing.JMenuItem listadoprestamosMenuItem;
    private javax.swing.JMenu menuArchivoMenu;
    private javax.swing.JMenuItem nuevoEstudianteMenuItem;
    private javax.swing.JMenuItem nuevoLibroMenuItem;
    private javax.swing.JMenu prestamoMenu;
    private javax.swing.JMenuItem prestarLibroMenuItem;
    // End of variables declaration//GEN-END:variables
}
