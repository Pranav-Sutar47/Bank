package bank;

import bank.Connectivity;
import java.util.Scanner;

public class AdminLogin {
    Scanner sc;
    public boolean authentication(){
        System.out.println("********* AUTHENTICATION *********");
        System.out.print("Enter Username:");
        sc=new Scanner(System.in);
        String uname=sc.next();
        System.out.print("Ente Password:");
        String psw= sc.next();
        if(uname.equals("ADMIN")&& psw.equals("ADMIN"))
            return true;
        else
            return false;
    }

    public void displayAllAccount(){
        Connectivity c=new Connectivity();
        c.createConnection();
        try{
            c.ps=c.con.prepareStatement("SELECT NAME,AcNo,ACType FROM `info`");
            c.rs=c.ps.executeQuery();
            System.out.println("NAME\tAccount Number\tAccount Type");
            while (c.rs.next())
                System.out.println(c.rs.getString("Name")+"\t"+c.rs.getString("AcNo")+"\t\t"+c.rs.getString("AcType"));
            c.closeConnection();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
