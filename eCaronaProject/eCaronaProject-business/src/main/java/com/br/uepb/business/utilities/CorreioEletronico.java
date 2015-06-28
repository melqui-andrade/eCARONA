package com.br.uepb.business.utilities;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Classe para gerenciamento de envio de emails pelo sistema * 
 * @author Melqui
 *
 */
public class CorreioEletronico {
	/**
	 * Enviar um email de notificação.  
	 * @param remetente Email de quem manda a mensagem
	 * @param destinatario Email para quem a mensagem será enviada
	 * @param assunto Assunto do email
	 * @param mensagem Conteudo do email
	 * @throws Exception Caso o email não consiga ser enviado
	 */
    public void enviaEmail(String remetente, String destinatario, String assunto, String mensagem) 
    		throws Exception {  
          
    	try{

            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.auth", "true");
            props.put("mail.debug", "true"); 
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "false");

            Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {

                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("chicotripa.matador@gmail.com", "123senha");
                }
            });

            mailSession.setDebug(true); // Enable the debug mode

            Message msg = new MimeMessage( mailSession );

            //--[ Set the FROM, TO, DATE and SUBJECT fields
            msg.setFrom( new InternetAddress(remetente) );
            msg.setRecipients( Message.RecipientType.TO,InternetAddress.parse(destinatario));
            msg.setSentDate( new Date());
            msg.setSubject(assunto);

            //--[ Create the body of the mail
            msg.setText(mensagem);

            //--[ Ask the Transport class to send our mail message
            Transport.send( msg );

        }catch(Exception E){
            System.out.println( "Oops something has gone pearshaped!");
            System.out.println( E );
        }
    }
}
