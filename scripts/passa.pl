$st = 0;
while(<STDIN>) {
	$line = $_;
#	print "process \"$line\"";
	if($line =~ /^([0-9]+)\tST$/) {
		$st = $1;
		print "#config $a $b $c $d $e $f $g\n";
	}
	elsif($line =~ /^([0-9]+)\tMS ([0-9]+) ([0-9]*(\.[0-9]*)?)$/) {
		$x = $1-$st;
		$f = $3;
		print "$x\t$f\n";
	}
	elsif($line =~ /^([0-9]+) ([0-9]*(\.[0-9]*)?)$/) {
		if($1 == 0) {
			$a = $2;
		}
		elsif($1 == 1) {
			$b = $2;
		}
		elsif($1 == 1000) {
			$c = $2;
		}
		elsif($1 == 1001) {
			$d = $2;
		}
		elsif($1 == 1002) {
			$e = $2;
		}
		elsif($1 == 1003) {
			$f = $2;
		}
	}
}
