package com.uudaddy;

/*please test input/output by entering inputs in the box below.*/
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
 * Created by minjiexu on 1/19/18.
 *
 * Count multiple occurrences of letter in a sentence (ignore white spaces and special characters)
 * 
 * /Library/Java/JavaVirtualMachines/jdk1.8.0_121.jdk/Contents/Home/bin/java -Didea.launcher.port=7532
 * "-Didea.launcher.bin.path=/Applications/IntelliJ IDEA CE.app/Contents/bin" -Dfile.encoding=UTF-8
 * -classpath "/Library/Java/JavaVirtualMachines/jdk1.8.0_121.jdk/Contents/Home/jre/lib/charsets.jar
 * :/Library/Java/JavaVirtualMachines/jdk1.8.0_121.jdk/Contents/Home/jre/lib/deploy.jar
 * :/Library/Java/JavaVirtualMachines/jdk1.8.0_121.jdk/Contents/Home/jre/lib/ext/cldrdata.jar
 * :/Library/Java/JavaVirtualMachines/jdk1.8.0_121.jdk/Contents/Home/jre/lib/ext/dnsns.jar
 * ......
 * com.uudaddy.CountLettersInSentence
 Sophia likes Immanuel school
 'a' 2
 'e' 2
 'h' 2
 'i' 3
 'l' 3
 'm' 2
 'o' 3
 's' 3
 Spoede Shooting Stars won their first game of the season today :-)
 'a' 4
 'd' 2
 'e' 6
 'f' 2
 'g' 2
 'h' 3
 'i' 3
 'n' 3
 'o' 7
 'r' 3
 's' 7
 't' 6
 */
public class CountLettersInSentence {

    //class Solution
    //{
        public static void main(String args[] ) throws Exception {
            String thisLine = null;
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while ((thisLine = br.readLine()) != null) {
                countCharacters(thisLine);
            }
        }

        public static void countCharacters(String inputString)
        {
            HashMap<String, Integer>charactersCountMap = new HashMap<String, Integer>();

            for(int ii=0; ii<inputString.length(); ii++)
            {
                String currentChar = (String.valueOf(inputString.charAt(ii))).toLowerCase() ;
                if( isLetter(currentChar) )
                {
                    if(charactersCountMap.containsKey(currentChar))
                    {
                        int count0=charactersCountMap.get(currentChar);
                        charactersCountMap.put(currentChar, new Integer(count0+1));
                    }
                    else
                    {
                        charactersCountMap.put(currentChar, new Integer(1));
                    }
                }
            }

            for(Map.Entry<String, Integer> entry: charactersCountMap.entrySet())
            {
                String key=entry.getKey();
                Integer value=entry.getValue();
                if(Integer.valueOf(value)>1)
                {
                    System.out.println("'"+key+"' "+value);
                }
            }
        }

        public static boolean isLetter(String inputString)
        {
            if(inputString.compareToIgnoreCase("a")>=0 && inputString.compareToIgnoreCase("z")<=0)
            {
                return true;
            }

            return false;
        }
    }
