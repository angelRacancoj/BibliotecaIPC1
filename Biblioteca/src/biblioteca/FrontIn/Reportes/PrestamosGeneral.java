package biblioteca.FrontIn.Reportes;

import biblioteca.BackEnd.Manejadorer.ManejadorBiblioteca;
import biblioteca.backEnd.Prestamo.RegistroPrestamo;
import java.util.ArrayList;
import java.util.List;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;

/**
 *
 * @author angel
 */
public class PrestamosGeneral extends javax.swing.JInternalFrame {

private ManejadorBiblioteca manejadorBiblioteca;
private List<RegistroPrestamo> registroPrestamo;
private ObservableList<RegistroPrestamo> registroPrestamoObservable;

    public PrestamosGeneral(ManejadorBiblioteca manejadorBiblioteca) {
        this.manejadorBiblioteca = manejadorBiblioteca;
        registroPrestamo = new ArrayList<>();
        registroPrestamoObservable = ObservableCollections.observableList(registroPrestamo);
        initComponents();
        fechaFinalFormattedTextField.setEnabled(false);
        fechaInicioFormattedTextField.setEnabled(false);
        carrerasComboBox.setEnabled(false);
        totalTextField.setEnabled(false);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        regresarButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        prestamoBox = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        prestamosTable = new javax.swing.JTable();
        cargarButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        fechaInicioFormattedTextField = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        fechaFinalFormattedTextField = new javax.swing.JFormattedTextField();
        carrerasComboBox = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        totalTextField = new javax.swing.JTextField();

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        setTitle("Reporte de prestamos General");

        regresarButton.setText("Regresar");
        regresarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regresarButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Elegir filtro");

        prestamoBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "General por Carrera", "Libros en Prestamo", "Libros Devueltos", "Libros por devolver hoy", "Pretamos que deben pagar Mora", "Recaudacion con Invervalo de tiempo", "Carreras mas frecuentes", "Estudiantes morosos", "Estudiantes frecuentes" }));
        prestamoBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prestamoBoxActionPerformed(evt);
            }
        });

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${registroPrestamoObservable}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, prestamosTable);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${carnetEstudiante}"));
        columnBinding.setColumnName("Carnet Estudiante");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${codigoLibro}"));
        columnBinding.setColumnName("Codigo Libro");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${diasPrestamo}"));
        columnBinding.setColumnName("Dias Prestamo");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${estadoPrestamo}"));
        columnBinding.setColumnName("Estado Prestamo");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${fechaPrestamo}"));
        columnBinding.setColumnName("Fecha Prestamo");
        columnBinding.setColumnClass(java.time.LocalDate.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${totalpago}"));
        columnBinding.setColumnName("Totalpago");
        columnBinding.setColumnClass(Double.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${totalpagoConMora}"));
        columnBinding.setColumnName("Totalpago Con Mora");
        columnBinding.setColumnClass(Double.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane1.setViewportView(prestamosTable);

        cargarButton.setText("Cargar");
        cargarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Fecha Inicio");

        try {
            fechaInicioFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel3.setText("Fecha Final");

        try {
            fechaFinalFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        carrerasComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ingenieria", "Medicina", "Derecho", "Arquitectura", "Administracion" }));

        jLabel4.setText("Carrera");

        jLabel5.setText("Total:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(prestamoBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cargarButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(regresarButton)
                .addGap(36, 36, 36))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaInicioFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaFinalFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(carrerasComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 734, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(prestamoBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cargarButton)
                    .addComponent(regresarButton)
                    .addComponent(jLabel5)
                    .addComponent(totalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fechaInicioFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(fechaFinalFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(carrerasComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cargarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarButtonActionPerformed
        actualizarlistado(manejadorBiblioteca.buscarRegistro(prestamoBox.getSelectedIndex(),carrerasComboBox.getSelectedIndex(), fechaInicioFormattedTextField.getText(), fechaFinalFormattedTextField.getText()));
        if (prestamoBox.getSelectedIndex()==6) {
         totalTextField.setText(String.valueOf(manejadorBiblioteca.prestamosCerradosConTotal(fechaInicioFormattedTextField.getText(), fechaFinalFormattedTextField.getText())));   
        }
    }//GEN-LAST:event_cargarButtonActionPerformed

    private void regresarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regresarButtonActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_regresarButtonActionPerformed

    private void prestamoBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prestamoBoxActionPerformed
        if (prestamoBox.getSelectedIndex()==1) {
            carrerasComboBox.setEnabled(true);
        }else{
            carrerasComboBox.setEnabled(false);
        }
        if (prestamoBox.getSelectedIndex()==6 || prestamoBox.getSelectedIndex()==7) {
            fechaFinalFormattedTextField.setEnabled(true);
            fechaInicioFormattedTextField.setEnabled(true);
        }else{
            fechaFinalFormattedTextField.setEnabled(false);
            fechaInicioFormattedTextField.setEnabled(false);
        }
    }//GEN-LAST:event_prestamoBoxActionPerformed
private void actualizarlistado(List<RegistroPrestamo> registroPrestamo){
    registroPrestamoObservable.clear();
    registroPrestamoObservable.addAll(registroPrestamo);
}

    public ObservableList<RegistroPrestamo> getRegistroPrestamoObservable() {
        return registroPrestamoObservable;
    }

    public void setRegistroPrestamoObservable(ObservableList<RegistroPrestamo> registroPrestamoObservable) {
        this.registroPrestamoObservable = registroPrestamoObservable;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cargarButton;
    private javax.swing.JComboBox<String> carrerasComboBox;
    private javax.swing.JFormattedTextField fechaFinalFormattedTextField;
    private javax.swing.JFormattedTextField fechaInicioFormattedTextField;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> prestamoBox;
    private javax.swing.JTable prestamosTable;
    private javax.swing.JButton regresarButton;
    private javax.swing.JTextField totalTextField;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
