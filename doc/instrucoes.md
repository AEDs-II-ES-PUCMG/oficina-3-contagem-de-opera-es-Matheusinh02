# Instruções de uso

## Compilação

No diretório raiz do projeto, execute:

```powershell
javac src/App.java
```

## Execução

Para executar todos os testes:

```powershell
java -Xmx6g -cp src App
```

Para executar apenas um algoritmo específico:

```powershell
java -Xmx6g -cp src App 1
java -Xmx6g -cp src App 2
java -Xmx6g -cp src App 3
java -Xmx6g -cp src App 4
```

## Saída

O programa imprime os resultados em formato CSV separado por `;`, com o cabeçalho:

```text
algoritmo;entrada;operacoes;tempo_ms;retorno
```

Cada linha pode ser copiada para uma planilha para gerar os gráficos pedidos na atividade.

## Observações

- Os algoritmos 1 e 2 usam os tamanhos do "Teste Grande" e podem exigir bastante memória.
- A contagem de operações foi instrumentada assim:
	- `codigo1`: uma operação por processamento de elemento visitado.
	- `codigo2`: uma operação por iteração do laço interno.
	- `codigo3`: uma operação por comparação feita na busca do menor elemento.
	- `codigo4`: uma operação por chamada recursiva.
