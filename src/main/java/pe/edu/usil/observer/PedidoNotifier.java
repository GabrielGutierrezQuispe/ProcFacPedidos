package pe.edu.usil.observer;

import pe.edu.usil.model.Pedido;

import java.util.ArrayList;
import java.util.List;

public class PedidoNotifier implements PedidoSubject {

    private final List<PedidoObserver> observers = new ArrayList<>();

    @Override
    public void suscribir(PedidoObserver o) {
        observers.add(o);
    }

    @Override
    public void desuscribir(PedidoObserver o) {
        observers.remove(o);
    }

    @Override
    public void notificarObservadores(Pedido pedido) {
        for (PedidoObserver o : observers) {
            o.notificar(pedido);
        }
    }
}
