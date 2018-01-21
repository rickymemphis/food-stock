package foodstock;

import org.springframework.web.bind.annotation.*;
import foodstock.bo.Product;
import foodstock.services.DatabaseManager;

@RestController
@RequestMapping("/products")
public class ProductController {

	@RequestMapping(value = "/{skn}", method = RequestMethod.GET)
	public Product getProduct(@PathVariable("skn") String skn) {

		Product product = DatabaseManager.getInstance().readProduct(skn);

		return product;
	}

	@RequestMapping(value = "/{skn}", method = RequestMethod.POST)
	public Product postProduct(@PathVariable("skn") String skn, @RequestBody Product product) {

		boolean result = DatabaseManager.getInstance().createProduct(product);

		return product;
	}

}
