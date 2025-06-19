package utez.edu.mx.u3_04_sqm.enums;

public enum TamanoAlmacen {
    G("Grande"),
    M("Mediano"),
    P("Pequeño");

    private final String descripcion;

    TamanoAlmacen(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
