package ng.codeinn.oaunet.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListWrapper{
    @SerializedName("items")
    @Expose
    private List<ItemsModel> items = null;

    public void setItems(List<ItemsModel> items) {
        this.items = items;
    }

    public List<ItemsModel> getItems() {
        return items;
    }
}