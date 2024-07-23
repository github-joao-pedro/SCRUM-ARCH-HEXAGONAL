# SCRUM-ARCH-HEXAGONAL
O SCRUM-API é uma API para gerenciamento de projetos baseada no framework Spring Boot e utilizando um banco de dados PostgreSQL. Essa API foi desenvolvida para auxiliar na gestão de projetos seguindo a metodologia SCRUM, permitindo o controle de usuários, projetos, sprints, backlog e tarefas de forma eficiente. Durante o desenvolvimento, o principal foco e a prática da arquitetura limpa no código, separando entre camadas assim desacoplando as regras de negócio do framework.
![Diagrama de relacionamentos](clean-arch.png)

## Futuras funcionalidades
- [X] Criar CRUD para usuários
- [ ] Tratamento de exceções
- [ ] Criar um sistema de login e autenticação
- [ ] Criar feat projetos
- [ ] Criar relação entre usuário e projeto (ManyToMany)
- [ ] Endpoints de adicionar/remover usuário do projeto

## Diagrama de relacionamentos
![Diagrama de relacionamentos](SCRUM-diagram.png)
Camadas e Componentes
- Framework

    - UserController: Controlador responsável por receber as requisições do cliente. Usa o UserService para realizar as operações de criação, leitura, atualização e deleção de usuários.

- Ports

    - UserService: Serviço que coordena os casos de uso. Ele chama diferentes casos de uso para realizar operações específicas.
        - CreateUserUseCase
        - ReadUserUseCase
        - UpdateUserUseCase
        - DeleteUserUseCase

- UseCases

    - CreateUserUseCaseImpl: Implementação do caso de uso para criar um usuário.
    - ReadUserUseCaseImpl: Implementação do caso de uso para ler um usuário.
    - UpdateUserUseCaseImpl: Implementação do caso de uso para atualizar um usuário.
    - DeleteUserUseCaseImpl: Implementação do caso de uso para deletar um usuário.
    - Interfaces:
        - CreateUserUseCase: Define o contrato para criar um usuário.
        - ReadUserUseCase: Define o contrato para ler um usuário.
        - UpdateUserUseCase: Define o contrato para atualizar um usuário.
        - DeleteUserUseCase: Define o contrato para deletar um usuário.

- Entity

    - User: Classe que representa o domínio do usuário com atributos como id, nickname, email, password, name, profile_picture.

- Repository

    - UserRepositoryPort: Porta de interface que define as operações de persistência de usuário.
    - JpaUserRepositoryAdapter: Adaptador que implementa a UserRepositoryPort e usa o JpaUserRepository para interagir com o banco de dados.
    - UserMapper: Classe responsável por mapear entre User e UserEntity.

- Persistence
    - JpaUserRepository: Repositório JPA para operações de banco de dados com a entidade UserEntity.

## Detalhamento dos Componentes

- UserService: É a camada de serviço que expõe métodos para cada operação de usuário. É um ponto central onde as chamadas de controlador são delegadas para os casos de uso específicos.
- UseCases: Implementações específicas para cada caso de uso, segregando a lógica de negócio específica.
- UserRepositoryPort: Interface que define as operações de persistência, implementada pelo adaptador JPA.
- JpaUserRepositoryAdapter: Adaptador que implementa a UserRepositoryPort, lidando com a persistência real usando JPA e mapeando entidades.

## O diagrama segue uma arquitetura baseada em Clean Architecture (ou Hexagonal Architecture), onde:

- Controladores (Controllers) lidam com a entrada e saída.
- Serviços (Services) coordenam os casos de uso.
- Casos de Uso (UseCases) contêm a lógica de negócio específica.
- Repositórios (Repositories) lidam com a persistência de dados.

Essa separação de responsabilidades facilita a manutenção, testes e evolução do sistema.
