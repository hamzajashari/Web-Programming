package com.onlineshop.onlineshop.bootstrap;
import com.onlineshop.onlineshop.model.Category;
import lombok.Getter;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class DataHolder {

    public static List<Category> categoryList = new ArrayList<>();

    @PostConstruct
    public void init(){
        categoryList.add(new Category("Books","Books category"));
        categoryList.add(new Category("Movies","Movies category"));
    }

}
