package pe.edu.usil;

import pe.edu.usil.facade.PedidoFacade;
import pe.edu.usil.facturacion.FacturaAdapter;
import pe.edu.usil.facturacion.FacturaService;
import pe.edu.usil.facturacion.LegacyBillingSystem;
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

        StockService stock = new StockService(inventario);
        ComprobanteService comp = new ComprobanteService();
        FacturaService fact = new FacturaAdapter(new LegacyBillingSystem());
        PedidoRepository repo = new InMemoryPedidoRepository();

        ImpuestoStrategy igv = new IGV18Strategy();
        PedidoFacade facade = new PedidoFacade(stock, repo, comp, fact, igv);

        String comprobante1 = facade.procesarPedido("GamaEF", "Laptop", 2, 2500.00);
        System.out.println(comprobante1);

        facade.setEstrategiaImpuesto(new ExoneradoStrategy());
        String comprobante2 = facade.procesarPedido("GamaEF", "Mouse", 5, 50.00);
        System.out.println(comprobante2);

        System.out.println("Pedidos guardados: " + repo.listar().size());
    }
}
