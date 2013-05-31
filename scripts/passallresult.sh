is=$(seq 514 8 1023)
for i in $is
do
	bash passresult.sh $i
done
