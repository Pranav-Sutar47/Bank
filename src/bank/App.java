package bank;
import bank.AccountOpening;
import bank.TransferMoney;

import java.util.Scanner;

public class App {
    static Scanner sc;
    public static void main(String[] args) throws Exception {

        int s=0;
        do{
            System.out.println("********** WELCOME TO PRANAV'S BANK *********");
            System.out.print("Choose \n1.For User Login\n2.For Admin Login\n3.Exit\n");
            sc=new Scanner(System.in);
            s=sc.nextInt();
            switch (s){
                case 1:
                        uLogin();
                        break;
                case 2: adLogin();
                        break;
                case 3: System.exit(0);
                        break;
                default:
                    System.out.println("Invalid Choice !!!!");
            }
        }while (s!=3);
    }

    public static void uLogin(){
        UserLogin u=new UserLogin();
        AccountOpening ac=new AccountOpening();
        TransferMoney t=new TransferMoney();
        System.out.println("********** WELCOME TO PRANAV'S BANK *********");
        System.out.println("\n1.For Creating Account");
        System.out.println("\n2.Check Bank Balance");
        System.out.println("\n3.Transfer Funds");
        System.out.println("\n4.Remove Account");
        System.out.println("\n5.Exit");
        System.out.print("\nChoose any one option:");
        int ch=sc.nextInt();
        switch (ch){
            case 1: ac.accountOpening();
                    break;
            case 2: u.displayBalance();
                    break;
            case 3: t.transferMoney();
                    break;
            case 4: u.removeAccount();
                    break;
            case 5: break;
            default:
                System.out.println("Invalid Choice !!!");
        }
    }

    public static void adLogin(){
        AdminLogin ad=new AdminLogin();
        Boolean b=ad.authentication();
        if(b==true){
            System.out.println("********** WELCOME TO PRANAV'S BANK *********");
            System.out.println("\n1.Display All Accounts");
            System.out.println("\n2.Exit");
            int ch=sc.nextInt();
            switch (ch){
                case 1: ad.displayAllAccount();
                        break;
                case 2: break;
                default:
                    System.out.println("Invalid Choice!!!");
            }
        }else
            System.out.println("Invalid Authentication!!!");



    }
}
