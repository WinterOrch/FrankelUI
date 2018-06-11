package UI.tools.encryption;

import UI.tools.HexConver;
import UI.tools.ToolConstants;
import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Objects;

/**
 * DES algorithm
 * @author Frankel.Y
 * Created in 15:03 2018/6/2
 */
public class DES_Encryption {

    /**
     * Use DES algorithm to encrypt byte array
     * @param content  Content to be encrypted
     * @param password Password to generate a key
     * @return Cipher text
     */
    public static byte[] encrypt(byte[] content, String password) {
        try {

            KeyGenerator kgen = KeyGenerator.getInstance("DES");
            //  将密码作为种子生成56位密钥
            kgen.init(ToolConstants.DES_KEYSIZE, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();

            //  返回基本编码格式的密钥，如果此密钥不支持编码，会返回null
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "DES");

            //  创建并初始化密码器
            Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            return cipher.doFinal(content);

        } catch (NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * DES加密字符串
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
     * DES decrypt
     * @param content       Cipher text
     * @param password      Password to generate a key
     * @return              Clear text
     */
    public static byte[] decrypt(byte[] content, String password) {
        try {

            //  将口令转化位密钥
            KeyGenerator kgen = KeyGenerator.getInstance("DES");
            kgen.init(ToolConstants.DES_KEYSIZE, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "DES");

            //  创建并初始化密码器
            Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化为解密模式的密码器
            return cipher.doFinal(content); // 明文

        } catch (NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            byte[] c = "狗屎W0".getBytes("utf-8");
            int l = c.length;
            System.out.println(HexConver.byte2HexStr(c,l));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        byte[] m = encrypt("狗屎W0","holly");
        System.out.println(HexConver.byte2HexStr(m,m.length));

        byte[] o = decrypt(m,"holly");
        System.out.println(HexConver.byte2HexStr(o,o.length));
    }
}
