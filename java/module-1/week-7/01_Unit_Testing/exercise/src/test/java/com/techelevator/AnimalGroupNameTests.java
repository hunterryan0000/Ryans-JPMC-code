package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AnimalGroupNameTests {

    AnimalGroupName animalGroupName = new AnimalGroupName();


    @Test
    public void testingForHerdsInTheList(){
        Assert.assertEquals("Tower", animalGroupName.getHerd("giraffe"));
        Assert.assertEquals("Crash", animalGroupName.getHerd("RHINO"));
        Assert.assertEquals("Float", animalGroupName.getHerd("cRocODIle"));

    }

    @Test
    public void testingForHerdsNotInTheList(){
        Assert.assertEquals("unknown", animalGroupName.getHerd(""));
        Assert.assertEquals("unknown", animalGroupName.getHerd("rat"));

    }
}
