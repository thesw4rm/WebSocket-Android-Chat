package com.example.ytpillai.cmsc_355_proj

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;

class SecurityUtils {

    companion object {
        @JvmStatic var BLOCKS = 128
        @JvmStatic var CIPHER = "AES"

        fun pad(seed: ByteArray): ByteArray {
            return ByteArray(0)
        }
    }

    fun encrypt(plainText: ByteArray): ByteArray {
        return ByteArray(0)
    }

    fun decrypt(cipherText: ByteArray): ByteArray {
        return ByteArray(0)
    }

    fun generateKey(): ByteArray {

        return ByteArray(0)

    }

}