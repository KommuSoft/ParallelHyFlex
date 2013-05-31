cd "/tmp/process/"
cd "es"
mkdir -p "../f$1/$d"
reg=$(ls | perl ~/groupby.pl $1 2> "../f$1/annot")
#echo "The regexes are"

for r in $reg
do
	#echo "$r"
	fil=$(ls | grep -P "$r")
	hd=$(echo "$fil" | head -n 1)
	tl=$(echo "$fil" | head -n -1)
	hf=$(head -n 1 < "../f$1/annot")
	tail -n +2 < "../f$1/annot" > "../f$1/nvm"
	mv "../f$1/nvm" "../f$1/annot"
	cp $hd "../f$1/$hf"
	for f in $tl
	do
		#echo "merge with $f"
		perl ~/mergepaths.pl "../f$1/$hf" <$f > "../f$1/nvm"
		mv "../f$1/nvm" "../f$1/$hf"
	done
	perl ~/calcstats.pl 1 < "../f$1/$hf" > "../f$1/sm-$hf"
done
rm annot
		
		#echo "Total in dir is $last"
		#for f in $fil
		#do
		#	mv "$f-0.txt" "../d/$f-root.txt"
		#	for j in $sq
		#	do
		#		if [ -f "../d/$f-root.txt" ]
		#		then
		#			if [ -f "$f-$j.txt" ]
		#			then
		#				perl ~/passc.pl "../d/$f-root.txt" < "$f-$j.txt" > "tmp4524515482"
		#				rm "$f-$j.txt"
		#				mv "tmp4524515482" "../d/$f-root.txt"
		#			fi
		#		fi
		#	done
		#	if [ -f "../d/$f-root.txt" ]
		#	then
		#		echo "Processed $f"
		#	else
		#		echo "Failed to process $f"
		#	fi
		#done
		
		#for f in $fil
		#do
		#	perl ~/passb.pl < $f > ../c/$f
		#	#rm "$f"
		#done
cd ..
echo "[Done]"
