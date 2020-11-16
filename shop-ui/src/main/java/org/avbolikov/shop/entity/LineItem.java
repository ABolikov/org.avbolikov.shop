package org.avbolikov.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.avbolikov.shop.representation.ProductRepr;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class LineItem implements Serializable {

    private Integer productId;

    private ProductRepr productRepr;

    private Integer qty;

    public LineItem(ProductRepr productRepr) {
        this.productId = productRepr.getId();
        this.productRepr = productRepr;
    }

    public LineItem() {
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public ProductRepr getProductRepr() {
        return productRepr;
    }

    public void setProductRepr(ProductRepr productRepr) {
        this.productRepr = productRepr;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    @JsonIgnore
    public BigDecimal getTotal() {
        return productRepr.getCost().multiply(new BigDecimal(qty));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LineItem lineItem = (LineItem) o;

        return Objects.equals(productId, lineItem.productId);
    }

    @Override
    public int hashCode() {
        return productId != null ? productId.hashCode() : 0;
    }
}
