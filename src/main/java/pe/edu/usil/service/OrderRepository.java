package pe.edu.usil.service;

public class OrderRepository {
    public void registrar(String cliente, String producto, int cantidad,
                          double subtotal, double igv, double total) {
        System.out.printf("Pedido registrado: %s - %s x%d | Subtotal %.2f | IGV %.2f | Total %.2f%n",
                cliente, producto, cantidad, subtotal, igv, total);
    }
}
