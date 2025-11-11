package pe.edu.usil.facturacion;

public interface FacturaService {
    String generarFactura(String cliente, String producto, int cantidad, double subtotal, double impuesto, double total);
}
