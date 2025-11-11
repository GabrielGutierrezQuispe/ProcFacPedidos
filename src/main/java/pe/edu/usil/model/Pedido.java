package pe.edu.usil.model;

public class Pedido {
    private String cliente;
    private String producto;
    private int cantidad;
    private double precioUnit;
    private double subtotal;
    private double impuesto;
    private double total;
    private String nroFactura;

    public Pedido(String cliente, String producto, int cantidad, double precioUnit,
                  double subtotal, double impuesto, double total, String nroFactura) {
        this.cliente = cliente;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnit = precioUnit;
        this.subtotal = subtotal;
        this.impuesto = impuesto;
        this.total = total;
        this.nroFactura = nroFactura;
    }

    public String getCliente() {
        return cliente;
    }

    public String getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnit() {
        return precioUnit;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public double getTotal() {
        return total;
    }

    public String getNroFactura() {
        return nroFactura;
    }
}
