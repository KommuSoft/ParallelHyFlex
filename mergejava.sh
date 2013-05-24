dat=$(find -name  "*.java")
for f in $dat
do
	echo "$f"
	cat "$f"
done
