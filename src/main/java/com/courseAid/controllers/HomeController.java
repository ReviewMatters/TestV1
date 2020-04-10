package com.courseAid.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.courseAid.beans.ConfirmationToken;
import com.courseAid.beans.Course;
import com.courseAid.beans.Instructor;
import com.courseAid.beans.Review;
import com.courseAid.beans.Role;
import com.courseAid.beans.Student;
import com.courseAid.repositories.ConfirmationTokenRepository;
import com.courseAid.repositories.CourseRepository;
import com.courseAid.repositories.InstructorRepository;
import com.courseAid.repositories.ReviewRepository;
import com.courseAid.repositories.RoleRepository;
import com.courseAid.repositories.StudentRepository;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HomeController {

	private StudentRepository studentRepository;
	private InstructorRepository instructorRepository;
	private ReviewRepository reviewRepository;
	private CourseRepository courseRepository;
	private RoleRepository roleRepository;
	private JavaMailSender javaMailSender;
	private ConfirmationTokenRepository confirmationTokenRepository;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/register")
	public String registerPage(Model model) {
		model.addAttribute("student", new Student());
		return "register";
	}

	@GetMapping("/student")
	public String studentHomePage(Model model, Authentication authentication) {

		Student student = studentRepository.findByuserNameIgnoreCase(authentication.getName());
		List<Instructor> professorList = instructorRepository.findAll();
		List<Course> courseList = courseRepository.findAll();
		List<Review> reviewList = reviewRepository.findByStudent(student);
		
		List<Course> courseListFull = courseRepository.findAll();
		model.addAttribute("courseListFull", courseListFull);


		model.addAttribute("student", student);
		model.addAttribute("professorList", professorList);
		model.addAttribute("courseList", courseList);
		model.addAttribute("reviewList", reviewList);
		return "/student/index";
	}

	@GetMapping("/student/mainSearchPage")
	public String mainSearchPage(Model model, Authentication authentication,
			@RequestParam("searchKeyword") String searchKeyword) {

		List<Course> courseList;
		System.out.println("Search keyword is : " + searchKeyword);
		if (searchKeyword == null || searchKeyword == "") {
			courseList = courseRepository.findAll();
		} else {
			courseList = courseRepository.findByNameContainingIgnoreCase(searchKeyword);
		}
		List<Course> courseListFull = courseRepository.findAll();
		model.addAttribute("courseListFull", courseListFull);

		Student student = studentRepository.findByuserNameIgnoreCase(authentication.getName());
		model.addAttribute("student", student);
		model.addAttribute("courseList", courseList);
		return "/student/mainSearchPage";
	}

	@GetMapping("/student/coursePage")
	public String mainSearchPage(Model model, Authentication authentication, @RequestParam("courseId") Long courseId) {

		Course course;
		System.out.println("Course Id : " + courseId);
		if (courseId == null) {
			return "redirect:/student";
		} else {
			course = courseRepository.findById(courseId).get();
		}

		List<Course> courseListFull = courseRepository.findAll();
		model.addAttribute("courseListFull", courseListFull);
		List<Review> reviewList = reviewRepository.findByCourse(course);
		Student student = studentRepository.findByuserNameIgnoreCase(authentication.getName());
		model.addAttribute("student", student);
		model.addAttribute("course", course);
		model.addAttribute("reviewList", reviewList);
		return "/student/coursePage";
	}


	@PostMapping("/register")
	public String doRegistration(Model model, @ModelAttribute Student student) {

		//check if the username is taken
		Student temp = studentRepository.findByUserName(student.getUserName());
		if(temp !=null)
		{
			model.addAttribute("errMsg", "User Name already taken!");
			model.addAttribute("student", student);
			return "/register";
		}
		
		// student should not be enabled by default
//		Student student = Student.builder().firstName(firstname).lastName(lastname).email(email).userName(username)
//				.encryptedPassword(encodePassword(password)).enabled(Byte.valueOf("0")).build();
		student.setEnabled(Byte.valueOf("0"));
		student.setEncryptedPassword(encodePassword(student.getEncryptedPassword()));
		System.out.println("doRegistration start");

		// default for all students
		Role userRole = roleRepository.findByRolename("ROLE_USER");
		System.out.println("userRole is: " + userRole);
		student.setRole(userRole);
		System.out.println("doRegistration Middle");
		studentRepository.save(student);

		ConfirmationToken confirmationToken = new ConfirmationToken(student);
		confirmationTokenRepository.save(confirmationToken);
		sendEmail(student.getEmail(), "CourseAid Confirmation Email", confirmationToken.getConfirmationToken());
		System.out.println("doRegistration about to finish");

		return "redirect:/";
	}

	@GetMapping("/verify-account/{tokenNo}")
	public String confirmStudentAccount(Model model, @PathVariable String tokenNo) {
		ConfirmationToken confirmationToken = confirmationTokenRepository.findByConfirmationToken(tokenNo);
		if (confirmationToken != null) {
			Student student = studentRepository.findByUserName(confirmationToken.getStudent().getUserName());
			if (student != null) {
				student.setEnabled(Byte.valueOf("1")); // enable student
				studentRepository.save(student);
				model.addAttribute("isVerified", true);
				model.addAttribute("student", student);
			}
		}
		return "verifyAccount";
	}

	@GetMapping("/addNewReview/{id}")
	public String addNewReview(Model model, @PathVariable Long id)
	{
		Course course = courseRepository.findById(id).get();
		List<Course> courseListFull = courseRepository.findAll();
		model.addAttribute("courseListFull", courseListFull);
		model.addAttribute("course", course);
		return "student/rate";
	}
	
	@PostMapping("/student/addReview")
	public String addReview(Model model, Authentication authentication, @RequestParam Long instructorId,
			@RequestParam Long courseId, @RequestParam String reviewBody, @RequestParam String title,
			@RequestParam(required = false) Boolean isAnonymous, @RequestParam int rating) {
		Student student = studentRepository.findByuserNameIgnoreCase(authentication.getName());
		Instructor instructor = instructorRepository.findById(instructorId).get();
		Course course = courseRepository.findById(courseId).get();
		
		
		Review review = Review.builder()
				.course(course).instructor(instructor)
				.dateReviewed(LocalDateTime.now())
				.reviewBody(reviewBody).rating(rating)
				.title(title).student(student).build();
		
		//if isAnonymous checkbox is unchecked then make make isAnonymous false otherwise true
		if(isAnonymous == null) {
			review.setIsAnonymous(false);
		}else if(isAnonymous == true) {
			review.setIsAnonymous(true);
		}
		
		reviewRepository.save(review);
		List<Review> reviewList = reviewRepository.findByCourse(course);

		model.addAttribute("student", student);
		model.addAttribute("course", course);
		model.addAttribute("reviewList", reviewList);
		
		return "/student/coursePage";
	}


	private String encodePassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}

	private void sendEmail(String to, String subject, String token) {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		String htmlMsg = "<div style=\"text-align:center;\"><h1 style=\"background:orange;color:white;\">Welcome to CourseAid</h1>"
				+ "<p><b>Click on this link to confirm your email <a href=\"http://localhost:8080/verify-account/"
				+ token + "\">Verify Account</b></p></div>";
		try {
			mimeMessage.setContent(htmlMsg, "text/html"); /** Use this or below line **/
			helper.setTo(to);
			helper.setSubject("This is the test message for testing gmail smtp server using spring mail");

			javaMailSender.send(mimeMessage);
			System.out.println("Email Sent Done");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
