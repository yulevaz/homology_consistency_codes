
plot_heat <- function(path,botup=T,degree=0) {

	if (botup==T) {

		regstr = paste("BottonUp_",degree,sep="",collapse=NULL)

	}
	else {

		regstr = paste("TopDown_",degree,sep="",collapse=NULL)

	}

	lFiles = dir(path,pattern=regstr)
	rounds = as.integer(gsub(regstr,"",lFiles))
	
	rounds = sort(rounds)

	res = NULL
	rowlabels = c()
	collabels = read.table(paste(path,"/",rounds[1],regstr,sep="",collapse=NULL))[,1]

	for (i in 1:length(rounds)) {

		print(i/length(rounds))
		serie = read.table(paste(path,"/",rounds[i],regstr,sep="",collapse=NULL))
		res = cbind(res,serie[,2])
		rowlabels = c(rowlabels,rounds[i])	

	}

	colnames(res) <- rowlabels
	rownames(res) <- collabels
	image(res,col=terrain.colors(256),axes=F)
	axis(1, at=seq(0,1,length.out=10), labels=sprintf("%0.2f",seq(0,max(collabels),length.out=10)))
	axis(2, at=seq(0,1,length.out=length(rowlabels)), labels=rowlabels)

}
