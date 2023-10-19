package com.ms.util;

import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class Util {

    private Util(){}

    @SneakyThrows
    public static String getUUID(String d1) {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(d1.getBytes(StandardCharsets.UTF_8));
        UUID customUUID = UUID.nameUUIDFromBytes(hash);

        return customUUID.toString();

    }
}
