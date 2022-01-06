package com.fonepay.loginauthentication.constants;

public enum MetaTableConstants {
    AES_ENCRYPTION_TYPE("AES_ENCRYPTION_TYPE"),
    BREAKING_NEWS_YOUTUBE_URL("BREAKING_NEWS_YOUTUBE_URL");

    private final String name;

    MetaTableConstants(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
