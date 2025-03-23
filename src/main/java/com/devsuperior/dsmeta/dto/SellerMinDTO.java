package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Seller;

public class SellerMinDTO {

    private String name;
    private Double total;

    public SellerMinDTO() {
    }

    public SellerMinDTO(String name, Double total) {
        this.name = name;
        this.total = total;
    }

    public SellerMinDTO(Seller entity) {
        this.name = entity.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

}
