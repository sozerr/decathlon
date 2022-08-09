package com.kuehne.nagel.decathlon;


import com.kuehne.nagel.decathlon.model.Athlete;
import com.kuehne.nagel.decathlon.operations.CSVReadOperation;
import com.kuehne.nagel.decathlon.operations.CalculateOperation;
import com.kuehne.nagel.decathlon.operations.ResultOperation;

import java.util.List;

public class DecathlonApplication {

    public static void main(String[] args) {

        System.out.println("---args---");
        for (String arg : args) {
            System.out.println(arg);
        }
        String inputFileName = null, outputFileName = null;
        if (args != null && args.length == 2) {
            inputFileName = args[0];
            outputFileName = args[1];
        }


        System.out.println("DecathlonApplication Started");


        System.out.println("CSVReadOperation Started");
        List<Athlete> athletes = CSVReadOperation.readFromFile(inputFileName);
        System.out.println("CSVReadOperation Finished");


        CalculateOperation calculateOperation = new CalculateOperation();

        System.out.println("CalculateOperation Started");
        calculateOperation.calculateAllCompetitionScore(athletes);
        System.out.println("CalculateOperation Finished");


        System.out.println("ResultOperation Started");
        ResultOperation.printResult(athletes, outputFileName);
        System.out.println("ResultOperation Finished");


    }

}
