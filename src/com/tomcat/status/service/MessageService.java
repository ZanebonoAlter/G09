package com.tomcat.status.service;

import com.aliyuncs.exceptions.ClientException;

public interface MessageService {
	public void sendMessage(int number) throws ClientException;
}
