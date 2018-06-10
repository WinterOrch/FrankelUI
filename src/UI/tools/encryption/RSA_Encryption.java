package UI.tools.encryption;

import UI.tools.ToolConstants;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.security.*;
import java.util.Objects;


public class RSA_Encryption {

    public static int TYPE_PUBLIC_KEY = 0;
    public static int TYPE_PRIVATE_KEY = 1;

    /**
     * 加密，以字节串输入
     * @param content       明文
     * @param password      口令
     * created in 2:36 2018/6/5
     */
    public static byte[] encrypt( byte[] content, String password ) {
        //为RSA创建KeyPairGenerator对象并利用随机数初始化
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Objects.requireNonNull(keyPairGenerator).initialize( ToolConstants.RSA_KEYSIZE, new SecureRandom(password.getBytes()) );

        // 生成并取得密匙对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        Key publicKey = keyPair.getPublic();

        return cipherEncrypt(content,publicKey);
    }

    /**
     * 加密，以字节串输入
     * @param content           明文
     * @param publicKeyFile     公钥文件
     * created in 2:36 2018/6/5
     */
    private static byte[] encrypt( byte[] content, File publicKeyFile ) {
        Key publicKey = getKey(publicKeyFile);

        return cipherEncrypt(content,publicKey);
    }

    private static byte[] cipherEncrypt( byte[] content, Key publicKey ) {
        // 得到Cipher对象来实现对源数据的RSA加密
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            //执行加密操作
            return cipher.doFinal(content);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
                | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;

    }


    /**
     * 解密，以字节串输入
     * created in 2:36 2018/6/5
     */
    public static byte[] decrypt( byte[] content, File privateKeyFile ) {

        Key privateKey = getKey(privateKeyFile);

        // 得到Cipher对象对已用公钥加密的数据进行RSA解密
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            // 执行解密操作
            return cipher.doFinal(content);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
                | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 生成密钥对并写入文件
     * @param password      口令
     * @param keyType       选择写入公钥还是私钥
     * created in 2:38 2018/6/5
     */
    public static void saveFile( String password, int keyType ) {
        //弹出文件选择框
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = null;
        String extension = null;

        if ( keyType == TYPE_PUBLIC_KEY ) {
            extension = ToolConstants.PUBLIC_KEY_FILE;
            //公钥后缀名过滤器
            filter = new FileNameExtensionFilter(
                    "公钥文件(*." + extension + ")", extension);//TODO 国际化
        } else if ( keyType == TYPE_PRIVATE_KEY ) {
            extension = ToolConstants.PRIVATE_KEY_FILE;
            //私钥后缀名过滤器
            filter = new FileNameExtensionFilter(
                    "私钥文件(*." + extension + ")", extension);//TODO 国际化
        }

        chooser.setFileFilter(filter);

        //下面的方法将阻塞，直到【用户按下保存按钮且“文件名”文本框不为空】或【用户按下取消按钮】
        int option = chooser.showSaveDialog(null);
        if(option==JFileChooser.APPROVE_OPTION){    //假如用户选择了保存
            File file = chooser.getSelectedFile();
            String fname = chooser.getName(file);   //从文件名输入框中获取文件名

            //假如用户填写的文件名不带我们制定的后缀名，那么我们给它添上后缀
            if(!fname.contains( "." + extension )){
                file = new File(chooser.getCurrentDirectory(),fname+"."+extension);
            }

            generateKeyPair( password, file, keyType );
        }
    }

    private static void generateKeyPair( String password, File key, int keyType ) {
        //为RSA创建KeyPairGenerator对象并利用随机数初始化
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Objects.requireNonNull(keyPairGenerator).initialize( ToolConstants.RSA_KEYSIZE, new SecureRandom(password.getBytes()) );

        // 生成并取得密匙对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        Key publicKey = keyPair.getPublic();
        Key privateKey = keyPair.getPrivate();

        // 用对象流将生成的密钥写入文件
        ObjectOutputStream oos = null;
            try {
                oos = new ObjectOutputStream(new FileOutputStream(key));

                if ( keyType == TYPE_PUBLIC_KEY ) {
                    //写入公钥
                    Objects.requireNonNull(oos).writeObject(publicKey);
                } else if ( keyType == TYPE_PRIVATE_KEY ) {
                    //写入私钥
                    Objects.requireNonNull(oos).writeObject(privateKey);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //关闭输出流
                try {
                    Objects.requireNonNull(oos).close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }

    /**
     * 从文件中获取密钥
     * created in 2:51 2018/6/5
     */
    private static Key getKey( File pKey ){
        Key key = null;
        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(new FileInputStream(pKey));
            key = (Key) ois.readObject();
            ois.close();

        } catch (IOException | ClassNotFoundException e ) {
            e.printStackTrace();
        }

        return key;
    }
}
