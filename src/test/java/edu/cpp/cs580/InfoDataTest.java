package edu.cpp.cs580;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.cpp.cs580.data.InfoData;
import junit.framework.Assert;

public class InfoDataTest {
	@Test
	public void test() {
		InfoData testData = new InfoData();
        String url = "http://www.tripadvisor.com/Tourism-g32655-Los_Angeles_California-Vacations.html";
        testData.getFromWeb(url);
        Assert.assertNotNull(testData);
        Assert.assertTrue(testData.getTitle().contains("Los Angeles"));
        Assert.assertTrue(testData.getContent().contains("Griffith Observatory"));
	}

}
