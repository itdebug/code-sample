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
import java.util.concurrent.atomic.AtomicInteger;

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
        String s = DigestUtil.sha256Hex("tf1010mdpft844c1ccbcb4619225a34fd3db5708f35");
        System.out.println(s);
    }

    @Test
    public void sha256File() {
        CsvReader csvReader = CsvUtil.getReader();
        try {

            CsvData csvData = csvReader.read(new File("C:\\Users\\rongkang.lu\\Desktop\\细白管&黑金唇镜购买人群补充_20221021.csv"), CharsetUtil.CHARSET_UTF_8);
            List<CsvRow> rows = csvData.getRows();
            List<String[]> aList = Lists.newArrayList();
            AtomicInteger i = new AtomicInteger();
            rows.forEach(x -> {
                String data = x.get(0);
                String sha256Hex = DigestUtil.sha256Hex(data);
                System.out.println(i.incrementAndGet());
                aList.add(new String[]{sha256Hex});
            });

            String path = FileUtil.isWindows() ? "C:\\Users\\rongkang.lu\\Desktop\\细白管&黑金唇镜购买人群补充_20221021_sha.csv" : "~/test/testWrite.csv";
            CsvWriter writer = CsvUtil.getWriter(path, CharsetUtil.CHARSET_UTF_8);
            writer.write(aList);
        } catch (IORuntimeException e) {
            e.printStackTrace();
        }
    }
}
