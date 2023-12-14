package com.wallet.tdwallet.connection;

import org.springframework.beans.factory.annotation.Value;

public class Credentials {
    @Value("${spring.datasource.url")
    public static String url;
    @Value("${spring.datasource.user}")
    public static String user;
    @Value("${sring.datasource.password")
    public static String password;
}
