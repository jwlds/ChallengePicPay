<!DOCTYPE html>
<html>

<body>

  <h1>Desafio backend PicPay</h1>

  <p>Este é um projeto desenvolvido para treinamento, utilizando o desafio de backend do PicPay. Foi utilizado Spring Boot (Java) e MongoDB como banco de dados.</p>

  <h2>Requisitos</h2>

  <ul>
    <li><input type="checkbox" checked> Para ambos os tipos de usuário, precisamos do Nome Completo, CPF, e-mail e Senha. CPF/CNPJ e e-mails devem ser únicos no sistema. Sendo assim, seu sistema deve permitir apenas um cadastro com o mesmo CPF ou endereço de e-mail.</li>
    <li><input type="checkbox" checked> Usuários podem enviar dinheiro (efetuar transferência) para lojistas e entre usuários.



  </li>
    <li><input type="checkbox" checked> Lojistas só recebem transferências, não enviam dinheiro para ninguém.

 ![Screenshot from 2024-03-22 20-39-42](https://github.com/jwlds/ChallengePicPay/assets/104650587/a9d9fc2b-1136-4802-b8da-dc00f20a774b)
</li>
    <li><input type="checkbox" checked> Validar se o usuário tem saldo antes da transferência.

![Screenshot from 2024-03-22 20-43-18](https://github.com/jwlds/ChallengePicPay/assets/104650587/a5c7b861-cc6a-4ce8-9597-43e931763e5c)

</li>
    <li><input type="checkbox" checked> Antes de finalizar a transferência, deve-se consultar um serviço autorizador externo, utilize este mock para simular (<a href="https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc">https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc</a>).</li>
    <li><input type="checkbox" checked> A operação de transferência deve ser uma transação (ou seja, revertida em qualquer caso de inconsistência) e o dinheiro deve voltar para a carteira do usuário que enviou.</li>
    <li><input type="checkbox" checked> No recebimento de pagamento, o usuário ou lojista precisa receber notificação (envio de e-mail, sms) enviada por um serviço de terceiros e eventualmente este serviço pode estar indisponível/instável. Utilize este mock para simular o envio (<a href="https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6">https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6</a>).</li>
    <li><input type="checkbox" checked> Este serviço deve ser RESTFul.</li>
  </ul>

  <h2>Instalação</h2>
  
  <ol>
    <li>Clone este repositório:</li>

    git clone https://github.com/jwlds/ChallengePicPay.git
  </ol>

  <ol>
    <li>Navegue até o diretório:</li>

    cd ChallengePicPay
  </ol>


  <h2>Configuração</h2>

  <p>Antes de executar o aplicativo, você precisa configurar as variáveis de ambiente necessárias. Crie um arquivo <code>.env</code> na raiz do projeto e defina as seguintes variáveis:</p>

  <pre>
# Configurações do Banco de Dados MongoDB
MONGODB_DATABASE = "";
MONGODB_USERNAME = "";
MONGODB_PASSWORD = "";
MONGODB_CLUSTER = "";

  </pre>

  <h2>Uso</h2>

  <p>Para iniciar o servidor, utilizando uma IDE ou mvn</p>


  <p>A API está documentada utilizando o swagger-ui e estará disponível em <code>http://localhost:8080/swagger-ui/index.html</code>.</p>

  <p>Você pode usar ferramentas como <a href="https://www.postman.com/">Postman</a>, <a href="https://curl.se/">curl</a> ou <a href="https://insomnia.rest/">Insomnia</a> para fazer solicitações CRUD para o servidor. Aqui estão alguns exemplos de como usar as rotas:</p>

  <ul>
    <li><strong>POST /auth/login</strong>: Faça login com login e senha</li>
    <li><strong>GET /auth/register</strong>: Registre um novo usuário.</li>
    <li><strong>POST /api/transaction/send</strong>: Enviar a transferência para outro usuário.</li>
    <li><strong>GET /user/</strong>: Lista todos os usuários.</li>
  </ul>
  
</body>

</html>
