package org.example.sha256;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.text.csv.*;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class ShaTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void sha256() {
        //SHA256加密
        String s = DigestUtil.sha256Hex("v305o5ftwohhn7ac8c0f02dbb0bb0f5d29e2bf6cd4789");
        System.out.println(s);
    }

    @Test
    public void sha256File() {
        CsvReader csvReader = CsvUtil.getReader();
        try {

            CsvData csvData = csvReader.read(new File("C:\\Users\\rongkang.lu\\Desktop\\demo.csv"), CharsetUtil.CHARSET_UTF_8);
            List<CsvRow> rows = csvData.getRows();
            List<String[]> aList = Lists.newArrayList();
            rows.forEach(x -> {
                String data = x.get(0);
                String sha256Hex = DigestUtil.sha256Hex(data);
                System.out.println(data + " --- " + sha256Hex);
                aList.add(new String[]{sha256Hex});
            });

            String path = FileUtil.isWindows() ? "C:\\Users\\rongkang.lu\\Desktop\\demo1.csv" : "~/test/testWrite.csv";
            CsvWriter writer = CsvUtil.getWriter(path, CharsetUtil.CHARSET_UTF_8);
            writer.write(aList);
        } catch (IORuntimeException e) {
            e.printStackTrace();
        }
    }
}
