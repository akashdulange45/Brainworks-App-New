package in.ad.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.ad.main.entity.Admin;
import in.ad.main.repository.AdminRepository;

@Service
public class AdminService implements UserDetailsService{

	@Autowired
	private AdminRepository repository;

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin = repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

		return User.builder().username(admin.getUsername()).password(admin.getPassword()).roles(admin.getRole())
				.build();

	}

}
