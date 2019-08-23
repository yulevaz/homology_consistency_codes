import java.io.*;

public class Data {

	private int ndata;
	private int dimension;
	private double[][] data;
	private double maxdist;

	public Data(int n, int d, double maxd, double[][] dat) {

		this.ndata = n;
		this.dimension = d;
		this.data = dat;
		this.maxdist = maxd;

	}

	public Data() {

		this.ndata = 0;
		this.dimension = 0;
		this.data = null;
		this.maxdist = 0;

	}

	public static void readData(Data dat, String filename) {

		try {
			File file = new File(filename);
			BufferedReader br = new BufferedReader(new FileReader(file));

			dat.ndata = Integer.parseInt(br.readLine());
			dat.dimension = Integer.parseInt(br.readLine());
			dat.maxdist = Double.valueOf(br.readLine());
			dat.data = new double[dat.ndata][dat.dimension];

			String st;
			int i = 0;
			while ((st = br.readLine()) != null) {
				String[] values = st.split(" ");

				for (int j = 0; j < values.length; j++) {

					dat.data[i][j] = Double.valueOf(values[j]);

				}

				i++;

			}

		}
		catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void printData() {

		for (int i = 0; i < this.ndata; i++) {

			for (int j = 0; j < this.dimension; j++) {

				System.out.printf("%.5f ",this.data[i][j]);
				
			}
			System.out.println();

		}

	}

	public int getSize() {

		return this.ndata;

	}

	public int getDimension() {

		return this.dimension;

	}

	public double getMaxDist() {

		return this.maxdist;

	}

	public double[][] getData() {

		return this.data;

	}
	
}

