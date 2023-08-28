package com.javaqa.enums;

import lombok.Getter;

@Getter
public enum Sorting {

    NAME_A_Z("Name (A to Z)", "az"),
    NAME_Z_A("Name (Z to A)", "za"),
    PRICE_L_H("Price (low to high)", "lohi"),
    PRICE_H_L("Price (high to low)", "hilo");

    final String sortingType;
    final String sortingValue;

    Sorting(String sortingType, String sortingValue) {
        this.sortingType = sortingType;
        this.sortingValue = sortingValue;
    }
}
