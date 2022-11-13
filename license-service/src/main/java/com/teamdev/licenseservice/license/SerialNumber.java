package com.teamdev.licenseservice.license;

import java.security.SecureRandom;
import java.util.Random;

public final class SerialNumber {

    static final private String CHARACTER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    static final private Random rand = new SecureRandom();

    private SerialNumber() {}

    static private char randomChar() {
        return CHARACTER.charAt(rand.nextInt(CHARACTER.length()));
    }

    static public String getSerialNumber() {
        return getSerialNumber(16, 4, '-');
    }

    static public String getSerialNumber(int length, int spacing, char spacerChar) {
        StringBuilder sb = new StringBuilder();

        for (int i=0, spacer = 0; i<length; i++, spacer++) {
            if (spacer == spacing) {
                sb.append(spacerChar);
                spacer = 0;
            }
            sb.append(randomChar());
        }
        return sb.toString();
    }
}
