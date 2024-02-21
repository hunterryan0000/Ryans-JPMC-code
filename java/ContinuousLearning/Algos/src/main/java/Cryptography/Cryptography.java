package Cryptography;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class Cryptography {
    public static class SecurePaymentProcessing {
        public static void main(String[] args) throws Exception {
            // Generate a SecretKey for AES encryption
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256); // Initialize the KeyGenerator with a key size of 256 bits
            SecretKey secretKey = keyGenerator.generateKey();

            // Create a Cipher instance for AES
            Cipher cipher = Cipher.getInstance("AES");

            // Example encryption
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encrypted = cipher.doFinal("Sensitive payment info".getBytes());
            // Encode the encrypted data as Base64 for display or transmission
            String encodedEncryptedData = Base64.getEncoder().encodeToString(encrypted);
            System.out.println("Encrypted data (Base64): " + encodedEncryptedData);

            // Example decryption
            // Decode the Base64 back to a byte array before decryption
            byte[] decodedEncryptedData = Base64.getDecoder().decode(encodedEncryptedData);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decrypted = cipher.doFinal(decodedEncryptedData);
            System.out.println("Decrypted data: " + new String(decrypted));
        }
    }


}
/**
 * The Advanced Encryption Standard (AES) is a symmetric encryption algorithm widely used across the globe to secure data. AES was established as an encryption standard by the U.S. National Institute of Standards and Technology (NIST) in 2001, after a 5-year standardization process. It replaced the older Data Encryption Standard (DES) due to DES's vulnerability to brute-force attacks.
 *
 * Key Characteristics of AES:
 * Symmetric Key Encryption: AES uses the same key for both encryption and decryption. This key must be known to both the sender and the receiver but kept secret from everyone else.
 * Block Cipher: AES encrypts data in fixed-size blocks; the standard block size is 128 bits. If the data does not fit perfectly into a block, it must be padded to match the block size.
 * Key Sizes: AES supports multiple key lengths: 128, 192, or 256 bits. The choice of key size determines the number of rounds the data goes through during encryption or decryption, with 10 rounds for 128-bit keys, 12 rounds for 192-bit keys, and 14 rounds for 256-bit keys.
 * Security: AES is considered highly secure and is used for encrypting sensitive data by governments, financial institutions, and security systems around the world. Its design has been extensively analyzed and tested for vulnerabilities.
 * Versatility: AES is suitable for encrypting data at rest (e.g., files stored on a disk) and data in transit (e.g., data being transferred over a network).
 *
 * How AES Works:
 * Key Expansion: The AES algorithm begins with a key expansion phase where the original encryption key is used to generate a series of round keys through the Rijndael key schedule. These round keys are used in each round of the encryption process.
 * Initial Round: The plaintext is added to the first round key using bitwise XOR.
 * Main Rounds: The main part of the algorithm includes several rounds of processing, including four main stages:
 * SubBytes: A non-linear substitution step where each byte is replaced with another according to a lookup table.
 * ShiftRows: A transposition step where each row of the state is shifted cyclically a certain number of steps.
 * MixColumns: A mixing operation which operates on the columns of the state, combining the four bytes in each column.
 * AddRoundKey: Each byte of the state is combined with the round key using bitwise XOR.
 * Final Round: The final round includes all the steps of the main rounds except the MixColumns stage.
 *
 * AES in Practice:
 * Modes of Operation: When using AES (or any block cipher) in practice, it's essential to select an appropriate mode of operation (e.g., CBC, GCM) depending on the requirements for data integrity and confidentiality. Each mode has its advantages and use cases.
 * Implementation: AES is implemented in various software libraries and hardware devices, providing efficient and secure encryption. It's supported by most modern programming languages and cryptographic libraries.
 * Given its balance of security, performance, and flexibility, AES is a cornerstone of modern cryptographic security protocols and applications.
 */