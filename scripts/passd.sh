cd "/tmp/process/"
cd "d"
mkdir -p "../e"
sec=$(ls -d -- *)
for s in $sec
do
	nama=$s
	dat=$(ls -d -- $s/*)
	for d in $dat
	do
		lim=$(echo "$d" | awk '{print length($0)}')
		namb=$(echo "$d" | cut -c $lim-$lim)
		fil=$(ls $d/*)
		for f in $fil
		do
			lim=$(echo "$f" | awk '{print length($0)}')
			lim=$(($lim-9))
			sta=$(($lim-9))
			namc=$(echo $f | cut -c $sta-$lim)
			namd=$(perl ~/generatename.pl < $f)
#				sleep 10
			mv "$f" "../e/$nama=$namb=$namc$namd"
		done
	done
done
cd ..
echo "made a run through it..."
sleep 1
