package com.example.EditMatch.service.generic;//Classe pronta, NÃO é necessário alterar nada

import java.util.Arrays;

public class PilhaObj<T> {
    private T[] pilha;
    private int topo;
    public PilhaObj(int capacidade) {
        pilha = (T[]) new Object[capacidade];
        topo = -1;
    }
    public Boolean isEmpty() {
        return topo == -1;
    }
    public Boolean isFull() {
        return topo == pilha.length-1;
    }
    public void push(T info) {
        if (isFull()) {
            throw new IllegalStateException();
        }
        else {
            pilha[++topo] = info;
        }
    }
    public T pop() {
        if (isEmpty()) {
            return null;
        }
        return pilha[topo--];
    }
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return pilha[topo];
    }
    public void exibe() {
        if (isEmpty()) {
            System.out.println("Pilha vazia");
        }
        else {
            for (int i = topo; i >= 0; i--) {
                System.out.println(pilha[i]);
            }
        }
    }
    public int getTopo() {
        return topo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PilhaObj{");
        sb.append("pilha=[");
        for (int i = 0; i <= topo; i++) {
            sb.append(pilha[i]);
            if (i < topo) {
                sb.append(", ");
            }
        }
        sb.append("], topo=").append(topo);
        sb.append(", isEmpty=").append(isEmpty());
        sb.append(", isFull=").append(isFull());
        sb.append('}');
        return sb.toString();
    }

}
