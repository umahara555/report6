package ie.uryukyu.ac.jp.e175732e175742;

/**
 * 3目並べ（oxゲーム）の実装例（実装途中）。
 *
 * ボードを用意(初期化)し、o手順から置き始め、手順管理をするところまで実装。
 */
public class TicTacToe {
    /**
     * 何置かれていない状態: 'e' (empty)
     * oを置いた状態: 'o'
     * xを置いた状態: 'x'
     */
    private char[][] board;
    /**
     * true: o, false: x
     */
    private boolean turn;

    /**
     * コンストラクタ。初期化するだけ。
     */
    public TicTacToe() {
        initialize();
    }

    /**
     * 初期化用メソッド。
     * 手番を'o'に設定し、ボードを初期化。
     */
    public void initialize() {
        int i, j;

        turn = true;
        board = new char[3][3];
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                board[i][j] = 'e';
            }
        }
        System.out.println("# board was initialized.");
    }

    /**
     * 現在の手番と、ボードを出力する。
     */
    public void print() {
        int i, j;
        printTurn();
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    /**
     * 現在の手番を出力する。
     */
    public void printTurn() {
        if (turn == true) {
            System.out.println("# Turn: o");
        } else {
            System.out.println("# Turn: x");
        }
    }

    /**
     * ユーザ'o'がボードに手を置く際に使用するメソッド。
     *
     * @param x ボードの横軸座標, 左から数えたインデックス。
     * @param y ボードの縦軸座標, 上から数えたインデックス。
     */
    public void handCircle(int x, int y) {
        if (turn == true && board[x][y] == 'e') {
            board[x][y] = 'o';
            System.out.printf("# player 'o' pointed at [%d][%d]\n", x, y);
            turn = false;
            print();
        } else {
            System.out.println("# Current turn: 'x'");
            print();
        }
    }


    /**
     * ユーザ'x'がボードに手を置く際に使用するメソッド。
     *
     * @param x ボードの横軸座標, 左から数えたインデックス。
     * @param y ボードの縦軸座標, 上から数えたインデックス。
     */
    public void handCross(int x, int y) {
        if (turn == false && board[x][y] == 'e') {
            board[x][y] = 'x';
            System.out.printf("# player 'x' pointed at [%d][%d]\n", x, y);
            turn = true;
            print();
        } else {
            System.out.println("# Current turn: 'o'");
            print();
        }
    }

    public char judge_winner() {
        char[] winner = {'o', 'x'};
        for (char c : winner){
            //横が揃う
            for (int x = 0; x < 3; x++) {
                if (board[x][0] == board[x][1] && board[x][1] == board[x][2] && board[x][2] == c) {
                    return c;
                }
            }//縦が揃う
        for (int y = 0; y < 3; y++) {
            if (board[0][y] == board[1][y] && board[1][y] == board[2][y] && board[2][y] == c) {
                return c;
            }
        }//斜めが揃う
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[2][2] == c) {
            return c;
        } else if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[2][0] == c) {
            return c;
        }
    }
        return 'e';
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }
}