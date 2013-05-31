$delta = $ARGV[$1];
$barrier = $delta;
while(<STDIN>) {
	$line = $_;
	if($line =~ /^([0-9]+)\t(.*)/) {
		$t = $1;
		while($t > $barrier) {
			print "$barrier\t$val\n";
			$barrier += $delta;
		}
		$val = $2;
	}
	else {
		print "$line\n";
	}
}
