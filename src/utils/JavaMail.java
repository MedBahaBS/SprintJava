/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author alaed
 */
public class JavaMail {
    public static void sendMail(String recipient)throws Exception{
        System.out.println("Preparing to send email");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true"); 
        properties.put("mail.smtp.host", "smtp.gmail.com"); 
        properties.put("mail.smtp.port", "587"); 
        String accountemail="alaeddinneg92@gmail.com";
        String password="hehexD";//
        Session session=Session.getInstance(properties,new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(accountemail, password);
            }    
        });
        Message message=prepareMessage(session, accountemail,recipient);
        
        Transport.send(message);
        System.out.println("message sent");
        }

    private static Message prepareMessage(Session session, String accountemail, String recipient) {
        Message message= new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(accountemail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Mercie pour votre don");
            message.setContent("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n" +
"<html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"fr\">\n" +
"<head>\n" +
"    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
"    <meta name=\"viewport\" content=\"width=device-width\"/>\n" +
"    <title>Modular Template Patterns</title>\n" +
"\n" +
"    <style type=\"text/css\">\n" +
"        /*////// RESET STYLES //////*/\n" +
"        body, #bodyTable, #bodyCell{height:100% !important; margin:0; padding:0; width:100% !important;}\n" +
"        table{border-collapse:collapse;}\n" +
"        img, a img{border:0; outline:none; text-decoration:none;}\n" +
"        h1, h2, h3, h4, h5, h6{margin:0; padding:0;}\n" +
"        p{margin: 1em 0;}\n" +
"\n" +
"        /*////// CLIENT-SPECIFIC STYLES //////*/\n" +
"        .ReadMsgBody{width:100%;} .ExternalClass{width:100%;} /* Force Hotmail/Outlook.com to display emails at full width. */\n" +
"        .ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, .ExternalClass td, .ExternalClass div{line-height:100%;} /* Force Hotmail/Outlook.com to display line heights normally. */\n" +
"        table, td{mso-table-lspace:0pt; mso-table-rspace:0pt;} /* Remove spacing between tables in Outlook 2007 and up. */\n" +
"        #outlook a{padding:0;} /* Force Outlook 2007 and up to provide a \"view in browser\" message. */\n" +
"        img{-ms-interpolation-mode: bicubic;} /* Force IE to smoothly render resized images. */\n" +
"        body, table, td, p, a, li, blockquote{-ms-text-size-adjust:100%; -webkit-text-size-adjust:100%;} /* Prevent Windows- and Webkit-based mobile platforms from changing declared text sizes. */\n" +
"\n" +
"        /*////// FRAMEWORK STYLES //////*/\n" +
"        .flexibleContainerCell{padding-top:20px; padding-Right:20px; padding-Left:20px;}\n" +
"        .flexibleImage{height:auto;}\n" +
"        .bottomShim{padding-bottom:20px;}\n" +
"        .imageContent, .imageContentLast{padding-bottom:20px;}\n" +
"        .nestedContainerCell{padding-top:20px; padding-Right:20px; padding-Left:20px;}\n" +
"\n" +
"        /*////// GENERAL STYLES //////*/\n" +
"        body, #bodyTable{background-color:#F5F5F5;}\n" +
"        #bodyCell{padding-top:40px; padding-bottom:40px;}\n" +
"        #emailBody{background-color:#FFFFFF; border:1px solid #DDDDDD; border-collapse:separate; border-radius:4px;}\n" +
"        h1, h2, h3, h4, h5, h6{color:#202020; font-family:Helvetica; font-size:20px; line-height:125%; text-align:Left;}\n" +
"        .textContent, .textContentLast{color:#404040; font-family:Helvetica; font-size:16px; line-height:125%; text-align:Left; padding-bottom:20px;}\n" +
"        .textContent a, .textContentLast a{color:#2C9AB7; text-decoration:underline;}\n" +
"        .nestedContainer{background-color:#E5E5E5; border:1px solid #CCCCCC;}\n" +
"        .emailButton{background-color:#2C9AB7; border-collapse:separate; border-radius:4px;}\n" +
"        .buttonContent{color:#FFFFFF; font-family:Helvetica; font-size:18px; font-weight:bold; line-height:100%; padding:15px; text-align:center;}\n" +
"        .buttonContent a{color:#FFFFFF; display:block; text-decoration:none;}\n" +
"        .emailCalendar{background-color:#FFFFFF; border:1px solid #CCCCCC;}\n" +
"        .emailCalendarMonth{background-color:#2C9AB7; color:#FFFFFF; font-family:Helvetica, Arial, sans-serif; font-size:16px; font-weight:bold; padding-top:10px; padding-bottom:10px; text-align:center;}\n" +
"        .emailCalendarDay{color:#2C9AB7; font-family:Helvetica, Arial, sans-serif; font-size:60px; font-weight:bold; line-height:100%; padding-top:20px; padding-bottom:20px; text-align:center;}\n" +
"\n" +
"        /*////// MOBILE STYLES //////*/\n" +
"        @media only screen and (max-width: 480px){\n" +
"            /*////// CLIENT-SPECIFIC STYLES //////*/\n" +
"            body{width:100% !important; min-width:100% !important;} /* Force iOS Mail to render the email at full width. */\n" +
"\n" +
"            /*////// FRAMEWORK STYLES //////*/\n" +
"            /*\n" +
"                CSS selectors are written in attribute\n" +
"                selector format to prevent Yahoo Mail\n" +
"                from rendering media query styles on\n" +
"                desktop.\n" +
"            */\n" +
"            table[id=\"emailBody\"], table[class=\"flexibleContainer\"]{width:100% !important;}\n" +
"\n" +
"            /*\n" +
"                The following style rule makes any\n" +
"                image classed with 'flexibleImage'\n" +
"                fluid when the query activates.\n" +
"                Make sure you add an inline max-width\n" +
"                to those images to prevent them\n" +
"                from blowing out. \n" +
"            */\n" +
"            img[class=\"flexibleImage\"]{height:auto !important; width:100% !important;}\n" +
"\n" +
"            /*\n" +
"                Make buttons in the email span the\n" +
"                full width of their container, allowing\n" +
"                for left- or right-handed ease of use.\n" +
"            */\n" +
"            table[class=\"emailButton\"]{width:100% !important;}\n" +
"            td[class=\"buttonContent\"]{padding:0 !important;}\n" +
"            td[class=\"buttonContent\"] a{padding:15px !important;}\n" +
"\n" +
"            td[class=\"textContentLast\"], td[class=\"imageContentLast\"]{padding-top:20px !important;}\n" +
"\n" +
"            /*////// GENERAL STYLES //////*/\n" +
"            td[id=\"bodyCell\"]{padding-top:10px !important; padding-Right:10px !important; padding-Left:10px !important;}\n" +
"        }\n" +
"    </style>\n" +
"    <!--\n" +
"        Outlook Conditional CSS\n" +
"        These two style blocks target Outlook 2007 & 2010 specifically, forcing\n" +
"        columns into a single vertical stack as on mobile clients. This is\n" +
"        primarily done to avoid the 'page break bug' and is optional.\n" +
"        More information here:\n" +
"        http://templates.mailchimp.com/development/css/outlook-conditional-css\n" +
"    -->\n" +
"    <!--[if mso 12]>\n" +
"    <style type=\"text/css\">\n" +
"        .flexibleContainer{display:block !important; width:100% !important;}\n" +
"    </style>\n" +
"    <![endif]-->\n" +
"    <!--[if mso 14]>\n" +
"    <style type=\"text/css\">\n" +
"        .flexibleContainer{display:block !important; width:100% !important;}\n" +
"    </style>\n" +
"    <![endif]-->\n" +
"</head>\n" +
"<body>\n" +
"<center>\n" +
"    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\" id=\"bodyTable\">\n" +
"        <tr>\n" +
"            <td align=\"center\" valign=\"top\" id=\"bodyCell\">\n" +
"\n" +
"                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" id=\"emailBody\">\n" +
"\n" +
"\n" +
"\n" +
"                    <tr>\n" +
"                        <td align=\"center\" valign=\"top\">\n" +
"\n" +
"                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
"                                <tr>\n" +
"                                    <td align=\"center\" valign=\"top\">\n" +
"\n" +
"                                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" class=\"flexibleContainer\">\n" +
"                                            <tr>\n" +
"                                                <td align=\"center\" valign=\"top\" width=\"600\" class=\"flexibleContainerCell bottomShim\">\n" +
"                                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"nestedContainer\">\n" +
"                                                        <tr>\n" +
"                                                            <td align=\"center\" valign=\"top\" class=\"nestedContainerCell\">\n" +
"                                                                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
"                                                                    <tr>\n" +
"                                                                        <td valign=\"top\" class=\"imageContent\">\n" +
"                                                                            <img src=\"https://i.imgur.com/LsEfrkb.jpg\" width=\"520\" class=\"flexibleImage\" style=\"max-width:520px;\"  alt=\"merci\"/>\n" +
"                                                                        </td>\n" +
"                                                                    </tr>\n" +
"                                                                    <tr>\n" +
"                                                                        <td valign=\"top\" class=\"textContent\">\n" +
"                                                                            <h3>Mercie pour votre don</h3>\n" +
"                                                                            <br />\n" +
"                                                                            \n" +
"                                                                        </td>\n" +
"                                                                    </tr>\n" +
"                                                                </table>\n" +
"                                                            </td>\n" +
"                                                        </tr>\n" +
"                                                    </table>\n" +
"                                                </td>\n" +
"                                            </tr>\n" +
"                                        </table>\n" +
"                                    </td>\n" +
"                                </tr>\n" +
"                            </table>\n" +
"                        </td>\n" +
"                    </tr>\n" +
"                </table>\n" +
"            </td>\n" +
"        </tr>\n" +
"    </table>\n" +
"</center>\n" +
"</body>\n" +
"</html>", "text/html");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
        
    }

