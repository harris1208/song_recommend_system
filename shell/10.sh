#!/bin/bash
cd /opt/lampp/bin
cmd1="update song_count set like_times=like_times+1 ,  ratio=like_times/(recom_times+search_times)*100 where song_id = $1"
cmd2="insert into p$2 (type,song_id) values (3,$1)"
cnt=$(./mysql -u root -p000000 -s -e "use song_db;" -s -e "${cmd1};" -s -e "use user_db;" -s -e "${cmd2};")
exit
exec bash
