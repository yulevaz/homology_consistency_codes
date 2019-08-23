import edu.stanford.math.plex4.homology.barcodes.Interval;
import edu.stanford.math.plex4.homology.barcodes.BarcodeCollection;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class MultiplicityCriteria<T extends Comparable<T>> {

	public MultiplicityCriteria() {



	}

	private boolean existsIntervalOnSection(Double interStart, Double interEnd, Double section) {

		if (interStart.compareTo(section) <= 0 && interEnd.compareTo(section) >= 0) {
			
			return true;
		
		}
		else {

			return false;

		}

	}

	private int[] getMultiplicity(List<Interval<T>> intervals, double botbeg, double botend, double topbeg, double topend) {

		int[] mult = new int[2];
		mult[0] = 0;
		mult[1] = 0;

		Iterator<Interval<T>> iit = intervals.iterator();

		while(iit.hasNext()) {

			Interval<T> interval = iit.next();
			Double start, end;
			if (interval.isLeftInfinite()) {

				start = -Double.MAX_VALUE;

			}
			else {

				start = (Double)interval.getStart();

			}
			if (interval.isRightInfinite()) {

				end = Double.MAX_VALUE;

			}
			else {

				end = (Double)interval.getEnd();

			}
			Double topi = Double.valueOf(topbeg);
			Double topf = Double.valueOf(topend);
			Double boti = Double.valueOf(botbeg);
			Double botf = Double.valueOf(botend);

			/*if (this.existsIntervalOnSection(start,end,boti))
				mult[0]++; */
			if (this.existsIntervalOnSection(start,end,botf))
				mult[0]++;

						
			/*if (this.existsIntervalOnSection(start,end,topi))
				mult[1]++;*/
			if (this.existsIntervalOnSection(start,end,topf))
				mult[1]++;

		}

		return mult;

	}

	private List<Pair<String,LinkedHashSet<double[]>>> getMultiplicitySeries(List<Interval<T>> intervals0, List<Interval<T>> intervalsM, int dim, double beg, double end, int nSections) {

		String labelBotUp = "BottonUp_" + Integer.toString(dim);	
		LinkedHashSet<double[]> multCrit = new LinkedHashSet<double[]>();
		List<Pair<String,LinkedHashSet<double[]>>> series = new ArrayList<Pair<String,LinkedHashSet<double[]>>>();
		series.add(new Pair<String,LinkedHashSet<double[]>>(labelBotUp, new LinkedHashSet<double[]>()));

		double step = ((double)(end-beg))/nSections;
	
		double[] xy0;
		double[] xy1;
		for (double i = 0; i <= (end-step); i+=step) {
		
			int[] mult0 = this.getMultiplicity(intervals0, 0, (i+step), (end-i-step), end);
			int[] multM = this.getMultiplicity(intervalsM, 0, (i+step), (end-i-step), end);
			xy0 = new double[2];
			xy0[0] = (i+step);
			xy0[1] = Double.valueOf(Math.abs(multM[0]-mult0[0]));
			if (dim == 0)
				System.out.printf("%d %d\n",multM[0],mult0[0]);
			series.get(0).getElement1().add(xy0);
			//System.out.println(xy0[1]);
			xy1 = new double[2];
			xy1[0] = end-i;
			xy1[1] = Double.valueOf(Math.abs(multM[1]-mult0[1]));
			series.get(1).getElement1().add(xy1);

		}
		return series;

	}

	public HashMap<String,LinkedHashSet<double[]>> calculateMultiplicities(BarcodeCollection<T> barcodes0, BarcodeCollection<T> barcodesM, int nSection, double end) {


		Set<Integer> dimensions = barcodes0.getDimensions();
		Iterator<Integer> iit = dimensions.iterator();
		HashMap<String,LinkedHashSet<double[]>> result = new HashMap<String,LinkedHashSet<double[]>>();


		while(iit.hasNext()) {

			int dim = iit.next();
			List<Interval<T>> intervals0 = barcodes0.getIntervalsAtDimension(dim);
			List<Interval<T>> intervalsM = barcodesM.getIntervalsAtDimension(dim);
			List<Pair<String,LinkedHashSet<double[]>>> mult = this.getMultiplicitySeries(intervals0, intervalsM, dim, 0, end, nSection);
			result.put(mult.get(0).getElement0(),mult.get(0).getElement1());

			Iterator<double[]> pit = mult.get(0).getElement1().iterator();
			result.put(mult.get(1).getElement0(),mult.get(1).getElement1());	

		}

		return result;	

	}

	public void writeResults(String expsName, HashMap<String,LinkedHashSet<double[]>> results) {

		Set<String> keys = results.keySet();
		Iterator<String> sit = keys.iterator();

		while(sit.hasNext()) {

			try {
				String label = sit.next();
				LinkedHashSet<double[]> series = results.get(label);
				FileWriter fos = new FileWriter(expsName+label);
				BufferedWriter outSt = new BufferedWriter(fos);

				Iterator<double[]> pit = series.iterator();

				while(pit.hasNext()) {

					double[] p = pit.next();
					outSt.write(Double.toString(p[0])+" "+Double.toString(p[1])+"\n");
				
				}

				outSt.close();
			
			}
			catch(Exception ex) {

				ex.printStackTrace();

			}
		}


	}

}
