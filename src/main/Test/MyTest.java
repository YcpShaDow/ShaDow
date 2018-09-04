
import com.ycp.pojo.ItemsCustom;
import com.ycp.service.ItemsService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author lxf
 * @version 1.0
 * @description springmybatis01
 * @date 2018/8/24
 **/
public class MyTest {
    private ItemsService itemsService;

    @Before
    public void before() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationContext-service.xml");
        itemsService = (ItemsService) ac.getBean("itemsService");
    }

    @Test
    public void test01() throws Exception {
        List<ItemsCustom> itemsList = itemsService.findItemsList(null);
        System.out.println("*****" + itemsList);
    }



}
