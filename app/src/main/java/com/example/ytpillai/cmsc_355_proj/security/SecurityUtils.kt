package com.example.ytpillai.cmsc_355_proj.security


import com.example.ytpillai.cmsc_355_proj.BuildConfig

import android.os.Build
import android.support.annotation.RequiresApi

import android.util.Log
import android.util.Base64

import android.security.keystore.KeyProperties
import android.security.keystore.KeyGenParameterSpec

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

import java.security.KeyStore
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.interfaces.RSAPrivateKey

import javax.crypto.Cipher
import javax.crypto.CipherInputStream
import javax.crypto.CipherOutputStream
import java.security.interfaces.RSAPublicKey


open class SecurityUtils {

    companion object {
        const val KEY_ALIAS = "INKO_Key"
//        var keyAliases  = ArrayList<String>()


        /**
         * Encrypt plaintext with the device keypair
         *
         * @plainText: The plaintext string to be encrypted
         * @alias: The alias of the key pair
         */
        fun encryptMessage(plainText: String, alias: String = KEY_ALIAS): String? {

            try {

                if (!keyPairExists())
                    generateKeyPair()
                generateKeyPair()
                val ks = KeyStore.getInstance("AndroidKeyStore")
                ks.load(null, null)
                val entry = ks.getEntry(KEY_ALIAS, null) as KeyStore.PrivateKeyEntry
                val pubKey = entry.certificate.publicKey as RSAPublicKey

                // Check for empty string
                if (plainText.isEmpty()) {
                    return null
                }

                val input = Cipher.getInstance("RSA/ECB/PKCS1Padding")
                input.init(Cipher.ENCRYPT_MODE, pubKey)

//                val outputStream = ByteArrayOutputStream()
//                val cipherOutputStream = CipherOutputStream(
//                        outputStream, input)
//                cipherOutputStream.write(plainText.toByteArray(charset("UTF-8")))
//                cipherOutputStream.close()
//
//                val vals = outputStream.toByteArray()

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
        fun decryptMessage(cipherText: String, alias: String = KEY_ALIAS): String? {

            if (cipherText.isEmpty()) {
                return null
            }

            try {

//                val alias = keyAliases[aliasID]

                val ks = KeyStore.getInstance("AndroidKeyStore")
                ks.load(null, null)
                if (!keyPairExists())
                    generateKeyPair()
                val privateKeyEntry = ks.getEntry(alias, null) as KeyStore.PrivateKeyEntry
                val output = Cipher.getInstance("RSA/ECB/PKCS1Padding")
                output.init(Cipher.DECRYPT_MODE, privateKeyEntry.privateKey)

//                val cipherInputStream = CipherInputStream(
//                        ByteArrayInputStream(Base64.decode(cipherText, Base64.DEFAULT)),
//                        output)
//
//                val values = ArrayList<Byte>()
//                var nextByte: Int = cipherInputStream.read()
//                while (nextByte != -1) {
//                    values.add(nextByte.toByte())
//                    nextByte = cipherInputStream.read()
//                }
//
//                val bytes = ByteArray(values.size)
//                for (i in 0..bytes.size) {
//                    bytes[i] = values[i]
//                }

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
                    KeyProperties.PURPOSE_ENCRYPT).run {
                KeyProperties.DIGEST_SHA256
                setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                setRandomizedEncryptionRequired(false)
                build()

            }


            gen.initialize(parameterSpec)
            val keyPair = gen.genKeyPair()

            return keyPair

        }

        /**
         * Check android keystore for aliases
         * TODO: validate keys in case of tampering. Maybe use some sort of signing method?
         *
         * @alias: nickname for the key
         */
        private fun keyPairExists(alias: String = KEY_ALIAS): Boolean {

            val ks = KeyStore.getInstance("AndroidKeyStore")
            ks.load(null, null)
            return ks.containsAlias(alias)
        }
    }

}