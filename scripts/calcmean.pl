my @sum;
my @count;
$n = 0;
foreach(<STDIN>) {
	$n++;
	$i = 0;
	foreach(split(/\t/)) {
		if($_ >= 0) {
			$sum[$i] += $_;
			$count[$i]++;
			$i++;
		}
	}
}
for($j = 0; $j < $i; $j++) {
	$sum[$j] /= $count[$j];
}
print "@sum\n";
