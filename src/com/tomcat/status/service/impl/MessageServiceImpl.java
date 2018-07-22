package com.tomcat.status.service.impl;

import org.springframework.stereotype.Service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.tomcat.status.service.MessageService;

@Service("MessageService")
public class MessageServiceImpl implements MessageService{

	@Override
	public void sendMessage(int number) throws ClientException{
		// TODO Auto-generated method stub
		 //���ó�ʱʱ��-�����е���
	    System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
	    System.setProperty("sun.net.client.defaultReadTimeout", "10000");

	    //��ʼ��ascClient��Ҫ�ļ�������
	    final String product = "Dysmsapi";//����API��Ʒ���ƣ����Ų�Ʒ���̶��������޸ģ�
	    final String domain = "dysmsapi.aliyuncs.com";//����API��Ʒ�������ӿڵ�ַ�̶��������޸ģ�

	    //�滻�����AK
	    final String accessKeyId = "";
	    final String accessKeySecret = "";//���accessKeySecret��
	    IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,accessKeySecret);
	    DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
	    IAcsClient acsClient = new DefaultAcsClient(profile);

	    //��װ�������
	     SendSmsRequest request = new SendSmsRequest();
	     //ʹ��post�ύ
	     request.setMethod(MethodType.POST);
	     //����:�������ֻ��š�֧���Զ��ŷָ�����ʽ�����������ã���������Ϊ1000���ֻ�����,������������ڵ������ü�ʱ�������ӳ�,��֤�����͵Ķ����Ƽ�ʹ�õ������õķ�ʽ
	     request.setPhoneNumbers("");
	     //����:����ǩ��-���ڶ��ſ���̨���ҵ�----����ǩ������
	     request.setSignName("");
	     //����:����ģ��-���ڶ��ſ���̨���ҵ� ���磺SMS_127156997
	     request.setTemplateCode("SMS_139981014");
	     //��ѡ:ģ���еı����滻JSON��,��ģ������Ϊ"�װ���${name},������֤��Ϊ${code}"ʱ,�˴���ֵΪ
	     request.setTemplateParam("{\"warn_number\":\""+number+"\"}");
	     //��ѡ-���ж�����չ��(��չ���ֶο�����7λ�����£������������û�����Դ��ֶ�)
	     //request.setSmsUpExtendCode("90997");
	     //��ѡ:outIdΪ�ṩ��ҵ����չ�ֶ�,�����ڶ��Ż�ִ��Ϣ�н���ֵ���ظ�������
	     //request.setOutId("yourOutId");

	    //����ʧ���������ClientException�쳣
	     SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
	     if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
	     //����ɹ�
	         System.out.println("���ŷ��ͳɹ������");
	     }else {
	        System.out.println("���Ͷ���ʧ��");
	    }   
	}

}
