import accesoadatos.ProductDAL;
import entidadesdenegocio.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class ProductDALTest {
    @Test
    public void guardarTest() throws SQLException {
        Product product = new Product();
        product.setNombre("MackBookPro");
        product.setCodigo("HE43J8");
        product.setUnitPrice(400.00);
        product.setUnitCost(200.00);

        int esperado = 1;
        int actual = ProductDAL.guardar(product);
        Assertions.assertEquals(esperado, actual);
    }

    @Test
    public void obtenerTodosTest() throws SQLException{
        int esperado = 1;
        int actual = ProductDAL.obtenerTodos().size();
        Assertions.assertEquals(esperado, actual);
    }

    @Test
    public void modificarTest() throws SQLException{
        Product product = new Product();
        product.setId(1);
        product.setNombre("MackBookPro");
        product.setCodigo("HE43J8");
        product.setUnitPrice(800.00);
        product.setUnitCost(400.00);

        int esperado = 1;
        int actual = ProductDAL.modificar(product);
        Assertions.assertEquals(esperado, actual);
    }

    @Test
    public void eliminarTest() throws SQLException{
        Product product = new Product();
        product.setId(1);

        int esperado = 1;
        int actual = ProductDAL.eliminar(product);
        Assertions.assertEquals(esperado, actual);
    }

    @Test
    public void obtenerDatosFiltradosTest() throws SQLException{
        Product product = new Product();
        product.setNombre("MackBookPro");
        product.setId(0);
        product.setCodigo("");

        int actual = ProductDAL.obtenerDatosFiltrados(product).size();
        Assertions.assertNotEquals(0, actual);
    }
}
