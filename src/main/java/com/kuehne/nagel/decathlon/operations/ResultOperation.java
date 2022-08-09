package com.kuehne.nagel.decathlon.operations;

import com.kuehne.nagel.decathlon.model.Athlete;
import com.kuehne.nagel.decathlon.utils.DecathlonException;
import com.kuehne.nagel.decathlon.utils.XmlUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ResultOperation {

    public static void printResult(List<Athlete> athletes, String outFileName) {
        List<Athlete> orderedAthletes = orderAthletes(athletes);
        calculatePlaces(orderedAthletes);

        outFileName = outFileName == null ? "out.xml" : outFileName;
        print(orderedAthletes, outFileName);
    }

    public static void print(List<Athlete> orderedAthletes, String outFileName) {

        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = null;
            documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            // root element
            Element root = document.createElement("competition");
            document.appendChild(root);

            for (Athlete athlete : orderedAthletes) {
                // athlete element
                Element athleteElement = document.createElement("athlete");
                root.appendChild(athleteElement);

                XmlUtil.addAttribute(document, athleteElement, "name", athlete.getName());
                XmlUtil.addAttribute(document, athleteElement, "place", athlete.getPlace());
                XmlUtil.addAttribute(document, athleteElement, "score", String.valueOf(athlete.getScore()));


                Element events = document.createElement("events");
                athleteElement.appendChild(events);

                for (String eventKey : athlete.getEventResultMap().keySet()) {

                    Element event = document.createElement(eventKey);
                    events.appendChild(event);

                    XmlUtil.addElement(document, event, "result", athlete.getEventResultMap().get(eventKey));
                    XmlUtil.addElement(document, event, "score", String.valueOf(athlete.getEventScoreMap().get(eventKey)));
                }

            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(outFileName));

            transformer.transform(domSource, streamResult);
        } catch (Exception e) {
            throw new DecathlonException("An error occured in xml write " + e.getMessage(), e);
        }
    }

    public static void calculatePlaces(List<Athlete> orderedAthletes) {

        int place = 1;
        Athlete previousAthlete = null;
        for (Athlete athlete : orderedAthletes) {
            if (place == 1) {
                previousAthlete = athlete;
                athlete.setPlace(String.valueOf(place));
            } else {
                athlete.setPrevious(previousAthlete);
                previousAthlete = athlete;
                athlete.setPlace(String.valueOf(place));
                if (athlete.getScore().equals(athlete.getPrevious().getScore())) {
                    recalculatePlaces(athlete, String.valueOf(place));
                }
            }
            place++;
        }

    }

    private static String recalculatePlaces(Athlete athlete, String place) {
        if (athlete.getPrevious() != null && athlete.getScore().equals(athlete.getPrevious().getScore())) {
            athlete.setPlace(recalculatePlaces(athlete.getPrevious(), place));
        } else {
            athlete.setPlace(athlete.getPlace() + "-" + place);
        }
        return athlete.getPlace();
    }

    public static List<Athlete> orderAthletes(List<Athlete> athletes) {
        return athletes.stream()
                .peek(Athlete::calculateTotalScore)
                .sorted(Comparator.comparing(Athlete::getScore).reversed())
                .collect(Collectors.toList());
    }

}
