echo "set xlabel \"tijd (s)\""
echo "set ylabel \"fitness-waarde\""
echo "set terminal postscript size 64cm,32cm"
echo "set output 'out.ps'"
echo "set logscale y"
echo "set style fill transparent solid 0.1 noborder"

echo -n "plot "
k=$(ls sm-* | wc --lines)
i=1
for f in sm-*
do
	tit=$(echo $f | cut -c 12-99)
	col=$(head -n $i < ~/colors | tail -n 1)
	echo -n "\"$f\" u (\$1/1000):8:9 t \"\" w filledcurve lw 0 lc rgb \"#$col\", "
	i=$(($i+1))
done
i=1
for f in sm-*
do
	tit=$(echo $f | cut -c 12-99)
	col=$(head -n $i < ~/colors | tail -n 1)
	echo -n "\"$f\" u (\$1/1000):6 t \"p=$tit\" w linespoints lw 5 lc rgb \"#$col\""
	if [[ $i -lt $k ]]
	then
		echo -n ", "
	fi
	i=$(($i+1))
done
echo ""

echo ""
echo "set terminal postscript size 10cm,5cm"
echo "set output '10x5.ps'"
echo "replot"
echo ""
echo "set terminal postscript size 8cm,9cm"
echo "set output '20x10.ps'"
echo "replot"

echo "set terminal postscript size 40cm,22cm"
echo "set output 'fullhd.ps'"
echo "replot"
