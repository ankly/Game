package ru.sigma;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by asklykova on 23.04.2015.
 */
public class Game {
    private JButton exitButton;
    private JButton newGameButton;
    public boolean isXTurn = true;

    public JButton[] buttons = new JButton[9];


    private JButton yesButton;
    private JButton noButton;


    public void buildExitConfirm() {
        final JFrame confirmExitFrame = new JFrame("");
        confirmExitFrame.setBounds(300, 300, 150, 100);

        yesButton = new JButton("Yes");
        noButton = new JButton("No");

        JLabel label = new JLabel("Are you sure?");
        JPanel panel = new JPanel();

        FlowLayout flow = new FlowLayout();
        panel.setLayout(flow);
        panel.add(yesButton);
        panel.add(noButton);
        confirmExitFrame.add(BorderLayout.CENTER, label);
        confirmExitFrame.add(BorderLayout.SOUTH, panel);

        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmExitFrame.setVisible(false);
                ;
            }
        });


        confirmExitFrame.setVisible(true);

    }


    public void buildGUI() {
        JFrame mainFrame = new JFrame("Game");
        mainFrame.setBounds(300, 300, 550, 400);

        JPanel panel = new JPanel();
        JPanel zoomPanel = new JPanel();

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridLayout grid = new GridLayout(3, 3, 5, 5);
        GridLayout zoomGrid = new GridLayout(2, 1, 200, 200);


        exitButton = new JButton("EXIT");
        newGameButton = new JButton("New Game");

        panel.setLayout(grid);
        zoomPanel.setLayout(zoomGrid);


        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton("");
            Font f = new Font("Arial", Font.BOLD, 100);
            buttons[i].setFont(f);
            buttons[i].addActionListener(new ButtonsStepActionListener(buttons[i]));
            panel.add(buttons[i]);
        }


        exitButton.addActionListener(new ExitButtonActionListener());


        mainFrame.add(BorderLayout.CENTER, panel);
        mainFrame.add(BorderLayout.EAST, zoomPanel);


        zoomPanel.add(newGameButton);
        zoomPanel.add(exitButton);


        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        JMenu aboutMenu = new JMenu("About");
        JMenuItem aboutMenuItem = new JMenuItem("About");
        JMenuItem newGameMenuItem = new JMenuItem("New Game");
        JMenuItem quitMenuItem = new JMenuItem("Quit");

        aboutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame aboutFrame = new JFrame("About");
                aboutFrame.setBounds(300, 300, 200, 200);
                JLabel aboutLabel = new JLabel("Information");
                JButton okButton = new JButton("OK");

                aboutFrame.add(BorderLayout.CENTER, aboutLabel);
                aboutFrame.add(BorderLayout.SOUTH, okButton);
                aboutFrame.setVisible(true);

                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        aboutFrame.dispose();
                    }
                });

            }
        });

        newGameMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < buttons.length; i++) {
                    buttons[i].setText("");

                }

            }
        });

        quitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buildExitConfirm();
            }
        });


        gameMenu.add(newGameMenuItem);
        gameMenu.add(quitMenuItem);
        aboutMenu.add(aboutMenuItem);
        menuBar.add(gameMenu);
        menuBar.add(aboutMenu);
        mainFrame.setJMenuBar(menuBar);

        mainFrame.setVisible(true);
    }

    private String checkWinner() {
        if (buttons[0].getText().equals(buttons[1].getText()) && (buttons[1].getText().equals(buttons[2].getText()))
                && !buttons[0].getText().equals("")) return buttons[0].getText();
        else if (buttons[3].getText().equals(buttons[4].getText()) && (buttons[4].getText().equals(buttons[5].getText()))
                && !buttons[3].getText().equals("")) return buttons[3].getText();
        else if (buttons[6].getText().equals(buttons[7].getText()) && (buttons[7].getText().equals(buttons[8].getText()))
                && !buttons[6].getText().equals("")) return buttons[6].getText();
        else if (buttons[0].getText().equals(buttons[3].getText()) && (buttons[3].getText().equals(buttons[6].getText()))
                && !buttons[0].getText().equals("")) return buttons[0].getText();
        else if (buttons[1].getText().equals(buttons[4].getText()) && (buttons[4].getText().equals(buttons[7].getText()))
                && !buttons[1].getText().equals("")) return buttons[1].getText();
        else if (buttons[2].getText().equals(buttons[5].getText()) && (buttons[5].getText().equals(buttons[8].getText()))
                && !buttons[2].getText().equals("")) return buttons[2].getText();
        else if (buttons[0].getText().equals(buttons[4].getText()) && (buttons[4].getText().equals(buttons[8].getText()))
                && !buttons[0].getText().equals("")) return buttons[0].getText();
        else if (buttons[2].getText().equals(buttons[4].getText()) && (buttons[4].getText().equals(buttons[6].getText()))
                && !buttons[2].getText().equals("")) return buttons[2].getText();

        return null;

    }


    private class ButtonsStepActionListener implements ActionListener {
        private JButton button;

        public ButtonsStepActionListener(JButton button) {
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (button.getText().equals("")) {

                if (isXTurn) {
                    button.setText("X");
                    isXTurn = false;
                } else {
                    button.setText("0");
                    isXTurn = true;
                }
                if (checkWinner().equals("X")) System.out.println("X");
                if (checkWinner().equals("0")) System.out.println("0");

                    ;
            }

        }
    }

    private class ExitButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            buildExitConfirm();
        }
    }

}

