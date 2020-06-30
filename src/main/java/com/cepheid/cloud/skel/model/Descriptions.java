package com.cepheid.cloud.skel.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
/**
 * Descriptions entity.
 */
@Entity
public class Descriptions {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long dId;
	private String Description;
	
	

	@ManyToOne
    @JoinColumn(name="mId", nullable=false)
    private Item item;
 
    public Descriptions() {
    	
    }
    
    public Descriptions(Item i) {
        this.item = i;
    }

	public Descriptions(String string) {
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	public long getdId() {
		return dId;
	}

	public void setdId(long dId) {
		this.dId = dId;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Description == null) ? 0 : Description.hashCode());
		result = prime * result + (int) (dId ^ (dId >>> 32));
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Descriptions other = (Descriptions) obj;
		if (Description == null) {
			if (other.Description != null)
				return false;
		} else if (!Description.equals(other.Description))
			return false;
		if (dId != other.dId)
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		return true;
	}

	
	
	
}
