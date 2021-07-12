package com.hocs.test.pages.platform;

import au.com.bytecode.opencsv.CSVReader;
import java.io.FileReader;
import java.util.Arrays;

public class CSVExtractReader {

    public void main() throws Exception {
        getHeaders();
       // getSpecificCase("MIN/0120834/20");
    }

    @SuppressWarnings("resource")
    public static void getHeaders() throws Exception {
        //Build reader instance
        //Read data.csv
        //Default seperator is comma
        //Default quote character is double quote
        //Line value is starts from 0
        CSVReader reader = new CSVReader(new FileReader("C:\\Users\\Cameron.Page\\IdeaProjects\\hocs-serenity-automation\\src\\test\\resources"
                + "\\documents\\min-case_data-2020-11-11 (1).csv"), ',' , '"' , 0);

        //Read CSV line by line and use the string array as you want
        System.out.println(Arrays.toString(reader.readNext()));
    }

    public static void getSpecificCase(String reference) throws Exception {

        CSVReader reader = new CSVReader(new FileReader("C:\\Users\\Cameron.Page\\IdeaProjects\\hocs-serenity-automation\\src\\test\\resources"
                + "\\documents\\min-case_data-2020-11-11 (1).csv"), ',' , '"' , 1);

        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            if (Arrays.toString(nextLine).contains(reference)) {
                System.out.println(Arrays.toString(nextLine));
            }
        }
    }
}