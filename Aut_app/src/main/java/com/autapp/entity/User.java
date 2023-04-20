package com.autapp.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer.UserDetailsBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;


import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User  implements UserDetails,Serializable {
	
	@Id
	@GeneratedValue
	@Column(name="user_id")
	private Integer id;
	@Column(name="user_fname")
	private  String firstname;
	@Column(name="user_lname")
	private  String lastname;
	@Column(name="user_email")
	private  String email;
	@Column(name="user_password")
	private  String password;
	
	private int resetPasswordAttempts;
	
	public int getResetPasswordAttempts() {
		return resetPasswordAttempts;
	}
	
	public void setResetPasswordAttempts(int resetPasswordAttempts) {
		this.resetPasswordAttempts=resetPasswordAttempts;
	}
	
	
	
	
	
	
	
	
	
	@OneToMany(mappedBy = "user")
	  public   List<Token> token;
	//@Enumerated(EnumType.STRING)
	  //private Role role;
	  
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="role",joinColumns=@JoinColumn(name="user_id"))
	@Column(name="user_role")
	private Set<String>role;
	
	public Set<String> getRole() {
		return role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Token> getToken() {
		return token;
	}

	public void setToken(List<Token> token) {
		this.token = token;
	}

//	public Role getRole() {
	//	return role;
	//}

	//public void setRole(Role role) {
		//this.role = role;
	//}

	///public String getPassword(){
		//return password; 
	///}
	public void setPassword(String password) {
		this.password = password;
    }
	
	

	public static UserBuilder builder() {
		return new UserBuilder();
	}
	
	public static class UserBuilder{
		private Integer id;
		private String firstname;
		private String lastname;
		private String email;
		private String password;
		private List<Token> token;
		//private Role role;
		private Set<String>role;
		
		public UserBuilder role(Set<String> role) {
			this.role=role;
			return this;
		}

		
		public UserBuilder id(Integer id) {
			this.id=id;
			return this;
		}
		public UserBuilder firstname(String firstname) {
			this.firstname=firstname;
			return this;
		}
		public UserBuilder lastname(String lastname) {
			this.lastname=lastname;
			return this;
		}
		public UserBuilder email(String email) {
			this.email=email;
			return this;
		}
		public UserBuilder password(String password) {
			this.password=password;
			return this;
		}
		public UserBuilder token(List<Token> token) {
			this.token=token;
			return this;
		}
		//public UserBuilder role(Role role) {
			//this.role=role;
			//return this;
		//}
		public User build() {
			User user=new User();
			user.setId(id);
			user.setFirstname(firstname);
			user.setLastname(lastname);
			user.setEmail(email);
			user.setPassword(password);
			user.setToken(token);
			//user.setRole(role);
			user.setRole(role);
			return user;
			
		}
		
	}

	
	




	{/*
	@Id
	@GeneratedValue
	@Column(name="user_id")
	private Integer id;
	
	
	@Column(name="user_fname")
	private  String firstname;
	@Column(name="user_lname")
	private  String lastname;
	@Column(name="user_email")
	private  String email;
	@Column(name="user_password")
	private  String password;
	
	
	
	
	
	
	
 @OneToMany(mappedBy = "user")
	  public   List<Token> token;
	  	
	  
	  @Enumerated(EnumType.STRING)
	  private Role role;
	  
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
   }
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
	   this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Token> getTokens() {
		return tokens;
	}
	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
		  
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
		
	}
	@Override
	public String getPassword() {
		
		return password;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	//builder
	
	public static UserBuilder builder() {
		return new UserBuilder();
	}
	public static class UserBuilder{
		private int id;
		private  String firstname;
		private  String lastname;
		private  String email;
		private  String password;
		private Role role;
		
		public List<Token>tokens;
		
		private UserBuilder() {
			
		}
		
		
		public UserBuilder firstname(String firstname) {
			this.firstname=firstname;
			return this;
		}
		public UserBuilder lastname(String lastname) {
			this.lastname=lastname;
			return this;
		}
		public UserBuilder email(String email) {
			this.email=email;
			return this;
		}
		public UserBuilder password(String password) {
			this.password=password;
			return this;
		}
		public UserBuilder role(Role role) {
			this.role=role;
			return this;
		}
		
		
		
		public User build() {
			return new User();
		}
	}
	  
	  
	  */}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority(role.toString()));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
		
	
	}
