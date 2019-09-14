import com.practice.service.GenericService;
import com.practice.service.impl.GenericServiceImpl;
import com.practice.utils.convert.ConvertUtil;
import org.junit.Test;

/**
 * @ClassName Generic
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/9/922:51
 */
public class Generic {

    @Test
    public void generic() {
        ConvertUtil.showMessage(1);
        ConvertUtil.showMessage('c');
        ConvertUtil.showMessage("string");
    }

    /**
     * 泛型接口测试
     */
    @Test
    public void testGenericService() {
        GenericService<Integer> genericInteger = new GenericServiceImpl<Integer>();
        genericInteger.testGeneric(1);
        GenericService<Character> genericCharacter = new GenericServiceImpl<Character>();
        genericCharacter.testGeneric('c');
        GenericService<String> genericString = new GenericServiceImpl<String>();
        genericString.testGeneric("string");
    }

}
