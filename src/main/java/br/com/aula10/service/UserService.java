package br.com.aula10.service;

import br.com.aula10.model.Usuario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Servico de gerenciamento de usuarios validado nos estagios de Build, Test e Quality Gate.
 */
public class UserService {

    private final List<Usuario> usuarios = new ArrayList<>();

    public void carregarUsuarios() {
        usuarios.clear();
        usuarios.add(new Usuario(1, "Leanne Graham", "Sincere@april.biz", "1-770-736-8031 x56442"));
        usuarios.add(new Usuario(2, "Ervin Howell", "Shanna@melissa.tv", "010-692-6593 x09125"));
        usuarios.add(new Usuario(3, "Clementine Bauch", "Nathan@yesenia.net", "1-463-123-4447"));
        usuarios.add(new Usuario(4, "Patricia Lebsack", "Julianne.OConner@kory.org", "493-170-9623 x156"));
        usuarios.add(new Usuario(5, "Chelsey Dietrich", "Lucio_Hettinger@annie.ca", "(254)954-1289"));
        usuarios.add(new Usuario(6, "Mrs. Dennis Schulist", "Karley_Dach@jasper.info", "1-477-935-8478 x6430"));
        usuarios.add(new Usuario(7, "Kurtis Weissnat", "Telly.Hoeger@billy.biz", "210.067.6132"));
        usuarios.add(new Usuario(8, "Nicholas Runolfsdottir V", "Sherwood@rosamond.me", "586.493.6943 x140"));
        usuarios.add(new Usuario(9, "Glenna Reichert", "Chaim_McDermott@dana.io", "(775)976-6794 x41206"));
        usuarios.add(new Usuario(10, "Clementina DuBuque", "Rey.Padberg@karina.biz", "024-648-3804"));
    }

    public void adicionarUsuario(Usuario usuario) {
        if (usuario != null) {
            usuarios.add(usuario);
        }
    }

    public String formatarHeader(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            return "Todos os Usuarios";
        }
        return titulo.trim();
    }

    public List<Usuario> getUsuarios() {
        return Collections.unmodifiableList(usuarios);
    }

    public int getTotalUsuarios() {
        return usuarios.size();
    }

    public Optional<Usuario> usuarioPorId(int id) {
        return usuarios.stream()
                .filter(u -> u.getId() == id)
                .findFirst();
    }

    public List<Usuario> usuariosComEmailBiz() {
        return usuarios.stream()
                .filter(u -> u.getEmail() != null && u.getEmail().endsWith(".biz"))
                .collect(Collectors.toList());
    }

    public void limparUsuarios() {
        usuarios.clear();
    }
}
