package com.example.ytpillai.cmsc_355_proj

import android.security.keystore.KeyProperties
import android.util.Log
import java.io.File
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.SecureRandom
import java.security.KeyStore

open class SecurityUtils {

    private var key1: ByteArray

    private var key2: ByteArray = ByteArray(0)

    companion object {
        @JvmStatic
        var BLOCKS = 128
        @JvmStatic
        var CIPHER = "AES"

        fun generateKey(): ByteArray {

            return ByteArray(0)

        }

        fun pad(seed: ByteArray): ByteArray {
            return ByteArray(0)
        }
    }

    init {
        this.key1 = generateKey()
    }

    fun encryptMessage(plainText: ByteArray): ByteArray {
        return plainText
    }

    fun decryptMessage(cipherText: ByteArray): ByteArray {
        return cipherText
    }

    /**
     * Gets the encryption keys if they exist, otherwise generates and writes them to the file
     * TODO: AES encrypt files with the password hash
     */
    fun getEncryptionKeys(keyDir: String, passwordHash: String = "0"){
        if(checkIfKeyPairExists(keyDir)){
            val privateKey = File(keyDir + R.string.private_key_filename).readText()
            val publicKey = File(keyDir + R.string.public_key_filename).readText()

        }
            
    }
    /**
     * Generates a 2048 bit RSA key pair
     */
    private fun generateKeyPair(): KeyPair{
        val gen = KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_RSA)
        gen.initialize(2048, SecureRandom())
        val keyPair = gen.genKeyPair()
        if(BuildConfig.DEBUG) {
            Log.d("Public key generated", keyPair.public.toString())
            Log.d("Private key generated", keyPair.private.toString())
        }

        return keyPair
    }

    /**
     * Checks if private/public keys exist at specified directory.
     * TODO: validate keys in case of tampering. Maybe use some sort of signing method?
     *
     * @keyDir: the directory where the keys should be located
     */
    private fun checkIfKeyPairExists(keyDir: String): Boolean{
        val privateKeyFile = File(keyDir + R.string.private_key_filename)
        if(BuildConfig.DEBUG)
            Log.d("PRIVATE_KEY_CHECK", "Checking if private key exists")
        val publicKeyFile = File(keyDir + R.string.public_key_filename)
        if(BuildConfig.DEBUG)
            Log.d("PUBLIC_KEY_CHECK", "Checking if public key exists")

        return privateKeyFile.exists() && publicKeyFile.exists()
    }

}