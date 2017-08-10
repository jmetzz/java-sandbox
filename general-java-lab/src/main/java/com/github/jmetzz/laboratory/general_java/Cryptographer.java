package com.github.jmetzz.laboratory.general_java;


import com.github.jmetzz.laboratory.general_java.encoding.Hex;
import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.google.common.base.Preconditions.checkArgument;

public class Cryptographer {

    /*
     * convenience constants to use as encryption algorithm specifier
     */
    public static final String SHA_1 = "SHA-1";
    public static final String SHA_256 = "SHA-256";
    public static final String SHA_384 = "SHA-384";
    public static final String SHA_512 = "SHA-512";

    private Cryptographer() {
    }

    public static byte[] generateHash(String algorithm, String inputText) throws NoSuchAlgorithmException, IllegalArgumentException {
        checkArgument(algorithm != null, "*** Encryption error *** A valid encryption method must be given, but was null");
        checkArgument(inputText != null, "*** Encryption error *** Non null input text must be given, but was null");
        MessageDigest digester = MessageDigest.getInstance(algorithm);
        digester.update(inputText.getBytes());
        return digester.digest();
    }

    public static String generateHexHash(String algorithm, String inputText) throws NoSuchAlgorithmException, IllegalArgumentException {
        return Hex.encode(generateHash(algorithm, inputText));
    }
}
