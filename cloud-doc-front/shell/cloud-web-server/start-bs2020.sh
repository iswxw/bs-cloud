#!/bin/bash
#project=bs-cloud
module=cloud-web-server
version=3.0.0
moduleJar="$module"-"$version".jar

nohup java -Djava.security.egd=file:/dev/./urandom -Dfile.encoding=UTF-8 -Xmx1g -Xms1g -jar "$moduleJar" --server.port=8081 --system.node.name=node-8081 1>node-8081.log 2>&1 &
