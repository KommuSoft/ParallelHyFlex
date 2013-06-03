#echo "MOVED TO ~/Projects/parallelhyflex/ParallelHyFlex/data/$1/f$2"
cd ~/Projects/parallelhyflex/ParallelHyFlex/data/$1/f$2
for f in summary*
do
#	if [ -f "sm-$f" ]
#	then
#		echo -n ""
#	else
		perl ~/Projects/parallelhyflex/ParallelHyFlex/scripts/calcstats.pl 1 < $f > sm-$f
#	fi
done

for n in {2..5}
	do
	mkdir -p ~/Projects/parallelhyflex/ParallelHyFlex/sigs/$1/$2-$n
	cd ~/Projects/parallelhyflex/ParallelHyFlex/data/$1/f$2
	fils=$(ls | grep "sm-summary.*-$n.txt")
	for f in $fils
	do
		cp "$f" ~/Projects/parallelhyflex/ParallelHyFlex/sigs/$1/$2-$n/$f
	done
	cd ~/Projects/parallelhyflex/ParallelHyFlex/sigs/$1/$2-$n
	bash ~/Projects/parallelhyflex/ParallelHyFlex/scripts/gengnuplot.sh > ~/Projects/parallelhyflex/ParallelHyFlex/sigs/$1/$2-$n/pdfplot.gp 2> /dev/null & bash ~/Projects/parallelhyflex/ParallelHyFlex/scripts/gengnuplotps.sh > ~/Projects/parallelhyflex/ParallelHyFlex/sigs/$1/$2-$n/psplot.gp 2> /dev/null
	gnuplot < ~/Projects/parallelhyflex/ParallelHyFlex/sigs/$1/$2-$n/pdfplot.gp 2> /dev/null & gnuplot < ~/Projects/parallelhyflex/ParallelHyFlex/sigs/$1/$2-$n/psplot.gp 2> /dev/null
done


for p in {1..4}
do
	for n in {2..5}
	do
#		sleep 1
#		echo "ls sm-summary-$p-*-$n.txt in ~/Projects/parallelhyflex/ParallelHyFlex/data/$1/f$2"
		mkdir -p ~/Projects/parallelhyflex/ParallelHyFlex/sigs/$1/$2-$p-$n
		cd ~/Projects/parallelhyflex/ParallelHyFlex/data/$1/f$2
		fils=$(ls | grep "sm-summary-$p-.*-$n.txt")
		for f in $fils
		do
#			echo "$f from ~/Projects/parallelhyflex/ParallelHyFlex/data/$1/f$2"
			cp "$f" ~/Projects/parallelhyflex/ParallelHyFlex/sigs/$1/$2-$p-$n/$f
		done
		cd ~/Projects/parallelhyflex/ParallelHyFlex/sigs/$1/$2-$p-$n
		bash ~/Projects/parallelhyflex/ParallelHyFlex/scripts/gengnuplot.sh > ~/Projects/parallelhyflex/ParallelHyFlex/sigs/$1/$2-$p-$n/pdfplot.gp 2> /dev/null & bash ~/Projects/parallelhyflex/ParallelHyFlex/scripts/gengnuplotps.sh > ~/Projects/parallelhyflex/ParallelHyFlex/sigs/$1/$2-$p-$n/psplot.gp 2> /dev/null
		gnuplot < ~/Projects/parallelhyflex/ParallelHyFlex/sigs/$1/$2-$p-$n/pdfplot.gp 2> /dev/null & gnuplot < ~/Projects/parallelhyflex/ParallelHyFlex/sigs/$1/$2-$p-$n/psplot.gp 2> /dev/null
	done
done
