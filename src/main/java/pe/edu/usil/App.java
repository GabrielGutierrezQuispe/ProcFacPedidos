package pe.edu.usil;

import pe.edu.usil.facade.PedidoFacade;
import pe.edu.usil.facturacion.FacturaAdapter;
import pe.edu.usil.facturacion.FacturaService;
import pe.edu.usil.facturacion.LegacyBillingSystem;
import pe.edu.usil.service.ComprobanteService;
import pe.edu.usil.service.OrderRepository;
import pe.edu.usil.service.StockService;
import pe.edu.usil.service.TaxService;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Map<String, Integer> inventario = new HashMap<>();
        inventario.put("Laptop", 10);
        inventario.put("Mouse", 100);

        StockService stock = new StockService(inventario);
        TaxService tax = new TaxService();
        OrderRepository repo = new OrderRepository();
        ComprobanteService comp = new ComprobanteService();
        FacturaService fact = new FacturaAdapter(new LegacyBillingSystem());

        PedidoFacade facade = new PedidoFacade(stock, tax, repo, comp, fact);

        String comprobante = facade.procesarPedido("GamaEF", "Laptop", 2, 2500.00);
        System.out.println(comprobante);
    }
}