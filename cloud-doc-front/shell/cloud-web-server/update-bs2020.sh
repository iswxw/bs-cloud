#!/bin/bash
workHome=$(pwd)
sourceHome="$workHome"/source
if [ -f $workHome/update.pid ]; then
    echo "正在更新中，请稍候再试！"
    exit 1
fi
echo "当前发布程序进程ID：$$"
echo $$ > $workHome/update.pid

project=bs-cloud
version=3.0.0

# compile
cd $sourceHome/$project
git reset --hard
git checkout master
git pull
#git log -3
mvn clean package -Dmaven.test.skip=true

cd $workHome

module=cloud-web-server
moduleJar="$module"-"$version".jar
#stop bs-cloud
./stop-bs2020.sh

# deploy bs-cloud
rm -rf "$workHome"/"$moduleJar"
cp "$sourceHome"/"$project"/"$module"/target/"$moduleJar" "$workHome"/"$moduleJar"

# start bs-cloud
./start-bs2020.sh

rm -rf $workHome/update.pid
