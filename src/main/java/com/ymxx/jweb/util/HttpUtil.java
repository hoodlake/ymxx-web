package com.ymxx.jweb.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

public class HttpUtil {

	private HttpUtil(){}
	/*
	 * 获取远程文件的文件名
	 */
	private static String getRemoteFileName(String path){
		String [] str = path.split("/");
		return str[str.length-1] != null?str[str.length-1]:null;
	}
	/**
	 * @param destUrl
	 * @param dirName
	 * @throws Exception
	 */
	public static Long download(String destUrl,String dirName) throws Exception { 
		File dir = new File(dirName);
		if(!dir.exists()){
			System.out.println("目录 "+dirName+"不存在，创建该目录");
			dir.mkdir();
		}
		FileOutputStream fos = null;   
		BufferedInputStream bis = null;   
		HttpURLConnection httpUrl = null;	
		URL url = null;   
		byte[] buf = new byte[809600]; 
		int size = 0;
		long totalSize = 0;
		url = new URL(destUrl);
		String fileName = new String(getRemoteFileName(url.getFile()));
		httpUrl = (HttpURLConnection) url.openConnection();
		if(httpUrl == null){
			System.out.println("连接失败 null"+destUrl);
			return -1l;
		}
		System.out.println("链接成功");
		httpUrl.connect();
		totalSize = httpUrl.getContentLength();
		if(totalSize <= 0){
			System.out.println("连接失败 <0 "+destUrl);
			return -1l;
		}
		
		bis = new BufferedInputStream(httpUrl.getInputStream());   
		
		fos = new FileOutputStream(dirName+"/"+fileName);   
	     
		System.out.println("正在获取链接[" + destUrl + "]的内容...\n" +
							"将其保存为文件["+dirName+"/" + fileName + "]\n" +
							"该文件大小为："+totalSize/1024+" KB"); 
	
		while ( (size = bis.read(buf)) != -1){
			fos.write(buf, 0, size);
		}
		fos.close();   
		bis.close();   
		httpUrl.disconnect();
		return totalSize;
	}
	public static String decodeThunderUrl(String url) throws IOException{
		
		url = url.substring(10);
		byte[] bytes  = Encryption.Base64Decrypt(url);
		url = new String(bytes,"GBK");
		url = url.substring(2, url.length()-2);
		
		url = URLEncoder.encode(url,"GBK");
		System.out.println(url);
		return url;
	}
	public static void downloadWithTime(String url,String dir)throws Exception{
		
		System.out.println("开始下载："+DateTimeUtil.date2Str(new Date(), "yyyy-MM-dd HH:mm:ss"));
		Long begin = System.currentTimeMillis();
		Long size = HttpUtil.download(url, dir)/1024;
		Long end = System.currentTimeMillis();
		Long time = (end-begin)/1000;
		System.out.println("下载结束："+DateTimeUtil.date2Str(new Date(), "yyyy-MM-dd HH:mm:ss"));
		System.out.println("用时："+time+"秒,平均下载速度："+size/time+"KB/S");
	}
	public static void main(String[] args) throws Exception{
		String url = "thunder://QUFodHRwOi8vZGwzLmhkLWR2ZDkuY29tL6G+w7/I1bj80MI3MDBkdmQuY29tob80aGV5em9faGRfMDEzM19mdWxsLm1wNFpa";
		
		HttpUtil.downloadWithTime(decodeThunderUrl(url), "c:\\download");
	}
}
