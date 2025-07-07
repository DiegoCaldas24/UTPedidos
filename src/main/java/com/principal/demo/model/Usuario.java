package com.principal.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//la llave primaria autoincrementable
    @Column(name = "id_usuario") // 👈 esta línea es la clave
    private int id;

    private String nombre;

    @Column(nullable = false, unique = true)
    private String correo;

    private String password;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    private boolean estado;

    @Column(name = "fecha_ingreso")
    private String fechaIngreso;

}
