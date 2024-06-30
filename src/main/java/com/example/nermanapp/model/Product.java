package com.example.nermanapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductID")
    private int productID;

    @Column(name = "ProductName", length = 100, unique = true)
    private String productName;

    @Column(name = "Description", length = 500)
    private String description;

    @Column(name = "Price", nullable = false)
    private double price;

    @Column(name = "Quantity", nullable = false)
    private int quantity;

    @Column(name = "QuantitySold")
    private int quantitySold;

    @Column(name = "Status")
    private boolean status;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "CategoryId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference
    private Category category;

//    @ManyToOne(cascade = CascadeType.MERGE, optional = false, fetch = FetchType.LAZY)
//    @JoinColumn(name = "UsersID")
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    @JsonBackReference
//    private User user;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private List<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private List<Cart> cart;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    private List<ProductImages> productImages;

}
