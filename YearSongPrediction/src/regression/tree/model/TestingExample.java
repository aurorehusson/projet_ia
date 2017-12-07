package regression.tree.model;

import java.util.ArrayList;

public class TestingExample {
	private ArrayList<Double> arguments;
	private String expectedYear;
	private String obtainedYear;
	
	public TestingExample(ArrayList<Double> arg, String year) {
		if (arg.size() != 90) {
			throw new IllegalStateException("Array should contain 90 arguments");
		}
		arguments = arg;
		setExpectedYear(year);
	}
	
	public TestingExample(String testLine) {
		String year = testLine.substring(1, 5);
		String argLine = testLine.substring(7);
		String[] splitLine = argLine.split("\t");
		ArrayList<Double> args = new ArrayList<>();
		for (int i = 0; i < splitLine.length; i++) {
			String arg = splitLine[i].replaceAll(",", ".");
			Double value = Double.valueOf(arg);
			args.add(value);
		}
		if (args.size() != 90) {
			throw new IllegalStateException("Array should contain 90 arguments");
		}
		arguments = args;
		setExpectedYear(year);
	}
	
	public Double getArgument(Integer index) {
		return this.arguments.get(index-1);
	}

	
	/**
	 * @return the obtainedYear
	 */
	public String getObtainedYear() {
		return obtainedYear;
	}

	/**
	 * @param obtainedYear the obtainedYear to set
	 */
	public void setObtainedYear(String obtainedYear) {
		this.obtainedYear = obtainedYear;
	}



	/**
	 * @return the expectedYear
	 */
	public String getExpectedYear() {
		return expectedYear;
	}



	/**
	 * @param expectedYear the expectedYear to set
	 */
	private void setExpectedYear(String expectedYear) {
		this.expectedYear = expectedYear;
	}
}
