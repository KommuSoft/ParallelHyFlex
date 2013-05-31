echo "set xlabel \"tijd (s)\""
echo "set ylabel \"fitness-waarde\""
echo "set terminal pdf size 64cm,32cm"
echo "set output 'out.pdf'"
echo "set logscale y"
echo "set style fill transparent solid 0.2 noborder"
echo -n "plot "
k=$(ls sm-* | wc --lines)
i=1
for f in sm-*
do
	tit=$(echo $f | cut -c 12-99)
	echo -n "\"$f\" u (\$1/1000):8:9 t \"p=$tit\" w filledcurve lw 0 lc rgb \"#ff00ff\", "
	echo -n "\"$f\" u (\$1/1000):6 t \"p=$tit\" w linespoints lw 5 lc rgb \"#0000ff\""
	if [[ $i -lt $k ]]
	then
		echo -n ", "
	fi
	i=$(($i+1))
done
echo ""
echo ""
echo "set terminal pdf size 10cm,5cm"
echo "set output 'out10x5.pdf'"
echo "replot"
echo ""
echo "set terminal pdf size 18cm,9cm"
echo "set output 'out20x10.pdf'"
echo "replot"

echo "set terminal pdf size 40cm,22cm"
echo "set output 'outfullhd.pdf"
echo "replot"
