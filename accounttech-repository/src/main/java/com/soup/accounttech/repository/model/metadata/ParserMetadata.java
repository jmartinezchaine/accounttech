package com.soup.accounttech.repository.model.metadata;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Entity;

import com.soup.accounttech.repository.model.generic.AbstractHibernateEntity;

@Entity
@Table(name = "PARSER_METADATA")
public class ParserMetadata  extends AbstractHibernateEntity<Long>{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
	
	@Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
	
}
