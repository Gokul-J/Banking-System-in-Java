public class User {
  private String name;
  private int bal;
  private String pin;
  private String phone;
  
  User(String name, int bal, String pin, String phone){
    this.name = name;
    this.bal = bal;
    this.pin = pin;
    this.phone = phone;
    System.out.println("Account created Successfully.");
  }

  public int getBalance(){
    return this.bal;
  }

  public String getUserName(){
    return this.name;
  }

  public String getPin(){
    return this.pin;
  }

  public String getPhone(){
    return this.phone;
  }

  public void setBalance(int bal){
    this.bal = bal;
  }

  public void setPin(String pin){
    this.pin = pin;
  }
}
