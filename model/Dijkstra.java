package model;

import java.util.*;

public class Dijkstra {
    private int origem;
    private double[] dist;
    private int[] anterior;
    private boolean[] visitado;
    private double[][] grafo;

    public Dijkstra(double[][] grafo, int origem) {
        this.grafo = grafo;
        this.origem = origem;
        int n = grafo.length;
        dist = new double[n];
        anterior = new int[n];
        visitado = new boolean[n];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        Arrays.fill(anterior, -1);
        dist[origem] = 0;
        calcular();
    }

    private void calcular() {
        int n = grafo.length;
        for (int i = 0; i < n - 1; i++) {
            int u = minDist();
            if (u == -1) break;
            visitado[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visitado[v] && grafo[u][v] > 0 && dist[u] + grafo[u][v] < dist[v]) {
                    dist[v] = dist[u] + grafo[u][v];
                    anterior[v] = u;
                }
            }
        }
    }

    private int minDist() {
        double min = Double.POSITIVE_INFINITY;
        int indice = -1;
        for (int i = 0; i < dist.length; i++) {
            if (!visitado[i] && dist[i] < min) {
                min = dist[i];
                indice = i;
            }
        }
        return indice;
    }

    public String getResultados() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dist.length; i++) {
            if (i == origem) continue;
            sb.append("Vértice 0 → ").append(i).append(": ");
            if (dist[i] == Double.POSITIVE_INFINITY) {
                sb.append("não existe caminho.\n");
            } else {
                sb.append("custo = ").append(dist[i])
                  .append(", caminho = ").append(reconstruirCaminho(i))
                  .append("\n");
            }
        }
        return sb.toString();
    }

    private String reconstruirCaminho(int destino) {
        List<Integer> caminho = new ArrayList<>();
        for (int v = destino; v != -1; v = anterior[v]) {
            caminho.add(v);
        }
        Collections.reverse(caminho);
        return caminho.toString();
    }
}
