package test.java;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import main.java.Dataframe;

public class DataframeTest {
	@Test
	public void testDataframeFile() throws Exception {
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
	public void testDataframeFileTypeError() throws Exception {
		String filepath = "src/test/resources/testfileError.csv";

		Dataframe dataframe = new Dataframe(filepath);
	}

	@Test
	public void testDataframeTable() throws Exception {
		Object[][] table = { { 1, 2.0, "aa" }, { null, 3.6, "bb" }, { 3, 5.9, "cc" } };
		Dataframe dataframe = new Dataframe(table);

		ArrayList<ArrayList<Object>> dataframeBody = new ArrayList<ArrayList<Object>>();

		ArrayList<Object> column1 = new ArrayList<Object>();
		column1.add(1);
		column1.add(null);
		column1.add(3);

		ArrayList<Object> column2 = new ArrayList<Object>();
		column2.add(2.0);
		column2.add(3.6);
		column2.add(5.9);

		ArrayList<Object> column3 = new ArrayList<Object>();
		column3.add("aa");
		column3.add("bb");
		column3.add("cc");

		dataframeBody.add(column1);
		dataframeBody.add(column2);
		dataframeBody.add(column3);

		assertEquals(dataframeBody, dataframe.getDataframeBody());
	}

	@Test(expected = Exception.class)
	public void testDataframeTableTypeError() throws Exception {
		Object[][] table = { { 1 }, { "this string is not an int" } };
		Dataframe dataframe = new Dataframe(table);
	}

	@Test
	public void testDisplayDataframe() throws Exception {
		Object[][] table = { { 1, 2.0, "aa" }, { null, 3.6, "bb" }, { 3, 5.9, "cc" } };
		Dataframe dataframe = new Dataframe(table);

		String expected = "  1 2 3 \na 1 2.0 aa \nb NaN 3.6 bb \nc 3 5.9 cc \n";
		String actual = dataframe.displayDataframe();
		assertEquals(expected, actual);
	}

	@Test
	public void testHead() throws Exception {
		Object[][] table = { { 1, 2.0, "aa" }, { null, 3.6, "bb" }, { 3, 5.9, "cc" } };
		Dataframe dataframe = new Dataframe(table);

		String expected = "  1 2 3 \na 1 2.0 aa \nb NaN 3.6 bb \n";
		String actual = dataframe.head();
		assertEquals(expected, actual);
	}

	@Test
	public void testTail() throws Exception {
		Object[][] table = { { 1, 2.0, "aa" }, { null, 3.6, "bb" }, { 3, 5.9, "cc" } };
		Dataframe dataframe = new Dataframe(table);

		String expected = "  1 2 3 \nb NaN 3.6 bb \nc 3 5.9 cc \n";
		String actual = dataframe.tail();
		assertEquals(expected, actual);
	}

	@Test
	public void testIloc() throws Exception {
		Object[][] table = { { 1, 2.0, "aa" }, { null, 3.6, "bb" }, { 3, 5.9, "cc" } };
		Dataframe dataframe = new Dataframe(table);
		int[] indexes = { 0, 2 };
		Dataframe actual = dataframe.iloc(indexes);

		Object[][] tableExpected = { { 1, 2.0, "aa" }, { 3, 5.9, "cc" } };

		Dataframe expected = new Dataframe(tableExpected);

		assertEquals(expected.getDataframeBody(), actual.getDataframeBody());

	}

	@Test
	public void testLoc() throws Exception {
		Object[][] table = { { 1, 2.0, "aa" }, { null, 3.6, "bb" }, { 3, 5.9, "cc" } };
		Dataframe dataframe = new Dataframe(table);
		int[] labels = { 0, 1 };
		Dataframe actual = dataframe.loc(labels);

		Object[][] tableExpected = { { 1, 2.0 }, { null, 3.6 }, { 3, 5.9 } };

		Dataframe expected = new Dataframe(tableExpected);

		assertEquals(expected.getDataframeBody(), actual.getDataframeBody());
	}

	@Test
	public void testSum() throws Exception {
		Object[][] table = { { 1, 2.0, "aa" }, { null, 3.6, "bb" }, { 3, 5.9, "cc" } };
		Dataframe dataframe = new Dataframe(table);

		assert (new BigDecimal(4).compareTo(dataframe.sum(0)) == 0);
	}

	@Test
	public void testSumNonNumber() throws Exception {
		Object[][] table = { { 1, 2.0, "aa" }, { null, 3.6, "bb" }, { 3, 5.9, "cc" } };
		Dataframe dataframe = new Dataframe(table);

		assert (new BigDecimal(0).compareTo(dataframe.sum(2)) == 0);
	}

	@Test
	public void testMean() throws Exception {
		Object[][] table = { { 1, 2.0, "aa" }, { null, 3.6, "bb" }, { 3, 5.9, "cc" } };
		Dataframe dataframe = new Dataframe(table);

		assert (new BigDecimal(11.5).divide(new BigDecimal(3), 10, RoundingMode.HALF_UP)
				.compareTo(dataframe.mean(1)) == 0);
	}

	@Test
	public void testMeanNonNumber() throws Exception {
		Object[][] table = { { 1, 2.0, "aa" }, { null, 3.6, "bb" }, { 3, 5.9, "cc" } };
		Dataframe dataframe = new Dataframe(table);

		assert (new BigDecimal(0).compareTo(dataframe.mean(2)) == 0);
	}

	@Test
	public void testMax() throws Exception {
		Object[][] table = { { 1, 2.0, "aa" }, { null, 3.6, "bb" }, { 3, 5.9, "cc" } };
		Dataframe dataframe = new Dataframe(table);

		assert (new BigDecimal(3).compareTo(dataframe.max(0)) == 0);
	}

	@Test
	public void testMaxNonNumber() throws Exception {
		Object[][] table = { { 1, 2.0, "aa" }, { null, 3.6, "bb" }, { 3, 5.9, "cc" } };
		Dataframe dataframe = new Dataframe(table);

		assert (new BigDecimal(0).compareTo(dataframe.max(2)) == 0);

	}

	@Test
	public void testMin() throws Exception {
		Object[][] table = { { 1, 2.0, "aa" }, { null, 3.6, "bb" }, { 3, 5.9, "cc" } };
		Dataframe dataframe = new Dataframe(table);

		assert (new BigDecimal(2.0).compareTo(dataframe.min(1)) == 0);
	}

	@Test
	public void testMinNonNumber() throws Exception {
		Object[][] table = { { 1, 2.0, "aa" }, { null, 3.6, "bb" }, { 3, 5.9, "cc" } };
		Dataframe dataframe = new Dataframe(table);

		assert (new BigDecimal(0).compareTo(dataframe.min(2)) == 0);

	}

}
