package UI.tools.encryption;

import UI.tools.ToolConstants;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * AES algorithm
 * @author Frankel.Y
 * Created in 15:03 2018/6/2
 */
public class AES_Encryption {

    /**
     * Use AES algorithm to encrypt byte array
     * @param content  Content to be encrypted
     * @param password Password to generate a key
     * @return Cipher text
     */
    public static byte[] encrypt(byte[] content, String password) {
        try {

            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            //  将密码作为种子生成128位密钥
            kgen.init(ToolConstants.AES_KEYSIZE, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();

            //  返回基本编码格式的密钥，如果此密钥不支持编码，会返回null
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");

            //  创建并初始化密码器
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            return cipher.doFinal(content);

        } catch (NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * AES加密字符串
     * @param content   需要被加密的字符串
     * @param password  加密需要的密码
     * @return          密文
     */
    public static byte[] encrypt(String content, String password) {

        try {
            byte[] byteContent = content.getBytes("utf-8");

            return encrypt(byteContent,password);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * AES decrypt
     * @param content       Cipher text
     * @param password      Password to generate a key
     * @return              Clear text
     */
    public static byte[] decrypt(byte[] content, String password) {
        try {

            //  将口令转化位密钥
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(ToolConstants.AES_KEYSIZE, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");

            //  创建并初始化密码器
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化为解密模式的密码器
            return cipher.doFinal(content); // 明文

        } catch (NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
