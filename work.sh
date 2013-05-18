rhythmbox-client --hide
while true
do
	bash clean.sh
	speaker-test -t sine -b 1000 -p 1000 -f 1000 -l 2
	echo | date
	rhythmbox-client --play
	sleep 3300
	rhythmbox-client --pause
	speaker-test -t sine -b 1000 -p 1000 -f 1000 -l 2
	git add .
	git commit -a -m "$1"
	git push
	sleep 300
	echo | date
done
