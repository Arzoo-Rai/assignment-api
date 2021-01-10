package api.assignment.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.assignment.springboot.exception.ResourceNotFoundException;
import api.assignment.springboot.model.Price;
import api.assignment.springboot.repository.PriceRepository;
import api.assignment.springboot.service.SequenceGeneratorService;
import org.apache.commons.logging.Log;
import org.bson.types.ObjectId;
//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1")
public class PricingController {
	@Autowired
	private PriceRepository priceRepository;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	@GetMapping("/products")
	public List<Price> getAllProducts() {
		return (List<Price>) priceRepository.findAll();
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<Price> getProductById(@PathVariable(value = "id") String priceid)
			throws ResourceNotFoundException {
		Price price = priceRepository.findByOid(priceid)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + priceid));
		return ResponseEntity.ok().body(price);
	}

	@PostMapping("/products")
	public Price createProduct(@Valid @RequestBody Price productprice) {
		productprice.setId(sequenceGeneratorService.generateSequence(Price.SEQUENCE_NAME));
		return priceRepository.save(productprice);
	}

	@PutMapping("/products/{id}")
	public ResponseEntity<Price> updateProduct(@PathVariable(value = "id") String pId,
												@Valid @RequestBody Price priceD) throws ResourceNotFoundException {
		Price price = priceRepository.findByOid(pId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + pId));

		
		price.setName(priceD.getName());
		price.setCurrent_price(priceD.getCurrent_price());
		final Price updatedPrice = priceRepository.save(price);
		return ResponseEntity.ok(updatedPrice);
	}
//
//	@DeleteMapping("/Products/{id}")
//	public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") Long pId)
//			throws ResourceNotFoundException {
//		Price price = priceRepository.findById(pId)
//				.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + pId));
//
//		priceRepository.delete(price);
//		Map<String, Boolean> response = new HashMap<>();
//		response.put("deleted", Boolean.TRUE);
//		return response;
//	}
}
