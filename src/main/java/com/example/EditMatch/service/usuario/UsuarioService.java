package com.example.EditMatch.service.usuario;

import com.example.EditMatch.controller.usuario.dto.UsuarioServiceDto;
import com.example.EditMatch.controller.usuario.mapper.UsuarioMapper;
import com.example.EditMatch.entity.Editor;
import com.example.EditMatch.entity.Usuario;
import com.example.EditMatch.repository.UsuarioRepositoryJWT;
import com.example.EditMatch.configuration.security.jwt.GerenciadorTokenJwt;
import com.example.EditMatch.service.email.EmailService;
import com.example.EditMatch.service.usuario.autenticacao.dto.UsuarioLoginDto;
import com.example.EditMatch.service.usuario.autenticacao.dto.UsuarioTokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepositoryJWT usuarioRepositoryJwt;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;
    private final AuthenticationManager authenticationManager;


    private final EmailService emailService;

    public void cadastrar(Usuario usuario) {
        String senhaCriptografada = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(senhaCriptografada);
        this.usuarioRepositoryJwt.save(usuario);

        // Envie o e-mail de boas-vindas
//        emailService.sendWelcomeEmail(usuario.getEmail(),usuario.getNome(),usuario.getLast_name());
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
                        user.getIsEditor(), user.getEmail(), user.getPhotoProfileFile(),
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

    public static void gravaRegistroTxt(String registro, String nomeArq) {
        BufferedWriter saida = null;

        // Bloco try-catch para abrir o arquivo
        try {
            saida = new BufferedWriter(new FileWriter(nomeArq, false));
        }
        catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
        }

        // Bloco try-catch para gravar o registro e fechar o arquivo
        try {
            saida.append(registro + "\n");
            saida.close();
        }
        catch (IOException erro) {
            System.out.println("Erro ao gravar o arquivo");
            erro.printStackTrace();
        }
    }

    public File gravaArquivoTxt(List<Usuario> lista, String nomeArq) {
        int contaRegDadosGravados = 0;

        // Monta o registro de header
        String header = "USUARIO";
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        header += "00";

        // Grava o header
        gravaRegistroTxt(header, nomeArq);

        // Monta e grava os registros de dados (registros de corpo)
        for (Usuario usuario : lista) {
            String corpo = "02";
            corpo += String.format("%02d", usuario.getId());
            corpo += String.format("%-50.50s", usuario.getNome());
            corpo += String.format("%-50.50s", usuario.getLast_name());
            corpo += String.format("%-12.12s", usuario.getRg());
            corpo += String.format("%-14.14s", usuario.getCpf());
            corpo += String.format("%-10.10s", usuario.getBirth());
            corpo += String.format("%01d", usuario.getGender());
            corpo += String.format("%-5.5s", String.valueOf(usuario.getIsEditor()));
            corpo += String.format("%-50.50s", usuario.getEmail());
            corpo += String.format("%-255.255s", usuario.getDesc_profile());

            // Grava o registro de corpo
            gravaRegistroTxt(corpo, nomeArq);
            // Contabiliza a quantidade de reg de dados gravados
            contaRegDadosGravados++;
        }

        // Monta e grava o registro de trailer
        String trailer = "01";
        trailer += String.format("%010d", contaRegDadosGravados);

        gravaRegistroTxt(trailer, nomeArq);

        // Retorna o objeto File
        return new File(nomeArq);
    }

    public void importarArquivoTxt(MultipartFile arquivoTxt) {
        BufferedReader entrada = null;
        String registro, tipoRegistro;
        String nome, sobrenome, rg, cpf, email, descricaoPerfil;
        boolean isEditor;
        LocalDate dataNascimento;
        int id, genero, qtdRegDadosGravados, contaRegDadosLidos = 0;


        try {
            String uploadDirectory = "resources/txt";

            String fileName = arquivoTxt.getOriginalFilename();
            Path filePath = Paths.get(uploadDirectory, fileName);

            Files.copy(arquivoTxt.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch (Exception e){

        }

        // Cria uma listaLida para receber os objetos Alunos com os dados lidos do arquivo
        List<Usuario> listaLida = new ArrayList<>();

        // Bloco try-catch para abrir o arquivo
        try {
            entrada = new BufferedReader(new FileReader("Usuarios"));
        } catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
        }

        // Bloco try-catch para ler o arquivo
        try {
            // Le a primeira linha do arquivo
            registro = entrada.readLine();

            while (registro != null) {
                // Obtem o tipo do registro que sao os 2 primeiros caracteres do registro
                // 0123456...
                // 00NOTA20232
                // 1234567...
                // substring considera os indices iniciando de zero
                // substring espera receber como 1o argumento o indice inicial do que se deseja
                // e o 2o argumento eh o indice final do que se deseja MAIS UM
                // O 2o argumento tem o mesmo valor da posicao final do campo no doc layout

                tipoRegistro = registro.substring(0, 2);
                if (tipoRegistro.equals("00")) {
                    System.out.println("Eh um registro de header");
                    System.out.println("Tipo de arquivo: " + registro.substring(2, 9));
                    System.out.println("Data e hora de gravação do arquivo: " + registro.substring(9, 28));
                    System.out.println("Versão do documento: " + registro.substring(28, 30));
                } else if (tipoRegistro.equals("01")) {
                    System.out.println("Eh um registro de trailer");
                    qtdRegDadosGravados = Integer.parseInt(registro.substring(3, 7));
                    if (qtdRegDadosGravados == contaRegDadosLidos) {
                        System.out.println("Quantidade de reg de dados gravados é compatível com a " +
                                "quantidade de reg de dados lidos");
                    } else {
                        System.out.println("Quantidade de reg de dados gravados é incompatível com a " +
                                "quantidade de reg de dados lidos");
                    }
                } else if (tipoRegistro.equals("02")) {
                    System.out.println("Eh um registro de corpo");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                    id = Integer.parseInt(registro.substring(2, 4).trim());
                    // System.out.println(id);
                    nome = registro.substring(4, 54).trim();
                    // System.out.println(nome);
                    sobrenome = registro.substring(54, 104).trim();
                    // System.out.println(sobrenome);
                    rg = registro.substring(104, 116).trim();
                    // System.out.println(rg);
                    cpf = registro.substring(116, 130).replace(',', '.');
                    // System.out.println(cpf);
                    dataNascimento = LocalDate.parse(registro.substring(130, 140),formatter);
                    // System.out.println(dataNascimento);
                    genero = Integer.parseInt(registro.substring(140, 141));
                    // System.out.println(genero);
                    isEditor = Boolean.parseBoolean(registro.substring(141, 146));
                    // System.out.println(isEditor?"true":"false");
                    email = registro.substring(146, 196).trim();
                    // System.out.println(email);
                    descricaoPerfil = registro.substring(196, 215).trim();
                    // System.out.println(descricaoPerfil);

                    // Contabiliza que leu mais um reg de dados
                    contaRegDadosLidos++;

                    // Criar um objeto Usuario com os dados lidos desse registro
                    Usuario a = new Usuario();
                    a.setId(id);
                    a.setNome(nome);
                    a.setLast_name(sobrenome);
                    a.setRg(rg);
                    a.setCpf(cpf);
                    a.setBirth(dataNascimento);
                    a.setGender(genero);
                    a.setIsEditor(isEditor);
                    a.setEmail(email);
                    a.setDesc_profile(descricaoPerfil);
                    System.out.println(a);
                    // Para importar esse dado, podemos fazer
                    // teste salvando no banco
                   // usuarioRepositoryJwt.save(a);

                    // No nosso caso, como não estamos conectados a banco de dados,
                    // vamos add o objeto a a uma listaLida
                    listaLida.add(a);
                } else {
                    System.out.println("Eh um registro inválido");
                }

                // Le o proximo registro do arquivo
                registro = entrada.readLine();
            } // fim do while

            // Fecha o arquivo
            entrada.close();

        } // fim do try
        catch (IOException erro) {
            System.out.println("Erro ao ler o arquivo");
            erro.printStackTrace();
        }

        // Aqui tb seria possível enviar toda a lista para o BD
        // repository.saveAll(listaLida);

        // Exibe a lista lida
        System.out.println("\nLista lida:");
        for (Usuario a : listaLida) {
            System.out.println(a);
        }
    }

    public void adicionarVideo(Integer id, String link) {
        Usuario usuario = usuarioRepositoryJwt.findById(id);
        if (usuario != null) {
            if (usuario.getLinkYtVideoId() == null) {
                usuario.setLinkYtVideoId(new ArrayList<>());
            }
            usuario.getLinkYtVideoId().add(link);
            usuarioRepositoryJwt.save(usuario);
        } else {
            throw new RuntimeException("Usuário não encontrado com o ID: " + id);
        }
    }


}
