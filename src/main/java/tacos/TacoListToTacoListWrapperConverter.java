package tacos;

import java.util.ArrayList;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TacoListToTacoListWrapperConverter implements Converter<ArrayList<Taco>, TacoListWrapper> {
    
    @Override
    public TacoListWrapper convert(ArrayList<Taco> source) {
        // Implementation to convert the list to TacoListWrapper
        TacoListWrapper wrapper = new TacoListWrapper();
        wrapper.setTacos(source);
        return wrapper;
    }
}
