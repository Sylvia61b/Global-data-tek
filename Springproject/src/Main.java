import javax.management.InvalidApplicationException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class Tea {

    String name;

    public void setName(String name) {
        this.name = name;
    }

    public void prepareTea() {

        System.out.println(name+" is being prepared...");
    }
}

class Restaurant {

    Tea tea;

    public Restaurant(Tea tea) {
        this.tea = tea;
    }

    public void placeOrder() {

        tea.prepareTea();
    }
}

public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("com\\awesome\\beans.xml");

        Restaurant restaurant = context.getBean("restaurantBean", Restaurant.class);

        restaurant.placeOrder();
    }
}
