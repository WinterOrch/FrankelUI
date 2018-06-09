package UI.tools.image;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * ImageWall class is used to embed a single watermark binary image into the whole picture.
 * @author Yu.M
 * Created in 20:32 2018/6/7
 * Modified by Frankel.Y
 */
public class ImageWall {

    private static ImageBrick[][] wall;
    private static int width;
    private static int height;

    public static final int INSERT_HASH_SUCCESS = 1;
    public static final int INSERT_HASH_ERR = 2;

    public static final int ENCRYP_TYPE_AES = 0;
    public static final int ENCRYP_TYPE_RSA = 1;

    /**
     * @param originPicture     Original Picture
     * @param buffer            Binary Watermark(1 as black, 0 as white)
     * */
    public ImageWall( BufferedImage originPicture, int[][] buffer ) {
        initialize(fillPiece(originPicture,buffer));
    }


    /**
     * Initialize by cutting the whole picture into 8*8 parts
     * created in 20:38 2018/6/7
     */
    public static void initialize( int[][] buffer ) {
        int m, n;

        m = buffer[0].length / 8;
        n = buffer.length / 8;
        width = m;
        height = n;
        wall = new ImageBrick[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                wall[i][j] = new ImageBrick();
                for (int p = i * 8; p < (i + 1) * 8; i++) {
                    for (int q = j * 8; q < (j + 1) * 8; j++) {
                        wall[i][j].clay[p % 8] = (byte)(wall[i][j].clay[p % 8] |
                                ((buffer[p][q] & 0x01) << (q%8)));
                    }
                }
            }
        }
    }

    /**
     * Extend the binary watermark according to the size of original picture p
     * @param p     Original Picture
     * @param x     Binary Watermark
     * created in 20:40 2018/6/7
     */
    private static int[][] fillPiece(BufferedImage p, int[][] x) {
        int m,n;
        m = p.getHeight();
        n = p.getWidth();
        int[][] newP= new int[m+8-m%8][n+8-n%8];

        for(int i = 0; i < m+8-m%8;i++)
            for(int j = 0; j < n+8-n%8; j++) {
                if(i <= m && j <= n)
                    newP[i][j] = x[i%x[0].length][j%x.length];
                else
                    newP[i][j] = 0;
            }
        return newP;
    }

    /**
     * Insert hashvalue into all ImageBricks
     * created in 20:41 2018/6/7
     */
    public static int insertHash(byte[] hashValue) {
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                wall[i][j].insertHash(hashValue);
            }
        }
        return INSERT_HASH_SUCCESS;
    }

    /**
     * Encrypt bytes in all ImageBricks
     * created in 20:42 2018/6/7
     */
    public static void encrypt(String password, int encrypType) {
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                wall[i][j].encrypt(password,encrypType);
            }
        }
    }

    public static void decrypt(String password, int decryptType, File file){
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width;j++){
                    wall[i][j].decrypt(password,decryptType,file);
            }
        }
    }



    /**
     * Convert Matrix Information into two-dimensional integar array
     * created in 20:44 2018/6/7
     */
    public static int[][] matrixOutput() {
        int[][] outputMatrix = new int[height*8][width*8];
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                for (int p = i * 8; p < (i + 1) * 8; i++) {
                    for (int q = j * 8; q < (j + 1) * 8; j++) {
                        outputMatrix[p][q] = ((wall[i][j].clay[p % 8] &
                                (0x01 << (q % 8))) >> (q % 8)) & 0x01;
                    }
                }
            }
        }
        return outputMatrix;
    }

    public static BufferedImage wall2BufferImage(){

        BufferedImage outputImage = new BufferedImage(height, width,BufferedImage.TYPE_INT_RGB);



        return outputImage;
    }


}
