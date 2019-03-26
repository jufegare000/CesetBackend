package co.edu.udea.ceset.dto;

public class ExpenditureByItemDTO {

    private Integer id;
    private ItemDTO idItem;
    private ExpenditureDTO idExpend;


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

    public ExpenditureDTO getIdExpend() {
        return idExpend;
    }

    public void setIdExpend(ExpenditureDTO idExpend) {
        this.idExpend = idExpend;
    }
}
