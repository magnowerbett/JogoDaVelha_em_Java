import java.util.Scanner;

public class JogoDaVelha {

    private static final char VAZIO = ' ';
    private static final char X = 'X';
    private static final char O = 'O';
    private char[][] tabuleiro;
    private char jogadorAtual;
    private boolean jogoTerminado;

    public JogoDaVelha() {
        tabuleiro = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro[i][j] = VAZIO;
            }
        }
        jogadorAtual = X;
        jogoTerminado = false;
    }

    public void jogar() {
        Scanner scanner = new Scanner(System.in);
        while (!jogoTerminado) {
            desenhaTabuleiro();
            System.out.println("Jogador " + jogadorAtual + ", escolha linha e coluna (1-3): ");
            int linha = scanner.nextInt() - 1;
            int coluna = scanner.nextInt() - 1;
            if (linha >= 0 && linha < 3 && coluna >= 0 && coluna < 3 && tabuleiro[linha][coluna] == VAZIO) {
                tabuleiro[linha][coluna] = jogadorAtual;
                if (checarVencedor()) {
                    jogoTerminado = true;
                    desenhaTabuleiro();
                    System.out.println("Jogador " + jogadorAtual + " venceu!");
                } else if (isTabuleiroCheio()) {
                    jogoTerminado = true;
                    desenhaTabuleiro();
                    System.out.println("O jogo terminou empatado!");
                } else {
                    jogadorAtual = (jogadorAtual == X) ? O : X;
                }
            } else {
                System.out.println("Essa posição já está ocupada ou é inválida. Tente novamente.");
            }
        }
        scanner.close();
    }

    private boolean checarVencedor() {
        for (int i = 0; i < 3; i++) {
            // Verifica linhas
            if (tabuleiro[i][0] == jogadorAtual && tabuleiro[i][1] == jogadorAtual && tabuleiro[i][2] == jogadorAtual) {
                return true;
            }
            // Verifica colunas
            if (tabuleiro[0][i] == jogadorAtual && tabuleiro[1][i] == jogadorAtual && tabuleiro[2][i] == jogadorAtual) {
                return true;
            }
        }
        // Verifica diagonais
        if (tabuleiro[0][0] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][2] == jogadorAtual) {
            return true;
        }
        if (tabuleiro[0][2] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][0] == jogadorAtual) {
            return true;
        }
        return false;
    }

    private boolean isTabuleiroCheio() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == VAZIO) {
                    return false;
                }
            }
        }
        return true;
    }

    private void desenhaTabuleiro() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tabuleiro[i][j]);
                if (j < 2) System.out.print("|");
            }
            System.out.println();
            if (i < 2) System.out.println("-----");
        }
    }

    public static void main(String[] args) {
        JogoDaVelha jogo = new JogoDaVelha();
        jogo.jogar();
    }
}
