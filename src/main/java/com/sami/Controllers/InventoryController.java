package com.sami.Controllers;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sami.dao.CategoryRepo;
import com.sami.dao.CheckoutProductRepo;
import com.sami.dao.ProductsRepo;
import com.sami.dao.UserRepository;
import com.sami.entities.Categories;
import com.sami.entities.CheckoutProduct;
import com.sami.entities.Products;
import com.sami.entities.User;
import com.sami.helper.Message;

@Controller
@RequestMapping("/Inventory")
public class InventoryController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ProductsRepo productRepo;
	@Autowired
	private CheckoutProductRepo checkoutProductRepo;

	@ModelAttribute
	public void addCommonData(Model model, Principal principal) {

		String userName = principal.getName();
		User user = userRepository.getUserByUserName(userName);
		model.addAttribute(user);

	}

	@RequestMapping("/index")
	public String dashboard(Model model) {

		model.addAttribute("title", "This is Invetory Dashboard Page");
		int allCategories=(int)this.categoryRepo.count();
		int allProducts=(int) this.productRepo.count();
		model.addAttribute("allCategories",allCategories);
		model.addAttribute("allProducts",allProducts);
		
		return "Inventory/Inventory-home";
	}

	@RequestMapping("/Category")
	public String Category(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {

		model.addAttribute("title", "This is Invetory Category Page");
		Pageable pageable = PageRequest.of(page, 6);
		Page<Categories> categories = this.categoryRepo.findAll(pageable);
		model.addAttribute("categories", categories);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", categories.getTotalPages());
		return "Inventory/Category";
	}

	@RequestMapping("/Category-submit")
	public String categorySubmit(Model model, Categories categories,HttpSession session) {

		model.addAttribute("title", "This is Invetory Category Page");
		this.categoryRepo.save(categories);
		session.setAttribute("message", new Message("New Category Created","alert-success"));
		return "redirect:/Inventory/Category";
	}

	@RequestMapping("/Products")
	public String products(Model model, @RequestParam("id") int id,
			@RequestParam(value = "page", defaultValue = "0") int page, RedirectAttributes redirectAttributes) {
		model.addAttribute("title", "This is Invetory Dashboard Page");
		Pageable pageable = PageRequest.of(page, 6);
		Page<Products> products = this.productRepo.getAllProducts(pageable, id);
		model.addAttribute("products", products);

		model.addAttribute("id", id);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", products.getTotalPages());
		redirectAttributes.addAttribute("id", id);

		return "Inventory/Products";
	}
	
	@RequestMapping("/Product-delete")
	public String productDelete(Model model, Products products, @RequestParam("id") int id,
			@RequestParam("categoryId") int categoryId,
			RedirectAttributes redirectAttributes,HttpSession session) {

		model.addAttribute("title", "This is Invetory Category Page");
		this.productRepo.deleteById(id);
		session.setAttribute("message", new Message("Product Deleted Successfully","alert-danger"));

		redirectAttributes.addAttribute("id", categoryId);
		return "redirect:/Inventory/Products";
	}

	@RequestMapping("/Product-submit")
	public String productSubmit(Model model, Products products, @RequestParam("categoriesId") int id,
			RedirectAttributes redirectAttributes,HttpSession session) {

		model.addAttribute("title", "This is Invetory Category Page");
		this.productRepo.save(products);
		session.setAttribute("message", new Message("Product Add Succesfully","alert-success"));

		redirectAttributes.addAttribute("id", id);
		return "redirect:/Inventory/Products";
	}

	@RequestMapping("/Product-checkout-submit")
	public String checkoutSubmitProduct(Model model, Products products, CheckoutProduct checkoutProduct,
			@RequestParam("categoriesId") int id, @RequestParam("productId") int productId,
			@RequestParam("quantity") String quantity, @RequestParam("productPrice") int productPrice,
			@RequestParam("customerName") String customerName,
			@RequestParam("requiredQuantity") String quantityRequired, @RequestParam("sellingPrice") int sellingPrice,
			RedirectAttributes redirectAttributes) {
		products.setId(productId);
		checkoutProduct.setProductsid(products);
		checkoutProduct.setSellingPrice(sellingPrice);
		checkoutProduct.setCustomerName(customerName);
		checkoutProduct.setSellingQuantity(quantityRequired);
		products.setProductPrice(productPrice);
		products.setQuantity(quantity);
		this.checkoutProductRepo.save(checkoutProduct);
		this.productRepo.updateProductCategoryId(productId, quantity);
		model.addAttribute("title", "This is Invetory Category Page");
//		this.productRepo.save(products);
		redirectAttributes.addAttribute("id", id);
		return "redirect:/Inventory/Products";
	}

	@RequestMapping("/checkout-products")
	public String CheckOutProducts(Model model,@RequestParam(value = "page", defaultValue = "0") int page) {
		model.addAttribute("title", "This is Invetory Dashboard Page");
		Pageable pageable = PageRequest.of(page, 6);
		Page<CheckoutProduct> checkOutProducts = this.checkoutProductRepo.findAll(pageable);
		model.addAttribute("checkOutProducts", checkOutProducts);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", checkOutProducts.getTotalPages());

		return "Inventory/check-out-products";
	}

}
