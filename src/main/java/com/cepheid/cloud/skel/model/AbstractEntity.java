package com.cepheid.cloud.skel.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import com.cepheid.cloud.skel.enums.ItemState;

@MappedSuperclass
public abstract class AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long mId;
	protected String mName;
	@Enumerated(EnumType.STRING)
	protected ItemState itemState;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="item", cascade = CascadeType.ALL)
    private List<Descriptions> descriptions;

	public List<Descriptions> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(List<Descriptions> descriptions) {
		this.descriptions = descriptions;
	}

	public ItemState getItemState() {
		return itemState;
	}

	public void setItemState(ItemState itemState) {
		this.itemState = itemState;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public Long getId() {
		return mId;
	}

	public void setId(Long id) {
		mId = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemState == null) ? 0 : itemState.hashCode());
		result = prime * result + ((mId == null) ? 0 : mId.hashCode());
		result = prime * result + ((mName == null) ? 0 : mName.hashCode());
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
		AbstractEntity other = (AbstractEntity) obj;
		if (itemState != other.itemState)
			return false;
		if (mId == null) {
			if (other.mId != null)
				return false;
		} else if (!mId.equals(other.mId))
			return false;
		if (mName == null) {
			if (other.mName != null)
				return false;
		} else if (!mName.equals(other.mName))
			return false;
		return true;
	}

}
