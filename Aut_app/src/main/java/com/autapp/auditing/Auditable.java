package com.autapp.auditing;

import java.time.ZonedDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.autapp.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<User> {
	
	@CreatedBy
	@OneToOne(fetch=FetchType.EAGER)
	private User createdBy;
	
	//@CreatedDate
	//@Column(name="created_at",updatable=false)
	//private ZonedDateTime createdAt;
	
	//@LastModifiedDate
	//private ZonedDateTime updateAt;
	
	@LastModifiedBy
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="updated_by")
	private User updatedBy;

}
