package drools.chapter06.stateful;

import drools.chapter06.PropertiesRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tanabe
 */
public class HouseMonitoringTest {

  @Rule
  public PropertiesRule propertiesRule = new PropertiesRule();

  private KieSession kSession;
  private Map<String, Room> name2room;

  @Before
  public void setUp() throws Exception {
    // Setup
    KieServices kieServices = KieServices.Factory.get();
    KieContainer kContainer = kieServices.getKieClasspathContainer();
    kSession = kContainer.newKieSession();

    String[] names = {"kitchen", "bedroom", "office", "livingroom"};

    name2room = new HashMap<>();
    Arrays.stream(names).forEach(name -> {
      Room room = new Room(name);
      name2room.put(name, room);
      kSession.insert(room);

      Sprinkler sprinkler = new Sprinkler(room);
      kSession.insert(sprinkler);
    });
  }
  @Test
  public void test1() throws Exception {
    // Exercise & Verify
    kSession.fireAllRules();
  }

  @Test
  public void test2() throws Exception {
    // Setup
    Fire kitchenFire = new Fire(name2room.get("kitchen"));
    Fire officeFire = new Fire(name2room.get("office"));

    // Exercise & Verify
    FactHandle kitchenFireHandle = kSession.insert(kitchenFire);
    FactHandle officeFireHandle = kSession.insert(officeFire);

    kSession.fireAllRules();

    kSession.delete(kitchenFireHandle);
    kSession.delete(officeFireHandle);

    kSession.fireAllRules();
  }

}
