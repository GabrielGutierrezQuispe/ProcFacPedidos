package pe.edu.usil.observer;

import pe.edu.usil.model.Pedido;

public interface PedidoSubject {
    void suscribir(PedidoObserver o);
    void desuscribir(PedidoObserver o);
    void notificarObservadores(Pedido pedido);
}