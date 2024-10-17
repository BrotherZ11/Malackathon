public class EmbalseDTO {
    private String nombre;
    private double coordenadaX;
    private double coordenadaY;

    // Getters y setters
    public void setNombre(String newNombre){
        nombre = newNombre;
    }

    public String getNombre(){
        return nombre;
    }

    public void setCoordenadaX(double x){
        coordenadaX = x;
    }

    public void setCoordenadaY(double y){
        coordenadaY = y;
    }

    public double getCoordenadaX(){
        return coordenadaX;
    }

    public double getCoordenadaY(){
        return coordenadaY;
    }
    
    // Constructor
    public EmbalseDTO(String nombre, double coordenadaX, double coordenadaY) {
        this.nombre = nombre;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
    }
}
