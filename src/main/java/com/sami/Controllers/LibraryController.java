package com.sami.Controllers;

import java.security.Principal;
import java.util.Date;
import java.util.List;

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
import com.sami.dao.BookIssueRepo;
import com.sami.dao.BookRepo;
import com.sami.dao.StudentsRepository;
import com.sami.dao.UserRepository;
import com.sami.entities.Book;
import com.sami.entities.BookIssued;
import com.sami.entities.Students;
import com.sami.entities.User;
import com.sami.helper.Message;

@Controller
@RequestMapping("/Library")
public class LibraryController {
	
	
	
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BookRepo bookRepo;
	@Autowired
	private BookIssueRepo bookIssueRepo;
	@Autowired
	private StudentsRepository studentsRepository;
	
	
	
	@ModelAttribute
	public void addCommonData(Model model, Principal principal) {
		
		String userName = principal.getName();
		User user = userRepository.getUserByUserName(userName);
		
		model.addAttribute(user);
		
	}
	
	@RequestMapping("/index")
	public String dashboard(Model model) {
		
		model.addAttribute("title","This is Library Dashboard Page");
		int totalIssuedBooks= this.bookIssueRepo.getTotalIssuedBooks();
		int totalBooks=(int) this.bookRepo.count();
		model.addAttribute("totalBooks",totalBooks);
		model.addAttribute("totalIssuedBooks",totalIssuedBooks);
		return "Library/library-home";
	}
	
	@RequestMapping("/book-record")
	public String addBook(Model model,@RequestParam(value = "page",defaultValue = "0")int page,Book book) {
		model.addAttribute("title","This is Add Book Page");
		Pageable pageable = PageRequest.of(page, 10);

		Page<Book> books = this.bookRepo.getAllBooks(pageable);
		model.addAttribute("books",books);
		List<Students> students=this.studentsRepository.findAll();
		model.addAttribute("students", students);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", books.getTotalPages());
		
		return "Library/book-record";
	}
	
	@RequestMapping("/search-book-record")
	public String searchBook(Model model,@RequestParam(value = "page",
	defaultValue = "0")int page,@RequestParam(value = "id",
	defaultValue = "0")int id,Book book) {
		model.addAttribute("title","This is Add Book Page");
		Pageable pageable = PageRequest.of(page, 10);

		Page<Book> books = this.bookRepo.getBooksById(pageable,id);
		model.addAttribute("books",books);
		List<Students> students=this.studentsRepository.findAll();
		model.addAttribute("students", students);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", books.getTotalPages());
		
		return "Library/book-record";
	}
	
	
	@RequestMapping("/add-book-submit")
	public String addBookSubmit(Model model,Book book,HttpSession session) {
		model.addAttribute("title","This is Add Book Page");
		book.setTotalQuantity(book.getQuantity());
		Book book2=this.bookRepo.save(book);
		session.setAttribute("message", new Message("Book Added Successfully!", "alert-success"));

		System.out.print(book2);
		return "redirect:/Library/book-record";
	}
	
	@RequestMapping("/issue-book")
	public String issueBook(Model model,BookIssued bookIssued,Book book,Students students) {
		model.addAttribute("title","This is Add Book Page");
		int id=bookIssued.getBookId().getId();
		book=this.bookRepo.getOne(id);
		if(book.getQuantity()==1) {
		this.bookRepo.updateBookRecord("Not Available",id );
		this.bookRepo.updateBookRecordOfQuantity(book.getQuantity()-1, id);
		}else {
	
			this.bookRepo.updateBookRecordOfQuantity(book.getQuantity()-1, id);
		}
        Date date=new Date();
		bookIssued.setIssueDate(date);
		bookIssued.setStatus("Issued");
		
		this.bookIssueRepo.save(bookIssued);
		
		return "redirect:/Library/book-record?page=0";
	}
	
	@RequestMapping("/return-book")
	public String returnBook(Model model,BookIssued bookIssued,Book book,@RequestParam("id")Book bookId) {
		model.addAttribute("title","This is Add Book Page");
		int id=bookId.getId();
		Book book2=this.bookRepo.getOne(id);
		this.bookRepo.updateBookRecordOfQuantity(book2.getQuantity()+1, id);
		this.bookRepo.updateBookRecord("Available",id);
//        Date date=new Date();
        book.setId(bookId.getId());
		//this.bookIssueRepo.updateBookIssueRecord(date,"Return",book);
		
		return "redirect:/Library/book-record?page=0";
	}
	
	@RequestMapping("/return-book-by-click")
	public String returnBookByClick(Model model,BookIssued bookIssued,Book book,
			@RequestParam(value="bookId",defaultValue = "0")Book bookId,
			@RequestParam(value="id",defaultValue = "0")int bookIssuedId,
			@RequestParam(value="studentId",defaultValue = "0")Students studentId,
			RedirectAttributes redirectAttributes) {
		model.addAttribute("title","This is Add Book Page");
		int id=bookId.getId();
		Book book2=this.bookRepo.getOne(id);
		this.bookRepo.updateBookRecordOfQuantity(book2.getQuantity()+1, id);
		this.bookRepo.updateBookRecord("Available",id);
        Date date=new Date();
        book.setId(bookId.getId());
		this.bookIssueRepo.updateBookIssueRecord(date,"Return",bookIssuedId);
		redirectAttributes.addAttribute("id",studentId);
		return "redirect:/Library/member-books";
	}
	
	@RequestMapping("/Issue-book")
	public String issueBook(Model model,@RequestParam(value = "page",defaultValue = "0")int page,Book book) {
		model.addAttribute("title","This is Add Book Page");
		Pageable pageable = PageRequest.of(page, 6);

		Page<BookIssued> issuedBooks = this.bookIssueRepo.getAllIssuedBooksRecord(pageable);
		System.out.print(issuedBooks);
		model.addAttribute("issuedBooks",issuedBooks);
		
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", issuedBooks.getTotalPages());
		
		return "Library/Issue-book";
	}
	@RequestMapping("/member-issued-books")
	public String memberIssuedBooks(Model model,@RequestParam(value = "page",defaultValue = "0")int page,Book book) {
		model.addAttribute("title","This is member-issued-books Page");
		Pageable pageable = PageRequest.of(page, 6);

		Page<BookIssued> issuedBooks = this.bookIssueRepo.getAllIssuedBooksRecordByGroup(pageable);
		model.addAttribute("issuedBooks",issuedBooks);
		model.addAttribute("totalBooks",issuedBooks);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", issuedBooks.getTotalPages());
		return "Library/member-issued-books";
	}
	
	@RequestMapping("/member-books")
	public String memberBooks(Model model,@RequestParam(value = "page",defaultValue = "0")int page,
			Book book,@RequestParam("id") Students id,RedirectAttributes redirectAttributes) {
		model.addAttribute("title","This is member-issued-books Page");
		Pageable pageable = PageRequest.of(page, 6);

		Page<BookIssued> issuedBooks = this.bookIssueRepo.getAllIssuedBooksRecordById(pageable,id);
		model.addAttribute("issuedBooks",issuedBooks);
		model.addAttribute("totalBooks",issuedBooks);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", issuedBooks.getTotalPages());
		return "Library/member-books";
	}
	
}
