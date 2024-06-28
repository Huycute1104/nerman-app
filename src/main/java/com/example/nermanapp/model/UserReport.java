package com.example.nermanapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "UserReport")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserReportID")
    private Integer userReportId;

    @Column(name = "Description", length = 50)
    private String description;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ShopID")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference
    private User shop;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ProductID")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference
    private Product product;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "UsersID")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference
    private User user;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ReportId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference
    private Report report;
}
