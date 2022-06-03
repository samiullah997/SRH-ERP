package com.sami.Controllers;

import java.security.Principal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.sami.dao.AccountingRepo;
import com.sami.dao.BusMaintenanceRepo;
import com.sami.dao.ProductsRepo;
import com.sami.dao.StudentFeeRecordRepo;
import com.sami.dao.UserRepository;
import com.sami.entities.User;
import com.sun.istack.Nullable;


@Controller
@RequestMapping("/Accounting")
public class AccountingController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private StudentFeeRecordRepo studentFeeRecordRepo;
	@Autowired
	private ProductsRepo productRepo;
	@Autowired
	private BusMaintenanceRepo busMaintenanceRepo=null;
	@Autowired
	private AccountingRepo accountingRepo;
	
	
	@ModelAttribute
	public void addCommonData(Model model, Principal principal) {
		String userName = principal.getName();
		User user = userRepository.getUserByUserName(userName);
		model.addAttribute(user);

	}
	
	@RequestMapping("/index")
	public String dashboard(Model model) {
		@Nullable
		int grossIncome=0;
		int totalProductPrice=0;
		@Nullable
		int totalBusMaintenance=0;
		int netIcome=0;
		int totalSemesterFee=0;
		int totalRegistrationFee=0;
		model.addAttribute("title", "This is Accounting Dashboard Page");
			if(this.studentFeeRecordRepo.getGrossIncome()!=null) {
			grossIncome=this.studentFeeRecordRepo.getGrossIncome();
			}
			if(this.studentFeeRecordRepo.getNetIncome()!=null) {
			netIcome=this.studentFeeRecordRepo.getNetIncome();
			}
			if(this.productRepo.getAllTotalProductsPrice()!=null) {
			totalProductPrice=this.productRepo.getAllTotalProductsPrice();
			}
			if(this.busMaintenanceRepo.getAllBusMaintenace()!=null) {
			totalBusMaintenance=this.busMaintenanceRepo.getAllBusMaintenace();
			}
			if(this.accountingRepo.getTotalSemesterFee()!=null) {
			totalSemesterFee=this.accountingRepo.getTotalSemesterFee();
			}
			if(this.accountingRepo.getTotalRegistrationFee()!=null) {
			totalRegistrationFee=this.accountingRepo.getTotalRegistrationFee();
			}		
		
		
		model.addAttribute("grossIncome",grossIncome+totalProductPrice+totalBusMaintenance+totalSemesterFee+totalRegistrationFee);
		model.addAttribute("netIcome",(netIcome+totalRegistrationFee+totalSemesterFee)-totalProductPrice-totalBusMaintenance);
		model.addAttribute("totalDues",grossIncome-netIcome);
		model.addAttribute("totalExpenses",totalProductPrice+totalBusMaintenance);
		 
		return "Accounting/home";
	}
	
	@RequestMapping("/balance-sheet")
	public String balanceSheet(Model model) {

		model.addAttribute("title", "This is Accounting Dashboard Page");
		int totalProductPrice=0;
		int grossIncome=0;
		int totalBusMaintenance=0;
		int totalRegistrationFee=0;
		int totalSemesterFee=0;
		
		
		if(this.productRepo.getAllTotalProductsPrice()!=null) {
		totalProductPrice=this.productRepo.getAllTotalProductsPrice();
		}
		if(this.studentFeeRecordRepo.getGrossIncome()!=null) {
		grossIncome=this.studentFeeRecordRepo.getGrossIncome();
		}
		if(this.busMaintenanceRepo.getAllBusMaintenace()!=null) {
		totalBusMaintenance=this.busMaintenanceRepo.getAllBusMaintenace();
		}
		if(this.accountingRepo.getTotalRegistrationFee()!=null) {
		totalRegistrationFee=this.accountingRepo.getTotalRegistrationFee();
		}
		if(this.accountingRepo.getTotalSemesterFee()!=null) {
		totalSemesterFee=this.accountingRepo.getTotalSemesterFee();
		}
		model.addAttribute("totalProductPrice",totalProductPrice);
		model.addAttribute("grossIncome",grossIncome);
		model.addAttribute("totalAssets",totalProductPrice+grossIncome+totalSemesterFee);
		model.addAttribute("totalBusMaintenance",totalBusMaintenance);
		model.addAttribute("totalLaibilities",totalBusMaintenance+totalProductPrice);
		model.addAttribute("totalRegistrationFee",totalRegistrationFee);
		model.addAttribute("totalSemesterFee",totalSemesterFee);
		return "Accounting/balance-sheet";
	}
	
	

	@RequestMapping("/balance-sheet-search")
	public String balanceSheetSearch(Model model,@RequestParam("startDate")Date startDate,@RequestParam("endDate")Date endDate) {

		model.addAttribute("title", "This is Accounting Dashboard Page");
		int totalProductPrice=0;
		int grossIncome=0;
		int totalBusMaintenance=0;
		int totalRegistrationFee=0;
		int totalSemesterFee=0;
		
		if(this.productRepo.getAllTotalProductsPriceByDate(startDate, endDate)!=null) {
		totalProductPrice=this.productRepo.getAllTotalProductsPriceByDate(startDate,endDate);
		}
		if(this.studentFeeRecordRepo.getGrossIncomeByDate(startDate.toString(),endDate.toString())!=null) {
		grossIncome=this.studentFeeRecordRepo.getGrossIncomeByDate(startDate.toString(),endDate.toString());
		}
		if(this.busMaintenanceRepo.getAllBusMaintenaceByDate(startDate, endDate)!=null) {
		totalBusMaintenance=this.busMaintenanceRepo.getAllBusMaintenaceByDate(startDate, endDate);
		}
		if(this.accountingRepo.getTotalRegistrationFeeByDate(startDate, endDate)!=null) {
		totalRegistrationFee=this.accountingRepo.getTotalRegistrationFeeByDate(startDate, endDate);
		}
		if(this.accountingRepo.getTotalSemesterFeeByDate(startDate, endDate)!=null) {
		totalSemesterFee=this.accountingRepo.getTotalSemesterFeeByDate(startDate, endDate);
		}
		model.addAttribute("totalProductPrice",totalProductPrice);
		model.addAttribute("grossIncome",grossIncome);
		model.addAttribute("totalAssets",totalProductPrice+grossIncome+totalSemesterFee);
		model.addAttribute("totalBusMaintenance",totalBusMaintenance);
		model.addAttribute("totalLaibilities",totalBusMaintenance+totalProductPrice);
		model.addAttribute("totalRegistrationFee",totalRegistrationFee);
		model.addAttribute("totalSemesterFee",totalSemesterFee);
		return "Accounting/balance-sheet";
	}
	

	@RequestMapping("/trail-sheet")
	public String trailSheet(Model model) {

		model.addAttribute("title", "This is Accounting Dashboard Page");
		int expenses=0;
		int grossIncome=0;
		int totalBusMaintenance=0;
		int totalRegistrationFee=0;
		
		if(this.productRepo.getAllTotalProductsPrice()!=null) {
		expenses=this.productRepo.getAllTotalProductsPrice();
		}
		if(this.studentFeeRecordRepo.getGrossIncome()!=null) {
		grossIncome=this.studentFeeRecordRepo.getGrossIncome();
		}
		if(this.busMaintenanceRepo.getAllBusMaintenace()!=null) {
		totalBusMaintenance=this.busMaintenanceRepo.getAllBusMaintenace();
		}if(this.accountingRepo.getTotalRegistrationFee()!=null) {
		totalRegistrationFee=this.accountingRepo.getTotalRegistrationFee();
		}
		model.addAttribute("expenses",expenses);
		//model.addAttribute("grossIncome",grossIncome);
		model.addAttribute("Assets",expenses+grossIncome);
		//model.addAttribute("totalBusMaintenance",totalBusMaintenance);
		model.addAttribute("Laibilities",totalBusMaintenance+expenses);
		model.addAttribute("totalRegistrationFee",totalRegistrationFee);
		model.addAttribute("totalDebit",totalBusMaintenance+expenses);
		model.addAttribute("totalCradit",expenses+grossIncome+expenses);
		 
		return "Accounting/trail-sheet";
	}
	
	@RequestMapping("/trail-sheet-search")
	public String trailSheetSearch(Model model,@RequestParam("startDate")Date startDate,@RequestParam("endDate")Date endDate) {

		model.addAttribute("title", "This is Accounting Dashboard Page");
		int expenses=0;
		int grossIncome=0;
		int totalBusMaintenance=0;
		int totalRegistrationFee=0;
		
		if(this.productRepo.getAllTotalProductsPriceByDate(startDate, endDate)!=null) {
		expenses=this.productRepo.getAllTotalProductsPriceByDate(startDate, endDate);
		}
		if(this.studentFeeRecordRepo.getGrossIncomeByDate(startDate.toString(), endDate.toString())!=null) {
		grossIncome=this.studentFeeRecordRepo.getGrossIncomeByDate(startDate.toString(), endDate.toString());
		}
		if(this.busMaintenanceRepo.getAllBusMaintenaceByDate(startDate, endDate)!=null) {
		totalBusMaintenance=this.busMaintenanceRepo.getAllBusMaintenaceByDate(startDate, endDate);
		}if(this.accountingRepo.getTotalRegistrationFeeByDate(startDate, endDate)!=null) {
		totalRegistrationFee=this.accountingRepo.getTotalRegistrationFeeByDate(startDate, endDate);
		}
		model.addAttribute("expenses",expenses);
		//model.addAttribute("grossIncome",grossIncome);
		model.addAttribute("Assets",expenses+grossIncome);
		//model.addAttribute("totalBusMaintenance",totalBusMaintenance);
		model.addAttribute("Laibilities",totalBusMaintenance+expenses);
		model.addAttribute("totalRegistrationFee",totalRegistrationFee);
		model.addAttribute("totalDebit",totalBusMaintenance+expenses);
		model.addAttribute("totalCradit",expenses+grossIncome+expenses);
		 
		return "Accounting/trail-sheet";
	}
	
	public static void getCurrentTimeUsingDate() {
	    Date date = new Date(0);
	    String strDateFormat = "hh:mm:ss a";
	    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
	    String formattedDate= dateFormat.format(date);
	    System.out.println("Current time of the day using Date - 12 hour format: " + formattedDate);
	}
	
	
}
