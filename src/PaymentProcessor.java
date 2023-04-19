// PaymentGateway interface that defines the common methods for all payment gateways
interface PaymentGateway {
    void processPayment(double paymentAmount);
}

// PayPal gateway
class PayPalGateway {
    void processPayPalPayment(double paymentAmount, String userEmail) {
        System.out.println("Processing PayPal payment of $" + paymentAmount + " for user " + userEmail);
    }
}

// Stripe gateway
class StripeGateway {
    void processStripePayment(double paymentAmount, String cardholderName, String cardNumber, String expirationDate) {
        System.out.println("Processing Stripe payment of $" + paymentAmount + " with cardholder name " + cardholderName + ", card number " + cardNumber + ", and expiration date " + expirationDate);
    }
}

// Square gateway
class SquareGateway {
    void processSquarePayment(double paymentAmount, String locationId) {
        System.out.println("Processing Square payment of $" + paymentAmount + " at location " + locationId);
    }
}

// Adapter for PayPal gateway
class PayPalAdapter implements PaymentGateway {
    private PayPalGateway paypalGateway;

    PayPalAdapter(PayPalGateway paypalGateway) {
        this.paypalGateway = paypalGateway;
    }

    @Override
    public void processPayment(double paymentAmount) {
        paypalGateway.processPayPalPayment(paymentAmount, "adapter@gmail.com");
    }
}

// Adapter for Stripe gateway
class StripeAdapter implements PaymentGateway {
    private StripeGateway stripeGateway;

    StripeAdapter(StripeGateway stripeGateway) {
        this.stripeGateway = stripeGateway;
    }

    @Override
    public void processPayment(double paymentAmount) {
        stripeGateway.processStripePayment(paymentAmount, "Stuti Trivedi", "4242424242424242", "12/23");
    }
}

// Adapter for Square gateway
class SquareAdapter implements PaymentGateway {
    private SquareGateway squareGateway;

    SquareAdapter(SquareGateway squareGateway) {
        this.squareGateway = squareGateway;
    }

    @Override
    public void processPayment(double paymentAmount) {
        squareGateway.processSquarePayment(paymentAmount, "12345");
    }
}

// Driver to test the gateways
public class PaymentProcessor {
    public static void main(String[] args) {
        // Initialize the payment gateways
        PayPalGateway paypalGateway = new PayPalGateway();
        StripeGateway stripeGateway = new StripeGateway();
        SquareGateway squareGateway = new SquareGateway();

        // Process payments using the adapters
        PaymentGateway paymentGateway = new PayPalAdapter(paypalGateway);
        paymentGateway.processPayment(100); // Process PayPal payment of $100 for user user@example.com

        paymentGateway = new StripeAdapter(stripeGateway);
        paymentGateway.processPayment(200); // Process Stripe payment of $200 with cardholder name John Doe, card number 4242424242424242, and expiration date 12/23

        paymentGateway = new SquareAdapter(squareGateway);
        paymentGateway.processPayment(300); // Process Square payment of $300 at location 12345
    }
}
