package ma.enset;

import jade.content.AgentAction;
import jade.core.AID;
import ma.enset.entities.Product;

public class Sell implements AgentAction {
    private AID buyer;
    private Product product;

    public AID getBuyer() {
        return buyer;
    }

    public void setBuyer(AID buyer) {
        this.buyer = buyer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
