# MVSistemas
Desafio para vaga de Java Pleno

# O desenvolvimento desse projeto foi utilizando banco de dados Oracle, Java e Maven

# Criação do Banco
1. Usuário(schema) utilizado no desenvolvimento foi "developer". Foi necessário a criação do mesmo com uma senha "developer";
2. Executar os scripts das tabelas para criação das mesmas no banco. Os scripts localizam-se na pasta /database/TABLE/;
3. Compilar as PCK's no banco de dados. Você pode encontrá-las em /database/PACKAGE/(nome da tabela referente pck);

# Aplicação JAVA
1. Para a conexão funcionar, é necessário executar o passo 1 de Criação do Banco utilizando os mesmos paramêtros. Caso deseja outras configuração, é necessário alterar a string de conexão com banco no pacote "persistencia" na classe "Conexão.java";
2. Importar as libs configuradas no pom.xml com Maven;
3. O projeto não está 100% funcional, para testar, apenas execute o classe App3.java 

# Observações
1. O programa cria um Cliente, Endereço, Conta e registra os Balanços das contas (incluíndo a operação de creditar ou debitar);
2. Não está desenvolvido: Cálculo da receita da empresa assim como as impressões de relatórios, apenas com select's no banco para ver os resultados;
3. A única ação programada é ao iniciar o programa, sendo assim, não existe interação com usuário.

# Boas Práticas
1. Reutilização de código;
2. Responsabilidade unica para cada Classe e para cada PCK no banco;
3. Utilização do padrão MVC

# Padrões de Projeto:
1. Singleton 
2. Composite 
