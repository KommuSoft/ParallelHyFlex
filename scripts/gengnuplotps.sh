echo "set terminal postscript size 64cm,32cm"
echo "set output 'out.ps'"
echo "set logscale y"
echo -n "plot "
k=$(ls sm-* | wc --lines)
i=1
for f in sm-*
do
	echo -n "\"$f\" using 1:6 with lines"
	#echo "$i/$k"
	if [[ $i -lt $k ]]
	then
		echo -n ", "
	fi
	i=$(($i+1))
done
echo ""
