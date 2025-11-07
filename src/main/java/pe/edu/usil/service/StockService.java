package pe.edu.usil.service;

import java.util.Map;

public class StockService {
    private final Map<String, Integer> inventario;

    public StockService(Map<String, Integer> inventario) {
        this.inventario = inventario;
    }

    public boolean hayStock(String producto, int cantidad) {
        return cantidad > 0 && inventario.getOrDefault(producto, 0) >= cantidad;
    }

    public void descontar(String producto, int cantidad) {
        inventario.put(producto, inventario.getOrDefault(producto, 0) - cantidad);
    }
}
