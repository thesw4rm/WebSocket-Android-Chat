package com.example.ytpillai.cmsc_355_proj.security

import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.support.annotation.RequiresApi
import android.util.Base64
import android.util.Log
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.PublicKey
import java.security.interfaces.RSAPublicKey
import javax.crypto.Cipher as jCipher

open class RSA private constructor() : Cipher {

    private var keyAliases: HashMap<String, PublicKey> = HashMap() // Won't be a hashmap for long
    
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

            val ks = KeyStore.getInstance("AndroidKeyStore")
            ks.load(null, null)
            if (!keyPairExists())
                generateKeyPair()
            val entry = ks.getEntry(KEY_ALIAS, null) as KeyStore.PrivateKeyEntry
            val pubKey = entry.certificate.publicKey as RSAPublicKey

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
     * @aliasID: Id of the alias to be used for decryption
     */
    override fun decrypt(cipherText: String, alias: String): String? {

        try {

//                val alias = keyAliases[aliasID]

            val ks = KeyStore.getInstance("AndroidKeyStore")
            ks.load(null, null)
            if (!keyPairExists())
                generateKeyPair()
            val privateKeyEntry = ks.getEntry(alias, null) as KeyStore.PrivateKeyEntry
            val output = jCipher.getInstance("RSA/ECB/PKCS1Padding")
            output.init(jCipher.DECRYPT_MODE, privateKeyEntry.privateKey)

            val decryptedBytes = output.doFinal(Base64.decode(cipherText, Base64.DEFAULT))

            return String(decryptedBytes, 0, decryptedBytes.size, Charsets.UTF_8) // Final text

        } catch (e: Exception) {
            Log.e("Decrypt", Log.getStackTraceString(e))
        }

        return null
    }


    /**
     * Gets the local public key as a string if it exist, otherwise generates
     *
     * @alias: Alias of the key
     */
    @RequiresApi(Build.VERSION_CODES.M)
    fun getEncryptionKey(alias: String = KEY_ALIAS): String {

        return if (keyPairExists(alias)) {

            val ks = KeyStore.getInstance("AndroidKeyStore")
            ks.load(null)
            val privateKeyEntry = ks.getEntry(alias, null) as KeyStore.PrivateKeyEntry
            Log.d("HAHAHAHA", privateKeyEntry.certificate.publicKey.toString())

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

    /**
     * Check android keystore for aliases
     * TODO: validate keys in case of tampering. Maybe use some sort of signing method?
     *
     * @alias: nickname for the key
     */
    fun keyPairExists(alias: String = KEY_ALIAS): Boolean {

        val ks = KeyStore.getInstance("AndroidKeyStore")
        ks.load(null, null)
        return ks.containsAlias(alias)
    }

    /**
     * Don't worry, this won't be a hashmap for very long
     */
    fun insertKey(key: PublicKey, alias: String) {

        keyAliases[alias] = key

    }

}