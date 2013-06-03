$orig = $ARGV[0];
@vals = split(/,/,$ARGV[1]);
$line = <STDIN>;
$j = 0;
print "$vals\t$orig\n";
while($j <= $#vals && defined $line) {
	#var	n	min	max	median	mean	stdev	q1	q3	-95%	+95%
	#0	50	33	75	53	51.44	8.61456	46	58	34.5555	68.3245
	if($line =~ /^([0-9]+)\t(-?[0-9]+(\.[0-9]*)?)\t(-?[0-9]+(\.[0-9]*)?)\t(-?[0-9]+(\.[0-9]*)?)\t(-?[0-9]+(\.[0-9]*)?)\t(-?[0-9]+(\.[0-9]*)?)/) {
		while($j <= $#vals && $6 <= $vals[$j]*$orig/10000) {
			$val = $vals[$j];
			print "$val\t$1\n";
			$j++;
		}
	}
	$line = <STDIN>;
	
}
