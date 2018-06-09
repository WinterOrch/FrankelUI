package UI.tools.watermarking;

import UI.tools.image.Conver;
import UI.tools.image.ImageWall;
import UI.tools.insert.MatrixEncoding;
import org.apache.commons.codec.digest.DigestUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
    private static final int PICTURE_SIZE_ERR = 21;
    private static final int PROP_GET_ERROR = 24;
    private static final int HASH_ERROR = 25;
    private static final int FILE_OUTPUT_ERROR = 26;

    /**
     * For outside use
     * @param carrierImage          待保护图片
     * @param watermarkImage        水印图片
     * @param password              加密口令
     * @param savePath              图片保存地址
     * created in 12:01 2018/6/6
     */
    public int doFinal( BufferedImage carrierImage, BufferedImage watermarkImage, String password, File savePath ) {
        int hashType, encrptionType;

        switch (Objects.requireNonNull(WMProperties.getProperty("HASH.EMBED"))) {
            case "SHA1": hashType = HASH_TYPE_SHA1;         break;
            case "MD5": hashType = HASH_TYPE_MD5;           break;
            default: return PROP_GET_ERROR;
        }

        switch (Objects.requireNonNull(WMProperties.getProperty("ENCRYP.EMBED"))) {
            case "AES": encrptionType = ENCRYP_TYPE_AES;        break;
            case "RSA": encrptionType = ENCRYP_TYPE_RSA;        break;
            default: return PROP_GET_ERROR;
        }

        return embed(carrierImage, watermarkImage, password, hashType, encrptionType, savePath);
    }


    /**
     * Wong算法主体
     * created in 22:44 2018/5/29
     */
    private int embed(BufferedImage carrierImage, BufferedImage watermarkImage, String password, int hashType,
                      int encryptionType, File savePath){

        int status;

        byte[] pictureSummary = PictureHash.operate(carrierImage);

        BufferedImage watermark = Conver.compress(watermarkImage);
        watermark = Conver.binaryImage(watermark);
        int[][] binaryWatermark = Conver.binary2Matrix(watermark);

        new ImageWall(carrierImage,binaryWatermark);

        if(hashType == HASH_TYPE_SHA1) {
            status = ImageWall.insertHash(DigestUtils.sha(pictureSummary));
        }else if(hashType == HASH_TYPE_MD5) {
            status = ImageWall.insertHash(DigestUtils.md5(pictureSummary));
        }else {
            return PROP_GET_ERROR;
        }

        if(status == ImageWall.INSERT_HASH_ERR) {
            return HASH_ERROR;
        }else if(status == ImageWall.INSERT_HASH_SUCCESS) {
            status = ImageWall.INSERT_HASH_SUCCESS;
        }

        if(encryptionType == ENCRYP_TYPE_AES) {
            ImageWall.encrypt(password, ImageWall.ENCRYP_TYPE_AES);
        }else if(encryptionType == ENCRYP_TYPE_RSA) {
            ImageWall.encrypt(password, ImageWall.ENCRYP_TYPE_RSA);
        }

        binaryWatermark = ImageWall.matrixOutput();

        BufferedImage output = MatrixEncoding.embed(carrierImage,binaryWatermark,binaryWatermark);//TODO About the difference between low and high

        try {
            ImageIO.write((Objects.requireNonNull(output)), "bmp", savePath);
        } catch (IOException e) {
            e.printStackTrace();
            status = FILE_OUTPUT_ERROR;
            return status;
        }

        status = EMBED_SUCESS_OPTION;
        return status;
    }

}
