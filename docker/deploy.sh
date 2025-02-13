#!/bin/bash

# 设置时区
TZ=Asia/Shanghai

# JVM参数
JAVA_OPTS="-server -Xms1g -Xmx1g -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=320m -XX:+HeapDumpOnOutOfMemoryError -XX:+PrintGCDateStamps -XX:+PrintGCDetails"
JAVA_OPTS="${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom"
JAVA_OPTS="${JAVA_OPTS} -Dfile.encoding=UTF-8"
JAVA_OPTS="${JAVA_OPTS} -Duser.timezone=${TZ}"
JAVA_OPTS="${JAVA_OPTS} -Dserver.port=8100"
JAVA_OPTS="${JAVA_OPTS} -Dspring.profiles.active=prod"

# 应用名称
APP_NAME=bryce-generator-server-0.0.1-SNAPSHOT.jar

# 启动脚本
case $1 in
"start") {
  echo "-------------------- 启动 bryce-generator --------------------"
  # shellcheck disable=SC2009
  pid=$(ps -ef | grep java | grep $APP_NAME | grep -v grep | awk '{print $2}')
  if [ -n "$pid" ]; then
    echo "-------------------- bryce-generator 正在运行，pid: $pid --------------------"
    exit 1
    else
      # shellcheck disable=SC2086
      nohup java -jar $JAVA_OPTS $APP_NAME >/dev/null 2>&1 &
      # shellcheck disable=SC2009
      pid=$(ps -ef | grep java | grep $APP_NAME | grep -v grep | awk '{print $2}')
      echo "-------------------- bryce-generator 启动成功，pid: $pid --------------------"
      exit 1
  fi
};;

"stop") {
  echo "-------------------- 停止 bryce-generator --------------------"
  # shellcheck disable=SC2009
  ps -ef | grep java | grep $APP_NAME | grep -v grep | awk '{print $2}' | xargs kill -9
};;

"restart") {
  echo "-------------------- 重启 bryce-generator --------------------"
  # shellcheck disable=SC2009
  pid=$(ps -ef | grep java | grep $APP_NAME | grep -v grep | awk '{print $2}')
  if [ -n "$pid" ]; then
    kill -9 "$pid"
  fi
  # shellcheck disable=SC2086
  nohup java -jar $JAVA_OPTS $APP_NAME >/dev/null 2>&1 &
};;

"status") {
  echo "-------------------- bryce-generator 状态 --------------------"
  # shellcheck disable=SC2009
  pid=$(ps -ef | grep java | grep $APP_NAME | grep -v grep | awk '{print $2}')
  if [ -z "$pid" ]; then
    echo "-------------------- bryce-generator 未运行 --------------------"
  else
    echo "-------------------- bryce-generator 正在运行，pid: $pid --------------------"
  fi
};;

"log") {
  if [ "$2" == "info" ] || [ "$2" == "warn" ] || [ "$2" == "error" ]; then
     echo "-------------------- bryce-generator $2 日志 --------------------"
    tail -n 75 logs/"$2".log
  elif [ -n "$2" ]; then
    echo "Usage: sh deploy.sh log [info|warn|error]"
  else
    echo "-------------------- bryce-generator info 日志 --------------------"
    tail -n 75 logs/info.log
  fi
};;

"help") {
  echo "Usage: sh deploy.sh [start|stop|restart|status|log|copy|remove]"
  echo "       deploy.sh log [info|warn|error]"
};;

"copy") {
  echo "-------------------- 复制 bryce-generator-server sql --------------------"
  cp ../db/mysql.sql ./mysql/db/
  echo "-------------------- 复制 bryce-generator-server jar包 --------------------"
  cp ../bryce-generator-server/target/bryce-generator-server-*.jar ./server/jar/
};;

"remove") {
  echo "-------------------- 删除 bryce-generator-server sql --------------------"
  rm -f ./mysql/db/*.sql
  echo "-------------------- 删除 bryce-generator-server jar包 --------------------"
  rm -f ./server/jar/bryce-boot-server-*.jar
};;

esac