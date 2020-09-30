import java.util.Scanner;

public class Main{

  public static Scanner sc = new Scanner(System.in);
  private static User user;
  public static void main(String[] args){
    createAccount();
    banking();
  }

  public static void createAccount(){
    System.out.print("Creating new Account...\nEnter your Name : ");
    String name = sc.nextLine();
    System.out.print("Enter the amount to be deposited : ");
    int bal = sc.nextInt();
    sc.nextLine();
    boolean validPh = false;
    String ph = null;
    while(!validPh){
      System.out.print("Enter your mobile no. : ");
      ph = sc.nextLine().trim();
      validPh = ph.matches("^[0-9]{10}$");
      if(!validPh){
        System.out.println("Invalid mobile no.");
      }
    }
    boolean validPin = false;
    String pin = null;
    while(!validPin){
      System.out.print("Enter a four digit pin : ");
      pin = sc.nextLine();
      validPin = pin.matches("^[0-9]{4}$");
      if(!validPin){
        System.out.println("Invalid Pin!!");
      }
    }
    user = new User(name, bal, pin, ph);
    enterBanking();
  }

  public static void enterBanking(){
    System.out.print("Enter Banking? Y/N : ");
    String cont = sc.nextLine().trim().toLowerCase();
    if(cont.length()>1){
      System.out.println("Invalid Input");
      enterBanking();
    }
    char c = cont.charAt(0);
    if(c=='y'){
      boolean validPin = false;
      String pin = null;
      while(!validPin){
        System.out.print("Enter a four digit pin : ");
        pin = sc.nextLine();
        validPin = pin.matches("^[0-9]{4}$");
        if(!validPin){
          System.out.println("Invalid Pin!!");
        }
      }
      if(pin.equals(user.getPin())){
        banking();
      }
      else{
        System.out.println("Authentication failed.");
        System.exit(0);
      }
    }
    else if(c=='n'){
      System.out.println("Thank You...");
    }
    else{
      System.out.println("Invalid Input");
      enterBanking();
    }
  }

  public static void Cont(){
    System.out.print("Continue banking? Y/N : ");
    String cont = sc.nextLine().trim().toLowerCase();
    if(cont.length()>1){
      System.out.println("Invalid Input");
      Cont();
    }
    char c = cont.charAt(0);
    if(c=='y'){
      banking();
    }
    else if(c=='n'){
      System.out.println("Thank You...");
    }
    else{
      System.out.println("Invalid Input");
      Cont();
    }
  } 

  public static void banking(){
    System.out.println("Select an Option :");
    System.out.println("1. View Balance\n2. Withdraw\n3. Deposit\n4. Change Pin\n5. Exit");
    int op = sc.nextInt();
    sc.nextLine();
    switch(op){
      case 1 :
        System.out.println(user.getBalance());
        break;
      case 2 :
        withdraw();
        break;
      case 3:
        deposit();
        break;
      case 4:
        changePin();
        break;
      case 5 :
        System.out.println("Thank you...");
        System.exit(0);
        break;
      default :
        System.out.println("Please select an valid option.");
        banking();
    }
    Cont();
  }

  public static void withdraw(){
    System.out.print("Enter the amount to withdraw : ");
    int wd = sc.nextInt();
    sc.nextLine();
    if(wd == 0){
      System.out.println("Amount should not be zero.");
    }
    else if(wd>user.getBalance()){
      System.out.println("Insufficient Balance...");
      banking();
    }
    else if(wd%100!=0){
      System.out.println("Please enter amount in multiples of 100.");
      withdraw();
    }
    else{
      int bal = user.getBalance();
      bal-=wd;
      user.setBalance(bal);
      System.out.println("Account Balance : "+user.getBalance());
    }
  }

  public static void deposit(){
    System.out.print("Enter the amount to deposit : ");
    int dp = sc.nextInt();
    sc.nextLine();
    if(dp == 0){
      System.out.println("Amount should not be zero.");
    }
    else if(dp%100!=0){
      System.out.println("Please enter amount in multiples of 100.");
      deposit();
    }
    else{
      int bal = user.getBalance();
      bal+=dp;
      user.setBalance(bal);
      System.out.println("Account Balance : "+user.getBalance());
    }
  }

  public static void changePin(){
    System.out.print("Authentication required.\nEnter your mobile no. : ");
    String ph = sc.nextLine().trim();
    if(ph.equals(user.getPhone())){
      boolean validPin = false;
      String pin = null;
      while(!validPin){
        System.out.print("Enter a new four digit pin : ");
        pin = sc.nextLine();
        validPin = pin.matches("^[0-9]{4}$");
        if(!validPin){
          System.out.println("Invalid Pin!!");
        }
      }
      user.setPin(pin);
      System.out.println("Pin updated successfully.");
    }
    else{
      System.out.println("Authentication failed.");
      System.exit(0);
    }
  }
}