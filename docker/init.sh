#!/usr/bin/env bash

sudo groupadd docker     #添加docker用户组
sudo gpasswd -a -G $USER docker     #将登陆用户加入到docker用户组中
newgrp docker     #更新用户组
docker ps    #测试docker命令是否可以使用sudo正常使用

./0-clean.sh
./1-image.sh
./2-network.sh
./3-container.sh
