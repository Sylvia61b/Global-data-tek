import java.util.List;
import java.util.Map;
import java.util.Set;

public class SetterInjectService {

    private String name;
    private DependencyService dependency;
    private List<String> stringList;
    private List<Integer> integerList;
    private Set<String> stringSet;
    private Map<String, Integer> stringMap;
    private Map<Integer, String> intMap;

    public void setName(String name) {
        this.name = name;
    }

    public void setDependency(DependencyService dependency) {
        this.dependency = dependency;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public void setIntegerList(List<Integer> integerList) {
        this.integerList = integerList;
    }

    public void setStringSet(Set<String> stringSet) {
        this.stringSet = stringSet;
    }

    public void setStringMap(Map<String, Integer> stringMap) {
        this.stringMap = stringMap;
    }

    public void setIntMap(Map<Integer, String> intMap) {
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

    public List<Integer> getIntegerList() {
        return integerList;
    }

    public Set<String> getStringSet() {
        return stringSet;
    }

    public Map<String, Integer> getStringMap() {
        return stringMap;
    }

    public Map<Integer, String> getIntMap() {
        return intMap;
    }
}
