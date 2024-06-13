package kg.booster.rental_service.models.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JsonProperty("first_name")
    String firstname;

    @JsonProperty("last_name")
    String lastname;

    @JsonProperty("middle_name")
    String patronymic;

    String inn;

    @JsonProperty("home_address")
    String address;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "document_id")
    Document document;

}

