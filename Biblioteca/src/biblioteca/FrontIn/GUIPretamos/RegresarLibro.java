package biblioteca.FrontIn.GUIPretamos;

import biblioteca.BackEnd.Biblioteca.Biblioteca;
import biblioteca.BackEnd.Excepciones.InputsVaciosException;
import biblioteca.BackEnd.Manejadorer.ManejadorBiblioteca;
import biblioteca.BackEnd.Manejadorer.ManejadorEstudiantes;
import biblioteca.BackEnd.Manejadorer.ManejadorLibros;
import biblioteca.backEnd.Estudiante.Estudiante;
import biblioteca.backEnd.Libro.Libro;
import biblioteca.backEnd.Prestamo.RegistroPrestamo;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.xml.bind.ValidationException;

/**
 *
 * @author angel
 */
public class RegresarLibro extends javax.swing.JDialog {

    private RegistroPrestamo registroPrestamo;
    ManejadorBiblioteca manejadorBiblioteca;
    ManejadorLibros manejadorLibros;
    ManejadorEstudiantes manejadorEst;

    public RegresarLibro(boolean modal, ManejadorBiblioteca manejadorBiblioteca, ManejadorLibros manejadorLibros, ManejadorEstudiantes manejadorEst) {
        this.manejadorBiblioteca = manejadorBiblioteca;
        this.manejadorLibros = manejadorLibros;
        this.manejadorEst = manejadorEst;
        initComponents();
        this.setModal(modal);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        fechaDevolucionFormattedTextField = new javax.swing.JFormattedTextField();
        devolverLibroButton = new javax.swing.JButton();
        salirButton = new javax.swing.JButton();
        limpiarButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        codigoLibroFormattedTextField = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        carnetEstFormattedTextField = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Devolver Libro a Biblioteca");

        jLabel4.setText("Fecha devolucion:");

        try {
            fechaDevolucionFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        devolverLibroButton.setText("Devolver Libro");
        devolverLibroButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                devolverLibroButtonActionPerformed(evt);
            }
        });

        salirButton.setText("Salir");
        salirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirButtonActionPerformed(evt);
            }
        });

        limpiarButton.setText("Limpiar");
        limpiarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 15)); // NOI18N
        jLabel1.setForeground(java.awt.Color.red);
        jLabel1.setText("Devolver Libro");

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

        carnetEstFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#########"))));
        carnetEstFormattedTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                carnetEstFormattedTextFieldFocusLost(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(limpiarButton))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(carnetEstFormattedTextField)
                            .addComponent(codigoLibroFormattedTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                            .addComponent(fechaDevolucionFormattedTextField)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(devolverLibroButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(salirButton)))))
                .addContainerGap(21, Short.MAX_VALUE))
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
                    .addComponent(fechaDevolucionFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(devolverLibroButton)
                    .addComponent(limpiarButton)
                    .addComponent(salirButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void devolverLibroButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_devolverLibroButtonActionPerformed
        try {
            registroPrestamo = manejadorBiblioteca.DevolucionLibro(carnetEstFormattedTextField.getText(), codigoLibroFormattedTextField.getText(), fechaDevolucionFormattedTextField.getText());
            if (registroPrestamo.getTotalpago() <= Biblioteca.LIMITE_DIAS_HABILES_PRESTAMO * Biblioteca.PRECIO_PRESTAMO_NORMAL) {
                JOptionPane.showMessageDialog(this.getParent(), "Total: Q." + registroPrestamo.getTotalpago(), "Total", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this.getParent(), "Total: Q." + registroPrestamo.getTotalpagoConMora() + " con Mora", "Total", JOptionPane.INFORMATION_MESSAGE);
            }
            limpiar();
            this.setVisible(false);
        } catch (ValidationException | InputsVaciosException ex) {
            JOptionPane.showMessageDialog(this.getParent(), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_devolverLibroButtonActionPerformed

    private void salirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirButtonActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_salirButtonActionPerformed

    private void limpiarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarButtonActionPerformed
        limpiar();
    }//GEN-LAST:event_limpiarButtonActionPerformed

    private void codigoLibroFormattedTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_codigoLibroFormattedTextFieldFocusLost
        Libro libro = manejadorLibros.buscarLibroPorCodigo(codigoLibroFormattedTextField.getText());
        if (libro == null) {
            error("No existe el Libro: " + codigoLibroFormattedTextField.getText());
            codigoLibroFormattedTextField.setText("");
        } else if (codigoLibroFormattedTextField.getText().replace(" ", "").replace("-", "").isEmpty()) {
            error("Debe colocar el codigo del libro");
        }
    }//GEN-LAST:event_codigoLibroFormattedTextFieldFocusLost

    private void carnetEstFormattedTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_carnetEstFormattedTextFieldFocusLost
        Estudiante estudiante = manejadorEst.buscarEstPorCarnet(carnetEstFormattedTextField.getText());
        if (estudiante == null) {
            error("No existe el Estudiante" + carnetEstFormattedTextField.getText());
        } else if (carnetEstFormattedTextField.getText().replace(" ", "").isEmpty()) {
            error("Debe colocarse un carnet");
        }if (estudiante.getCantidadLibroPrestados()==0) {
            devolverLibroButton.setEnabled(false);
            error("No tiene libros pendientes de entregar");
        }else{
            devolverLibroButton.setEnabled(true);
        }
    }//GEN-LAST:event_carnetEstFormattedTextFieldFocusLost
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField carnetEstFormattedTextField;
    private javax.swing.JFormattedTextField codigoLibroFormattedTextField;
    private javax.swing.JButton devolverLibroButton;
    private javax.swing.JFormattedTextField fechaDevolucionFormattedTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton limpiarButton;
    private javax.swing.JButton salirButton;
    // End of variables declaration//GEN-END:variables
private void error(String mensajeError) {
        JOptionPane.showMessageDialog(this.getParent(), mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void limpiar() {
        carnetEstFormattedTextField.setText("");
        codigoLibroFormattedTextField.setText("");
        fechaDevolucionFormattedTextField.setText("");
        devolverLibroButton.setEnabled(true);
    }
}
