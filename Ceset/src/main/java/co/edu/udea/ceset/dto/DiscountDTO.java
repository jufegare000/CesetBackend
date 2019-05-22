package co.edu.udea.ceset.dto;

public class DiscountDTO {
    private Integer idDiscount;

    private String description;

    private Integer quantity;

    private Double valuedis;

    private Double total;

    private AcademicActivityDTO idAcad;

    public DiscountDTO() {
    }

    public Integer getIdDiscount() {
        return idDiscount;
    }

    public void setIdDiscount(Integer idDiscount) {
        this.idDiscount = idDiscount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getValuedis() {
        return valuedis;
    }

    public void setValuedis(Double valuedis) {
        this.valuedis = valuedis;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public AcademicActivityDTO getIdAcad() {
        return idAcad;
    }

    public void setIdAcad(AcademicActivityDTO idAcad) {
        this.idAcad = idAcad;
    }
}
