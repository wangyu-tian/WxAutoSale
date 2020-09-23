#! /bin/bash
version="1.0";
appName=wx-auto-sale-1.0-SNAPSHOT.jar;
runAppName=/root/java/$appName;

function usage() {
    echo "Usage: $0 (start|stop|restart|status)";
    echo "Example: $0 status";
    exit 1;
}

function start(){
  count=`ps -ef | grep java | grep $appName | grep -v grep | wc -l`
  if [ $count != 0 ]; then
    echo "$runAppName is running..."
  else
    echo "The $runAppName is starting..."
      nohup java -jar $runAppName > /dev/null 2> nohup.out &
  fi
}

function stop(){
  appId=`ps -ef | grep java | grep $appName | grep -v grep | awk '{print $2}'`
  if [ -z $appId ];then
    echo "$runAppName  not running..."
  else
    echo "The $runAppName is stoping..."
    kill $appId > /dev/null 2>&1
    echo "stop is complete"
    echo "PID : $appId"
  fi
}

function restart(){
  stop;
  start;
}

function status(){
  appId=`ps -ef | grep java | grep $appName | grep -v grep | awk '{print $2}'`
  if [ -z $appId ];then
    echo "$runAppName  not running..."
  else
    echo "The $runAppName is running..."
    echo "PID : $appId"
  fi
}

case $1 in start)start;;
           stop)stop;;
           status)status;;
           *)usage;;
         esac