#!/bin/bash

#   Licensed to the Apache Software Foundation (ASF) under one
#   or more contributor license agreements.  See the NOTICE file
#   distributed with this work for additional information
#   regarding copyright ownership.  The ASF licenses this file
#   to you under the Apache License, Version 2.0 (the
#   "License"); you may not use this file except in compliance
#   with the License.  You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
#   Unless required by applicable law or agreed to in writing,
#   software distributed under the License is distributed on an
#   #  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
#   KIND, either express or implied.  See the License for the
#   specific language governing permissions and limitations
#   under the License.

# Note:  Do not output anything in this script file, any output
#        may be inadvertantly placed in any output files if
#        output redirection is used.

if [ -z "$JAVACMD" ] ; then
  if [ -n "$JAVA_HOME"  ] ; then
    JAVACMD="$JAVA_HOME/bin/java"
  else
    JAVACMD="`which java`"
  fi
fi

# -----------------------------------------------------------------------------
# System configuration
# -----------------------------------------------------------------------------
# Might fail if $0 is a link
ROOT_HOME=`dirname "$0"`/../..
JAVAARGS=
#"-Xmx4092m"
TIME=/usr/bin/time 
#-f '"%E real,%U user,%S sys"'
#         %Uuser %Ssystem %Eelapsed %PCPU (%Xtext+%Ddata %Mmax)k
#         %Iinputs+%Ooutputs (%Fmajor+%Rminor)pagefaults %Wswaps
#              U      Total number of CPU-seconds that the process used directly (in user mode), in seconds.
#              S      Total number of CPU-seconds used by the system on behalf of the process (in kernel mode), in seconds.
#              E      Elapsed real (wall clock) time used by the process, in [hours:]minutes:seconds.
#              P      Percentage of the CPU that this job got.  This is just user + system times divided by the total running time. It also prints a percentage sign.
#              I      Number of file system inputs by the process.
#              O      Number of file system outputs by the process.


# -----------------------------------------------------------------------------
# General configuration
# -----------------------------------------------------------------------------
RUNNUMBER=100000
RUNNUMBER=1000000
DATE=`date +%Y%m%d%H%M`
mkdir -p ${ROOT_HOME}/tmp/simpleTokenizer/benchmark/
LOGFILE=${ROOT_HOME}/tmp/simpleTokenizer/benchmark/log

CMD="java -cp bin fr/univnantes/lina/app/tokenizer/RunSimpleTokenizer"
CHARACTERFUNCTIONING="-c \"'\" 3 -c \"-\" 3"
OUTPUTFORMAT="-o 2  -n '.' -n '!' -n '?'"
#DEBUG="2>> $LOGFILE"
#DEBUG=2>> ./resources/simpleTokenizer/../../tmp/simpleTokenizer/benchmark/log 1>/dev/null 
#DEBUG='2>> ./resources/simpleTokenizer/../../tmp/simpleTokenizer/benchmark/log '
#"-v 2>/tmp/log"

# -----------------------------------------------------------------------------
# Test 1
# -----------------------------------------------------------------------------
TEST=QEvsFlagI
echo 
echo Testing $TEST - $DATE
echo "" >> $LOGFILE
echo "" >> $LOGFILE
echo Testing $TEST - $DATE>> $LOGFILE

INPUTTESTFILE=$ROOT_HOME/tmp/simpleTokenizer/benchmark/QEvsFlagI-$RUNNUMBER.test

REGEXUNITS="-u ${ROOT_HOME}/resources/simpleTokenizer/benchmark/regexFlagI.csv"
echo "$CMD -i $INPUTTESTFILE $REGEXUNITS $CHARACTERFUNCTIONING $OUTPUTFORMAT 2>> $LOGFILE 1>/dev/null "
echo "" >> $LOGFILE
echo "$CMD -i $INPUTTESTFILE $REGEXUNITS $CHARACTERFUNCTIONING $OUTPUTFORMAT 2>> $LOGFILE 1>/dev/null " >> $LOGFILE
$TIME $CMD -i $INPUTTESTFILE $REGEXUNITS $CHARACTERFUNCTIONING $OUTPUTFORMAT 2>> $LOGFILE 1>/dev/null 
echo "test $TEST - run time done $REGEXUNITS"

REGEXUNITS="-u ${ROOT_HOME}/resources/simpleTokenizer/benchmark/regexQE.csv"
echo "$CMD -i $INPUTTESTFILE $REGEXUNITS $CHARACTERFUNCTIONING $OUTPUTFORMAT 2>> $LOGFILE 1>/dev/null "
echo "" >> $LOGFILE
echo "$CMD -i $INPUTTESTFILE $REGEXUNITS $CHARACTERFUNCTIONING $OUTPUTFORMAT 2>> $LOGFILE 1>/dev/null ">> $LOGFILE
$TIME $CMD -i $INPUTTESTFILE $REGEXUNITS $CHARACTERFUNCTIONING $OUTPUTFORMAT 2>> $LOGFILE 1>/dev/null 
echo "test $TEST - run time done $REGEXUNITS"


