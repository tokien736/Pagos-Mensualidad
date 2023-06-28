/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mensualidad.control;

import mensualidad.modelo.Usuarios;

/**
 *
 * @author USUARIO
 */
public class UsuarioAutenticado {

    private static UsuarioAutenticado instancia;
    private Usuarios usuario;

    private UsuarioAutenticado() {
    }

    public static UsuarioAutenticado getInstance() {
        if (instancia == null) {
            instancia = new UsuarioAutenticado();
        }
        return instancia;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
}

