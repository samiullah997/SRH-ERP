package com.sami.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class AsignBusRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int asignBusRecordId;
	private String driverName;
	private String driverAddress;
	private String driverPhone;
	private Date asignDate;
	@OneToOne
	@JoinColumn(name = "Bus_id")
	private Bus busId;
	@OneToOne
	@JoinColumn(name = "v_id")
	private VehicleCondition vehicleConditionId;
	public AsignBusRecord() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AsignBusRecord(int asignBusRecordId, String driverName, String driverAddress, String driverPhone,
			Date asignDate, Bus busId, VehicleCondition vehicleConditionId) {
		super();
		this.asignBusRecordId = asignBusRecordId;
		this.driverName = driverName;
		this.driverAddress = driverAddress;
		this.driverPhone = driverPhone;
		this.asignDate = asignDate;
		this.busId = busId;
		this.vehicleConditionId = vehicleConditionId;
	}
	public int getAsignBusRecordId() {
		return asignBusRecordId;
	}
	public void setAsignBusRecordId(int asignBusRecordId) {
		this.asignBusRecordId = asignBusRecordId;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getDriverAddress() {
		return driverAddress;
	}
	public void setDriverAddress(String driverAddress) {
		this.driverAddress = driverAddress;
	}
	public String getDriverPhone() {
		return driverPhone;
	}
	public void setDriverPhone(String driverPhone) {
		this.driverPhone = driverPhone;
	}
	public Date getAsignDate() {
		return asignDate;
	}
	public void setAsignDate(Date asignDate) {
		this.asignDate = asignDate;
	}
	public Bus getBusId() {
		return busId;
	}
	public void setBusId(Bus busId) {
		this.busId = busId;
	}
	public VehicleCondition getVehicleConditionId() {
		return vehicleConditionId;
	}
	public void setVehicleConditionId(VehicleCondition vehicleConditionId) {
		this.vehicleConditionId = vehicleConditionId;
	}
	@Override
	public String toString() {
		return "AsignBusRecord [asignBusRecordId=" + asignBusRecordId + ", driverName=" + driverName
				+ ", driverAddress=" + driverAddress + ", driverPhone=" + driverPhone + ", asignDate=" + asignDate
				+ ", busId=" + busId + ", vehicleConditionId=" + vehicleConditionId + "]";
	}

	

}
