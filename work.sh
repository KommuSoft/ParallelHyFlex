while true
do
	bash clean.sh
	speaker-test -t sine -b 1000 -p 1000 -f 1000 -l 2 > /dev/null
	rhythmbox-client --play
	echo | date
	sleep 3300
	bash clean.sh
	rhythmbox-client --pause
	speaker-test -t sine -b 1000 -p 1000 -f 1000 -l 2 > /dev/null
	bash commitwork.sh "$1"
	sleep 300
	echo | date
done
