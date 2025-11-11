package pe.edu.usil.service;

public class ComprobanteService {
    public String generarComprobante(String cliente, String producto, int cantidad,
                                     double subtotal, double impuesto, double total, String nroFactura) {
        return "\n===== COMPROBANTE =====\n" +
                "Cliente: " + cliente + "\n" +
                "Producto: " + producto + "\n" +
                "Cantidad: " + cantidad + "\n" +
                String.format("Subtotal: %.2f\n", subtotal) +
                String.format("Impuesto: %.2f\n", impuesto) +
                String.format("Total: %.2f\n", total) +
                "Factura: " + nroFactura + "\n" +
                "=======================\n";
    }
}
