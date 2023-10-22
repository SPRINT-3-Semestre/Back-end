package com.example.EditMatch.service.usuario;

import com.example.EditMatch.Entity.Editor;
import com.example.EditMatch.Entity.Usuario;
import com.example.EditMatch.Repository.UserRepository;
import com.example.EditMatch.Repository.UsuarioRepositoryJWT;
import com.example.EditMatch.configuration.security.jwt.GerenciadorTokenJwt;
import com.example.EditMatch.service.usuario.autenticacao.dto.UsuarioLoginDto;
import com.example.EditMatch.service.usuario.autenticacao.dto.UsuarioTokenDto;
import com.example.EditMatch.service.usuario.dto.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;

@Service
public class UsuarioService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepositoryJWT usuarioRepositoryJwt;

    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @Autowired
    private AuthenticationManager authenticationManager;

    public void cadastrar(Usuario usuario) {
        final Usuario novoUsuario = usuario;

        String senhaCriptografada = passwordEncoder.encode(novoUsuario.getPassword());
        novoUsuario.setPassword(senhaCriptografada);

        this.usuarioRepositoryJwt.save(novoUsuario);
    }

    public void atualizar(int id, Editor novoEditor) {
        String senhaCriptografada = passwordEncoder.encode(novoEditor.getPassword());
        novoEditor.setPassword(senhaCriptografada);

        this.usuarioRepositoryJwt.save(novoEditor);
    }

    public UsuarioTokenDto autenticar(UsuarioLoginDto usuarioLoginDto) {
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDto.getEmail(), usuarioLoginDto.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Usuario usuarioAutenticado =
                usuarioRepositoryJwt.findByEmail(usuarioLoginDto.getEmail())
                        .orElseThrow(
                                () -> new ResponseStatusException(404, "Email do usuário não cadastrado", null)
                        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return UsuarioMapper.of(usuarioAutenticado, token);
    }

    public File gravarArquivoCsv(String nomeArq) throws IOException {
        FileWriter arq = null;
        Formatter saida = null;
        Boolean deuRuim = false;

        // Adiciona a extensão ".csv" ao nome do arquivo
        /*nomeArq += ".csv";*/

        // Cria um arquivo temporário com base no nome fornecido
        File csvFile = File.createTempFile(nomeArq + "-", ".csv");

        try {
            arq = new FileWriter(csvFile, false);
            saida = new Formatter(arq);
        } catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo!");
            System.exit(1);
        }

        try {
            ListaObj<Usuario> listUserObj = new ListaObj(usuarioRepositoryJwt.countAllBy());
            for (int i = 0; i < usuarioRepositoryJwt.count(); i++) {
                listUserObj.adicionar(usuarioRepositoryJwt.findById(i));
            }
            for (int i = 1; i <= listUserObj.getTamanho(); i++) {
                Usuario user = usuarioRepositoryJwt.findById(i);
                saida.format("%d;%s;%s;%s;%s;%s;%d;%b;%s;%s;%s;%s;%s;%s\n",
                        user.getId(), user.getNome(), user.getLast_name(),
                        user.getRg(), user.getCpf(), user.getBirth(), user.getGender(),
                        user.getIs_editor(), user.getEmail(), user.getPhoto_profile(),
                        user.getDesc_profile(), user.getCreated_at(), user.getUpdated_at(), user.getDataEntrega());
            }
        } catch (FormatterClosedException erro) {
            System.out.println("Erro ao gravar o arquivo");
            erro.printStackTrace();
            deuRuim = true;
        } finally {
            saida.close();
            try {
                arq.close();
            } catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                erro.printStackTrace();
                deuRuim = true;
            }
            if (deuRuim) System.exit(1);
        }

        return csvFile;
    }
}
