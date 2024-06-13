package interfazgrafica;

import logicadenegocio.ProductBL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class Product extends JFrame {
    private JPanel jpPrincipal;
    private JTextField txtId;
    private JTextField txtCodigo;
    private JTextField txtUnitPrice;
    private JButton btnNuevo;
    private JButton btnGuardar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnCancelar;
    private JButton btnSalir;
    private JRadioButton rdbId;
    private JRadioButton rdbNombre;
    private JRadioButton rdbCodigo;
    private JTextField txtUnitCost;
    private JTextField txtNombre;
    private JTextField txtCriterio;
    private JButton btnBuscar;
    private JTable tbProduct;
    private ButtonGroup criterioBusqueda;


    // instancias propias (creadas por nosotros)
    ArrayList<entidadesdenegocio.Product> listProducts;
    entidadesdenegocio.Product product;
    ProductBL productoBL = new ProductBL();

    // declaración del método main, permite que clase sea ejecutable
    public static void main(String[] args) throws SQLException {
        new Product();
    }

    // Método constructor que llama a los métodos que inicializan la ventana y dan estado inicial al formulario.
    // También están las sobrescrituras para dar la funcionalidad a los botones.
    public Product() throws SQLException{
        inicializar();
        actualizarForm();

        // funcionalidad del botón Nuevo
        btnNuevo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                txtCodigo.setEnabled(true);
                txtNombre.setEnabled(true);
                txtUnitPrice.setEnabled(true);
                txtUnitCost.setEnabled(true);
                txtCodigo.grabFocus();
                btnGuardar.setEnabled(true);
                btnNuevo.setEnabled(false);
                btnCancelar.setEnabled(true);
            }
        });

        // funcionalidad del botón Guardar
        btnGuardar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                product = new entidadesdenegocio.Product();
                product.setCodigo(txtCodigo.getText());
                product.setNombre(txtNombre.getText());
                product.setUnitPrice(Double.parseDouble(txtUnitPrice.getText()));
                product.setUnitCost(Double.parseDouble(txtUnitCost.getText()));
                try{
                    productoBL.guardar(product);
                    JOptionPane.showMessageDialog(null, "Se guardó correctamente");
                    actualizarForm();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // funcionalidad del botón Salir
        btnSalir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(0);
            }
        });

        // funcionalidad del botón Cancelar
        btnCancelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    actualizarForm();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // funcionalidad del clic sobre la Tabla
        tbProduct.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int fila = tbProduct.getSelectedRow();
                txtId.setText(tbProduct.getValueAt(fila, 0).toString());
                txtCodigo.setText(tbProduct.getValueAt(fila, 1).toString());
                txtNombre.setText(tbProduct.getValueAt(fila, 2).toString());
                txtUnitPrice.setText(tbProduct.getValueAt(fila, 3).toString());
                txtUnitCost.setText(tbProduct.getValueAt(fila, 4).toString());

                txtCodigo.setEnabled(true);
                txtNombre.setEnabled(true);
                txtUnitPrice.setEnabled(true);
                txtUnitCost.setEnabled(true);
                txtCodigo.grabFocus();

                btnNuevo.setEnabled(false);
                btnModificar.setEnabled(true);
                btnEliminar.setEnabled(true);
                btnCancelar.setEnabled(true);
            }
        });

        // funcionalidad del botón Modificar
        btnModificar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                product = new entidadesdenegocio.Product();
                product.setId(Integer.parseInt(txtId.getText()));
                product.setCodigo(txtCodigo.getText());
                product.setNombre(txtNombre.getText());
                product.setUnitPrice(Double.parseDouble(txtUnitPrice.getText()));
                product.setUnitCost(Double.parseDouble(txtUnitCost.getText()));
                try {
                    productoBL.modificar(product);
                    JOptionPane.showMessageDialog(null, "Se modificó con éxito");
                    actualizarForm();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // funcionalidad del botón Eliminar
        btnEliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                product = new entidadesdenegocio.Product();
                product.setId(Integer.parseInt(txtId.getText()));
                try{
                    productoBL.eliminar(product);
                    JOptionPane.showMessageDialog(null, "Se eliminó correctamente");
                    actualizarForm();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // funcionalidad del botón Buscar
        btnBuscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(txtCriterio.getText().equals("") || (!rdbId.isSelected() &&
                        !rdbNombre.isSelected() && !rdbCodigo.isSelected()) ){
                    JOptionPane.showMessageDialog(null,
                            "Seleccione un criterio de búsqueda o escriba el valor a buscar");
                }

                product = new entidadesdenegocio.Product();

                if(rdbId.isSelected()){
                    product.setId(Integer.parseInt(txtCriterio.getText()));
                    try{
                        llenarTabla(productoBL.obtenerDatosFiltrados(product));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                if(rdbNombre.isSelected()){
                    product.setNombre(txtCriterio.getText());
                    try{
                        llenarTabla(productoBL.obtenerDatosFiltrados(product));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                if(rdbCodigo.isSelected()){
                    product.setCodigo(txtCriterio.getText());
                    try{
                        llenarTabla(productoBL.obtenerDatosFiltrados(product));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }

    // método para inicializar la ventana
    void inicializar(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 700);
        setTitle("Control de Productos");
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        criterioBusqueda = new ButtonGroup();
        criterioBusqueda.add(rdbId);
        criterioBusqueda.add(rdbNombre);
        criterioBusqueda.add(rdbCodigo);

        setContentPane(jpPrincipal);
        setVisible(true);
    }

    // método para llenar la tabla con los datos obtenidos de la base de datos
    void llenarTabla(ArrayList<entidadesdenegocio.Product> productos){
        Object[] obj = new Object[5];
        listProducts = new ArrayList<>();
        String[] encabezado = {"ID", "CÓDIGO", "NOMBRE", "PRECIO UNITARIO", "COSTO UNITARIO"};
        DefaultTableModel tm = new DefaultTableModel(null, encabezado);
        listProducts.addAll(productos);
        for(entidadesdenegocio.Product item : listProducts){
            obj[0] = item.getId();
            obj[1] = item.getCodigo();
            obj[2] = item.getNombre();
            obj[3] = item.getUnitPrice();
            obj[4] = item.getUnitCost();

            tm.addRow(obj);
        }
        tbProduct.setModel(tm);
    }

    // método para dar estado inicial al formulario
    void actualizarForm() throws SQLException {
        txtId.setText("");
        txtCodigo.setText("");
        txtNombre.setText("");
        txtUnitPrice.setText("");
        txtUnitCost.setText("");

        txtId.setEnabled(false);
        txtCodigo.setEnabled(false);
        txtNombre.setEnabled(false);
        txtUnitPrice.setEnabled(false);
        txtUnitCost.setEnabled(false);

        btnNuevo.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);

        criterioBusqueda.clearSelection();
        txtCriterio.setText("");

        llenarTabla(productoBL.obtenerTodos());
    }
}
