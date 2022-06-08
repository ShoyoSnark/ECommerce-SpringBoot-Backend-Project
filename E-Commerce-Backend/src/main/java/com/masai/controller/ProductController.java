package com.masai.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< HEAD
=======
import com.masai.exception.LoginException;
>>>>>>> main
import com.masai.models.CategoryEnum;
import com.masai.models.Product;
import com.masai.models.ProductDTO;
import com.masai.models.ProductStatus;
<<<<<<< HEAD
=======
import com.masai.models.Seller;
import com.masai.repository.SellerDao;
import com.masai.service.LoginLogoutService;
>>>>>>> main
import com.masai.service.ProductService;
import com.masai.service.SellerService;

@RestController
public class ProductController {

	@Autowired
	private ProductService pService;

	@Autowired
	private LoginLogoutService loginservice;

	@Autowired
	private SellerService sService;

	@Autowired
	private SellerDao sDao;

	// this method adds new product to catalog by seller(if seller is new it adds seller as well
//	if seller is already existing products will be mapped to same seller) and returns added product

	@PostMapping("/products")
	public ResponseEntity<Product> addProductToCatalogHandler(@RequestHeader("token") String token,
			@Valid @RequestBody Product product) {
		
<<<<<<< HEAD
		
		Product prod = pService.addProductToCatalog(product);
=======
>>>>>>> main
		
//		validating the seller and also current login status
		
		if (token.contains("seller") == false) {
			throw new LoginException("Invalid session token for seller");
		}
		loginservice.checkTokenStatus(token);

		
//		checking if the seller is already existing in database
		Seller Existingseller = sService.getSellerByMobile(product.getSeller().getMobile(), token);
		Optional<Seller> opt = sDao.findById(Existingseller.getSellerId());

		if (opt.isPresent()) {
			Seller seller = opt.get();

			product.setSeller(seller);

			Product prod = pService.addProductToCatalog(product);

			seller.getProduct().add(product);
			sDao.save(seller);

		} else {
			Product prod = pService.addProductToCatalog(product);
		}
	

		return new ResponseEntity<Product>(product, HttpStatus.ACCEPTED);

	}

	
	
	
	// This method gets the product which needs to be added to the cart returns
	// product

	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProductFromCatalogByIdHandler(@PathVariable("id") Integer id) {

		Product prod = pService.getProductFromCatalogById(id);

		return new ResponseEntity<Product>(prod, HttpStatus.FOUND);

	}

	// This method will delete the product from catalog and returns the response
	// This will be called only when the product qty will be zero or seller wants to
	// delete for any other reason

	@DeleteMapping("/product/{id}")
	public ResponseEntity<String> deleteProductFromCatalogHandler(@PathVariable("id") Integer id) {

		String res = pService.deleteProductFromCatalog(id);
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}
<<<<<<< HEAD
	
	
	@PutMapping("/products")
	public ResponseEntity<Product> updateProductInCatalogHandler(@Valid @RequestBody Product prod){
		
				Product prod1 = pService.updateProductIncatalog(prod);
				
				return new ResponseEntity<Product>(prod1,HttpStatus.OK);
		
=======

	@PutMapping("/products")
	public ResponseEntity<Product> updateProductInCatalogHandler(@Valid @RequestBody Product prod) {

		Product prod1 = pService.updateProductIncatalog(prod);

		return new ResponseEntity<Product>(prod1, HttpStatus.OK);

>>>>>>> main
	}

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProductsHandler() {

		List<Product> list = pService.getAllProductsIncatalog();

		return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
	}
<<<<<<< HEAD
	
	@GetMapping("/products/{catenum}")
	public ResponseEntity<List<ProductDTO>> getAllProductsInCategory(@PathVariable("catenum") String catenum){
		CategoryEnum ce = CategoryEnum.valueOf(catenum.toUpperCase());
		List<ProductDTO> list = pService.getProductsOfCategory(ce);
		return new ResponseEntity<List<ProductDTO>>(list,HttpStatus.OK);
		
	}
	
	@GetMapping("/products/status/{status}")
	public ResponseEntity<List<ProductDTO>> getProductsWithStatusHandler(@PathVariable("status") String status){
		
		ProductStatus ps = ProductStatus.valueOf(status.toUpperCase());
		List<ProductDTO> list = pService.getProductsOfStatus(ps);
		
		return new ResponseEntity<List<ProductDTO>>(list,HttpStatus.OK);
		
	}
	
	
	
=======

	@GetMapping("/products/{catenum}")
	public ResponseEntity<List<ProductDTO>> getAllProductsInCategory(@PathVariable("catenum") String catenum) {
		CategoryEnum ce = CategoryEnum.valueOf(catenum.toUpperCase());
		List<ProductDTO> list = pService.getProductsOfCategory(ce);
		return new ResponseEntity<List<ProductDTO>>(list, HttpStatus.OK);

	}

	@GetMapping("/products/status/{status}")
	public ResponseEntity<List<ProductDTO>> getProductsWithStatusHandler(@PathVariable("status") String status) {

		ProductStatus ps = ProductStatus.valueOf(status.toUpperCase());
		List<ProductDTO> list = pService.getProductsOfStatus(ps);

		return new ResponseEntity<List<ProductDTO>>(list, HttpStatus.OK);

	}

>>>>>>> main
}
