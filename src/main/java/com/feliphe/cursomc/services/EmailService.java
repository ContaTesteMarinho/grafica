package com.feliphe.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.feliphe.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
