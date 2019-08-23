# homology_consistency_codes
Source-codes for the experiments over homology (Betti-numbers) consistency

NOTE: The experiments were executed by using the Javaplex 4.0

CODES:
WitnessComplexDemo.java: Generates barcode plots and objects containing the persistence intervals;
MultiplicityCriteria.java: Calculates the absolute difference of Betti number between original and perturbed data;
RunMult.java: Generates the data with the absolute Betti number differences;
DataGen.r: Generates employed data;
plot_heat.r: Generates heat plots with the absolute Betti number differences vs. data inclusion vs. radius of open balls employed in the lazy witness.

ABOUT DATA CREATED:
BottomUp: Evaluate Betti number differeces increasing the radius of lazy witness;
TopDown: Evaluate Betti number differences deacreasing the radius of lazy witness.
