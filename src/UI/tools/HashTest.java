package UI.tools;

import UI.tools.encryption.AES_Encryption;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Objects;


public class HashTest {

    public static void main(String[] args) {
        //System.out.println(HexConver.byte2HexStr(DigestUtils.sha("all work and no play makes jack a dull boy."),20));



        //AES测试
        String content = "all work and no play makes jack a dull boy.";
        String password = "Holly Shit";
        System.out.println("加密之前：" + content);
        // 加密
        byte[] encrypt = AES_Encryption.encrypt(content, password);

        String hexStrResult = HexConver.parseByte2HexStr(Objects.requireNonNull(encrypt));
        System.out.println("Hex密文："  + hexStrResult);

        // 解密
        byte[] decrypt = AES_Encryption.decrypt(encrypt, password);
        System.out.println("解密后的内容：" + new String(Objects.requireNonNull(decrypt)));
        System.out.println(HexConver.byte2HexStr(AES_Encryption.encrypt("all work and no play makes jack a dull boy.","Holly Shit"),20));
    }
}
