# Implementação de Service e Controller - AutoFast

Foram adicionadas as camadas `service` e `controller` para Cliente, Mecanico, Veiculo, Peca, Orcamento e Mensagem.

## Endpoints

- `/api/clientes`
- `/api/mecanicos`
- `/api/veiculos`
- `/api/pecas`
- `/api/orcamentos`
- `/api/mensagens`

Cada recurso possui operações de consulta, criação, atualização e exclusão, conforme as operações compatíveis com a entidade. Também foram adicionadas consultas por nome, e-mail, cliente, mecânico, veículo, status e relações já previstas nos repositories.

## Observações importantes

- A entidade `Usuario` é abstrata e não possui Controller próprio; Cliente e Mecanico são seus tipos concretos.
- Para criar Veiculo, envie o objeto `cliente` com seu `id`.
- Para criar Orcamento, envie `cliente`, `veiculo` e `peca` com seus respectivos `id`; `mecanico` é opcional.
- Para criar Mensagem, envie `orcamento`, `remetente` e `destinatario` com seus respectivos `id`.
- A senha foi configurada para ser aceita na entrada, mas não aparecer nas respostas JSON. O projeto ainda não implementa hash de senha nem autenticação; isso deve ser tratado antes de usar o sistema em produção.
- O `application.properties` original contém credencial de banco de dados. Não compartilhe esse arquivo publicamente e altere a senha caso ela seja real/reutilizada.

## Validação

A compilação automatizada não pôde ser executada neste ambiente: o ZIP original não contém `.mvn/wrapper/maven-wrapper.properties`, o Maven Wrapper está incompleto e não há Maven instalado. Portanto, execute `mvn test` em uma instalação Maven local ou corrija o Wrapper antes de considerar a implementação validada.
