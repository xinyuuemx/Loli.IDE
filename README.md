# Loli.IDE
A project on our Programming Language subject that is an application of building virtual machines.
PROGRAMMING LANGUAGE
PROJECT DOCUMENTATION

Note: Since this project is still in development, some components and/or parts of this document may change based on the upcoming update/version of the project and/or changes made due to bugs, functionality, or developer’s preferences.

Project Name: Loli.IDE

Objective: 
(1)  To create an interactive-learning framework about programming for beginners/children (main target is on gradeschool level) through the use of Integrated Development Environment (IDE) application.
(2) To be able to implement pseudocode as a compilable syntax code to be able to check if the algorithm that the pseudocode implies is precise.
(3) To be able to learn how to imply databases in an IDE without prior knowledge in SQL statements. (Since SQL Statements are kind of complex when combined with program code/syntax)

Functions: Mainly divided in two parts – (1) Basic coding syntax/Algorithm application through coding and (2) Basic Database Coding(as planned but 2nd one is not yet implemented).

Platform: Java

IDE Mechanism: This project is Java-based and has a virtual machine (defined in the code as a class named “VM()” ) which serves as the  framework for the whole project in order to function properly. This virtual machine has three registers: (1) instruction pointer, (2) stack pointer, and (3) frame pointer. It also currently has 23 instruction sets defined as:

public static final short NEW=1; 
public static final short OPEN=2;  
public static final short ICONST=3;
public static final short IADD=4;
public static final short ISUB=5;
public static final short IMUL=6;
public static final short IDIV=7;
public static final short FADD=8;
public static final short FSUB=9;
public static final short FMUL=10;
public static final short FDIV=11;
public static final short BCONST=12;
public static final short BTRUE=13;
public static final short BFALSE=14;
public static final short CCONST=15;
public static final short CFUNC=16;
public static final short LOAD=17;	
public static final short STORE=18;
public static final short GLOAD=19;	
public static final short GSTORE=20;
public static final short PRINT=21;
public static final short POP=22;
public static final short END=23;	

Through this instruction sets, users can make and compile simple programs which currently can execute arithmetic operations and character data type-customization.

Plans: 
Implementations of arrays on different data types the IDE includes
Database operations and instruction sets
Improved Graphical User Interface for easier usage
Additional utilites for the Menu Bar
Syntax proofreading without compilation
