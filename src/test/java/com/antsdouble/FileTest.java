package com.antsdouble;

import com.antsdouble.util.AntsArrayUtil;
import com.antsdouble.util.AntsFileUtil;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author lyy
 * @Deprecated
 * @date 2019/10/11
 */
public class FileTest {

    @Test
    public void testFile(){
        try {
            List<String> readFile = AntsFileUtil.readFileForList("F:\\mianhua\\src\\main\\resources\\testfile.txt");
            System.out.println(readFile);
            System.out.println(Arrays.toString(AntsArrayUtil.convertArray(readFile,String.class)));
            String readString=AntsFileUtil.readFileForString("F:\\mianhua\\src\\main\\resources\\testfile.txt");
            System.out.println(readString);
          AntsFileUtil.writeStringToFile("./target/01.txt",readString);
            System.out.println(readString);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
