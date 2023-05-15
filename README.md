[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-8d59dc4de5201274e310e4c54b9627a8934c3b88527886e3b421487c677d23eb.svg)](https://classroom.github.com/a/_jy02Gvr)
[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-f4981d0f882b2a3f0472912d15f9806d57e124e0fc890972558857b51b24a6f9.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=10597562)
# Nome do projeto -- Algoritmos em Grafos
Projeto para implementar um conjunto de classes representando um grafo básico e suas operações.

## Correção e comentários

### Nota: 14,6

Grafo de teste

![Grafo de teste](docs/grafoTeste.png)

---

- Sem documentação do projeto nesta página -- eu que escrevi lá em cima
- Salvar: salva parcialmente correto, errou várias arestas e pesos no fim do teste. Parece que salvou em octal (?!?)
- Não descobri exatamente onde foi o erro, porém BFS não achou aresta 2-8 no grafo de teste. (todos os vértices estão sem vizinhos). **Segunda olhada: parece que estão tentando adicionar aresta antes do vértice existir**
- DFS também retorna grafo sem arestas. Me parece ser o parâmetro da recursividade (a recursividade deveria retornar o subgrafo)
- App é só uma iteração de teste

---

- Aderência às classes do diagrama: 2/2 pontos
- Requisitos de grafos corretamente implementados: 8,7/12 pontos
  - Carregar/salvar		1,5/2 pontos
  - Grafo completo e subgrafo 3/3 pontos
  - Grafo direcionado/não direcionado 2/2 pontos
  - Busca em largura e profundidade 2/4 pontos
  - App para uso 0,2/1 ponto
- Documentação de código: 2,5/4 pontos
  - parcial: parâmetros sem documentar, grafo completo, entre outros.
- Testes (quantidade e qualidade): 1,4/2 pontos
  - bom, mas vejam que passaram os erros acima: vários testes (salvar, bfs, subgrafo...) não verificam se está correto, apenas se foi realizada a ação de retorno.


## Alunos integrantes da equipe

* Bruno Pontes Duarte
* Diogo Martins de Assis
* Eduardo Augusto Brito
* Marcos Paulo Freitas da Silva
* Samuel Marques Sousa Leal

## Professores responsáveis

* João Caram Santos de Oliveira
