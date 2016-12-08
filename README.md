# Arquitetura BACK-END

## MVC

Foi utilizado o conceito de MVC para construção do projeto, utilizando modelos, controles e serviços. 

## JPA
A implementação do JPA utilizado foi o Hibernate.

## DB
O banco de dados MYSQL.

## Camada de dados

### Design patterns DAO e GenericDAO

A **GenericDAO** fornece os métodos básicos, que serão implementados pela classe abstrata HibernateDAO.

Toda interface de DAO que estender GenericDAO, imediatamente suas implementações terão os métodos básicos. 

## Factory de injeção

A factory retorna dinamicamente as instancias das DAOs, é construído normalmente pelo construtor das services.

## RESTful API JAX-RS implementado pelo framework Jersey

Aplicação voltada a serviço, utilizando-se a especificação  **RESTful API JAX-RS**, que será consumida pelo aplicação angular, baseada em JSON 

## Testes unitários
Utilizei os frameworks Junit e o mockito para execução dos testes.


# Arquitetura FRONT-END
Para a aplicação front-end foi utilizado o Angular com o conceito de MVP, single-page

# Servidor de aplicação 
Foi utilizado o tomcat como servidor de aplicação para esse projeto 


# Configuração do projeto

## Execute o build
## Altere as configurações de conexão do banco no arquivo hibernate.cfg.xml (resources/META-INF/)
## Crie um novo schema 'souninja'
## Inicialize o banco (resources/data_base_inicialise.sql)
## Deployment no tomcat.

# Bonus
### Para escalar esse sistema seria inicialmente criar um sistema de fila de processo, execução multi-thread.
### Para facilitar o lançamento das notas, preencher automático a data de emissão com a data atual
### Gerar o imposto a cada nota lançada / removida.



