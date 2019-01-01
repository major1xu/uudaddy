package com.uudaddy.accessLog;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by minjiexu on 11/19/18.
 * refer to
 * https://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
 * 
 */
public class Parser {
    public static void main(String[] args) {

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
                System.out.println("Country.length: " + country.length );
                System.out.println("Log [time= " + country[0] + " , IP=" + country[1] + "]");
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
}
