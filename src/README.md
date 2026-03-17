# Código do Projeto

Contém o código-fonte da oficina de contagem de operações.

## Arquivo principal

- `App.java`: implementação dos 4 algoritmos instrumentados, gerador de vetores e programa principal que executa os testes e imprime os resultados em CSV.

## Compilação e execução

```powershell
javac src/App.java
java -Xmx6g -cp src App
```

Para executar um algoritmo específico:

```powershell
java -Xmx6g -cp src App 1
java -Xmx6g -cp src App 2
java -Xmx6g -cp src App 3
java -Xmx6g -cp src App 4
```