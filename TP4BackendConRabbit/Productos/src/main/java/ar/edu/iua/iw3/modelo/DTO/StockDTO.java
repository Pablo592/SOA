package ar.edu.iua.iw3.modelo.DTO;

public class StockDTO {


    public StockDTO(boolean stock, double precio){
        this.stock = stock;
        this.precio = precio;
    }


    private  boolean stock;

    private double precio;


    public boolean isStock() {
        return stock;
    }

    public void setStock(boolean stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
