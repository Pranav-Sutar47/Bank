package bank;

import java.util.Scanner;
import java.sql.*;
import bank.Connectivity;

public class TransferMoney {

    String s,d;
    Scanner sc;
    boolean check(String s){
        for(char c:s.toCharArray()){
            if(Character.isDigit(c))
                return true;
        }
        return false;
    }

    void transferMoney(){
        byte flag=0;
        while (flag==0){
            System.out.print("Enter your account number:");
            sc=new Scanner(System.in);
            s=sc.next();
            boolean b=check(s);
            if(b==true && s.length()==10)
                flag=1;
        }
        flag=0;
        while (flag==0){
            System.out.print("Enter receiver account number:");
            d=sc.next();
            boolean b=check(d);
            if(b==true && d.length()==10)
                flag=1;
        }
        try{
            int mpin=0;
            String nm="";
            Connectivity c=new Connectivity();
            c.createConnection();
            System.out.print("Enter amount to transfer:");
            float amount=sc.nextFloat();
            float balance=0,temp=0;
            if(amount>0 && amount<=100000){
                c.ps=c.con.prepareStatement("SELECT Balance,Mpin from `info` WHERE AcNo=?");
                c.ps.setString(1,s);
                c.rs=c.ps.executeQuery();
                while (c.rs.next()){
                    balance=c.rs.getFloat("Balance");
                    mpin=c.rs.getInt("Mpin");
                }
                if(balance>amount){
                    c.ps=c.con.prepareStatement("SELECT Balance,Name from `info` WHERE AcNo=?");
                    c.ps.setString(1,d);
                    c.rs=c.ps.executeQuery();
                    while (c.rs.next()) {
                        temp = c.rs.getFloat("Balance");
                        nm=c.rs.getString("Name");
                    }
                    System.out.print("Are you Sure to Transfer Money to "+nm+" having Account Number="+d+":(Y/N)");
                    char ch=sc.next().charAt(0);

                    if(ch=='Y'||ch=='y'){
                        byte b=0;

                        while (b==0){
                        System.out.print("Enter MPIN:");
                        int pin=sc.nextInt();

                            if(pin==mpin){
                                b=1;
                                float t=temp+amount;
                                c.ps=c.con.prepareStatement("UPDATE info set Balance=? WHERE AcNo=?");
                                c.ps.setDouble(1,t);
                                c.ps.setString(2,d);
                                int i=c.ps.executeUpdate();
                                if(i==1){
                                    c.ps=c.con.prepareStatement("UPDATE info set Balance=? WHERE AcNo=?");
                                    t=balance-amount;
                                    c.ps.setDouble(1,t);
                                    c.ps.setString(2,s);
                                    int j=c.ps.executeUpdate();
                                    if(j==1){
                                        System.out.println("Transaction Successful!!!");
                                        c.ps=c.con.prepareStatement("SELECT Balance from `info` WHERE AcNo=?");
                                        c.ps.setString(1,s);
                                        c.rs=c.ps.executeQuery();
                                        while (c.rs.next())
                                            balance=c.rs.getFloat("Balance");
                                        System.out.println("Available balance is:"+balance);
                                        System.out.println("\nThanks for banking with us!!!");
                                    }
                                    else
                                        System.out.println("Error!!!");
                                }
                                else
                                    System.out.println("Error!!! Receiver Account number is invalid !!!!!\nMoney is not deducted!!!");
                            }else{
                                System.out.println("Incorrect MPIN\n1.For Try Again\n2.Exit");
                                int t=sc.nextInt();
                                switch (t){
                                    case 1: b=0;
                                            break;
                                    case 2: b=1;
                                            break;
                                    default:
                                        System.out.println("Invalid Choice");
                                        b=1;
                                }
                            }
                        }


                    }else {
                        System.out.println("Transaction Declined!!!");
                        c.closeConnection();
                    }
                }else{
                    System.out.println("Insufficient Balance!!!");
                }
            }else{
                System.out.println("You can't transfer 0 Rs or more than 100000 Rs at a time!!!");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
