cd "/tmp/process/"
cd "b"
sec=$(ls -d -- *)
for s in $sec
do
	dat=$(ls -d -- $s/*)
	for d in $dat
	do
		mkdir -p "../c/$d"
		fil=$(ls $d/*)
		for f in $fil
		do
			perl ~/passb.pl < $f > ../c/$f
			rm "$f"
		done
	done
done
cd ..
echo "made a run through it..."
sleep 1
