fls=$(find . -name "*.class")
for f in $fls
do
	rm "$f"
done
