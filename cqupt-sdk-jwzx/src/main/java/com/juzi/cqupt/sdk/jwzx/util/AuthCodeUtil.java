package com.juzi.cqupt.sdk.jwzx.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Juzi
 * @since 2019/3/28 21:29
 * Blog https://juzibiji.top
 */
public class AuthCodeUtil {

    /**
     * 验证码位数
     */
    private static final int AUTH_CODE_NUMS = 5;

    /**
     * 验证码字符宽8个像素
     */
    private static final int AUTH_CODE_WIDTH = 8;

    /**
     * 验证码字符高10个像素
     */
    private static final int AUTH_CODE_HEIGHT = 10;

    private static int[][] num0 = new int[][]{
            {0, 0, 0, 1, 1, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 0, 0},
            {0, 1, 1, 0, 0, 1, 1, 0},
            {1, 1, 0, 0, 0, 0, 1, 1},
            {1, 1, 0, 0, 0, 0, 1, 1},
            {1, 1, 0, 0, 0, 0, 1, 1},
            {1, 1, 0, 0, 0, 0, 1, 1},
            {0, 1, 1, 0, 0, 1, 1, 0},
            {0, 0, 1, 1, 1, 1, 0, 0},
            {0, 0, 0, 1, 1, 0, 0, 0}};

    private static int[][] num1 = new int[][]{
            {0, 0, 0, 1, 1, 0, 0, 0},
            {0, 0, 1, 1, 1, 0, 0, 0},
            {0, 1, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 1, 1, 0, 0, 0},
            {0, 0, 0, 1, 1, 0, 0, 0},
            {0, 0, 0, 1, 1, 0, 0, 0},
            {0, 0, 0, 1, 1, 0, 0, 0},
            {0, 0, 0, 1, 1, 0, 0, 0},
            {0, 0, 0, 1, 1, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 0}};

    private static int[][] num2 = new int[][]{
            {0, 0, 1, 1, 1, 1, 0, 0},
            {0, 1, 1, 0, 0, 1, 1, 0},
            {1, 1, 0, 0, 0, 0, 1, 1},
            {0, 0, 0, 0, 0, 0, 1, 1},
            {0, 0, 0, 0, 0, 1, 1, 0},
            {0, 0, 0, 0, 1, 1, 0, 0},
            {0, 0, 0, 1, 1, 0, 0, 0},
            {0, 0, 1, 1, 0, 0, 0, 0},
            {0, 1, 1, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 1, 1, 1, 1}};

    private static int[][] num3 = new int[][]{
            {0, 1, 1, 1, 1, 1, 0, 0},
            {1, 1, 0, 0, 0, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 1, 1},
            {0, 0, 0, 0, 0, 1, 1, 0},
            {0, 0, 0, 1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 1, 1},
            {0, 0, 0, 0, 0, 0, 1, 1},
            {1, 1, 0, 0, 0, 1, 1, 0},
            {0, 1, 1, 1, 1, 1, 0, 0}};

