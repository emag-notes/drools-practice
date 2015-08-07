package drools.chapter06.stateless;

import drools.chapter06.PropertiesRule;
import org.junit.Rule;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

import java.util.Arrays;
import java.util.Date;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * @author tanabe
 */
public class LicenseApplicationTest {

  @Rule
  public PropertiesRule propertiesRule = new PropertiesRule();

  @Test
  public void test1() throws Exception {
    // Setup
    KieServices kieServices = KieServices.Factory.get();
    KieContainer kContainer = kieServices.getKieClasspathContainer();

    StatelessKieSession kSession = kContainer.newStatelessKieSession("LicenseApplicationKS");

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

  @Test
  public void test2() throws Exception {
    // Setup
    KieServices kieServices = KieServices.Factory.get();
    KieContainer kContainer = kieServices.getKieClasspathContainer();

    StatelessKieSession kSession = kContainer.newStatelessKieSession("LicenseApplicationKS");

    Application application = new Application(new Date());
    System.out.println(application);

    assertThat(application.isValid(), is(true));

    // Exercise
    kSession.execute(application);

    // Verify
    assertThat(application.isValid(), is(false));
  }

}