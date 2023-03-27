package project;

public class UtilEscolar {
    
        private String descripcion;
        private int cantidadAComprar;
        private int cantidadComprada;
    
        public UtilEscolar(String descripcion, int cantidadAComprar) {
            this.descripcion = descripcion.toUpperCase();
            this.cantidadAComprar = cantidadAComprar;
            this.cantidadComprada = 0;
        }
    
        public int obtenerCantidadPendiente() {
            return cantidadAComprar - cantidadComprada;
        }
    
        public String obtenerDescripcionYPendiente() {
            return descripcion + " - Cantidad pendiente: " + obtenerCantidadPendiente();
        }
    
        public void modificarCantidadComprada(int cantidad) {
            if (cantidad <= obtenerCantidadPendiente()) {
                cantidadComprada += cantidad;
            } else {
                System.out.println("La cantidad comprada excede la cantidad pendiente.");
            }
        }
    
        public String getDescripcion() {
            return descripcion;
        }
    }

