package br.com.aula10.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UsuarioTest {

    @Test
    void deveCriarUsuarioEValidarGettersEqualsHashCodeToString() {
        Usuario u1 = new Usuario(1, "Leanne Graham", "Sincere@april.biz", "1-770-736-8031");
        Usuario u2 = new Usuario(1, "Leanne Graham", "Sincere@april.biz", "1-770-736-8031");
        Usuario u3 = new Usuario(2, "Ervin Howell", "Shanna@melissa.tv", "010-692-6593");

        assertEquals(1, u1.getId());
        assertEquals("Leanne Graham", u1.getName());
        assertEquals("Sincere@april.biz", u1.getEmail());
        assertEquals("1-770-736-8031", u1.getPhone());

        assertEquals(u1, u1);
        assertEquals(u1, u2);
        assertEquals(u1.hashCode(), u2.hashCode());
        assertNotEquals(u1, u3);
        assertNotEquals(u1, null);
        assertNotEquals(u1, "outroObjeto");
        assertTrue(u1.toString().contains("Leanne Graham"));
    }
}
