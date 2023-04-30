
package com.mycompany.zhibernate.modelo;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "Cuenta")
public class Cuenta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name="numeroCC")
    private String numeroCC;
    @Column(name="saldo")
    private float saldo;
    @Column(name="divisa")
    private String divisa;
    
    @ManyToOne
    @JoinColumn(name="IdCliente")
    private Cliente cliente;
    
    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name="IdCuenta")
    private List<Movimiento> listaMovimientos;
    
}
