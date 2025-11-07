package pe.edu.usil.facade;

import pe.edu.usil.facturacion.FacturaService;
import pe.edu.usil.service.ComprobanteService;
import pe.edu.usil.service.OrderRepository;
import pe.edu.usil.service.StockService;
import pe.edu.usil.service.TaxService;

public class PedidoFacade {
    private final StockService stock;
    private final TaxService tax;
    private final OrderRepository repo;
    private final ComprobanteService comp;
    private final FacturaService fact;

    public PedidoFacade(StockService stock, TaxService tax, OrderRepository repo,
                        ComprobanteService comp, FacturaService fact) {
        this.stock = stock;
        this.tax = tax;
        this.repo = repo;
        this.comp = comp;
        this.fact = fact;
    }

    public String procesarPedido(String cliente, String producto, int cantidad, double precioUnit) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser positiva");
        }
        if (!stock.hayStock(producto, cantidad)) {
            throw new IllegalStateException("No hay stock suficiente");
        }

        double subtotal = redondear(precioUnit * cantidad);
        double igv = tax.calcularIGV(subtotal);
        double total = redondear(subtotal + igv);

        repo.registrar(cliente, producto, cantidad, subtotal, igv, total);
        stock.descontar(producto, cantidad);

        String nroFactura = fact.generarFactura(cliente, producto, cantidad, subtotal, igv, total);
        return comp.generarComprobante(cliente, producto, cantidad, subtotal, igv, total, nroFactura);
    }

    private double redondear(double v) {
        return Math.round(v * 100.0) / 100.0;
    }
}