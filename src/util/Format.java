package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.junit.Test;

public class Format {

	@Test
	public void format() throws IOException{
		String file = "D:/eclipse-worksp/mye65/ccntrecuit/relation_identify/other/words-my.dic";
		FileInputStream fin = new FileInputStream(new File(file));
		BufferedReader br = new BufferedReader(new InputStreamReader(fin));
		String str ;
		String outfile = "D:/eclipse-worksp/mye65/ccntrecuit/relation_identify/other/words-my1.dic";
		PrintWriter pw = new PrintWriter(outfile);
		int i=0;
		while((str=br.readLine())!=null){
			pw.print(str+'\n');
			if(i++>10)
				break;
		}
		
		br.close();
		pw.close();
	}
}
