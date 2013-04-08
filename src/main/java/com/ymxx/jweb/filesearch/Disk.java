package com.ymxx.jweb.filesearch;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Date;

import com.ymxx.jweb.util.DateTimeUtil;
/**
 * @author Administrator
 *
 */
public class Disk {

	
	/*
	 * 磁盘文件
	 */
	private File disk;
	/*
	 * 索引文件
	 */
	private File idxFile;

	/*
	 * 构造方法
	 */
	public Disk(File disk,File idxDir)throws Exception{
		if(disk == null||!disk.isDirectory()){
			throw new Exception("参数非法，dir="+disk+"\n");
		}
		this.disk = disk;
		String dirname = DateTimeUtil.date2Str(new Date(), "yyyyMMdd");
		File diskIdxDir = new File(idxDir.getAbsolutePath()+File.separator+dirname);
		if(!diskIdxDir.exists()){
			diskIdxDir.mkdir();
		}
		String idxName = disk.getAbsolutePath().substring(0, 1);
		this.idxFile = new File(diskIdxDir.getAbsolutePath()+File.separator+idxName+".idx");
	}
	/*
	 * 遍历和索引磁盘文件
	 */
	private void walkAndIdxDisk(File dir,ObjectOutput objOut)throws IOException{
		File [] files = dir.listFiles();		
		File tmp = null;
		if(files != null){
			for(int i = 0 ; i < files.length ; i++){
				tmp = files[i];
				objOut.writeObject(tmp);
				
				if(tmp.isDirectory()){
					walkAndIdxDisk(tmp,objOut);
				}
			}
		}
	}
	/*
	 * 
	 */
	public void walkAndIndex(){
		ObjectOutput objOut;
		try {
			objOut = new ObjectOutputStream(
					new FileOutputStream(idxFile));
			System.out.println("遍历磁盘:"+this.disk.getAbsolutePath()+" 开始创建索引");
			long begin = System.currentTimeMillis();
			walkAndIdxDisk(this.disk,objOut);
			long end = System.currentTimeMillis();
			System.out.println("遍历目录:"+this.disk.getAbsolutePath()+"结束，索引创建成功，用时："+DateTimeUtil.getDurTime(end-begin));
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
