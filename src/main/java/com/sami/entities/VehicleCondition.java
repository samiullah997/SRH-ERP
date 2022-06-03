package com.sami.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class VehicleCondition {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int vehicleConditionId;
	private String engine;
	private String body;
	private String tire;
	private String interior;
	@OneToOne(mappedBy = "vehicleConditionId")
	private AsignBusRecord asignBusRecordId;
	public VehicleCondition() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VehicleCondition(int vehicleConditionId, String engine, String body, String tire, String interior,
			AsignBusRecord asignBusRecordId) {
		super();
		this.vehicleConditionId = vehicleConditionId;
		this.engine = engine;
		this.body = body;
		this.tire = tire;
		this.interior = interior;
		this.asignBusRecordId = asignBusRecordId;
	}
	public int getVehicleConditionId() {
		return vehicleConditionId;
	}
	public void setVehicleConditionId(int vehicleConditionId) {
		this.vehicleConditionId = vehicleConditionId;
	}
	public String getEngine() {
		return engine;
	}
	public void setEngine(String engine) {
		this.engine = engine;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getTire() {
		return tire;
	}
	public void setTire(String tire) {
		this.tire = tire;
	}
	public String getInterior() {
		return interior;
	}
	public void setInterior(String interior) {
		this.interior = interior;
	}
	public AsignBusRecord getAsignBusRecordId() {
		return asignBusRecordId;
	}
	public void setAsignBusRecordId(AsignBusRecord asignBusRecordId) {
		this.asignBusRecordId = asignBusRecordId;
	}
	@Override
	public String toString() {
		return "VehicleCondition [vehicleConditionId=" + vehicleConditionId + ", engine=" + engine + ", body=" + body
				+ ", tire=" + tire + ", interior=" + interior + ", asignBusRecordid=" + asignBusRecordId + "]";
	}
	
}
