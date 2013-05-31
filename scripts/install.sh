export PATH="/usr/lib/jvm/java-7-openjdk-amd64/jre/bin:$PATH"
export MPJ_HOME="/home/s0198856/Libraries/mpj-v0_38"
mkdir -p /tmp/phf
cp -r "/media/New Volume/testing2/ParallelHyFlex" "/tmp/phf"
cp -r "/media/New Volume/testing2/samples" "/tmp/phf"
cp /media/New\ Volume/testing2/* "/tmp/phf"
cd /tmp/phf
bash runlarge.sh
