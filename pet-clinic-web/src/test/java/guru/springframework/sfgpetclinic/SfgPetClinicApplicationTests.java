package guru.springframework.sfgpetclinic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class SfgPetClinicApplicationTests {

	@Test
	void contextLoads(ApplicationContext appCtx) {
		assertThat(appCtx).isNotNull();
	}

}
