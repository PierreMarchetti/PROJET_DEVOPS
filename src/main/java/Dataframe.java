package main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Dataframe {

	private ArrayList<ArrayList<Object>> dataframeBody;
	private ArrayList<Class<?>> columntypes;


	public Dataframe(String filepath) throws Exception {
		dataframeBody = new ArrayList<ArrayList<Object>>();
		columntypes = new ArrayList<Class<?>>();

		String line = "";
		try {

			BufferedReader br = new BufferedReader(new FileReader(filepath));

			line = br.readLine();
			String[] classes = line.split(",");
			for (String sClass : classes) {
				columntypes.add(Class.forName(sClass));
				ArrayList<Object> column = new ArrayList<Object>();
				dataframeBody.add(column);

			}
			while ((line = br.readLine()) != null) {
				String[] items = line.split(",");

				for (int i = 0; i < items.length; i++) {

					if (items[i].equals("")) {
						dataframeBody.get(i).add(null);
					} else {
						try {
							Constructor<?> ctor = columntypes.get(i).getConstructor(String.class);
							Object o = ctor.newInstance(items[i]);

							dataframeBody.get(i).add(o);
						} catch (NoSuchMethodException | SecurityException | InstantiationException
								| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							throw new Exception();
						}

					}

				}
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	public Dataframe(Object[][] table) throws Exception {
		dataframeBody = new ArrayList<ArrayList<Object>>();
		columntypes = new ArrayList<Class<?>>();

		for (Object o : table[0]) {
			ArrayList<Object> column = new ArrayList<Object>();
			column.add(o);
			dataframeBody.add(column);
			columntypes.add(o.getClass());
		}

		for (int i = 1; i < table.length; i++) {

			for (int j = 0; j < table[i].length; j++) {
				if (table[i][j] != null) {
					if (table[i][j].getClass() != columntypes.get(j)) {

						throw new Exception("les éléments d'un label n'ont pas tous le même type");
					}
				}

				dataframeBody.get(j).add(table[i][j]);
			}
		}
	}

	public String displayDataframe() {
		String result = "  ";

		for (int i = 1; i < dataframeBody.size() + 1; i++) {
			result += i + " ";
		}
		result += "\n";

		for (int i = 0; i < dataframeBody.get(0).size(); i++) {
			result += (char) (i + 97);
			result += " ";
			for (int j = 0; j < dataframeBody.size(); j++) {
				if (dataframeBody.get(j).get(i) == null)
					result += "NaN ";
				else
					result += dataframeBody.get(j).get(i) + " ";
			}
			result += "\n";
		}

		return result;

	}

	public String head() {
		String result = "  ";

		for (int i = 1; i < dataframeBody.size() + 1; i++) {
			result += i + " ";
		}
		result += "\n";

		for (int i = 0; i < Math.min(dataframeBody.get(0).size(), 2); i++) {
			result += (char) (i + 97);
			result += " ";
			for (int j = 0; j < dataframeBody.size(); j++) {
				if (dataframeBody.get(j).get(i) == null)
					result += "NaN ";
				else
					result += dataframeBody.get(j).get(i) + " ";
			}
			result += "\n";
		}

		return result;
	}

	public String tail() {
		String result = "  ";

		for (int i = 1; i < dataframeBody.size() + 1; i++) {
			result += i + " ";
		}
		result += "\n";

		for (int i = (dataframeBody.size() < 2 ? 0 : dataframeBody.size() - 2); i < dataframeBody.get(0).size(); i++) {
			result += (char) (i + 97);
			result += " ";
			for (int j = 0; j < dataframeBody.size(); j++) {
				if (dataframeBody.get(j).get(i) == null)
					result += "NaN ";
				else
					result += dataframeBody.get(j).get(i) + " ";
			}
			result += "\n";
		}

		return result;

	}

	public Dataframe iloc(int[] indexes) throws Exception {
		Object[][] table = new Object[indexes.length][dataframeBody.get(0).size()];

		for (int i = 0; i < indexes.length; i++) {
			for (int j = 0; j < dataframeBody.size(); j++) {

				table[i][j] = dataframeBody.get(j).get(indexes[i]);
			}
		}
		return new Dataframe(table);
	}

	public Dataframe loc(int[] labels) throws Exception {
		Object[][] table = new Object[dataframeBody.size()][labels.length];

		for (int i = 0; i < labels.length; i++) {
			for (int j = 0; j < dataframeBody.size(); j++) {

				table[j][i] = dataframeBody.get(labels[i]).get(j);
			}
		}
		return new Dataframe(table);
	}

	public BigDecimal sum(int label) {

		BigDecimal r = new BigDecimal(0);

		if (!Number.class.isAssignableFrom(columntypes.get(label))) {
			return r;
		}

		for (Object object : dataframeBody.get(label)) {
			if (object != null) {
				r = r.add(new BigDecimal(object.toString()));
			}
		}
		return r;
	}

	public BigDecimal mean(int label) {

		if (!Number.class.isAssignableFrom(columntypes.get(label))) {
			return new BigDecimal(0);
		}

		return sum(label).divide(new BigDecimal(dataframeBody.get(0).size()), 10, RoundingMode.HALF_UP);
	}

	public BigDecimal max(int label) {

		BigDecimal r = new BigDecimal(0);

		if (!Number.class.isAssignableFrom(columntypes.get(label))) {
			return r;
		}

		for (Object object : dataframeBody.get(label)) {
			if (object != null) {
				r = r.max(new BigDecimal(object.toString()));
			}
		}

		return r;
	}

	public BigDecimal min(int label) {
		BigDecimal r = new BigDecimal(0);

		if (!Number.class.isAssignableFrom(columntypes.get(label)) || dataframeBody.get(label).size() == 0) {
			return r;
		}

		r = new BigDecimal(dataframeBody.get(label).get(0).toString());

		for (Object object : dataframeBody.get(label)) {
			if (object != null) {
				r = r.min(new BigDecimal(object.toString()));
			}
		}

		return r;
	}

	public ArrayList<ArrayList<Object>> getDataframeBody() {
		return dataframeBody;
	}
}
