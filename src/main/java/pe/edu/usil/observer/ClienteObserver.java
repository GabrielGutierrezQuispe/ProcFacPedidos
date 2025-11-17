package pe.edu.usil.observer;

import pe.edu.usil.model.Pedido;

public class ClienteObserver implements PedidoObserver {
    @Override
    public void notificar(Pedido pedido) {
        System.out.printf("[ClienteObserver] Se notificó al cliente %s por el pedido %s (total: %.2f)%n",
                pedido.getCliente(), pedido.getProducto(), pedido.getTotal());
    }
}