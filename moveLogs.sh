dat=$(date "+%Y%m%d%H%M%S")
sq=$(seq 0 $1)
for j in $sq
do
	echo "copying log$j.txt"
	mv "log$j.txt" "data/data$1/log$j-$dat.txt"
done
