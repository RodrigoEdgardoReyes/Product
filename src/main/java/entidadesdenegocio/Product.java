package entidadesdenegocio;

public class Product {
    private int id;
    private String nombre;
    private String codigo;
    private double unitPrice;
    private double unitCost;

    public Product(){}

    public Product(int id, String nombre, String codigo, double unitPrice, double unitCost) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.unitPrice = unitPrice;
        this.unitCost = unitCost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(Double unitCost) {
        this.unitCost = unitCost;
    }
}
