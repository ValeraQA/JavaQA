package com.javaqa.enums;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Getter
public enum Products {

    BACKPACK("Sauce Labs Backpack", 29.99),
    BIKE_LIGHT("Sauce Labs Bike Light", 9.99),
    BOLT_T_SHIRT("Sauce Labs Bolt T-Shirt", 15.99),
    FLEECE_JACKET("Sauce Labs Fleece Jacket", 49.99),
    ONESIE("Sauce Labs Onesie", 7.99),
    TEST_T_SHIRT("Test.allTheThings() T-Shirt (Red)", 15.99);

    final String productName;
    final Double productPrice;

    Products(String productName, Double productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public static List<String> getInitialProductsList() {
        return new ArrayList<>(Stream.of(values()).map(Products::getProductName).toList());
    }

    public static List<String> getProductsListFromZtoA() {
        List<String> productList = getProductsListFromAtoZ();
        Collections.reverse(productList);
        return productList;
    }

    public static List<String> getProductsListFromAtoZ() {
        List<String> productList = getInitialProductsList();
        Collections.sort(productList);
        return productList;
    }

    public static List<String> getProductsListFromLowToHighPrice() {
        Comparator<Products> compareByPrice = Comparator.comparing(Products::getProductPrice);
        Comparator<Products> compareByName = Comparator.comparing(Products::getProductName);
        Comparator<Products> priceAndName = compareByPrice.thenComparing(compareByName);
        return Stream.of(values()).sorted(priceAndName).map(Products::getProductName).toList();
    }

    public static List<String> getProductsListFromHighToLowPrice() {
        Comparator<Products> compareByPrice = Comparator.comparing(Products::getProductPrice).reversed();
        Comparator<Products> compareByName = Comparator.comparing(Products::getProductName);
        Comparator<Products> priceAndName = compareByPrice.thenComparing(compareByName);
        return Stream.of(values()).sorted(priceAndName).map(Products::getProductName).toList();
    }
}
