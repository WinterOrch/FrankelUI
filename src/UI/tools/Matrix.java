package UI.tools;
//TODO 此对象下所有方法目前皆无法使用
public class Matrix {

    public int[][] reshape ( int x, int y, int[][] origin ) {
        int[][] result = new int[x][y];

        if ( x * y != origin.length * origin[0].length )
            return null;
        else {
            for(int i = 0; i < origin.length; i++){
                for(int j = 0; j < origin[i].length; j++){

                    result[(i*origin[0].length + j) / y][(i*origin[0].length + j) % y] = origin[i][j];

                }
            }
            return result;
        }
    }
/*
    public byte[][] reshape ( int x, int y, byte[][] origin ) {
        byte[][] result = new byte[x][y];

        if ( x * y != origin.length * origin[0].length )
            return null;
        else {
            for(int i = 0; i < origin.length; i++){
                for(int j = 0; j < origin[i].length; j++){
                    result[(i*origin[0].length + j) / y][(i*origin[0].length + j) % y] = origin[i][j];
                }
            }
            return result;
        }
    }*/
}
