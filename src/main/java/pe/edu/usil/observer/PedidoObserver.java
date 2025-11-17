package pe.edu.usil.observer;

import pe.edu.usil.model.Pedido;

public interface PedidoObserver {
    void notificar(Pedido pedido);
}