package drools.chapter06.executioncontrol.agendagroup;

import drools.chapter06.utils.DateUtils;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.Agenda;
import org.kie.api.runtime.rule.FactHandle;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author tanabe
 */
public class CashFlowTest {

  @Test
  public void test() throws Exception {
    // Setup
    KieServices kieServices = KieServices.Factory.get();
    KieContainer kContainer = kieServices.getKieClasspathContainer();

    KieSession kSession = kContainer.newKieSession("AgendaGroupCashFlowKS");

    KieRuntimeLogger auditLogger = kieServices.getLoggers()
      .newFileLogger(kSession, this.getClass().getName());

    Account account = new Account(1L);

    CashFlow cashFlow1 = new CashFlow(DateUtils.toDate(LocalDate.of(2007, 1, 12)), 100D, Type.CREDIT, 1L);
    CashFlow cashFlow2 = new CashFlow(DateUtils.toDate(LocalDate.of(2007, 2,  2)), 200D, Type.DEBIT,  1L);
    CashFlow cashFlow3 = new CashFlow(DateUtils.toDate(LocalDate.of(2007, 5, 18)),  50D, Type.CREDIT, 1L);
    CashFlow cashFlow4 = new CashFlow(DateUtils.toDate(LocalDate.of(2007, 3,  7)),  75D, Type.CREDIT, 1L);

    AccountPeriod accountPeriod = new AccountPeriod(
      DateUtils.toDate(LocalDate.of(2007, 1, 1)),
      DateUtils.toDate(LocalDate.of(2007, 3, 31)));

    // Exercise
    kSession.insert(account);
    kSession.insert(cashFlow1);
    kSession.insert(cashFlow2);
    kSession.insert(cashFlow3);
    kSession.insert(cashFlow4);
    FactHandle accountPeriodHandle = kSession.insert(accountPeriod);

    Agenda agenda = kSession.getAgenda();
    agenda.getAgendaGroup("report").setFocus();
    agenda.getAgendaGroup("calculation").setFocus();


    kSession.fireAllRules();

    // Verify
    assertThat(account.getBalance(), is(-25D));

    // More Exercise & Verify
    accountPeriod.setStart(DateUtils.toDate(LocalDate.of(2007, 4, 1)));
    accountPeriod.setEnd(DateUtils.toDate(LocalDate.of(2007, 6, 30)));
    kSession.update(accountPeriodHandle, accountPeriod);

    agenda.getAgendaGroup("report").setFocus();
    agenda.getAgendaGroup("calculation").setFocus();

    kSession.fireAllRules();

    assertThat(account.getBalance(), is(25D));

    auditLogger.close();
  }

}
