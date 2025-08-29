package structural.decoratorDesignPattern;

public class Main {

    public static void main(String[] args) {
        //Using Paypal

        PaymentProcessor paypal= new PaypalAdapter(new PaypalGateway());
        ShoppingCart cart1= new ShoppingCart(paypal);
        cart1.checkout(500);

        //using Stripe
        PaymentProcessor stripe = new StripeAdapter(new StripeGateway());
        ShoppingCart cart2= new ShoppingCart(stripe);
        cart2.checkout(1200);
    }
}

// Target interface
interface PaymentProcessor {
    void pay(int amount);
}

// Legacy paypal API
class PaypalGateway {
    public void makePayment(int amount) {
        System.out.println("Payment of Rs." + amount + " processed via paypal.");
    }
}


class StripeGateway {
    public void makePayment(int amountInPaise) {
        System.out.println("Payment of Rs." + amountInPaise / 100 + " processed via stripe.");
    }
}

class PaypalAdapter implements PaymentProcessor {

    private final PaypalGateway paypalGateway;

    PaypalAdapter(PaypalGateway paypalGateway) {
        this.paypalGateway = paypalGateway;
    }

    @Override
    public void pay(int amount) {
        paypalGateway.makePayment(amount);
    }
}

class StripeAdapter implements PaymentProcessor {
    private final StripeGateway stripeGateway;

    StripeAdapter(StripeGateway stripeGateway) {
        this.stripeGateway = stripeGateway;
    }

    @Override
    public void pay(int amount) {
        stripeGateway.makePayment(amount);
    }
}


class ShoppingCart{
    private final PaymentProcessor paymentProcessor;

    public ShoppingCart(PaymentProcessor paymentProcessor){
        this.paymentProcessor=paymentProcessor;
    }

    public void checkout(int amount){
        paymentProcessor.pay(amount);
    }
}