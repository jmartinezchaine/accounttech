package com.soup.accounttech.repository.model.metadata;

import java.util.Date;
import java.util.List;

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

    private Long id;
    private Date fromDate;
    
    private int firstRow;
    
    //Columns
    private CellMetadata dateColumn;
    private List<CellMetadata> descriptionColumns;
    private CellMetadata debitAmountColumn;
    private CellMetadata creditAmountColumn;
    
    private CellMetadata numberColumn;
    
    // booleans
    private boolean initialBalance;
    private boolean finalBalance;
    private boolean number;

    
    
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

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public int getFirstRow() {
		return firstRow;
	}

	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}

	public CellMetadata getDateColumn() {
		return dateColumn;
	}

	public void setDateColumn(CellMetadata dateColumn) {
		this.dateColumn = dateColumn;
	}

	public List<CellMetadata> getDescriptionColumns() {
		return descriptionColumns;
	}

	public void setDescriptionColumns(List<CellMetadata> descriptionColumns) {
		this.descriptionColumns = descriptionColumns;
	}

	public CellMetadata getDebitAmountColumn() {
		return debitAmountColumn;
	}

	public void setDebitAmountColumn(CellMetadata debitAmountColumn) {
		this.debitAmountColumn = debitAmountColumn;
	}

	public CellMetadata getCreditAmountColumn() {
		return creditAmountColumn;
	}

	public void setCreditAmountColumn(CellMetadata creditAmountColumn) {
		this.creditAmountColumn = creditAmountColumn;
	}

	public CellMetadata getNumberColumn() {
		return numberColumn;
	}

	public void setNumberColumn(CellMetadata numberColumn) {
		this.numberColumn = numberColumn;
	}

	public boolean isInitialBalance() {
		return initialBalance;
	}

	public void setInitialBalance(boolean initialBalance) {
		this.initialBalance = initialBalance;
	}

	public boolean isFinalBalance() {
		return finalBalance;
	}

	public void setFinalBalance(boolean finalBalance) {
		this.finalBalance = finalBalance;
	}

	public boolean isNumber() {
		return number;
	}

	public void setNumber(boolean number) {
		this.number = number;
	}

}
