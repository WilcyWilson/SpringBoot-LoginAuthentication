package com.fonepay.loginauthentication.constants;

public enum MetaTableConstants {
    PASSWORD_TOTAL_COUNT("PASSWORD_TOTAL_COUNT"),
    TOTAL_SPECIAL_CHARACTERS("TOTAL_SPECIAL_CHARACTERS"),
    TOTAL_CAPITAL_LETTERS("TOTAL_CAPITAL_LETTERS"),
    TOTAL_SMALL_LETTERS("TOTAL_SMALL_LETTERS"),
    TOTAL_NUMERIC_VALUES("TOTAL_NUMERIC_VALUES");

    private final String name;

    MetaTableConstants(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
