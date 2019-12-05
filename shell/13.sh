#!/bin/bash
cd /opt/lampp/bin
if [ $1 = 1 ]
then
	cmd="select song_id from song_emotion where emotion=1 and strength=1"
elif [ $1 = 2 ]
then
	cmd="select song_id from song_emotion where emotion=$3-1 and strength=$2"
elif [ $1 = 3 ]
then
	cmd="select song_id from song_emotion where emotion=$3+1 and strength=$2"
elif [ $1 = 4 ]
then
	cmd="select song_id from song_emotion where emotion=$3 and strength=$2-1"
elif [ $1 = 5 ]
then
	cmd="select song_id from song_emotion where emotion=$3 and strength=$2+1"
elif [ $1 = 6 ]
then
	cmd="select song_id from song_emotion where emotion=$3-1 and strength=$2-1 union
	     select song_id from song_emotion where emotion=$3-1 and strength=$2 union
	     select song_id from song_emotion where emotion=$3 and strength=$2-1"
elif [ $1 = 7 ]
then
	cmd="select song_id from song_emotion where emotion=$3+1 and strength=$2+1 union
	     select song_id from song_emotion where emotion=$3+1 and strength=$2 union
	     select song_id from song_emotion where emotion=$3 and strength=$3+1"
elif [ $1 = 8 ]
then
	cmd="select song_id from song_emotion where emotion=$3+1 and strength=$2-1 union
	     select song_id from song_emotion where emotion=$3+1 and strength=$2 union
	     select song_id from song_emotion where emotion=$3 and strength=$2-1 "
elif [ $1 = 9 ]
then
	cmd="select song_id from song_emotion where emotion=$3-1 and strength=$2+1 union
	     select song_id from song_emotion where emotion=$3-1 and strenght=$2 union
	     select song_id from song_emotion where emotion=$3 and strength=$2+1"
fi
cnt=$(./mysql -u root -p000000 -s -e "use song_db;" -s -e "${cmd};")
echo "${cnt}"
exit
exec bash
