package com.vs.rough;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.vs.utility.MonitoringMail;
import com.vs.utility.TestConfig;

public class LocalHostTest {

	public static void main(String[] args) throws UnknownHostException, AddressException, MessagingException {

		String messageBody = "http://"+InetAddress.getLocalHost().getHostAddress()+":8080/job/DemoBankingSystem/Extent_20Report/";
		System.out.println(messageBody);
		MonitoringMail mail = new MonitoringMail();
		mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);

	}

}
