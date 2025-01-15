import java.util.List;
import java.util.Map;
import java.util.Set;

public class ConstructorInjectService {

    private final String name;                   // Primitive
    private final DependencyService dependency;   // Dependency Object
    private final List<String> stringList;        // List with String
    private final List<Integer> integerList;      // List without String
    private final Set<String> stringSet;          // Set with String
    private final Map<String, Integer> stringMap; // Map with String keys
    private final Map<Integer, String> intMap;    // Map without String keys

    public ConstructorInjectService(String name,
                                    DependencyService dependency,
                                    List<String> stringList,
                                    List<Integer> integerList,
                                    Set<String> stringSet,
                                    Map<String, Integer> stringMap,
                                    Map<Integer, String> intMap) {
        this.name = name;
        this.dependency = dependency;
        this.stringList = stringList;
        this.integerList = integerList;
        this.stringSet = stringSet;
        this.stringMap = stringMap;
        this.intMap = intMap;
    }

    public String getName() {
        return name;
    }

    public DependencyService getDependency() {
        return dependency;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public Set<String> getStringSet() {
        return stringSet;
    }

    public List<Integer> getIntegerList() {
        return integerList;
    }

    public Map<Integer, String> getIntMap() {
        return intMap;
    }

    public Map<String, Integer> getStringMap() {
        return stringMap;
    }
}
