# CHAT O POMBO

- Ferramenta utilizada para desenvolvimento: NetBeans. 
- Bibliotecas: Gson 2.6, Mysql Connector 5.35
- Banco de dados MySQL


# TUTORIAL EXECUÇÃO

1. PomboServidor:

    a) Crie o banco de dados conforme script que se encontra na raiz   "script_criar_bd.sql".

    b) Execute o projeto. Binário se encontra no diretório PomboServidor/dist/PomboServidor.jar
 
2. PomboCliente:

    a) Execute o projeto. Binário se encontra no diretório PomboCliente/dist/PomboCliente.jar, para funcionar o servidor deve ter sido iniciado antes de executar o cliente.

    b) Por padrão o cliente tentará se comunicar com o servidor no endreço 127.0.0.1 (localhost).

    c) Se o servidor estiver sendo executado em outro computador, a aplicação dará um erro. Mas um arquivo de configuração será gerado no mesmo diretório do executável. Deve se chamar "config.properties".

    d) Abra o arquivo "config.properties" e altere o ip do servidor.

Dúvidas contato, juarezfrancojr@gmail.com

att. Juarez A. Franco

