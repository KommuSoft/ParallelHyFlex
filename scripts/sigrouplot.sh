echo "MOVED TO ~/Projects/parallelhyflex/ParallelHyFlex/data/$1/f$2"
cd ~/Projects/parallelhyflex/ParallelHyFlex/data/$1/f$2
for f in summary*
do
	if [ -f "sm-$f" ]
	then
		echo -n ""
	else
		perl ~/Projects/parallelhyflex/ParallelHyFlex/scripts/calcstats.pl 1 < $f > sm-$f
	fi
done
fils=$(ls sm-summary-*4.txt | perl ~/Projects/parallelhyflex/ParallelHyFlex/scripts/sigrouplot.pl "$3")
mkdir -p ~/Projects/parallelhyflex/ParallelHyFlex/sigs/$1/$2
for f in $fils
do
	echo "$f"
	cp "$f" ~/Projects/parallelhyflex/ParallelHyFlex/sigs/$1/$2/$f
done
cd ~/Projects/parallelhyflex/ParallelHyFlex/sigs/$1/$2
bash ~/Projects/parallelhyflex/ParallelHyFlex/scripts/gengnuplot.sh | gnuplot
bash ~/Projects/parallelhyflex/ParallelHyFlex/scripts/gengnuplot.sh > ~/Projects/parallelhyflex/ParallelHyFlex/sigs/$1/$2/pdfplot.gp
bash ~/Projects/parallelhyflex/ParallelHyFlex/scripts/gengnuplotps.sh | gnuplot
bash ~/Projects/parallelhyflex/ParallelHyFlex/scripts/gengnuplotps.sh > ~/Projects/parallelhyflex/ParallelHyFlex/sigs/$1/$2/psplot.gp
