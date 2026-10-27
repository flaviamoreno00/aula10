package br.com.aula10;

import br.com.aula10.model.Usuario;
import br.com.aula10.service.UserService;

/**
 * Classe principal da aplicacao demonstrando o fluxo validado pelo pipeline CI/CD.
 */
public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        userService.carregarUsuarios();

        System.out.println("=== " + userService.formatarHeader(null)
                + " (Total: " + userService.getTotalUsuarios() + ") ===");
        for (Usuario u : userService.getUsuarios()) {
            System.out.println(" - " + u.getName() + " <" + u.getEmail() + ">");
        }

        System.out.println("\n=== " + userService.formatarHeader("Usuarios Biz")
                + " (Total: " + userService.usuariosComEmailBiz().size() + ") ===");
        for (Usuario u : userService.usuariosComEmailBiz()) {
            System.out.println(" - " + u.getName() + " <" + u.getEmail() + ">");
        }
    }
}
