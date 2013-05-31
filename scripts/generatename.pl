use POSIX;

sub log10 {
	my $n = shift;
	return log($n)/log(10);
}

$lina = <STDIN>;
if($lina =~ /#config ([0-9]+) ([0-9]+) ([0-9]*(.[0-9]*)?) ([0-9]+) ([0-9]+) ([0-9]*(.[0-9]*)?)/) {
	$a = $1;
	$b = $2;
	$c = 20*$3;
	$d = $5;
	$e = $6;
	$f = 20*$7;
}
$linb = <STDIN>;
if($linb =~ /([0-9]+)\t([0-9]+(.[0-9]*)?)/) {
	$val = $2;
	$g = ceil(log10($val));
}
print "=$a=$b=$c=$d=$e=$f=$g.txt"
