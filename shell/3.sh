#!/bin/bash
cd /opt/lampp/bin
cmd="insert into user (id,username,password,gender,birth) values ($1,$2,$3,$4,$5)"
echo ${cmd}
cnt=$(./mysql -u root -p000000 -s -e "use user_db;" -s -e "${cmd};")
echo "${cnt}"
exit
exec bash
