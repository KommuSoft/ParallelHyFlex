mkdir -p "data/run$1"
mkdir -p "datb/run$1"
echo "$3" > sep.dat
echo "=================================================" >> sep.dat
e=$(($1-1))
d=$(date +%s)
ps=$(seq 0 $e)
for p in $ps
do
	cat "$2" "/proc/cpuinfo" "sep.dat" "log$p.txt" > "data/run$1/log$d-$p.txt"
	perl ~/passa.pl < "data/run$1/log$d-$p.txt" > "datb/run$1/log$d-$p.txt"
	rm "data/run$1/log$d-$p.txt"
	rm "log$p.txt"
done
