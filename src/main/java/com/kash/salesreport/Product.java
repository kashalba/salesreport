package com.kash.salesreport;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "product")
public class Product implements Comparable<Product> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min = 3, max = 20)
    private String name;

    public Product() {
        super();
    }

    public Product(String name) {
        super();
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long value) {
        this.id = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        boolean equal = this == obj ? true : false;
        if (!equal || getClass() != obj.getClass()) {
            return false;
        }
        Product other = (Product) obj;
        if (id != other.id || !name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Product that) {
        return this.name.equals(that.getName()) ? 0 : 1;
    }
}
