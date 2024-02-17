package bank;

import java.sql.*;

public class Connectivity {
    public Connection con;
    public PreparedStatement ps;
    public ResultSet rs;

    void createConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
            //ps=con.prepareStatement("SELECT * FROM info");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    void closeConnection(){
        try {
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
