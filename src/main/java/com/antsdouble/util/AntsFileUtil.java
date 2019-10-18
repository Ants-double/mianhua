package com.antsdouble.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author lyy
 * @Deprecated
 * @date 2019/10/9
 */
public class AntsFileUtil {

    public static List<String> readFileForList(String path) throws IOException {
        return FileUtils.readLines(new File(path), StandardCharsets.UTF_8);
    }

    public static String readFileForString(String path) throws IOException {
        return FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);
    }

    public static void writeStringToFile(String path, String content) throws IOException {
        FileUtils.writeStringToFile(new File(path), content, StandardCharsets.UTF_8, true);
    }

    public static void writeStringToFile(String path, String content, boolean append) throws IOException {
        FileUtils.writeStringToFile(new File(path), content, StandardCharsets.UTF_8, append);
    }

    public static void writeListToFile(String path, List<String> list) throws IOException {
        FileUtils.writeLines(new File(path), list, true);
    }

    public static void writeListToFile(String path, List<String> list, boolean append) throws IOException {
        FileUtils.writeLines(new File(path), list, append);
    }

    public static void deleteFile(String path) throws IOException {
        FileUtils.deleteDirectory(new File(path));
    }

    public static void deleteForce(String path) {
        FileUtils.deleteQuietly(new File(path));
    }

    public static void copyDirectorySameLevel(String origin, String target) throws IOException {
        FileUtils.copyDirectory(new File(origin), new File(target));
    }

    public static void copyDirectoryToDirectory(String origin, String target) throws IOException {
        FileUtils.copyDirectoryToDirectory(new File(origin), new File(target));
    }

    public static void copyFile(String origin, String target) throws IOException {
        FileUtils.copyFile(new File(origin), new File(target));
    }

    public static void copyFileToDirectory(String origin, String target) throws IOException {
        FileUtils.copyFileToDirectory(new File(origin), new File(target));
    }

    public static void copyURLToFile(String url, String targetPath) throws IOException {
        FileUtils.copyURLToFile(new URL(url), new File(targetPath));
    }

    public static void downloadURLToFile(String url, String targetPath) throws IOException {
        FileUtils.copyURLToFile(new URL(url), new File(targetPath));
    }

    public static void downloadURLToFileForStream(String url, String targetPath) throws IOException {
        InputStream in = new URL(url).openStream();
        byte[] bytes = IOUtils.toByteArray(in);
        FileUtils.writeByteArrayToFile(new File(targetPath), bytes);
        IOUtils.closeQuietly(in);
    }

    public static void moveFileOrDirectory(String origin, String target) throws IOException {
        FileUtils.moveDirectory(new File(origin), new File(target));
    }

    public static void moveToDirectory(String origin, String target) throws IOException {
        FileUtils.moveDirectoryToDirectory(new File(origin), new File(target), true);
    }

    public static void moveFileToDirectory(String origin, String target) throws IOException {
        FileUtils.moveFileToDirectory(new File(origin), new File(target), true);
    }

    public static String readTxtFileOneLine(String fileP) {
        try {
            //项目路径
            String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource("")) + "../../";
            filePath = filePath.replaceAll("file:/", "");
            filePath = filePath.replaceAll("%20", " ");
            filePath = filePath.trim() + fileP.trim();
            if (filePath.indexOf(":") != 1) {
                filePath = File.separator + filePath;
            }
            String encoding = "utf-8";
            File file = new File(filePath);
            //判断文件是否存在
            if (file.isFile() && file.exists()) {
                // 考虑到编码格式
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    return lineTxt;
                }
                read.close();
            } else {
                System.out.println("找不到指定的文件,查看此路径是否正确:" + filePath);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


}
