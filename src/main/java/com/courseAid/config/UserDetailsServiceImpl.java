package com.courseAid.config;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.courseAid.repositories.StaffRepository;
import com.courseAid.repositories.StudentRepository;

import com.courseAid.beans.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private StaffRepository staffRepo;
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		Student student = studentRepo.findByUserName(username);
		
		if(student == null)
		{
			Staff staff = staffRepo.findByUserName(username);
			
			if (staff == null) 
			  {
				System.out.println("User not found:" + username);
				throw new UsernameNotFoundException("User " + username + " was not found in the database");
			  }
			
			
			List<GrantedAuthority> grantList= new ArrayList<GrantedAuthority>();
			for (Role role: staff.getRoles()) 
				{
					grantList.add(new SimpleGrantedAuthority(role.getRolename()));
				}
			
			UserDetails userDetails= (UserDetails) new User(staff.getUserName(), staff.getEncryptedPassword(), grantList);
			return userDetails;
			
			
		}
		//to restrict login to verified students only
		if(student.getEnabled() == Byte.valueOf("0")) {
			throw new UsernameNotFoundException("Student Not Verified yet");
		}
		List<GrantedAuthority> grantList= new ArrayList<GrantedAuthority>();
				
		grantList.add(new SimpleGrantedAuthority(student.getRole().getRolename()));
			
		
		UserDetails userDetails= (UserDetails) new User(student.getUserName(), student.getEncryptedPassword(), grantList);
		return userDetails;
	}
	

}