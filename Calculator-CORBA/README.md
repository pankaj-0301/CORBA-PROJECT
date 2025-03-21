# Calculator-CORBA
A distributed calculator using CORBA architecture.
Please see the root folder for the project description.

## How to Compile
### Server Side
idlj Calc.idl
javac *.java Calculator/*.java

### Client Side
javac *.java Calculator/*.java

## How to Run
You will need to open three terminal windows.

Terminal 1:
orbd -ORBInitialPort 1050 -ORBInitialHost localhost

Terminal 2:
java StartServer -ORBInitialPort 1050 -ORBInitialHost localhost

Terminal 3:
java StartClient -ORBInitialPort 1050 -ORBInitialHost localhost