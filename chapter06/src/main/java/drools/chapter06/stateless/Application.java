package drools.chapter06.stateless;

import java.util.Date;

/**
 * @author tanabe
 */
public class Application {

  private Date dateApplied;
  private boolean valid;

  public Application() {
    this.valid = true;
  }

  public Date getDateApplied() {
    return dateApplied;
  }

  public void setDateApplied(Date dateApplied) {
    this.dateApplied = dateApplied;
  }

  public boolean isValid() {
    return valid;
  }

  public void setValid(boolean valid) {
    this.valid = valid;
  }

  @Override
  public String toString() {
    return "Application{" +
      "dateApplied=" + dateApplied +
      ", valid=" + valid +
      '}';
  }

}
