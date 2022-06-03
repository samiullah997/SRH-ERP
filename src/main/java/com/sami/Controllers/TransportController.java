package com.sami.Controllers;

import java.security.Principal;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sami.dao.AssignBusRepo;
import com.sami.dao.BusMaintenanceRepo;
import com.sami.dao.BusRepo;
import com.sami.dao.CheckOutRepo;
import com.sami.dao.SeatBookRepo;
import com.sami.dao.StudentsRepository;
import com.sami.dao.UserRepository;
import com.sami.dao.VehicleConditionRepo;
import com.sami.entities.AsignBusRecord;
import com.sami.entities.Bus;
import com.sami.entities.BusMaintenance;
import com.sami.entities.CheckOut;
import com.sami.entities.SeatBook;
import com.sami.entities.Students;
import com.sami.entities.User;
import com.sami.entities.VehicleCondition;

@Controller
@RequestMapping("/Transport")
public class TransportController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BusRepo busRepo;
	@Autowired
	private StudentsRepository students;
	@Autowired
	private SeatBookRepo seatBookRepo;
	@Autowired
	private CheckOutRepo checkOutRepo;
	@Autowired
	private AssignBusRepo assignBusRepo;
	@Autowired
	private BusMaintenanceRepo busMaintenanceRepo;
	@Autowired
	private VehicleConditionRepo vehicleConditionRepo;

	@ModelAttribute
	public void addCommonData(Model model, Principal principal) {
		String userName = principal.getName();
		User user = userRepository.getUserByUserName(userName);
		model.addAttribute(user);

	}

	@RequestMapping("/index")
	public String dashboard(Model model) {

		model.addAttribute("title", "This is Transport Dashboard Page");
		int totalSeats=0;
		 int totalBus=(int) this.busRepo.count();
		 int totalReserveSeates=(int) this.seatBookRepo.count();
		 if(this.busRepo.getTotalBusSeats()!=null) {
		 totalSeats= this.busRepo.getTotalBusSeats();
		 }
		 int totalRemaingSeats=totalSeats-totalReserveSeates;
		 model.addAttribute("totalBus",totalBus);
		 model.addAttribute("totalReserveSeates",totalReserveSeates);
		 model.addAttribute("totalRemaingSeats",totalRemaingSeats);
		 
		return "Transport/Transport-home";
	}

	@RequestMapping("/Bus-Record")
	public String BusRecord(Model model, Bus bus, @RequestParam(value = "page", defaultValue = "0") int page) {

		model.addAttribute("title", "This is Bus Record Page");
		Pageable pageable = PageRequest.of(page, 6);
		Page<Bus> allBuses = this.busRepo.findAll(pageable);
		List<Students> students = this.students.findAll();
		model.addAttribute("students", students);
		model.addAttribute("allBuses", allBuses);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", allBuses.getTotalPages());
		return "Transport/Bus-Record";
	}

	@RequestMapping("/add-bus-record")
	public String addBusRecord(Model model, Bus bus) {

		model.addAttribute("title", "This is Bus Record Page");
		this.busRepo.save(bus);

		return "redirect:/Transport/Bus-Record";
	}

	@RequestMapping("/Seat-Reservation")
	public String seatReservation(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {

		model.addAttribute("title", "This is Seat Reservation Page");
		List<Students> students = this.students.findAll();
		model.addAttribute("students", students);
		Pageable pageable = PageRequest.of(page, 6);
		Page<SeatBook> seatBook = this.seatBookRepo.findAll(pageable);
		model.addAttribute("seatBook", seatBook);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", seatBook.getTotalPages());

		return "Transport/Seat-Reservation";
	}

	@RequestMapping("/Seat-Reservation-submit")
	public String seatReservationSubmit(Model model, SeatBook seatBook) {

		model.addAttribute("title", "This is Seat Reservation Page");
		this.seatBookRepo.save(seatBook);

		return "redirect:/Transport/Bus-Record";
	}

	@RequestMapping("/Seat-Reservation-form")
	public String seatReservationForm(Model model, SeatBook seatBooks,
			@RequestParam(value = "id", defaultValue = "0") int id, @RequestParam(value = "busName") String busName) {
		List<Bus> bus = this.busRepo.getBusRecordById(id);
		model.addAttribute("title", "This is Seat Reservation Page");
		model.addAttribute("Bus", bus);
		if (this.seatBookRepo.getBusRecordById(id) == null) {
			seatBooks.setSeatNo(1);
			model.addAttribute("maxSeatNo", seatBooks);
		} else {

			Bus bus2 = this.busRepo.getBusTotalSeatsById(id);
			int totalSeats = bus2.getBusSeat();
			int tSeats = totalSeats;
			int maxSeatNo = this.seatBookRepo.getBusRecordById(id);

			if (maxSeatNo == tSeats) {
				model.addAttribute("seatFull", "seatFull");
			}
			int msn = maxSeatNo + 1;
			seatBooks.setSeatNo(msn);
			model.addAttribute("maxSeatNo", seatBooks);
		}
		List<Students> students = this.students.findAll();
		model.addAttribute("students", students);

		return "Transport/Seat-Reservation-form";
	}

	@RequestMapping("/Seat-check-out")
	public String seatCheckOut(Model model, CheckOut checkOut, @RequestParam("seatBookId") int seatBookId) {

		model.addAttribute("title", "This is Seat Reservation Page");
		this.seatBookRepo.deleteById(seatBookId);
		this.checkOutRepo.save(checkOut);

		return "redirect:/Transport/Seat-Reservation";
	}

	@RequestMapping("/All-Check-Out-Details")
	public String AllCheckOutDetails(Model model, CheckOut checkOut,
			@RequestParam(value = "page", defaultValue = "0") int page) {

		model.addAttribute("title", "This is Seat Reservation Page");
		Pageable pageable = PageRequest.of(page, 6);
		Page<CheckOut> checkDetails = this.checkOutRepo.findAll(pageable);
		model.addAttribute("checkDetails", checkDetails);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", checkDetails.getTotalPages());
		return "Transport/All-Check-Out-Details";
	}

	@RequestMapping("/Seat-check-out-update-fee")
	public String seatCheckOutUpdateFee(Model model, CheckOut checkOut, @RequestParam("id") int id,
			@RequestParam("oldFee") float oldFee,
			@RequestParam("checkOutDate") Date checkOutDate) {
		this.checkOutRepo.updateTransportFeeRecord(id, checkOutDate,oldFee);

		return "redirect:/Transport/All-Check-Out-Details";
	}
	
	@RequestMapping("/asign-vehicle")
	public String seatAssignVehicles(Model model,Bus bus, AsignBusRecord asignBusRecord,VehicleCondition vehicleCondition) {
		vehicleCondition=this.vehicleConditionRepo.save(vehicleCondition);
		asignBusRecord.setVehicleConditionId(vehicleCondition);
		this.assignBusRepo.save(asignBusRecord);
		bus.setAsignStatus("Assigned");
		this.busRepo.updateBusStatusById(asignBusRecord.getBusId().getBusId(), bus.getAsignStatus());
		
		
		return "redirect:/Transport/Bus-Record";
	}
	@RequestMapping("/Bus-Maintenance")
	public String seatBusMaintenance(Model model,@RequestParam(value = "page", defaultValue = "0") int page) {
		Pageable pageable = PageRequest.of(page, 10);
		Page<BusMaintenance> busMaintenances=this.busMaintenanceRepo.findAll(pageable);
		model.addAttribute("busMaintenances",busMaintenances);
		List<Bus> buses=this.busRepo.findAll();
		model.addAttribute("buses",buses);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", busMaintenances.getTotalPages());
		return "Transport/Bus-Maintenance";
	}
	
	@RequestMapping("/Bus-Maintenance-submit")
	public String seatBusMaintenanceSubmit(Model model,BusMaintenance busMaintenance) {
		
		this.busMaintenanceRepo.save(busMaintenance);
		
		return "redirect:/Transport/Bus-Maintenance";
	}
	
	@RequestMapping("/Generate-Maintenance-Bill")
	public String generateMaintenanceBill(Model model,@RequestParam("id") int id) {
		
		BusMaintenance busMaintenance= this.busMaintenanceRepo.getOne(id);
		model.addAttribute("busMaintenance",busMaintenance);
		
		
		return "Transport/GenerateMaintenanceBill";
	}
	
	@RequestMapping("/show-asign-bus-record")
	public String asignBusRecord(Model model,@RequestParam("id") int id) {
		Bus busRecord=this.busRepo.getOne(id);
		model.addAttribute("busRecord",busRecord);
		
		return "Transport/show-asign-bus-record";
	}
	
	@RequestMapping("/checkout-bus")
	public String checkoutBus(Model model,@RequestParam("id") int id,
			@RequestParam("asignId") int asignId) {
		this.busRepo.updateBusStatusById(id, "Return");
		this.assignBusRepo.deleteById(asignId);
		return "redirect:/Transport/Bus-Record";
	}

}
