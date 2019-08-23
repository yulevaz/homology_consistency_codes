import edu.stanford.math.plex4.homology.barcodes.BarcodeCollection;
import java.util.LinkedHashSet;
import java.util.HashMap;

public class RunMult {

	public static void main(String[] args) {

		BarcodeCollection<Double> barcodes0 = BarcodesIOF.readBarcode("bcobjects/"+args[0]+"_pert.dat_bc.obj");	
		BarcodeCollection<Double> barcodesM = BarcodesIOF.readBarcode("bcobjects/"+args[0]+".dat_bc.obj");
		double end = Double.parseDouble(args[1]);
		int nSection = Integer.parseInt(args[2]);
		MultiplicityCriteria mult = new MultiplicityCriteria();
		HashMap<String,LinkedHashSet<double[]>> results = mult.calculateMultiplicities(barcodes0, barcodesM, nSection, end);
		mult.writeResults(args[0], results);

	}	

}
