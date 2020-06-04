package com.feliphe.cursomc.email.service;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.feliphe.cursomc.cliente.domain.Cliente;
import com.feliphe.cursomc.filter.EmailSpecificationsFilter;
import com.feliphe.cursomc.pedido.domain.Pedido;
import com.feliphe.cursomc.util.enums.OrderEmailType;

public abstract class AbstractEmailService implements EmailService {
	
	@Value("${default.sender}")	
	private String sender;
	@Value("${default.recipient}")	
	private String recipient;
	@Autowired
	private TemplateEngine templateEngine;
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
		
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getCliente().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido confirmado. Código: " + obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		
		return sm;
	}
	
	@Override
	public void sendOrderConfirmationHtmlEmail(Pedido pedido) {
		
		try {
			EmailSpecificationsFilter filter = new EmailSpecificationsFilter();
			
			filter.setOrderEmailType(OrderEmailType.NEW_ORDER);
			
			MimeMessage mm = prepareHtmlMailMessage(pedido, filter);
			sendHtmlEmail(mm);
			
		} catch (MessagingException e) {
			sendOrderConfirmationEmail(pedido);
		}
	}
	
	@Override
	public void sendChangeOrderStatus(Pedido pedido) {
		try {
			
			EmailSpecificationsFilter filter = new EmailSpecificationsFilter();
			
			filter.setOrderEmailType(OrderEmailType.STATUS_CHANGE);
			
			MimeMessage mm = prepareHtmlMailMessage(pedido, filter);
			sendHtmlEmail(mm);
			
		} catch (MessagingException e) {
			sendOrderConfirmationEmail(pedido);
		}
	}
	
	protected MimeMessage prepareHtmlMailMessage(Pedido pedido, EmailSpecificationsFilter filter) throws MessagingException {
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(pedido.getCliente().getEmail());
		mmh.setFrom(sender);
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		
		if (OrderEmailType.NEW_ORDER.getDescription()
				.equals(filter.getOrderEmailType().getDescription())) {
			
			mmh.addCc(recipient);
			mmh.setSubject("Pedido confirmadio. Código: " + pedido.getId());
			mmh.setText(htmlFromTemplatePedido(pedido, filter.getOrderEmailType().getTemplate()), true);		
			
		} else if (OrderEmailType.STATUS_CHANGE.getDescription()
				.equals(filter.getOrderEmailType().getDescription())) {
			
			mmh.setSubject("Pedido confirmadio. Código: " + pedido.getId());
			mmh.setText(htmlFromTemplatePedido(pedido, filter.getOrderEmailType().getTemplate()), true);
		}
		
		
		return mimeMessage;
	}
	
	@Override
	public void sendNewPasswordEmail(Cliente cliente, String newPass) {
		SimpleMailMessage sm = prepareNewPasswordEmail(cliente, newPass);
		sendEmail(sm);
	}
	
	protected String htmlFromTemplatePedido(Pedido pedido, String template) {
		
		Context context = new Context();
		context.setVariable("pedido", pedido);
		
		return templateEngine.process(template, context);
	}

	private SimpleMailMessage prepareNewPasswordEmail(Cliente cliente, String newPass) {

		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(cliente.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Solicitação de uma nova senha");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Nova senha: " + newPass);
		
		return sm;
	}

}