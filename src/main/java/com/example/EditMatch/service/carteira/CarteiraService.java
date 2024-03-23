package com.example.EditMatch.service.carteira;

import com.example.EditMatch.entity.Editor;
import com.example.EditMatch.entity.Usuario;
import com.example.EditMatch.entity.carteira.Carteira;
import com.example.EditMatch.excepition.SaldoInsuficienteException;
import com.example.EditMatch.excepition.ValorInvalidoException;
import com.example.EditMatch.repository.CarteiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarteiraService {
    @Autowired
    private CarteiraRepository carteiraRepository;

    public Carteira criarCarteira(Usuario editor) {
        Carteira carteira = new Carteira();
        carteira.setUsuario(editor);
        carteira.setSaldoTotal(0.0);
        carteira.setSaldoAtual(0.0);
        carteiraRepository.save(carteira);

        return carteira;
    }

    public Carteira findByIdUsuario(Integer idUsuario) {
        return carteiraRepository.findByUsuarioId(idUsuario);
    }


    public Carteira AtualizarSaldo(Carteira carteira, Double valor) {
        carteira.setSaldoAtual(carteira.getSaldoAtual() + valor);
        carteira.setSaldoTotal(carteira.getSaldoTotal() + valor);
        return carteiraRepository.save(carteira);
    }

    public Carteira sacar(Carteira carteira, Double valor) {

        if (valor == null) {
            throw new RuntimeException("Valor inválido");
        }

        try {
            if (carteira.getSaldoAtual() < valor) {
                throw new SaldoInsuficienteException();
            }
            if (valor <= 0) {
                throw new ValorInvalidoException();
            }
            carteira.setSaldoAtual(carteira.getSaldoAtual() - valor);
            return carteiraRepository.save(carteira);
        } catch (SaldoInsuficienteException e) {
            throw new RuntimeException("Saldo insuficiente");
        } catch (ValorInvalidoException e) {
            throw new RuntimeException("Valor inválido");
        }
    }


}

//    public void depositar(Double valor, Usuario editor){
//        Carteira carteira = getCarteira(editor);
//        carteira.setSaldoAtual(carteira.getSaldoAtual() + valor);
//        carteira.setSaldoTotal(carteira.getSaldoTotal() + valor);
//        carteiraRepository.save(carteira);
//    }


//    public Double getSaldoTotal(Usuario editor){
//        return getCarteira(editor).getSaldoTotal();
//    }

