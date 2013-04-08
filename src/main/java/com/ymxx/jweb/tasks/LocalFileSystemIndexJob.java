package com.ymxx.jweb.tasks;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.ymxx.jweb.filesearch.Disk;
import com.ymxx.jweb.filesearch.WalkAndIndexThread;

public class LocalFileSystemIndexJob implements Job{

	private static File[] roots;
	private static File idxDir;
	static{
		roots = File.listRoots();
		idxDir = new File("D:/ymxx.windows.Server/tomcat/6.x/webapps/ROOT/System.File.Index");
	}
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		for(File disk:roots){
			try {
				//new WalkAndIndexThread(new Disk(disk,idxDir)).start();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
	}
	public static void main(String[] args) throws Exception{
		
		File [] idxs = new File("D:/ymxx.windows.Server\\tomcat\\6.x" +
				"\\webapps\\ROOT\\System.File.Index\\20130410").listFiles();
		
		ObjectInputStream in;
		for(File idx :idxs){
			
			if(!idx.getName().startsWith("E")){
				continue;
			}
			System.out.println("查询："+idx.getAbsolutePath());
			in = new ObjectInputStream(new FileInputStream(idx));
			while(true){
			
				try{
					File f = (File)in.readObject();
					if(f.length()/1024/1024 > 100){
						System.out.println(f.length()/1024/1024+"M\t"+f.getAbsolutePath());
					}
				}catch(Exception e){
					break;
				}
			}
			in.close();
			
		}
	}
}
