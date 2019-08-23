#!/bin/bash

# java <Create barcodes plots and objects> <data> <number of landmarks> <dimensions> <maxdistance>
java WitnessComplexDemo data/lor 50 2 10
#java WitnessComplexDemo data/ross 200 2 10
#java WitnessComplexDemo data/banana 200 2 10
#java WitnessComplexDemo data/mackey 200 3 10

#java <Create Absolute Betti-number differences> <maxdist> <filtration resolution>
java RunMult lor 10 10000
#java RunMult ross 10 10000
#java RunMult banana 10 10000
#java RunMult mackey 10 10000
