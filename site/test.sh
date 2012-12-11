#!/bin/bash
cd ../WEB-INF/lib;
for j in $(ls *.jar);
do
export CLASSPATH=$CLASSPATH:$(pwd)/$j;
done


cd ../classes
java -cp $CLASSPATH:. MainTest