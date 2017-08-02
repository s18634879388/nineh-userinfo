import com.ninehcom.common.enums.ErrorCode;
import com.ninehcom.common.util.Result;
import com.ninehcom.userinfo.Application;
import com.ninehcom.userinfo.agent.SearchAgent;
import com.ninehcom.userinfo.entity.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by Administrator on 2017/8/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class SearchAgentTest {
    @Autowired
    SearchAgent searchAgent;

    @Test
    public void searchTest(){
        UserInfo userInfo = new UserInfo();
        userInfo.setId("e60afaca-25d8-4f4f-9dc6-e1763e1fcae1");
        userInfo.setNickName("006sPHdY1iY");
        try {
            boolean flag = searchAgent.updateSearchWord(userInfo);
            if (!flag){
                System.out.println("----------fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
