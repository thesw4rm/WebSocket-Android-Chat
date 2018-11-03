package com.example.ytpillai.cmsc_355_proj.security

import android.os.Build
import android.support.annotation.RequiresApi

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties

import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.interfaces.RSAPublicKey

import javax.crypto.Cipher as jCipher

import android.util.Base64
import android.util.Log

open class RSA private constructor() : Cipher {

    val keyStorage: KeyStorage = KeyStorage.instance
    val KEY_ALIAS: String = "INKO_KEY"

    private object Holder { val INSTANCE = RSA() }

    companion object {
        val instance: RSA by lazy { Holder.INSTANCE }
    }

    /**
     * Encrypt plaintext with the device keypair
     *
     * @plainText: The plaintext string to be encrypted
     * @alias: The alias of the key pair
     */
    override fun encrypt(plainText: String, alias: String): String? {

        try {

            var pubKey = keyStorage.getEncryptionKey(KEY_ALIAS) as RSAPublicKey

            if (pubKey == null) {
                val keyPair = generateKeyPair()
                pubKey = keyPair.public as RSAPublicKey
            }

            // Check for empty string
            if (plainText.isEmpty()) {
                return null
            }

            val input = jCipher.getInstance("RSA/ECB/PKCS1Padding")
            input.init(jCipher.ENCRYPT_MODE, pubKey)

            val encryptBytes = input.doFinal(plainText.toByteArray())

            return Base64.encodeToString(encryptBytes, Base64.DEFAULT)

        } catch (e: Exception) {
            Log.e("Encrypt", Log.getStackTraceString(e))
        }

        return null
    }

    /**
     * Decrypt ciphers with using public key
     *
     * @cipherText: ciphertext to be decrypted
     * @alias: alias to be used for decryption
     */
    override fun decrypt(cipherText: String, alias: String): String? {

        try {

            val privateKey = keyStorage.getDecryptionKey(alias)

            val output = jCipher.getInstance("RSA/ECB/PKCS1Padding")
            output.init(jCipher.DECRYPT_MODE, privateKey)

            val decryptedBytes = output.doFinal(Base64.decode(cipherText, Base64.DEFAULT))

            return String(decryptedBytes, 0, decryptedBytes.size, Charsets.UTF_8) // Final text

        } catch (e: Exception) {
            Log.e("Decrypt", Log.getStackTraceString(e))
        }

        return null
    }



    /**
     * Generates a 2048 bit RSA key pair and add the keypair to the keystore
     */
    @RequiresApi(Build.VERSION_CODES.M)
     fun generateKeyPair(): KeyPair {

        val gen = KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_RSA, "AndroidKeyStore")
        val parameterSpec: KeyGenParameterSpec = KeyGenParameterSpec.Builder(
                KEY_ALIAS,
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        ).run {
            KeyProperties.DIGEST_SHA256
            setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
            setRandomizedEncryptionRequired(false)
            build()

        }

        gen.initialize(parameterSpec)

        return gen.genKeyPair()

    }

}