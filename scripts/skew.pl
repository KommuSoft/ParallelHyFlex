while(!defined $skew) {
	$lin = <STDIN>;
	if($lin =~ /([0-9]+)\t([0-9]*(.[0-9]+)?)/) {
		$skew = $1;
		print "0\t$2\n";
	}
	else {
		print "$lin";
	}
}
while(<STDIN>) {
	if($_ =~ /([0-9]+)\t([0-9]*.([0-9]+)?)/) {
		$delta = $1-$skew;
		print "$delta\t$2\n";
	}
	else {
		print "$_";
	}
}
