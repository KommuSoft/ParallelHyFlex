awk '{sum=0; opening=$1; closing=$NF; min=$1; max=$1; for (i=1; i<=NF; i++) {sum=sum+$i; if ($i<min) min=$i; if ($i>max) max=$i}; print sum/NF, opening, closing, min, max}' $1
