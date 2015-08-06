package drools.chapter06.stateless;

/**
 * @author tanabe
 */
public class Applicant {

  private String name;
  private int age;

  public Applicant(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "AppClient{" +
      "name='" + name + '\'' +
      ", age=" + age +
      '}';
  }

}
