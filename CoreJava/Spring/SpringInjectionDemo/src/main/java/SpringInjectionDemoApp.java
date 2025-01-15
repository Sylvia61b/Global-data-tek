import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringInjectionDemoApp {

    public static void main(String[] args) {
        // Load the Spring context from XML configuration
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Retrieve the bean
        SetterInjectService setterInjectService = context.getBean("setterInjectionService", SetterInjectService.class);
        ConstructorInjectService constructorInjectService = context.getBean("constructorInjectService", ConstructorInjectService.class);

        // Test and display the injected values
        System.out.println("Name: " + setterInjectService.getName());
        System.out.println("Dependency: " + setterInjectService.getDependency().getServiceName());
        System.out.println("String List: " + setterInjectService.getStringList());
        System.out.println("Integer List: " + setterInjectService.getIntegerList());
        System.out.println("String Set: " + setterInjectService.getStringSet());
        System.out.println("String Map: " + setterInjectService.getStringMap());
        System.out.println("Integer Map: " + setterInjectService.getIntMap());

        System.out.println("Name: " + constructorInjectService.getName());
        System.out.println("Dependency: " + constructorInjectService.getDependency().getServiceName());
        System.out.println("String List: " + constructorInjectService.getStringList());
        System.out.println("Integer List: " + constructorInjectService.getIntegerList());
        System.out.println("String Set: " + constructorInjectService.getStringSet());
        System.out.println("String Map: " + constructorInjectService.getStringMap());
        System.out.println("Integer Map: " + constructorInjectService.getIntMap());
    }
}

