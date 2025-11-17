package pe.edu.usil;

import pe.edu.usil.facade.PedidoFacade;
import pe.edu.usil.facturacion.FacturaAdapter;
import pe.edu.usil.facturacion.FacturaService;
import pe.edu.usil.facturacion.LegacyBillingSystem;
import pe.edu.usil.observer.*;
import pe.edu.usil.repository.InMemoryPedidoRepository;
import pe.edu.usil.repository.PedidoRepository;
import pe.edu.usil.service.ComprobanteService;
import pe.edu.usil.service.StockService;
import pe.edu.usil.service.impuesto.ExoneradoStrategy;
import pe.edu.usil.service.impuesto.IGV18Strategy;
import pe.edu.usil.service.impuesto.ImpuestoStrategy;

import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {

        Map<String, Integer> inventario = new HashMap<>();
        inventario.put("Laptop", 10);
        inventario.put("Mouse", 100);
        inventario.put("Teclado", 50);

        StockService stock = new StockService(inventario);
        ComprobanteService comp = new ComprobanteService();
        FacturaService fact = new FacturaAdapter(new LegacyBillingSystem());
        PedidoRepository repo = new InMemoryPedidoRepository();

        PedidoNotifier subject = new PedidoNotifier();
        subject.suscribir(new ClienteObserver());
        subject.suscribir(new InventarioObserver());
        subject.suscribir(new LogObserver());

        ImpuestoStrategy igv = new IGV18Strategy();

        PedidoFacade facade = new PedidoFacade(stock, repo, comp, fact, igv, subject);

        // ========== HILOS ==========

        // Hilo 1: procesa pedidos
        Runnable procesarPedidos = () -> {
            System.out.println("[Hilo-Pedidos] Iniciando procesamiento de pedidos...");
            facade.procesarPedido("Ana", "Laptop", 2, 2500.0);
            dormir(500);
            facade.procesarPedido("Luis", "Mouse", 5, 50.0);
            dormir(500);
            facade.setEstrategiaImpuesto(new ExoneradoStrategy());
            facade.procesarPedido("Carla", "Teclado", 3, 120.0);
            System.out.println("[Hilo-Pedidos] Finalizó procesamiento de pedidos.");
        };

        // Hilo 2: revisa facturas
        Runnable revisarFacturas = () -> {
            System.out.println("[Hilo-Facturas] Iniciando revisión de facturas...");
            for (int i = 0; i < 5; i++) {
                repo.listar().forEach(p ->
                        System.out.printf("[Hilo-Facturas] Verificando factura %s de %s (total: %.2f)%n",
                                p.getNroFactura(), p.getCliente(), p.getTotal())
                );
                dormir(700);
            }
            System.out.println("[Hilo-Facturas] Finalizó revisión de facturas.");
        };

        // Hilo 3: monitoreo / notificaciones extra
        Runnable monitoreo = () -> {
            System.out.println("[Hilo-Monitoreo] Sistema de monitoreo iniciado...");
            for (int i = 0; i < 5; i++) {
                System.out.printf("[Hilo-Monitoreo] Pedidos registrados hasta ahora: %d%n",
                        repo.listar().size());
                dormir(600);
            }
            System.out.println("[Hilo-Monitoreo] Sistema de monitoreo finalizado.");
        };

        Thread t1 = new Thread(procesarPedidos, "Hilo-Pedidos");
        Thread t2 = new Thread(revisarFacturas, "Hilo-Facturas");
        Thread t3 = new Thread(monitoreo, "Hilo-Monitoreo");

        t1.start();
        t2.start();
        t3.start();
    }

    private static void dormir(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
