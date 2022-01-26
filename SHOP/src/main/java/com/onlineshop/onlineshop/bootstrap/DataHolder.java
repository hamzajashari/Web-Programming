package com.onlineshop.onlineshop.bootstrap;
import com.onlineshop.onlineshop.model.*;
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
    public static List<Manufacturer> manufacturers=new ArrayList<>();
    public static List<Product> products=new ArrayList<>();
    public static List<ShoppingCart> shoppingCarts=new ArrayList<>();

    @PostConstruct
    public void init(){
        categoryList.add(new Category("Books","Books category"));
        categoryList.add(new Category("Movies","Movies category"));
        userList.add(new User("hamza","wp","Hamza","Jashari"));
        userList.add(new User("hamzaj","pw","Hamza","Jashari"));
        Manufacturer manufacturer=new Manufacturer("Nike","MY NY");
        Manufacturer manufacturer1=new Manufacturer("Addidas","MY NY");
        manufacturers.add(manufacturer);
        manufacturers.add(manufacturer1);
        Category category=new Category("Sport","Sport for young");
        categoryList.add(category);
        products.add(new Product("Water",25.8,8,category,manufacturer));
        products.add(new Product("Energy",55.8,1,category,manufacturer));
        products.add(new Product("CocaCola",256.8,6,category,manufacturer));
    }

}
