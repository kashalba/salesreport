package com.kash.salesreport;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

@Entity
@Table(name = "salesentry")
public class SalesEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "salesid")
    private long salesId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "productid")
    private Product product;

    @NotNull
    @Past
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateofsale")
    private Date dateOfSale;

    @NotNull
    @Digits(integer = 5, fraction = 2)
    @Column(name = "saleamount")
    private BigDecimal amount;

    public SalesEntry() {
        super();
    }

    public SalesEntry(Product product, Date dateOfSale, BigDecimal amount) {
        super();
        this.product = product;
        this.dateOfSale = dateOfSale;
        this.amount = amount;
    }

    public long getSalesId() {
        return salesId;
    }

    public void setSalesId(long salesId) {
        this.salesId = salesId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getDateOfSale() {
        return dateOfSale;
    }

    public void setDateOfSale(Date dateOfSale) {
        this.dateOfSale = dateOfSale;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
        result = prime * result
                + ((dateOfSale == null) ? 0 : dateOfSale.hashCode());
        result = prime * result + ((product == null) ? 0 : product.hashCode());
        result = prime * result + (int) (salesId ^ (salesId >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        boolean equal = this == obj ? true : false;
        if (!equal || getClass() != obj.getClass()) {
            return false;
        }
        SalesEntry other = (SalesEntry) obj;
        if (amount == null) {
            if (other.amount != null)
                return false;
        } else if (!amount.equals(other.amount))
            return false;
        if (dateOfSale == null) {
            if (other.dateOfSale != null)
                return false;
        } else if (!dateOfSale.equals(other.dateOfSale))
            return false;
        if (product == null) {
            if (other.product != null)
                return false;
        } else if (!product.equals(other.product))
            return false;
        if (salesId != other.salesId)
            return false;
        return true;
    }
}
