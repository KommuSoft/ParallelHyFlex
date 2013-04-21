page=$(wget -S -O - "$1" 2> /dev/null)
year=$(date "+%Y")
month=$(date "+%b")
author=$(echo "$page" | grep -P "<meta +name=\"author\"" | head -n 1 | cut -s -f4 -d'"')
title=$(echo "$page" | grep -P "<title>[^<]*</title>" | head -n 1 | perl -pi -e's/.*<title> *//g' | perl -pi -e's/ *<\/title>.*//g')
datlook=$(date "+%Y-%m-%d")
echo "@ONLINE{$2,"
echo "    author = \"{$author}\","
echo "    title = \"{$title}\","
echo "    month = {$month},"
echo "    year = {$year},"
echo "    url = {$1},"
echo "    howpublished = {URL: \url{$1}, laatst nagekeken op $datlook},"
echo "}"
