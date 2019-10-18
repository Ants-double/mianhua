package com.antsdouble.util;

import com.antsdouble.common.Constans;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

/**
 * @author lyy
 * @Deprecated
 * @date 2019/10/18
 */
public class AntsImageUtil {






    public static BufferedImage cutImage(File image, Rectangle rect) {
        if (image.exists() && rect != null) {
            // 用ImageIO读取字节流
            BufferedImage bufferedImage = getImage(image.getAbsolutePath());
            int width = rect.width;
            int height = rect.height;
            int x = rect.x;
            int y = rect.y;
            BufferedImage dest = new BufferedImage(width, height, Transparency.TRANSLUCENT);
            Graphics g = dest.getGraphics();
            g.drawImage(bufferedImage, 0, 0, width, height, x, y, x + width, height + y, null);
            return dest;
        }
        return null;
    }

    public static BufferedImage cutImage2(File image, Rectangle rect) {
        if (image.exists() && rect != null) {
            BufferedImage bi = null;
            try {
                ImageReader reader = getImageReader(image.getAbsolutePath());
                ImageReadParam param = reader.getDefaultReadParam();
                param.setSourceRegion(rect);
                bi = reader.read(0, param);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bi;
        }
        return null;
    }


    public static BufferedImage getImage(String fileName) {
        File picture = new File(fileName);
        BufferedImage sourceImg = null;
        try {
            sourceImg = ImageIO.read(new FileInputStream(picture));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sourceImg;
    }

    public static ImageReader getImageReader(String fileName) {
        if (fileName != null) {
            String suffix = "";
            for (String str : ImageIO.getReaderFormatNames()) {
                if (fileName.lastIndexOf(Constans.POINT_STR + str) > 0) {
                    suffix = str;
                }
            }

            if (!"".equals(suffix)) {
                try {
                    // 将FileInputStream 转换为ImageInputStream
                    ImageInputStream iis = ImageIO.createImageInputStream(new FileInputStream(fileName));
                    // 根据图片类型获取该种类型的ImageReader
                    Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName(suffix);
                    ImageReader reader = readers.next();
                    reader.setInput(iis, true);
                    return reader;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return null;
    }

    public static void writeImage(File destImage, BufferedImage bufferedImage) {
        try {
            ImageIO.write(bufferedImage, Constans.IMAGE_PNG, destImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
