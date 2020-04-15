package test.java;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import main.java.Dataframe;

public class DataframeTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testDataframeString() {
		new Dataframe("t");
	}

}
