package pe.edu.usil.service.impuesto;

public class IGV18Strategy implements ImpuestoStrategy {
    private static final double RATE = 0.18;

    @Override
    public double calcular(double subtotal) {
        return redondear(subtotal * RATE);
    }

    private double redondear(double v) {
        return Math.round(v * 100.0) / 100.0;
    }
}
