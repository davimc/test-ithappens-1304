# Read
o bd é o h2, podendo ser acessado ao iniciar o spring e acessando o localhost:8080/h2
e apertar entrar.
se necessário:
modificar jdbc url para: jdbc:h2:mem:ithappens-test

## Back-End
  - domains: as entidades do banco que foram requisitadas(produto, filial, cliente, funcionário, estoque e pedido);
  - repositories: busca direta no banco de dados;
  - services: para "encapsular" as regras de negócio que a parte do cliente não precisa saber;
  - controllers: utilizado para alimentar a parte do cliente (rest);
  * controllers/dto e form: apenas para receber e mandar informações só com o necessário e não precisar poluir e fornecer formações à mais para o cliente;
  - miscs (miscelanious): guardar classes váriadas como enums e exceptions(sendo o ultimo não utilizado).
## OBS:
__não é necessário nenhuma execução de script para crianção do banco de dados. Tudo será feito ao instalar as dependências que estão no pom.xml e executar o arquivo InventoryApplication.java como spring application__

## Front-End
Infelizmente houve alguns imprevistos e não pude finalizar as telas com o react

O documento que está na pasta foi exportado pelo **Insomnia** e pode ser importado para o mesmo.

### passo 1:
	- Cadastra Pedido: cria um pedido, cadastrando o branch(filial), identificação do cliente(cpf), identificação do funcionário(identificação fictícia), o tipo de pedido(Entrada ou Saída) e a observação de entrega;
### passo 2: 
	- Cadastra Item 1: cria um item buscando pelo código de barras;
### passo 3:
	- Cadastra Item 2: cria um item buscando pelo nome, mas o produto está com estoque zerado;
### passo 4:
	- Cadastra Item 3: cria um item buscando pelo sequencial (será removido no próximo comando);
### passo 5:
	- Remove Item: remove o item 3, pelo id;
### passo 6:
	- Adiciona Quantidade: busca um item do pedido e adiciona quantidade(12), no total ficam (13);
### passo 7:
	- Remove Quantidade: busca um item do pedido e remove quantidade(10), no total ficam (3);
### passo 8:
	- Finalizar Pedido: Escolhe a forma de pagamento e ele retorna o pedido.