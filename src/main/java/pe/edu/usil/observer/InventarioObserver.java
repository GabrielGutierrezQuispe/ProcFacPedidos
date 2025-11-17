package pe.edu.usil.observer;

import pe.edu.usil.model.Pedido;

public class InventarioObserver implements PedidoObserver {
    @Override
    public void notificar(Pedido pedido) {
        System.out.printf("[InventarioObserver] Inventario actualizado para %s, cantidad %d%n",
                pedido.getProducto(), pedido.getCantidad());
    }
}