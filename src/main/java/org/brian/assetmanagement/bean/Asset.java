/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.brian.assetmanagement.bean;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Kavitha
 */
@Entity
@Table(name = "Asset")
public class Asset {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @Column(name = "type")
    private String type;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "model")
    private String model;

    @Column(name = "serial")
    private String serial;

    @Column(name = "assigned")
    private String assigned;

    @Column(name = "purchaseDate")
    private LocalDate purchaseDate;

    @Column(name = "warranty")
    private String warranty;

    @Column(name = "os")
    private String os;

    @Column(name = "hdSize")
    private String hdSize;

    @Column(name = "ram")
    private String ram;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getAssigned() {
        return assigned;
    }

    public void setAssigned(String assignedTo) {
        this.assigned = assignedTo;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getHdSize() {
        return hdSize;
    }

    public void setHdSize(String hdSize) {
        this.hdSize = hdSize;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    @Override
    public String toString() {
        return "Asset{" + "id=" + id + ", type=" + type +
                ", manufacturer=" + manufacturer + ", model=" + model + 
                ", serial=" + serial + ", assignedTo=" + assigned + 
                ", purchaseDate=" + purchaseDate + ", warranty=" + warranty + 
                ", os=" + os + ", hdSize=" + hdSize + ", ram=" + ram + '}';
    }

}
