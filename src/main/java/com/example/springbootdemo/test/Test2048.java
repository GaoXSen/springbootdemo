package com.example.springbootdemo.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Test2048 {
    private static final int SIZE = 4;
    private static int[][] board = new int[SIZE][SIZE];
    private static Random rand = new Random();

    public static void main(String[] args) {
        JFrame frame = new JFrame("2048 Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(415, 438);
        frame.setResizable(false);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawBoard(g);
            }
        };

        panel.setFocusable(true);
        panel.requestFocus();
        panel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                String direction = "";
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        direction = "W";
                        break;
                    case KeyEvent.VK_DOWN:
                        direction = "S";
                        break;
                    case KeyEvent.VK_LEFT:
                        direction = "A";
                        break;
                    case KeyEvent.VK_RIGHT:
                        direction = "D";
                        break;
                }
                move(direction);
                panel.repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        frame.add(panel);
        frame.setVisible(true);

        initialize();
        panel.repaint();
    }

    private static void initialize() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = 0;
            }
        }
        addRandomTile();
        addRandomTile();
    }

    private static void addRandomTile() {
        int x, y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (board[x][y] != 0);
        board[x][y] = (rand.nextDouble() < 0.9) ? 2 : 4;
    }

    private static void drawBoard(Graphics g) {
        int tileSize = 100;
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, 400, 400);
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                g.setColor(getTileColor(board[i][j]));
                g.fillRect(j * tileSize, i * tileSize, tileSize, tileSize);
                g.setColor(Color.BLACK);
                g.drawRect(j * tileSize, i * tileSize, tileSize, tileSize);
                if (board[i][j] != 0) {
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("Arial", Font.BOLD, 20));
                    String tileValue = String.valueOf(board[i][j]);
                    int stringWidth = g.getFontMetrics().stringWidth(tileValue);
                    int stringHeight = g.getFontMetrics().getHeight();
                    g.drawString(tileValue, j * tileSize + (tileSize - stringWidth) / 2, i * tileSize + (tileSize + stringHeight) / 2);
                }
            }
        }
    }

    private static Color getTileColor(int value) {
        switch (value) {
            case 2: return new Color(0xEEE4DA);
            case 4: return new Color(0xEDE0C8);
            case 8: return new Color(0xF2B179);
            case 16: return new Color(0xF59563);
            case 32: return new Color(0xF67C5F);
            case 64: return new Color(0xF65E3B);
            case 128: return new Color(0xEDCF72);
            case 256: return new Color(0xEDCC61);
            case 512: return new Color(0xEDC850);
            case 1024: return new Color(0xEDC53F);
            case 2048: return new Color(0xEDC22E);
            default: return new Color(0xCDC1B4);
        }
    }

    private static void move(String direction) {
        switch (direction) {
            case "W":
                moveUp();
                break;
            case "A":
                moveLeft();
                break;
            case "S":
                moveDown();
                break;
            case "D":
                moveRight();
                break;
        }
    }

    private static void moveUp() {
        for (int j = 0; j < SIZE; j++) {
            for (int i = 1; i < SIZE; i++) {
                if (board[i][j] != 0) {
                    int k = i;
                    while (k > 0 && board[k - 1][j] == 0) {
                        board[k - 1][j] = board[k][j];
                        board[k][j] = 0;
                        k--;
                    }
                    if (k > 0 && board[k - 1][j] == board[k][j]) {
                        board[k - 1][j] *= 2;
                        board[k][j] = 0;
                    }
                }
            }
        }
        addRandomTile();
    }

    private static void moveLeft() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 1; j < SIZE; j++) {
                if (board[i][j] != 0) {
                    int k = j;
                    while (k > 0 && board[i][k - 1] == 0) {
                        board[i][k - 1] = board[i][k];
                        board[i][k] = 0;
                        k--;
                    }
                    if (k > 0 && board[i][k - 1] == board[i][k]) {
                        board[i][k - 1] *= 2;
                        board[i][k] = 0;
                    }
                }
            }
        }
        addRandomTile();
    }

    private static void moveDown() {
        for (int j = 0; j < SIZE; j++) {
            for (int i = SIZE - 2; i >= 0; i--) {
                if (board[i][j] != 0) {
                    int k = i;
                    while (k < SIZE - 1 && board[k + 1][j] == 0) {
                        board[k + 1][j] = board[k][j];
                        board[k][j] = 0;
                        k++;
                    }
                    if (k < SIZE - 1 && board[k + 1][j] == board[k][j]) {
                        board[k + 1][j] *= 2;
                        board[k][j] = 0;
                    }
                }
            }
        }
        addRandomTile();
    }

    private static void moveRight() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = SIZE - 2; j >= 0; j--) {
                if (board[i][j] != 0) {
                    int k = j;
                    while (k < SIZE - 1 && board[i][k + 1] == 0) {
                        board[i][k + 1] = board[i][k];
                        board[i][k] = 0;
                        k++;
                    }
                    if (k < SIZE - 1 && board[i][k + 1] == board[i][k]) {
                        board[i][k + 1] *= 2;
                        board[i][k] = 0;
                    }
                }
            }
        }
        addRandomTile();
    }
}
