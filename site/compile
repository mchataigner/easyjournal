#!/bin/bash
for j in ../WEB-INF/lib/*.jar;
do
export CLASSPATH=$CLASSPATH:$j;
done


for i in $(find . -name "*.java");do
file="$(echo ${i%.java*}.class | sed 's/\/src/\.\/WEB-INF\/classes/g')";
if [ ! -e $file ];then
javac -d ../WEB-INF/classes/ -cp $CLASSPATH:../WEB-INF/classes/ -sourcepath ./src/ $i;
echo $i
else 
d1=$(stat -c %Y $i);
d2=$(stat -c %Y $(echo ${i%.java*}.class | sed 's/\/src/\.\/WEB-INF\/classes/g'));
if [ $((d1)) -gt $((d2)) ];then
javac -d ../WEB-INF/classes/ -cp $CLASSPATH:../WEB-INF/classes/ -sourcepath ./src/ $i;
echo $i
fi
fi
done


#java -cp $CLASSPATH:../WEB-INF/classes/ MainTest;
#java -cp $CLASSPATH:../WEB-INF/classes/ MainTest;
#java -cp $CLASSPATH:../WEB-INF/classes/ MainTest;

#for i in $(find . -name "*.java");do 
#echo "$(($(stat -c %Y $i) - $(stat -c %Y $(echo ${i%.java*}.class | sed 's/\/src/\.\/WEB-INF\/classes/g'))))
#";done
