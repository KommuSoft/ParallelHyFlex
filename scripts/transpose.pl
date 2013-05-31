$i = 0;
$j = 0;
my @data;
while(<STDIN>) {
	@list = split(/\t/);
	$j = 0;
	foreach(@list) {
		chomp($_);
		$data{"$i $j"} = $_;
		$j++;
	}
	$i++;
}
$n = $i;#$#data;
$m = $j;#$#first;
for($i = 0; $i < $m; $i++) {
	for($j = 0; $j < $n; $j++) {
		$val = $data{"$j $i"};
		print "$val\t"
	}
	print "\n"
}
