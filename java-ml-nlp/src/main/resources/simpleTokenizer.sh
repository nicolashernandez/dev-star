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

# Might fail if $0 is a link
JAVAMLNLP_HOME=`dirname "$0"`

JAVAARGS=
#"-Xmx4092m"

#$JAVACMD $JAVAARGS -jar $JAVAMLNLP_HOME/lib/ftb++-*.jar $@
#java -cp bin fr.univnantes.lina.app.ftbplusplus.Process merge+revise+extend -i ../data/FrenchTreebank/corpus-tagged/ -o  /tmp/FrenchTreebank-merged-revised-extended.xml 2>> /tmp/FrenchTreebank-merged-revised-extended.xml.log


