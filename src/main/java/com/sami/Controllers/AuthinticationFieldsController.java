package com.sami.Controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sami.dao.StudentFeedbackRepo;
import com.sami.dao.SubjectRepo;
import com.sami.dao.UserRepository;
import com.sami.entities.StudentFeedback;
import com.sami.entities.Subject;
import com.sami.entities.User;
import com.sami.helper.Message;

import java.security.Principal;
import java.util.List;

@Controller
public class AuthinticationFieldsController {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SubjectRepo subject;
	@Autowired
	private StudentFeedbackRepo studentFeedback;

	@RequestMapping("/")
	public String home(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
		model.addAttribute("title", "Home - SRH ERP");
		Pageable pageable = PageRequest.of(page, 3);
		Page<Subject> subjectList = this.subject.findAll(pageable);
		List<StudentFeedback> studentFeedbackList = this.studentFeedback.findAll();
		model.addAttribute("studentFeedbackList", studentFeedbackList);

		model.addAttribute("subjectList", subjectList);

		return "Dashboard/home";
	}

	@RequestMapping("/st-feedback")
	public String studentFeedback(Model model, StudentFeedback studentFeedback) {
		model.addAttribute("title", "Home - SRH ERP");
		this.studentFeedback.save(studentFeedback);

		return "redirect:/";
	}

	@RequestMapping("/all-subjects")
	public String allSubjects(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
		model.addAttribute("title", "Home - SRH ERP");
		Pageable pageable = PageRequest.of(page, 6);
		Page<Subject> subjectList = this.subject.findAll(pageable);
		model.addAttribute("subjectList", subjectList);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", subjectList.getTotalPages());
		return "Dashboard/all-subjects";
	}

	@RequestMapping("/about")
	public String about(Model model) {

		model.addAttribute("title", "about - SRH ERP");
		return "Dashboard/about";
	}

	@RequestMapping("/signup")
	public String signUp(Model model) {
		model.addAttribute("title", "Register - SRH ERP");
		model.addAttribute("user", new User());
		return "Authinticationfields/signup";
	}

//	this for Register user Data
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String Register(@ModelAttribute("user") User user,
			@RequestParam(value = "confirm_password") String confirm_password, Model model, HttpSession session) {
		try {
			if (!confirm_password.equals(user.getPassword())) {
				throw new Exception("your password Does not match");
			}
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setAbout("ROLE_ADMIN");
			this.userRepository.save(user);
			model.addAttribute("user", new User());
			session.setAttribute("message", new Message("Successfully Register!!", "alert-success"));
			return "Authinticationfields/signup";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("user", user);
			session.setAttribute("message", new Message("Something Went Wrong..!!" + e.getMessage(), "alert-danger"));
			return "Authinticationfields/signup";
		}

	}

	// handler for custom login page
	@RequestMapping("/login")
	public String login(Model model, Principal principal) {

		model.addAttribute("title", "login page");
//		if(principal!=null) {
//			String username=principal.getName();
//			
//		  User user= this.userRepository.getUserByUserName(username);
//		  if(user.getStatus().equals("Admission")) { return "Admission/index";}
//			}

		return "Authinticationfields/login";
	}
}
