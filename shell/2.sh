#!/bin/bash
cd /opt/lampp/bin
cmd="select count(*) from user"
cnt=$(./mysql -u root -p000000 -s -e "use user_db;" -s -e "${cmd};")
echo "${cnt}"
exit
exec bash
