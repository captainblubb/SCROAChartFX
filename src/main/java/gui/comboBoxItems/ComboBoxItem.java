package gui.comboBoxItems;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public  class ComboBoxItem extends ListCell<String> {

    Label label;

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null || empty) {
            setItem(null);
            setGraphic(null);
        } else {
            setText(item);
            ImageView image = getImageView(item);
            label = new Label("",image);
            setGraphic(label);
        }
    }


    private static ImageView getImageView(String imageName) {
        ImageView imageView = null;

        String urlPath = "ComboboxImages/";

        switch (imageName) {
            case "Rastirgin":
                imageView = new ImageView(new Image(urlPath+imageName + ".png"));
                break;
            case "Rosenbrock":
                imageView = new ImageView(new Image(urlPath+imageName + ".png"));
                break;
            case "Ackley":
                imageView = new ImageView(new Image(urlPath+imageName + ".png"));
                break;
            case "MishrasBird":
                imageView = new ImageView(new Image(urlPath+imageName + ".png"));
            default:
                imageName = null;
        }
        return imageView;
    }
}
