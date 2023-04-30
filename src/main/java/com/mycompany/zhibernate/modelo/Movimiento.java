package com.mycompany.zhibernate.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Marcos Miranda
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Movimiento")
public class Movimiento implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name="idMovimiento")
    private int idMovimiento;
    @Column(name="cantidad")
    private float cantidad;
    @Column(name="concepto")
    private String concepto;
    @Column(name="fecha")
    private Date fecha;
    
    @ManyToOne
    @JoinColumn(name="IdCuenta")
    private Cuenta cuenta;
    
}
