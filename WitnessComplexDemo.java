import edu.stanford.math.plex4.api.Plex4;
import edu.stanford.math.plex4.examples.PointCloudExamples;
import edu.stanford.math.plex4.homology.barcodes.BarcodeCollection;
import edu.stanford.math.plex4.homology.chain_basis.Simplex;
import edu.stanford.math.plex4.homology.interfaces.AbstractPersistenceAlgorithm;
import edu.stanford.math.plex4.metric.impl.EuclideanMetricSpace;
import edu.stanford.math.plex4.metric.landmark.LandmarkSelector;
import edu.stanford.math.plex4.metric.landmark.MaxMinLandmarkSelector;
import edu.stanford.math.plex4.streams.impl.LazyWitnessStream;
import edu.stanford.math.plex.*;
import edu.stanford.math.plex4.homology.barcodes.Interval;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.*;

public class WitnessComplexDemo {

	private static LandmarkSelector<double[]> getLandmarks(EuclideanMetricSpace metricSpace, int numLandmarkPoints) {
	
		LandmarkSelector<double[]> landmarkSelector = new MaxMinLandmarkSelector<double[]>(metricSpace, numLandmarkPoints);

		return landmarkSelector;


	}

	private static BarcodeCollection<Double> calculateBarcodes(EuclideanMetricSpace metricSpace, LandmarkSelector<double[]> landmarkSelector, int d, double maxdist) {
											
		LazyWitnessStream<double[]> stream = new LazyWitnessStream<double[]>(metricSpace, landmarkSelector, d, maxdist,1, 1000);
		stream.finalizeStream();
																						
		System.out.println("Number of simpleces in complex: " + stream.getSize());
																							
		AbstractPersistenceAlgorithm<Simplex> algorithm = Plex4.getDefaultSimplicialAlgorithm(d);																
		BarcodeCollection<Double> intervals = algorithm.computeIntervals(stream);

		return intervals;


	}

	public static void main(String[] args) {
				
		String datName = args[0] + ".dat";
		String pertDatName = args[0] + "_pert.dat";

		int numLandmarkPoints = Integer.parseInt(args[1]);
		int d = Integer.parseInt(args[2]);
				
		Data dat = new Data();
		Data datPert = new Data();
		Data.readData(dat,datName);	
		Data.readData(datPert,pertDatName);	
		double[][] points = datPert.getData();

		EuclideanMetricSpace metricSpace = new EuclideanMetricSpace(points);
		LandmarkSelector<double[]> landmarkSelector = getLandmarks(metricSpace,numLandmarkPoints);
		double maxdist = Double.parseDouble(args[3]);
		BarcodeCollection<Double> intervalsPert = calculateBarcodes(metricSpace, landmarkSelector, d, maxdist);
		BarcodesIOF.writeBarcode("bcobjects/"+pertDatName+"_bc.obj",intervalsPert);
	       	BarcodesIOF.readBarcode("bcobjects/"+pertDatName+"_bc.obj");	

		points = dat.getData();
		metricSpace = new EuclideanMetricSpace(points);
		BarcodeCollection<Double> intervals = calculateBarcodes(metricSpace, landmarkSelector, d, maxdist);
		BarcodesIOF.writeBarcode("./"+datName+"_bc.obj",intervals);
	       	BarcodesIOF.readBarcode("./"+datName+"_bc.obj");	

		//System.out.println(intervals);

		try{
			Plex4.createBarcodePlot(intervalsPert,new String("barcodes/"+pertDatName+"_barcode"),maxdist);	
			Plex4.createBarcodePlot(intervals,new String("barcodes/"+datName+"_barcode"),maxdist);	
		} 
		catch(java.io.IOException e){
			System.out.println("Error while creating barcode plot.");
			e.printStackTrace();
		}

	}
}









