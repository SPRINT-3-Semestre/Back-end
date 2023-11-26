package com.example.EditMatch.service.generic;


import com.example.EditMatch.Entity.Usuario;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ListaObj <T> {


    // É inicializado no construtor
    private T[] vetor;



    // Tem dupla função: representa quantos elementos foram adicionado no vetor
    // Também o índice de onde será adicionado o próximo elemento
    private int nroElem;


    // Recebe como argumento o tamanho máximo do vetor
    // Cria vetor com tamanho máximo informado
    // Inicializa nroElem
    public ListaObj(int tamanho) {
        vetor = (T[]) new Object[tamanho];
        nroElem = 0;
    }


    // Recebe o elemento a ser adicionado na lista
    // Se a lista estiver cheia exibe mensagem de Lista cheia;
    public void adiciona(T elemento) {
        if (nroElem >= vetor.length) {
            System.out.println("Lista cheia!");;
        }
        else {
            vetor[nroElem++] = elemento;

        }
    }


    // Recebe o elemento a ser procurado na lista
    // Retorna o índice do elemento, se for encontrado
    // Retorna -1 se não encontrou
    public int busca(T elementoBuscado) {
        for (int i = 0; i < nroElem; i++) {
            if (vetor[i].equals(elementoBuscado)) {
                return i;
            }
        }
        return -1;
    }


    // Recebe o índice do elemento a ser removido
    // Se o índice for inválido, retorna false
    // Se removeu, retorna true
    public boolean removePeloIndice (int indice) {
        if (indice < 0 || indice >= nroElem) {
            System.out.println("\nÍndice inválido!");
            return false;
        }

        // Loop para "deslocar para a esquerda" os elementos do vetor
        // sobrescrevendo o elemento removido
        for (int i = indice; i < nroElem-1; i++) {
            vetor[i] = vetor[i+1];
        }

        nroElem--;
        return true;
    }


    // Recebe um elemento a ser removido
    // Utiliza os métodos busca e removePeloIndice
    // Retorna false, se não encontrou o elemento
    // Retorna true, se encontrou e removeu o elemento
    public boolean removeElemento(T elementoARemover) {
        return removePeloIndice(busca(elementoARemover));
    }


    // Retorna o tamanho da lista
    public int getTamanho() {
        return nroElem;
    }


    // Recebe um índice e retorna o elemento desse índice
    // Se o índice for inválido, retorna null
    public T getElemento(int indice) {
        if (indice < 0 || indice >= nroElem) {
            return null;
        }
        else {
            return vetor[indice];
        }
    }

    // Limpa a lista
    public void limpa() {
        nroElem = 0;
    }


    public void exibe() {
        if (nroElem == 0) {
            System.out.println("\nA lista está vazia.");
        }
        else {
            System.out.printf(" %-10s  %-15s  %15s  %-10s  %5s  %-25s %15s \n", "Nome", "Sobrenome", "Aniversário", "Gênero",
                    "Editor", "Email", "Data Criação");
            for (int i = 0; i < nroElem; i++) {
                Usuario pessoa = (Usuario) vetor[i];
                System.out.printf("%-10s  %-15s  %15s  %-10s  %5b  %-25s %15s\n", pessoa.getNome(), pessoa.getLast_name(),
                        pessoa.getBirth(), pessoa.getGender(), pessoa.getIsEditor(), pessoa.getEmail(),
                        pessoa.getCreated_at());

            }
        }
    }
}

