package com.vendingmachine.email;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class EmailSenderTest {

	@Test
	public void sendEmailTest() {
		List<String> listEmail = new ArrayList<String>();
		listEmail.add("zanc.razvan@gmail.com");
		listEmail.add("alexandra.boian@yahoo.com");
		listEmail.add("adelinapopa14@yahoo.com");
		for (String email : listEmail) {

			EmailSender emailSender = new EmailSender();
			emailSender.sendEmail(email);
		}
	}
}
