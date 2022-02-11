package github.kanwalnain.creditcard.repository;

import github.kanwalnain.creditcard.entity.CreditCardEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardDetailsRepository  extends CrudRepository<CreditCardEntity, Long> {

    List<CreditCardEntity> findAll();
}