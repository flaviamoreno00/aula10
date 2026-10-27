package br.com.aula10.service;

import br.com.aula10.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    @Test
    void deveIniciarComListaVaziaEAdicionarUsuario() {
        assertEquals(0, userService.getTotalUsuarios());
        assertTrue(userService.getUsuarios().isEmpty());

        userService.adicionarUsuario(null);
        assertEquals(0, userService.getTotalUsuarios());

        Usuario novo = new Usuario(99, "Teste", "teste@empresa.biz", "1234-5678");
        userService.adicionarUsuario(novo);
        assertEquals(1, userService.getTotalUsuarios());
    }

    @Test
    void deveCarregarTodosOsUsuariosEFiltrarEmailsBiz() {
        userService.carregarUsuarios();
        userService.adicionarUsuario(new Usuario(11, "Sem Email", null, "0000"));

        assertEquals(11, userService.getTotalUsuarios());

        List<Usuario> usuariosBiz = userService.usuariosComEmailBiz();
        assertEquals(3, usuariosBiz.size());
        assertTrue(usuariosBiz.stream().allMatch(u -> u.getEmail().endsWith(".biz")));
    }

    @Test
    void deveBuscarDetalhesDoUsuarioPorId() {
        userService.carregarUsuarios();

        Optional<Usuario> usuario = userService.usuarioPorId(1);
        assertTrue(usuario.isPresent());
        assertEquals("Leanne Graham", usuario.get().getName());
        assertEquals("Sincere@april.biz", usuario.get().getEmail());

        assertFalse(userService.usuarioPorId(999).isPresent());
    }

    @Test
    void deveTratarHeaderNuloCorretamente() {
        assertEquals("Todos os Usuarios", userService.formatarHeader(null));
        assertEquals("Todos os Usuarios", userService.formatarHeader("   "));
        assertEquals("Usuarios Biz", userService.formatarHeader("  Usuarios Biz  "));
    }

    @Test
    void deveLimparUsuariosCorretamente() {
        userService.carregarUsuarios();
        assertEquals(10, userService.getTotalUsuarios());

        userService.limparUsuarios();
        assertEquals(0, userService.getTotalUsuarios());
    }
}
