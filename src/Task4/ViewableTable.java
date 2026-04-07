package Task4;

public class ViewableTable implements Viewable {
    @Override
    public View getView() {
        return new ViewTable();
    }
}