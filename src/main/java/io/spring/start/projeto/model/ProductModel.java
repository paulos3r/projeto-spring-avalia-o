package io.spring.start.projeto.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "product")
public class ProductModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique = false, length = 255)
    private String name;
    @Column(nullable = false,unique = false, length = 255)
    private String description;
    @Column(nullable = false, unique = false)
    private String price;
    @OneToMany(mappedBy = "product")
    private List<SalesModel> sales;
}
