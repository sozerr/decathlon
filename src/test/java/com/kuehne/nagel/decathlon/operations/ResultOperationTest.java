package com.kuehne.nagel.decathlon.operations;

import com.kuehne.nagel.decathlon.model.Athlete;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ResultOperationTest {

    @Test
    void athletes_order_control() {

        // Arrange
        Athlete usain = new Athlete("usain" );
        usain.putScore("100m", 3);

        Athlete damain = new Athlete("damain" );
        damain.putScore("100m", 4);

        Athlete mike = new Athlete("mike" );
        mike.putScore("100m", 1 );

        Athlete simon = new Athlete("simon" );
        simon.putScore("100m", 2);

        List<Athlete> athletes = new ArrayList<>();
        athletes.add(usain);
        athletes.add(damain);
        athletes.add(mike);
        athletes.add(simon);

        // Act
        List<Athlete> orederedAthletes =  ResultOperation.orderAthletes(athletes);

        // Assert
        Assertions.assertEquals(orederedAthletes.get(0).getName(), "damain");
        Assertions.assertEquals(orederedAthletes.get(1).getName(), "usain");
        Assertions.assertEquals(orederedAthletes.get(2).getName(), "simon");
        Assertions.assertEquals(orederedAthletes.get(3).getName(), "mike");

    }


    @Test
    void athletes_place_control_same_point() {

        // Arrange
        Athlete usain = new Athlete("usain" );
        usain.putScore("100m", 4);

        Athlete damain = new Athlete("damain" );
        damain.putScore("100m", 4);

        Athlete mike = new Athlete("mike" );
        mike.putScore("100m", 1 );

        Athlete simon = new Athlete("simon" );
        simon.putScore("100m" ,1);

        List<Athlete> athletes = new ArrayList<>();
        athletes.add(usain);
        athletes.add(damain);
        athletes.add(mike);
        athletes.add(simon);

        // Act
        List<Athlete> orederedAthletes =  ResultOperation.orderAthletes(athletes);
        ResultOperation.calculatePlaces(orederedAthletes);
        // Assert
        Assertions.assertEquals(orederedAthletes.get(0).getPlace(), "1-2");
        Assertions.assertEquals(orederedAthletes.get(1).getPlace(), "1-2");
        Assertions.assertEquals(orederedAthletes.get(2).getPlace(), "3-4");
        Assertions.assertEquals(orederedAthletes.get(3).getPlace(), "3-4");
    }


    @Test
    void athletes_place_control_all_same_point() {

        // Arrange
        Athlete usain = new Athlete("usain" );
        usain.putScore("100m", 4);

        Athlete damain = new Athlete("damain" );
        damain.putScore("100m", 4);

        Athlete mike = new Athlete("mike" );
        mike.putScore("100m", 4);

        Athlete simon = new Athlete("simon" );
        simon.putScore("100m", 4);

        List<Athlete> athletes = new ArrayList<>();
        athletes.add(usain);
        athletes.add(damain);
        athletes.add(mike);
        athletes.add(simon);

        // Act
        List<Athlete> orederedAthletes =  ResultOperation.orderAthletes(athletes);
        ResultOperation.calculatePlaces(orederedAthletes);
        // Assert
        Assertions.assertEquals(orederedAthletes.get(0).getPlace(), "1-2-3-4");
        Assertions.assertEquals(orederedAthletes.get(1).getPlace(), "1-2-3-4");
        Assertions.assertEquals(orederedAthletes.get(2).getPlace(), "1-2-3-4");
        Assertions.assertEquals(orederedAthletes.get(3).getPlace(), "1-2-3-4");
    }


    @Test
    void athletes_place_control_all_different_point() {

        // Arrange
        Athlete usain = new Athlete("usain" );
        usain.putScore("100m", 1 );

        Athlete damain = new Athlete("damain" );
        damain.putScore("100m", 4);

        Athlete mike = new Athlete("mike" );
        mike.putScore("100m", 3);

        Athlete simon = new Athlete("simon" );
        simon.putScore("100m", 7);

        List<Athlete> athletes = new ArrayList<>();
        athletes.add(usain);
        athletes.add(damain);
        athletes.add(mike);
        athletes.add(simon);

        // Act
        List<Athlete> orederedAthletes =  ResultOperation.orderAthletes(athletes);
        ResultOperation.calculatePlaces(orederedAthletes);
        // Assert
        Assertions.assertEquals(orederedAthletes.get(0).getPlace(), "1");
        Assertions.assertEquals(orederedAthletes.get(1).getPlace(), "2");
        Assertions.assertEquals(orederedAthletes.get(2).getPlace(), "3");
        Assertions.assertEquals(orederedAthletes.get(3).getPlace(), "4");
    }
}