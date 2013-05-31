$head=<STDIN>;
$j = 0;
print "\\begin{tabular}{";
while($head=~/[^\t]+/g) {
	$j++;
	print "r\@{.}l";
}
print "}\n\\toprule\n";
$i = 0;
while($head=~/([^\t\n]+)/g) {
	if($i > 0) {
		print "\t&";
	}
	$col = $1;
	$col =~ s/#/\\#/g;
	$col =~ s/%/\\%/g;
	print "\\textbf{$col}&";
	$i++;
}
print "\\\\\\midrule\n";
while(<STDIN>) {
	$line =$_;
	$i = 0;
	while($line=~/(-?[0-9]+(\.[0-9]+)?)/g) {
		$num = $1;
		if($i > 0) {
			print "\t&";
		}
		if($num =~/\./) {
			$num =~ s/\./&/g;
			print "$num";
		}
		else {
			print "$num&";
		}
		$i++;
	}
	if($i > 0) {
		print "\\\\\n";
	}
}
$TIT = $ARGV[0];
$LAB = $ARGV[0];
print "\\bottomrule\n\\end{tabular}"
