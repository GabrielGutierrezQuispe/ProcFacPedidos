package pe.edu.usil.facturacion;

public class FacturaAdapter implements FacturaService {
    private final LegacyBillingSystem legacy;

    public FacturaAdapter(LegacyBillingSystem legacy) {
        this.legacy = legacy;
    }

    @Override
    public String generarFactura(String cliente, String producto, int cantidad,
                                 double subtotal, double impuesto, double total) {
        return legacy.issueDocument(cliente, producto, cantidad, subtotal, impuesto, total);
    }
}
