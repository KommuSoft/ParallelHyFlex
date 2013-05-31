cd "/tmp/process/"
while true
	do
	cd "a"
	sec=$(ls -d -- *)
	for s in $sec
	do
		dat=$(ls -d -- $s/*)
		for d in $dat
		do
			mkdir -p "../b/$d"
			fil=$(ls $d/*)
			for f in $fil
			do
				perl ~/passa.pl < $f > ../b/$f
				rm "$f"
			done
		done
	done
	cd ..
	echo "made a run through it..."
	sleep 60
done
