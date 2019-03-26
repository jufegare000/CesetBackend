package co.edu.udea.ceset.dto;

public class BudgetByItemDTO {
    private Integer id;

    private BudgetDTO idBudget;

    private ItemDTO idItem;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BudgetDTO getIdBudget() {
        return idBudget;
    }

    public void setIdBudget(BudgetDTO idBudget) {
        this.idBudget = idBudget;
    }

    public ItemDTO getIdItem() {
        return idItem;
    }

    public void setIdItem(ItemDTO idItem) {
        this.idItem = idItem;
    }
}
