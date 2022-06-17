package io.spring.start.projeto.model;


import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "client")
public class ClientModel implements Serializable {
    //nao sei para que serve. comenta!
    private static final long serialVersionUID = 1L;
    //dados da tabela
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = false, length = 255)
    private String name;

    @Column(nullable = false, unique = false, length = 100)
    private String email;

    @Column(nullable = false, unique = false)
    @Type(type = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth_date;

    @OneToMany(mappedBy = "client")
    private List<SalesModel> sales;
}
