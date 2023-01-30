import com.qa.CreateDriverSession;
import org.testng.annotations.BeforeClass;

public class FirtsTestNgTest {

    @BeforeClass
    void createDriver(){
        try {
            CreateDriverSession.initializeDriver("Android");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
