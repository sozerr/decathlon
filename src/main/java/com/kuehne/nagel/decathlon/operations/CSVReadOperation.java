package com.kuehne.nagel.decathlon.operations;

import com.kuehne.nagel.decathlon.model.Athlete;
import com.kuehne.nagel.decathlon.calculation.EventReferences;
import com.kuehne.nagel.decathlon.utils.DecathlonException;
import com.kuehne.nagel.decathlon.utils.StringUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVReadOperation {

    private static final String SEPARATOR_CSV = ";";

    public static List<Athlete> readFromFile(String filePath) {

        String fileName = filePath == null ? "results.csv" : filePath;

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            return stream.map(line -> {
                        if (StringUtil.isEmpty(line)) {
                            return null;
                        }
                        String[] values = line.split(SEPARATOR_CSV);

                        Athlete athlete = new Athlete(values[0]);

                        athlete.putResult(EventReferences.M100.getXmlLabel(), values[1]);
                        athlete.putResult(EventReferences.LONG_JUMP.getXmlLabel(), values[2]);
                        athlete.putResult(EventReferences.SHOT_PUT.getXmlLabel(), values[3]);
                        athlete.putResult(EventReferences.HIGH_JUMP.getXmlLabel(), values[4]);
                        athlete.putResult(EventReferences.M400.getXmlLabel(), values[5]);
                        athlete.putResult(EventReferences.M110_HURDLES.getXmlLabel(), values[6]);
                        athlete.putResult(EventReferences.DISCUS_THROW.getXmlLabel(), values[7]);
                        athlete.putResult(EventReferences.POLE_VAULT.getXmlLabel(), values[8]);
                        athlete.putResult(EventReferences.JAVELIN_THROW.getXmlLabel(), values[9]);
                        athlete.putResult(EventReferences.M1500.getXmlLabel(), values[10]);

                        return athlete;
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new DecathlonException("Unhandled exception occured!", e);
        }
    }
}
