if not exist "bin" mkdir bin

REM Compilar los tests. Los tests dependen de la implementación de las clases testadas para compilar correctamente.

javac -classpath .\bin;.\lib\* ^
-encoding UTF-8 ^
-d .\bin ^
-sourcepath .\src;.\test ^
.\src\excepcion\*.java ^
.\src\tiempo\*.java ^
.\test\excepcion\*.java ^
.\test\tiempo\*.java 