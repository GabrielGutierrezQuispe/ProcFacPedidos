package pe.edu.usil.repository;

import pe.edu.usil.model.Pedido;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryPedidoRepository implements PedidoRepository {
    private final List<Pedido> pedidos = new ArrayList<>();

    @Override
    public void guardar(Pedido p) {
        pedidos.add(p);
    }

    @Override
    public List<Pedido> listar() {
        return new ArrayList<>(pedidos);
    }

    @Override
    public List<Pedido> buscarPorCliente(String nombre) {
        return pedidos.stream()
                .filter(p -> p.getCliente().equalsIgnoreCase(nombre))
                .collect(Collectors.toList());
    }
}
