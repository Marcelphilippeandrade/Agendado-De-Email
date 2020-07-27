package br.com.marcelphilippe.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.MessageListener;
import br.com.marcelphilippe.business.AgendamentoEmailBusiness;
import br.com.marcelphilippe.entity.AgendamentoEmail;
import br.com.marcelphilippe.interceptor.Logger;

@Logger
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup",
                propertyValue = "java:/jms/queue/EmailQueue"),
        @ActivationConfigProperty(propertyName = "destinationType",
                propertyValue = "javax.jms.Queue")
})
public class EmailMDB implements MessageListener {

	@Inject
    private AgendamentoEmailBusiness agendamentoEmailBusiness;

    

    @Override
    public void onMessage(javax.jms.Message message) {

       try {
		   AgendamentoEmail agendamentoEmail = message.getBody(AgendamentoEmail.class);
		   agendamentoEmailBusiness.enviarEmail(agendamentoEmail);
       } catch (JMSException e) {
    	   throw new RuntimeException(e);
       }

    }
}

