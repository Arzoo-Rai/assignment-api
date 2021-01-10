package api.assignment.springboot.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import api.assignment.springboot.model.Price;

@Repository
public interface PriceRepository extends CrudRepository<Price, Long>{
	 public Optional<Price> findById(Long pid);
	 public Optional<Price> findByOid(String pid);
}
