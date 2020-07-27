package br.com.marcelphilippe.timer;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import br.com.marcelphilippe.business.AgendamentoEmailBusiness;
import br.com.marcelphilippe.entity.AgendamentoEmail;

@Singleton
public class AgendamentoEmailTimer {

	@Inject
	private AgendamentoEmailBusiness agendamentoEmailBusiness;

	@Inject
	@JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
	private JMSContext context;

	@Resource(mappedName = "java:/jms/queue/EmailQueue")
	private Queue queue;

	@Timeout
	public void timeout() {
	}

	@Schedule(hour = "*", minute = "*")
	public void enviarEmailsAgendados() {

		List<AgendamentoEmail> agendamenmentoEmails = agendamentoEmailBusiness.listarAgendamentosEmailNaoEnviados();

		agendamenmentoEmails.stream().forEach(agendamenmentoEmail -> {
			context.createProducer().send(queue, agendamenmentoEmail);
			agendamentoEmailBusiness.marcarEnviado(agendamenmentoEmail);
		});
	}
}
