#!/bin/bash
cd /opt/lampp/bin
cmd1="insert into p$1 (type,song_id) values (2,$2)"
cmd2="update song_count set search_times=search_times+1, ratio=like_times/(recom_times+search_times)*100 where song_id=$2"
cnt=$(./mysql -u root -p000000 -s -e "use user_db;" -s -e "${cmd1};" -s -e "use song_db;" -s -e "${cmd2};")
exit
exec bash
