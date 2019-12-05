#!/bin/bash
cd /opt/lampp/bin
cmd="create table p$1 (type int,song_id int)"
echo ${cmd}
cnt=$(./mysql -u root -p000000 -s -e "use user_db;" -s -e "${cmd};")
echo "${cnt}"
exit
exec bash
