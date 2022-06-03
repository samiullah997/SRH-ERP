package com.sami.Controllers;

import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sami.dao.AccountingRepo;
import com.sami.dao.BatchRecodeRepo;
import com.sami.dao.CheckCraditHourRepo;
import com.sami.dao.DateSheetHeaderRepo;
import com.sami.dao.DateSheetRepo;
import com.sami.dao.DutyRosterRepo;
import com.sami.dao.ExamListRepository;
import com.sami.dao.ExamOldListRepo;
import com.sami.dao.GPARecordRepo;
import com.sami.dao.PaperRepository;
import com.sami.dao.StudentFeeRecordRepo;
import com.sami.dao.StudentsRepository;
import com.sami.dao.SubjectRepo;
import com.sami.dao.TeacherRepo;
import com.sami.dao.UserRepository;
import com.sami.entities.AccountingRecord;
import com.sami.entities.BatchRecord;
import com.sami.entities.CheckCraditHour;
import com.sami.entities.DateSheetHeader;
import com.sami.entities.DateSheetRecords;
import com.sami.entities.DutyRosterRecord;
import com.sami.entities.ExamList;
import com.sami.entities.ResultList;
import com.sami.entities.StudentFeeRcord;
import com.sami.entities.Students;
import com.sami.entities.Subject;
import com.sami.entities.Teacher;
import com.sami.entities.User;
import com.sami.entities.ExamOldList;
import com.sami.entities.GPARecord;
import com.sami.helper.Message;

