package foodstock;

import org.springframework.web.bind.annotation.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import foodstock.bo.Inventory;
import foodstock.services.DatabaseManager;

@RestController
@RequestMapping("/products/{skn}/inventory")
public class InventoryController {

	@RequestMapping(method = RequestMethod.GET)
	public List<Inventory> getInventory(
			@PathVariable("skn") String skn,
			@RequestParam(value = "expirationDate", required = false) String expirationDate) throws ParseException {

		Date expirationDateLimit = null;

		if (expirationDate != null) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			expirationDateLimit = df.parse(expirationDate);
		} else {
			// default to now
			expirationDateLimit = new Date();
		}

		List<Inventory> inventory = DatabaseManager.getInstance().readInventory(skn, expirationDateLimit);

		return inventory;
	}

	@RequestMapping(method = RequestMethod.POST)
	public Inventory postInventory(@PathVariable("skn") String skn, @RequestBody Inventory inventory) {

		boolean result = DatabaseManager.getInstance().createInventory(inventory);

		return inventory;
	}

}
