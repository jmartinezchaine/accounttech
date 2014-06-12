package com.soup.accounttech.repository.model.business;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Entity;

import com.soup.accounttech.repository.model.generic.AbstractHibernateEntity;
import com.soup.accounttech.repository.model.metadata.ParserMetadata;

@Entity
@Table(name = "ACCOUNTING_SYSTEM")
public class AccountingSystem extends AbstractHibernateEntity<Long> {

	
    private Long id;
	private ParserMetadata metadata;
	
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
    
    public ParserMetadata getMetadata() {
		return metadata;
	}
    
    public void setMetadata(ParserMetadata metadata) {
		this.metadata = metadata;
	}
	
}
