#!/bin/bash
cd /opt/lampp/bin
cmd="select id from user where username = $1 and password=$2"
cnt=$(./mysql -u root -p000000 -s -e "use user_db;" -s -e "${cmd};")
echo "${cnt}"
exit
exec bash
