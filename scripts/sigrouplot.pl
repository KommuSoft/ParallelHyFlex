@VS = split(/ /,$ARGV[0]);
$n = $#VS;
while(<STDIN>) {
	$j = 0;
	$a = 1;
	$lin = $_;
	while (/([0-9]+)/g) {
	 	  if($j < $n) {
	 	  	if($1 < $VS[$j] || $1 > $VS[$j+1]) {
	 	  		$a = 0;
	 	  	}
	 	  	$j += 2;
	 	  }
	 }
	 if($a == 1) {
	 	print "$lin";
	 }
}
