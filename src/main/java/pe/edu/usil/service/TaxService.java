package pe.edu.usil.service;

public class TaxService {
    private static final double IGV_RATE = 0.18;

    public double calcularIGV(double subtotal) {
        return redondear(subtotal * IGV_RATE);
    }

    private double redondear(double v) {
        return Math.round(v * 100.0) / 100.0;
    }
}
