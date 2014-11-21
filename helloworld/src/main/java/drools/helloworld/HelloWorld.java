package drools.helloworld;

import org.kie.api.KieServices;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tanabe
 */
public class HelloWorld {
  public static void main(String[] args) {

    // KieServices は全ての KIE サービスのファクトリ
    KieServices ks = KieServices.Factory.get();

    /*
     * クラスパスから KieContainer を生成
     * 設定のために /META-INF/kmodule.xml を探し、KieContainer に KieModule をインスタンス化する
     */
    KieContainer kc = ks.getKieClasspathContainer();

    // kmodule.xml で設定した HelloWorldKS という名前でセッションを生成
    KieSession ksession = kc.newKieSession("HelloWorldKS");

    ksession.setGlobal("list", new ArrayList<Object>());

    ksession.addEventListener(new DebugAgendaEventListener());
    ksession.addEventListener(new DebugRuleRuntimeEventListener());

    KieRuntimeLogger logger = ks.getLoggers().newFileLogger(ksession, "./helloworld");

//    KieRuntimeLogger logger = ks.getLoggers().newThreadedFileLogger(ksession, "./helloworld", 1_000);

    final Message message = new Message();
    message.setMessage("Hello World");
    message.setStatus(Message.HELLO);
    ksession.insert(message);

    ksession.fireAllRules();

    logger.close();

    ksession.dispose();
  }

  public static class Message {
    public static final int HELLO = 0;
    public static final int GOODBYE = 1;

    private String message;

    private int status;

    public Message() {

    }

    public String getMessage() {
      return this.message;
    }

    public void setMessage(final String message) {
      this.message = message;
    }

    public int getStatus() {
      return this.status;
    }

    public void setStatus(final int status) {
      this.status = status;
    }

    public static Message doSomething(Message message) {
      return message;
    }

    public boolean isSomething(String msg,
                               List<Object> list) {
      list.add(this);
      return this.message.equals(msg);
    }
  }

}
