#!/bin/bash

for p in {"*.~","*.*~*","*.backup","*.aux","*.bbl","*.blg","*.ilg","*.ind","*.lof","*.log","*.lot","*.out","*.toc"}
do
	fall=$(find . -name "$p")
	for f in $fall
	do
		rm "$f"
	done
done
