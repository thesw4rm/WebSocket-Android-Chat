package com.example.ytpillai.cmsc_355_proj

import android.os.Build
import android.util.Log
// import java.io.File
import android.security.keystore.KeyProperties
import android.security.keystore.KeyGenParameterSpec
import android.support.annotation.RequiresApi
import java.security.KeyStore
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.SecureRandom




open class SecurityUtils {

    companion object {
        const val KEY_ALIAS = "INKO_Key"
        var keyAliases  = ArrayList<String>()
    }


    /**
     * Encrypt plaintext with the device keypair
     *
     * @plainText: The plaintext string to be encrypted
     * @alias: The alias of the key pair
     */
    fun encryptMessage(plainText: String, alias: String = KEY_ALIAS): String {



        return plainText
    }

    /**
     * Decrypt ciphers with using public key
     *
     * @cipherText: ciphertext to be decrypted
     * @aliasID: Id of the alias to be used for decryption
     */
    fun decryptMessage(cipherText: String, aliasID: Int): String {

        val alias = keyAliases[aliasID]

        return cipherText
    }

    @RequiresApi(Build.VERSION_CODES.M)
     /**
     * Gets the local public key as a string if it exist, otherwise generates
     *
     * @alias: Alias of the key
     */
    fun getEncryptionKey(alias: String = KEY_ALIAS): String {
//        if (keyPairExists(keyDir)){
//            val privateKey = File(keyDir + R.string.private_key_filename).readText()
//            val publicKey = File(keyDir + R.string.public_key_filename).readText()
//
//        }

        return if (keyPairExists(alias)) {

            val ks = KeyStore.getInstance("AndroidKeyStore")
            val privateKeyEntry = ks.getEntry(alias, null) as KeyStore.PrivateKeyEntry

            privateKeyEntry.certificate.publicKey.toString() // public key

        } else {

            val kp = generateKeyPair()


            kp.public.toString()

        }
    }

    /**
     * Generates a 2048 bit RSA key pair
     */
    @RequiresApi(Build.VERSION_CODES.M)
    private fun generateKeyPair(): KeyPair {

        val gen = KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_RSA, "AndroidKeyStore")
        val parameterSpec: KeyGenParameterSpec = KeyGenParameterSpec.Builder(
                KEY_ALIAS,
                KeyProperties.PURPOSE_SIGN).run {
            KeyProperties.DIGEST_SHA256
            build()
        }

        gen.initialize(parameterSpec)
        val keyPair = gen.genKeyPair()
        if (BuildConfig.DEBUG) {
            Log.d("Public key generated", keyPair.public.toString())
            Log.d("Private key generated", keyPair.private.toString())
        }

        return keyPair

    }

    /**
     * Check android keystore for aliases
     * TODO: validate keys in case of tampering. Maybe use some sort of signing method?
     *
     * @alias: nickname for the key
     */
    private fun keyPairExists(alias: String = KEY_ALIAS): Boolean {
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