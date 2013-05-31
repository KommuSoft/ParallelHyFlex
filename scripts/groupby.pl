$w = $ARGV[0];
$cpun = "([A-Za-z]+)";
$numb = "([0-9]+)";
$pa = $w%2; $w /= 2;
$pb = $w%2; $w /= 2;
$pc = $w%2; $w /= 2;
$pd = $w%2; $w /= 2;
$pe = $w%2; $w /= 2;
$pf = $w%2; $w /= 2;
$pg = $w%2; $w /= 2;
$ph = $w%2; $w /= 2;
$pi = $w%2; $w /= 2;
$pj = $w%2; $w /= 2;
my %hash;
while(<STDIN>) {
	if($_ =~ /([A-Za-z]+)=([0-9]+)=([0-9]+)=([0-9]+)=([0-9]+)=([0-9]+)=([0-9]+)=([0-9]+)=([0-9]+)=([0-9]+)/) {
		$va = $cpun; $vb = $numb; $vc = $numb; $vd = $numb; $ve = $numb; $vf = $numb; $vg = $numb; $vh = $numb; $vi = $numb; $vj = $numb;
		$rs = "";
		if($pa) {$va = $1; $rs = "$rs-$1";}
		if($pb) {$vb = $2; $rs = "$rs-$2";}
		if($pc) {$vc = $3; $rs = "$rs-$3";}
		if($pd) {$vd = $4; $rs = "$rs-$4";}
		if($pe) {$ve = $5; $rs = "$rs-$5";}
		if($pf) {$vf = $6; $rs = "$rs-$6";}
		if($pg) {$vg = $7; $rs = "$rs-$7";}
		if($ph) {$vh = $8; $rs = "$rs-$8";}
		if($pi) {$vi = $9; $rs = "$rs-$9";}
		if($pj) {$vj = $10; $rs = "$rs-$10";}
	}
	$res = "$va=$vb=$vc=$vd=$ve=$vf=$vg=$vh=$vi=$vj\\.txt\n";
	if(!exists $hash{$res}) {
		$hash{$res}=1;
		print $res;
		print STDERR "summary$rs.txt\n"
	}
}

sub dec2bin {
    my $str = unpack("B32", pack("N", shift));
    $str =~ s/^0+(?=\d)//;   # otherwise you'll get leading zeros
    return $str;
}
