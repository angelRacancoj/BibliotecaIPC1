package biblioteca.FrontIn.GUIPretamos;

import biblioteca.BackEnd.Excepciones.InputsVaciosException;
import biblioteca.BackEnd.Manejadorer.ManejadorBiblioteca;
import biblioteca.BackEnd.Manejadorer.ManejadorEstudiantes;
import biblioteca.BackEnd.Manejadorer.ManejadorLibros;
import biblioteca.backEnd.Estudiante.Estudiante;
import biblioteca.backEnd.Libro.Libro;
import biblioteca.backEnd.Prestamo.RegistroPrestamo;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.xml.bind.ValidationException;

/**
 *
 * @author angel
 */
public class PrestarLibro extends javax.swing.JDialog {

    private RegistroPrestamo registroPrestamo;
//    private ManejadorPrestamos manejadorPrestamo;
    private ManejadorBiblioteca manejadorBiblioteca;
    
    private ManejadorLibros manejadorBooks;
    private ManejadorEstudiantes manejadorEst;

//    public PrestarLibro(boolean modal, ManejadorBiblioteca manejador) {
//        this.manejadorBiblioteca =manejador;
//        initComponents();
//        this.setModal(modal);
//    }
    public PrestarLibro(boolean modal, ManejadorBiblioteca manejadorBiblioteca, ManejadorEstudiantes manejadorEstudiante, ManejadorLibros manejadorLibros) {
        this.manejadorBiblioteca = manejadorBiblioteca;
//        this.manejadorPrestamo = manejadorPrestamo;
        this.manejadorBooks = manejadorLibros;
        this.manejadorEst = manejadorEstudiante;
        initComponents();
        this.setModal(modal);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        limpiarButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        codigoLibroFormattedTextField = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        carnetEstFormattedTextField = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        fechaPrestamoFormattedTextField = new javax.swing.JFormattedTextField();
        prestarLibroButton = new javax.swing.JButton();
        salirButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Regisntrar Prestamo");

        limpiarButton.setText("Limpiar");
        limpiarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 15)); // NOI18N
        jLabel1.setForeground(java.awt.Color.red);
        jLabel1.setText("Prestar Libro");

        jLabel2.setText("Codigo libro: ");

        try {
            codigoLibroFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###-AAA")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        codigoLibroFormattedTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                codigoLibroFormattedTextFieldFocusLost(evt);
            }
        });

        jLabel3.setText("Carnet Estudiante");

        try {
            carnetEstFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        carnetEstFormattedTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                carnetEstFormattedTextFieldFocusLost(evt);
            }
        });

        jLabel4.setText("Fecha prestamo:");

        try {
            fechaPrestamoFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        prestarLibroButton.setText("Prestar Libro");
        prestarLibroButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                prestarLibroButtonMouseClicked(evt);
            }
        });
        prestarLibroButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prestarLibroButtonActionPerformed(evt);
            }
        });

        salirButton.setText("Salir");
        salirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(limpiarButton)
                                .addGap(70, 70, 70)
                                .addComponent(prestarLibroButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                                .addComponent(salirButton))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(carnetEstFormattedTextField)
                                    .addComponent(codigoLibroFormattedTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                                    .addComponent(fechaPrestamoFormattedTextField)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(jLabel1)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codigoLibroFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(carnetEstFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fechaPrestamoFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prestarLibroButton)
                    .addComponent(salirButton)
                    .addComponent(limpiarButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void codigoLibroFormattedTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_codigoLibroFormattedTextFieldFocusLost
        Libro libro = manejadorBooks.buscarLibroPorCodigo(codigoLibroFormattedTextField.getText());
        if (libro == null) {
            error("No existe el Libro: " + codigoLibroFormattedTextField.getText());
            codigoLibroFormattedTextField.setText("");
        } else if (codigoLibroFormattedTextField.getText().replace(" ", "").replace("-", "").isEmpty()) {
            error("Debe colocar el codigo del libro");
        }
        if (libro.getCantidadLibro()==0) {
            prestarLibroButton.setEnabled(false);
            error("Se han agotado las copias del libro");
        }else{
            prestarLibroButton.setEnabled(true);
        }
    }//GEN-LAST:event_codigoLibroFormattedTextFieldFocusLost

    private void carnetEstFormattedTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_carnetEstFormattedTextFieldFocusLost
        Estudiante estudiante = manejadorEst.buscarEstPorCarnet(carnetEstFormattedTextField.getText());
        if (estudiante == null) {
            error("No existe el Estudiante" + carnetEstFormattedTextField.getText());
        } else if (carnetEstFormattedTextField.getText().replace(" ", "").isEmpty()) {
            error("Debe colocarse un carnet");
        }
        if(estudiante.getCantidadLibroPrestados()==Estudiante.CANTIDAD_MAXIMA_LIBROS){
            prestarLibroButton.setEnabled(false);
            error("Cantidad maxima de libros prestados alcanzado");
        }else{
            prestarLibroButton.setEnabled(true);
        }
    }//GEN-LAST:event_carnetEstFormattedTextFieldFocusLost

    private void prestarLibroButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prestarLibroButtonActionPerformed
        try {
            registroPrestamo = manejadorBiblioteca.PrestarLibro(codigoLibroFormattedTextField.getText(), fechaPrestamoFormattedTextField.getText(),carnetEstFormattedTextField.getText(), manejadorEst, manejadorBooks);
            limpiar();
            JOptionPane.showMessageDialog(this,"Prestamo Guardado Exitosamente", "Completado", JOptionPane.INFORMATION_MESSAGE);
            this.setVisible(false);
        } catch (ValidationException | InputsVaciosException ex) {
            Logger.getLogger(PrestarLibro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_prestarLibroButtonActionPerformed

    private void limpiarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarButtonActionPerformed
        limpiar();
    }//GEN-LAST:event_limpiarButtonActionPerformed

    private void salirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirButtonActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_salirButtonActionPerformed

    private void prestarLibroButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prestarLibroButtonMouseClicked
//        try {
//            this.registroPrestamo = manejadorPrestamo.prestarLibro(this.codigoLibroFormattedTextField.getText(),
//                    this.carnetEstFormattedTextField.getText(), this.fechaPrestamoFormattedTextField.getText());
////this.registroPrestamo = manejadorPrestamo.prestarLibro("123-lkj", "111111111","02/02/2002");
//            JOptionPane.showMessageDialog(this.getParent(), "Prestamo guardado exitosamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
//            this.setVisible(false);
//        } catch (InputsVaciosException | ValidationException ex) {
//            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        } catch (DateTimeParseException e) {
//            JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
//        }
    }//GEN-LAST:event_prestarLibroButtonMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField carnetEstFormattedTextField;
    private javax.swing.JFormattedTextField codigoLibroFormattedTextField;
    private javax.swing.JFormattedTextField fechaPrestamoFormattedTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton limpiarButton;
    private javax.swing.JButton prestarLibroButton;
    private javax.swing.JButton salirButton;
    // End of variables declaration//GEN-END:variables

    private void limpiar() {
        carnetEstFormattedTextField.setText("");
        codigoLibroFormattedTextField.setText("");
        fechaPrestamoFormattedTextField.setText("");
        prestarLibroButton.setEnabled(true);
    }

    private void error(String mensajeError) {
        JOptionPane.showMessageDialog(this.getParent(), mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
