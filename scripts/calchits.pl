$ep = $ARGV[0];
$orig = 4.26*(10**($ep));
$val = 25;
$line = <STDIN>;
while($val >= 0 && defined $line) {
	#var	n	min	max	median	mean	stdev	q1	q3	-95%	+95%
	#0	50	33	75	53	51.44	8.61456	46	58	34.5555	68.3245
	if($line =~ /^([0-9]+)\t(-?[0-9]+(\.[0-9]*)?)\t(-?[0-9]+(\.[0-9]*)?)\t(-?[0-9]+(\.[0-9]*)?)\t(-?[0-9]+(\.[0-9]*)?)\t(-?[0-9]+(\.[0-9]*)?)/) {
		while($val >= 0 && $6 <= $val*$orig/1000) {
			print "$val\t$1\n";
			$val--;
		}
	}
	$line = <STDIN>;
	
}
