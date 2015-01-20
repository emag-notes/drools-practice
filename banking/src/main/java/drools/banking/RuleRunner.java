package drools.banking;

import java.util.Arrays;
import java.util.Collection;

import org.kie.api.KieServices;
import org.kie.api.io.ResourceType;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.definition.KnowledgePackage;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;

public class RuleRunner {

  public void runRules(String[] rules, Object[] facts) {

    KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
    KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
    
    Arrays.stream(rules).forEach(rule -> {
      System.out.println("Loading file: " + rule);
      kbuilder.add(ResourceFactory.newClassPathResource(rule, RuleRunner.class), ResourceType.DRL);
    });
    
    Collection<KnowledgePackage> kpackages = kbuilder.getKnowledgePackages();
    kbase.addKnowledgePackages(kpackages);
    StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
    
    KieServices ks = KieServices.Factory.get();
    KieRuntimeLogger logger = ks.getLoggers().newFileLogger(ksession, "./banking");

    Arrays.stream(facts).forEach(fact -> {
      System.out.println("Inserting fact: " + fact);
      ksession.insert(fact);
    });
    
    ksession.fireAllRules();
    
    logger.close();
    ksession.dispose();
  }
}
