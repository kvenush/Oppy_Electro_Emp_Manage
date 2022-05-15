package com;
import com.Employee;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/EmpAPI")
public class EmpAPI extends HttpServlet{
	
	private static final long serialVersionUID =1L;
	Employee empObj = new Employee();
	
	public EmpAPI()
	{
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
			 System.out.println("requets recieved");
		String output = empObj.insertEmp(request.getParameter("eName"),
			 request.getParameter("position"),
			request.getParameter("salary"),
			request.getParameter("empType"));
			 
			response.getWriter().write(output);
	}
	protected void doPut(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
			 Map paras = getParasMap(request);
			 
			 String output = empObj.updateEmp(Integer.parseInt(paras.get("hidEmpIdSave").toString()),
					 paras.get("eName").toString(),paras.get("position").toString(),paras.get("salary").toString(),paras.get("empType").toString());
			 
			response.getWriter().write(output);
	} 
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
			 Map paras = getParasMap(request);
			 
			 String output = empObj.deleteEmp(paras.get("empId").toString().trim());
			 System.out.println(paras.get("empId").toString());
			 response.getWriter().write(output);
	}
	private static Map getParasMap(HttpServletRequest request)
	{
		Map<String, String> map = new HashMap<String, String>();
		
		try
		{
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ?
			scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			
			String[] params = queryString.split("&");
			for (String param : params)
			{ 
	
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
	 }
	catch (Exception e)
	 {
	 }
	return map;
	}
}