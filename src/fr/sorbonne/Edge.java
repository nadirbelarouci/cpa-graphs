package fr.sorbonne;

import java.util.Objects;

public class Edge {
     int u, v;
    private int hash = -1;

    public Edge(int u, int v) {
        if (u <= v) {
            this.u = u;
            this.v = v;
        } else {
            this.u = v;
            this.v = u;
        }
    }

    public static Edge valueOf(String line) {
        String[] edge = line.split("\\s+");
        return new Edge(Integer.valueOf(edge[0]), Integer.valueOf(edge[1]));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;
        Edge edge = (Edge) o;
        return u == edge.u && v == edge.v;
    }

    @Override
    public int hashCode() {
        if (hash == -1) {
            hash = Objects.hash(u, v);
        }
        return hash;
    }

    @Override
    public String toString() {
        return u + " " + v;
    }
}
