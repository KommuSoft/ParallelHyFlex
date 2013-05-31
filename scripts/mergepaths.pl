$root = $ARGV[0];
open(OTHER, "$root");
$lina = <OTHER>;
if($lina =~ /([0-9]+)\t(.*)/) {
	$ta = $1;
	$ovala = $vala;
	$vala = $2;
}
$linb = <STDIN>;
if($linb =~ /([0-9]+)\t(.*)/) {
	$tb = $1;
	$ovalb = $valb;
	$valb = $2;
}
while(defined $lina || defined $linb) {
	if(!defined $linb || $ta < $tb) {
		print "$ta\t$vala\t$ovalb\n";
		$lina = <OTHER>;
		if($lina =~ /([0-9]+)\t(.*)/) {
			$ta = $1;
			$ovala = $vala;
			$vala = $2;
		}
		elsif(!defined $lina) {
			$ovala = $vala;
			$ta = 9000000;
		}
	}
	elsif(!defined $lina || $tb < $ta) {
		print "$tb\t$ovala\t$valb\n";
		$linb = <STDIN>;
		if($linb =~ /([0-9]+)\t(.*)/) {
			$tb = $1;
			$ovalb = $valb;
			$valb = $2;
		}
		elsif(!defined $linb) {
			$ovalb = $valb;
			$tb = 90000000;
		}
	}
	else {
		print "$ta\t$vala\t$valb\n";
		$lina = <OTHER>;
		if($lina =~ /([0-9]+)\t(.*)/) {
			$ta = $1;
			$ovala = $vala;
			$vala = $2;
		}
		elsif(!defined $lina) {
			$ovala = $vala;
			$ta = 90000000000;
		}
		$linb = <STDIN>;
		if($linb =~ /([0-9]+)\t(.*)/) {
			$tb = $1;
			$ovalb = $valb;
			$valb = $2;
		}
		elsif(!defined $linb) {
			$ovalb = $valb;
			$tb = 90000000000;
		}
	}
}
