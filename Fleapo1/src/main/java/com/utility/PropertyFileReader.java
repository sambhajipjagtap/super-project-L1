package com.utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.testbase.TestBase;


public class PropertyFileReader extends TestBase{

	public static FileOutputStream fos;

	public static void getProperty() throws IOException
	{
		FileInputStream in = new FileInputStream("resources/config.properties");
		prop.load(in);
		in.close();
	}

	public static void setProperty() throws IOException
	{
		fos = new FileOutputStream("resources/config.properties");
		fos.close();
	}
}
