package com.soup.accounttech.repository.model.business;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Entity;

import com.soup.accounttech.repository.model.generic.AbstractHibernateEntity;
import com.soup.accounttech.repository.model.metadata.ParserMetadata;

@Entity
@Table(name = "RD_BANK_ACCOUNT")
public class Bank extends AbstractHibernateEntity<Long> {

	private Long id;
	private String name;
	private String code;
	
	private List<ParserMetadata> metadata;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
	@Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<ParserMetadata> getMetadata() {
		return metadata;
	}
	
	public void setMetadata(List<ParserMetadata> metadata) {
		this.metadata = metadata;
	}
    
}
