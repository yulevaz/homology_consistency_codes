require(nonlinearTseries)
require(RSSL)
require(frbs)
require(MASS)
data(frbsData)

writeDat <- function(dat,filename) {

	nr = nrow(dat)
	nc = ncol(dat)
	maxdist = max(dist(dat))
	print(maxdist)	
	write(nr,file=filename)
	write(nc,file=filename,append=T)
	write(maxdist,file=filename,append=T)
	write(t(dat),file=filename,sep=" ",append=T,ncolumns=nc)

}

#lor = lorenz(sigma = 10, beta = 8/3, rho = 28, start = c(-13, -14, 47), time = seq(0, 50, by = 0.005), do.plot=F)
#dat = cbind(lor$x,lor$y,lor$z)
#writeDat(dat/max(dat),"lor.dat")
#ross = rossler(a = 0.2, b = 0.2, w = 5.7, start=c(-2,-10,0.2), time = seq(0, 50, by = 0.005), do.plot = F)
#dat = cbind(ross$x, ross$y, ross$z)
#writeDat(dat/max(dat),"ross.dat")
#banana = generateCrescentMoon(n=5000, d=2, sigma=0.5)
#dat = cbind(banana$X1,banana$X2)
#writeDat(dat/max(dat),"banana.dat")
#dat = frbsData$MackeyGlass1000.dt
#writeDat(dat/max(dat),"mackey.dat")

lor = lorenz(sigma = 10, beta = 8/3, rho = 28, start = c(-13, -14, 47), time = seq(0, 50, by = 0.001), do.plot=F)
dat = cbind(lor$x,lor$y,lor$z)
dat = dat[sample(1:nrow(dat),2500),]
writeDat(dat,"lor.dat")
dat = dat[sample(1:nrow(dat),2000),]
writeDat(dat,"lor_pert.dat")
ross = rossler(a = 0.2, b = 0.2, w = 5.7, start=c(-2,-10,0.2), time = seq(0, 50, by = 0.001), do.plot = F)
dat = cbind(ross$x, ross$y, ross$z)
dat = dat[sample(1:nrow(dat),2500),]
writeDat(dat,"ross.dat")
dat = dat[sample(1:nrow(dat),2000),]
writeDat(dat,"ross_pert.dat")
banana = generateCrescentMoon(n=5000, d=2, sigma=0.5)
dat = cbind(banana$X1,banana$X2)
dat = dat[sample(1:nrow(dat),2500),]
writeDat(dat,"banana.dat")
dat = dat[sample(1:nrow(dat),2000),]
writeDat(dat,"banana_pert.dat")
dat = frbsData$MackeyGlass1000.dt
#dat = dat[sample(1:nrow(dat),500),]
writeDat(dat,"mackey.dat")
dat = dat[sample(1:nrow(dat),900),]
writeDat(dat,"mackey_pert.dat")

