package org.eric.utils;

public class UUID {
    public static String getRandomId() {
        java.util.UUID uuid = java.util.UUID.randomUUID();
        String randomUUID = uuid.toString().replace("-", "");
        String hash = randomUUID.substring(0, 7);

        return hash;
    }
}
