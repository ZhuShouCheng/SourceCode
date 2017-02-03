package regex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class SourceCounter
{
	static int NormalCode = 0;
	static int CommendCode = 0;
	static int WhiteCode = 0;
	
	public static void main(String[] args)
	{
		File f = new File("E:\\GitHub\\ChatOnline");
		File[] CodeFile = f.listFiles();
		for (File Child : CodeFile)
		{
			if (Child.getName().matches(".*\\.java$"))
			{
				Parse(Child);
			}
		}
		
		System.out.println("NormalCode:" + NormalCode);
		System.out.println("CommendCode:" + CommendCode);
		System.out.println("WhiteCode:" + WhiteCode);
	}

	private static void Parse(File f)
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line ="";
			boolean Commend = false;
			while ((line = br.readLine()) != null)
			{
				line = line.trim();
				if (line.matches("^\\s*"))
					WhiteCode ++;
				else if (line.startsWith("/*"))
				{
					CommendCode ++;
					Commend = true;
				}
				else if (true == Commend)
				{
					CommendCode++;
					if (line.endsWith("*/"))
					{
						Commend = false;
					}
				}
				else if (line.startsWith("//"))
					CommendCode++;
				else 
					NormalCode++;
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
