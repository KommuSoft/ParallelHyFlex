use strict;
use warnings;

use List::Util qw< min max >;

sub by_number {
    if ($a < $b){ -1 } elsif ($a > $b) { 1 } else { 0 }
}


#
my $number_rx = qr{

  # leading sign, positive or negative
    (?: [+-] ? )

  # mantissa
    (?= [0123456789.] )
    (?: 
        # "N" or "N." or "N.N"
        (?:
            (?: [0123456789] +     )
            (?:
                (?: [.] )
                (?: [0123456789] * )
            ) ?
      |
        # ".N", no leading digits
            (?:
                (?: [.] )
                (?: [0123456789] + )
            ) 
        )
    )

  # abscissa
    (?:
        (?: [Ee] )
        (?:
            (?: [+-] ? )
            (?: [0123456789] + )
        )
        |
    )
}x;
my $skip = $ARGV[0];
my $j = 0;
print "#";	
while($j < $skip) {
	$j++;
	print "var\t";
}
print "n\tmin\tmax\tmedian\tmean\tstdev\tq1\tq3\t-95%\t+95%\n";
while (<STDIN>) {
	 if($_ =~ /#.*/) {
	 	print $_
	 }
	 else {
		 my $n = 0;
		 my $sum = 0;
		 my @values = ();
		 my %seen = ();
		 my $j = 0;
		 while (/($number_rx)/g) {
		 	  if($j < $skip) {
		 	  	$j++;
		 	  	print "$1\t";
		 	  }
		 	  else {
				  my $num = 0 + $1;  # 0+ is so numbers in alternate form count as same
				  $sum += $num;
				  push @values, $num;
				  $seen{$num}++;
				  $n++;
		     }
		 }
		 if($n != 0) {
			my $mean = $sum / $n;
			my $sqsum = 0;
			for (@values) {
				 $sqsum += ( ($_-$mean) ** 2 );
			}
			if($n > 1) {
				$sqsum /= $n-1;
			}
			my $stdev = sqrt($sqsum);

			my $max_seen_count = max values %seen;

			my $median;
			my $mid = int @values/2;
			my @sorted_values = sort by_number @values;
			if (@values % 2) {
				 $median = $sorted_values[ $mid ];
			} else {
				 $median = ($sorted_values[$mid-1] + $sorted_values[$mid])/2;
			}
			my $q1i = int @values/4;
			my $q1;
			if (@values % 4) {
				 $q1 = $sorted_values[ $q1i ];
			} else {
				 $q1 = ($sorted_values[$q1i-1] + $sorted_values[$q1i])/2;
			}
			
			my $q3i = int 3*@values/4;
			my $q3;
			if (@values % 4) {
				 $q3 = $sorted_values[ $q3i ];
			} else {
				 $q3 = ($sorted_values[$q3i-1] + $sorted_values[$q3i])/2;
			}

			my $min = min @values;
			my $max = max @values;
			printf "%d\t%g\t%g\t", $n, $min, $max;
			printf "%g\t%g\t%g\t%g\t%g\t%g\t%g\n", $median, $mean, $stdev, $q1, $q3, $mean-1.96*$stdev, $mean+1.96*$stdev;
		}
  }
}
