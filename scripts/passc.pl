$st = 0;
$root = $ARGV[0];
open(OTHER, "$root");
$min = 200000000;
$ta = 0;
$tb = 0;
$mina = $min;
$minb = $min;
$tim = 0;
<OTHER>;
$linb = <OTHER>;
if($linb =~ /^([0-9]+)\t([0-9]*(\.[0-9]*)?)/) {
	$tb = $1;
	$minb = $2;
}
$lina = <STDIN>;
print $lina;
while(<STDIN>) {
	$lina = $_;
	if($lina =~ /^([0-9]+)\t([0-9]*(\.[0-9]*)?)/) {
		$ta = $1;
		$mina = $2;
	}
	while($tb < $ta) {
		if($minb < $min) {
			$min = $minb;
			print "$linb";
		}
		$linb = <OTHER>;
		if(!defined $linb) {
			$tb = 200000000;
			$minb = $min+1;
		}
		elsif($linb =~ /^([0-9]+)\t([0-9]*(\.[0-9]*)?)/) {
			$tb = $1;
			$minb = $2;
		}
	}
	if($mina < $min) {
		$min = $mina;
		print "$lina";
	}
}
if($minb < $min) {
	$min = $minb;
	print "$linb";
	while(<OTHER>) {
		print "$_";
	}
}
