package kg.booster.rental_service.specifications;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import kg.booster.rental_service.models.entities.Client;
import kg.booster.rental_service.models.entities.Item;
import kg.booster.rental_service.models.entities.Rental;
import kg.booster.rental_service.models.enums.Status;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class RentalSpecifications {

    public static Specification<Rental> hasClientName(String firstname) {
        return (root, query, criteriaBuilder) -> {
            if (firstname == null || firstname.isBlank())
                return criteriaBuilder.conjunction();


            Join<Rental, Client> rentalClientJoin = root.join("client", JoinType.INNER);

            return criteriaBuilder.like(rentalClientJoin.get("firstname"), "%" + firstname + "%");
        };
    }

    public static Specification<Rental> hasClientLastname(String lastname) {
        return (root, query, criteriaBuilder) -> {
            if (lastname == null || lastname.isBlank())
                return criteriaBuilder.conjunction();


            Join<Rental, Client> rentalClientJoin = root.join("client", JoinType.INNER);

            return criteriaBuilder.like(rentalClientJoin.get("lastname"), "%" + lastname + "%");
        };
    }

    public static Specification<Rental> hasClientPatronymic(String patronymic) {
        return (root, query, criteriaBuilder) -> {
            if (patronymic == null || patronymic.isBlank())
                return criteriaBuilder.conjunction();


            Join<Rental, Client> rentalClientJoin = root.join("client", JoinType.INNER);

            return criteriaBuilder.like(rentalClientJoin.get("patronymic"), "%" + patronymic + "%");
        };
    }

    public static Specification<Rental> hasItemInventoryNumber(String inventoryNumber) {
        return (root, query, criteriaBuilder) -> {
            if (inventoryNumber == null || inventoryNumber.isBlank())
                return criteriaBuilder.conjunction();


            Join<Rental, Item> rentalItemJoin = root.join("items", JoinType.INNER);

            return criteriaBuilder.equal(rentalItemJoin.get("inventoryNumber"), inventoryNumber);
        };
    }

    public static Specification<Rental> hasRentalStartDate(Date startDate) {
        return (root, query, criteriaBuilder) -> {
            if (startDate == null)
                return criteriaBuilder.conjunction();

            return criteriaBuilder.equal(root.get("startDate"), startDate);
        };
    }

    public static Specification<Rental> hasRentalStatus(Status status) {
        return (root, query, criteriaBuilder) -> {
            if (status == null)
                return criteriaBuilder.conjunction();

            return criteriaBuilder.equal(root.get("status"), status);
        };
    }
}
