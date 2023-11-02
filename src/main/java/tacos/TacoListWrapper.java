package tacos;

import java.util.ArrayList;
import java.util.List;

public class TacoListWrapper {
    private List<Taco> tacos = new ArrayList<>();

    public List<Taco> getTacos() {
        return tacos;
    }

    public void setTacos(List<Taco> tacos) {
        this.tacos = tacos;
    }
}
