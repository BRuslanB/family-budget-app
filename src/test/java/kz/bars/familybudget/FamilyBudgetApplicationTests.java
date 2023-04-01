package kz.bars.familybudget;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {FamilyBudgetApplication.class})
public class FamilyBudgetApplicationTests {

}
