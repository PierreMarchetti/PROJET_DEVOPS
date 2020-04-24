package test.java;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import main.java.Dataframe;

public class DataframeTest {
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testDataframeFile() {
		 String filepath = "src/test/resources/testfile.csv";
		 
		 Dataframe dataframe = new Dataframe(filepath);
		 
		 ArrayList<ArrayList<?>> dataframeBody = new ArrayList<ArrayList<?>>();
			
		ArrayList<Integer> column1 = new ArrayList<Integer>();
		column1.add(1);
		column1.add(null);
		column1.add(3);

		ArrayList<Double> column2 = new ArrayList<Double>();
		column2.add(2.0);
		column2.add(3.6);
		column2.add(5.9);
		
		ArrayList<String> column3 = new ArrayList<String>();
		column3.add("aa");
		column3.add("bb");
		column3.add("cc");
		
		dataframeBody.add(column1);
		dataframeBody.add(column2);
		dataframeBody.add(column3);
		
		assertEquals(dataframeBody, dataframe.getDataframeBody());
	}

	@Test(expected = Exception.class)
	public void testDataframeFileTypeError() {
		 String filepath = "src/test/resources/testfileError.csv";
		 Dataframe dataframe = new Dataframe(filepath);
	}
	
	@Test
	public void testDataframeTable() {
		Object[][] table = {{1,2.0,"aa"},{null,3.6,"bb"},{3,5.9,"cc"}};
		Dataframe dataframe =  new Dataframe(table);
		
		ArrayList<ArrayList<?>> dataframeBody = new ArrayList<ArrayList<?>>();
		
		ArrayList<Integer> column1 = new ArrayList<Integer>();
		column1.add(1);
		column1.add(null);
		column1.add(3);

		ArrayList<Double> column2 = new ArrayList<Double>();
		column2.add(2.0);
		column2.add(3.6);
		column2.add(5.9);
		
		ArrayList<String> column3 = new ArrayList<String>();
		column3.add("aa");
		column3.add("bb");
		column3.add("cc");
		
		dataframeBody.add(column1);
		dataframeBody.add(column2);
		dataframeBody.add(column3);
		
		assertEquals(dataframeBody, dataframe.getDataframeBody());
		
//		ArrayList<String> indexes = new ArrayList<String>();
//		indexes.add("1");
//		indexes.add("2");
//		indexes.add("3");
//		assertEquals(indexes, dataframe.getIndexList());
//		
//		ArrayList<String> labels = new ArrayList<String>();
//		indexes.add("a");
//		indexes.add("b");
//		indexes.add("c");
//		assertEquals(labels, dataframe.getLabelList());		
	}
	
	@Test(expected = Exception.class)
	public void testDataframeTableTypeError() {
		Object[][] table = {{1},{"this string is not an int"}};
		Dataframe dataframe =  new Dataframe(table);
	}

	@Test
	public void testDisplayDataframe() {
		Object[][] table = {{1,2.0,"aa"},{null,3.6,"bb"},{3,5.9,"cc"}};
		Dataframe dataframe =  new Dataframe(table);
		
		String expected = "  1 2 3\na 1 2.0 aa\nb NaN 3.6 bb\n3 5.9 cc";
		String actual = dataframe.displayDataframe();
		assertEquals(expected, actual);
	}

	@Test
	public void testHead() {
		Object[][] table = {{1,2.0,"aa"},{null,3.6,"bb"},{3,5.9,"cc"}};
		Dataframe dataframe =  new Dataframe(table);
		
		String expected = "  1 2 3\na 1 2.0 aa\nb NaN 3.6 bb";
		String actual = dataframe.head();
		assertEquals(expected, actual);
	}

	@Test
	public void testTail() {
		Object[][] table = {{1,2.0,"aa"},{null,3.6,"bb"},{3,5.9,"cc"}};
		Dataframe dataframe =  new Dataframe(table);
		
		String expected = "  1 2 3\nb NaN 3.6 bb\n3 5.9 cc";
		String actual = dataframe.tail();
		assertEquals(expected, actual);
	}

	@Test
	public void testIloc() {
		Object[][] table = {{1,2.0,"aa"},{null,3.6,"bb"},{3,5.9,"cc"}};
		Dataframe dataframe =  new Dataframe(table);
		int[] indexes = {0,1};
		Dataframe actual = dataframe.iloc(indexes);
		
		Object[][] tableExpected = {{1,2.0,"aa"},{3,5.9,"cc"}};

		Dataframe expected =  new Dataframe(tableExpected);
		
		assertEquals(expected, actual);

	}

	@Test
	public void testLoc() {
		Object[][] table = {{1,2.0,"aa"},{null,3.6,"bb"},{3,5.9,"cc"}};
		Dataframe dataframe =  new Dataframe(table);
		String[] labels = {"a","b"};
		Dataframe actual = dataframe.loc(labels);
		
		Object[][] tableExpected = {{1,2.0},{null,3.6},{3,5.9}};

		Dataframe expected =  new Dataframe(tableExpected);
		
		assertEquals(expected, actual);
	}

	@Test
	public void testSum() {
		Object[][] table = {{1,2.0,"aa"},{null,3.6,"bb"},{3,5.9,"cc"}};
		Dataframe dataframe =  new Dataframe(table);
		
		assertEquals(4,dataframe.sum(0));
	}
	
	public void testSumNonNumber() {
		Object[][] table = {{1,2.0,"aa"},{null,3.6,"bb"},{3,5.9,"cc"}};
		Dataframe dataframe =  new Dataframe(table);
		
		assertEquals(0,dataframe.sum(2));
	}

	@Test
	public void testMean() {
		Object[][] table = {{1,2.0,"aa"},{null,3.6,"bb"},{3,5.9,"cc"}};
		Dataframe dataframe =  new Dataframe(table);
		
		assertEquals(11.5/3,dataframe.mean(1));
	}
	
	@Test
	public void testMeanNonNumber() {
		Object[][] table = {{1,2.0,"aa"},{null,3.6,"bb"},{3,5.9,"cc"}};
		Dataframe dataframe =  new Dataframe(table);
		
		assertEquals(0,dataframe.mean(2));
	}

	@Test
	public void testMax() {
		Object[][] table = {{1,2.0,"aa"},{null,3.6,"bb"},{3,5.9,"cc"}};
		Dataframe dataframe =  new Dataframe(table);
		
		assertEquals(3,dataframe.max(0));
	}
	
	@Test
	public void testMaxNonNumber() {
		Object[][] table = {{1,2.0,"aa"},{null,3.6,"bb"},{3,5.9,"cc"}};
		Dataframe dataframe =  new Dataframe(table);
		
		assertEquals(0,dataframe.max(2));
	}
	

	@Test
	public void testMin() {
		Object[][] table = {{1,2.0,"aa"},{null,3.6,"bb"},{3,5.9,"cc"}};
		Dataframe dataframe =  new Dataframe(table);
		
		assertEquals(3,dataframe.min(1));
	}
	
	@Test
	public void testMinNonNumber() {
		Object[][] table = {{1,2.0,"aa"},{null,3.6,"bb"},{3,5.9,"cc"}};
		Dataframe dataframe =  new Dataframe(table);
		
		assertEquals(0,dataframe.min(2));
	}

}
