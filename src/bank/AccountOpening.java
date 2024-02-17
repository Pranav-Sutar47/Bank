package bank;
import java.util.Scanner;
import bank.Connectivity;
import java.sql.*;

public class AccountOpening {
    String nm,ac,adno,mn;
    int mpin;
    Scanner sc;
    float balance;
    boolean check(String s){
        for(char c:s.toCharArray()){
            if(Character.isDigit(c))
                return true;
        }
        return false;
    }

    public void accountOpening(){
        System.out.println("***** WELCOME TO PRANAV'S BANK *****");
        sc=new Scanner(System.in);
        int i=0;
        byte flag=1;
        while(flag==1){
            System.out.print("Choose Account Type\n1.For Saving\n2.For Current\n3.Exit\n");
            i=sc.nextInt();
            if(i==1 || i==2 ||i==3)
                flag=0;
        }
        switch (i) {
            case 1:
                ac="Saving";
                break;
            case 2: ac="Current";
                break;
        }
        if(i==3)
            System.out.println("Try Again !!!");
        else{
            System.out.print("Enter Name:");
            nm=sc.next();
            flag=1;
            while(flag==1){
                System.out.print("Enter Adhar Number:");
                adno=sc.next();
                Boolean b=check(adno);
                if((adno.length()==12) && b==true){
                    flag=0;
                    break;
                }
                System.out.println("Invalid Adhar Number !!!!");
            }
            flag=1;
            while(flag==1){
                System.out.print("Enter Mobile Number:");
                mn=sc.next();
                Boolean b=check(mn);
                if((mn.length()==10)&& b==true)
                    flag=0;
            }
           
            balance=50;
            insert();
        }
    }

    void insert(){
        try {
            Connectivity c=new Connectivity();
            c.createConnection();
            c.ps=c.con.prepareStatement("INSERT INTO `info` VALUES (DEFAULT,?,?,?,?,?,DEFAULT,?)");

            c.ps.setString(1, ac);
            c.ps.setString(2, nm);
            c.ps.setString(3, adno);
            c.ps.setDouble(4, balance);
            c.ps.setString(5, mn);
            c.ps.setNull(6, Types.INTEGER);
            int i=c.ps.executeUpdate();
            if(i==1){
                System.out.println("Thanks for creating Account in our Bank!!!");
                System.out.println("!!!!!!!We will credit Rs.50 in your Account!!!!!!!\n*** Congrulations ***");
                c.ps=c.con.prepareStatement("SELECT `AcNo` from `info` WHERE AdharNo=?");
                c.ps.setString(1, adno);
                c.rs=c.ps.executeQuery();
                String ano="";
                while(c.rs.next())
                    ano=c.rs.getString("AcNo");
                System.out.println("Carefully note down your account number is:"+ano);
                System.out.println("**********************************************");
                System.out.println("Guidelines for MPIN\nIt contain only 4 digit\n");

                byte flag=1;
                while (flag==1) {
                    System.out.print("Enter MPIN:");
                    mpin = sc.nextInt();
                    System.out.print("Confirm MPIN:");
                    int t = sc.nextInt();
                    if (mpin == t) {
                        flag = 0;
                        break;
                    }
                    System.out.println("MPIN are not same!!!!");
                }
                c.ps=c.con.prepareStatement("UPDATE `info` set Mpin=? WHERE AcNo=?");
                c.ps.setInt(1,mpin);
                c.ps.setString(2,ano);
                int j=c.ps.executeUpdate();
                if(j==1)
                    System.out.println("MPIN Set Successfully!!!!!");
                else
                    System.out.println("Error while setting MPIN!!!!");
            }
            c.closeConnection();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
