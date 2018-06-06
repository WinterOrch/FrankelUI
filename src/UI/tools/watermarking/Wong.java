package UI.tools.watermarking;

import java.awt.image.BufferedImage;
import java.util.Objects;

public class Wong {

    // 哈希方法常量
    private static final int HASH_TYPE_SHA1 = 1;
    private static final int HASH_TYPE_MD5 = 2;

    // 加密方法常量
    private static final int ENCRYP_TYPE_AES = 3;
    private static final int ENCRYP_TYPE_RSA = 4;

    // 返回参数常量
    private static final int EMBED_SUCESS_OPTION = 20;
    private static final int PROP_FAIL_OPTION = 24;

    /**
     * For outside use
     * @param carrierImage          待保护图片
     * @param watermarkImage        水印图片
     * @param password              加密口令
     * created in 12:01 2018/6/6
     */
    public int doFinal( BufferedImage carrierImage, BufferedImage watermarkImage, String password ) {
        int hashType, encrptionType;

        switch (Objects.requireNonNull(WMProperties.getProperty("HASH.EMBED"))) {
            case "SHA1": hashType = HASH_TYPE_SHA1;         break;
            case "MD5": hashType = HASH_TYPE_MD5;           break;
            default: return PROP_FAIL_OPTION;
        }

        switch (Objects.requireNonNull(WMProperties.getProperty("ENCRYP.EMBED"))) {
            case "AES": encrptionType = ENCRYP_TYPE_AES;        break;
            case "RSA": encrptionType = ENCRYP_TYPE_RSA;        break;
            default: return PROP_FAIL_OPTION;
        }

        return embed(carrierImage, watermarkImage, password, hashType, encrptionType);
    }


    /**
     * Wong算法主体
     * created in 22:44 2018/5/29
     */
    private int embed(BufferedImage carrierImage, BufferedImage watermarkImage, String password, int hashType, int encryptionType){
        return EMBED_SUCESS_OPTION;
    }

}
