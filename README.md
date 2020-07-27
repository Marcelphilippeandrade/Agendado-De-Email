# Agendador de Email
Sistema responsável pelo agendamento de E-mails.

# Objetivo:
Construir um agendador de e-mail responsável pelo envio de e-mails de qualquer natureza.

# Tecnologias utilizadas: 
* Java (Versão 8); 
* Maven;
* Wildfly;
* Mysql;
* JPA;
* Git; 
* Mailtrap;

# Pre-requisitos:
1. Criar uma conta no Mailtrap(https://mailtrap.io/);
2. Baixar e descompactar o Mysql Connector(https://dev.mysql.com/downloads/connector/j/)

# Configuração do container de aplicação Wildfly
1. Configuração do driver e do data-source do banco de dados

* jboss-cli.bat --connect;
* module add --name=com.mysql --resources=C:\mysql-connector-java-8.0.16\mysql-connector-java-8.0.16.jar --dependencies=javax.api,javax.transaction.api
* /subsystem=datasources/jdbc-driver=mysql:add(driver-name=mysql,driver-module-name=com.mysql,driver-xa-datasource-class-name=com.mysql.cj.jdbc.MysqlXADataSource)
* data-source add --name=AgendamentoDS --jndi-name=java:jboss/datasources/AgendamentoDS --driver-name=mysql  --connection-url=jdbc:mysql://localhost:3306/agendamentobd?useTimezone=true&serverTimezone=UTC --user-name=root --password=root --min-pool-size=10 --max-pool-size=20

2. Configuração do E-mail

* /subsystem=mail/mail-session=agendamentoMailSession:add(jndi-name=java:jboss/mail/AgendamentoMailSession)
* /socket-binding-group=standard-sockets/remote-destination-outbound-socket-binding=my-smtp-binding:add(host=smtp.mailtrap.io, port=2525)
* /subsystem=mail/mail-session=agendamentoMailSession/server=smtp:add(outbound-socket-binding-ref= my-smtp-binding, username=a3e8df3c039341, password=eede820da0b1d1, tls=true)

:v: :eyes: Projeto top esse! :ok_hand: :computer:
