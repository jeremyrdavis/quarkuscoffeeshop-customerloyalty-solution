package io.quarkuscoffeeshop.customerloyalty;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class LoyalCustomerPurchase extends PanacheEntity {

    String orderJson;

    String customerLoyaltyId;

    public LoyalCustomerPurchase() {
    }

    public LoyalCustomerPurchase(String orderJson, String customerLoyaltyId) {
        this.orderJson = orderJson;
        this.customerLoyaltyId = customerLoyaltyId;
    }

    @Override
    public String toString() {
        return "LoyalCustomerPurchase{" +
                "orderJson='" + orderJson + '\'' +
                ", customerLoyaltyId='" + customerLoyaltyId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoyalCustomerPurchase that = (LoyalCustomerPurchase) o;

        if (orderJson != null ? !orderJson.equals(that.orderJson) : that.orderJson != null) return false;
        return customerLoyaltyId != null ? customerLoyaltyId.equals(that.customerLoyaltyId) : that.customerLoyaltyId == null;
    }

    @Override
    public int hashCode() {
        int result = orderJson != null ? orderJson.hashCode() : 0;
        result = 31 * result + (customerLoyaltyId != null ? customerLoyaltyId.hashCode() : 0);
        return result;
    }

    public String getOrderJson() {
        return orderJson;
    }

    public void setOrderJson(String orderJson) {
        this.orderJson = orderJson;
    }

    public String getCustomerLoyaltyId() {
        return customerLoyaltyId;
    }

    public void setCustomerLoyaltyId(String customerLoyaltyId) {
        this.customerLoyaltyId = customerLoyaltyId;
    }
}
