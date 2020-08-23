#!/bin/bash
#project=bs-cloud
module=cloud-web-server
version=3.0.0
moduleJar="$module"-"$version".jar

for i in `ps ux | grep "$moduleJar" | grep -v grep | awk '{print $2}'`
do
  echo "安全退出进程$i"
  kill -15 $i
done

sleep 5

for i in `ps ux | grep "$moduleJar" | grep -v grep | awk '{print $2}'`
do
  echo "强制停止进程$i"
  kill -9 $i
done
