package com.sami.Controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sami.dao.AccountingRepo;
import com.sami.dao.StudentsRepository;
import com.sami.dao.SubjectRepo;
import com.sami.dao.TeacherRepo;
import com.sami.dao.UserRepository;
import com.sami.entities.AccountingRecord;
import com.sami.entities.Students;
import com.sami.entities.Subject;
import com.sami.entities.Teacher;
import com.sami.entities.User;
import com.sami.helper.Message;

@Controller
@RequestMapping("/Admission")
public class AdmissionController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private StudentsRepository studentsRepository;
	@Autowired
	private SubjectRepo subjectsRepo;
	@Autowired
	private TeacherRepo teachersRepo;
	
	@Autowired
	private AccountingRepo accountingRepo;
	
	@ModelAttribute
	public void addCommonData(Model model, Principal principal,HttpSession session) {
		String userName = principal.getName();
		User user = userRepository.getUserByUserName(userName);
		if(user.getStatus().equals("Admission")) {
			model.addAttribute(user);
		}else {
			session.setAttribute("message", new Message("Try agian...!!", "alert-danger"));
			
		}
		
	}
	
	@RequestMapping("/index")
	public String dashboard(Model model) {
		int totalSemesterFee=0;
		int totalRegistrationFee=0;
		model.addAttribute("title","This is Home Page Page");
		int totalStudents=(int) this.studentsRepository.count();
		if(this.accountingRepo.getTotalSemesterFee()!=null) {
		 totalSemesterFee=(int) this.accountingRepo.getTotalSemesterFee();
		}
		if(this.accountingRepo.getTotalRegistrationFee()!=null) {
		totalRegistrationFee=(int) this.accountingRepo.getTotalRegistrationFee();
		}
		model.addAttribute("totalSemesterFee",totalSemesterFee);
		model.addAttribute("totalRegistrationFee",totalRegistrationFee);
		model.addAttribute("totalStudents",totalStudents);
		
		return "Admission/home";
	}
	
	@GetMapping("/student-registration")
	public String studentRegistration(Model model,AccountingRecord accountingRecord,Students students) {

		model.addAttribute("title", "Student Registration List Page");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yy");  
		   LocalDateTime now = LocalDateTime.now();
		   String studentRollNO=this.studentsRepository.getStudentNoByBatch("Bcs-"+dtf.format(now));
		   int RollNO=(Integer.parseInt(studentRollNO)+1);
		model.addAttribute("students", new Students());
		model.addAttribute("studentRollNO",RollNO);
		return "Admission/student_registration";

	}

	@PostMapping("/st-register")
	public String studentRegister(@ModelAttribute(name = "students") Students students,
			@RequestParam(value = "slipNumber",defaultValue = "0" ) String slipNumber,
			@RequestParam(value = "feeDate",defaultValue = "0" ) Date feeDate,
			@RequestParam("image") MultipartFile file,
			Model model,
			HttpSession session,
			
			AccountingRecord accountingRecord) throws IOException {
		
		
		if (file.isEmpty()) {
			System.out.print("File is Empty");
		} else {
			students.setImageUrl(file.getOriginalFilename());
			File saveFile;
			saveFile = new ClassPathResource("static/images").getFile();
			Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		}
		students.setYearOfSubmission(String.valueOf(students.getDate()));
		if(this.studentsRepository.getStudentsByCNIC(students.getCnicNo())!=null) {
			session.setAttribute("message", new Message("Student Already Registered!", "alert-danger"));

		}else {
		Students students2 = this.studentsRepository.save(students);
		accountingRecord.setStudentsId(students2);
		accountingRecord.setType("Registration Fee");
		accountingRecord.setDate(feeDate);
		accountingRecord.setSlipNumber(slipNumber);
		this.accountingRepo.save(accountingRecord);
		System.out.println(students2);
		session.setAttribute("message", new Message("Successfully Registered!", "alert-success"));
		}
		
		return "Admission/student_registration";

	}
	
	@RequestMapping("/all-batches")
	public String StudentBatchResult(Model model, @RequestParam(value = "page",defaultValue = "0" ) int page,
			RedirectAttributes redirectAttributes,
			Principal principal) {
		model.addAttribute("title", "Student Result Data Page");
		Pageable pageable = PageRequest.of(page, 10);
		Page<Students> studentBatch = this.studentsRepository.getAllStudentsBatch(pageable);
		model.addAttribute("batchRecord", studentBatch);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", studentBatch.getTotalPages());

		return "Admission/all-batches";
	}
	
	@RequestMapping("/student_semester_record")
	public String semester(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "batch", required = false, defaultValue = "Bcs-17") String batch, Model model,
			Principal principal) {
		model.addAttribute("title", "User Semester List Page");
		System.out.print(batch);
		model.addAttribute("batch", batch);
		return "Admission/student_semester_record";
	}
	
	@RequestMapping("/fee_record")
	public String resultList(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "batch", required = false, defaultValue = "Bcs-17") String batch,
			@RequestParam(value = "semester", required = false, defaultValue = "1st") String semester, Model model,
			Principal principal, Subject subject) {
		model.addAttribute("title", "User Result List Page");
		Pageable pageable = PageRequest.of(page, 10);
		System.out.print(batch);
		List<Students> students=this.studentsRepository.getAllStudentsByBatch(batch);
		Page<AccountingRecord> studentFee = this.accountingRepo.getAllStudentsFeeRecord(pageable, batch, semester);
		model.addAttribute("students",students);
		model.addAttribute("studentFee", studentFee);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", studentFee.getTotalPages());
		model.addAttribute("batch", batch);
		model.addAttribute("semester", semester);

		return "Admission/fee_record";
	}
	
	
	@RequestMapping("/semester-fee-submit")
	public String semesterFeeSubmited(Model model,AccountingRecord accountingRecord,RedirectAttributes redirectAttributes,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "batch", required = false, defaultValue = "Bcs-17") String batch,
			@RequestParam(value = "semester", required = false, defaultValue = "1st") String semester,
			HttpSession session) {

		model.addAttribute("title", "Student Registration List Page");
		accountingRecord.setType("Semester Fee");
		accountingRecord.setSemester(semester);
		if(this.accountingRepo.getAllStudentsFeeRecordByRollNoAndSemester(accountingRecord.getType(), semester, accountingRecord.getStudentsId().id)!=null) {
			session.setAttribute("message", new Message("Fee Already Registered!", "alert-danger"));
		}else {
		this.accountingRepo.save(accountingRecord);
		session.setAttribute("message", new Message("Fee Submitted Successfully!", "alert-success"));
		}
		redirectAttributes.addAttribute("page", page);
		redirectAttributes.addAttribute("batch", batch);
		redirectAttributes.addAttribute("semester", semester);

		return "redirect:/Admission/fee_record";

	}
	
	@RequestMapping("/semester-fee-update")
	public String semesterFeeUpdate(Model model,AccountingRecord accountingRecord,RedirectAttributes redirectAttributes,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "batch", required = false, defaultValue = "Bcs-17") String batch,
			@RequestParam(value = "semester", required = false, defaultValue = "1st") String semester,
			HttpSession session) {

		model.addAttribute("title", "Student Registration List Page");
		
		this.accountingRepo.updateFeeRecord(accountingRecord.getSubmitAmount(),accountingRecord.getAccountId());
		session.setAttribute("message", new Message("Fee updated Successfully!", "alert-success"));
		redirectAttributes.addAttribute("page", page);
		redirectAttributes.addAttribute("batch", batch);
		redirectAttributes.addAttribute("semester", semester);

		return "redirect:/Admission/fee_record";

	}
	
	@RequestMapping("/all-subjects")
	public String allSubjects(@RequestParam(value = "page", required = false, defaultValue = "0") int page,Model model,
			Principal principal) {
		
		model.addAttribute("title", "All Subjects Page");
		Pageable pageable = PageRequest.of(page, 12);
		Page<Subject> allSubjects=this.subjectsRepo.getAllSubjectOrderBySemester(pageable);
		
		
		model.addAttribute("allSubjects",allSubjects);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", allSubjects.getTotalPages());
		return "Admission/all_subjects";
	}
	
	@RequestMapping("/submit-subject")
	public String SubmitSubjectRecord(@RequestParam(value = "page", required = false, defaultValue = "0") int page,Model model,
			Subject subject,Principal principal,HttpSession session) {
		this.subjectsRepo.save(subject);
		model.addAttribute("title", "All Teachers Page");
		session.setAttribute("message", new Message("Subject added Successfully!", "alert-success"));

		return "redirect:/Admission/all-subjects";
	}
	
	@RequestMapping("/all-teachers")
	public String allTeacher(@RequestParam(value = "page", required = false, defaultValue = "0") int page,Model model,
			Principal principal) {
		
		model.addAttribute("title", "All Teachers Page");
		Pageable pageable = PageRequest.of(page, 12);
		Page<Teacher> allTeacher=this.teachersRepo.findAll(pageable);
		
		
		model.addAttribute("allTeacher",allTeacher);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", allTeacher.getTotalPages());
		
		return "Admission/all_teachers";
	}
	
	@RequestMapping("/submit-teacher")
	public String SubmitTeacherRecord(@RequestParam(value = "page", required = false, defaultValue = "0") int page,Model model,
			Teacher teacher,Principal principal,HttpSession session) {
		this.teachersRepo.save(teacher);
		model.addAttribute("title", "All Teachers Page");
		session.setAttribute("message", new Message("Teacher Record added Successfully!", "alert-success"));

		return "redirect:/Admission/all-teachers";
	}
	


}
