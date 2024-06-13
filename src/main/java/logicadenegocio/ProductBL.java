package logicadenegocio;

import accesoadatos.ProductDAL;
import entidadesdenegocio.Product;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductBL {
    public int guardar(Product product) throws SQLException {
        return ProductDAL.guardar(product);
    }

    public int modificar(Product product) throws SQLException{
        return ProductDAL.modificar(product);
    }

    public int eliminar(Product product) throws SQLException{
        return ProductDAL.eliminar(product);
    }

    public ArrayList<Product> obtenerTodos() throws SQLException{
        return ProductDAL.obtenerTodos();
    }

    public ArrayList<Product> obtenerDatosFiltrados(Product product) throws SQLException{
        return ProductDAL.obtenerDatosFiltrados(product);
    }
}
