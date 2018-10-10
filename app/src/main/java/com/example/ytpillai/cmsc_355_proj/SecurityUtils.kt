package com.example.ytpillai.cmsc_355_proj

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;

open class SecurityUtils {

    private var key1: ByteArray
    get() {
       return key1
    }

    set(value) {
        key1 = value
    }

    private var key2: ByteArray
    get() {
        return key2
    }

    set(value) {
        key2 = value
    }

    companion object {
        @JvmStatic var BLOCKS = 128
        @JvmStatic var CIPHER = "AES"

        fun pad(seed: ByteArray): ByteArray {
            return ByteArray(0)
        }
    }

    fun encrypt(plainText: ByteArray): ByteArray {
        return plainText
    }

    fun decrypt(cipherText: ByteArray): ByteArray {
        return cipherText
    }

    fun generateKey(): ByteArray {

        return ByteArray(0)

    }

}