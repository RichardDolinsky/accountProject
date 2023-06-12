package sk.example.accountant.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "fees", uniqueConstraints = {@UniqueConstraint(columnNames = "reasonName4")})
public class Fees {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column (name = "reasonName4", unique = true)
	String reasonName;
	
	@Column(name="fixed_fees_euro")
	int fixedFees;
	
	
	public int getFixedFees() {
		return fixedFees;
	}
	public void setFixedFees(int fixedFees) {
		this.fixedFees = fixedFees;
	}
	public String getReasonName() {
		return reasonName;
	}
	public void setReasonName(String reasonName) {
		this.reasonName = reasonName;
	}
	@Override
	public int hashCode() {
		return Objects.hash(reasonName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fees other = (Fees) obj;
		return Objects.equals(reasonName, other.reasonName);
	}

	
	
}
