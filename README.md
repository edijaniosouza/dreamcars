# DREAMCARS! 
Projeto desenvolvido como teste mobile para empresa WS WORK

## Decisões técnicas

#### Técnologias escolhidas:

-   Android
-   Kotlin - Linguagem com mais afinidade
-   Jetpack compose - Gosto muito da facilidade e rapidez de criar interfaces e é fortemente recomendada pela Google
-   Koin - Injeção de dependencia pratica e facil. Ótimo para se utilizar em conjunto ao Kotlin
-   Retrofit - Ótima ferramenta para consumo de APIs. Foi escolhida pela afinidade e robustes, pois também acredito que ktor seja uma ótima opção.
-   Room - Banco de dados SQLite local recomendada pela google e que opera muito bem com Coroutines do kotlin.
-   Navigation - Para navegação entre as telas
-   DataStore - Para guardar sessão do usuario
-   Arquitetura: MVVM na qual sou mais familiarizado no desenvolvimento Mobile

#### Pontos principais:

##### Uso do banco de dados:

-   Pensando em oferecer uma experiencia mais agradavel ao usuario e na possibilidade de obter uma lista de dados muito grande, optei por utilizar banco de dados interno não apenas para salvar os carros desejados pelo usuario, mas para ter os dados ja baixados em cache.
-   Por haver necessidade de enviar informações (leads) para o servidor, decidi criar tambem uma entidade para o Usuario, para alem de enviar os carros desejados, enviar o usuario e seu email para facilitar a analise dos leads.

##### Rotina para envio de leads ao servidor:

-   Para o envio dos leads utilizei o WorkManager do android para criar uma rotina que realiza um POST de 1 em 1 hora. Caso receba código 200 como resposta a rotina é bem sucedida e sera reenviada novamente em 1 hora, caso contrario, será tentado enviar novamente em 15 minutos. A rotina não é enviada caso não haja carros marcados como desejados (favoritos) no banco de dados.

##### Login de usuario:

-   Decedi criar um model de usuario simples, apenas nome e e-mail, pois não vi a necessidade de senha para o mvp. O sistema se o email é valido e se o usuario nao possui espaços ou se esta em branco.
-   O ID do usuario e seu email é enviado para o servidor (leads)
-   Mantive a sessão do usuario ativa utilizando datastore, cadastrando o Id do usuario logado para utilizar no processo de envio de leads.

##### Interface:

-   Escolhi as cores: Vermelho, preto e branco como base para o tema do projeto.
-   As cores variam para modo light e dark.
-   Para apresentar a lista de carros precisei realizar 2 consultas, primeiro na tabela de carros (mesmo padrao que baixada pela API) e segundo a tabela dos carros desejados. Essas tabelas sao disponibilizadas para o composable e la implemento uma consulta de “DE → PARA", consultando na tabela de carros desejados se o carro em questão esta presente, assim atualizando o texto “EU QUERO” para um icone.
-   Optei por manter apenas 1 tela principal, pois não havia informações o suficiente para preencher uma tela de detalhes do carro.
-   No topbar disponibilizei uma action para exibir apenas os carros desejados.