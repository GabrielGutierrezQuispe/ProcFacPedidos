package pe.edu.usil.observer;

import pe.edu.usil.model.Pedido;

public class LogObserver implements PedidoObserver {
    @Override
    public void notificar(Pedido pedido) {
        System.out.printf("[LogObserver] Pedido registrado: cliente=%s, producto=%s, total=%.2f, factura=%s%n",
                pedido.getCliente(), pedido.getProducto(), pedido.getTotal(), pedido.getNroFactura());
    }
}
