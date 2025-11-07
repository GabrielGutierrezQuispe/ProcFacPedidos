package pe.edu.usil.facturacion;

public class LegacyBillingSystem {
    public String issueDocument(String fullName, String item, int units, double net, double vat, double grand) {

        return "LEGACY-" + System.currentTimeMillis();
    }
}
