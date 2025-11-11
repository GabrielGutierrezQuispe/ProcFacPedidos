package pe.edu.usil.repository;

import pe.edu.usil.model.Pedido;
import java.util.List;

public interface PedidoRepository {
    void guardar(Pedido p);

    List<Pedido> listar();

    List<Pedido> buscarPorCliente(String nombre);
}
