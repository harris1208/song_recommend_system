#!/bin/bash
cd /opt/lampp/bin
cmd="select song_id from p$1 where type<3"
cnt=$(./mysql -u root -p000000 -s -e "use user_db;" -s -e "${cmd};")
echo "${cnt}"
exit
exec bash
