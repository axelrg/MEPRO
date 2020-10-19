#!/bin/bash

if [ ! -d ./bin ]; then
    mkdir bin
fi

javac -classpath ./bin:./lib/* \
-encoding UTF-8 \
-d bin \
-sourcepath ./src \
./src/juego/util/*.java \
./src/juego/modelo/*.java \
./src/juego/control/*.java \
./src/juego/interfaz/*.java