$st = 0;
$min = 200000000;
while(<STDIN>) {
	$line = $_;
	if($line =~ /^([0-9]+)\t([0-9]*(\.[0-9]*)?)/) {
		if($2 < $min) {
			$min = $2;
			print "$line";
		}
	}
	else {
		print "$line";
	}
}
