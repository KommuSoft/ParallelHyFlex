#!/bin/bash

for p in {"*.~","*.*~*","*.backup","*.aux","*.bbl","*.blg","*.ilg","*.ind","*.lof","*.log","*.lot","*.out","*.toc"}
do
	echo "$p"
	fall=$(find . -name "$p")
	echo "$fall"
	for f in $fall
	do
		echo "Removing $f..."
		rm "$f"
	done
done
