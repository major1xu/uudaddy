package com.uudaddy.accessLog;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;


/**
 * Created by minjiexu on 11/19/18.
 * refer to
 * https://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
 * 
 */
public class LogParser {
    public static void main(String[] args) {
        connectToDB();


        String csvFile = "/Users/minjiexu/Downloads/Java_MySQL_Test/access_0.log";
        BufferedReader br = null;
        String line = "";
        // https://stackoverflow.com/questions/21524642/splitting-string-with-pipe-character
        // | is a metacharacter in regex. You'd need to escape it:
        String cvsSplitBy = "\\|";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                System.out.println("line: " + line);
                String[] country = line.split(cvsSplitBy);
                //System.out.println("Country.length: " + country.length );
                System.out.println("startDate: " + country[0]);
                System.out.println("IP: " + country[1]);
                System.out.println("method: " + country[2]);
                System.out.println( "statusCode: " + country[3]);
                System.out.println("browser: " + country[4]);
                System.out.println();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private static void connectToDB() {
        // https://www.javatpoint.com/example-to-connect-to-the-mysql-database
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/turtle","turtledev","!doris2MJ");
//here sonoo is database name, root is username and password
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from AccessLog");
            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }
}
