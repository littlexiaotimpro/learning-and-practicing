import com.practice.dto.UserDTO;
import com.practice.entity.User;
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
     * 明确类型
     * 限制传入的数据类型
     */
    @Test
    public void genericAB() {
        ConvertUtil<Integer, String> convertUtil = new ConvertUtil<Integer, String>();
        convertUtil.setA(10).setB("bString");
        convertUtil.abTest();
    }

    /**
     * 初始化不指定类型
     */
    @Test
    public void genericABC() {
        ConvertUtil convertUtil = new ConvertUtil();
        convertUtil.setA(10).setB("bString");
        convertUtil.abTest();
        convertUtil.setA('c').setB("bString");
        convertUtil.abTest();
        convertUtil.setA(10d).setB(true);
        convertUtil.abTest();
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

    @Test
    public void testConvertUtils() {
        User user = new User();
        user.setCode("001").setUserName("userOne");
        UserDTO userDTO = new UserDTO();
        System.out.println(user + "\n" + userDTO);

        userDTO = userDTO.convertBack(user);
        System.out.println(userDTO);

        userDTO.setUserName("userOneChange");
        user = userDTO.convertTo();
        System.out.println(user);
    }

}
