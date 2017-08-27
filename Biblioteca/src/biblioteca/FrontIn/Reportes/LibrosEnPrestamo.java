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
public class LibrosEnPrestamo extends javax.swing.JInternalFrame {

    private ManejadorBiblioteca manejadorBiblioteca;
private List<RegistroPrestamo> registroPrestamo;
private ObservableList<RegistroPrestamo> registroPrestamoObservable;

    public LibrosEnPrestamo(ManejadorBiblioteca manejadorBiblioteca) {
        this.manejadorBiblioteca = manejadorBiblioteca;
        registroPrestamo = new ArrayList<>();
        registroPrestamoObservable = ObservableCollections.observableList(registroPrestamo);
        
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        cargarButton = new javax.swing.JButton();
        regresarButton = new javax.swing.JButton();

        setTitle("Libros en Prestamo");

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${registroPrestamoObservable}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jTable1);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${libro.codigoLibro}"));
        columnBinding.setColumnName("CodigoLibro");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${libro.cantidadLibro}"));
        columnBinding.setColumnName("Libros Exixtentes");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${fechaPrestamo}"));
        columnBinding.setColumnName("Fecha Prestamo");
        columnBinding.setColumnClass(java.time.LocalDate.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane1.setViewportView(jTable1);

        cargarButton.setText("Cargar libros en Prestamo");
        cargarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarButtonActionPerformed(evt);
            }
        });

        regresarButton.setText("Regresar");
        regresarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regresarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cargarButton)
                        .addGap(225, 225, 225)
                        .addComponent(regresarButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(regresarButton, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cargarButton, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cargarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarButtonActionPerformed
        actualizarlistado(manejadorBiblioteca.listadoLibrosConRegistroAbierto());
    }//GEN-LAST:event_cargarButtonActionPerformed

    private void regresarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regresarButtonActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_regresarButtonActionPerformed

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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton regresarButton;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
