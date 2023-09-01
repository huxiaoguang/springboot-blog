package main.blog.utils;

import cn.hutool.core.io.resource.ClassPathResource;
import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.xdb.Searcher;
import java.io.IOException;

@Slf4j
public class IpParseUtil
{
    private static Searcher searcher = null;

    static
    {
        ClassPathResource resource = new ClassPathResource("ip2region/ip2region.xdb");
        String dbPath = resource.getUrl().getPath();

        // 1、从 dbPath 中预先加载 VectorIndex 缓存，并且把这个得到的数据作为全局变量，后续反复使用。
        byte[] vIndex = new byte[0];
        try {
            vIndex = Searcher.loadVectorIndexFromFile(dbPath);
        } catch (Exception e) {
            log.error("failed to load vector index from `%s`: %s\n", dbPath, e);
        }

        // 2、使用全局的 vIndex 创建带 VectorIndex 缓存的查询对象。
        try {
            searcher = Searcher.newWithVectorIndex(dbPath, vIndex);
        } catch (Exception e) {
            System.out.printf("failed to create vectorIndex cached searcher with `%s`: %s\n", dbPath, e);
        }
    }

    public static String parse(String ip) throws IOException
    {
        String region = "";
        try {
            region = searcher.search(ip).replace("0|", "");
        } catch (Exception e)
        {
            log.error("failed to search(%s): %s\n", ip, e);
        }
        searcher.close();
        return region;
    }
}
