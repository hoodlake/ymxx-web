package com.ymxx.jweb.filesearch;


public class WalkAndIndexThread extends Thread{

	private Disk disk;
	public WalkAndIndexThread(Disk disk){
		
		this.disk = disk;
	}
	public void run(){
		disk.walkAndIndex();
	}
}
