package model;

import java.io.*;
import java.util.*;

public class Graph {
    private int vertices;
    private double[][] matrizAdjacencia;

    public Graph(File file) throws IOException {
        carregarGrafo(file);
    }

    private void carregarGrafo(File file) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linha = br.readLine();
            if (linha == null) throw new IOException("Arquivo vazio!");
            vertices = Integer.parseInt(linha.trim());

            matrizAdjacencia = new double[vertices][vertices];
            for (int i = 0; i < vertices; i++) {
                Arrays.fill(matrizAdjacencia[i], -1);
            }

            int linhaAtual = 0;
            while ((linha = br.readLine()) != null && linhaAtual < vertices) {
                String[] valores = linha.trim().split("\\s+");
                for (int j = 0; j <= linhaAtual && j < valores.length; j++) {
                    double val = Double.parseDouble(valores[j]);
                    matrizAdjacencia[linhaAtual][j] = val;
                    matrizAdjacencia[j][linhaAtual] = val;
                }
                linhaAtual++;
            }
        }
    }

    public int getVertices() {
        return vertices;
    }

    public double[][] getMatrizAdjacencia() {
        return matrizAdjacencia;
    }
}
