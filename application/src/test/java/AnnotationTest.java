import com.practice.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @ClassName AnnotationTest
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/10/513:54
 */
@Slf4j
public class AnnotationTest {

    @Test
    public void testPasswordSize() {
        Account account = new Account("001");
        System.out.println(account);
    }

}
