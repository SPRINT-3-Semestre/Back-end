package com.example.EditMatch.Controller;

import com.example.EditMatch.Entity.Usuario;
import com.example.EditMatch.Repository.UserRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioControllerTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UsuarioController usuarioController;

    @Nested
    class TesteDeEncapsulamento {

        @Test
        void atributosDevemSerPrivados() {
            var clazz = UsuarioController.class;

            var fields = clazz.getDeclaredFields();

            assertFalse(fields.length == 0);

            for (var field : fields) {
                var modifiers = field.getModifiers();

                assertTrue((modifiers & 2) == 2);
            }
        }

        @Test
        void atributosDevemPossuirMetodosDeAcesso() {
            var clazz = UsuarioController.class;

            var fields = clazz.getDeclaredFields();

            assertFalse(fields.length == 0);

            for (var field : fields) {
                var fieldName = field.getName();

                var getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                var setMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

                assertDoesNotThrow(() -> clazz.getDeclaredMethod(getMethodName));
                assertDoesNotThrow(() -> clazz.getDeclaredMethod(setMethodName, field.getType()));
            }
        }
    }
    @Nested
    public void FindByIdTest(){
        var usuario = new Usuario();
        usuario.setId(1);
        usuario.setNome("Teste");
        usuario.setEmail("teste@teste.com");
        usuario.setPassword("123456");
        usuario.setIsEditor(true);
        usuario.setCpf("12345678910");
        usuario.setRg("123456789");

        var usuario2 = new Usuario();
        usuario2.setId(2);
        usuario2.setNome("Teste 2");
        usuario2.setEmail("teste2@teste.com");
        usuario2.setPassword("123422");
        usuario2.setIsEditor(true);
        usuario2.setCpf("12345678222");
        usuario2.setRg("123456222");


}