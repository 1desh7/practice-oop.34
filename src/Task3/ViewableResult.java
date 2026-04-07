package Task3;

public class ViewableResult implements Viewable {
    @Override
    public View getView() {
        return new ViewResult();
    }
}