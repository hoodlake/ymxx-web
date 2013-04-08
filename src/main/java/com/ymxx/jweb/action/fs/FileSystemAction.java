package com.ymxx.jweb.action.fs;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ymxx.jweb.action.fs.bean.FileBean;
import com.ymxx.jweb.component.Pagination;
import com.ymxx.jweb.util.StringUtil;

@Controller
@RequestMapping("/filesystem")
public class FileSystemAction {

	public static Map<String,ArrayList<FileBean>> allFiles = new HashMap<String, ArrayList<FileBean>>();
	
	@RequestMapping(method=RequestMethod.GET)
	public String filesystem(){
		
		return "filesystem/search"; 
	}
	@RequestMapping("search")
	public @ResponseBody void searchFile(
			HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		String disk = request.getParameter("disk");
		String pattern = request.getParameter("pattern");
		String suffix = request.getParameter("suffix");
		if(disk == null){
			response.getWriter().write(Pagination.getEmptyTableJson());
			return ;
		}
		File [] idxs = new File("D:/ymxx.windows.Server\\tomcat\\6.x" +
				"\\webapps\\ROOT\\System.File.Index\\20130410").listFiles();
		ObjectInputStream in = null;
		ArrayList<FileBean> searchResults = new ArrayList<>(1000);
		if(allFiles.get(disk) == null){
			System.out.println("读文件");
			for(File idx :idxs){
				if(!idx.getName().startsWith(disk)){
					continue;
				}
				in = new ObjectInputStream(new FileInputStream(idx));
				while(true){
					try{
						File f = (File)in.readObject();
						FileBean fileBean = new FileBean();
						fileBean.setName(f.getName());
						fileBean.setPath(f.getAbsolutePath());
						fileBean.setLength(f.length());
						searchResults.add(fileBean);
					}catch(Exception e){
						break;
					}
				}
				allFiles.put(disk, searchResults);
				in.close();
			}
		}else{
			System.out.println("从内存");
		}
		ArrayList<FileBean> orc = allFiles.get(disk);
		System.out.println(orc.size()+"======================");
		ArrayList<FileBean> copy = new ArrayList<FileBean>(orc);
		Iterator<FileBean> it = copy.iterator();
		FileBean bean = null;
		while(it.hasNext()){
			bean = it.next();
			String name = bean.getName().toLowerCase();
			if(!StringUtil.isBlank(suffix) && !name.endsWith(suffix)){
				it.remove();
			}
			if(!StringUtil.isBlank(pattern) && !name.contains(pattern)){
				it.remove();
			}
		}
		Pagination<FileBean> pagination = new Pagination<>(request);
		int first = (pagination.getPageNo()-1)*pagination.getPageSize();
		int end = (pagination.getPageNo()*pagination.getPageSize())>searchResults.size()?
				searchResults.size():(pagination.getPageNo()*pagination.getPageSize());
		List<FileBean> list = searchResults.subList(first, end);
		
		pagination.setPageItems(list);
		pagination.setTotalCount((long)searchResults.size());
		response.getWriter().write(pagination.toTableJson());
	}
}
