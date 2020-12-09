package org.avbolikov.shop.service;

import org.avbolikov.shop.entity.LineItem;
import org.avbolikov.shop.representation.ProductRepr;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CartServiceTest {

    private static ProductRepr productRepr1;
    private static ProductRepr productRepr2;
    private CartService cartService;

    @BeforeAll
    static void createProductRepr() {
        productRepr1 = new ProductRepr();
        productRepr1.setId(1);
        productRepr1.setCost(new BigDecimal("99.23"));
        productRepr1.setName("Product test");
        productRepr1.setBrandName("Brand test");

        productRepr2 = new ProductRepr();
        productRepr2.setId(2);
        productRepr2.setCost(new BigDecimal("10000.23"));
        productRepr2.setName("Product test 2");
        productRepr2.setBrandName("Brand test 2");
    }

    @BeforeEach
    public void init() {
        cartService = new CartServiceImpl();
    }

    @Test
    public void testEmptyCart() {
        assertEquals(0, cartService.getLineItems().size());
        assertEquals(BigDecimal.ZERO, cartService.getSubTotal());
    }

    @Test
    public void testAddOneProduct() {
        List<Integer> pictures = new ArrayList<>();
        pictures.add(1);
        pictures.add(2);

        productRepr1.setPictureIds(pictures);

        cartService.addProductQty(productRepr1, 1);
        List<LineItem> lineItems = cartService.getLineItems();

        assertNotNull(lineItems);
        assertEquals(1, lineItems.size());

        LineItem lineItem = lineItems.get(0);
        assertEquals(1, lineItem.getQty());
        assertEquals(productRepr1.getId(), lineItem.getProductId());
        assertEquals(productRepr1.getCost(), lineItem.getTotal());

        assertNotNull(lineItem.getProductRepr());
        assertEquals(productRepr1.getId(), lineItem.getProductRepr().getId());
        assertEquals(productRepr1.getCost(), lineItem.getProductRepr().getCost());
        assertEquals(productRepr1.getName(), lineItem.getProductRepr().getName());
        assertEquals(productRepr1.getBrandName(), lineItem.getProductRepr().getBrandName());
        assertEquals(pictures.size(), lineItem.getProductRepr().getPictureIds().size());
        assertEquals(pictures, lineItem.getProductRepr().getPictureIds());
    }

    @Test
    public void testSubTotal() {
        cartService.addProductQty(productRepr1, 2);
        cartService.addProductQty(productRepr2, 1);
        List<LineItem> lineItems = cartService.getLineItems();

        assertNotNull(lineItems);
        assertEquals(2, lineItems.size());

        LineItem lineItem1 = lineItems.get(0);
        assertEquals(2, lineItem1.getQty());
        assertNotNull(lineItem1.getProductRepr());
        assertEquals(productRepr1.getId(), lineItem1.getProductId());
        assertEquals(productRepr1.getCost().multiply(BigDecimal.valueOf(2)), lineItem1.getTotal());
        assertEquals(productRepr1, lineItem1.getProductRepr());

        LineItem lineItem2 = lineItems.get(1);
        assertEquals(1, lineItem2.getQty());
        assertNotNull(lineItem2.getProductRepr());
        assertEquals(productRepr2.getCost(), lineItem2.getTotal());

        assertEquals(cartService.getSubTotal(), lineItem1.getTotal().add(lineItem2.getTotal()));
    }

    @Test
    public void testRemoveProductQty() {
        cartService.addProductQty(productRepr1, 5);
        LineItem lineItem = cartService.getLineItems().get(0);
        assertEquals(5, lineItem.getQty());
        assertEquals(productRepr1.getCost().multiply(BigDecimal.valueOf(5)), lineItem.getTotal());

        cartService.removeProductQty(productRepr1, 1);
        lineItem = cartService.getLineItems().get(0);
        assertEquals(4, lineItem.getQty());
        assertEquals(productRepr1.getCost().multiply(BigDecimal.valueOf(4)), lineItem.getTotal());

        cartService.removeProductQty(productRepr1, 3);
        lineItem = cartService.getLineItems().get(0);
        assertEquals(1, lineItem.getQty());
        assertEquals(productRepr1.getCost(), lineItem.getTotal());

        cartService.removeProductQty(productRepr1, 1);
        List<LineItem> lineItems = cartService.getLineItems();
        assertEquals(0, lineItems.size());
    }

    @Test
    public void testRemoveProduct() {
        cartService.addProductQty(productRepr1, 2);
        cartService.addProductQty(productRepr2, 1);
        List<LineItem> lineItems = cartService.getLineItems();
        assertEquals(2, lineItems.size());

        cartService.removeProduct(lineItems.get(0));
        lineItems = cartService.getLineItems();
        assertEquals(1, lineItems.size());
    }

    @Test
    public void testUpdateCart() {
        cartService.addProductQty(productRepr1, 1);
        LineItem lineItem = cartService.getLineItems().get(0);
        assertEquals(1, lineItem.getQty());
        assertEquals(productRepr1.getId(), lineItem.getProductId());
        assertEquals(productRepr1.getCost(), lineItem.getTotal());
        assertEquals(productRepr1, lineItem.getProductRepr());

        lineItem.setProductId(productRepr2.getId());
        lineItem.setProductRepr(productRepr2);
        lineItem.setQty(2);

        cartService.updateCart(lineItem);
        lineItem = cartService.getLineItems().get(0);
        assertEquals(2, lineItem.getQty());
        assertEquals(productRepr2.getId(), lineItem.getProductId());
        assertEquals(productRepr2.getCost().multiply(BigDecimal.valueOf(2)), lineItem.getTotal());
        assertEquals(productRepr2, lineItem.getProductRepr());
    }


}
