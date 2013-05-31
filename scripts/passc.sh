cd "/tmp/process/"
#while true
#	do
	cd "c"
	sec=$(ls -d -- *)
	for s in $sec
	do
		dat=$(ls -d -- $s/*)
		for d in $dat
		do
			lim=$(echo "$d" | awk '{print length($0)}')
			end=$(($lim+1))
			last=$(echo "$d" | cut -c $lim-$end)
			last=$(($last-1))
			sq=$(seq 1 $last)
			mkdir -p "../d/$d"
			lim=$(echo "$d/" | awk '{print length($0)}')
			lim=$(($lim+13))
			fil=$(ls $d/* | cut -c 1-$lim | uniq)
			$last=$(echo $fil | wc -l)
			for f in $fil
			do
				mv "$f-0.txt" "../d/$f-root.txt"
				for j in $sq
				do
					if [ -f "../d/$f-root.txt" ]
					then
						if [ -f "$f-$j.txt" ]
						then
							perl ~/passc.pl "../d/$f-root.txt" < "$f-$j.txt" > "tmp4524515482"
							rm "$f-$j.txt"
							mv "tmp4524515482" "../d/$f-root.txt"
						fi
					fi
				done
#				if [ -f	 "../d/$f-root.txt" ]
#				then
#					echo "Processed $f"
#				else
#					echo "Failed to process $f"
#				fi
			done
			#for f in $fil
			#do
			#	perl ~/passb.pl < $f > ../c/$f
			#	#rm "$f"
			#done
		done
	done
	cd ..
#	sleep 900
#	echo "made a run through it..."
#done
