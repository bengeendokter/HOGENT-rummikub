@SuppressWarnings("module")
module rummikubG16
{

	exports persistentie;
	exports cui;
	exports utility;
	exports gui;
	exports main;
	exports domein;
	exports testen;
	exports exceptions;

	requires java.sql;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.graphics;
	requires org.junit.jupiter.api;
	requires mysql.connector.java;
}