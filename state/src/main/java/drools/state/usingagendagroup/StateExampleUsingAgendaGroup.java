package drools.state.usingagendagroup;

import org.kie.api.KieServices;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieSession;

import drools.state.State;

public class StateExampleUsingAgendaGroup {

  public static void main(String[] args) {
    KieServices ks = KieServices.Factory.get();
    KieSession ksession = ks
        .getKieClasspathContainer()
        .newKieSession("StateAgendaGroupKS");

    KieRuntimeLogger logger = ks.getLoggers().newFileLogger(ksession, "./state");
    ksession.addEventListener(new DebugAgendaEventListener());
    ksession.addEventListener(new DebugRuleRuntimeEventListener());

    final State a = new State("A");
    final State b = new State("B");
    final State c = new State("C");
    final State d = new State("D");

    ksession.insert(a);
    ksession.insert(b);
    ksession.insert(c);
    ksession.insert(d);

    ksession.fireAllRules();

    logger.close();

    ksession.dispose();
  }
  
}
