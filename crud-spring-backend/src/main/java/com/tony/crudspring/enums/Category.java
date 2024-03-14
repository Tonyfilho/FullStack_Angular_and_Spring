package com.tony.crudspring.enums;

public enum Category {
    FRONT_END("front-end"), BACK_END("back-end");

    private final String value;

    Category(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value; // required for @ValueOfEnum
    }
}