@Controller
@RequestMapping("/examination")
public class ExaminationController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private StudentsRepository studentsRepository;
	@Autowired
	private PaperRepository paperRepository;
	@Autowired
	StudentFeeRecordRepo studentFeeRecordRepo;
	@Autowired
	ExamListRepository examListRepository;
	@Autowired
	BatchRecodeRepo batchRecordRepo;
	@Autowired
	DateSheetRepo dateSheetRepo;
	@Autowired
	DateSheetHeaderRepo dateSheetHeaderRepo;
	@Autowired
	SubjectRepo subjectRepo;
	@Autowired
	ExamOldListRepo examOldListRepo;
	@Autowired
	GPARecordRepo gpaRecordRepo;
	@Autowired
	TeacherRepo teacherRepo;
	@Autowired
	DutyRosterRepo dutyRosterRepo;
	@Autowired
	AccountingRepo acccountingRepo;
	@Autowired
	CheckCraditHourRepo checkCraditHourRepo;
	

	@ModelAttribute
	public void addCommonData(Model model, Principal principal,HttpSession session) {
		String userName = principal.getName();
		User user = userRepository.getUserByUserName(userName);
		if(user.getStatus().equals("Examination")) {
			model.addAttribute(user);
		}else {
			session.setAttribute("message", new Message("Try agian...!!", "alert-danger"));
			
		}
		
	}
	
	@RequestMapping("/index")
	public String dashboard(Model model) {
		
		model.addAttribute("title","This is Dashboard Page");

		
		int totalResults =(int) this.paperRepository.count();
		int totalRepeatStudents=this.paperRepository.getAllRepeaterListStudents();
		int totalImproveStudents=this.paperRepository.getAllImproveListStudents();
		model.addAttribute("totalResults",totalResults);
		model.addAttribute("totalRepeateStudents",totalRepeatStudents);
		model.addAttribute("totalImproveStudents",totalImproveStudents);
		
		return "examination/dashboard";
	}

	@RequestMapping("/student-result-data")
	public String StudentBatchResult(Model model, @RequestParam(value = "page",defaultValue = "0" ) int page,
			RedirectAttributes redirectAttributes,
			Principal principal) {
		model.addAttribute("title", "Student Result Data Page");
		Pageable pageable = PageRequest.of(page, 10);
		Page<Students> studentBatch = this.studentsRepository.getAllStudentsBatch(pageable);
		model.addAttribute("batchRecord", studentBatch);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", studentBatch.getTotalPages());

		return "examination/student-result-data";
	}
	
	@RequestMapping("/students-all-data")
	public String studentsAllData(Model model,@RequestParam("batch")String batch) {
		
		model.addAttribute("batch",batch);
		return "examination/students-all-data";
	}
	
	@RequestMapping("/semester")
	public String semester(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "batch", required = false, defaultValue = "Bcs-17") String batch, Model model,
			Principal principal) {
		model.addAttribute("title", "User Semester List Page");
		System.out.print(batch);
		model.addAttribute("batch", batch);
		return "examination/semester";
	}

	@PostMapping("/batch-register")
	public String paperRegister(BatchRecord batchRecord, Model model, HttpSession session) throws IOException {

		batchRecord = this.batchRecordRepo.save(batchRecord);
		System.out.println(batchRecord);
		session.setAttribute("message", new Message("Batch Record Inserted Successfully!!", "alert-success"));
		return "redirect:/examination/student-result-data/0";

	}

	@GetMapping("/repeater-list/{page}")
	public String repeaterListForm(@PathVariable("page") int page, Model model) {

		model.addAttribute("title", "Repeater List Page");
		Pageable pageable = PageRequest.of(page, 10);

		Page<ResultList> resultList = this.paperRepository.getAllRepeaterListStudents(pageable);
		System.out.print(resultList);
		model.addAttribute("resultList", resultList);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", resultList.getTotalPages());
		return "examination/repeater-list";
	}

	@RequestMapping("/improvement-list/{page}")
	public String improvementListForm(@PathVariable("page") int page, Model model) {

		model.addAttribute("title", "Improvement List Page");
		Pageable pageable = PageRequest.of(page, 10);

		Page<ResultList> resultList = this.paperRepository.getAllImprovementListStudents(pageable);
		System.out.print(resultList);
		model.addAttribute("resultList", resultList);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", resultList.getTotalPages());

		return "examination/improvement-list";
	}

	
	@RequestMapping("/result-list")
	public String resultList(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "batch", required = false, defaultValue = "Bcs-17") String batch,
			@RequestParam(value = "semester", required = false, defaultValue = "1st") String semester, Model model,
			Principal principal, Subject subject) {
		model.addAttribute("title", "User Result List Page");
		Pageable pageable = PageRequest.of(page, 10);
		System.out.print(batch);
		Page<ResultList> resultList = this.paperRepository.getAllRsultListByPage(pageable, batch, semester);
		List<Subject> subjects = this.subjectRepo.getSubjectBySemester(semester);
		List<Students> allStudents=this.studentsRepository.getAllStudentsByBatch(batch);
		model.addAttribute("allStudents",allStudents);
		model.addAttribute("subjects", subjects);
		model.addAttribute("resultList", resultList);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", resultList.getTotalPages());
		model.addAttribute("batch", batch);
		model.addAttribute("semester", semester);

		return "examination/result-list";
	}
	
	@RequestMapping("/search-result-list")
	public String searchResultList(@RequestParam(value = "id") int id,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "batch") String batch,
			@RequestParam(value = "semester") String semester, Model model,
			Principal principal, Subject subject) {
		model.addAttribute("title", "User Result List Page");
		Pageable pageable = PageRequest.of(page, 10);
		System.out.print(batch);
		Page<ResultList> resultList = this.paperRepository.getAllRsultListById(pageable,id);
		List<Subject> subjects = this.subjectRepo.getSubjectBySemester(semester);
		model.addAttribute("subjects", subjects);
		model.addAttribute("resultList", resultList);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", resultList.getTotalPages());
		model.addAttribute("batch", batch);
		model.addAttribute("semester", semester);

		return "examination/result-list";
	}

	@PostMapping("/paper-register")
	public String paperRegister(@RequestParam("marks") int marks, @RequestParam("totalMarks") int totalMarks,
			RedirectAttributes redirectAttributes,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "batch") String batch, @RequestParam(value = "semester") String semester,
			ResultList resultList,  Model model, HttpSession session) throws IOException {
		float value = 0;
		resultList.setBatch(batch);
		resultList.setSemester(semester);
		String paperName = resultList.getPaperName();
		int id = resultList.getId();
		Students studentDetails=this.studentsRepository.getStudentsById(id);
		Subject subject = this.subjectRepo.getSubjectByPaperName(paperName);
		String craditHour = subject.getSubjectCraditHour();
		float totalCraditHour = this.subjectRepo.selectTotalsCraditHours(semester);
		

		if (marks >= 90 && marks <= 100) {
			resultList.setValue(4);

			value = 4;
		} else if (marks < 50) {
			resultList.setValue(0);
			value = 0;
		} else {
			value = findGrad(marks);
			resultList.setValue(value);
		}
		
		
		
		int craditHour2 = Integer.parseInt(craditHour);

		float gp = (float) (value * craditHour2);

		resultList.setGp(gp);
		resultList.setFatherName(studentDetails.fatherName);
		resultList.setRegistrationNo(studentDetails.getRegistrationNo());
		resultList.setStudentName(studentDetails.firstName+' '+studentDetails.lastName);
		resultList.setDepartment(studentDetails.getDepartment());
		resultList.setRollNo(studentDetails.getRollNo());
		
		if(this.paperRepository.getAllRsultListBySemesterAndBatchForPaperChecking(semester, batch, studentDetails.getRollNo(), paperName)!=null) {
			session.setAttribute("message", new Message("Paper Records Already Registered!!", "alert-danger"));
		}else {
		this.paperRepository.save(resultList);
		float GPA=0;
		if (this.paperRepository.selectTotalsGradPoints(studentDetails.getRollNo() ,semester, batch) != null) {
			Float totalGp = this.paperRepository.selectTotalsGradPoints(studentDetails.getRollNo(), semester, batch);
			GPA = totalGp / totalCraditHour;
			System.out.println(totalGp);
		}
		
		float CGPA=0;
		if (this.paperRepository.selectTotalsGradPoints(studentDetails.getRollNo(), batch) != null) {
			String firstsemester="1st";
			float totalCraditHour2 = this.subjectRepo.selectTotalsCraditHours(firstsemester,semester);
			Float totalGp = this.paperRepository.selectTotalsGradPointsBySemester(studentDetails.getRollNo(),batch,semester);
			CGPA = totalGp / totalCraditHour2;	
			System.out.println(totalGp);
		}
		saveGPAData(batch,semester, studentDetails.getRollNo(), GPA, CGPA);
		session.setAttribute("message", new Message("Paper Records Inserted Successfully!!", "alert-success"));
		}
		redirectAttributes.addAttribute("page", page);
		redirectAttributes.addAttribute("batch", batch);
		redirectAttributes.addAttribute("semester", semester);
		return "redirect:/examination/result-list";

}
	
	
	@PostMapping("/paper-update")
	public String paperUpdate(@RequestParam("marks") int marks, @RequestParam("totalMarks") int totalMarks,
			@RequestParam("id") int id,@RequestParam("rollNo") String rollNo,@RequestParam("paperName") String paperName,
			RedirectAttributes redirectAttributes,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "batch") String batch, @RequestParam(value = "semester") String semester,
			ResultList resultList,  Model model, HttpSession session) throws IOException {
		float value = 0;
		Subject subject = this.subjectRepo.getSubjectByPaperName(paperName);
		String craditHour = subject.getSubjectCraditHour();
		//float totalCraditHour = this.subjectRepo.selectTotalsCraditHours(semester);
		

		if (marks >= 90 && marks <= 100) {
			resultList.setValue(4);

			value = 4;
		} else if (marks < 50) {
			resultList.setValue(0);
			value = 0;
		} else {
			value = findGrad(marks);
			resultList.setValue(value);
		}
		
		
		
		int craditHour2 = Integer.parseInt(craditHour);

		float gp = (float) (value * craditHour2);

		resultList.setGp(gp);
		
		
		
		this.paperRepository.updatePaperMarks(id, marks, resultList.getGp(),resultList.getValue());
//		float GPA=0;
//		if (this.paperRepository.selectTotalsGradPoints(rollNo ,semester, batch) != null) {
//			Float totalGp = this.paperRepository.selectTotalsGradPoints(rollNo, semester, batch);
//			GPA = totalGp / totalCraditHour;
//			System.out.println(totalGp);
//		}
//		
//		float CGPA=0;
//		if (this.paperRepository.selectTotalsGradPoints(rollNo, batch) != null) {
//			String firstsemester="1st";
//			float totalCraditHour2 = this.subjectRepo.selectTotalsCraditHours(firstsemester,semester);
//			Float totalGp = this.paperRepository.selectTotalsGradPoints(rollNo,batch);
//			CGPA = totalGp / totalCraditHour2;	
//			System.out.println(totalGp);
//		}
//		saveGPAData(batch,semester, rollNo, GPA, CGPA);
		updateAllStudentGPARecord(rollNo,batch);
		this.paperRepository.updatePaperStatus(id, "");
		session.setAttribute("message", new Message("Paper Records Updated Successfully!!", "alert-success"));
		redirectAttributes.addAttribute("page", page);
		redirectAttributes.addAttribute("batch", batch);
		redirectAttributes.addAttribute("semester", semester);
		return "redirect:/examination/result-list";

}
	
	

	@PostMapping("/paper-repeat")
	public String paperRepeat(@RequestParam("id") int id,AccountingRecord accountingRecord ,RedirectAttributes redirectAttributes,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "rollNo", required = false, defaultValue = "Bcs17-33") String rollNo,
			@RequestParam(value = "batch", required = false, defaultValue = "Bcs-17") String batch,
			@RequestParam(value = "semester", required = false, defaultValue = "1st") String semester,
			@RequestParam(value = "currentSemester", required = false, defaultValue = "currentSemester") String currentSemester,
			StudentFeeRcord studentFeeRcord, ResultList resultList, ExamList examList, Model model, HttpSession session)
			throws IOException {
		
		if(checkCraditHourOFRepeatedPaper(resultList,semester, currentSemester, rollNo,session)==true) {
			this.paperRepository.updatePaperStatus(id, "Repeated");
			studentFeeRcord.setFeeType("Repeat");
//			Students students=new Students();
//			students.setId(id);
//			accountingRecord.setStudentsId(students);
//			accountingRecord.setTotalAmount(studentFeeRcord.getTotalFee());
//			accountingRecord.setSubmitAmount(studentFeeRcord.getSubmittedFee());
//			accountingRecord.setType("Paper Repeat");
//			this.acccountingRepo.save(accountingRecord);
			StudentFeeRcord studentFeeRcord2 = this.studentFeeRecordRepo.save(studentFeeRcord);
			examList.setPaperName(resultList.getPaperName());
			examList.setPaperStatus("Repeat");
			examList.setExam("New Result");
			examList.setListDate(studentFeeRcord.getFeeSumbmittedDate());
			this.examListRepository.save(examList);
			System.out.println(studentFeeRcord2);
			session.setAttribute("message", new Message("Paper Repeate Records Inserted Successfully!!", "alert-success"));
		}else {
			session.setAttribute("message", new Message("Your Cradit Hour Is Increased From Current Semester Required", "alert-danger"));

		}		
		
		
		redirectAttributes.addAttribute("page", page);
		redirectAttributes.addAttribute("batch", batch);
		redirectAttributes.addAttribute("semester", semester);
		return "redirect:/examination/result-list";

	}

	@PostMapping("/paper-improve")
	public String paperImprove(@RequestParam("id") int id,AccountingRecord accountingRecord, RedirectAttributes redirectAttributes,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "rollNo", required = false, defaultValue = "Bcs17-33") String rollNo,
			@RequestParam(value = "batch", required = false, defaultValue = "Bcs-17") String batch,
			@RequestParam(value = "semester", required = false, defaultValue = "1st") String semester,
			@RequestParam(value = "currentSemester", required = false, defaultValue = "currentSemester") String currentSemester,
			@RequestParam("paperName") String paperName, @RequestParam("courseCode") String courseCode,
			StudentFeeRcord studentFeeRcord, ResultList resultList, ExamList examList, Model model, HttpSession session)
			throws IOException {
		if(checkCraditHourOFRepeatedPaper(resultList, semester, currentSemester, rollNo, session)) {
		this.paperRepository.updatePaperStatus(id, "Improved");
		studentFeeRcord.setFeeType("Improve");
//		Students students=new Students();
//		students.setId(id);
//		accountingRecord.setStudentsId(students);
//		accountingRecord.setTotalAmount(studentFeeRcord.getTotalFee());
//		accountingRecord.setSubmitAmount(studentFeeRcord.getSubmittedFee());
//		accountingRecord.setType("Paper Repeat");
//		this.acccountingRepo.save(accountingRecord);
		StudentFeeRcord studentFeeRcord2 = this.studentFeeRecordRepo.save(studentFeeRcord);
		examList.setPaperName(paperName);
		examList.setCourseCode(courseCode);
		examList.setPaperStatus("Improve");
		examList.setExam("New Result");
		examList.setListDate(studentFeeRcord.getFeeSumbmittedDate());
		this.examListRepository.save(examList);
		System.out.println(studentFeeRcord2);
		session.setAttribute("message", new Message("Paper Improved Records Inserted Successfully!!", "alert-success"));
		}else {
			session.setAttribute("message", new Message("Your Cradit Hour Is Increased From Current Semester Required", "alert-danger"));

		}
		redirectAttributes.addAttribute("page", page);
		redirectAttributes.addAttribute("exam", examList.getExam());
		redirectAttributes.addAttribute("batch", batch);
		redirectAttributes.addAttribute("semester", semester);
		return "redirect:/examination/result-list";

	}

	@RequestMapping("/exam-dashboard")
	public String exameDashBoard(Model model, Principal principal) {
		model.addAttribute("title", "User Exam List Page");

		return "examination/exam-dashboard";
	}

	@RequestMapping("/exam-list")
	public String exameList(@RequestParam("page") int page, @RequestParam(value="date",defaultValue="01/02/2020") String listDate,
			@RequestParam("exam") String exam, RedirectAttributes redirectAttributes, Model model,
			ExamListRepository examListRepository, ExamList examList, Principal principal) {
		model.addAttribute("title", "User Exam List Page");
		Pageable pageable = PageRequest.of(page, 10);
		Page<ExamList> allExamList = this.examListRepository.getAllExamList(exam, listDate, pageable);
		model.addAttribute("examList", allExamList);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", allExamList.getTotalPages());

		model.addAttribute("exam", examList.getExam());
		return "examination/exam-list";
	}

	@RequestMapping("/exam-new-list")
	public String exameNewList(@RequestParam("page") int page, @RequestParam("exam") String exam,
			RedirectAttributes redirectAttributes, Model model, ExamListRepository examListRepository,
			ExamList examList, Principal principal) {
		model.addAttribute("title", "User Exam List Page");
		Pageable pageable = PageRequest.of(page, 10);
		Page<ExamList> allExamList = this.examListRepository.getAllExamList(exam, pageable);
		model.addAttribute("examList", allExamList);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", allExamList.getTotalPages());

		model.addAttribute("exam", examList.getExam());
		return "examination/exam-list";
	}

	@RequestMapping("/date-sheet")
	public String dateSheet(Model model, DateSheetRecords dateSheetRecords, DateSheetHeader dateSheetHeader)
			throws ParseException {
		model.addAttribute("title", "This is Date Sheet Page");

		List<DateSheetRecords> dateSheetRecords2 = this.dateSheetRepo.getAllDateSheetRecords();
		List<Subject> subjects = this.subjectRepo.getAllSubject();
		model.addAttribute("subjects", subjects);
		model.addAttribute("dateSheetRecords", dateSheetRecords2);
		List<DateSheetHeader> dateSheetHeaders = this.dateSheetHeaderRepo.getAllDateSheetHeader();
		model.addAttribute("dateSheetHeaders", dateSheetHeaders);
		return "examination/date-sheet";
	}

	@RequestMapping("/submit-date-sheet")
	public String submitDateSheet(Model model, @RequestParam("id") int id, DateSheetRecords dateSheetRecords) {
		model.addAttribute("title", "This is Date Sheet Page");

		this.dateSheetRepo.updateDateSheetRecords(id, dateSheetRecords.getFirstPaperNameOne(),
				dateSheetRecords.getFirstPaperNameTwo(), dateSheetRecords.getSecondPaperNameOne(),
				dateSheetRecords.getSecondPaperNameTwo(), dateSheetRecords.getSemester(), dateSheetRecords.getDate());
		this.dutyRosterRepo.updateDutyRosterDate(id, dateSheetRecords.getDate());

		model.addAttribute("message", "Date Sheet Record Inserted Successfully");
		return "redirect:/examination/date-sheet";
	}

	@RequestMapping("/submit-date-sheet-header")
	public String submitDateSheetHeader(Model model, DateSheetHeader dateSheetHeader) {
		model.addAttribute("title", "This is Date Sheet Page");

		this.dateSheetHeaderRepo.updateDateSheetHeader(dateSheetHeader.getDateSheetDate(),
				dateSheetHeader.getDepartment(), dateSheetHeader.getDateSheetType(),
				dateSheetHeader.getFirstShiftTime(), dateSheetHeader.getSecondShiftTime());

		model.addAttribute("message", "Date Sheet Record Inserted Successfully");
		return "redirect:/examination/date-sheet";
	}

	@RequestMapping("/attendence-sheet")
	public String attendenceSheet(@RequestParam("page") int page, @RequestParam("exam") String exam,
			RedirectAttributes redirectAttributes, Model model, ExamListRepository examListRepository,
			ExamList examList, Principal principal) {
		model.addAttribute("title", "User Attendence Sheet Page");
		Pageable pageable = PageRequest.of(page, 20);
		Page<ExamList> allExamList = this.examListRepository.getAllExamList(exam, pageable);
		int i = (int) allExamList.getTotalElements();
		examList.setId(i);
		model.addAttribute("examList", allExamList);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", allExamList.getTotalPages());

		model.addAttribute("exam", examList.getExam());

		return "examination/attendence-sheet";
	}
	
	@RequestMapping("/attendance-sheet-list")
	public String attendenceSheetList(Model model,@RequestParam("batch")String batch) {
		model.addAttribute("title", "User Attendence Sheet Page");
		List<Students> students=this.studentsRepository.getAllStudentsByBatch(batch);
		model.addAttribute("students",students);

		return "examination/attendence-sheet-list";
	}

	@RequestMapping("/clear-list")
	public String clearList(@RequestParam("page") int page, @RequestParam("exam") String exam, Model model,
			ExamOldList examOldList, DateSheetHeader dateSheetHeader, RedirectAttributes redirectAttributes) {
		model.addAttribute("title", "This is clear List Page");
		
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDateTime now = LocalDateTime.now();
		examOldList.setListDate(dtf.format(now).toString());
		examOldList.setExam("Old Result");
		examOldListRepo.save(examOldList);
		examListRepository.updateListeDate(dtf.format(now).toString());
		examListRepository.updatePaperStatus("Old Result");
		System.out.println(dtf.format(now).toString());

		model.addAttribute("message", "Date Sheet Record Inserted Successfully");
		redirectAttributes.addAttribute("page", page);
		redirectAttributes.addAttribute("exam", exam);
		return "redirect:/examination/exam-list";
	}

	@RequestMapping("/exam-old-dash")
	public String examOldDashboard(@RequestParam("page") int page, Model model, Principal principal) {
		model.addAttribute("title", "User Exam Old List Page");
		Pageable pageable = PageRequest.of(page, 10);
		Page<ExamOldList> examOldLists = this.examOldListRepo.getAllOldExamList(pageable);

		model.addAttribute("examOldList", examOldLists);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", examOldLists.getTotalPages());

		return "examination/exam-old-dashboard";
	}

	@RequestMapping("duty-roaster-list")
	public String dutyRoasterList(Model model, Teacher teacher)
			throws ParseException {
		model.addAttribute("title", "This is Duty Roster List Page");

		List<Teacher> teachers=this.teacherRepo.getAllTeacher();
		model.addAttribute("teachers", teachers);
		List<DateSheetHeader> dateSheetHeaders = this.dateSheetHeaderRepo.getAllDateSheetHeader();
		model.addAttribute("dateSheetHeaders", dateSheetHeaders);
		
		List<DutyRosterRecord> dutyRosterRecords=this.dutyRosterRepo.getAllDutyRosterRecord();
		model.addAttribute("dutyRosterRecords",dutyRosterRecords);

		return "examination/duty-roaster-list";
	}
	
	@RequestMapping("/submit-duty-roster")
	public String submitDutyRoster(Model model, @RequestParam("id") int id, DutyRosterRecord dutyRosterRecord) {
		model.addAttribute("title", "This is Duty Roster Record Sheet Page");

		this.dutyRosterRepo.updateDutyRosterRecord(id, dutyRosterRecord.getFirstTeacherNameOne(),
				dutyRosterRecord.getFirstTeacherNameTwo(), dutyRosterRecord.getFirstTeacherNameThree(),
				dutyRosterRecord.getSecondTeacherNameOne(), dutyRosterRecord.getSecondTeacherNameTwo(),
				dutyRosterRecord.getSecondTeacherNameThree());

		model.addAttribute("message", "Duty Roster Record Inserted Successfully");
		return "redirect:/examination/duty-roaster-list";
	}

	@RequestMapping("result-print-page")
	public String printPage(Model model,
			@RequestParam("semester") String semester,
			@RequestParam("batch") String batch) {
		
		model.addAttribute("title", "Print Page");
		List<Subject> subjects = this.subjectRepo.getSubjectBySemester(semester);
		model.addAttribute("subjects", subjects);
		List<GPARecord> allGpaRecords=this.gpaRecordRepo.getAllGPARecordList(semester, batch);
		model.addAttribute("allGpaRecords",allGpaRecords);
		

		List<ResultList> resultLists = this.paperRepository.getAllRsultListBySemesterAndBatch(semester, batch);
		model.addAttribute("resultLists", resultLists);
		
		
		
		List<ResultList> resultLists1 = this.paperRepository.getAllRsultListByCourse(subjects.get(0).getSubjectName(),batch);
		model.addAttribute("resultLists1", resultLists1);
		
		List<ResultList> resultLists2 = this.paperRepository.getAllRsultListByCourse(subjects.get(1).getSubjectName(),batch);
		model.addAttribute("resultLists2", resultLists2);
		
		List<ResultList> resultLists3 = this.paperRepository.getAllRsultListByCourse(subjects.get(2).getSubjectName(),batch);
		model.addAttribute("resultLists3", resultLists3);
		
		List<ResultList> resultLists4 = this.paperRepository.getAllRsultListByCourse(subjects.get(3).getSubjectName(),batch);
		model.addAttribute("resultLists4", resultLists4);
		
		List<ResultList> resultLists5 = this.paperRepository.getAllRsultListByCourse(subjects.get(4).getSubjectName(),batch);
		model.addAttribute("resultLists5", resultLists5);
		int size=subjects.size();
		System.out.println(size);
		if(size>=6)
		{		
		List<ResultList> resultLists6 = this.paperRepository.getAllRsultListByCourse(subjects.get(5).getSubjectName(),batch);
		model.addAttribute("resultLists6", resultLists6);
		}
		model.addAttribute("semester",semester);
		model.addAttribute("batch",batch);
		
		return "examination/print-result-page";
	}

	@RequestMapping("/seat-no-list")
	public String seatNoList(Model model) {
		
		List<DateSheetRecords> dateSheetRecords=this.dateSheetRepo.getAllDateSheetRecords();
		List<DateSheetHeader> dateSheetHeaders=this.dateSheetHeaderRepo.getAllDateSheetHeader();
//		List<Students> studentsbatch=this.studentsRepository.getAllListOfBatchRecord();
		List<Students> batchRecords=this.studentsRepository.getAllListOfBatchRecord();
		model.addAttribute("dateSheetRecords",dateSheetRecords);
		model.addAttribute("dateSheetHeaders",dateSheetHeaders);
		model.addAttribute("batch",batchRecords);
		
		
		return "examination/seat-no-list";
	}

	
	@RequestMapping("/print-seat-no-list")
	public String seatNoTime(Model model,
			@RequestParam("date")String date,
			@RequestParam("time")String time,
			@RequestParam("firstPaper")String firstPaper,
			@RequestParam("secondPaper")String secondPaper,
			@RequestParam("firstBatch")String firstBatch,
			@RequestParam("secondBatch")String secondBatch) {
			
			List<Students> firstStudents=this.studentsRepository.getAllStudentsByBatch(firstBatch,secondBatch);
			//List<Students> secondStudents=this.studentsRepository.getAllStudentsByBatch();
			model.addAttribute("firstStudents",firstStudents);
			//model.addAttribute("secondStudents",secondStudents);
			model.addAttribute("date",date);
			model.addAttribute("time",time);
			model.addAttribute("firstPaper",firstPaper);
			model.addAttribute("secondPaper",secondPaper);
			
		
		
		return "examination/print-seat-no-list";
	}
	
	public float findGrad(int marks) {
		float value = 0;
		if (marks == 50) {
			value = (float) 2.00;
		} else if (marks == 51) {
			value = (float) 2.05;
		} else if (marks == 52) {
			value = (float) 2.10;
		} else if (marks == 53) {
			value = (float) 2.15;
		} else if (marks == 54) {
			value = (float) 2.20;
		} else if (marks == 55) {
			value = (float) 2.25;
		} else if (marks == 56) {
			value = (float) 2.30;
		} else if (marks == 57) {
			value = (float) 2.35;
		} else if (marks == 58) {
			value = (float) 2.40;
		} else if (marks == 59) {
			value = (float) 2.45;
		} else if (marks == 60) {
			value = (float) 2.50;
		} else if (marks == 61) {
			value = (float) 2.55;
		} else if (marks == 62) {
			value = (float) 2.60;
		} else if (marks == 63) {
			value = (float) 2.65;
		} else if (marks == 64) {
			value = (float) 2.70;
		} else if (marks == 65) {
			value = (float) 2.75;
		} else if (marks == 66) {
			value = (float) 2.80;
		} else if (marks == 67) {
			value = (float) 2.85;
		} else if (marks == 68) {
			value = (float) 2.90;
		} else if (marks == 69) {
			value = (float) 2.95;
		} else if (marks == 70) {
			value = (float) 3.00;
		}

		else if (marks == 71) {
			value = (float) 3.05;
		} else if (marks == 72) {
			value = (float) 3.10;
		} else if (marks == 73) {
			value = (float) 3.15;
		} else if (marks == 74) {
			value = (float) 3.20;
		} else if (marks == 75) {
			value = (float) 3.25;
		} else if (marks == 76) {
			value = (float) 3.30;
		} else if (marks == 77) {
			value = (float) 3.35;
		} else if (marks == 78) {
			value = (float) 3.40;
		} else if (marks == 79) {
			value = (float) 3.45;
		} else if (marks == 80) {
			value = (float) 3.50;
		} else if (marks == 81) {
			value = (float) 3.55;
		} else if (marks == 82) {
			value = (float) 3.60;
		} else if (marks == 83) {
			value = (float) 3.65;
		} else if (marks == 84) {
			value = (float) 3.70;
		} else if (marks == 85) {
			value = (float) 3.75;
		} else if (marks == 86) {
			value = (float) 3.80;
		} else if (marks == 87) {
			value = (float) 3.85;
		} else if (marks == 88) {
			value = (float) 3.90;
		} else if (marks == 89) {
			value = (float) 3.95;
		} else if (marks == 90) {
			value = (float) 4.00;
		}
		return value;

	}

	public void saveGPAData(String batch,String semester,String rollNo,float GPA,float CGPA) {
		GPARecord gpaRecord=new GPARecord();
		GPARecord gpaRecord2=new GPARecord();
		if(this.gpaRecordRepo.getAllGPARecord(semester,rollNo)!=null) {
		gpaRecord=this.gpaRecordRepo.getAllGPARecord(semester,rollNo);
		}
		else {
			gpaRecord2.setRollNo(null);
		}
		gpaRecord.setGPA(GPA);
		gpaRecord.setCGPA(CGPA);
		gpaRecord.setRollNo(rollNo);
		gpaRecord.setSemester(semester);
		gpaRecord.setBatch(batch);
		String rollNo1=gpaRecord.getRollNo();
		if(rollNo1.equals(gpaRecord2.getRollNo())) {
			this.gpaRecordRepo.updateGPARecord(GPA,CGPA,rollNo);
		}
		else {
			this.gpaRecordRepo.save(gpaRecord);
		}
		System.out.print(gpaRecord);
	}

	public boolean checkCraditHourOFRepeatedPaper(ResultList resultList,String semester,String currentSemester,String rollNo,HttpSession session) {
		float currentSemesterCraditHour=this.subjectRepo.selectTotalsCraditHours(currentSemester);
		CheckCraditHour checkCH=new CheckCraditHour();
		checkCH.setRollNo(rollNo);
		checkCH.setSemester(currentSemester);
		Subject subject=this.subjectRepo.getSubjectByPaperName(resultList.getPaperName());
		String subjectCraditHour=subject.getSubjectCraditHour();
		float currentSemesterCraditHourPlusSubjectCraditHour=currentSemesterCraditHour+Float.parseFloat(subjectCraditHour);
		checkCH.setSemesterCraditHour(currentSemesterCraditHourPlusSubjectCraditHour);
		CheckCraditHour gpaRecordRollNo = null;
		if(this.checkCraditHourRepo.getAllGPARecord(currentSemester, rollNo)!=null) {
		gpaRecordRollNo=this.checkCraditHourRepo.getAllGPARecord(currentSemester, rollNo);
		System.out.println(gpaRecordRollNo);
		float presentCraditHour=gpaRecordRollNo.getSemesterCraditHour();
		if(presentCraditHour>0) {
		float newCraditHour=presentCraditHour + Float.parseFloat(subjectCraditHour);
		if(newCraditHour<21) {
			this.checkCraditHourRepo.updateGPARecordCraditHour(newCraditHour, rollNo, currentSemester);
			return true;
		}
		else {
			session.setAttribute("message", new Message("Your Cradit Hour Is Increased From Current Semester Required", "alert-danger"));
			return false;
		}
			
		}	
		else {
			if(currentSemesterCraditHourPlusSubjectCraditHour<21) {
				this.checkCraditHourRepo.updateGPARecordCraditHour(currentSemesterCraditHourPlusSubjectCraditHour, rollNo, currentSemester);
				return true;
			}
			else {
				session.setAttribute("message", new Message("Your Cradit Hour Is Increased From Current Semester Required", "alert-danger"));
				return false;
			}
		}
		}else {
			this.checkCraditHourRepo.save(checkCH);
			return true;
		}
		
		
	}

	public void updateAllStudentGPARecord(String rollNo,String batch) {

		if (this.paperRepository.selectTotalsGradPoints(rollNo ,"1st", batch) != null) {
			float totalCraditHour = this.subjectRepo.selectTotalsCraditHours("1st");
			float GPA=0;
			float CGPA=0;
			Float totalGp1 = this.paperRepository.selectTotalsGradPoints(rollNo, "1st", batch);
			GPA = totalGp1 / totalCraditHour;
			
			if (this.paperRepository.selectTotalsGradPoints(rollNo, batch) != null) {
				String firstsemester="1st";
				float totalCraditHour2 = this.subjectRepo.selectTotalsCraditHours(firstsemester,"1st");
				Float totalGp = this.paperRepository.selectTotalsGradPointsBySemester(rollNo,batch,"1st");
				CGPA = totalGp / totalCraditHour2;	
				System.out.println(totalGp);
			}
			saveGPAData(batch,"1st", rollNo, GPA, CGPA);
		}
		
		if (this.paperRepository.selectTotalsGradPoints(rollNo ,"2nd", batch) != null) {
			float totalCraditHour = this.subjectRepo.selectTotalsCraditHours("2nd");
			float GPA=0;
			float CGPA=0;
			Float totalGp1 = this.paperRepository.selectTotalsGradPoints(rollNo, "2nd", batch);
			GPA = totalGp1 / totalCraditHour;
			System.out.println(totalGp1);
			
			if (this.paperRepository.selectTotalsGradPoints(rollNo, batch) != null) {
				String firstsemester="1st";
				float totalCraditHour2 = this.subjectRepo.selectTotalsCraditHours(firstsemester,"2nd");
				Float totalGp = this.paperRepository.selectTotalsGradPointsBySemester(rollNo,batch,"2nd");;
				CGPA = totalGp / totalCraditHour2;	
				System.out.println(totalGp);
			}
			saveGPAData(batch,"2nd", rollNo, GPA, CGPA);
		}
		
		if (this.paperRepository.selectTotalsGradPoints(rollNo ,"3rd", batch) != null) {
			float totalCraditHour = this.subjectRepo.selectTotalsCraditHours("3rd");
			float GPA=0;
			float CGPA=0;
			Float totalGp1 = this.paperRepository.selectTotalsGradPoints(rollNo, "3rd", batch);
			GPA = totalGp1 / totalCraditHour;
			System.out.println(totalGp1);
			
			if (this.paperRepository.selectTotalsGradPoints(rollNo, batch) != null) {
				String firstsemester="1st";
				float totalCraditHour2 = this.subjectRepo.selectTotalsCraditHours(firstsemester,"3rd");
				Float totalGp = this.paperRepository.selectTotalsGradPointsBySemester(rollNo,batch,"3rd");;
				CGPA = totalGp / totalCraditHour2;	
				System.out.println(totalGp);
			}
			saveGPAData(batch,"3rd", rollNo, GPA, CGPA);
		}
		
		if (this.paperRepository.selectTotalsGradPoints(rollNo ,"4th", batch) != null) {
			float totalCraditHour = this.subjectRepo.selectTotalsCraditHours("4th");
			float GPA=0;
			float CGPA=0;
			Float totalGp1 = this.paperRepository.selectTotalsGradPoints(rollNo, "4th", batch);
			GPA = totalGp1 / totalCraditHour;
			System.out.println(totalGp1);
			
			if (this.paperRepository.selectTotalsGradPoints(rollNo, batch) != null) {
				String firstsemester="1st";
				float totalCraditHour2 = this.subjectRepo.selectTotalsCraditHours(firstsemester,"4th");
				Float totalGp = this.paperRepository.selectTotalsGradPointsBySemester(rollNo,batch,"4th");;
				CGPA = totalGp / totalCraditHour2;	
				System.out.println(totalGp);
			}
			saveGPAData(batch,"4th", rollNo, GPA, CGPA);
		}
		
		if (this.paperRepository.selectTotalsGradPoints(rollNo ,"5th", batch) != null) {
			float totalCraditHour = this.subjectRepo.selectTotalsCraditHours("5th");
			float GPA=0;
			float CGPA=0;
			Float totalGp1 = this.paperRepository.selectTotalsGradPoints(rollNo, "5th", batch);
			GPA = totalGp1 / totalCraditHour;
			System.out.println(totalGp1);
			
			if (this.paperRepository.selectTotalsGradPoints(rollNo, batch) != null) {
				String firstsemester="1st";
				float totalCraditHour2 = this.subjectRepo.selectTotalsCraditHours(firstsemester,"5th");
				Float totalGp = this.paperRepository.selectTotalsGradPointsBySemester(rollNo,batch,"5th");;
				CGPA = totalGp / totalCraditHour2;	
				System.out.println(totalGp);
			}
			saveGPAData(batch,"5th", rollNo, GPA, CGPA);
		}
		
		if (this.paperRepository.selectTotalsGradPoints(rollNo ,"6th", batch) != null) {
			float totalCraditHour = this.subjectRepo.selectTotalsCraditHours("6th");
			float GPA=0;
			float CGPA=0;
			Float totalGp1 = this.paperRepository.selectTotalsGradPoints(rollNo, "6th", batch);
			GPA = totalGp1 / totalCraditHour;
			System.out.println(totalGp1);
			
			if (this.paperRepository.selectTotalsGradPoints(rollNo, batch) != null) {
				String firstsemester="1st";
				float totalCraditHour2 = this.subjectRepo.selectTotalsCraditHours(firstsemester,"6th");
				Float totalGp = this.paperRepository.selectTotalsGradPointsBySemester(rollNo,batch,"6th");;
				CGPA = totalGp / totalCraditHour2;	
				System.out.println(totalGp);
			}
			saveGPAData(batch,"6th", rollNo, GPA, CGPA);
		}
		
		if (this.paperRepository.selectTotalsGradPoints(rollNo ,"7th", batch) != null) {
			float totalCraditHour = this.subjectRepo.selectTotalsCraditHours("7th");
			float GPA=0;
			float CGPA=0;
			Float totalGp1 = this.paperRepository.selectTotalsGradPoints(rollNo, "7th", batch);
			GPA = totalGp1 / totalCraditHour;
			System.out.println(totalGp1);
			
			if (this.paperRepository.selectTotalsGradPoints(rollNo, batch) != null) {
				String firstsemester="1st";
				float totalCraditHour2 = this.subjectRepo.selectTotalsCraditHours(firstsemester,"7th");
				Float totalGp = this.paperRepository.selectTotalsGradPointsBySemester(rollNo,batch,"7th");;
				CGPA = totalGp / totalCraditHour2;	
				System.out.println(totalGp);
			}
			saveGPAData(batch,"7th", rollNo, GPA, CGPA);
		}
		
		if (this.paperRepository.selectTotalsGradPoints(rollNo ,"8th", batch) != null) {
			float totalCraditHour = this.subjectRepo.selectTotalsCraditHours("8th");
			float GPA=0;
			float CGPA=0;
			Float totalGp1 = this.paperRepository.selectTotalsGradPoints(rollNo, "8th", batch);
			GPA = totalGp1 / totalCraditHour;
			System.out.println(totalGp1);
			
			if (this.paperRepository.selectTotalsGradPoints(rollNo, batch) != null) {
				String firstsemester="1st";
				float totalCraditHour2 = this.subjectRepo.selectTotalsCraditHours(firstsemester,"8th");
				Float totalGp = this.paperRepository.selectTotalsGradPointsBySemester(rollNo,batch,"8th");;
				CGPA = totalGp / totalCraditHour2;	
				System.out.println(totalGp);
			}
			saveGPAData(batch,"8th", rollNo, GPA, CGPA);
		}
		
	}



}
