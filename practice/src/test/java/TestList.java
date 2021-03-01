import org.junit.Test;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author XiaoSi
 * @className TestList
 * @description TODO
 * @date 2020/2/10
 */
public class TestList {
    @Test
    public void testObjectToList01(){
        Object sources = new String[]{"a", "vv", "a"};
        String simpleName = sources.getClass().getSimpleName();
        System.out.println(simpleName);

        ArrayList sources1 = (ArrayList) sources;
        System.out.println(sources1.size());
    }

    @Test
    public void test(){
        String format = MessageFormat.format("var5={0}, var6={1}", "superVariable5", "superVariable6");
        System.out.println(format);
    }
}
