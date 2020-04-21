#!/usr/bin/env bash

echo "请输入环境配置(prod/test)"
read env
echo "当前环境配置为 $env"

echo "==== git pull  ===="
git pull


echo "==== starting to build ===="
mvn package -DskipTests
echo "==== building finished  ===="

./startup.sh $env


