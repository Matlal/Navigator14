package se.ltu.student.navigator.navigator14;

/**
 * Created by Navigator on 2014-09-22.
 */
public abstract class ObjectItem {
    public int itemId;
    public String itemName;
    public boolean selected = false;

    public ObjectItem(int itemId, String itemName) {
        this.itemId = itemId;
        this.itemName = itemName;
    }

    public abstract void callBack(Object data);

}

