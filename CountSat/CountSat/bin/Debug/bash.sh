#!/bin/bash
for n in {1..32}
do
	let "kmax = 5 * $n"
	kseq=$(seq $n $kmax)
	for k in $kseq
	do
		let "khmax = $k/3"
		khseq=$(seq 0 $khmax)
		for kh in $khseq
		do
			mono CountSat.exe "50" "$n" "$k" "$kh" > "data/count-$n-$k-$kh.dat"
		done
	done
done
