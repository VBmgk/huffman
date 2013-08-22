# Estrutura do Arquivo

Número de *char*(decodificado em *char* ou *int*) - **n**\\
*Booleanos* em pares\\
**n** *Char*'s\\
*Booleanos*\\

## Procedimento

Ler **n**\\
criar árvore ao percorrer:\\
  enquanto número de nós menor que **n** executar\\
    se encontrar o segundo bit do par zero criar sub árvore respectiva\\
    se encontrar um criar subárvore e fazer folha = *true* e reiniciar percurso da raiz\\
  
ler **n** *char*'s\\
  
ler *boolean*'s e percorrer árvore\\
  se encontrar folha imprime *char* respectivo e retorna para raiz\\
  se não continua percorrer árvore\\
  tratar o que fazer ao chegar no fim
