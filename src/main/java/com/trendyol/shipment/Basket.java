package com.trendyol.shipment;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket {

    private List<Product> products;

    public ShipmentSize getShipmentSize() {

        if (products == null || products.isEmpty()) {
            return null;
        }

        Map<ShipmentSize, Integer> sizeCountMap = new HashMap<>();
        for (Product product : products) {
            ShipmentSize size = product.getSize();
            sizeCountMap.put(size, sizeCountMap.getOrDefault(size, 0) + 1);
        }

        for (Map.Entry<ShipmentSize, Integer> entry : sizeCountMap.entrySet()) {
            if (entry.getValue() >= 3) {
                return getNextLargerSize(entry.getKey());
            }
        }

        ShipmentSize largestSize = Collections.max(sizeCountMap.keySet());

        if (largestSize == ShipmentSize.X_LARGE) {
            return ShipmentSize.X_LARGE;
        }

        return largestSize;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    private ShipmentSize getNextLargerSize(ShipmentSize size) {
        switch (size) {
            case SMALL:
                return ShipmentSize.MEDIUM;
            case MEDIUM:
                return ShipmentSize.LARGE;
            default:
                return ShipmentSize.X_LARGE;
        }
    }
}