    private static int[][] num4 = new int[][]{
            {0, 0, 0, 0, 0, 1, 1, 0},
            {0, 0, 0, 0, 1, 1, 1, 0},
            {0, 0, 0, 1, 1, 1, 1, 0},
            {0, 0, 1, 1, 0, 1, 1, 0},
            {0, 1, 1, 0, 0, 1, 1, 0},
            {1, 1, 0, 0, 0, 1, 1, 0},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 1, 1, 0},
            {0, 0, 0, 0, 0, 1, 1, 0},
            {0, 0, 0, 0, 0, 1, 1, 0}};

    private static int[][] num5 = new int[][]{
            {1, 1, 1, 1, 1, 1, 1, 0},
            {1, 1, 0, 0, 0, 0, 0, 0},
            {1, 1, 0, 0, 0, 0, 0, 0},
            {1, 1, 0, 1, 1, 1, 0, 0},
            {1, 1, 1, 0, 0, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 1, 1},
            {0, 0, 0, 0, 0, 0, 1, 1},
            {1, 1, 0, 0, 0, 0, 1, 1},
            {0, 1, 1, 0, 0, 1, 1, 0},
            {0, 0, 1, 1, 1, 1, 0, 0}};

    private static int[][] num6 = new int[][]{
            {0, 0, 1, 1, 1, 1, 0, 0},
            {0, 1, 1, 0, 0, 1, 1, 0},
            {1, 1, 0, 0, 0, 0, 1, 0},
            {1, 1, 0, 0, 0, 0, 0, 0},
            {1, 1, 0, 1, 1, 1, 0, 0},
            {1, 1, 1, 0, 0, 1, 1, 0},
            {1, 1, 0, 0, 0, 0, 1, 1},
            {1, 1, 0, 0, 0, 0, 1, 1},
            {0, 1, 1, 0, 0, 1, 1, 0},
            {0, 0, 1, 1, 1, 1, 0, 0}};

    private static int[][] num7 = new int[][]{
            {1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 1, 1},
            {0, 0, 0, 0, 0, 0, 1, 1},
            {0, 0, 0, 0, 0, 1, 1, 0},
            {0, 0, 0, 0, 1, 1, 0, 0},
            {0, 0, 0, 1, 1, 0, 0, 0},
            {0, 0, 1, 1, 0, 0, 0, 0},
            {0, 1, 1, 0, 0, 0, 0, 0},
            {1, 1, 0, 0, 0, 0, 0, 0},
            {1, 1, 0, 0, 0, 0, 0, 0}};

    private static int[][] num8 = new int[][]{
            {0, 0, 1, 1, 1, 1, 0, 0},
            {0, 1, 1, 0, 0, 1, 1, 0},
            {1, 1, 0, 0, 0, 0, 1, 1},
            {0, 1, 1, 0, 0, 1, 1, 0},
            {0, 0, 1, 1, 1, 1, 0, 0},
            {0, 1, 1, 0, 0, 1, 1, 0},
            {1, 1, 0, 0, 0, 0, 1, 1},
            {1, 1, 0, 0, 0, 0, 1, 1},
            {0, 1, 1, 0, 0, 1, 1, 0},
            {0, 0, 1, 1, 1, 1, 0, 0}};

    private static int[][] num9 = new int[][]{
            {0, 0, 1, 1, 1, 1, 0, 0},
            {0, 1, 1, 0, 0, 1, 1, 0},
            {1, 1, 0, 0, 0, 0, 1, 1},
            {1, 1, 0, 0, 0, 0, 1, 1},
            {0, 1, 1, 0, 0, 1, 1, 1},
            {0, 0, 1, 1, 1, 0, 1, 1},
            {0, 0, 0, 0, 0, 0, 1, 1},
            {0, 1, 0, 0, 0, 0, 1, 1},
            {0, 1, 1, 0, 0, 1, 1, 0},
            {0, 0, 1, 1, 1, 1, 0, 0}};

    private static int[][][] nums = new int[][][]{num0, num1, num2, num3, num4, num5, num6, num7, num8, num9};

    /**
     * 解析教务在线验证码
     *
     * @param inputStream
     * @return
     */
    public static String jwzx(InputStream inputStream) {
        BufferedImage bufferedImage = null;
        // 读取图片
        try {
            bufferedImage = ImageIO.read(inputStream);
        } catch (IOException e) {
        }

        StringBuilder sb = new StringBuilder();

        // 5个数字，循环5次
        for (int i = 0; i < AUTH_CODE_NUMS; i++) {
            int[][] tempNum = new int[10][8];
            // 每个数字高10个像素
            for (int j = 0; j < AUTH_CODE_HEIGHT; j++) {
                // 每个数字宽8个像素
                for (int k = 0; k < AUTH_CODE_WIDTH; k++) {
                    int rgb = bufferedImage.getRGB(k + i * 9, j + 3);
                    // 对颜色值进行判断
                    if (rgb == -1) {
                        tempNum[j][k] = 0;
                    } else {
                        tempNum[j][k] = 1;
                    }
                }
            }

            // 当前数字是否匹配标识
            boolean matchFlag = false;
            // 遍历所有样本中的每个数字
            for (int j = 0; j < nums.length; j++) {

                // 获取二维数组中的每一行
                for (int k = 0; k < nums[j].length; k++) {

                    boolean lineFlag = false;
                    // 比较每一行中的每个数字
                    for (int l = 0; l < nums[j][k].length; l++) {
                        // 出现不等的情况，切换flag并跳出循环
                        if (nums[j][k][l] == 1) {
                            if (tempNum[k][l] == 1) {

                            } else {
                                lineFlag = true;
                                break;
                            }
                        }

                    }

                    // 判断行标志
                    if (lineFlag) {
                        break;
                    }
                    // 如果到最后一行还没有跳出循环，则代表匹配成功
                    if (k == nums[j].length - 1) {
                        matchFlag = true;
                        break;
                    }

                }

                // 如果匹配成功，则将数字加入stringBuilder
                if (matchFlag) {
                    sb.append(j);
                    break;
                }
            }

        }
        return sb.toString();
    }

}
