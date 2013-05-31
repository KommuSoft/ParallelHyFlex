cd "/tmp/process/"
cd "e"
mkdir -p "../es"
for f in *
do
	perl ~/skew.pl < "$f" > "../es/$f";
done
