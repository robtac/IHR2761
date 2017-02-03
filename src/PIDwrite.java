package org.usfirst.frc.team2761.robot;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

import org.usfirst.frc.team2761.robot.RobotMap;


public class PIDwrite {

	private String path;
	private boolean append_to_file = false;
	
	public PIDwrite(String file_path) {
		path = file_path;
	}
	
	public PIDwrite( String file_path , boolean append_value ) {

		path = file_path;
		append_to_file = append_value;

		}
	
	public void writeToFile( String textLine ) throws IOException {

		FileWriter write = new FileWriter( path , append_to_file);
		
		PrintWriter print_line = new PrintWriter( write );
		
		print_line.printf( "%s" + "%n" , textLine);
		
		
		print_line.close();
	}
	
}
