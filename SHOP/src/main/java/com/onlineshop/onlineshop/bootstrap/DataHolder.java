package com.onlineshop.onlineshop.bootstrap;
import com.onlineshop.onlineshop.model.Category;
import com.onlineshop.onlineshop.model.User;
import lombok.Getter;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class DataHolder {

    public static List<Category> categoryList = new ArrayList<>();
    public static List<User> userList=new ArrayList<>();
    @PostConstruct
    public void init(){
        categoryList.add(new Category("Books","Books category"));
        categoryList.add(new Category("Movies","Movies category"));
        userList.add(new User("hamza","wp","Hamza","Jashari"));
        userList.add(new User("hamzaj","pw","Hamza","Jashari"));
    }

}
