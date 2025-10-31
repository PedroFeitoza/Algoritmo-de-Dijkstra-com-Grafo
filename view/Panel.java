package view;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Panel extends JFrame {
    private JTextArea txtResultado;
    private JButton btnSelecionarArquivo;

    public Panel() {
        setTitle("Dijkstra - Caminhos Mínimos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        txtResultado.setFont(new Font("Consolas", Font.PLAIN, 14));

        btnSelecionarArquivo = new JButton("Selecionar arquivo .in");
        btnSelecionarArquivo.addActionListener(e -> carregarArquivo());

        add(btnSelecionarArquivo, BorderLayout.NORTH);
        add(new JScrollPane(txtResultado), BorderLayout.CENTER);
    }

    private void carregarArquivo() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Selecione o arquivo .in");
        int opt = chooser.showOpenDialog(this);
        if (opt == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                Graph g = new Graph(file);
                Dijkstra d = new Dijkstra(g.getMatrizAdjacencia(), 0);
                txtResultado.setText(d.getResultados());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(),
                        "Erro de leitura", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
