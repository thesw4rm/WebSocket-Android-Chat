package com.example.ytpillai.cmsc_355_proj

import android.util.Log
// import java.io.File
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.SecureRandom
import java.security.KeyStore
import android.security.keystore.KeyProperties

open class SecurityUtils {

    companion object {
        @JvmStatic
        var BLOCKS = 128
        @JvmStatic
        var CIPHER = "AES"

    }

    fun encryptMessage(plainText: String, alias: String): String {


        return plainText
    }

    fun decryptMessage(cipherText: String, alias: String): String {



        return cipherText
    }

    /**
     * Gets the encryption keys if they exist, otherwise generates and writes them to the file
     * TODO: AES encrypt files with the password hash
     */
    fun getEncryptionKey(alias: String): PublicKey {
//        if (keyPairExists(keyDir)){
//            val privateKey = File(keyDir + R.string.private_key_filename).readText()
//            val publicKey = File(keyDir + R.string.public_key_filename).readText()
//
//        }


        val ks = KeyStore.getInstance("AndroidKeyStore")
        val privateKeyEntry = ks.getEntry(alias, null) as KeyStore.PrivateKeyEntry
        val publicKey = privateKeyEntry.certificate.publicKey


        return publicKey
            
    }
    /**
     * Generates a 2048 bit RSA key pair
     */
    private fun generateKeyPair(): KeyPair {
        val gen = KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_RSA, "AndroidKeyStore")
        gen.initialize(2048, SecureRandom())
        val keyPair = gen.genKeyPair()
        if (BuildConfig.DEBUG) {
            Log.d("Public key generated", keyPair.public.toString())
            Log.d("Private key generated", keyPair.private.toString())
        }

        return keyPair

    }

    /**
     * Check keystore for aliases
     * TODO: validate keys in case of tampering. Maybe use some sort of signing method?
     *
     * @alias: nickname for the key
     */
    private fun keyPairExists(alias: String): Boolean {
//        val privateKeyFile = File(keyDir + R.string.private_key_filename)
//        if (BuildConfig.DEBUG)
//            Log.d("PRIVATE_KEY_CHECK", "Checking if private key exists")
//        val publicKeyFile = File(keyDir + R.string.public_key_filename)
//        if (BuildConfig.DEBUG)
//            Log.d("PUBLIC_KEY_CHECK", "Checking if public key exists")
//
//        return privateKeyFile.exists() && publicKeyFile.exists()

        val ks = KeyStore.getInstance("AndroidKeyStore")

        return ks.containsAlias(alias)
    }

}