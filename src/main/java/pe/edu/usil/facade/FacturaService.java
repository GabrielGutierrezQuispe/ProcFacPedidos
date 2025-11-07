package pe.edu.usil.facade;

public interface FacturaService {

    String generarFactura(String cliente, String producto, int cantidad, double subtotal, double igv, double total);
}
