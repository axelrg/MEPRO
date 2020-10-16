#!/bin/bash

if [ ! -d ./bin ]; then
    mkdir bin
fi

javac -classpath ./bin:./lib/* \
-encoding UTF-8 \
-d bin \
-sourcepath ./src \
./src/*.java