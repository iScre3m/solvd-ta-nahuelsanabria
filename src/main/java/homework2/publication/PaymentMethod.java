package homework2.publication;

public enum PaymentMethod {
    CASH(0.90),
    CREDIT(1.20),
    DEBIT(1.00);

    private final double PricePaymentMethod;


    PaymentMethod(double pricePaymentMethod) {
        this.PricePaymentMethod = pricePaymentMethod;
    }

    public double getPricePaymentMethod() {
        return PricePaymentMethod;
    }

    public double addPricePaymentMethod(double bill){
        return bill * getPricePaymentMethod();
    }
}
