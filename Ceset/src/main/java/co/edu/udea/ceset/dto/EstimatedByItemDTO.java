package co.edu.udea.ceset.dto;

public class  EstimatedByItemDTO {
    private Integer id;

    private ItemDTO idItem;

    private EstimatedDTO idEstimated;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ItemDTO getIdItem() {
        return idItem;
    }

    public void setIdItem(ItemDTO idItem) {
        this.idItem = idItem;
    }

    public EstimatedDTO getIdEstimated() {
        return idEstimated;
    }

    public void setIdEstimated(EstimatedDTO idEstimated) {
        this.idEstimated = idEstimated;
    }
}
