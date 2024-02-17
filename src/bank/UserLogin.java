package bank;

import java.util.Scanner;
import bank.Connectivity;
import bank.TransferMoney;
public class UserLogin {
    public void displayBalance(){
        Connectivity c=new Connectivity();
        c.createConnection();
        TransferMoney t=new TransferMoney();
        double amount=0;
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter Mpin:");
        int i=sc.nextInt();

        try{
            c.ps=c.con.prepareStatement("SELECT Balance FROM `info` WHERE Mpin=?");
            c.ps.setInt(1,i);
            c.rs=c.ps.executeQuery();
            byte flag=0;
            while (c.rs.next()){
                flag++;
                amount=c.rs.getDouble("Balance");
            }
            if(flag==1)
                System.out.println("Your account balance is:"+amount+" Rs");
            else{
                String mn="";
                byte b=0;
                while (b==0){
                    System.out.print("Enter Mobile Number:");
                    mn=sc.next();
                    Boolean ba=t.check(mn);
                    if(ba==true && mn.length()==10)
                        b=1;
                }
                c.ps=c.con.prepareStatement("SELECT Balance FROM `info` WHERE Mpin=? AND MobileNo=?");
                c.ps.setInt(1,i);
                c.ps.setString(2,mn);
                c.rs=c.ps.executeQuery();
                while (c.rs.next())
                    amount=c.rs.getDouble("Balance");
                System.out.println("Your account balance is:"+amount+" Rs");
            }
            c.closeConnection();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void removeAccount(){
        Connectivity c=new Connectivity();
        c.createConnection();
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter Account number:");
        String ac=sc.next();
        try{
            String nm="";
            double balance=0;
            int mpin=0;
            c.ps=c.con.prepareStatement("SELECT Name,Balance,Mpin FROM `info` WHERE AcNo=?");
            c.ps.setString(1,ac);

            c.rs=c.ps.executeQuery();
            while (c.rs.next()){
                nm=c.rs.getString("Name");
                balance=c.rs.getDouble("Balance");
                mpin=c.rs.getInt("Mpin");
            }
            System.out.println("Your Name is:"+nm+" Account Balance is:"+balance);
            System.out.println("Please transfer your funds to your other account or Friend's Account before removing account!!!");
            System.out.print("Are you sure to Transfer your funds:(Y|N)");
            char ch=sc.next().charAt(0);
            if(ch=='Y'||ch=='y'){
                TransferMoney t=new TransferMoney();
                t.transferMoney();
                byte f=1;
                while (f==1){
                    System.out.print("Enter MPIN:");
                    int temp=sc.nextInt();
                    if(mpin==temp){
                        f=0;
                        c.ps=c.con.prepareStatement("DELETE FROM `info` WHERE Mpin=? AND AcNo=?");
                        c.ps.setInt(1,temp);
                        c.ps.setString(2,ac);
                        int i=c.ps.executeUpdate();
                        if(i==1)
                            System.out.println("Your account Deleted Successfully!!!");
                        else
                            System.out.println("Error while Deleting Account");
                    }else {
                        System.out.println("Incorrect MPIN!!!!");
                        System.out.print("1.For Try Again\n2.For Exit\n");
                        int choice = sc.nextInt();
                        switch (choice) {
                            case 1:
                                f = 0;
                                break;
                            case 2:
                                f = 0;
                                break;
                            default:
                                System.out.println("Invalid Choice");
                        }
                    }
                }

            }else
                System.out.println("Account cannot be deleted as it contains money!!!");

        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        c.closeConnection();
    }
}
