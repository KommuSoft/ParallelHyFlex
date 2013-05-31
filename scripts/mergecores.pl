$solvetime = -1;
@list = <STDIN>;
$_ = $list[$0];
@single = split(/\t/);
foreach(@list) {
	@other = split(/\t/);
	if($single[0] < 0) {
		$single[0] = $other[0];
	}
	elsif($other[0] >= 0 && $other[0] < $single[0]) {
		$single[0] = $other[0];
	}
	for($i=1; $i < $#single; $i++) {
		if($other[$i] < $single[$i]) {
			$single[$i] = $other[$i];
		}
	}
}
print "$single[0]";
for($i=1; $i < $#single; $i++) {
	$text = $single[$i];
	chomp($text);
	print "\t$text";
}
print "\n"
