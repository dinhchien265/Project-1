package game;

import pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessBoard extends JFrame implements ActionListener {
    private static ChessBoard instance;

    private GameController gc;
    public JButton jButton[][];
    public Board board;

    private  JPanel contentPanel=new JPanel();
    private JPanel gridPanel =new JPanel();
    private JToolBar toolBar =new JToolBar();

    public ChessBoard(GameController gc) {
        this.gc=gc;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentPanel);
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(toolBar,BorderLayout.NORTH);
        gridPanel.setLayout(new GridLayout(8,8));

        toolBar.setFloatable(false);
        JButton newGame = new JButton("New game");
        toolBar.add(newGame);
        toolBar.add(new JToolBar.Separator());

        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gc.newGame();
            }
        });
        JButton PvC=new JButton("PvC");
        toolBar.add(PvC);
        toolBar.addSeparator();
        JButton PvP=new JButton("PvP");
        toolBar.add(PvP);
        toolBar.addSeparator();
        PvC.setEnabled(false);
        PvC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gc.PvC=true;
                gc.newGame();
                PvP.setEnabled(true);
                PvC.setEnabled(false);
            }
        });
        PvP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gc.PvC=false;
                gc.newGame();
                PvC.setEnabled(true);
                PvP.setEnabled(false);
            }
        });
        JButton undo=new JButton("Undo");
        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gc.undo();
            }
        });
        toolBar.add(undo);
        toolBar.addSeparator();
        JButton redo=new JButton("Redo");
        redo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gc.redo();
            }
        });
        toolBar.add(redo);
        toolBar.addSeparator();

        JButton chooseColor =new JButton("Black");
        chooseColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(chooseColor.getText().equalsIgnoreCase("Black")) {
                    chooseColor.setText("White");
                    gc.humanColor= Piece.BLACK;
                    gc.alphaBetaPlayer.setColor(Piece.WHITE);
                    turnBoard();
                    gc.newGame();

                }
                else {
                    chooseColor.setText("Black");
                    gc.humanColor= Piece.WHITE;
                    gc.alphaBetaPlayer.setColor(Piece.BLACK);
                    returnBoard();
                    gc.newGame();
                }
            }
        });
        toolBar.add(chooseColor);

        board = new Board();
        jButton = new JButton[8][8];
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                jButton[x][y] = new JButton();
                jButton[x][y].addActionListener(this);
                if ((x % 2 == 0 && y % 2 == 0) || (x % 2 == 1 && y % 2 == 1)) {
                    jButton[x][y].setBackground(Color.YELLOW);
                    jButton[x][y].setName("Y"+x+y);
                }
                else{
                    jButton[x][y].setBackground(Color.WHITE);
                    jButton[x][y].setName("W"+x+y);
                }
            }
        }
        for (int y = 7; y >= 0; y--) {
            for (int x = 0; x < 8; x++) {
                gridPanel.add(jButton[x][y]);
            }
        }
        setImageIcon(board.tiles);
        paintBoard();
        setSize(600, 600);
        contentPanel.add(gridPanel,BorderLayout.CENTER);

        setVisible(true);
        setLocationRelativeTo(null);

    }


    public void paintBoard() {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (board.tiles[x][y].isOccupied()) {
                    jButton[x][y].setIcon(board.tiles[x][y].getPiece().imageIcon);
                } else
                    jButton[x][y].setIcon(new ImageIcon());
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        int x, y;
        Position position;
        JButton jb = (JButton) (e.getSource());
        x=jb.getName().charAt(1)-'0';
        y=jb.getName().charAt(2)-'0';
        position=new Position(x,y);
        gc.click(position);
    }

    public void setImageIcon(Tile[][] tiles) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tiles[i][j].isOccupied()) {
                    tiles[i][j].getPiece().imageIcon = new ImageIcon(tiles[i][j].getImage());
                }
            }
        }
    }
    private void turnBoard(){
        gridPanel.removeAll();
        for (int y = 0; y < 8; y++) {
            for (int x = 7; x > -1; x--) {
                gridPanel.add(jButton[x][y]);
            }
        }

    }
    private void returnBoard(){
        gridPanel.removeAll();
        for (int y = 7; y > -1; y--) {
            for (int x = 0; x<8; x++) {
                gridPanel.add(jButton[x][y]);
            }
        }
    }

//    @Override
//    public void run() {
//
//        Player player1 = new HumanPlayer(Piece.WHITE);
//        Player player2 = new HumanPlayer(Piece.BLACK);
//        ArrayList<game.Move> moves = new ArrayList<>();
//        game.Move move;
//        int count = 0;
//        while (true) {
//            if (count++ > 200)
//                break;
//
//            if (turn == player1.getColor()) {
//                move = player1.getNextMove();
//                game.ChessBoard.getInstance().board.makeMove(move);
//                turn=player2.getColor();
//                repaint();
//                moves = board.getMoves(player2.getColor());
//                if (moves.size() == 0) {
//                    int n = JOptionPane.showConfirmDialog(
//                            this, "Player 1 win! Start a new game?",
//                            "New game",
//                            JOptionPane.YES_NO_OPTION);
//                    if (n == 0)
//                        game.GameController.newGame();
//
//                }
//
//                System.out.println(game.ChessBoard.getInstance().board);
//            } else {
//                move = player2.getNextMove();
//                game.ChessBoard.getInstance().board.makeMove(move);
//                turn=player1.getColor();
//                repaint();
//                moves = board.getMoves(player1.getColor());
//                if (moves.size() == 0) {
//                    int n = JOptionPane.showConfirmDialog(
//                            this, "Player 2 win! Start a new game?",
//                            "New game",
//                            JOptionPane.YES_NO_OPTION);
//                    if (n == 0)
//                        game.GameController.newGame();
//
//                }
//
//                System.out.println(game.ChessBoard.getInstance().board);
//            }
//        }
//    }
}
