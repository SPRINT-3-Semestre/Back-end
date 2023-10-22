package com.example.EditMatch.service.usuario;

public class ListaObj<T>{
    private T[] vetor;
    private int nroElem;

    public ListaObj(int tam) {
        this.vetor = (T[]) new Object[tam];
        nroElem = 0;
    }

    public void adicionar(T elem) {
        if (vetor.length == nroElem) {
            System.out.println("Lista cheia");
        } else {
            vetor[nroElem] = elem;
            nroElem++;
        }
    }

    public void exibir() {
        for (int i = 0; i < nroElem; i++) {
            System.out.println(vetor[i]);
        }
    }

    public int buscar(T elem) {
        for (int i = 0; i < nroElem; i++) {
            if (vetor[i].equals(elem)) {
                return i;
            }
        }
        return -1;
    }

    public boolean removePeloIndice(int indice) {
        if (indice >= nroElem || indice < 0) return false;

        for (int i = indice; i < nroElem - 1; i++) {
            vetor[i] = vetor[i + 1];
        }
        nroElem--;
        return true;
    }

    public boolean removeElemento(T elemento) {
        int indice = 0;
        for (int i = 0; i < nroElem; i++) {
            if (vetor[i] == elemento) {
                indice = i;
                for (int j = indice; j < nroElem - 1; j++) {
                    vetor[j] = vetor[j + 1];
                }
                nroElem--;
                return true;
            }
        }
        return false;
    }

    public boolean substitui(T antigo, T novo) {
        for (int i = 0; i < nroElem; i++) {
            if (vetor[i] == antigo) {
                vetor[i] = novo;
                return true;
            }
        }
        System.out.println("valor não encontrado");
        return false;
    }
    public int contaOcorrencias(Integer valor){
        int contador = 0;
        for (int i = 0; i < nroElem; i++){
            if (vetor[i] == valor) contador++;
        }
        return contador;
    }
    public boolean adicinaNoInicio(T novoValor){
        if (nroElem == vetor.length) {
            System.out.println("Lidta Cheia");
            return false;
        }
        for (int i = nroElem - 1; i >= 0; i--){
            vetor[i+1] = vetor[i];
        }
        vetor[0] = novoValor;
        nroElem++;
        return true;
    }
    public  Integer getTamanho(){
        return nroElem;
    }
    public T getElemento(Integer id){
        return vetor[id];
    }
}
