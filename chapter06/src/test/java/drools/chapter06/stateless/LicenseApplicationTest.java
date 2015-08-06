package drools.chapter06.stateless;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * @author tanabe
 */
public class LicenseApplicationTest {

  @Test
  public void test() throws Exception {
    // Setup
    KieServices kieServices = KieServices.Factory.get();
    KieContainer kContainer = kieServices.getKieClasspathContainer();

    StatelessKieSession kSession = kContainer.newStatelessKieSession();

    Applicant applicant = new Applicant("Mr John Smith", 16);
    Application application = new Application();

    assertThat(application.isValid(), is(true));

    // Exercise
//    kSession.execute(Arrays.asList(new Object[]{appClient, application}));
    kSession.execute(
      kieServices.getCommands()
        .newInsertElements(Arrays.asList(applicant, application)));

    // Verify
    assertThat(application.isValid(), is(false));
  }
}