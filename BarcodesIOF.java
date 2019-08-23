import edu.stanford.math.plex4.homology.barcodes.BarcodeCollection;
import edu.stanford.math.plex4.homology.barcodes.Interval;

import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;

import java.util.*;

public class BarcodesIOF<T extends Comparable<T>> {

	public static <T extends Comparable<T>> void writeBarcode(String filename, BarcodeCollection<T> barcode) {

		Set<Integer> dimensions = barcode.getDimensions();
		Iterator<Integer> sit = dimensions.iterator();
		int dim = 0;

		try {

			FileOutputStream fo = new FileOutputStream(filename);
			ObjectOutputStream objOut = new ObjectOutputStream(fo);

			while (sit.hasNext()) {

				dim = sit.next();
				List<Interval<T>> list = barcode.getIntervalsAtDimension(dim);
				Iterator<Interval<T>> iit = list.iterator();

				while(iit.hasNext()) {
					Pair<Integer,Interval<T>> pair = new Pair<Integer,Interval<T>>(dim,iit.next());
					objOut.writeObject(pair);
				}


			}
	
			objOut.flush();
			objOut.close();

		}
		catch (Exception ex) {

			ex.printStackTrace();

		}
		

	}

	public static <T extends Comparable<T>> BarcodeCollection<T> readBarcode(String args) {
	
		try {

			String file = args;	
			FileInputStream fi = new FileInputStream(args);
			ObjectInputStream objIn = new ObjectInputStream(fi);
			Pair<Integer,Interval<T>> pair;
			System.out.println(fi.available());
			BarcodeCollection<T> barcodes = new BarcodeCollection<T>();	

			while (fi.available() != 0) {

				pair = (Pair<Integer,Interval<T>>) objIn.readObject();
				barcodes.addInterval(pair.getElement0(),pair.getElement1());

			}

			objIn.close();
			return barcodes;
				
		}
		catch (Exception ex) {

			ex.printStackTrace();
			return null;

		}
	}
}









