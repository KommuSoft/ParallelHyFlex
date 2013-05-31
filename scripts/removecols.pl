@cols = split(/,/,$ARGV[0]);
$n = $#cols;
#print STDERR "@cols/$n";
while(<STDIN>) {
	$line = $_;
	$i = 0;
	$j = 0;
	$k = 0;
	while($line =~ /([^\t\n]+)/g) {
		if($j <= $n && $i == $cols[$j]) {
			$j++;
		}
		else {
			if($k > 0) {
				print "\t";
			}
			print "$1";
			$k++;
		}
		$i++;
	}
	print "\n";
}
