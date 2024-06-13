package accesoadatos;

import entidadesdenegocio.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAL {
    // método que muestra todos los registros de la tabla
    public static ArrayList<Product> obtenerTodos() throws SQLException {
        ArrayList<Product> lista = new ArrayList<>();
        Product product;
        try{
            String sql = "SELECT Id, Nombre, Codigo, UnitPrice, UnitCost FROM Products";
            Connection conexion = ComunDB.obtenerConexion();
            PreparedStatement ps = ComunDB.crearPreparedStatement(conexion, sql);
            ResultSet rs = ComunDB.obtenerResultSet(ps);
            while (rs.next()){
                product = new Product(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getDouble(4), rs.getDouble(5));
                lista.add(product);
            }
            conexion.close();
            ps.close();
            rs.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return lista;
    }

    // método que permite guardar un nuevo registro
    public static int guardar(Product product) throws SQLException{
        int result = 0;
        try {
            String sql = "INSERT INTO Products(Nombre, Codigo, UnitPrice, UnitCost) VALUES(?, ?, ?, ?)";
            Connection conexion = ComunDB.obtenerConexion();
            PreparedStatement ps = ComunDB.crearPreparedStatement(conexion, sql);
            ps.setString(1, product.getNombre());
            ps.setString(2, product.getCodigo());
            ps.setDouble(3, product.getUnitPrice());
            ps.setDouble(4, product.getUnitCost());
            result = ps.executeUpdate();
            conexion.close();
            ps.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    // método que permite modificar un registro existente
    public static int modificar(Product product) throws SQLException{
        int result = 0;
        try {
            String sql = "UPDATE Products SET Nombre = ?, Codigo = ?, UnitPrice = ?, UnitCost = ? WHERE Id = ?";
            Connection conexion = ComunDB.obtenerConexion();
            PreparedStatement ps = ComunDB.crearPreparedStatement(conexion, sql);
            ps.setString(1, product.getNombre());
            ps.setString(2, product.getCodigo());
            ps.setDouble(3, product.getUnitCost());
            ps.setDouble(4, product.getUnitPrice());
            ps.setInt(5, product.getId());
            result = ps.executeUpdate();
            conexion.close();
            ps.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    // método que permite eliminar un registro existente
    public static int eliminar(Product product) throws SQLException{
        int result = 0;
        try {
            String sql = "DELETE FROM Products WHERE Id = ?";
            Connection conexion = ComunDB.obtenerConexion();
            PreparedStatement ps = ComunDB.crearPreparedStatement(conexion, sql);
            ps.setInt(1, product.getId());
            result = ps.executeUpdate();
            conexion.close();
            ps.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    // método para consultar mediante criterios
    public static ArrayList<Product> obtenerDatosFiltrados(Product product) throws SQLException{
        ArrayList<Product> lista = new ArrayList<>();
        Product est;
        try{
            String sql = "SELECT id, nombre, codigo, unitPrice, unitCost FROM Products WHERE id = ? or Nombre like'%" + product.getNombre() + "%' or Codigo like'%" + product.getCodigo() + "%'";
            Connection connection = ComunDB.obtenerConexion();
            PreparedStatement ps = ComunDB.crearPreparedStatement(connection, sql);
            ps.setInt(1, product.getId());
            ResultSet rs = ComunDB.obtenerResultSet(ps);
            while (rs.next()){
                est = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDouble(5));
                lista.add(est);
            }
            connection.close();
            try{
                ps.close();
                rs.close();
            }catch (Exception ignored){}
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return lista;
    }
}
