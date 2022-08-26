package homework2.publication;

public enum PriceCopyColor {
    COLOR(2.00),
    GRAYSCALE(1.00);

    private final double priceColor;
    PriceCopyColor(double priceColor) {
        this.priceColor = priceColor;
    }

    private double getPriceColor (){return priceColor;}

    public double addPriceColor(double copy){
        return copy * getPriceColor();
    }
}
