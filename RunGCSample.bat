@echo on

setlocal

set CLASSPATH=.\WEB-INF\classes;%CLASSPATH%

java ^
-Xloggc:.\gclogs\gc.log ^
-XX:+PrintGCDetails -XX:+PrintGCDateStamps ^
-XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=10M ^
-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=.\gclogs\ ^
com.gasyou.gam.GCSample

endlocal