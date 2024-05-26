package kg.booster.rental_service.models.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import kg.booster.rental_service.models.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "rentals")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @JsonProperty("rental_start_date")
    Date startDate;

    @JsonProperty("rental_end_date")
    Date endDate;

    @Enumerated(EnumType.STRING)
    Status status;

    double price;

    @ManyToOne
    @JoinColumn(name = "client_id")
    Client client;

    @ManyToMany()
    @JoinTable(
            name = "rentals_items",
            joinColumns = @JoinColumn(name = "rental_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    @JsonProperty("item_inventory_numbers")
    List<Item> items = new ArrayList<>();

}
