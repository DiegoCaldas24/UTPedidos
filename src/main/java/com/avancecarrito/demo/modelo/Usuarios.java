package com.avancecarrito.demo.modelo;

import jakarta.persistence.*;

import java.time.LocalDateTime;

enum Rol {
    USUARIO,
    ADMINISTRADOR,
    TRABAJADOR
}

@Entity
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "correo",unique = true)
    private String correo;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @Column(name = "fecha_ingreso", updatable = false)
    private LocalDateTime fechaIngreso;

    @Column(name = "estado")
    private boolean estado;

    @PrePersist
    protected void onCreate() {
        fechaIngreso = LocalDateTime.now();
    }

    public Usuarios() {}

    public Usuarios(LocalDateTime fechaIngreso, Rol rol, boolean estado, String password, String correo, String nombre) {
        this.fechaIngreso = fechaIngreso;
        this.rol = rol;
        this.estado = estado;
        this.password = password;
        this.correo = correo;
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDateTime fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
