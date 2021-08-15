package utp.misiontic2022.c2.p21.reto4.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;

import utp.misiontic2022.c2.p21.reto4.controlador.ControladorRequerimientos;
import utp.misiontic2022.c2.p21.reto4.modelo.vo.Requerimiento_1;
import utp.misiontic2022.c2.p21.reto4.modelo.vo.Requerimiento_2;
import utp.misiontic2022.c2.p21.reto4.modelo.vo.Requerimiento_3;

public class VentanaRequerimientos extends JFrame {

    private JTable tabla;
            
    private ControladorRequerimientos controlador;
 

    public VentanaRequerimientos() {

        controlador = new ControladorRequerimientos();

        initUI();
        setLocationRelativeTo(null);
    }

    
    public void initUI(){

        
        //// Ventana //////

        setTitle("Interfaz Gráfica - Reto 5 Ciclo2 MISIONTIC");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700,550);

        var tbd = new JTabbedPane();
        getContentPane().add(tbd, BorderLayout.CENTER);
        
        var panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        
         
        /// Pestañas ///

        tbd.addTab("Consultas Reto 4", panel);
        
        // Etiqueta botones //
        var panelEntrada =  new JPanel();
        
        var mensaje =  new JPanel();
        mensaje.setBackground(Color.WHITE);

        JLabel puntos = new JLabel (
            "<html><br>Enunciados de las consultas:<br><br>1. Materiales de construcción importados con su respectivo precio unitario, ordenados<br>por precio de mayor a menor.<br><br>2. ¿Qué empresas constructoras se encuentran en ciudades cuyo nombre empieza<br> con la letra ’B’? Mostrar su nombre y la ciudad donde operan.<br><br>3. Productos vendidos por ‘Homecenter’ de los cuales se hayan vendido más de<br>100 unidades. Mostrar además si son importados y su precio unitario.<br><br><html>", SwingConstants.LEFT);
        panel.add(puntos);


        // Botones //

        //B1
        var btnConsulta = new JButton("Requerimiento 1");
        btnConsulta.setBackground(Color.GREEN);
        btnConsulta.addActionListener(e -> cargarTablaConsulta());
                       
        panelEntrada.add(btnConsulta);
        panel.add(panelEntrada, BorderLayout.PAGE_START);
                            
        tabla = new JTable();
        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);
        
        //B2
        var btnConsulta2 = new JButton("Requerimiento 2");
        btnConsulta2.setBackground(Color.GREEN);
        btnConsulta2.addActionListener(e -> cargarTablaConsulta2());

        panelEntrada.add(btnConsulta2);
        panel.add(panelEntrada, BorderLayout.PAGE_START);
            
        tabla = new JTable();
        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);


        //B3
        var btnConsulta3 = new JButton("Requerimiento 3");
        btnConsulta3.setBackground(Color.GREEN);
        btnConsulta3.addActionListener(e -> cargarTablaConsulta3());

        panelEntrada.add(btnConsulta3);
        panel.add(panelEntrada, BorderLayout.PAGE_START);
        
        tabla = new JTable();
        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);
        
        mensaje.add(puntos);
        panel.add(mensaje, BorderLayout.PAGE_END);	

    }

      private void cargarTablaConsulta() {

    try {
        var lista = controlador.consultarRequerimiento1(); 
                
        var tableModel = new Requerimiento1TableModel();
                
        tableModel.setData(lista);
        
        tabla.setModel(tableModel);
    }
    catch (SQLException e) {
        JOptionPane.showMessageDialog(this, e.getMessage(), getTitle(), JOptionPane.ERROR_MESSAGE);
    }
    
    }



    private void cargarTablaConsulta2() {

        try {
            var lista = controlador.consultarRequerimiento2(); 
                    
            var tableModel = new Requerimiento2TableModel();
            
    
            tableModel.setData(lista);
            
            tabla.setModel(tableModel);
            
    
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), getTitle(), JOptionPane.ERROR_MESSAGE);
        }
        
    }




    private void cargarTablaConsulta3() {

        try {
            var lista = controlador.consultarRequerimiento3(); 
                    
            var tableModel = new Requerimiento3TableModel();
            
    
            tableModel.setData(lista);
            
            tabla.setModel(tableModel);
            
    
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), getTitle(), JOptionPane.ERROR_MESSAGE);
        }
            
    }


//Mostrando datos de cada requerimiento

// Req 1.

    private class Requerimiento1TableModel extends AbstractTableModel{

        private ArrayList<Requerimiento_1> data;

        public void setData(ArrayList<Requerimiento_1> data) {
            this.data = data;
        }

        @Override
        public String getColumnName(int column) {
            switch(column){
                case 0:
                    return "Materiales importados";
                case 1:
                    return "Precio unitario";       
                    
            }
            return super.getColumnName(column);
        }


        @Override
        public int getRowCount() {
            return data.size();
        }

        @Override
        public int getColumnCount() {
            return 2;        
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            
            var registros = data.get(rowIndex);
            

            switch(columnIndex){
                case 0:
                    return registros.getNombre_material();
                case 1:
                    return registros.getPrecio();

            }


            return null;
        }

    }


/// Req 2.

    private class Requerimiento2TableModel extends AbstractTableModel{

        private ArrayList<Requerimiento_2> data;

        public void setData(ArrayList<Requerimiento_2> data) {
            this.data = data;
        }

        @Override
        public String getColumnName(int column) {
            switch(column){
                case 0:
                    return "Constructora";
                case 1:
                    return "Ciudad";       
                    
            }
            return super.getColumnName(column);
        }


        @Override
        public int getRowCount() {
            return data.size();
        }

        @Override
        public int getColumnCount() {
            return 2;        
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            var registros = data.get(rowIndex);

            switch(columnIndex){
                case 0:
                    return registros.getConstructora();
                case 1:
                    return registros.getCiudad();

            }
            return null;
        }

    }



/// Req 3.

    private class Requerimiento3TableModel extends AbstractTableModel{

        private ArrayList<Requerimiento_3> data;

        public void setData(ArrayList<Requerimiento_3> data) {
            this.data = data;
        }

        @Override
        public String getColumnName(int column) {
            switch(column){
                case 0:
                    return "Proveedor";
                case 1:
                    return "Material";
                case 2:
                    return "Importado";
                case 3:
                    return "Precio unitario";
                case 4:
                    return "Cantidad";       
                    
            }
            return super.getColumnName(column);
        }


        @Override
        public int getRowCount() {
            return data.size();
        }

        @Override
        public int getColumnCount() {
            return 5;        
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            var registros = data.get(rowIndex);

            switch(columnIndex){
                case 0:
                    return registros.getProveedor();
                case 1:
                    return registros.getNombre_material();

                case 2:
                    return registros.getImportado();

                case 3:
                    return registros.getPrecio_unidad();

                case 4:
                    return registros.getCantidad();

            }
            return null;
        }

    }

}

