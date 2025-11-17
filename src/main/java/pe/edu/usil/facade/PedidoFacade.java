package pe.edu.usil.facade;

import pe.edu.usil.facturacion.FacturaService;
import pe.edu.usil.model.Pedido;
import pe.edu.usil.observer.PedidoSubject;
import pe.edu.usil.repository.PedidoRepository;
import pe.edu.usil.service.ComprobanteService;
import pe.edu.usil.service.StockService;
import pe.edu.usil.service.impuesto.ImpuestoStrategy;

public class PedidoFacade {
    private final StockService stock;
    private final PedidoRepository repo;
    private final ComprobanteService comp;
    private final FacturaService fact;
    private ImpuestoStrategy estrategiaImpuesto;
    private final PedidoSubject subject; // <<--- NUEVO

    public PedidoFacade(StockService stock,
                        PedidoRepository repo,
                        ComprobanteService comp,
                        FacturaService fact,
                        ImpuestoStrategy estrategiaImpuesto,
                        PedidoSubject subject) {          // <<--- NUEVO PARÁMETRO
        this.stock = stock;
        this.repo = repo;
        this.comp = comp;
        this.fact = fact;
        this.estrategiaImpuesto = estrategiaImpuesto;
        this.subject = subject;
    }

    public void setEstrategiaImpuesto(ImpuestoStrategy estrategia) {
        this.estrategiaImpuesto = estrategia;
    }

    public String procesarPedido(String cliente, String producto, int cantidad, double precioUnit) {
        if (cantidad <= 0) throw new IllegalArgumentException("La cantidad debe ser positiva");
        if (!stock.hayStock(producto, cantidad)) throw new IllegalStateException("No hay stock suficiente");

        double subtotal = redondear(precioUnit * cantidad);
        double impuesto = estrategiaImpuesto.calcular(subtotal);
        double total = redondear(subtotal + impuesto);

        String nroFactura = fact.generarFactura(cliente, producto, cantidad, subtotal, impuesto, total);

        Pedido pedido = new Pedido(cliente, producto, cantidad, precioUnit, subtotal, impuesto, total, nroFactura);
        repo.guardar(pedido);

        stock.descontar(producto, cantidad);

        // Notificar a los observadores
        subject.notificarObservadores(pedido);

        return comp.generarComprobante(cliente, producto, cantidad, subtotal, impuesto, total, nroFactura);
    }

    private double redondear(double v) {
        return Math.round(v * 100.0) / 100.0;
    }
}
