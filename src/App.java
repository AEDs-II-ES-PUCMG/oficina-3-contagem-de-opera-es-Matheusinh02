import java.util.Random;

/** 
 * MIT License
 *
 * Copyright(c) 2024-255 João Caram <caram@pucminas.br>
 *                       Eveline Alonso Veloso
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

public class App {
    static final int[] tamanhosTesteGrande =  { 31_250_000, 62_500_000, 125_000_000, 250_000_000, 500_000_000 };
    static final int[] tamanhosTesteMedio =   {     12_500,     25_000,      50_000,     100_000,     200_000 };
    static final int[] tamanhosTestePequeno = {          3,          6,          12,          24,          48 };
    static Random aleatorio = new Random(42);
    static long operacoes;
    static double nanoToMilli = 1.0/1_000_000;

    static class ResultadoExecucao {
        String algoritmo;
        int entrada;
        long operacoes;
        double tempoMs;
        String retorno;

        ResultadoExecucao(String algoritmo, int entrada, long operacoes, double tempoMs, String retorno) {
            this.algoritmo = algoritmo;
            this.entrada = entrada;
            this.operacoes = operacoes;
            this.tempoMs = tempoMs;
            this.retorno = retorno;
        }
    }

    /**
     * Código de teste 1. Este método...
     * @param vetor Vetor com dados para teste.
     * @return Uma resposta que significa....
     */
    static int codigo1(int[] vetor) {
        int resposta = 0;
        for (int i = 0; i < vetor.length; i += 2) {
            operacoes++;
            resposta += vetor[i]%2;
        }
        return resposta;
    }

    /**
     * Código de teste 2. Este método...
     * @param vetor Vetor com dados para teste.
     * @return Uma resposta que significa....
     */
    static int codigo2(int[] vetor) {
        int contador = 0;
        for (int k = (vetor.length - 1); k > 0; k /= 2) {
            for (int i = 0; i <= k; i++) {
                operacoes++;
                contador++;
            }

        }
        return contador;
    }

    /**
     * Código de teste 3. Este método...
     * @param vetor Vetor com dados para teste.
     */
    static void codigo3(int[] vetor) {
        for (int i = 0; i < vetor.length - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < vetor.length; j++) {
                operacoes++;
                if (vetor[j] < vetor[menor])
                    menor = j;
            }
            int temp = vetor[i];
            vetor[i] = vetor[menor];
            vetor[menor] = temp;
        }
    }

    /**
     * Código de teste 4 (recursivo). Este método...
     * @param n Ponto inicial do algoritmo
     * @return Um inteiro que significa...
     */
    static long codigo4(int n) {
        operacoes++;
        if (n <= 2)
            return 1;
        else
            return codigo4(n - 1) + codigo4(n - 2);
    }

    /**
     * Gerador de vetores aleatórios de tamanho pré-definido. 
     * @param tamanho Tamanho do vetor a ser criado.
     * @return Vetor com dados aleatórios, com valores entre 1 e (tamanho/2), desordenado.
     */
    static int[] gerarVetor(int tamanho){
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = aleatorio.nextInt(1, tamanho/2);
        }
        return vetor;
        
    }

    static ResultadoExecucao executarCodigo1(int tamanho) {
        int[] vetor = gerarVetor(tamanho);
        operacoes = 0;
        long inicio = System.nanoTime();
        int resposta = codigo1(vetor);
        long fim = System.nanoTime();
        return new ResultadoExecucao("codigo1", tamanho, operacoes, (fim - inicio) * nanoToMilli, String.valueOf(resposta));
    }

    static ResultadoExecucao executarCodigo2(int tamanho) {
        int[] vetor = gerarVetor(tamanho);
        operacoes = 0;
        long inicio = System.nanoTime();
        int resposta = codigo2(vetor);
        long fim = System.nanoTime();
        return new ResultadoExecucao("codigo2", tamanho, operacoes, (fim - inicio) * nanoToMilli, String.valueOf(resposta));
    }

    static ResultadoExecucao executarCodigo3(int tamanho) {
        int[] vetor = gerarVetor(tamanho);
        operacoes = 0;
        long inicio = System.nanoTime();
        codigo3(vetor);
        long fim = System.nanoTime();
        return new ResultadoExecucao("codigo3", tamanho, operacoes, (fim - inicio) * nanoToMilli, String.valueOf(vetor[0]));
    }

    static ResultadoExecucao executarCodigo4(int n) {
        operacoes = 0;
        long inicio = System.nanoTime();
        long resposta = codigo4(n);
        long fim = System.nanoTime();
        return new ResultadoExecucao("codigo4", n, operacoes, (fim - inicio) * nanoToMilli, String.valueOf(resposta));
    }

    static void imprimirCabecalho() {
        System.out.println("algoritmo;entrada;operacoes;tempo_ms;retorno");
    }

    static void imprimirResultado(ResultadoExecucao resultado) {
        System.out.printf(
            "%s;%d;%d;%.3f;%s%n",
            resultado.algoritmo,
            resultado.entrada,
            resultado.operacoes,
            resultado.tempoMs,
            resultado.retorno
        );
    }

    static boolean deveExecutar(String[] args, String algoritmo) {
        if (args.length == 0) {
            return true;
        }

        for (String argumento : args) {
            if (argumento.equalsIgnoreCase("all") || argumento.equals(algoritmo)) {
                return true;
            }
        }

        return false;
    }

    static void executarBateriaCodigo1() {
        for (int tamanho : tamanhosTesteGrande) {
            imprimirResultado(executarCodigo1(tamanho));
        }
    }

    static void executarBateriaCodigo2() {
        for (int tamanho : tamanhosTesteGrande) {
            imprimirResultado(executarCodigo2(tamanho));
        }
    }

    static void executarBateriaCodigo3() {
        for (int tamanho : tamanhosTesteMedio) {
            imprimirResultado(executarCodigo3(tamanho));
        }
    }

    static void executarBateriaCodigo4() {
        for (int n : tamanhosTestePequeno) {
            imprimirResultado(executarCodigo4(n));
        }
    }

    public static void main(String[] args) {
        imprimirCabecalho();

        try {
            if (deveExecutar(args, "1")) {
                executarBateriaCodigo1();
            }
            if (deveExecutar(args, "2")) {
                executarBateriaCodigo2();
            }
            if (deveExecutar(args, "3")) {
                executarBateriaCodigo3();
            }
            if (deveExecutar(args, "4")) {
                executarBateriaCodigo4();
            }
        } catch (OutOfMemoryError erro) {
            System.err.println("Memoria insuficiente para executar os testes atuais.");
            System.err.println("Tente executar com mais heap, por exemplo: java -Xmx6g -cp src App");
            throw erro;
        }
    }
}
