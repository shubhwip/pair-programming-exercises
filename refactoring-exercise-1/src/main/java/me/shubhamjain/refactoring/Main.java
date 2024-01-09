package me.shubhamjain.refactoring;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;

/*
* Given a csv file with data about pull requests in our git projects we want to print out
* the names of the two developers who raised the most pull requests.
*
* The program below does the job but it is poorly written and we'd like better code in production. :)
*
* Exercise: Refactor the following code in Java 8 so it can meet your personal standard
* on how production code should look like. We do not expect this exercise to take longer than 45min.
*
* We might want to add more statistics/reports in the future and your solution should
* encourage code reusability.
*
*/
public class Main {

    public static void main(String[] args) {

        String csvFile = "pull_requests_data.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            ClassLoader classLoader = Main.class.getClassLoader();
            File file = new File(classLoader.getResource(csvFile).getFile());
            HashMap number = new HashMap();

            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {

                String[] a = line.split(cvsSplitBy);

                Long count = (Long) number.get(a[3]);
                if (count == null) {
                    number.put(a[3], 1l);
                } else {
                    number.put(a[3], (Long) number.get(a[3]) + 1);
                }

            }

            String user1 = null;
            String user2 = null;

            Long count1 = 0L;
            Long count2 = 0L;

            Iterator i = number.keySet().iterator();
            while (i.hasNext()) {

                String name = (String) i.next();

                if (user1 == null) {
                    user1 = name;
                    count1 = (Long) number.get(name);
                } else if (count1 < (Long) number.get(name)) {
                    user2 = user1;
                    count2 = count1;
                    user1 = name;
                    count1 = (Long) number.get(name);
                } else if (user2 == null) {
                    user2 = name;
                    count2 = (Long) number.get(name);
                } else if (count2 < (Long) number.get(name)) {
                    user2 = name;
                    count2 = (Long) number.get(name);
                } else {}

            }

            System.out.println(user1);
            System.out.println(user2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
