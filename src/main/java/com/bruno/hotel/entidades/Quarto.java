package com.bruno.hotel.entidades;

import com.bruno.hotel.enums.DisponibilidadeQuarto;
import com.bruno.hotel.enums.TipoQuarto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "quarto_db")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Quarto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer numero;
    @Column
    private TipoQuarto tipoQuarto;
    @Column
    private DisponibilidadeQuarto disponibilidadeQuarto;
    @Column
    private Double precoPorDia;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @OneToMany(mappedBy = "quarto")
    @ToString.Exclude
    private List<Reserva> reservas;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Quarto hotel = (Quarto) o;
        return id != null && Objects.equals(id, hotel.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
