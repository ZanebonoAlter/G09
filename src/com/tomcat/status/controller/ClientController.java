package com.tomcat.status.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.tomcat.status.model.config;



@Controller
@RequestMapping("/client")
public class ClientController {
	@RequestMapping("/test")
	@ResponseBody
	public String doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
				System.out.println("doPost");
				Gson gson = new Gson();
				StringBuffer jb = new StringBuffer();
	              String line = null;
	              String result = "";
	              config config=null;
	              try {
	                  //��ȡ��������StringBuffer��
	                  BufferedReader reader = request.getReader();
	                    while ((line = reader.readLine()) != null)
	                      jb.append(line);
	                     
	              } catch (Exception e) { /*report an error*/ }
	 
	              try {
	                  //ʹ��JSONObject��parseObject��������JSON�ַ���
	                  
	                  config=gson.fromJson(jb.toString(), config.class);
	                     
	              } catch (Exception e) {
	                // crash and burn
	                throw new IOException("Error parsing JSON request string");
	              }
	              //�Ƚ��������յ���JSON�ַ�����ӡ���ͻ��ˣ��ٽ����ַ���ת��ΪJSON����Ȼ����ת���ɵ�JSON�ַ�����ӡ���ͻ���
	              PrintStream out = new PrintStream(response.getOutputStream());
	              out.println(jb.toString());
	              out.println(result);
	              System.out.print(config.getIpaddress());
				//System.out.println(gson.fromJson(config, config.getClass()));
				return "ok";
		}
	
    
}
