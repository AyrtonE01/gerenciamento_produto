# Atividade - Introdução ao Spring Security

# Parte 1 - Conceitos

### 1. Explique com suas palavras o que é Spring Security.
O Spring Secutiry é um framework padrão de mercado onde serve para proteger aplicações javas. 
Ele funciona como um "segurança" do sistema, baseando-se em dois pilares: a Autenticação - identifica 
que é o usuário, sendo por login/senha, redes sociais, etc. E a Autorização - define que o usuário pode 
acessar ou fazer, como por exemplo, se ele é um administrador ou um usuário comum.

### 2. Para que serve o método csrf().disable()?
O método csrf().disable() serve para desativar a proteção contra ataques CRSF (Cross-Site Request Forgery, 
ou Falsificação de Requisição Entre Sites) no Spring Security.

Agora, o que é p ataque CSRF?

O CRSF acontece quando um site malicioso faz com que o navegador do usuário enviar uma requisição 
indesejada para um site onde o usuário já está autenticado.
Como o navegador envia os cookies de sessão automaticamente em todas as requisições para aquele site, 
o sistema acha que a requisição é legítima e a executa, por exemplo, transferir dinheiro ou mudar uma senha.
Para evitar isso, o Spring Security ativa por padrão um mecanismo que exige um "Token CSRF" - um código 
aleatório secreto - em qualquer requisição que altere dados (POST, PUT, DELETE). Se o site malicioso não 
tiver esse token, a requisição é bloqueada.

### 3. Explique o que significa SessionCreationPolicy.STATELESS.
SessionCreationPolicy.STATELESS é uma diretriz de configuração do Spring Security que altera fundamentalmente 
a maneira como o servidor gerencia a identidade dos usuários, instruindo o framework a nunca criar e nunca 
utilizar sessões HTTP para fins de autenticação.

### 4. Qual a função do permitAll()?
O método permitAll() serve para liberar o acesso público e irrestrito a determinados caminhos de URL ou 
endpoints da sua aplicação.
Ele diz ao Spring Security para não exigir nenhuma autenticação para aquela rota. Qualquer pessoa — seja um 
usuário anônimo (não logados) ou um usuário autenticado — pode acessar o recurso.
É usado para criar as portas de entrada do sistema, ou seja, páginas ou rotas da API que precisam estar 
acessíveis antes do login, como a rota de autenticação (/login), o cadastro de novos usuários (/cadastro), 
páginas institucionais ou a documentação da API (Swagger).

### 5. Explique o que faz o requestMatchers().
O requestMatchers() serve para selecionar quais rotas ou métodos HTTP vão receber uma regra de segurança.
Ele funciona como um "alvo". Sozinho ele não bloqueia nem libera nada; ele apenas aponta para o Spring Security: 
"Olhe para estas URLs específicas aqui".
Ele sempre trabalha em dupla. Primeiro você seleciona o caminho com o requestMatchers() e, logo em seguida, 
define a ação com métodos como permitAll() (para liberar) ou authenticated() (para exigir login).

### 6. Qual a função do anyRequest().authenticated()?
O anyRequest().authenticated() serve como uma trava de segurança global que exige que qualquer outra rota 
do sistema, que não tenha sido configurada explicitamente antes, exija autenticação (login) para ser acessada.
Ele estabelece a política de "seguro por padrão". O anyRequest() captura qualquer requisição restante e o 
.authenticated() define que o usuário precisa estar logado para prosseguir.
Ele funciona como a última linha do seu bloco de configurações. Você primeiro lista as exceções 
(usando requestMatchers().permitAll() para liberar a tela de login, por exemplo) e encerra com o 
anyRequest().authenticated() para garantir que todo o resto do sistema fique trancado.

### 7. O que é uma API Stateless?
Uma API Stateless é uma API onde o servidor não guarda nenhum histórico ou registro das ações do usuário entre 
uma requisição e outra.
O servidor não cria sessões (cookies). Cada requisição é um evento isolado que deve conter, por si mesma, 
todos os dados necessários para ser processada — inclusive o token de identificação (JWT) do usuário.
Como o servidor não gasta memória guardando dados de quem está conectado, a API se torna extremamente leve, 
rápida e fácil de escalar (você pode colocar vários servidores trabalhando juntos, pois qualquer um deles 
pode atender qualquer usuário a qualquer momento).

### 8. Explique o que a lambda abaixo faz: session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
Essa lambda desativa o uso de sessões de memória no servidor, forçando a aplicação a ser Stateless.
Ela configura o Spring Security para não criar cookies de sessão e não guardar dados de login no servidor.
Cada requisição HTTP passa a ser tratada como única. O cliente (front-end) se torna responsável por enviar um token de 
identificação (como JWT) em cada nova requisição para que o sistema saiba quem ele é.
