import com.practice.dto.UserDTO;
import com.practice.entity.User;
import com.practice.service.GenericService;
import com.practice.service.impl.GenericServiceImpl;
import com.practice.utils.convert.ConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Generic
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/9/922:51
 */
@Slf4j
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

    @Test
    public void testErase(){
        List<String> list1 = new ArrayList<String>();
        list1.add("aa");
        String str = list1.get(0);

        List<Integer> list2 = new ArrayList<Integer>();
        list2.add(100);
        Integer integer = list2.get(0);
    }

    @Test
    public void testList() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<String> list1 = new ArrayList<String>();
        List<Integer> list2 = new ArrayList<Integer>();
        log.info("数据类型：lis1 -> [{}], list2 -> [{}] ", list1.getClass(), list2.getClass());
        list1.getClass().getMethod("add", Object.class).invoke(list1, 100);
        log.info("list1: {}, list2: {}, class(list1 = list2): [{}]", list1.toString(), list2.toString(), list1.getClass() == list2.getClass());
    }

    @Test
    public void testWildcard() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 使用通配符无法直接add数据
        List<?> list = new ArrayList<>();
        list.getClass().getMethod("add", Object.class).invoke(list, 100);
        log.info("数据类型：list3 -> [{}], list3: [{}]", list.getClass(), list.get(0).getClass());
    }

}
